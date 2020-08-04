package com.github.fang.jedismock.operations;

import com.github.fang.jedismock.server.Response;
import com.github.fang.jedismock.server.Slice;
import com.github.fang.jedismock.storage.RedisBase;

import java.util.List;


public class RO_hexists extends AbstractRedisOperation {
    RO_hexists(RedisBase base, List<Slice> params) {
        super(base, params);
    }

    Slice response() {
        if (base().getValue(params().get(0), params().get(1)) == null){
            return Response.integer(0);
        }
        return Response.integer(1);
    }
}