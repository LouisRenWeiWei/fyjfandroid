package com.fyjf.dao.entity;

import java.io.Serializable;

/**
 * Created by czf on 2017/7/12.
 */

public class LoanTime implements Serializable{
    private String yearMonth;
    private String count;
    private String reportImages;

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

    public String getReportImages() {
        return reportImages;
    }

    public void setReportImages(String reportImages) {
        this.reportImages = reportImages;
    }

    @Override
    public String toString() {
        return "LoanTime{" +
                "yearMonth='" + yearMonth + '\'' +
                ", count='" + count + '\'' +
                '}';
    }
}
