package com.github.fang.jedismock.operations;

import com.github.fang.jedismock.storage.RedisBase;
import com.github.fang.jedismock.server.Slice;

/**
 * Represents a Redis Operation which can be executed against {@link RedisBase}
 */
public interface RedisOperation {
    Slice execute();
}
