package com.github.fang.jedismock.operations;

import com.github.fang.jedismock.server.Response;
import com.github.fang.jedismock.server.Slice;
import com.github.fang.jedismock.storage.OperationExecutorState;

public class RO_auth implements RedisOperation {
    private OperationExecutorState state;

    public RO_auth(OperationExecutorState state) {
        this.state = state;
    }

    @Override
    public Slice execute() {
        state.owner().sendResponse(Response.clientResponse("auth", Response.OK), "auth");
        return Response.SKIP;
    }
}
