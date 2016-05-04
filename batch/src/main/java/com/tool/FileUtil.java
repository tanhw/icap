package com.tool;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 * Created by 西 on 2015/7/23.
 */
public class FileUtil {

    /**
     * 获取文件内容
     * @param file
     * @return
     * @throws Exception
     */
    public static String getFileByContent(File file) throws Exception {
        /**
         * 获取文件内容
         */
        FileReader fileReader = new FileReader(file);

        BufferedReader reader = new BufferedReader(fileReader);

        StringBuffer txt = new StringBuffer();

        boolean flag;

        do {
            int value = reader.read(); //读取字符
            flag = value != -1; //是否是内容尾

            if (flag) {
                txt.append((char) value); //保存字符
            }

        } while (flag);

        if (reader != null) {
            reader.close();
        }

        if(fileReader != null){
            fileReader.close();
        }

        return txt.toString();
    }
}