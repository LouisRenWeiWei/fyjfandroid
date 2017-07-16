package com.fyjf.vo.report;

import com.fyjf.vo.BaseVO;
import com.fyjf.vo.RequestUrl;

/**
 * Created by czf on 2017/7/16.
 */

public class ReportProgressVO extends BaseVO {
    @Override
    protected void setup() {
        setUrl(RequestUrl.report_progress);
    }
}
