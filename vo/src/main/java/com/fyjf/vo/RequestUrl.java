package com.fyjf.vo;

public class RequestUrl {
    //静态文件
    // public static String file_base ="http://192.168.1.104/file";//家里ip
    public static String file_base ="http://192.168.0.215/file";//公司ip
    //public static String file_base ="http://47.93.217.20:8081/file";//线上
    public static String pdf_file =file_base+"/pdf/";
    public static String pdf_file_upload =file_base+"/upload/";
    public static String pdf_file_ext = ".pdf";
    public static String img_file_upload =file_base+"/upload/";

    public static String file_download =file_base+"/upload/";

    // public static String html_base ="http://192.168.1.104/fyjfadmin";//家里ip
    public static String html_base ="http://192.168.0.215/fyjfadmin";//公司ip
    //public static String html_base ="http://47.93.217.20:8081/fyjfadmin";//线上
    public static String customer_report_analysis = html_base+"/fyjf/report/echarts.html?customerId=";


    // public static String base ="http://192.168.1.104/api/api";//家里ip
    public static String base ="http://192.168.0.215/api/api";//公司ip
    //public static String base ="http://47.93.217.20:8081/fyjf/api";//线上
    public static String login =base+"/login/v2";
    public static String loan_check = base+"/customer/list/by/bank/user";
    public static String report_get = base+"/report/get";
    public static String overdue_list = base+"/overdue/list/by/bank/worker";
    public static String version_check = base+"/app/version/last";
}
