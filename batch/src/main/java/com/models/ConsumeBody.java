package com.models;

/**
 * Created by 西 on 2015/7/22.
 * 消费交易
 */
public class ConsumeBody {

    private String txnseq;  //本地流水号

    private String txnattr;  //交易性质

    private String stationid;  //采集点编号

    private String lineid;   //线路编号

    private String merid;  //清算商户号

    private String posid;  //终端编号

    private String termid;  //pos终端编号

    private String lockflag;   //锁卡标识

    private String posseq;  //终端交易流水号

    private String citycode;    //城市代码

    private String cardno;    //卡号

    private String cardkind;    //主卡类型

    private String childcardkind;    //子卡类型

    private String cardvaliddate;   //卡片有效期

    private String eccpan;    //应用主账户

    private String eccpanseq;   //应用主账号序列

    private String eccdate;    //应用失效日期

    private String ecciac;    //电子现金发卡行授权码

    private String eccamtcode;    //应用货币代码

    private String eccappencmsg;   //应用密文

    private String eccappmsgdat;    //应用其信息数据

    private String eccissappdat;    //发卡行应用数据

    private String eccrand;    //不可预知数

    private String eccatc;   //应用交易计数器

    private String ecctvr;    //终端验证结果

    private String ecctrantp;   //交易类型

    private String eccaip;  //应用交互特征

    private String ecctvm;    //终端性能

    private String eccgc;   //授权响应码

    private String balbef;    //消费前卡余额（分）

    private String txnamt;   //交易发生日期

    private String txndate;    //交易发生时间

    private String txntime;    //交易发生日期

    private String tac;    //交易认证码

    private String cardverno;   //卡内版本号

    private String phytype;   //卡物理类型

    private String otheramt;    //它金额

    private String aertcode;   //终端国家代码

    private String cardtypetab;    //卡产品标示

    public String getTxnseq() {
        return txnseq;
    }

    public void setTxnseq(String txnseq) {
        this.txnseq = txnseq;
    }

    public String getTxnattr() {
        return txnattr;
    }

    public void setTxnattr(String txnattr) {
        this.txnattr = txnattr;
    }

    public String getStationid() {
        return stationid;
    }

    public void setStationid(String stationid) {
        this.stationid = stationid;
    }

    public String getLineid() {
        return lineid;
    }

    public void setLineid(String lineid) {
        this.lineid = lineid;
    }

    public String getMerid() {
        return merid;
    }

    public void setMerid(String merid) {
        this.merid = merid;
    }

    public String getPosid() {
        return posid;
    }

    public void setPosid(String posid) {
        this.posid = posid;
    }

    public String getTermid() {
        return termid;
    }

    public void setTermid(String termid) {
        this.termid = termid;
    }

    public String getLockflag() {
        return lockflag;
    }

    public void setLockflag(String lockflag) {
        this.lockflag = lockflag;
    }

    public String getPosseq() {
        return posseq;
    }

    public void setPosseq(String posseq) {
        this.posseq = posseq;
    }

    public String getCitycode() {
        return citycode;
    }

    public void setCitycode(String citycode) {
        this.citycode = citycode;
    }

    public String getCardno() {
        return cardno;
    }

    public void setCardno(String cardno) {
        this.cardno = cardno;
    }

    public String getCardkind() {
        return cardkind;
    }

    public void setCardkind(String cardkind) {
        this.cardkind = cardkind;
    }

    public String getChildcardkind() {
        return childcardkind;
    }

    public void setChildcardkind(String childcardkind) {
        this.childcardkind = childcardkind;
    }

    public String getCardvaliddate() {
        return cardvaliddate;
    }

    public void setCardvaliddate(String cardvaliddate) {
        this.cardvaliddate = cardvaliddate;
    }

    public String getEccpan() {
        return eccpan;
    }

    public void setEccpan(String eccpan) {
        this.eccpan = eccpan;
    }

    public String getEccpanseq() {
        return eccpanseq;
    }

    public void setEccpanseq(String eccpanseq) {
        this.eccpanseq = eccpanseq;
    }

    public String getEccdate() {
        return eccdate;
    }

    public void setEccdate(String eccdate) {
        this.eccdate = eccdate;
    }

    public String getEcciac() {
        return ecciac;
    }

    public void setEcciac(String ecciac) {
        this.ecciac = ecciac;
    }

    public String getEccamtcode() {
        return eccamtcode;
    }

    public void setEccamtcode(String eccamtcode) {
        this.eccamtcode = eccamtcode;
    }

    public String getEccappencmsg() {
        return eccappencmsg;
    }

    public void setEccappencmsg(String eccappencmsg) {
        this.eccappencmsg = eccappencmsg;
    }

    public String getEccappmsgdat() {
        return eccappmsgdat;
    }

    public void setEccappmsgdat(String eccappmsgdat) {
        this.eccappmsgdat = eccappmsgdat;
    }

    public String getEccissappdat() {
        return eccissappdat;
    }

    public void setEccissappdat(String eccissappdat) {
        this.eccissappdat = eccissappdat;
    }

    public String getEccrand() {
        return eccrand;
    }

    public void setEccrand(String eccrand) {
        this.eccrand = eccrand;
    }

    public String getEccatc() {
        return eccatc;
    }

    public void setEccatc(String eccatc) {
        this.eccatc = eccatc;
    }

    public String getEcctvr() {
        return ecctvr;
    }

    public void setEcctvr(String ecctvr) {
        this.ecctvr = ecctvr;
    }

    public String getEcctrantp() {
        return ecctrantp;
    }

    public void setEcctrantp(String ecctrantp) {
        this.ecctrantp = ecctrantp;
    }

    public String getEccaip() {
        return eccaip;
    }

    public void setEccaip(String eccaip) {
        this.eccaip = eccaip;
    }

    public String getEcctvm() {
        return ecctvm;
    }

    public void setEcctvm(String ecctvm) {
        this.ecctvm = ecctvm;
    }

    public String getEccgc() {
        return eccgc;
    }

    public void setEccgc(String eccgc) {
        this.eccgc = eccgc;
    }

    public String getBalbef() {
        return balbef;
    }

    public void setBalbef(String balbef) {
        this.balbef = balbef;
    }

    public String getTxnamt() {
        return txnamt;
    }

    public void setTxnamt(String txnamt) {
        this.txnamt = txnamt;
    }

    public String getTxndate() {
        return txndate;
    }

    public void setTxndate(String txndate) {
        this.txndate = txndate;
    }

    public String getTxntime() {
        return txntime;
    }

    public void setTxntime(String txntime) {
        this.txntime = txntime;
    }

    public String getTac() {
        return tac;
    }

    public void setTac(String tac) {
        this.tac = tac;
    }

    public String getCardverno() {
        return cardverno;
    }

    public void setCardverno(String cardverno) {
        this.cardverno = cardverno;
    }

    public String getPhytype() {
        return phytype;
    }

    public void setPhytype(String phytype) {
        this.phytype = phytype;
    }

    public String getOtheramt() {
        return otheramt;
    }

    public void setOtheramt(String otheramt) {
        this.otheramt = otheramt;
    }

    public String getAertcode() {
        return aertcode;
    }

    public void setAertcode(String aertcode) {
        this.aertcode = aertcode;
    }

    public String getCardtypetab() {
        return cardtypetab;
    }

    public void setCardtypetab(String cardtypetab) {
        this.cardtypetab = cardtypetab;
    }
}
