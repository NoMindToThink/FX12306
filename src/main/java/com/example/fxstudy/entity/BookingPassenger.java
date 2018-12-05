package com.example.fxstudy.entity;

import java.util.Objects;

/**
 * @Author: D7-Dj
 * @Date: 2018/12/4 21:57
 */
public class BookingPassenger {
    private String passenger_type_name;
    private String passenger_type;
    private String seatType;
    private String seatType_name;
    private String passenger_name;
    private String passenger_id_type_code;
    private String passenger_id_type_name;
    private String passenger_id_no;
    private String mobile_no;
    private String phone_no;

    public String getPassenger_type_name() {
        return passenger_type_name;
    }

    public void setPassenger_type_name(String passenger_type_name) {
        this.passenger_type_name = passenger_type_name;
    }

    public String getPassenger_type() {
        return passenger_type;
    }

    public void setPassenger_type(String passenger_type) {
        this.passenger_type = passenger_type;
    }

    public String getSeatType() {
        return seatType;
    }

    public void setSeatType(String seatType) {
        this.seatType = seatType;
    }

    public String getSeatType_name() {
        return seatType_name;
    }

    public void setSeatType_name(String seatType_name) {
        this.seatType_name = seatType_name;
    }

    public String getPassenger_name() {
        return passenger_name;
    }

    public void setPassenger_name(String passenger_name) {
        this.passenger_name = passenger_name;
    }

    public String getPassenger_id_type_code() {
        return passenger_id_type_code;
    }

    public void setPassenger_id_type_code(String passenger_id_type_code) {
        this.passenger_id_type_code = passenger_id_type_code;
    }

    public String getPassenger_id_type_name() {
        return passenger_id_type_name;
    }

    public void setPassenger_id_type_name(String passenger_id_type_name) {
        this.passenger_id_type_name = passenger_id_type_name;
    }

    public String getPassenger_id_no() {
        return passenger_id_no;
    }

    public void setPassenger_id_no(String passenger_id_no) {
        this.passenger_id_no = passenger_id_no;
    }

    public String getMobile_no() {
        return mobile_no;
    }

    public void setMobile_no(String mobile_no) {
        this.mobile_no = mobile_no;
    }

    public String getPhone_no() {
        return phone_no;
    }

    public void setPhone_no(String phone_no) {
        this.phone_no = phone_no;
    }

    public BookingPassenger() {
        this.setSeatType_name("硬座");
        this.setSeatType("1");
        this.setPassenger_type("1");
        this.setPassenger_type_name("成人票");
    }
    public BookingPassenger(Passengers.DataBean.NormalPassengersBean normalPassengersBean) {
        this();
        this.setPassenger_type(normalPassengersBean.getPassenger_type());
        this.setPassenger_name(normalPassengersBean.getPassenger_name());
        this.setPassenger_id_type_code(normalPassengersBean.getPassenger_id_type_code());
        this.setPassenger_id_type_name(normalPassengersBean.getPassenger_id_type_name());
        this.setPassenger_id_no(normalPassengersBean.getPassenger_id_no());
        this.setPhone_no(normalPassengersBean.getPhone_no());
        this.setMobile_no(normalPassengersBean.getMobile_no());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookingPassenger that = (BookingPassenger) o;
        return Objects.equals(passenger_id_type_code, that.passenger_id_type_code) &&
                Objects.equals(passenger_id_type_name, that.passenger_id_type_name) &&
                Objects.equals(passenger_id_no, that.passenger_id_no);
    }

    @Override
    public int hashCode() {
        return Objects.hash(passenger_id_type_code, passenger_id_type_name, passenger_id_no);
    }
}
