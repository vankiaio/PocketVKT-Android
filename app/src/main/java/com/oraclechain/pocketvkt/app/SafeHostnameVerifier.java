package com.oraclechain.pocketvkt.app;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

/**
 * Created by pocketVkt on 2018/4/2.
 */
public class SafeHostnameVerifier implements HostnameVerifier {
    @Override
    public boolean verify(String hostname, SSLSession session) {
        //验证主机名是否匹配
        //return hostname.equals("xxxxxxxxx");
        return true;
    }
}
