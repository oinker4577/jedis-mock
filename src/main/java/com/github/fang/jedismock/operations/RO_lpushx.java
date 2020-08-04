package com.github.fang.jedismock.operations;

import com.github.fang.jedismock.storage.RedisBase;
import com.github.fang.jedismock.server.Response;
import com.github.fang.jedismock.server.Slice;

import java.util.List;

class RO_lpushx extends RO_lpush {
    RO_lpushx(RedisBase base, List<Slice> params) {
        super(base, params);
    }

    Slice response(){
        Slice key = params().get(0);
        Slice data = base().getValue(key);

        if(data != null){
            return super.response();
        }

        return Response.integer(0);
    }
}
