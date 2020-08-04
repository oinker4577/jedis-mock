package com.github.fang.jedismock.operations;

import com.github.fang.jedismock.storage.RedisBase;
import com.github.fang.jedismock.server.Slice;

import java.util.List;

class RO_incr extends RO_incrby {
    RO_incr(RedisBase base, List<Slice> params) {
        super(base, params);
    }

    @Override
    long incrementOrDecrementValue(List<Slice> params){
        return 1L;
    }
}
