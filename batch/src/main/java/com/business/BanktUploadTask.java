package com.business;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.core.controller.service.batch.IBatCutCtlService;
import com.core.controller.service.dict.IDictService;
import com.core.controller.service.pos.IPosInfoService;
import com.core.controller.service.unit.IUnitInfoService;
import com.core.models.TBatDayCutCtl;
import com.core.models.TDict;
import com.core.models.TUnitInfo;
import com.core.models.common.Order;
import com.toolbox.util.DateUtil;
import com.toolbox.util.StringUtil;


@Service("BanktUploadTask")
public class BanktUploadTask implements IHandleTask {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IDictService dictService;

    @Autowired
    private IPosInfoService posInfoService;

    @Autowired
    private IBatCutCtlService batCutCtlService;

    @Autowired
    private IUnitInfoService unitInfoService;

    @Value("${bankmark}")
    private String BANKMARK; //POSS

    @Value("${vername}")
    private String VERNAME; //版本号

    private final String PATH = "G:\\文档\\桌面\\FILE\\"; // temp

    /**
     * 银行文件上传
     */
    @Override
    public boolean doBusiness(String taskdate) throws Exception {

        /**
         * 查询参数
         */
        String PBOCCARD = dictService.findObjByKey("PBOCCARD").getCvalue();//物理卡类型 1,3
        String possoffpath = dictService.findObjByKey("POSSOFFPATH").getCvalue(); //消费文件
        String settleing = dictService.findObjByKey("SETTLEING").getCvalue(); //清算中
        String hospital = dictService.findObjByKey("HOSPITAL").getCvalue(); //医院编号
        String bankUnitFlag = dictService.findObjByKey("BANKUNITFLAG").getCvalue(); //代理机构标识码
        TDict dict = dictService.findObjByKey("BANKID");// 批量清算机构
        TBatDayCutCtl batDayCutCtl = this.getBatDayCutCtl();//日切时间
        List<TUnitInfo> unitlist = this.getUnitByBusType();

        /**
         * 参数
         */
        String TimePrefix = batDayCutCtl.getPrevtxndt();
        String mark = BANKMARK;
        String filePath = possoffpath;

        if(taskdate != null) {
            TimePrefix = taskdate;
        }

        for (TUnitInfo obj : unitlist) {

            if (obj.getBankid() == null || obj.getBankorgid() == null || obj.getBankposoffseq() == null) {
                continue;
            }

            /**对账文件名**/
            StringBuffer fileName = new StringBuffer();

            fileName.append(hospital);//医院编号,现一卡通平台
            fileName.append("INF");
            fileName.append(DateUtil.formatDate(new Date(),"yyMMdd"));//上传日期
            fileName.append("01C");//固定01C (01C表示脱机清算数据)

            File file = new File(filePath + "/" );
            if (!file.exists()) {
                file.mkdirs();
            }

            File batchFile = new File(filePath + "/" + fileName);
            if (batchFile.exists()) {
                batchFile.delete();
            }

            FileOutputStream out = new FileOutputStream(batchFile);
            OutputStreamWriter writer = new OutputStreamWriter(out, "GBK");

            // 组装数据段:文件头
            String TRANCD1 = "000"; // 交易代码 默认000
            String BITMAP1 = "    "; // 位图 默认8000
            String INSNO = StringUtil.rightAddChar(dict.getCvalue(), 11, " ");// 银行机构
            String BATDTE = DateUtil.formatDate(new Date(),"yyMMdd");// 批结日期
            String RESV0 = DateUtil.formatDate(new Date(), "yyMMdd"); //结算日期
            String verName = VERNAME; // 版本标记 (TEST/PROD)
            String VERNO = "00000001"; // 版本号

            writer.write(TRANCD1);
            writer.write(BITMAP1);
            writer.write(INSNO);
            writer.write(BATDTE);
            writer.write(RESV0);
            writer.write(verName);
            writer.write(VERNO);
            //writer.write("\n"); // 换行

            // 写入交易数据
            //List<TBustxnLog> buslist = this.getBusDatas(PBOCCARD, TimePrefix, obj.getUnitid());
            int size = 2;

            /*if (buslist != null && buslist.size() > 0) {
                size = size + buslist.size();
                String termSeq = "0001";
                TPosInfo tPosInfo = null;
                for (TBustxnLog temp : buslist) {
                    tPosInfo = posInfoService.findObjByKey(temp.getPosid());
                    termSeq = tPosInfo.getTermseq();// Pos终端序列号

                    if (termSeq != null && termSeq.length() >= 4) {
                        termSeq = termSeq.substring(termSeq.length() - 4);
                    } else {
                        termSeq = "0001";
                    }
                    writer.write("300"); // 交易代码
                    writer.write("A000"); // 段位图(补空格)
                    writer.write(StringUtil.rightAddChar(temp.getCardno(), 19, " ")); // 卡号
                    writer.write(StringUtil.leftAddChar(temp.getTxnamt().toString(), 12, "0"));// 交易金额
                    writer.write("156"); // 交易货币代码
                    writer.write(temp.getSysdatetime().substring(4, 14)); // 交易传输时间
                    writer.write(StringUtil.leftAddChar(temp.getSysseqno(), 6, "0")); // 系统跟踪号
//                    |57  |6  |授权应答标识码    |ECC001 <-- 固定值
//                    writer.write(temp.getEcauthcode());
                    writer.write("ECC001"); // 授权应答标识码
                    //writer.write(StringUtil.leftAddChar("", 4, " ")); // 授权日期
                    writer.write(DateUtil.formatDate(new Date(),"MMdd"));
//                    检索参考号        |000001106315 <-- 参考IC卡脱机清算--文件接口说明书V1.5文件的2.4节
                    writer.write("00" + termSeq + StringUtil.leftAddChar(temp.getSysseqno(), 6, "0")); // 检索参考号
                    writer.write(StringUtil.rightAddChar(bankUnitFlag,11," "));// 代理机构标识码
                    writer.write(StringUtil.rightAddChar(bankUnitFlag,11," ")); // 发送机构标识码
//                    |101 |4  |商户类型          |8398 <-- 这个从113段的15位商户编号中7到11位中截取
                    writer.write(temp.getBranchid().substring(7,11));// 商户类型
                    writer.write(temp.getPosid());// 终端号
                    writer.write(StringUtil.rightAddChar(temp.getBranchid(), 15, " ")); // 商户号
                    writer.write(StringUtil.rightAddChar("gongjiao", 40, " ")); // 受卡方名称地址
                    writer.write(StringUtil.rightAddChar("", 23, "0")); // 原始交易信息
                    writer.write("0000"); // 报文原因代码
                    writer.write("0"); // 单双信息标志
                    writer.write("000000000"); // CUPS流水号
                    writer.write(StringUtil.rightAddChar(bankUnitFlag,11," "));// 接收机构代码
                    writer.write(StringUtil.rightAddChar(bankUnitFlag,11," ")); // 发卡机构代码
                    writer.write("0"); // CUPS通知标志
                    writer.write("03"); // 交易发起渠道:pos
//                    |230 | 1 | 交易特征标识 |<--固定值
                    writer.write(" "); // 交易特征标识
//                    |231 |8  |CUPS保留使用      |         <-- 固定值
                    writer.write(StringUtil.leftAddChar("", 8, " ")); // CUPS保留使用
//                    |239 |2  |POS服务点条件代码 |00 <-- 固定值
                    writer.write("00");
//                    |241 |12 |本方手续费        | 00000000000 <-- 固定值
                    writer.write("C00000000001");// 本方手续费
//                    |253 |1  |交易地域标志      |0 <-- 固定值
                    writer.write("0"); // 交易跨境标志:0
//                    |254 |2  |ECI标志           |00 <-- 固定值
                    writer.write("00"); // ECI标志
//                    |256 |2  |特殊计费标志      |00 <-- 固定值
                    writer.write("00"); // 保留使用
//                    |258 |1  |特殊计费档次      |0 <-- 固定值
                    writer.write("0"); // 保留使用
//                    |259 |1  |交易发起方式      |1 <-- 固定值
                    writer.write("1"); // 保留使用
//                    |260 |9  |保留使用          |          <-- 固定值
                    writer.write(StringUtil.leftAddChar("", 9, " ")); // 保留使用
                    writer.write(temp.getAc());// 应用密文
                    writer.write("072"); // 服务点输入方式码
                    String carseq = temp.getCarseq();//卡片序列号
                    if (carseq != null && carseq.length() > 0) {
                        writer.write(carseq);
                    } else {
                        writer.write("000"); // 卡片序列号
                    }
                    writer.write("6");// 终端读取能力 :6
                    writer.write("0");// IC卡条件代码
                    writer.write(temp.getTvm());// 终端性能
                    writer.write(temp.getTvr());// 终端验证结果
                    writer.write(temp.getRand());// 不可预知数
//                    |48  |8  |接口设备序列号    |00010001 <--目前使用建行POS编号的后4位（如：0001、0002、0003等）+ 建行POS编号的后4位（如：0001、0002、0003等）
                    writer.write(termSeq + termSeq);// 接口设备序列号
                    writer.write(temp.getAppdata());// 发卡行应用数据
                    writer.write(temp.getAtc());// 应用交易记数器
                    writer.write(temp.getAip());// 应用交互特征
                    writer.write(temp.getTxndatetime().substring(2, 8));// 交易日期 20
                    // 150724
                    // 123000
                    writer.write(temp.getTermstatecode());// 终端国家代码
                    writer.write("Y1");// 交易响应码
                    writer.write(temp.getTrantp());// 交易类型
                    writer.write(StringUtil.leftAddChar(temp.getTxnamt().toString(), 12, "0"));
                    writer.write("156");// 交易币种代码
                    writer.write("1"); // 应用密文校验结果
                    writer.write(temp.getCardenddt());// 卡有效期
                    writer.write(temp.getCptinfo());// 密文信息数据
                    writer.write(StringUtil.leftAddChar(temp.getOtheramt(), 12, "0"));// 其他金额
                    writer.write(StringUtil.leftAddChar("", 6, " ")); // |175 |6  |持卡人验证方法结果|       <-- 固定值
                    writer.write(StringUtil.leftAddChar("22", 2, " ")); // |181 |2  |终端类型          |22 <-- 固定值
                    // |183 |32 |专用文件名称      |A000000333010101
                    writer.write(StringUtil.rightAddChar(temp.getDf(), 32, " "));
                    writer.write(StringUtil.leftAddChar("", 4, " ")); // |215 |4  |应用版本号        |
                    // |219 |8  |交易序列计数器    |
                    writer.write(StringUtil.rightAddChar(temp.getPosseq().substring(temp.getPosseq().length() - 6), 8, " "));
                    writer.write(temp.getEcauthcode()); // |227 |6  |电子发卡行授权码  |ECC001 <-- 固定值
                    writer.write(StringUtil.leftAddChar("", 24, " ")); // |233 |24 |卡产品标识信息
                    //writer.write("\n");
                }
            }*/


            // 写入文件尾
            writer.write("001");// 交易代码
            writer.write("    ");// 段位图
            writer.write(StringUtil.leftAddChar(String.valueOf(size) + "", 10, "0")); // 交易记录数
            writer.write(StringUtil.leftAddChar("", 16, " "));// MAK
            writer.write(StringUtil.leftAddChar("", 16, " "));// MAC

            writer.flush();
            if (writer != null) {
                writer.close();
            }

           /* // 上传完毕更新数据库记录
            for (TBustxnLog log : buslist) {
                log.setSettlestat(settleing);// 清算中
                try {
                    busTxnLogService.modifyBasic(log);
                } catch (Exception e) {
                    logger.error("********脱机交易数据更新失败:" + log.getCardno());
                }
            }*/

        }

        return true;
    }

