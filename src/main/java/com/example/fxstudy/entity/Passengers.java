package com.example.fxstudy.entity;

import java.util.List;

/**
 * Created with IDEA
 * author:Dnetted
 * Date:2018/11/30
 * Time:9:37
 */
public class Passengers {



    private String validateMessagesShowId;
    private boolean status;
    private int httpstatus;
    private DataBean data;
    private ValidateMessagesBean validateMessages;
    private List<?> messages;

    public String getValidateMessagesShowId() {
        return validateMessagesShowId;
    }

    public void setValidateMessagesShowId(String validateMessagesShowId) {
        this.validateMessagesShowId = validateMessagesShowId;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getHttpstatus() {
        return httpstatus;
    }

    public void setHttpstatus(int httpstatus) {
        this.httpstatus = httpstatus;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public ValidateMessagesBean getValidateMessages() {
        return validateMessages;
    }

    public void setValidateMessages(ValidateMessagesBean validateMessages) {
        this.validateMessages = validateMessages;
    }

    public List<?> getMessages() {
        return messages;
    }

    public void setMessages(List<?> messages) {
        this.messages = messages;
    }

    public static class DataBean {


        private boolean isExist;
        private String exMsg;
        private List<String> two_isOpenClick;
        private List<String> other_isOpenClick;
        private List<NormalPassengersBean> normal_passengers;
        private List<?> dj_passengers;

        public boolean isIsExist() {
            return isExist;
        }

        public void setIsExist(boolean isExist) {
            this.isExist = isExist;
        }

        public String getExMsg() {
            return exMsg;
        }

        public void setExMsg(String exMsg) {
            this.exMsg = exMsg;
        }

        public List<String> getTwo_isOpenClick() {
            return two_isOpenClick;
        }

        public void setTwo_isOpenClick(List<String> two_isOpenClick) {
            this.two_isOpenClick = two_isOpenClick;
        }

        public List<String> getOther_isOpenClick() {
            return other_isOpenClick;
        }

        public void setOther_isOpenClick(List<String> other_isOpenClick) {
            this.other_isOpenClick = other_isOpenClick;
        }

        public List<NormalPassengersBean> getNormal_passengers() {
            return normal_passengers;
        }

        public void setNormal_passengers(List<NormalPassengersBean> normal_passengers) {
            this.normal_passengers = normal_passengers;
        }

        public List<?> getDj_passengers() {
            return dj_passengers;
        }

        public void setDj_passengers(List<?> dj_passengers) {
            this.dj_passengers = dj_passengers;
        }

        public static class NormalPassengersBean {
            private String code;
            private String passenger_name;
            private String sex_code;
            private String sex_name;
            private String born_date;
            private String country_code;
            private String passenger_id_type_code;
            private String passenger_id_type_name;
            private String passenger_id_no;
            private String passenger_type;
            private String passenger_flag;
            private String passenger_type_name;
            private String mobile_no;
            private String phone_no;
            private String email;
            private String address;
            private String postalcode;
            private String first_letter;
            private String recordCount;
            private String total_times;
            private String index_id;
            private String gat_born_date;
            private String gat_valid_date_start;
            private String gat_valid_date_end;
            private String gat_version;

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }

            public String getPassenger_name() {
                return passenger_name;
            }

            public void setPassenger_name(String passenger_name) {
                this.passenger_name = passenger_name;
            }

            public String getSex_code() {
                return sex_code;
            }

            public void setSex_code(String sex_code) {
                this.sex_code = sex_code;
            }

            public String getSex_name() {
                return sex_name;
            }

            public void setSex_name(String sex_name) {
                this.sex_name = sex_name;
            }

            public String getBorn_date() {
                return born_date;
            }

            public void setBorn_date(String born_date) {
                this.born_date = born_date;
            }

            public String getCountry_code() {
                return country_code;
            }

            public void setCountry_code(String country_code) {
                this.country_code = country_code;
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

            public String getPassenger_type() {
                return passenger_type;
            }

            public void setPassenger_type(String passenger_type) {
                this.passenger_type = passenger_type;
            }

            public String getPassenger_flag() {
                return passenger_flag;
            }

            public void setPassenger_flag(String passenger_flag) {
                this.passenger_flag = passenger_flag;
            }

            public String getPassenger_type_name() {
                return passenger_type_name;
            }

            public void setPassenger_type_name(String passenger_type_name) {
                this.passenger_type_name = passenger_type_name;
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

            public String getEmail() {
                return email;
            }

            public void setEmail(String email) {
                this.email = email;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public String getPostalcode() {
                return postalcode;
            }

            public void setPostalcode(String postalcode) {
                this.postalcode = postalcode;
            }

            public String getFirst_letter() {
                return first_letter;
            }

            public void setFirst_letter(String first_letter) {
                this.first_letter = first_letter;
            }

            public String getRecordCount() {
                return recordCount;
            }

            public void setRecordCount(String recordCount) {
                this.recordCount = recordCount;
            }

            public String getTotal_times() {
                return total_times;
            }

            public void setTotal_times(String total_times) {
                this.total_times = total_times;
            }

            public String getIndex_id() {
                return index_id;
            }

            public void setIndex_id(String index_id) {
                this.index_id = index_id;
            }

            public String getGat_born_date() {
                return gat_born_date;
            }

            public void setGat_born_date(String gat_born_date) {
                this.gat_born_date = gat_born_date;
            }

            public String getGat_valid_date_start() {
                return gat_valid_date_start;
            }

            public void setGat_valid_date_start(String gat_valid_date_start) {
                this.gat_valid_date_start = gat_valid_date_start;
            }

            public String getGat_valid_date_end() {
                return gat_valid_date_end;
            }

            public void setGat_valid_date_end(String gat_valid_date_end) {
                this.gat_valid_date_end = gat_valid_date_end;
            }

            public String getGat_version() {
                return gat_version;
            }

            public void setGat_version(String gat_version) {
                this.gat_version = gat_version;
            }

            @Override
            public String toString() {
                return "NormalPassengersBean{" +
                        "code='" + code + '\'' +
                        ", passenger_name='" + passenger_name + '\'' +
                        ", sex_code='" + sex_code + '\'' +
                        ", sex_name='" + sex_name + '\'' +
                        ", born_date='" + born_date + '\'' +
                        ", country_code='" + country_code + '\'' +
                        ", passenger_id_type_code='" + passenger_id_type_code + '\'' +
                        ", passenger_id_type_name='" + passenger_id_type_name + '\'' +
                        ", passenger_id_no='" + passenger_id_no + '\'' +
                        ", passenger_type='" + passenger_type + '\'' +
                        ", passenger_flag='" + passenger_flag + '\'' +
                        ", passenger_type_name='" + passenger_type_name + '\'' +
                        ", mobile_no='" + mobile_no + '\'' +
                        ", phone_no='" + phone_no + '\'' +
                        ", email='" + email + '\'' +
                        ", address='" + address + '\'' +
                        ", postalcode='" + postalcode + '\'' +
                        ", first_letter='" + first_letter + '\'' +
                        ", recordCount='" + recordCount + '\'' +
                        ", total_times='" + total_times + '\'' +
                        ", index_id='" + index_id + '\'' +
                        ", gat_born_date='" + gat_born_date + '\'' +
                        ", gat_valid_date_start='" + gat_valid_date_start + '\'' +
                        ", gat_valid_date_end='" + gat_valid_date_end + '\'' +
                        ", gat_version='" + gat_version + '\'' +
                        '}';
            }
        }
    }

    public static class ValidateMessagesBean {
    }

    @Override
    public String toString() {
        return "Passengers{" +
                "validateMessagesShowId='" + validateMessagesShowId + '\'' +
                ", status=" + status +
                ", httpstatus=" + httpstatus +
                ", data=" + data.toString()+
                ", validateMessages=" + validateMessages.toString() +
                ", messages=" + messages.toString() +
                '}';
    }
}
