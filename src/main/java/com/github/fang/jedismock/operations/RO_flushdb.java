package com.github.fang.jedismock.operations;

import com.github.fang.jedismock.server.Response;
import com.github.fang.jedismock.server.Slice;
import com.github.fang.jedismock.storage.RedisBase;

import java.util.List;

class RO_flushdb extends AbstractRedisOperation {
    RO_flushdb(RedisBase base, List<Slice> params) {
        super(base, params);
    }

    Slice response(){
        base().clear();
        return Response.OK;
    }
}
