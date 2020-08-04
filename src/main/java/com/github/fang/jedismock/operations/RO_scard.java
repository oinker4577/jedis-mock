package com.github.fang.jedismock.operations;

import com.github.fang.jedismock.server.Response;
import com.github.fang.jedismock.server.Slice;
import com.github.fang.jedismock.storage.RedisBase;

import java.util.List;
import java.util.Set;

class RO_scard extends AbstractRedisOperation {

    RO_scard(RedisBase base, List<Slice> params) {
        super(base, params);
    }

    Slice response() {
        Slice key = params().get(0);
        Set<Slice> set = getDataFromBase(key, null);
        if(set == null || set.isEmpty()) return Response.integer(0);
        return Response.integer(set.size());
    }
}
