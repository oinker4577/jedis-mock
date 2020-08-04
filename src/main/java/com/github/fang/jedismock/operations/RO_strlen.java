package com.github.fang.jedismock.operations;

import com.github.fang.jedismock.server.Response;
import com.github.fang.jedismock.server.Slice;
import com.github.fang.jedismock.storage.RedisBase;

import java.util.List;

class RO_strlen extends AbstractRedisOperation {
    RO_strlen(RedisBase base, List<Slice> params) {
        super(base, params);
    }

    Slice response() {
        Slice value = base().getValue(params().get(0));
        if (value == null) {
            return Response.integer(0);
        }
        return Response.integer(value.length());
    }
}
