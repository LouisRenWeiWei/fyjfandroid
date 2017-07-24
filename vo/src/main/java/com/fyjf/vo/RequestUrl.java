package com.fyjf.vo;

public class RequestUrl {
    public static String server = "http://47.93.217.20:8081";//线上
//    public static String server = "http://192.168.1.104";//家里

    //静态文件
    public static String file_base = server + "/storage";//
    public static String pdf_file_ext = ".pdf";
    public static String file_pdf_report = file_base + "/pdf/";
    public static String file_pdf = file_base + "/";//
    public static String file_image = file_base + "/";//
    public static String file_apk = file_base + "/";//

    public static String html_base = server + "/fyjfadmin";
    public static String customer_report_analysis = html_base + "/fyjf/report/echarts.html?customerId=";

//    public static String api_base = server + "/api/api";//家里ip
    public static String api_base = server + "/fyjf/api";//线上ip

    public static String login = api_base + "/bank/user/login";
    public static String changePasswd = api_base + "/bank/user/update/passwd";
    public static String report_list_msg = api_base + "/report/msg/list";
    public static String report_msg_add = api_base + "/report/msg/save";
    public static String report_get = api_base + "/report/get";
    public static String overdue_list = api_base + "/overdue/list/app";//逾期催收列表
    public static String version_check = api_base + "/app/version/last";
    public static String contact_us = api_base + "/dict/get/contact/us";
    public static String report_month = api_base + "/report/statistics/by/month";
    public static String report_custom = api_base + "/report/list/app";
    public static String report_msgs = api_base + "/report/msg/list/app";
    public static String report_imags = api_base + "/report/get/images";
    public static String report_progress = api_base + "/overdue/progress/list/app";
    public static String report_analysis = api_base + "/bank/analysis/list/app";
    public static String overdue_msgs = api_base + "/overdue/msg/list/app";
    public static String overdue_msgs_send = api_base + "/overdue/msg/save";
    public static String managers_list = api_base +  "/user/list/bank/managers";
    public static String report_msg_save = api_base +  "/report/msg/save";
}
