package com.fyjf.dao.entity;

import java.io.Serializable;

/**
 * Created by czf on 2017/7/12.
 */

public class LoanTime implements Serializable{
    private String yearMonth;
    private String count;

    public String getYearMonth() {
        return yearMonth;
    }

    public void setYearMonth(String yearMonth) {
        this.yearMonth = yearMonth;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "LoanTime{" +
                "yearMonth='" + yearMonth + '\'' +
                ", count='" + count + '\'' +
                '}';
    }
}
