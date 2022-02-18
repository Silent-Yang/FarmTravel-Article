package com.farmTravelOrder.model;

import java.sql.Timestamp;

public class FarmTravelOrderVO {

    private Integer order_ID;
    private Integer mem_ID;
    private Integer farm_travel_ID;
    private Integer f_mem_ID;
    private Timestamp order_time;
    private Integer people_num;
    private Integer order_fee;
    private Timestamp farm_travel_start;
    private Timestamp farm_travel_end;
    private Integer order_state;
    private Integer order_payment;
    private Timestamp refund_time;;
    private Integer farm_travel_stars;
    private Integer mem_ID_stars;
    private String order_memo;

    public Integer getOrder_ID() {
        return order_ID;
    }

    public void setOrder_ID(Integer order_ID) {
        this.order_ID = order_ID;
    }

    public Integer getMem_ID() {
        return mem_ID;
    }

    public void setMem_ID(Integer mem_ID) {
        this.mem_ID = mem_ID;
    }

    public Integer getFarm_travel_ID() {
        return farm_travel_ID;
    }

    public void setFarm_travel_ID(Integer farm_travel_ID) {
        this.farm_travel_ID = farm_travel_ID;
    }

    public Integer getF_mem_ID() {
        return f_mem_ID;
    }

    public void setF_mem_ID(Integer f_mem_ID) {
        this.f_mem_ID = f_mem_ID;
    }

    public Timestamp getOrder_time() {
        return order_time;
    }

    public void setOrder_time(Timestamp order_time) {
        this.order_time = order_time;
    }

    public Integer getPeople_num() {
        return people_num;
    }

    public void setPeople_num(Integer people_num) {
        this.people_num = people_num;
    }

    public Integer getOrder_fee() {
        return order_fee;
    }

    public void setOrder_fee(Integer order_fee) {
        this.order_fee = order_fee;
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

    public Integer getOrder_state() {
        return order_state;
    }

    public void setOrder_state(Integer order_state) {
        this.order_state = order_state;
    }

    public Integer getOrder_payment() {
        return order_payment;
    }

    public void setOrder_payment(Integer order_payment) {
        this.order_payment = order_payment;
    }

    public Timestamp getRefund_time() {
        return refund_time;
    }

    public void setRefund_time(Timestamp refund_time) {
        this.refund_time = refund_time;
    }

    public Integer getFarm_travel_stars() {
        return farm_travel_stars;
    }

    public void setFarm_travel_stars(Integer farm_travel_stars) {
        this.farm_travel_stars = farm_travel_stars;
    }

    public Integer getMem_ID_stars() {
        return mem_ID_stars;
    }

    public void setMem_ID_stars(Integer mem_ID_stars) {
        this.mem_ID_stars = mem_ID_stars;
    }

    public String getOrder_memo() {
        return order_memo;
    }

    public void setOrder_memo(String order_memo) {
        this.order_memo = order_memo;
    }
}
