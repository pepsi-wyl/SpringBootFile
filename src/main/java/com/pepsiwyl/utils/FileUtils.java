package com.pepsiwyl.utils;

import java.text.DecimalFormat;

/**
 * @author by pepsi-wyl
 * @date 2022-07-31 10:53
 */

public class FileUtils {

    /**
     * 文件大小转化工具
     *
     * @param fileLength
     * @return B K M G
     */
    public static String formatFileSize(Long fileLength) {
        String fileSizeString = "";
        if (fileLength == null) return fileSizeString;
        DecimalFormat df = new DecimalFormat("#.00");
        if (fileLength < 1024) fileSizeString = df.format((double) fileLength) + "B";
        else if (fileLength < 1048576) fileSizeString = df.format((double) fileLength / 1024) + "K";
        else if (fileLength < 1073741824) fileSizeString = df.format((double) fileLength / 1048576) + "M";
        else fileSizeString = df.format((double) fileLength / 1073741824) + "G";
        return fileSizeString;
    }

}