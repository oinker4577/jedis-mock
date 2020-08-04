package com.github.fang.jedismock.operations;

import com.github.fang.jedismock.server.Response;
import com.github.fang.jedismock.server.Slice;
import com.github.fang.jedismock.storage.RedisBase;

import java.util.List;

import static com.github.fang.jedismock.Utils.convertToNonNegativeInteger;

class RO_getbit extends AbstractRedisOperation {
    RO_getbit(RedisBase base, List<Slice> params) {
        super(base, params);
    }

    Slice response() {
        Slice value = base().getValue(params().get(0));
        int pos = convertToNonNegativeInteger(params().get(1).toString());

        if (value == null) {
            return Response.integer(0L);
        }
        if (pos >= value.length() * 8) {
            return Response.integer(0L);
        }
        if ((value.data()[pos / 8] & (1 << (pos % 8))) != 0) {
            return Response.integer(1);
        }
        return Response.integer(0);
    }
}
