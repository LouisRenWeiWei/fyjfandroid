package com.fyjf.vo.query;

import com.fyjf.vo.BaseVO;
import com.fyjf.vo.RequestUrl;

/**
 * Created by czf on 2017/7/23.
 */

public class MangerListVO extends BaseVO {
    @Override
    protected void setup() {
        setUrl(RequestUrl.managers_list);
    }
}
