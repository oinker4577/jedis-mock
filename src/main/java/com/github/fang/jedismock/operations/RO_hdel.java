package com.github.fang.jedismock.operations;

import com.github.fang.jedismock.server.Response;
import com.github.fang.jedismock.server.Slice;
import com.github.fang.jedismock.storage.RedisBase;

import java.util.List;

class RO_hdel extends AbstractRedisOperation {
    RO_hdel(RedisBase base, List<Slice> params) {
        super(base, params);
    }

    Slice response(){
        Slice key1 = params().get(0);
        Slice key2 = params().get(1);

        Slice oldValue = base().getValue(key1, key2);

        base().deleteValue(key1, key2);

        if(oldValue == null){
            return Response.integer(0);
        } else {
            return Response.integer(1);
        }
    }
}
