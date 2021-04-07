package com.lvgr.vod.utils;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author lvgr
 * @date 2021/3/19 22:46
 * @desc
 */
@Component
public class ConstantVodUtils implements InitializingBean {

    /**
     * ${aliyun.oss.file.keyid}读取配置文件
     */

    @Value("${aliyun.vod.file.keyid}")
    private String keyid;

    @Value("${aliyun.vod.file.keysecret}")
    private String keysecret;

    /**
     * 定义公开的静态常量
     */
    public static String KEY_ID;
    public static String KEY_SECRET;


    /**
     * 项目启动，加载配置文件并初始化值后执行该方法。
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        KEY_ID = keyid;
        KEY_SECRET = keysecret;
    }
}
