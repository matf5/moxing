package com.moxing.ssm.utils;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.sign.Credentials;

/**
 * Created by mtf on 2016/12/29.
 */
public class MyCos {
    private static final long APP_ID = 1251789093;
    private static final String SECRET_ID = "AKIDrcBbo8TJ9qeJp9jgz6hKqdJnKiwyFU2K";
    private static final String SECRET_KEY = "XxTs7A0e5OgrulS0PcgPVRM8kDTYQ6ar";

    // 通过控制台提前建好的bucket

    private static final String bucketName = "moxing1";

    // 默认获取的目录列表的数量

    private static final int DIR_NUM = 30;
    private static Credentials cred = new Credentials(APP_ID , SECRET_ID, SECRET_KEY);
    private static ClientConfig clientConfig = new ClientConfig();
    private static COSClient cosClient = new COSClient(clientConfig, cred);
    private MyCos(){
        clientConfig.setRegion("gz");
    }
    // 通过控制台获取AppId,SecretId,SecretKey
    public static COSClient getClient(){
        return cosClient;

    }
    public static String getBucketName(){
        return bucketName;
    }




}
