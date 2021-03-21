package com.hrms.utils;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.minidev.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class apiPayloadConstants {
    public static String createEmployeeBody(){

//        JSONObject obj=new JSONObject();
//
//        obj.put("emp_firstname","Alma");
//        obj.put("emp_lastname","Aldenia");
//        obj.put("emp_middle_name","Tina");
//        obj.put("emp_gender","F");
//        obj.put("emp_birthday","2021-02-19");
//        obj.put("emp_status","Employee");
//        obj.put("emp_job_title","IT Analyst");
//
//        return obj.toString();

        //just reading the file
        File input=new File("C:/Users/Work/eclipse-workspace/CucumberFrameWork/src/test/resources/JsonData/JsonData/createUser.json");
        JsonObject CreateUserData=null;
        try {
            //parsing the input file.json parser is a method that converts a string to a key of java obj
            //parseReaders return type is JsonElement
            JsonElement fileElement= JsonParser.parseReader(new FileReader(input));
     //       System.out.println(fileElement);//prints the user data
//we want to get some element from that file
            CreateUserData = fileElement.getAsJsonObject();//first convert my file json obj
            //System.out.println(CreateUserData);//we got json obj from the file
          //  String employee_firstname=CreateUserData.get("emp_firstname").getAsString();
            //System.out.println("The employee first name is "+employee_firstname);//printed first name
           // JsonElement employee_firstname=CreateUserData.get("emp_firstname");
        }

        catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return  CreateUserData.toString();
    }
}
