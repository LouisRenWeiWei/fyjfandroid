package com.fyjf.vo.user;

import com.fyjf.vo.BaseVO;
import com.fyjf.vo.RequestUrl;

/**
 * Created by ASUS on 2017/6/24.
 */
/*
* author: renweiwei
* datetime: 
*/
public class LoginVO extends BaseVO{

    @Override
    protected void setup() {
        setUrl(RequestUrl.login);
    }
}