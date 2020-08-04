package com.github.fang.jedismock.operations;

import com.github.fang.jedismock.storage.RedisBase;
import com.github.fang.jedismock.server.Slice;

import java.util.List;

class RO_decr extends RO_decrby {
    RO_decr(RedisBase base, List<Slice> params) {
        super(base, params);
    }

    @Override
    long incrementOrDecrementValue(List<Slice> params){
        return -1L;
    }
}
