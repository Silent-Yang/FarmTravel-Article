package com.farmTravelReport.model;

import java.sql.Connection;
import java.util.List;

public interface FarmTravelReportDAO {
    void add(Connection con, FarmTravelReportVO farm_travel_report);
    void update(Connection con, FarmTravelReportVO farm_travel_report);
//    void delete(Integer report_ID);
    FarmTravelReportVO findByPK(Connection con, Integer report_ID);
    List<FarmTravelReportVO> getAll(Connection con);
}
