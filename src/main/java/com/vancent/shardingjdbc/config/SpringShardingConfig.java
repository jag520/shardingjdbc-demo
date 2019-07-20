package com.vancent.shardingjdbc.config;

import com.vancent.shardingjdbc.config.algorithm.ModuloShardingDatabaseAlgorithm;
import com.vancent.shardingjdbc.config.algorithm.ModuloShardingTableAlgorithm;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.shardingsphere.api.config.sharding.KeyGeneratorConfiguration;
import org.apache.shardingsphere.api.config.sharding.ShardingRuleConfiguration;
import org.apache.shardingsphere.api.config.sharding.TableRuleConfiguration;
import org.apache.shardingsphere.api.config.sharding.strategy.StandardShardingStrategyConfiguration;
import org.apache.shardingsphere.shardingjdbc.api.ShardingDataSourceFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @Description:
 * @Author: vancent
 * @Date: 2019/7/17 23:48
 */
@MapperScan(basePackages = "com.vancent.shardingjdbc.dao")
@Configuration
public class SpringShardingConfig {

    @Bean
    @Primary
    public SqlSessionFactory sqlSessionFactory(DataSource shardingDataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(shardingDataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:com/vancent/shardingjdbc/dao/*.xml"));
        return bean.getObject();
    }

    @Bean
    DataSource shardingDataSource() throws SQLException {
        ShardingRuleConfiguration shardingRuleConfig = new ShardingRuleConfiguration();
        shardingRuleConfig.getTableRuleConfigs().add(getOrderTableRuleConfiguration());
        shardingRuleConfig.getTableRuleConfigs().add(getOrderItemTableRuleConfiguration());
        shardingRuleConfig.getBindingTableGroups().add("orders, orders_detail");

//        shardingRuleConfig.getBroadcastTables().add("t_config");
        shardingRuleConfig.setDefaultDatabaseShardingStrategyConfig(new StandardShardingStrategyConfiguration("id", new ModuloShardingDatabaseAlgorithm()));
        shardingRuleConfig.setDefaultTableShardingStrategyConfig(new StandardShardingStrategyConfiguration("parent_orders_id", new ModuloShardingTableAlgorithm()));

        //设置默认数据库
//        shardingRuleConfig.setDefaultDataSourceName("dataSource0");

        Properties prop = new Properties();
        prop.setProperty("sql.show", "true");
        return ShardingDataSourceFactory.createDataSource(createDataSourceMap(), shardingRuleConfig, prop);
    }

    private static KeyGeneratorConfiguration getKeyGeneratorConfiguration() {
        KeyGeneratorConfiguration result = new KeyGeneratorConfiguration("SNOWFLAKE", "id");
        return result;
    }

    TableRuleConfiguration getOrderTableRuleConfiguration() {
        TableRuleConfiguration result = new TableRuleConfiguration("orders", "shard_order_$->{0..1}.orders_$->{0..2}");
//        result.setKeyGeneratorConfig(getKeyGeneratorConfiguration());
        result.setDatabaseShardingStrategyConfig(new StandardShardingStrategyConfiguration("id", new ModuloShardingDatabaseAlgorithm()));
        result.setTableShardingStrategyConfig(new StandardShardingStrategyConfiguration("parent_orders_id", new ModuloShardingTableAlgorithm()));
        return result;
    }

    TableRuleConfiguration getOrderItemTableRuleConfiguration() {
        TableRuleConfiguration result = new TableRuleConfiguration("orders_detail", "shard_order_$->{0..1}.orders_detail_$->{0..1}");
        return result;
    }

    Map<String, DataSource> createDataSourceMap() {
        Map<String, DataSource> result = new HashMap<>();
        result.put("shard_order_0", dataSource0());
        result.put("shard_order_1", dataSource1());
        return result;
    }

    @Bean(name = "dataSource0")
    @ConfigurationProperties(prefix = "spring.datasource.shardorder0")
    public DataSource dataSource0() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "dataSource1")
    @ConfigurationProperties(prefix = "spring.datasource.shardorder1")
    public DataSource dataSource1() {
        return DataSourceBuilder.create().build();
    }
}
