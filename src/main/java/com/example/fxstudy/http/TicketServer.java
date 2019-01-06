package com.example.fxstudy.http;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.fxstudy.entity.*;
import com.example.fxstudy.exception.TicketException;
import com.example.fxstudy.util.TicketReqUtil;
import com.example.fxstudy.util.TicketRespUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import okhttp3.JavaNetCookieJar;
import okhttp3.OkHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import java.awt.*;
import java.io.IOException;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.HttpCookie;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.fxstudy.http.TicketInfoContain.API_URL;

/**
 * @Author: D7-Dj
 * @Date: 2018/12/3 22:19
 */
public class TicketServer {
    public static TicketApi  api;
    public static Retrofit retrofit;
    public static OkHttpClient okHttpClient;
    public final static Logger logger = LoggerFactory.getLogger(TicketServer.class);
    static{
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        //设置Cookie持久化
        setCookies(builder);
        okHttpClient=builder.build();
        /*设置OKHTTP客户端
        *设置基础API_URL
        * 设置常规转换类
        * 设置JSON转换类
        */
        retrofit=new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(API_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        api = retrofit.create(TicketApi.class);
    }

    /**
     * 设置Cookie持久化
     * @param builder
     */
    private static void setCookies(OkHttpClient.Builder builder) {
        CookieManager cookieManager = new CookieManager();
        cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);
        builder.cookieJar(new JavaNetCookieJar(cookieManager));
    }

    public static JSONObject getCaptcja() throws IOException {
        Map<String,String> req = TicketReqUtil.getCaptchaReq();
        logger.info("getCaptcja Req:"+req);
        Call<String> call = api.getCaptcja(req);
        Response<String> resp = call.execute();
//        logger.info("getCaptcja resp:"+resp.body());
        JSONObject jsonObject = TicketRespUtil.getJSONObjectFromBody(resp.body());
        return jsonObject;
    }
    public static JSONObject login(String username,String password,String ans) throws IOException, TicketException {
        Map<String,String> req1 = TicketReqUtil.getCheckCapReq(ans);
        logger.info("checkCapt req"+req1);
        Call<String> call1 = api.checkCaptcja(req1);
        Response<String> resp1 = call1.execute();
        logger.info("checkCapt resp:"+resp1.body());
        JSONObject jsonObject1 = TicketRespUtil.getJSONObjectFromBody(resp1.body());
        checkRespCode(jsonObject1,"4");
        //验证通过再登陆
        Map<String, String> req2 = TicketReqUtil.getLoginReq(username, password, ans);
        logger.info("login req:" + req2);
        Call<String> call2 = api.login(req2);
        Response<String> resp2 = call2.execute();
        logger.info("login resp:" + resp2.body());
        JSONObject jsonObject2= TicketRespUtil.getJSONObjectFromBody(resp2.body());
        checkRespCode(jsonObject2,"0");

        //获取tk
        Map<String, String> req3 = TicketReqUtil.getUamtkReq();
        logger.info("getTK req:" + req3);
        Call<String> call3 = api.uamtk(req3);
        Response<String> resp3 = call3.execute();
        logger.info("getTK resp:" + resp3.body());
        JSONObject jsonObject3= TicketRespUtil.getJSONObjectFromBody(resp3.body());
        checkRespCode(jsonObject3,"0");

        //发送tk
        String tk = jsonObject3.getString("newapptk");
        Map<String, String> req4 = TicketReqUtil.getUamauthclientReq(tk);
        logger.info("sendTk req:" + req4);
        Call<String> call4 = api.uamauthclient(req4);
        Response<String> resp4 = call4.execute();
        logger.info("sendTk resp:" + resp4.body());
        JSONObject jsonObject4= TicketRespUtil.getJSONObjectFromBody(resp4.body());
        checkRespCode(jsonObject4,"0");
        return jsonObject4;
    }
    public static List<QueryInfo> quertTicket(String train_date, String from_station, String to_station, String purpose_codes) throws IOException {
        List<QueryInfo> lq = new ArrayList<>();
        Map<String,String> req = TicketReqUtil.getQueryTicketReq(train_date,from_station,to_station,purpose_codes);
        logger.info("query req:"+req);
        Call<String> call = api.queryTicket(TicketInfoContain.getCLeftTicketUrl(),req);
        Response<String> resp = call.execute();
        logger.info(resp.body());
        try {
            lq=TicketRespUtil.getQueryTicket(resp.body());
        }
        catch (Exception e){
            //如果报异常，首先刷新请求Url
            getQueryUrl();
            logger.debug(e.toString());
        }

        return lq;
    }
    public static void getQueryUrl() throws IOException {
        Call<String> call = api.getQueryUrl();
        Response<String> resp = call.execute();
        String url =TicketRespUtil.getJsValue(resp.body(),"CLeftTicketUrl",1);
        logger.info(url);
        TicketInfoContain.setCLeftTicketUrl("/otn/"+url);
    }
    public static void checkRespCode(JSONObject jsonObject,String key,String correct) throws TicketException {
        if (!jsonObject.get(key).equals(correct)){
            throw new TicketException(jsonObject.getString("result_message"));
        }
    }
    public static void checkRespCode(JSONObject jsonObject,String correct) throws TicketException {
        if (!jsonObject.getString("result_code").equals(correct)){
            throw new TicketException(jsonObject.getString("result_message"));
        }
    }

