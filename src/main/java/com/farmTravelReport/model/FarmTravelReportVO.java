package com.farmTravelReport.model;

import java.sql.Timestamp;

public class FarmTravelReportVO {

    private Integer report_ID;
    private Integer mem_ID;
    private Integer farm_travel_ID;
    private String report_reason;
    private Timestamp report_time;
    private Integer report_state;
    private String report_note;
    private Timestamp review_time;

    public Integer getReport_ID() {
        return report_ID;
    }

    public void setReport_ID(Integer report_ID) {
        this.report_ID = report_ID;
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

    public String getReport_reason() {
        return report_reason;
    }

    public void setReport_reason(String report_reason) {
        this.report_reason = report_reason;
    }

    public Timestamp getReport_time() {
        return report_time;
    }

    public void setReport_time(Timestamp report_time) {
        this.report_time = report_time;
    }

    public Integer getReport_state() {
        return report_state;
    }

    public void setReport_state(Integer report_state) {
        this.report_state = report_state;
    }

    public String getReport_note() {
        return report_note;
    }

    public void setReport_note(String report_note) {
        this.report_note = report_note;
    }

    public Timestamp getReview_time() {
        return review_time;
    }

    public void setReview_time(Timestamp review_time) {
        this.review_time = review_time;
    }
}
