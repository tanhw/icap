package com.core.models;

import java.io.Serializable;

public class TPosFac implements Serializable {
    private Long factoryid;

    private String faccode;

    private String facname;

    private String facaddr;

    private String faccontact;

    private String factele;

    private String facmail;

    private String facfax;

    private String facdesc;

    private String facprodesc;

    private static final long serialVersionUID = 1L;

    public Long getFactoryid() {
        return factoryid;
    }

    public void setFactoryid(Long factoryid) {
        this.factoryid = factoryid;
    }

    public String getFaccode() {
        return faccode;
    }

    public void setFaccode(String faccode) {
        this.faccode = faccode;
    }

    public String getFacname() {
        return facname;
    }

    public void setFacname(String facname) {
        this.facname = facname;
    }

    public String getFacaddr() {
        return facaddr;
    }

    public void setFacaddr(String facaddr) {
        this.facaddr = facaddr;
    }

    public String getFaccontact() {
        return faccontact;
    }

    public void setFaccontact(String faccontact) {
        this.faccontact = faccontact;
    }

    public String getFactele() {
        return factele;
    }

    public void setFactele(String factele) {
        this.factele = factele;
    }

    public String getFacmail() {
        return facmail;
    }

    public void setFacmail(String facmail) {
        this.facmail = facmail;
    }

    public String getFacfax() {
        return facfax;
    }

    public void setFacfax(String facfax) {
        this.facfax = facfax;
    }

    public String getFacdesc() {
        return facdesc;
    }

    public void setFacdesc(String facdesc) {
        this.facdesc = facdesc;
    }

    public String getFacprodesc() {
        return facprodesc;
    }

    public void setFacprodesc(String facprodesc) {
        this.facprodesc = facprodesc;
    }

}