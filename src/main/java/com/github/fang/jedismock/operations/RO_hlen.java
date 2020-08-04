package com.github.fang.jedismock.operations;

import com.github.fang.jedismock.server.Response;
import com.github.fang.jedismock.server.Slice;
import com.github.fang.jedismock.storage.RedisBase;

import java.util.List;
import java.util.Map;

public class RO_hlen extends AbstractRedisOperation {
    public RO_hlen(RedisBase base, List<Slice> params) {
        super(base, params);
    }

    Slice response() {
        Slice key = params().get(0);
        Map<Slice, Slice> map = base().getFieldsAndValues(key);
        return Response.integer(map.size());
    }
}
