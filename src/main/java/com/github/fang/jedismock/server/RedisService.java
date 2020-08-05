package com.github.fang.jedismock.server;

import com.github.fang.jedismock.storage.RedisBase;
import com.google.common.base.Preconditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;

/**
 * Created by Xiaolu on 2015/4/21.
 */
public class RedisService implements Runnable {
    private static final Logger LOG = LoggerFactory.getLogger(RedisClient.class);

    private static final int NUM_THREADS = 10;
    private static final ExecutorService executorService
            = Executors.newFixedThreadPool(NUM_THREADS);
    private final ServerSocket server;
    private final Map<Integer, RedisBase> redisBases;
    private final ServiceOptions options;

    public RedisService(ServerSocket server, Map<Integer, RedisBase> redisBases, ServiceOptions options) {
        Preconditions.checkNotNull(server);
        Preconditions.checkNotNull(redisBases);
        Preconditions.checkNotNull(options);

        this.server = server;
        this.redisBases = redisBases;
        this.options = options;
    }

    public void run() {
        while (!server.isClosed()) {
            try {
                Socket socket = server.accept();
                socket.setSoTimeout(500);
//                Thread t = new Thread(new RedisClient(redisBases, socket, options));
//                t.start();
                Runnable task = new RedisClient(redisBases, socket, options);
                executorService.execute(task);
            } catch (IOException e) {
                // Do noting
            }
        }
    }
}
