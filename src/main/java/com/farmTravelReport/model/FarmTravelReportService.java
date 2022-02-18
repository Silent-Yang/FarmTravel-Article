package com.farmTravelReport.model;

import com.core.connectionFactory.ConnectionFactory;

import java.sql.Connection;
import java.util.List;

public class FarmTravelReportService {
    private FarmTravelReportDAO dao;

    public FarmTravelReportService(){
        dao = new FarmTravelReportJDBCDAO();
    }

    ConnectionFactory connectionFactory = new ConnectionFactory();
    Connection con = connectionFactory.getConnection();

    public FarmTravelReportVO addFarmTravelReport(Integer mem_ID, Integer farm_travel_ID, String report_reason) {

        FarmTravelReportVO farm_travel_report = new FarmTravelReportVO();
        farm_travel_report.setMem_ID(mem_ID);
        farm_travel_report.setFarm_travel_ID(farm_travel_ID);
        farm_travel_report.setReport_reason(report_reason);
        dao.add(con, farm_travel_report);

        return farm_travel_report;
    }

    public FarmTravelReportVO updateFarmTravelReport(Integer report_state, String report_note, Integer report_ID) {

        FarmTravelReportVO farm_travel_report = new FarmTravelReportVO();
        farm_travel_report.setReport_state(report_state);
        farm_travel_report.setReport_note(report_note);
        farm_travel_report.setReport_ID(report_ID);
        dao.update(con, farm_travel_report);

        return farm_travel_report;
    }

    public FarmTravelReportVO getOneFarmTravelReport(Integer report_ID) {
        return dao.findByPK(con, report_ID);
    }

    public List<FarmTravelReportVO> getAllFarmTravelReport() {
        return dao.getAll(con);
    }
}
