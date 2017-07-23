package com.fyjf.vo.overdue;

import com.fyjf.vo.BaseVO;
import com.fyjf.vo.RequestUrl;

/**
 * Created by czf on 2017/7/15.
 */

public class OverdueSendMsgVO extends BaseVO {

    @Override
    protected void setup() {
        setUrl(RequestUrl.overdue_msgs_send);
    }
}
