package org.vincent.memcached.config;

import com.whalin.MemCached.MemCachedClient;
import com.whalin.MemCached.SockIOPool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author PengRong
 * @package org.vincent.memcached.config
 * @ClassName MemcacheConfig.java
 * @date 2019/8/2 - 8:03
 * @ProjectName memcached-learn
 * @Description: https://www.cnblogs.com/gyli20170901/p/9564441.html
 */
@Configuration
public class MemcacheConfig {

    @Autowired
    private MemcachedPoolConfig memcachedPoolConfig;
    @Bean
    public SockIOPool sockIOPool(){

        //获取连接池的实例
        SockIOPool pool = SockIOPool.getInstance();
        //服务器列表及其权重
        String[] servers = memcachedPoolConfig.getServers();
        Integer[] weights = memcachedPoolConfig.getWeights();
        //设置服务器信息
        pool.setServers(servers);
        pool.setWeights(weights);
        //设置初始连接数、最小连接数、最大连接数、最大处理时间
        pool.setInitConn(memcachedPoolConfig.getInitConn());
        pool.setMinConn(memcachedPoolConfig.getMinConn());
        pool.setMaxConn(memcachedPoolConfig.getMaxConn());
        //设置连接池守护线程的睡眠时间
        pool.setMaintSleep(memcachedPoolConfig.getMaintSleep());
        //设置TCP参数，连接超时
        pool.setNagle(memcachedPoolConfig.isNagle());
        pool.setSocketConnectTO(memcachedPoolConfig.getSocketTO());
        //初始化并启动连接池
        pool.initialize();
        return pool;
    }
    @Bean
    public MemCachedClient  memCachedClient(){
        return    new MemCachedClient();
    }
}
