package com.github.fang.jedismock.operations;

import com.github.fang.jedismock.storage.RedisBase;
import com.github.fang.jedismock.server.Slice;

import java.util.LinkedList;
import java.util.List;

class RO_lpop extends RO_pop<LinkedList<Slice>> {
    RO_lpop(RedisBase base,List<Slice> params ) {
        super(base, params);
    }

    @Override
    Slice popper(LinkedList<Slice> list) {
        return list.removeFirst();
    }
}
