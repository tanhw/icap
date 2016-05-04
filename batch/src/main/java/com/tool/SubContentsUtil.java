package com.tool;

import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 西 on 2015/7/22.
 */
public class SubContentsUtil {


    /**
     * 获取解析后的对象
     *
     * @param sourceFileName 解析格式文件名称
     * @param txt            报文
     * @param c              model对象
     * @param <T>            任意model
     * @return 解析后的model
     * @throws Exception
     */
    public static <T> T  getModel(String sourceFolderPath, String sourceFileName, String txt, Class<T> c,Class childClass, String lengthflag, int lengthfine ) throws Exception {

        if (sourceFolderPath == null) {
            Exception ex = new Exception("Not set resource folder path!");
            throw ex;
        }

        URL classPath = SubContentsUtil.class.getResource("/");

        StringBuffer sourcePath = new StringBuffer(classPath.getPath());

        sourcePath.append(sourceFolderPath);

        sourcePath.append("/");

        sourcePath.append(sourceFileName);

        List<String> contentList = SubContentsUtil.getSourceCentent(sourcePath.toString());

        String[][] content = SubContentsUtil.getFieldsAndValuesByList(contentList, txt, lengthflag, lengthfine);
        return SubContentsUtil.setObjectBychildList(c,childClass,content[0], content[1],lengthflag,lengthfine);

    }

    /**
     * 获取object解析对象
     *
     * @param c   model对象
     * @param <T> 任意model
     * @return 解析后的model
     * @throws Exception
     */
    private static <T> T setObject(Class<T> c, String[] fields, String[] values, int i) throws Exception {

        T t = c.newInstance();

        for (; i < fields.length; i++) {

            if (fields[i] == null) {
                continue;
            }

            if (fields[i].equals("*")) {
                String[] bodyfomat = values[i].split(","); //是否循环标识,报文体循环次数,赋值对象
                if (bodyfomat.length != 3) {
                    throw new Exception("The " + i + " line parsing file format error");
                }

                if (bodyfomat[1].equals("start")) {
                    Field field = c.getDeclaredField(bodyfomat[2]);
                    field.setAccessible(true);
                    Class type = field.getType();
                    Object object = SubContentsUtil.setObject(type, fields, values, ++i);
                    field.set(t, field.getType().cast(object));
                    continue;
                } else if (bodyfomat[1].equals("end")) {
                    i++;
                    return t;
                } else {

                }
            }

            String fieldName = fields[i];
            String value = values[i];

            Field field = c.getDeclaredField(fieldName);
            field.setAccessible(true);

            String fieldType = field.getType().toString();

            if ("int".equalsIgnoreCase(fieldType)) {
                field.setInt(t, Integer.parseInt(value));
            } else if (fieldType.lastIndexOf("Integer") != -1) {
                field.set(t, Integer.valueOf(value));
            } else if ("long".equalsIgnoreCase(fieldType)) {
                field.setLong(t, Long.parseLong(value));
            } else if (fieldType.lastIndexOf("Long") != -1) {
                field.set(t, Long.valueOf(value));
            } else if ("short".equalsIgnoreCase(fieldType)) {
                field.setShort(t, Short.parseShort(value));
            } else if (fieldType.lastIndexOf("Short") != -1) {
                field.set(t, Short.valueOf(value));
            } else {
                field.set(t, field.getType().cast(value));
            }
        }
        return t;
    }


    /**
     * 根据报文截取字段&值
     *
     * @param contentList
     * @param txt
     * @return
     * @throws Exception
     */
    private static String[][] getFieldsAndValues(List<String> contentList, String txt) throws Exception {

        if (contentList == null || contentList.size() == 0) {
            Exception ex = new Exception("There is no content in parameters!");
            throw ex;
        }

        String[] fields = new String[contentList.size()];
        String[] values = new String[contentList.size()];

        int i = 0;
        for (String contentLine : contentList) {

            String[] content = contentLine.split("\\|");

            if (content[0].equals("*")) {//命令标记
                fields[i] = content[0];//字段
                values[i] = content[1];//值
            } else {
                int length = Integer.parseInt(content[1]);
                String value = txt.substring(0, length);
                txt = txt.substring(length);

                if (!content[0].equals("-")) {//忽略标记
                    fields[i] = content[0];//字段
                    values[i] = value;//值
                }
            }
            i++;
        }

        String[][] resultsArray = {fields, values};

        return resultsArray;
    }


