package com.github.fang.jedismock.operations;

import com.github.fang.jedismock.server.Response;
import com.github.fang.jedismock.server.Slice;
import com.github.fang.jedismock.storage.RedisBase;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class RO_hvals extends AbstractRedisOperation {
    public RO_hvals(RedisBase base, List<Slice> params) {
        super(base, params);
    }

    @Override
    Slice response() {
        Slice hash = params().get(0);

        Map<Slice, Slice> fieldAndValueMap = base().getFieldsAndValues(hash);
        int arraySize = fieldAndValueMap.size();
        Slice [] fvals = new Slice[arraySize];

        int currentIndex = 0;
        for (Map.Entry<Slice, Slice> entry: fieldAndValueMap.entrySet()){
            fvals[currentIndex] = Response.bulkString(entry.getValue());
            currentIndex++;
        }

        List<Slice> values = Arrays.asList(fvals);
        return Response.array(values);
    }
}
