package com.sibat.domain.pojo;

/**
 * Created by tgw61 on 2017/5/5.
 */
public class LocalPoliceEventCount {
    private String policeId;
    private String police;
    private String count;

    public LocalPoliceEventCount() {
    }

    public LocalPoliceEventCount(String policeId, String police, String count) {
        this.policeId = policeId;
        this.police = police;
        this.count = count;
    }

    public String getPoliceId() {
        return policeId;
    }

    public void setPoliceId(String policeId) {
        this.policeId = policeId;
    }

    public String getPolice() {
        return police;
    }

    public void setPolice(String police) {
        this.police = police;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }
}
