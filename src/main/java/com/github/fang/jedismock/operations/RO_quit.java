package com.github.fang.jedismock.operations;

import com.github.fang.jedismock.server.Response;
import com.github.fang.jedismock.server.Slice;
import com.github.fang.jedismock.storage.OperationExecutorState;

public class RO_quit implements RedisOperation {
    private OperationExecutorState state;

    public RO_quit(OperationExecutorState state) {
        this.state = state;
    }

    @Override
    public Slice execute() {
        state.owner().sendResponse(Response.clientResponse("quit", Response.OK), "quit");
        state.owner().close();
        return Response.SKIP;
    }
}
