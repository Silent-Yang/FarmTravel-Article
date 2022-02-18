package com.farmTravel.model;

import java.sql.Timestamp;

public class FarmTravelVO {

    private Integer farm_travel_ID;
    private Integer mem_ID;
    private Integer f_mem_ID;
    private String farm_travel_title;
    private byte[] farm_travel_img;
    private String farm_travel_info;
    private Timestamp farm_travel_start;
    private Timestamp farm_travel_end;
    private Integer farm_travel_fee;
    private Timestamp travel_apply_start;
    private Timestamp travel_apply_end;
    private Integer farm_travel_min;
    private Integer farm_travel_max;
    private Integer farm_travel_now;
    private Integer farm_travel_state;
    
    public Integer getFarm_travel_ID() {
        return farm_travel_ID;
    }

    public void setFarm_travel_ID(Integer farm_travel_ID) {
        this.farm_travel_ID = farm_travel_ID;
    }

    public Integer getMem_ID() {
        return mem_ID;
    }

    public void setMem_ID(Integer mem_ID) {
        this.mem_ID = mem_ID;
    }

    public Integer getF_mem_ID() {
        return f_mem_ID;
    }

    public void setF_mem_ID(Integer f_mem_ID) {
        this.f_mem_ID = f_mem_ID;
    }

    public String getFarm_travel_title() {
        return farm_travel_title;
    }

    public void setFarm_travel_title(String farm_travel_title) {
        this.farm_travel_title = farm_travel_title;
    }

    public byte[] getFarm_travel_img() {
        return farm_travel_img;
    }

    public void setFarm_travel_img(byte[] farm_travel_img) {
        this.farm_travel_img = farm_travel_img;
    }

    public String getFarm_travel_info() {
        return farm_travel_info;
    }

    public void setFarm_travel_info(String farm_travel_info) {
        this.farm_travel_info = farm_travel_info;
    }

    public Timestamp getFarm_travel_start() {
        return farm_travel_start;
    }

    public void setFarm_travel_start(Timestamp farm_travel_start) {
        this.farm_travel_start = farm_travel_start;
    }

    public Timestamp getFarm_travel_end() {
        return farm_travel_end;
    }

    public void setFarm_travel_end(Timestamp farm_travel_end) {
        this.farm_travel_end = farm_travel_end;
    }

    public Integer getFarm_travel_fee() {
        return farm_travel_fee;
    }

    public void setFarm_travel_fee(Integer farm_travel_fee) {
        this.farm_travel_fee = farm_travel_fee;
    }

    public Timestamp getTravel_apply_start() {
        return travel_apply_start;
    }

    public void setTravel_apply_start(Timestamp travel_apply_start) {
        this.travel_apply_start = travel_apply_start;
    }

    public Timestamp getTravel_apply_end() {
        return travel_apply_end;
    }

    public void setTravel_apply_end(Timestamp travel_apply_end) {
        this.travel_apply_end = travel_apply_end;
    }

    public Integer getFarm_travel_min() {
        return farm_travel_min;
    }

    public void setFarm_travel_min(Integer farm_travel_min) {
        this.farm_travel_min = farm_travel_min;
    }

    public Integer getFarm_travel_max() {
        return farm_travel_max;
    }

    public void setFarm_travel_max(Integer farm_travel_max) {
        this.farm_travel_max = farm_travel_max;
    }

    public Integer getFarm_travel_now() {
        return farm_travel_now;
    }

    public void setFarm_travel_now(Integer farm_travel_now) {
        this.farm_travel_now = farm_travel_now;
    }

    public Integer getFarm_travel_state() {
        return farm_travel_state;
    }

    public void setFarm_travel_state(Integer farm_travel_state) {
        this.farm_travel_state = farm_travel_state;
    }
}
