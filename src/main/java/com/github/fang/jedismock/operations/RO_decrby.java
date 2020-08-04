package com.github.fang.jedismock.operations;

import com.github.fang.jedismock.storage.RedisBase;
import com.github.fang.jedismock.server.Slice;

import java.util.List;

import static com.github.fang.jedismock.Utils.convertToLong;

class RO_decrby extends RO_incrOrDecrBy {
    RO_decrby(RedisBase base, List<Slice> params) {
        super(base, params);
    }

    long incrementOrDecrementValue(List<Slice> params){
        return convertToLong(String.valueOf(params.get(1))) * -1;
    }
}
