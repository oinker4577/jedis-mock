package com.github.fang.jedismock.operations;

import com.github.fang.jedismock.server.Response;
import com.github.fang.jedismock.server.Slice;
import com.github.fang.jedismock.storage.RedisBase;

import java.util.List;

class RO_mset extends AbstractRedisOperation {
    RO_mset(RedisBase base, List<Slice> params ) {
        super(base, params);
    }

    Slice response() {
        for (int i = 0; i < params().size(); i += 2) {
            base().putValue(params().get(i), params().get(i + 1));
        }
        return Response.OK;
    }
}
