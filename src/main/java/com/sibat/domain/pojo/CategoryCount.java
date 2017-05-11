package com.sibat.domain.pojo;

/**
 * Created by tgw61 on 2017/5/5.
 */
public class CategoryCount {
    private String name;
    private String count;

    public CategoryCount(String name, String count) {
        this.name = name;
        this.count = count;
    }

    public CategoryCount() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }
}
