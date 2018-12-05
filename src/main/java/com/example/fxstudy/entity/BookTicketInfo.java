package com.example.fxstudy.entity;

/**
 * Created with IDEA
 * author:Dnetted
 * Date:2018/11/29
 * Time:11:16
 */
public class BookTicketInfo {
    private String secretStr;
    private String train_date;
    private String back_train_date;
    private String tour_flag;
    private String purpose_codes;
    private String query_from_station_name;
    private String query_to_station_name;

    public BookTicketInfo() {
    }

    public BookTicketInfo(String secretStr, String train_date, String back_train_date, String tour_flag, String purpose_codes, String query_from_station_name, String query_to_station_name) {
        this.secretStr = secretStr;
        this.train_date = train_date;
        this.back_train_date = back_train_date;
        this.tour_flag = tour_flag;
        this.purpose_codes = purpose_codes;
        this.query_from_station_name = query_from_station_name;
        this.query_to_station_name = query_to_station_name;
    }

    public String getSecretStr() {
        return secretStr;
    }

    public void setSecretStr(String secretStr) {
        this.secretStr = secretStr;
    }

    public String getTrain_date() {
        return train_date;
    }

    public void setTrain_date(String train_date) {
        this.train_date = train_date;
    }

    public String getBack_train_date() {
        return back_train_date;
    }

    public void setBack_train_date(String back_train_date) {
        this.back_train_date = back_train_date;
    }

    public String getTour_flag() {
        return tour_flag;
    }

    public void setTour_flag(String tour_flag) {
        this.tour_flag = tour_flag;
    }

    public String getPurpose_codes() {
        return purpose_codes;
    }

    public void setPurpose_codes(String purpose_codes) {
        this.purpose_codes = purpose_codes;
    }

    public String getQuery_from_station_name() {
        return query_from_station_name;
    }

    public void setQuery_from_station_name(String query_from_station_name) {
        this.query_from_station_name = query_from_station_name;
    }

    public String getQuery_to_station_name() {
        return query_to_station_name;
    }

    public void setQuery_to_station_name(String query_to_station_name) {
        this.query_to_station_name = query_to_station_name;
    }

    @Override
    public String toString() {
        return "BookTicketInfo{" +
                "secretStr='" + secretStr + '\'' +
                ", train_date='" + train_date + '\'' +
                ", back_train_date='" + back_train_date + '\'' +
                ", tour_flag='" + tour_flag + '\'' +
                ", purpose_codes='" + purpose_codes + '\'' +
                ", query_from_station_name='" + query_from_station_name + '\'' +
                ", query_to_station_name='" + query_to_station_name + '\'' +
                '}';
    }
}
