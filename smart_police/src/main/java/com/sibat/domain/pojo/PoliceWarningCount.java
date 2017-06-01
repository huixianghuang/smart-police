package com.sibat.domain.pojo;

import java.math.BigInteger;

/**
 * Created by Programmer on 2017/5/15.
 */
public class PoliceWarningCount {
    private String police;
    private String police_id;
    private BigInteger event_count_numbers;

    public PoliceWarningCount() {

    }

    public PoliceWarningCount(String police, String police_id, BigInteger event_count_numbers) {
        this.police = police;
        this.police_id = police_id;
        this.event_count_numbers = event_count_numbers;
    }

    public String getPolice() {
        return police;
    }

    public void setPolice(String police) {
        this.police = police;
    }

    public String getPolice_id() {
        return police_id;
    }

    public void setPolice_id(String police_id) {
        this.police_id = police_id;
    }

    public BigInteger getEvent_count_numbers() {
        return event_count_numbers;
    }

    public void setEvent_count_numbers(BigInteger event_count_numbers) {
        this.event_count_numbers = event_count_numbers;
    }
}
