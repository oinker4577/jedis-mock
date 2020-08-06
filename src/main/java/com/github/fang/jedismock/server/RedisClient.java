package com.github.fang.jedismock.server;

import com.github.fang.jedismock.storage.OperationExecutorState;
import com.github.fang.jedismock.storage.RedisBase;
import com.github.fang.jedismock.commands.RedisCommand;
import com.github.fang.jedismock.commands.RedisCommandParser;
import com.github.fang.jedismock.Utils;
import com.github.fang.jedismock.exception.ParseErrorException;
import com.google.common.base.Preconditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

;

/**
 * Created by Xiaolu on 2015/4/18.
 */
public class RedisClient implements Runnable {
    private static final Logger LOG = LoggerFactory.getLogger(RedisClient.class);
    private final AtomicBoolean running;
    private final RedisOperationExecutor executor;
    private final Socket socket;
    private final ServiceOptions options;
    private final InputStream in;
    private final OutputStream out;

    RedisClient(Map<Integer, RedisBase> redisBases, Socket socket, ServiceOptions options) throws IOException {
        Preconditions.checkNotNull(redisBases);
        Preconditions.checkNotNull(socket);
        Preconditions.checkNotNull(options);

        OperationExecutorState state = new OperationExecutorState(this, redisBases);
        this.executor = new RedisOperationExecutor(state);
        this.socket = socket;
        this.options = options;
        this.in = socket.getInputStream();
        this.out = socket.getOutputStream();
        this.running = new AtomicBoolean(true);
    }

    public void run() {
        int count = 0;
        while (running.get()) {
            Optional<RedisCommand> command = nextCommand();

            if(command.isPresent()){
                LOG.debug("command: {}", command.get());
                Slice response = executor.execCommand(command.get());
                sendResponse(response, command.toString());

                count++;
                if (options.autoCloseOn() != 0 && options.autoCloseOn() == count) {
                    break;
                }
            } else {
                running.set(false);
            }
        }

        close();
        LOG.debug("Mock redis connection shutting down.");
    }

    /**
     * Gets the next command on the stream if one has been issued
     *
     * @return The next command on the stream if one was issues
     */
    private Optional<RedisCommand> nextCommand(){
        try {
            return Optional.of(RedisCommandParser.parse(in));
        } catch (ParseErrorException e){
            return Optional.empty(); // This simply means there is no next command
        }
    }

    /**
     * Send a response due to a specific command.
     *
     * @param response The respond to send.
     * @param respondingTo The reason for sending this response
     */
    public void sendResponse(Slice response, String respondingTo) {
        try {
            if (!response.equals(Response.SKIP)) {
                out.write(response.data());
            }
        } catch (IOException e){
            LOG.error("unable to send [" + response + "] as response to [" + respondingTo +"]", e);
        }
    }

    /**
     * Close all the streams used by this client effectively closing the client.
     * Also signals the client to stop working.
     */
    public void close(){
        running.set(false);
        Utils.closeQuietly(socket);
        Utils.closeQuietly(in);
        Utils.closeQuietly(out);
    }
}
