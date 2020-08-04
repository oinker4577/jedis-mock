package com.github.fang.jedismock.operations;

import com.github.fang.jedismock.storage.RedisBase;
import com.github.fang.jedismock.server.Response;
import com.github.fang.jedismock.server.Slice;

import java.util.List;

class RO_pttl extends RO_ttl {
    RO_pttl(RedisBase base, List<Slice> params) {
        super(base, params);
    }

    Slice finalReturn(Long pttl){
        return Response.integer(pttl);
    }
}
