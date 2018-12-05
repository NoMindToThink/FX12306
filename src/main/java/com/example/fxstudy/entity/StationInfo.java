package com.example.fxstudy.entity;

/**
 * @Author: D7-Dj
 * @Date: 2018/11/27 22:03
 */
public class StationInfo {
    String jianma;
    String name;
    String code;
    String pinyin;
    String jianpin;
    int id;

    public StationInfo() {
    }
    public StationInfo(String jianma, String name, String code, String pinyin, String jianpin, int id) {
        this.jianma = jianma;
        this.name = name;
        this.code = code;
        this.pinyin = pinyin;
        this.jianpin = jianpin;
        this.id = id;
    }
    public static  StationInfo creteStation(String info){
        info = info.replaceAll("@","");
        String[] infos = info.split("\\|");
        return new StationInfo(infos[0],infos[1],infos[2],infos[3],infos[4],Integer.valueOf(infos[5]));
    }



    public String getJianma() {
        return jianma;
    }

    public void setJianma(String jianma) {
        this.jianma = jianma;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    public String getJianpin() {
        return jianpin;
    }

    public void setJianpin(String jianpin) {
        this.jianpin = jianpin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "StationInfo{" +
                "jianma='" + jianma + '\'' +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", pinyin='" + pinyin + '\'' +
                ", jianpin='" + jianpin + '\'' +
                ", id=" + id +
                '}';
    }
}
