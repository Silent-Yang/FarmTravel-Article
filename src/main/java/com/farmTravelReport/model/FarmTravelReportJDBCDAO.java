package com.farmTravelReport.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FarmTravelReportJDBCDAO implements FarmTravelReportDAO{

    public static final String INSERT_STMT = "INSERT INTO FARM_TRAVEL_REPORT ( MEM_ID, FARM_TRAVEL_ID, REPORT_REASON, REPORT_TIME, REPORT_STATE ) VALUES ( ?, ?, ?, NOW(), '0' );";
    public static final String UPDATE_STMT = "UPDATE FARM_TRAVEL_REPORT SET REPORT_STATE = ?, REPORT_NOTE = ? , REVIEW_TIME = NOW() WHERE REPORT_ID = ?;";
    public static final String GET_ONE_STMT = "SELECT * FROM FARM_TRAVEL_REPORT WHERE REPORT_ID = ?;";
    public static final String GET_ALL_STMT = "SELECT * FROM FARM_TRAVEL_REPORT;";

    Connection con = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    @Override
    public void add(Connection con, FarmTravelReportVO farm_travel_report) {

        this.con = con;

        try {
            pstmt = con.prepareStatement(INSERT_STMT);

            pstmt.setInt(1, farm_travel_report.getMem_ID());
            pstmt.setInt(2, farm_travel_report.getFarm_travel_ID());
            pstmt.setString(3, farm_travel_report.getReport_reason());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace(System.err);
        }finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace(System.err);
                }
            }
        }
    }

    @Override
    public void update(Connection con, FarmTravelReportVO farm_travel_report) {

        this.con = con;

        try {
            pstmt = con.prepareStatement(UPDATE_STMT);

            pstmt.setInt(1,farm_travel_report.getReport_state());
            pstmt.setString(2, farm_travel_report.getReport_note());
            pstmt.setInt(3,farm_travel_report.getReport_ID());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace(System.err);
        }finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace(System.err);
                }
            }
        }
    }

    @Override
    public FarmTravelReportVO findByPK(Connection con, Integer report_ID) {
        FarmTravelReportVO farm_travel_report = null;

        this.con = con;

        try {
            pstmt = con.prepareStatement(GET_ONE_STMT);

            pstmt.setInt(1, report_ID);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                farm_travel_report = new FarmTravelReportVO();
                farm_travel_report.setReport_ID(rs.getInt("REPORT_ID"));
                farm_travel_report.setMem_ID(rs.getInt("MEM_ID"));
                farm_travel_report.setFarm_travel_ID(rs.getInt("FARM_TRAVEL_ID"));
                farm_travel_report.setReport_reason(rs.getString("REPORT_REASON"));
                farm_travel_report.setReport_time(rs.getTimestamp("REPORT_TIME"));
                farm_travel_report.setReport_state(rs.getInt("REPORT_STATE"));
                farm_travel_report.setReport_note(rs.getString("REPORT_NOTE"));
                farm_travel_report.setReview_time(rs.getTimestamp("REVIEW_TIME"));
            }
        } catch (SQLException e) {
            e.printStackTrace(System.err);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace(System.err);
                }
            }
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace(System.err);
                }
            }
        }
        return farm_travel_report;
    }

    @Override
    public List<FarmTravelReportVO> getAll(Connection con) {

        this.con = con;
        List<FarmTravelReportVO> farm_travel_report_list = new ArrayList<>();
        FarmTravelReportVO farm_travel_report = null;
        try {
            pstmt = con.prepareStatement(GET_ALL_STMT);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                farm_travel_report = new FarmTravelReportVO();
                farm_travel_report.setReport_ID(rs.getInt("REPORT_ID"));
                farm_travel_report.setMem_ID(rs.getInt("MEM_ID"));
                farm_travel_report.setFarm_travel_ID(rs.getInt("FARM_TRAVEL_ID"));
                farm_travel_report.setReport_reason(rs.getString("REPORT_REASON"));
                farm_travel_report.setReport_time(rs.getTimestamp("REPORT_TIME"));
                farm_travel_report.setReport_state(rs.getInt("REPORT_STATE"));
                farm_travel_report.setReport_note(rs.getString("REPORT_NOTE"));
                farm_travel_report.setReview_time(rs.getTimestamp("REVIEW_TIME"));
                farm_travel_report_list.add(farm_travel_report);
            }
        } catch (SQLException e) {
            e.printStackTrace(System.err);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace(System.err);
                }
            }
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace(System.err);
                }
            }
        }
        return farm_travel_report_list;
    }
}
