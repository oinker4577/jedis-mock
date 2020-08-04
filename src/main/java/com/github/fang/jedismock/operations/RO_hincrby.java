package com.github.fang.jedismock.operations;

import com.github.fang.jedismock.server.Response;
import com.github.fang.jedismock.server.Slice;
import com.github.fang.jedismock.storage.RedisBase;

import java.util.List;

import static com.github.fang.jedismock.Utils.convertToLong;

class RO_hincrby extends AbstractRedisOperation {
    RO_hincrby(RedisBase base, List<Slice> params) {
        super(base, params);
    }

    Slice hsetValue(Slice key1, Slice key2, Slice value) {
        long numericValue = convertToLong(String.valueOf(value));
        Slice foundValue = base().getValue(key1, key2);
        if (foundValue != null) {
            numericValue = convertToLong(new String(foundValue.data())) + numericValue;
        }
        base().putValue(key1, key2, Slice.create(String.valueOf(numericValue)), -1L);
        return Response.integer(numericValue);
    }

    Slice response() {
        Slice key1 = params().get(0);
        Slice key2 = params().get(1);
        Slice value = params().get(2);
        return hsetValue(key1, key2, value);
    }
}