    /**
     * 获取object解析对象
     *
     * @param c   model对象
     * @param <T> 任意model
     * @return 解析后的model
     * @throws Exception
     */
    private static <T> T setObjectBychildList(Class<T> c, Class m, String[] fields, String[] values, String lengthflag, int lengthfine) throws Exception {

        int size = 1;
        int startline = -1;
        int endline = -1;
        boolean listflag = false;

        T t = c.newInstance();

        for (int i = 0; i < fields.length; i++) {

            if (fields[i] == null) {
                continue;
            }

            String[] bodyfomat = values[i].split(",");
            if (fields[i].equals("*")) {
                if (bodyfomat.length != 2) {
                    throw new Exception("The " + i + " line parsing file format error");
                }
                if (size >= 1 && bodyfomat[0].equals("start")) {
                    listflag = true;
                    startline = i + 1;
                } else if (size >= 1 && bodyfomat[0].equals("end")) {
                    listflag = false;
                    endline = i - 1;

                    String fieldName = bodyfomat[1];
                    Field field = c.getDeclaredField(fieldName);
                    field.setAccessible(true);

                    List list = new ArrayList();
                    int flagline = startline;
                    for (int j = 1; size >= j; j++) {
                        String[] childFields = new String[(endline - startline) + 1];
                        String[] childValues = new String[(endline - startline) + 1];

                        int length = (endline - startline) + 1;
                        System.arraycopy(fields, startline, childFields, 0, length);
                        System.arraycopy(values, flagline, childValues, 0, length);

                        if (j > 1) {
                            flagline += length;
                        } else {
                            flagline += (length + 1);
                        }

                        list.add(setObject(m, childFields, childValues, 0));
                    }

                    field.set(t, field.getType().cast(list));
                }
              continue;
            }
                if (!listflag) {

                    String fieldName = fields[i];
                    String value = values[i];

                    if (fieldName.equals(lengthflag)) {//忽略标记
                        size = Integer.parseInt(value);//值
                        size += lengthfine;
                    }

                    Field field = c.getDeclaredField(fieldName);
                    field.setAccessible(true);

                    String fieldType = field.getType().toString();

                    if ("int".equalsIgnoreCase(fieldType)) {
                        field.setInt(t, Integer.parseInt(value));
                    } else if (fieldType.lastIndexOf("Integer") != -1) {
                        field.set(t, Integer.valueOf(value));
                    } else if ("long".equalsIgnoreCase(fieldType)) {
                        field.setLong(t, Long.parseLong(value));
                    } else if (fieldType.lastIndexOf("Long") != -1) {
                        field.set(t, Long.valueOf(value));
                    } else if ("short".equalsIgnoreCase(fieldType)) {
                        field.setShort(t, Short.parseShort(value));
                    } else if (fieldType.lastIndexOf("Short") != -1) {
                        field.set(t, Short.valueOf(value));
                    } else {
                        field.set(t, field.getType().cast(value));
                    }
                }

        }
            return t;
        }


    /**
     * 根据报文截取字段&值
     *
     * @param contentList
     * @param txt
     * @return
     * @throws Exception
     */
    private static String[][] getFieldsAndValuesByList(List<String> contentList, String txt, String lengthflag, int lengthfine) throws Exception {

        int size = 1;
        int startline = -1;
        int endline = -1;
        boolean listflag = false;

        if (contentList == null || contentList.size() == 0) {
            Exception ex = new Exception("There is no content in parameters!");
            throw ex;
        }

        ArrayList<String> fieldList = new ArrayList<>();
        ArrayList<String> valueList = new ArrayList<>();

        int i = 0;
        int count = contentList.size();
        for (; ; ) {
            if (count <= i) {
                break;
            }

            String contentLine = contentList.get(i);
            String[] content = contentLine.split("\\|");

            if (content[0].equals("*")) { //命令标记
                fieldList.add(content[0]);//字段
                valueList.add(content[1]);//值

                String[] bodyfomat = content[1].split(",");

                if (bodyfomat.length != 2) {
                    throw new Exception("The " + i + " line parsing file format error");
                };

                ++i;
                if (size > 1 && bodyfomat[0].equals("start")) {
                    startline = i;
                } else if (size > 1 && bodyfomat[0].equals("end")) {
                    listflag = true;
                    endline = i -2;
                    i = startline;
                    size--;
                }
                continue;
            }

            int length = Integer.parseInt(content[1]);
            String value = txt.substring(0, length);
            txt = txt.substring(length);

            if (!content[0].equals("-")) {//忽略标记
                if(!listflag){
                    fieldList.add(content[0]);//字段
                }
                valueList.add(value);//值
            }

            if (content[0].equals(lengthflag)) {//长度转换
                size = Integer.parseInt(value);//值
                size += lengthfine;
            }

            if (size > 1 && i == endline) {
                i = startline;
                size--;
                continue;
            } else if (size == 1 && i == endline) {
                i++;
            }
            i++;
        }

        String [] values = new String[valueList.size()];
        String [] fieldes = new String[fieldList.size()];

        String[][] resultsArray = {fieldList.toArray(fieldes), valueList.toArray(values)};

        return resultsArray;
    }


    /**
     * 获取格式文件内容
     *
     * @param sourcePath
     * @return
     * @throws Exception
     */
    private static List<String> getSourceCentent(String sourcePath) throws Exception {

        FileReader fileReader = new FileReader(sourcePath);

        BufferedReader reader = new BufferedReader(fileReader);

        List<String> contentList = new ArrayList<>();

        boolean flag;

        do {
            String line = reader.readLine(); //读取行
            flag = line != null; //是否是内容尾

            if (flag) {
                contentList.add(line); //保存行
            }

        } while (flag);

        return contentList;
    }

}
