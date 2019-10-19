package com.example.fxstudy.inteceptor;

import com.example.fxstudy.http.TicketServer;
import com.sun.deploy.util.StringUtils;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import sun.security.krb5.internal.Ticket;

import java.io.IOException;
import java.util.ArrayList;

/**
 * @Author: D7-Dj
 * @Date: 2019/10/19 10:29
 */
public class LoginCookieInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Request.Builder builder = request.newBuilder();
        String cookie = StringUtils.join(TicketServer.GLOBAL_STORAGE.getOrDefault("cookie",new ArrayList<>()), ";");
        builder.addHeader("Cookie", cookie);

        String referer = StringUtils.join(TicketServer.GLOBAL_STORAGE.getOrDefault("Referer",new ArrayList<>()), ";");
        builder.addHeader("Referer",referer);
        TicketServer.GLOBAL_STORAGE.remove("Referer");
        builder.addHeader("Origin","https://kyfw.12306.cn");
        builder.addHeader("Sec-Fetch-Mode","cors");
        builder.addHeader("Sec-Fetch-Site","same-origin");

        return chain.proceed(builder.build());
    }


}
