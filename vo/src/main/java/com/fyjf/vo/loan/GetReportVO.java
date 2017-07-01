package com.fyjf.vo.loan;

import com.fyjf.vo.BaseVO;
import com.fyjf.vo.RequestUrl;

/**
 * Created by ASUS on 2017/6/24.
 */
/*
* author: renweiwei
* datetime: 
*/
public class GetReportVO extends BaseVO{

    @Override
    protected void setup() {
        setUrl(RequestUrl.report_get);
    }
}