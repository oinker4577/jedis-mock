package com.github.fang.jedismock.operations;

import com.github.fang.jedismock.server.Response;
import com.github.fang.jedismock.server.Slice;
import com.github.fang.jedismock.storage.RedisBase;
import com.google.common.collect.Lists;

import java.util.LinkedList;
import java.util.List;

class RO_llen extends AbstractRedisOperation {
    RO_llen(RedisBase base, List<Slice> params) {
        super(base, params);
    }

    Slice response() {
        Slice key = params().get(0);
        LinkedList<Slice> list = getDataFromBase(key, Lists.newLinkedList());
        return Response.integer(list.size());
    }
}
