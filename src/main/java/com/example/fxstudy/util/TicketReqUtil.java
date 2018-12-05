package com.example.fxstudy.util;

import com.example.fxstudy.entity.BookTicketInfo;
import com.example.fxstudy.entity.BookingPassenger;
import com.example.fxstudy.entity.QueryLeftNewDTO;
import com.example.fxstudy.exception.TicketException;
import com.example.fxstudy.http.TicketInfoContain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.*;

import static com.example.fxstudy.util.CommonUtil.isNull;

/**
 * @Author: D7-Dj
 * @Date: 2018/12/3 22:36
 */
public class TicketReqUtil {
    public static final Logger logger = LoggerFactory.getLogger(TicketReqUtil.class);
    //证件类型
    //身份证
    public static final String PASSENGER_ID_TYPE_CODE_SFZ = "1";

    //座位类型
    //硬卧
    public static final String YW_SEATTYPE = "3";
    //软卧
    public static final String RW_SEATTYPE = "4";
    //二等座
    public static final String ZE_SEATTYPE = "O";
    //一等座
    public static final String ZY_SEATTYPE = "M";
    //硬座
    public static final String YZ_SEATTYPE = "1";

    //旅途类型
    //单程
    public static final String DC_TOUR_FLAG = "dc";
    //往返
    public static final String WC_TOUR_FLAG = "dc";

    //票类型
    //成人票
    public static final String CR_TICKET_TYPE_CODES="1";

    //不知 常量
    public static final String Y_WHATSSELECT = "1";

    //毫秒数不加貌似也行
    static long long_;
    static long mills = System.currentTimeMillis();

    public static String passengerTicketStr="";
    public static String oldPassengerStr="";

