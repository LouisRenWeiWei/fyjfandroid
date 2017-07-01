package com.fyjf.blservice;


/**
 * Created by 任伟伟
 * Datetime: 2016/12/9-11:50
 * Email:
 */

public class TestService {
    private static TestService instance = new TestService();

    public static TestService getInstance() {
        return instance;
    }

    private TestService() {
    }

}
