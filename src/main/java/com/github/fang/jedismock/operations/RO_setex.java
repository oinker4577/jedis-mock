package com.github.fang.jedismock.operations;

import com.github.fang.jedismock.storage.RedisBase;
import com.github.fang.jedismock.server.Response;
import com.github.fang.jedismock.server.Slice;

import java.util.List;

import static com.github.fang.jedismock.Utils.convertToLong;

class RO_setex extends RO_set {
    RO_setex(RedisBase base, List<Slice> params) {
        super(base, params);
    }

    long valueToSet(List<Slice> params){
        return convertToLong(new String(params.get(1).data())) * 1000;
    }

    Slice response() {
        base().putValue(params().get(0), params().get(2), valueToSet(params()));
        return Response.OK;
    }
}
