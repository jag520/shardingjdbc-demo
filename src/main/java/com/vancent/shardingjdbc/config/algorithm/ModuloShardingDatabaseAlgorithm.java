package com.vancent.shardingjdbc.config.algorithm;

import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;

import java.util.Collection;

/**
 * @Description:
 * @Author: vancent
 * @Date: 2019/7/18 21:44
 */
public class ModuloShardingDatabaseAlgorithm implements PreciseShardingAlgorithm<String> {

    @Override
    public String doSharding(Collection<String> tableNames, PreciseShardingValue<String> preciseShardingValue) {
        return (String) tableNames.toArray()[Math.abs(preciseShardingValue.getValue().hashCode() % tableNames.size())];
    }
}
