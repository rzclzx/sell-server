package com.imooc.util;

public class PathUtil {
    private static String separator = System.getProperty("file.separator");

    public static String getImgBasePath() {
        String os = System.getProperty("os.name");

        String basePath = "";

        if(os.toLowerCase().startsWith("win")) {
            basePath = "D:/project/dev/image/";
        }else {
            basePath = "/Users/zhangfei/Pictures/";
        }
        basePath = basePath.replace("/", separator);
        return basePath;
    }

    public static String getPath(String name) {
        String imagePath = name + "/";
        return imagePath.replace("/", separator);
    }
}
