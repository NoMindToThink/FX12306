package com.example.fxstudy.util;

/**
 * Created with IDEA
 * author:Dnetted
 * Date:2018/12/4
 * Time:12:41
 */
public class CommonUtil {
    public static boolean isNull(Object o){
        if (o==null||o.toString().equals("")){
            return true;
        }
        return false;
    }

    public static String formatDateStr(String date,String format){

        int[] ymd=new int[3];
        for (int is=0;is<format.toCharArray().length;is++){
            ymd[is]=format.toCharArray()[is]-'0';
        }
        StringBuilder sb  = new StringBuilder(date);
        int offset=0;
        for (int i =0;i<ymd.length-1;i++){
            offset+=i+ymd[i];
            sb.insert(offset,"-");
        }
        return sb.toString();
    }
}
