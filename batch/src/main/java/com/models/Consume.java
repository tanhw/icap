package com.models;

import java.util.List;

/**
 * Created by 西 on 2015/7/22.
 * 消费交易
 */
public class Consume {

    //版本号
    private int version;

    //交易类型
    private String txnsubtype;

    //记录总数
    private Integer recnum;

    //运营单位代码
    private String corpid;

    //单笔交易长度
    private Integer reclens;

    private List<ConsumeBody> body;

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getTxnsubtype() {
        return txnsubtype;
    }

    public void setTxnsubtype(String txnsubtype) {
        this.txnsubtype = txnsubtype;
    }

    public Integer getRecnum() {
        return recnum;
    }

    public void setRecnum(Integer recnum) {
        this.recnum = recnum;
    }

    public String getCorpid() {
        return corpid;
    }

    public void setCorpid(String corpid) {
        this.corpid = corpid;
    }

    public Integer getReclens() {
        return reclens;
    }

    public void setReclens(Integer reclens) {
        this.reclens = reclens;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public List<ConsumeBody> getBody() {
        return body;
    }

    public void setBody(List<ConsumeBody> body) {
        this.body = body;
    }
}