    /**
     * 获取验证码的请求
     * @return
     */
    public static Map<String,String> getCaptchaReq(){
        Map<String,String>  req = new HashMap<>();
        req.put("login_site","E");
        req.put("module","login");
        req.put("rand","sjrand");
        //这个貌似也没什么用
        req.put("callback","jQuery19108604870712068642_"+mills);
        req.put(System.currentTimeMillis()+"","");
        long l2 = mills+new Random().nextInt(10);
        long_ =l2;
        req.put("long_",l2+"");
        return req;
    }
    public static Map<String,String> getCheckCapReq(String ans) throws UnsupportedEncodingException, TicketException {
        if (isNull(ans)){
            throw new TicketException("请选择验证码图片");
        }
        Map<String,String> req = new HashMap<>();
        req.put("login_site","E");
        req.put("module","login");
        req.put("rand","sjrand");
        req.put("callback","jQuery19108604870712068642_"+mills);
        req.put("long_",(long_ +1)+"");
        String ans_url = URLEncoder.encode(ans,"UTF-8");
        req.put("answer",ans);
        return req;
    }
    public static Map<String,String> getLoginReq(String username,String password,String answer) throws UnsupportedEncodingException, TicketException {
        if(isNull(username)|| isNull(password)||isNull(answer)){
            throw new TicketException("用户名密码均不能为空!");
        }
        Map<String,String> req = new HashMap<>();
        req.put("appid","otn");
        req.put("username",username);
        req.put("password",password);
        req.put("answer",answer);
        return req;
    }
    public static Map<String,String> getUamtkReq(){
        Map<String,String> req = new HashMap<>();
        req.put("appid","otn");
        return req;
    }
    public static Map<String,String> getUamauthclientReq(String tk){
        Map<String,String> req = new HashMap<>();
        req.put("tk",tk);
        return req;
    }
    public static Map<String,String> getQueryTicketReq(String train_date,String from_station,String to_station,String purpose_codes){
        Map<String,String> req = new LinkedHashMap<>();
        req.put("leftTicketDTO.train_date",train_date);
        req.put("leftTicketDTO.from_station",from_station);
        req.put("leftTicketDTO.to_station",to_station);
        req.put("purpose_codes",purpose_codes);
        return req;
    }
    public static Map<String,String> getCheckUserReq(){
        Map<String,String> req = new HashMap<>();
        req.put("_json_att","");
        return req;
    }
    public static Map<String,String> getSubmitOrderReq(BookTicketInfo bookTicketInfo) throws TicketException {
    if(isNull(bookTicketInfo.getSecretStr())){
        throw new TicketException("该车暂无法预定");
    }
    Map<String,String> req = new HashMap<>();
        String se = bookTicketInfo.getSecretStr();
        try {
            se = URLDecoder.decode(se,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            throw new TicketException("加密字符串异常:"+se);
        }
        req.put("secretStr",se);
        req.put("train_date",bookTicketInfo.getTrain_date());
        req.put("back_train_date",bookTicketInfo.getBack_train_date());
        req.put("tour_flag",bookTicketInfo.getTour_flag());
        req.put("purpose_codes",bookTicketInfo.getPurpose_codes());
        String query_from_station_name = bookTicketInfo.getQuery_from_station_name();
        String query_to_station_name = bookTicketInfo.getQuery_to_station_name();
        req.put("query_from_station_name", query_from_station_name);
        req.put("query_to_station_name",query_to_station_name);
        req.put("undefined","");
        return req;
    }
    public static Map<String,String> getIniDc(){
        return getCheckUserReq();
    }
    public static Map<String,String> getPassengerDTOsReq(String REPEAT_SUBMIT_TOKEN){
        Map<String,String> req = new HashMap<>();
        req.put("_json_att","");
        req.put("REPEAT_SUBMIT_TOKEN",REPEAT_SUBMIT_TOKEN);
        return req;
    }

    public static Map<String, String> getcheckOrderInfoReq(List<BookingPassenger> list, String repeatSubmitToken) {
        Map<String,String> req = new HashMap<>();
        //        oldPassengerStr组成的格式：乘客名,passenger_id_type_code,passenger_id_no,passenger_type，’_’隔开最后一个不取掉
        //        passengerTicketStr组成:seatType,0,票类型（成人票填1）,乘客名,passenger_id_type_code,passenger_id_no,mobile_no,’N’ '_'隔开 最后一个去吊
        oldPassengerStr="";
        passengerTicketStr="";
        for (BookingPassenger b:list){
            oldPassengerStr=oldPassengerStr+getOldPassengerStr(b);
            passengerTicketStr=passengerTicketStr+getPassengerTicketStr(b);
        }
        if(list.size()>1){
            passengerTicketStr=passengerTicketStr.substring(0,passengerTicketStr.length()-1);
        }
        req.put("cancel_flag","2");
        req.put("bed_level_order_num","000000000000000000000000000000");
        req.put("passengerTicketStr",passengerTicketStr);
        req.put("oldPassengerStr",oldPassengerStr);
        req.put("tour_flag",DC_TOUR_FLAG);
        req.put("randCode","");
        req.put("whatsSelect",Y_WHATSSELECT);
        req.put("_json_att","");
        req.put("REPEAT_SUBMIT_TOKEN", repeatSubmitToken);
        return req;

    }
    public static Map<String, Object> getQueueCountReq(QueryLeftNewDTO qInfo) {
        Map<String,Object> req = new HashMap<>();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        req.put("train_date",calendar.getTime());
        req.put("train_no",qInfo.getTrain_no());
        req.put("stationTrainCode",qInfo.getStation_train_code());
        req.put("seatType",passengerTicketStr.substring(0,1));
        req.put("fromStationTelecode",qInfo.getFrom_station_telecode());
        req.put("toStationTelecode",qInfo.getTo_station_telecode());
        req.put("leftTicket",TicketInfoContain.getLEFTTICKETSTR());
        //TODO  暂时写死
        req.put("purpose_codes","00");
        req.put("train_location",qInfo.getLocation_code());
        req.put("_json_att","");
        req.put("REPEAT_SUBMIT_TOKEN",TicketInfoContain.getRepeatSubmitToken());
        return req;
    }

    public static String getOldPassengerStr(BookingPassenger bookingPassenger){
        String olsStr=bookingPassenger.getPassenger_name()+","+bookingPassenger.getPassenger_id_type_code()+","+bookingPassenger.getPassenger_id_no()+","+bookingPassenger.getPassenger_type()+"_";
        return olsStr;
    }
    public static String getPassengerTicketStr(BookingPassenger bookingPassenger){
        String str = bookingPassenger.getSeatType()+","+0+","+bookingPassenger.getPassenger_type()+","+bookingPassenger.getPassenger_name()+","+bookingPassenger.getPassenger_id_type_code()+","+bookingPassenger.getPassenger_id_no()+","+bookingPassenger.getMobile_no()+","+"N"+"_";
        return str;
    }


    public static Map<String, String> confirmSingleForQueueReq() {
        Map<String,String> req = new HashMap<>();
        QueryLeftNewDTO q = TicketInfoContain.getQueryLeftNewDTO();
        req.put("passengerTicketStr",passengerTicketStr);
        req.put("oldPassengerStr",oldPassengerStr);
        req.put("randCode","");
        //TODO 暂时写死
        req.put("purpose_codes","00");
        req.put("key_check_isChange",TicketInfoContain.getKeyCheckIschange());
        req.put("leftTicketStr", TicketInfoContain.getLEFTTICKETSTR());
        req.put("train_location",q.getLocation_code());
        req.put("choose_seats","");
        //TODO 死
        req.put("seatDetailType","000");
        req.put("whatsSelect","1");
        req.put("roomType","00");
        req.put("dwAll","N");
        req.put("_json_att","");
        req.put("REPEAT_SUBMIT_TOKEN",TicketInfoContain.getRepeatSubmitToken());
        return req;
    }
}
