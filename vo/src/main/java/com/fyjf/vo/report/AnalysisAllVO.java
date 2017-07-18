package com.fyjf.vo.report;

import com.fyjf.vo.BaseVO;
import com.fyjf.vo.RequestUrl;

/**
 * Created by czf on 2017/7/18.
 */

public class AnalysisAllVO extends BaseVO {
    @Override
    protected void setup() {
        setUrl(RequestUrl.report_analysis);
    }
}
