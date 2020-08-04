package com.github.fang.jedismock.operations;

import com.github.fang.jedismock.server.Slice;
import com.github.fang.jedismock.storage.RedisBase;

import java.util.List;

class RO_brpop extends RO_bpop {

    RO_brpop(RedisBase base, List<Slice> params) {
        super(base, params);
    }

    @Override
    RO_pop popper(List<Slice> params) {
        return new RO_rpop(base(), params);
    }
}
