package com.github.fang.jedismock.operations;

import com.github.fang.jedismock.storage.RedisBase;
import com.github.fang.jedismock.server.Slice;

import java.util.List;

import static com.github.fang.jedismock.Utils.convertToLong;

class RO_psetex extends RO_setex {
    RO_psetex(RedisBase base, List<Slice> params) {
        super(base, params);
    }

    @Override
    long valueToSet(List<Slice> params){
        return convertToLong(new String(params.get(1).data()));
    }
}
