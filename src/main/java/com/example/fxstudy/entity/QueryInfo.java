package com.example.fxstudy.entity;


/**
 * @Author: D7-Dj
 * @Date: 2018/11/27 20:13
 */
public class QueryInfo {
    public QueryLeftNewDTO queryLeftNewDTO;
    public String buttonTextInfo;
    public String secretStr;

    public QueryInfo() {
    }

    public QueryInfo(QueryLeftNewDTO queryLeftNewDTO, String buttonTextInfo, String secretStr) {
        this.queryLeftNewDTO = queryLeftNewDTO;
        this.buttonTextInfo = buttonTextInfo;
        this.secretStr = secretStr;
    }

    public QueryLeftNewDTO getQueryLeftNewDTO() {
        return queryLeftNewDTO;
    }

    public void setQueryLeftNewDTO(QueryLeftNewDTO queryLeftNewDTO) {
        this.queryLeftNewDTO = queryLeftNewDTO;
    }

    public String getButtonTextInfo() {
        return buttonTextInfo;
    }

    public void setButtonTextInfo(String buttonTextInfo) {
        this.buttonTextInfo = buttonTextInfo;
    }

    public String getSecretStr() {
        return secretStr;
    }

    public void setSecretStr(String secretStr) {
        this.secretStr = secretStr;
    }
}
