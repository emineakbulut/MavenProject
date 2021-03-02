package com.hrms.utils;

import net.minidev.json.JSONObject;

public class apiPayloadConstants {
    public static String createEmployeeBody(){

        JSONObject obj=new JSONObject();

        obj.put("emp_firstname","Alma");
        obj.put("emp_lastname","Aldenia");
        obj.put("emp_middle_name","Tina");
        obj.put("emp_gender","F");
        obj.put("emp_birthday","2021-02-19");
        obj.put("emp_status","Employee");
        obj.put("emp_job_title","IT Analyst");

        return obj.toString();

    }
}
