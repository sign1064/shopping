package com.study.contents;

import java.io.File;

public class Contents {
    public static int RECORD_PER_PAGE = 3;

    public static synchronized String getUploadDir() {
        String path = "";
        if (File.separator.equals("\\")) {
            path = "C:/AIstudy/deploy/shopping/contents/storage/";
            System.out.println("Windows 10: " + path);
            
        } else {
            path = "/home/ubuntu/deploy/shopping/contents/storage/";
        }
        
        return path;
    }

}
