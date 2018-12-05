package com.example.fxstudy.http;

import com.example.fxstudy.FxstudyApplication;
import com.example.fxstudy.entity.Passengers;
import com.example.fxstudy.entity.QueryLeftNewDTO;
import com.example.fxstudy.entity.StationInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @Author: D7-Dj
 * @Date: 2018/12/3 22:29
 */
public class TicketInfoContain {
    public static final Logger logger = LoggerFactory.getLogger(TicketInfoContain.class);
    public static String REPEAT_SUBMIT_TOKEN="";
    public static String LEFTTICKETSTR = "";
    public static String KEY_CHECK_ISCHANGE = "";
    public static final String API_URL = "https://kyfw.12306.cn/";
    public static final String LOGIN_SITE="E";
    public static final String MODULE="login";
    public static final String RAND = "sjrand";
    public static final String APPID= "otn";

    public static HashMap<String,String> captchReq = new HashMap<>();
    public static HashMap<String,String> captchResp = new HashMap<>();
    public static HashMap<String,String> loginReq = new HashMap<>();
    public static Map<String, StationInfo> STATIONS = new HashMap<>();
    public static List<Passengers.DataBean.NormalPassengersBean> normalPassengersBeans = new LinkedList<>();

    public static String passengerTicketStr;
    public static String oldPassengerStr;
    public static QueryLeftNewDTO queryLeftNewDTO;
    public static Passengers passengers;



    /**
     * 初始化地名信息
     */
    public static void initStation(){
        try {
            logger.info("init station pre");
            ClassLoader classLoader = FxstudyApplication.class.getClassLoader();
            InputStream is = classLoader.getResourceAsStream("STATION_NAME");
            InputStreamReader isr = new InputStreamReader(is,"UTF-8");
            BufferedReader br = new BufferedReader(isr);
            String line = null;
            while((line=br.readLine())!=null){
                String info = line.replaceAll("@","");
                StationInfo stationInfo=StationInfo.creteStation(info);
                STATIONS.put(stationInfo.getName(),stationInfo);
            }
        }catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getRepeatSubmitToken() {
        return REPEAT_SUBMIT_TOKEN;
    }

    public static void setRepeatSubmitToken(String repeatSubmitToken) {
        REPEAT_SUBMIT_TOKEN = repeatSubmitToken;
    }

    public static String getLEFTTICKETSTR() {
        return LEFTTICKETSTR;
    }

    public static void setLEFTTICKETSTR(String LEFTTICKETSTR) {
        TicketInfoContain.LEFTTICKETSTR = LEFTTICKETSTR;
    }

    public static String getApiUrl() {
        return API_URL;
    }

    public static String getLoginSite() {
        return LOGIN_SITE;
    }

    public static String getMODULE() {
        return MODULE;
    }

    public static String getRAND() {
        return RAND;
    }

    public static String getAPPID() {
        return APPID;
    }

    public static HashMap<String, String> getCaptchReq() {
        return captchReq;
    }

    public static void setCaptchReq(HashMap<String, String> captchReq) {
        TicketInfoContain.captchReq = captchReq;
    }

    public static HashMap<String, String> getCaptchResp() {
        return captchResp;
    }

    public static void setCaptchResp(HashMap<String, String> captchResp) {
        TicketInfoContain.captchResp = captchResp;
    }

    public static HashMap<String, String> getLoginReq() {
        return loginReq;
    }

    public static void setLoginReq(HashMap<String, String> loginReq) {
        TicketInfoContain.loginReq = loginReq;
    }

    public static Map<String, StationInfo> getSTATIONS() {
        return STATIONS;
    }

    public static void setSTATIONS(Map<String, StationInfo> STATIONS) {
        TicketInfoContain.STATIONS = STATIONS;
    }

    public static String getPassengerTicketStr() {
        return passengerTicketStr;
    }

    public static void setPassengerTicketStr(String passengerTicketStr) {
        TicketInfoContain.passengerTicketStr = passengerTicketStr;
    }

    public static String getOldPassengerStr() {
        return oldPassengerStr;
    }

    public static void setOldPassengerStr(String oldPassengerStr) {
        TicketInfoContain.oldPassengerStr = oldPassengerStr;
    }

    public static QueryLeftNewDTO getQueryLeftNewDTO() {
        return queryLeftNewDTO;
    }

    public static void setQueryLeftNewDTO(QueryLeftNewDTO queryLeftNewDTO) {
        TicketInfoContain.queryLeftNewDTO = queryLeftNewDTO;
    }

    public static String getKeyCheckIschange() {
        return KEY_CHECK_ISCHANGE;
    }

    public static void setKeyCheckIschange(String keyCheckIschange) {
        KEY_CHECK_ISCHANGE = keyCheckIschange;
    }

    public static Passengers getPassengers() {
        return passengers;
    }

    public static void setPassengers(Passengers passengers) {
        TicketInfoContain.passengers = passengers;
    }
}
