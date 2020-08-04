package com.github.fang.jedismock.operations;

import com.github.fang.jedismock.server.Response;
import com.github.fang.jedismock.server.Slice;
import com.github.fang.jedismock.storage.RedisBase;

import java.util.List;

class RO_flushall extends AbstractRedisOperation {
    RO_flushall(RedisBase base, List<Slice> params) {
        super(base, params);
    }

    Slice response(){
        base().clear();
        return Response.OK;
    }
}