package com.fyjf.vo.overdue;

import com.fyjf.vo.BaseVO;
import com.fyjf.vo.RequestUrl;

/**
 * Created by ASUS on 2017/6/24.
 */
/*
* author: renweiwei
* datetime: 
*/
public class OverduesVO extends BaseVO{

    @Override
    protected void setup() {
        setUrl(RequestUrl.overdue_list);
    }
}