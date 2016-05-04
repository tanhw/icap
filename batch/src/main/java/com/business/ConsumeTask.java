package com.business;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.core.controller.service.batch.IBatCutCtlService;
import com.core.controller.service.dict.IDictService;
import com.core.controller.service.merchant.IMerchantInfoService;
import com.core.controller.service.pos.IPosInfoService;

/**
 * Created by 西 on 2015/7/22.
 * 消费处理文件
 */
@Service("ConsumeTask")
public class ConsumeTask implements IHandleTask {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IBatCutCtlService batCutCtlService;

    @Autowired
    private IMerchantInfoService merchantInfoService;

    @Autowired
    private IPosInfoService posInfoService;

    @Autowired
    private IDictService dictService;

    private static final String PATH = "G:\\文档\\桌面\\FILE\\"; // temp

    private static final String SUSSPATH = "G:\\文档\\桌面\\FILE\\SUSS"; // temp

    @Override
    public boolean doBusiness(String taskDate) {

    	/*logger.info("**** start the ConsumeTask ****");

        try {

            TBatDayCutCtl batDayCutCtl = batCutCtlService.findObj(new HashMap<String, Object>());//日切

            String path = dictService.findObjByKey("CONSUMEFILE").getCvalue(); //消费文件
            String sussPath = dictService.findObjByKey("CONSUMESUSSFILE").getCvalue(); //消费文件

            File file = new File(path);
            File[] tempList = file.listFiles();

            for (int i = 0; i < tempList.length; i++) {
                if (tempList[i].isFile()) {

                    String content = FileUtil.getFileByContent(tempList[i]); //获取文件内容
                    Consume consume = SubContentsUtil.getModel("fomat", "CONSUME_FOMAT", content, Consume.class, ConsumeBody.class, "recnum", 0); // 解析文件报文

                    String settlestatus = dictService.findObjByKey("NORMALTRANS").getCvalue(); //日切状态
                    String busi = dictService.findObjByKey("BUS").getCvalue(); //公交



                    int num =0;
                    List<TBustxnLog> list = new ArrayList<TBustxnLog>();
                    for (ConsumeBody body : consume.getBody()) {
                        *//**
                         * 动态查询
                         *//*
                        Map<String, Object> params = new HashMap<String, Object>();
                        *//** 商户 **//*
                        params.put("branchid", body.getMerid());
                        params.put("branchstate", CommonConstant.BranchState.StartUser.toString());
                        TMerchantInfo merchantInfo = merchantInfoService.findObj(params);
                        if (merchantInfo == null) {
                            logger.info("清算商户号：[{}] 不存在,或无效商户" , body.getMerid());
                            continue;
                        }


                        Long unitid = merchantInfo.getUnitid(); //机构ID

                        params.clear();

                        *//** 线路 **//*
                        params.put("linecode", body.getLineid());
                        params.put("unitid", unitid);
                        params.put("linestate", CommonConstant.CommState.TRUE.toString());
                        TLineInfo lineInfo = lineInfoService.findObj(params);
                        if (lineInfo == null) {
                            logger.info("线路号：[{}] 不存在，或无效线路" , body.getLineid());
                            continue;
                        }

                        params.clear();

                        *//** 终端 **//*
                        params.put("posid", body.getPosid() != null ? body.getPosid().substring(4): null);
                        params.put("unitid", unitid);
                        TPosInfo posInfo = posInfoService.findObj(params);
                        if (posInfo == null) {
                            logger.info("终端号：[{}] 不存在" , body.getLineid());
                            continue;
                        }

                        *//** 子账户 **//*
                        TUserBusacc userBusacc = userBusaccService.findObjByKey(body.getCardno());

                        *//**个人信息*//*
                        TUserData tUserData = userDataService.findObjByKey(body.getCardno().trim());

                        TBustxnLog txn = new TBustxnLog();
                        txn.setSettledate(batDayCutCtl.getCurrtxndt()); // 清算日切
                        txn.setSettlestat(settlestatus); //清算状态
                        txn.setUnitid(unitid); //机构ID
                        txn.setBranchid(body.getMerid());//清算商户号
                        txn.setMerseq(merchantInfo.getMerseq());//商户号
                        txn.setBankid(tUserData != null ? tUserData.getBankid():null); //银行ID
                        txn.setPosid(body.getPosid() != null ? body.getPosid().substring(4): null); //终端
                        txn.setPosseq(body.getPosseq()); //终端流水
                        txn.setPosupseq(body.getPosseq() != null ? body.getPosseq().substring(3): null); //终端流水
                        txn.setCollectid(body.getStationid()); //采集点编号
                        txn.setBusid(posInfo.getBusid() != null?posInfo.getBusid().toString():null); //采集点编号（车辆）
                        txn.setLinecode(body.getLineid()); // 线路代码
                        txn.setCitycode(body.getCitycode()); // 城市代码
                        txn.setCitycode(busi);//公交
                        txn.setTxndatetime(body.getTxndate() + body.getTxntime()); //交易系统时间日期
                        txn.setCardkind(body.getCardkind()); //卡类型
                        txn.setTac(body.getTac()); // 交易认证码
                        txn.setCardno(body.getEccpan()); // pan
                        txn.setCarseq(StringUtil.leftAddChar(body.getEccpanseq(), 3, "0"));//卡片序号
                        txn.setAc(body.getEccappencmsg());//应用密文
                        txn.setTvm(body.getEcctvm()); //终端性能
                        txn.setTvr(body.getEcctvr()); //终端性能结果
                        txn.setRand(body.getEccrand()); //不可未知数
                        txn.setAppdata(body.getEccissappdat()); //发卡行应用数据
                        txn.setAtc(body.getEccatc()); //应用交易计数器
                        txn.setAip(body.getEccaip()); //应用交易特征码
                        txn.setTrandt(body.getTxntime());//交易时间
                        txn.setTermstatecode(body.getAertcode()); //国家代码
                        txn.setOtheramt(body.getOtheramt()); //其他金额
                        txn.setBefbal(Long.parseLong(body.getBalbef())); //卡片交易前余额
                        txn.setTxnamt(Long.parseLong(body.getTxnamt())); //交易金额
                        txn.setOritxnamt(Long.parseLong(body.getTxnamt()));// 原交易金额
                        //txn.setCardenddt(body.getCardvaliddate()); //前效期
                        txn.setEcauthcode(body.getEcciac().trim());//电子现金发卡行授权码
                        txn.setTrantp(body.getEcctrantp()); //交易类型
                        txn.setCardmodel(body.getPhytype().substring(1)); //卡物理类型
                        txn.setCptinfo(body.getEccappmsgdat());//密文信息数据
                        if(txn.getCardmodel().equals("1")){//M1
                            txn.setConstype(CommonConstant.ConsType.number.toString());//扣次费
                        }else if (txn.getCardmodel().equals("3")) {//银行卡
                            txn.setConstype(CommonConstant.ConsType.money.toString());//现金扣费
                            if (body.getCardkind().equals("05")) {
                                txn.setConstype(CommonConstant.ConsType.number.toString());//扣次费
                            }
                        }

                        num++;
                        list.add(txn);
                    }

                    bustxnLogService.addBatch(list);

                    logger.info("处理交易共[{}]" , num);


                    *//**
                     * 移动文件
                     *//*
                    File moveFile = new File(sussPath + tempList[i].getName());
                    if (moveFile.exists()) {// 目标文件夹下存在的话，删除
                        moveFile.delete();
                    }
                    tempList[i].renameTo(moveFile);// 移动文件
                    logger.info("[{}]消费文件移动成功", tempList[i].getName());
                }

            }
        } catch (Exception e) {
            logger.info(LogUtil.getTrace(e));
            return false;
        }

        logger.info("**** end the ConsumeTask ****");
        return true;
        
        */
    	return false;
    }
}
