package com.github.fang.jedismock.operations;

import com.github.fang.jedismock.storage.RedisBase;
import com.github.fang.jedismock.server.Slice;

import java.util.List;

import static com.github.fang.jedismock.Utils.convertToDouble;

class RO_incrbyfloat extends RO_incrOrDecrByFloat {
    RO_incrbyfloat(RedisBase base, List<Slice> params) {
        super(base, params);
    }

    double incrementOrDecrementValue(List<Slice> params){
        return convertToDouble(String.valueOf(params.get(1)));
    }
}
