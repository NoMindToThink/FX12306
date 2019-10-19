package com.example.fxstudy.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.fxstudy.entity.QueryInfo;
import com.example.fxstudy.entity.QueryLeftNewDTO;
import com.example.fxstudy.exception.TicketException;
import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.BASE64Decoder;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static com.example.fxstudy.util.CommonUtil.isNull;

/**
 * @Author: D7-Dj
 * @Date: 2018/12/3 23:40
 */
public class TicketRespUtil {
    public final static String JSON_REGEX = "(?<=\\().*(?=\\))";
    public final static Pattern JSON_PATTERN = Pattern.compile(JSON_REGEX);
    public final static String PRE_JSON_REGEX = ".*(?=\\()";
    public final static Pattern PRE_JSON_PATTERN = Pattern.compile(PRE_JSON_REGEX);
    public final static String JSON_PRO_REGEX = "(?<=\\(\\').*(?=\\'\\))";
    public final static Pattern JSON_PRO_PATTERN = Pattern.compile(JSON_PRO_REGEX);
    public final static BASE64Decoder BASE_64_DECODER = new BASE64Decoder();
    public static Logger logger = LoggerFactory.getLogger(TicketRespUtil.class);

    public static JSONObject getJSONObjectFromBody(String body){
        String jsonStr = getJSONStrFromBody(body);
        JSONObject jsonObject = JSON.parseObject(jsonStr);
        return jsonObject;
    }

    public static JSONObject getJSONObjectFromBody(String body,String reg){
        String jsonStr = getJSONStrFromBody(body,reg);
        JSONObject jsonObject = JSON.parseObject(jsonStr);
        return jsonObject;
    }

    public static String getJSONStrFromBody(String response){
        String result = "";
        Matcher matcher = JSON_PATTERN.matcher(response);
        while(matcher.find()){
            result=matcher.group();
        }
        if (isNull(result)){
            result=response;
        }
        return result;
    }
    public static String getJSONStrFromBody(String response,String reg){
        Pattern json_pattern = Pattern.compile(reg);
        String result = "";
        Matcher matcher = json_pattern.matcher(response);
        while(matcher.find()){
            result=matcher.group();
        }
        if (isNull(result)){
            result=response;
        }
        return result;
    }

    public static InputStream getImageBase64(String str){
        InputStream is = null;
        try {
            byte[] imagebytes = BASE_64_DECODER.decodeBuffer(str);
            is = new ByteInputStream(imagebytes,imagebytes.length);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return is;
    }
    public static List<QueryInfo> b5(JSONArray result, JSONObject map){
        List<QueryInfo> ct = new ArrayList<>();
        for (int cs = 0; cs < result.size(); cs++) {
            QueryInfo cx = new QueryInfo();
            String[] cr = ((String)result.get(cs)).split("\\|");
            cx.secretStr = cr[0];
            cx.buttonTextInfo = cr[1];
            QueryLeftNewDTO cv = new QueryLeftNewDTO();
            cv.train_no = cr[2];
            cv.station_train_code = cr[3];
            cv.start_station_telecode = cr[4];
            cv.end_station_telecode = cr[5];
            cv.from_station_telecode = cr[6];
            cv.to_station_telecode = cr[7];
            cv.start_time = cr[8];
            cv.arrive_time = cr[9];
            cv.lishi = cr[10];
            cv.canWebBuy = cr[11];
            cv.yp_info = cr[12];
            cv.start_train_date = cr[13];
            cv.start_train_date = CommonUtil.formatDateStr(cv.start_train_date,"422");
            cv.train_seat_feature = cr[14];
            cv.location_code = cr[15];
            cv.from_station_no = cr[16];
            cv.to_station_no = cr[17];
            cv.is_support_card = cr[18];
            cv.controlled_train_flag = cr[19];
            cv.gg_num =   cr[20]!=null&&!cr[20].equals("")   ? cr[20] : "--";
            cv.gr_num =   cr[21]!=null&&!cr[21].equals("")   ? cr[21] : "--";
            cv.qt_num =   cr[22]!=null&&!cr[22].equals("")   ? cr[22] : "--";
            cv.rw_num =   cr[23]!=null&&!cr[23].equals("")   ? cr[23] : "--";
            cv.rz_num =   cr[24]!=null&&!cr[24].equals("")   ? cr[24] : "--";
            cv.tz_num =   cr[25]!=null&&!cr[25].equals("")   ? cr[25] : "--";
            cv.wz_num =   cr[26]!=null&&!cr[26].equals("")   ? cr[26] : "--";
            cv.yb_num =   cr[27]!=null&&!cr[27].equals("")   ? cr[27] : "--";
            cv.yw_num =   cr[28]!=null&&!cr[28].equals("")   ? cr[28] : "--";
            cv.yz_num =   cr[29]!=null&&!cr[29].equals("")   ? cr[29] : "--";
            cv.ze_num =   cr[30]!=null&&!cr[30].equals("")   ? cr[30] : "--";
            cv.zy_num =   cr[31]!=null&&!cr[31].equals("")   ? cr[31] : "--";
            cv.swz_num =  cr[32]!=null&&!cr[32].equals("")  ? cr[32] : "--";
            cv.srrb_num = cr[33]!=null&&!cr[33].equals("") ? cr[33] : "--";
            cv.yp_ex = cr[34];
            cv.seat_types = cr[35];
            cv.exchange_train_flag = cr[36];
            cv.from_station_name = (String) map.get(cr[6]);
            cv.to_station_name = (String) map.get(cr[7]);
            cx.queryLeftNewDTO = cv;
            ct.add(cx);
        }
        return ct;
    }
    public static List<QueryInfo> getQueryTicket(String body) {
        List<QueryInfo> lq = new ArrayList<>();
        JSONObject jsonObject = getJSONObjectFromBody(body);
        JSONObject data = jsonObject.getJSONObject("data");
        JSONObject map = data.getJSONObject("map");
        JSONArray result = data.getJSONArray("result");
        lq = b5(result, map);
        return lq;
    }
    public static String getJsValue(String cont,String vname,int type){
        String result= "";
        Pattern patternJs = getJsValuePattern(vname,type);
        Matcher matcherJs = patternJs.matcher(cont);
        if(matcherJs.find()){
            result=matcherJs.group();
        }
        return result;
    }
    private static Pattern getJsValuePattern(String name,int type){
        String s ="";
        switch (type){
            case 1: s= "(?<=var "+name+" = \\').*(?=\\';)";break;
            case 2: s="(?<="+name+"\\':\\').*?(?=\\')";break;
            default: s = null;
        }
        return Pattern.compile(s);
    }
}
