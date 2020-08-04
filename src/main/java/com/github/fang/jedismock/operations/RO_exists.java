package com.github.fang.jedismock.operations;

import com.github.fang.jedismock.server.Response;
import com.github.fang.jedismock.server.Slice;
import com.github.fang.jedismock.storage.RedisBase;

import java.util.List;

class RO_exists extends AbstractRedisOperation {
    RO_exists(RedisBase base, List<Slice> params) {
        super(base, params);
    }

    Slice response() {
        if (base().exists(params().get(0))) {
            return Response.integer(1);
        }
        return Response.integer(0);
    }
}