    /**
     * 从数据库获记录,返回一个List
     *
     * @param @param  tBustxnLog
     * @param @return
     * @param @throws Exception
     * @return
     * @throws
     * @author tanhw
     * @date 2015年7月24日 上午10:47:54
     */
    /*private List<TBustxnLog> getBusDatas(String cardmodel, String settledate, long unitid) throws Exception {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("cardmodel", cardmodel);
        params.put("settledate", settledate);
        params.put("unitid", unitid);
        params.put("constype", CommonConstant.ConsType.money.toString());

        Order order = Order.asc("settlestat");

        List<TBustxnLog> list = busTxnLogService.findListByParams(params,order);

        return list;
    }*/

    /**
     * 获取日切
     * @return
     * @throws Exception
     */
    private TBatDayCutCtl getBatDayCutCtl()throws Exception{
        Map<String, Object> params = new HashMap<String, Object>();
        TBatDayCutCtl batDayCutCtl = batCutCtlService.findObj(params);
        return batDayCutCtl;
    }

    private List<TUnitInfo> getUnitByBusType()throws Exception{

        TDict dict = dictService.findObjByKey("BUSKIND");// 机构种类

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("unitkind", dict.getCvalue());

        Order order = Order.asc("unitid");
        return unitInfoService.findListByParams(params, order);
    }

}
 