    public static void bookingTicket(BookTicketInfo bookTicketInfo) throws IOException, TicketException {
        if(checkUser()){
            Map<String,String> req1 = TicketReqUtil.getSubmitOrderReq(bookTicketInfo);
            Call<String> call1 = api.submitOrderRequest(req1);
            Response<String> resp1 = call1.execute();
            logger.info("submit req:"+req1);
            logger.info("submit resp:"+resp1.body());
            JSONObject jsonObject1 = TicketRespUtil.getJSONObjectFromBody(resp1.body());
            if (!jsonObject1.getString("status").equals("true")){
                throw new TicketException("提交订单返回异常:"+jsonObject1.getString("messages"));
            }

            Map<String,String> req2 = TicketReqUtil.getIniDc();
            Call<String> call2 = api.initDc(req2);
            Response<String> resp2 = call2.execute();
            logger.info("iniDc req:"+req2);
            logger.info("iniDc resp:"+resp2.body());
            String globalRepeatSubmitToken = TicketRespUtil.getJsValue(resp2.body(), "globalRepeatSubmitToken",1);
            String leftTicket = TicketRespUtil.getJsValue(resp2.body(),"leftTicketStr",2);
            String key_check_isChange = TicketRespUtil.getJsValue(resp2.body(),"key_check_isChange",2);
            TicketInfoContain.setRepeatSubmitToken(globalRepeatSubmitToken);
            TicketInfoContain.setLEFTTICKETSTR(leftTicket);
            TicketInfoContain.setKeyCheckIschange(key_check_isChange);
            logger.info("RepeatSubmitToken:"+globalRepeatSubmitToken);
            logger.info("leftTicket:"+leftTicket);
            logger.info("key_check_isChange:"+key_check_isChange);

            Map<String,String> req3 = TicketReqUtil.getPassengerDTOsReq(globalRepeatSubmitToken);
            Call<String> call3 = api.getPassengerDTOs(req3);
            Response<String> resp3 = call3.execute();
            logger.info("passenger req:"+req3);
            logger.info("passenger resp:"+resp3.body());
            Passengers passengers = JSON.parseObject(resp3.body(),Passengers.class);
            TicketInfoContain.setPassengers(passengers);



        }
    }

