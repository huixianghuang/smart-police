package com.sibat.domain.pojo;

import java.math.BigInteger;

/**
 * Created by Programmer on 2017/5/15.
 */
public class WarningTypeCount {

    private String type;
    private BigInteger type_count;

    public WarningTypeCount() {

    }
    public WarningTypeCount(String type, BigInteger type_count) {
        this.type = type;
        this.type_count = type_count;
    }
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BigInteger getType_count() {
        return type_count;
    }

    public void setType_count(BigInteger type_count) {
        this.type_count = type_count;
    }
}
