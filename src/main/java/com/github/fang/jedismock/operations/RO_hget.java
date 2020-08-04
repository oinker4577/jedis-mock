package com.github.fang.jedismock.operations;

import com.github.fang.jedismock.server.Response;
import com.github.fang.jedismock.server.Slice;
import com.github.fang.jedismock.storage.RedisBase;

import java.util.List;

class RO_hget extends AbstractRedisOperation {
    RO_hget(RedisBase base, List<Slice> params) {
        super(base, params);
    }

    Slice response() {
        return Response.bulkString(base().getValue(params().get(0), params().get(1)));
    }
}
