package com.github.fppt.jedismock.operations;

import com.github.fppt.jedismock.server.Response;
import com.github.fppt.jedismock.server.Slice;
import com.github.fppt.jedismock.storage.RedisBase;

import java.util.List;
import java.util.Set;

import static com.github.fppt.jedismock.Utils.serializeObject;

class RO_srem extends AbstractRedisOperation {


    RO_srem(RedisBase base, List<Slice> params) {
        super(base, params);
    }

    Slice response() {
        Slice key = params().get(0);
        Set<Slice> set = getDataFromBase(key, null);
        if(set == null || set.isEmpty()) return Response.integer(0);
        int count = 0;
        for (int i = 1; i < params().size(); i++) {
            if (set.remove(params().get(i))) {
                count++;
            }
        }
        try {
            base().putValue(key, serializeObject(set));
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        return Response.integer(count);
    }
}
