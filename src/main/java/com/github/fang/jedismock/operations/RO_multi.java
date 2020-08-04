package com.github.fang.jedismock.operations;

import com.github.fang.jedismock.server.Response;
import com.github.fang.jedismock.server.Slice;
import com.github.fang.jedismock.storage.OperationExecutorState;

public class RO_multi implements RedisOperation {
    private OperationExecutorState state;

    RO_multi(OperationExecutorState state){
        this.state = state;
    }

    @Override
    public Slice execute() {
        state.newTransaction();
        return Response.OK;
    }
}