    public static Boolean checkUser() throws IOException {
        Map<String,String> req = TicketReqUtil.getCheckUserReq();
        logger.info("checkUser req:"+req);
        Call<String> call = api.checkUser(req);
        Response<String> resp = call.execute();
        logger.info("checkUser resp:"+resp.body());
        JSONObject jsonObject = JSON.parseObject(resp.body());
        if(jsonObject.getString("status").equals("true")&&jsonObject.getJSONObject("data").getString("flag").equals("true")) {
            return true;
        }
        return false;
    }
    public static String submitOrder(List<BookingPassenger> list, QueryLeftNewDTO queryLeftNewDTO) throws IOException {
        Map<String,String> req = TicketReqUtil.getcheckOrderInfoReq(list,TicketInfoContain.getRepeatSubmitToken());
        logger.info("checkOrderInfoReq:"+req.toString());
        Call<String> call = api.checkOrderInfo(req);
        Response<String> resp = call.execute();
        logger.info("checkOrderResp:"+resp.body());

        Map<String,Object> req1 = TicketReqUtil.getQueueCountReq(TicketInfoContain.getQueryLeftNewDTO());
        logger.info("getqueue req:"+req1.toString());
        Call<String> call1 = api.getQueueCount(req1);
        Response<String> resp1 = call1.execute();
        logger.info("getqueue resp:"+resp1.body());

        Map<String,String> req2 = TicketReqUtil.confirmSingleForQueueReq();
        logger.info("confirm req:"+req2);
        Call<String> call2 = api.confirmSingleForQueue(req2);
        Response<String> resp2 = call2.execute();
        logger.info("confirm resp:"+resp2.body());
        return resp2.body();
    }
    public static List<Passengers.DataBean.NormalPassengersBean> getPassenger() throws IOException {
        Map<String,String> req2 = TicketReqUtil.getIniDc();
        Call<String> call2 = api.initDc(req2);
        Response<String> resp2 = call2.execute();
        logger.info("iniDc req:"+req2);
        logger.info("iniDc resp:"+resp2.body());
        String globalRepeatSubmitToken = TicketRespUtil.getJsValue(resp2.body(), "globalRepeatSubmitToken",1);
        String leftTicket = TicketRespUtil.getJsValue(resp2.body(),"leftTicketStr",2);
        String key_check_isChange = TicketRespUtil.getJsValue(resp2.body(),"key_check_isChange",2);
        TicketInfoContain.setRepeatSubmitToken(globalRepeatSubmitToken);
        TicketInfoContain.setLEFTTICKETSTR(leftTicket);
        TicketInfoContain.setKeyCheckIschange(key_check_isChange);
        logger.info("RepeatSubmitToken:"+globalRepeatSubmitToken);
        logger.info("leftTicket:"+leftTicket);
        logger.info("key_check_isChange:"+key_check_isChange);

        Map<String,String> req3 = TicketReqUtil.getPassengerDTOsReq(globalRepeatSubmitToken);
        Call<String> call3 = api.getPassengerDTOs(req3);
        Response<String> resp3 = call3.execute();
        logger.info("passenger req:"+req3);
        logger.info("passenger resp:"+resp3.body());
        Passengers passengers = JSON.parseObject(resp3.body(),Passengers.class);
        TicketInfoContain.setPassengers(passengers);
        logger.info("getPassengerRes:"+passengers.getData().getNormal_passengers());
        return passengers.getData().getNormal_passengers();
    }
    public static String grabTicket(List<String> passengers,List<String> seatTypes,List<String> trainIds,String start,String end,String trainDate,String purpose_codes){
        try {
            //根据姓名查找订票人信息
            List<BookingPassenger> list = new ArrayList<>();

            //查票
            List<QueryInfo> quertTicket = quertTicket(trainDate,start,end,purpose_codes);
            //循环查询结果,如果匹配需要抢的车次，进入
            for (QueryInfo qi:quertTicket){
                if(trainIds.contains(qi.getQueryLeftNewDTO().station_train_code)){
                    for (String seatType:seatTypes){
                        int grabFlag = -1;
                        //检查是否有剩余票数
                        switch (seatType){
                            case "硬座": if(canBook(qi.getQueryLeftNewDTO().getYz_num())){grabFlag=1;};break;
                            case "软座": if(canBook(qi.getQueryLeftNewDTO().getRz_num())){grabFlag=2;};break;
                            case "硬卧": if(canBook(qi.getQueryLeftNewDTO().getYw_num())){grabFlag=3;};break;
                            case "软卧": if(canBook(qi.getQueryLeftNewDTO().getRw_num())){grabFlag=4;};break;
                            case "二等座": if(canBook(qi.getQueryLeftNewDTO().getZe_num())){grabFlag=5;};break;
                            case "一等座": if(canBook(qi.getQueryLeftNewDTO().getZy_num())){grabFlag=6;};break;
                            case "商务座": if(canBook(qi.getQueryLeftNewDTO().getTz_num())){grabFlag=7;};break;
                            default: break;
                        }
                        if(grabFlag>0){
                            //创建订票所需信息
                            BookTicketInfo bookTicketInfo = new BookTicketInfo();
                            bookTicketInfo.setSecretStr(qi.getSecretStr());
                            bookTicketInfo.setBack_train_date(trainDate);
                            bookTicketInfo.setTrain_date(trainDate);
                            bookTicketInfo.setTour_flag("dc");
                            bookTicketInfo.setPurpose_codes("ADULT");
                            bookTicketInfo.setQuery_from_station_name(qi.getQueryLeftNewDTO().from_station_name);
                            bookTicketInfo.setQuery_to_station_name(qi.getQueryLeftNewDTO().to_station_name);
                            logger.info(qi.getQueryLeftNewDTO().getStation_train_code());
                            for (Passengers.DataBean.NormalPassengersBean np :TicketInfoContain.normalPassengersBeans){
                                if(passengers.contains(np.getPassenger_name())){
                                    BookingPassenger bookingPassenger = new BookingPassenger(np,seatType);
                                    list.add(bookingPassenger);
                                }
                            }
                            TicketInfoContain.setQueryLeftNewDTO(qi.getQueryLeftNewDTO());
                            bookingTicket(bookTicketInfo);
                            String s = submitOrder(list, qi.getQueryLeftNewDTO());
                            return s;
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TicketException e) {
            e.printStackTrace();
            return e.getMessage();
        }
        return "-1";
    }
    public static boolean canBook(String s){
        if(s.equals("--")||s.equals("无")){
            return false;
        }else {
            return true;
        }
    }
}
