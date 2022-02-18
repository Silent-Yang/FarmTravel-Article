package com.farmTravelCollection.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FarmTravelCollectionJDBCDAO implements FarmTravelCollectionDAO{

    public static final String INSERT_STMT = "INSERT INTO FARM_TRAVEL_COLLECTION ( MEM_ID, FARM_TRAVEL_ID, COLLECTION_TIME ) VALUES ( ?, ?, NOW() );";
    public static final String DELETE_STMT = "DELETE FROM FARM_TRAVEL_COLLECTION WHERE MEM_ID = ? AND FARM_TRAVEL_ID = ?;";
    public static final String GET_ONE_STMT = "SELECT * FROM FARM_TRAVEL_COLLECTION WHERE MEM_ID = ? AND FARM_TRAVEL_ID = ?;";
    public static final String GET_ALL_STMT = "SELECT * FROM FARM_TRAVEL_COLLECTION WHERE MEM_ID = ?;";

    Connection con = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    @Override
    public void add(Connection con, FarmTravelCollectionVO farm_travel_collection) {

        this.con = con;

        try {
            pstmt = con.prepareStatement(INSERT_STMT);

            pstmt.setInt(1, farm_travel_collection.getMem_ID());
            pstmt.setInt(2, farm_travel_collection.getFarm_travel_ID());

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
    public void delete(Connection con, Integer mem_ID, Integer farm_travel_ID) {

        this.con = con;

        try {

            pstmt = con.prepareStatement(DELETE_STMT);

            pstmt.setInt(1, mem_ID);
            pstmt.setInt(2, farm_travel_ID);

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
    public boolean findByPK(Connection con, Integer mem_ID, Integer farm_travel_ID) {

        this.con = con;
        FarmTravelCollectionVO farm_travel_collection = null;
        try {
            pstmt = con.prepareStatement(GET_ONE_STMT);

            pstmt.setInt(1, mem_ID);
            pstmt.setInt(2, farm_travel_ID);

            rs = pstmt.executeQuery();

            if (rs.next()) {
                return true;
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
        return false;
    }

    @Override
    public List<FarmTravelCollectionVO> getAll(Connection con, Integer mem_ID) {

        this.con = con;
        List<FarmTravelCollectionVO> farm_travel_collection_list = new ArrayList<>();
        FarmTravelCollectionVO farm_travel_collection = null;
        try {
            pstmt = con.prepareStatement(GET_ALL_STMT);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                farm_travel_collection = new FarmTravelCollectionVO();
                farm_travel_collection.setMem_ID(rs.getInt("MEM_ID"));
                farm_travel_collection.setFarm_travel_ID(rs.getInt("FARM_TRAVEL_ID"));
                farm_travel_collection.setCollection_time(rs.getTimestamp("COLLECTION_TIME"));

                farm_travel_collection_list.add(farm_travel_collection);
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
        return farm_travel_collection_list;
    }
}
