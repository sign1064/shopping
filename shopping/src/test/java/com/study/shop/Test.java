package com.study.shop;

import java.io.File;

public class Test {

    public static void main(String[] args) {
        // 기준 경로 확인
        String user_dir = System.getProperty("user.dir"); // 시스템 제공
        System.out.println("user_dir: " + user_dir); // user_dir: C:\AIstudy\ojt\shopping
        
        String separator = File.separator;
        System.out.println("separator: " + separator);
        
        if (separator.equals("\\")) {
            System.out.println("Windows 10");
        } else {
            System.out.println("Linux");
        }
        
        // OS
        String os = System.getProperty("os.name").toLowerCase();
        System.out.println("os: " + os);  // windows 10, linux, mac os x
        
        if (os.equals("mac os x")) { // Mac
            System.out.println("맥");
        } else if (os.equals("windows 10")) {
            System.out.println("os: " + os);
        } else if (os.equals("linux")) {
            System.out.println("리눅스");
        } 
        
                
    }

}
