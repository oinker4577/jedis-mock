package com.github.fppt.jedismock.operations;

import com.github.fppt.jedismock.storage.RedisBase;
import com.github.fppt.jedismock.server.Slice;

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
