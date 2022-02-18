package com.farmTravelTagDetails.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class FarmTravelTagDetailsJDBCDAO implements FarmTravelTagDetailsDAO {

    public static final String INSERT_STMT = "INSERT INTO FARM_TRAVEL_TAG_DETAILS ( FARM_TRAVEL_ID, TAG_ID ) VALUES ( ?, ? );";
    public static final String DELETE_STMT = "DELETE FROM FARM_TRAVEL_TAG_DETAILS WHERE FARM_TRAVEL_ID = ? and TAG_ID = ?;";
    public static final String GET_BY_FARM_TRAVEL_STMT = "SELECT * FROM FARM_TRAVEL_TAG_DETAILS WHERE FARM_TRAVEL_ID = ?;";
    public static final String GET_BY_TAG_STMT = "SELECT * FROM FARM_TRAVEL_TAG_DETAILS WHERE TAG_ID = ?;";

    Connection con = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    @Override
    public void add(Connection con, Integer farm_travel_ID, Integer tag_ID) {

        this.con = con;

        try {
            pstmt = con.prepareStatement(INSERT_STMT);

            pstmt.setInt(1, farm_travel_ID);
            pstmt.setInt(2, tag_ID);

            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace(System.err);
        } finally {
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
    public void update(Connection con, FarmTravelTagDetailsVO farm_travel_tag_details) {

    }

    @Override
    public void delete(Connection con, Integer farm_travel_ID, Integer tag_ID) {

        this.con = con;

        try {
            pstmt = con.prepareStatement(DELETE_STMT);

            pstmt.setInt(1, farm_travel_ID);
            pstmt.setInt(2, tag_ID);

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
    public List<FarmTravelTagDetailsVO> getTagByFarmTravelID(Connection con, Integer farm_travel_ID) {

        this.con = con;
        List<FarmTravelTagDetailsVO> farm_travel_tag_details_list = new ArrayList<>();
        FarmTravelTagDetailsVO farm_travel_tag_details = null;
        try {
            pstmt = con.prepareStatement(GET_BY_FARM_TRAVEL_STMT);

            pstmt.setInt(1, farm_travel_ID);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                farm_travel_tag_details = new FarmTravelTagDetailsVO();
                farm_travel_tag_details.setFarm_travel_ID(rs.getInt("FARM_TRAVEL_ID"));
                farm_travel_tag_details.setTag_ID(rs.getInt("TAG_ID"));
                farm_travel_tag_details_list.add(farm_travel_tag_details);
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
        return farm_travel_tag_details_list;
    }

    @Override
    public List<FarmTravelTagDetailsVO> getFarmTravelByTagID(Connection con, Integer tag_ID) {

        this.con = con;
        List<FarmTravelTagDetailsVO> farm_travel_tag_details_list = new ArrayList<>();
        FarmTravelTagDetailsVO farm_travel_tag_details = null;
        try {
            pstmt = con.prepareStatement(GET_BY_TAG_STMT);

            pstmt.setInt(1, tag_ID);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                farm_travel_tag_details = new FarmTravelTagDetailsVO();
                farm_travel_tag_details.setFarm_travel_ID(rs.getInt("FARM_TRAVEL_ID"));
                farm_travel_tag_details.setTag_ID(rs.getInt("TAG_ID"));
                farm_travel_tag_details_list.add(farm_travel_tag_details);
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
        return farm_travel_tag_details_list;
    }
}
