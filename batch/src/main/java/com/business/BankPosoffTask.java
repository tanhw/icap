package com.business;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.core.controller.service.batch.IBatCutCtlService;
import com.core.controller.service.dict.IDictService;
import com.core.models.TBatDayCutCtl;
import com.toolbox.log.LogUtil;

@Service("BankPosoffTask")
public class BankPosoffTask implements IHandleTask {

    public final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IDictService dictService;

    @Autowired
    private IBatCutCtlService batCutCtlService;

    private final String BACK = "BACK";

    private final String RESULT = "result";

    private final String FILEWITH = "POSOFF-IC-FAL-";

    private final String PATH = "G:\\文档\\桌面\\FILE\\"; // temp


    /**
     * 银行文件勾兑
     *
     * @return
     */
    @Override
    public boolean doBusiness(String taskDate) throws Exception {

        String possoffpath = dictService.findObjByKey("POSSOFFPATH").getCvalue(); //文件目录
        TBatDayCutCtl batDayCutCtl = this.getBatDayCutCtl();//日切时间

        /**
         * 参数
         */
        String TimePrefix = batDayCutCtl.getPrevtxndt();
        String filepath = possoffpath;
        BufferedReader reader;
        FileInputStream in;
        String readStr;

        if(taskDate != null) {
            TimePrefix = taskDate;
        }

        /** 勾兑文件目录 **/

        String resultPath = filepath + RESULT + "//";
        File posoffFile = new File(resultPath);
        if (!posoffFile.exists()) { //目录不存在
            posoffFile.mkdirs();
        }

        File[] childrenFile = posoffFile.listFiles();
        for (File file : childrenFile) {
            int index = 0;
            if (file.isDirectory()) {//判断文件夹
                continue;
            }
            String fileName = file.getName(); //获取文件名
            String[] posoff = fileName.split("-"); //分解
            String txnDate = posoff[posoff.length - 1];//文件时间

            if (fileName.startsWith(FILEWITH) && txnDate.equals(TimePrefix)) {
                File localFile = new File(posoffFile.getPath() + "//" + fileName);
                try {
                    in = new FileInputStream(localFile);
                    reader = new BufferedReader(new InputStreamReader(in, "gbk"));

                    try {
                        while ((readStr = reader.readLine()) != null) {
                            index++;
                            if (index < 4) {
                                continue;
                            }
                            byte[] readByte = readStr.getBytes("gbk");
                            String orgId = new String(readByte, 0, 8, "gbk")
                                    .trim();
                            if ("".equals(orgId)) {
                                continue;
                            }
                            if (orgId.equals("总计")) {
                                break;
                            }
                            String seqNo = new String(readByte, 26, 6, "gbk").trim();
                            String txnTime = new String(readByte, 35, 10, "gbk").trim();
                            String txnSeqNo = txnDate.substring(0, 4) + txnTime;

                            Matcher m = Pattern.compile("\\[(\\d+)\\]").matcher(readStr);
                            if (m.find()) {
                                logger.error(m.group()); //!!
                            }

                            try {
//                                int buscount = bustxnLogService.modifySuattByTxnSeq(txnSeqNo, seqNo);
//                                if (buscount != 1 ) {
//                                    logger.error("流水中没有找到：[{}] ", txnSeqNo + seqNo);
//                                    return false;
//                                }
                            } catch (Exception e) {
                                logger.info(LogUtil.getTrace(e));
                                return false;
                            }
                        }
                    } catch (IOException e) {
                        logger.info(LogUtil.getTrace(e));
                        return false;
                    } finally {
                        if (in != null) {
                            try {
                                in.close();
                            } catch (IOException e) {
                                logger.info(LogUtil.getTrace(e));
                            }
                        }
                        if (reader != null) {
                            try {
                                reader.close();
                            } catch (Exception e) {
                                logger.info(LogUtil.getTrace(e));
                            }
                        }
                    }

                } catch (FileNotFoundException e) {
                    logger.info(LogUtil.getTrace(e));
                    return false;
                } catch (UnsupportedEncodingException e) {
                    logger.info(LogUtil.getTrace(e));
                    return false;
                } catch (Exception e) {
                    logger.info(LogUtil.getTrace(e));
                    return false;
                }
            }


            String backPath = filepath + BACK + "//" + txnDate + "//";
            File backpath = new File(backPath);

            if (!backpath.exists()) {
                backpath.mkdirs();
            }


            File oldfile = new File(resultPath + fileName);
            File newfile = new File(backPath + fileName);


            boolean success = oldfile.renameTo(newfile);

            logger.info("移动文件： " + resultPath + fileName + " 至 " + backPath + fileName + "  结果：[{}]", success);
        }

        this.updateSussTxnLog(TimePrefix); //批量修改成功
        return true;
    }


    /**
     * 修改流水数据状态
     *
     * @param settledate
     * @throws Exception
     */
    private void updateSussTxnLog(String settledate) throws Exception {

        String settleing = dictService.findObjByKey("SETTLEING").getCvalue(); // 清算中

        String settlesucc = dictService.findObjByKey("SETTLESUCC").getCvalue(); // 清算成功

        //bustxnLogService.modifyBySettlestat(settlesucc, settleing,settledate);

    }

    /**
     * 获取日切
     *
     * @return
     * @throws Exception
     */
    private TBatDayCutCtl getBatDayCutCtl() throws Exception {
        Map<String, Object> params = new HashMap<String, Object>();
        TBatDayCutCtl batDayCutCtl = batCutCtlService.findObj(params);
        return batDayCutCtl;
    }
}
