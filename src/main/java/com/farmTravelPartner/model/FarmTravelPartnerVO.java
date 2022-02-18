package com.farmTravelPartner.model;

public class FarmTravelPartnerVO {

    private Integer partner_ID;
    private Integer order_ID;
    private String partner_name;
    private String partner_phone;
    private String guardian_name;
    private String guardian_phone;
    private Integer apply_mem_ID;

    public Integer getPartner_ID() {
        return partner_ID;
    }

    public void setPartner_ID(Integer partner_ID) {
        this.partner_ID = partner_ID;
    }

    public Integer getOrder_ID() {
        return order_ID;
    }

    public void setOrder_ID(Integer order_ID) {
        this.order_ID = order_ID;
    }

    public String getPartner_name() {
        return partner_name;
    }

    public void setPartner_name(String partner_name) {
        this.partner_name = partner_name;
    }

    public String getPartner_phone() {
        return partner_phone;
    }

    public void setPartner_phone(String partner_phone) {
        this.partner_phone = partner_phone;
    }

    public String getGuardian_name() {
        return guardian_name;
    }

    public void setGuardian_name(String guardian_name) {
        this.guardian_name = guardian_name;
    }

    public String getGuardian_phone() {
        return guardian_phone;
    }

    public void setGuardian_phone(String guardian_phone) {
        this.guardian_phone = guardian_phone;
    }

    public Integer getApply_mem_ID() {
        return apply_mem_ID;
    }

    public void setApply_mem_ID(Integer apply_mem_ID) {
        this.apply_mem_ID = apply_mem_ID;
    }
}
