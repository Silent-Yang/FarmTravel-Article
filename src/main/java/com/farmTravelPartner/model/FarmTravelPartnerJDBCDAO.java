package com.farmTravelPartner.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FarmTravelPartnerJDBCDAO implements FarmTravelPartnerDAO{

    public static final String INSERT_STMT = "INSERT INTO FARM_TRAVEL_PARTNER ( ORDER_ID, PARTNER_NAME, PARTNER_PHONE, GUARDIAN_NAME, GUARDIAN_PHONE, APPLY_MEM_ID ) VALUES ( ?, ?, ?, ?, ?, ? );";
    public static final String UPDATE_STMT = "UPDATE FARM_TRAVEL_PARTNER SET PARTNER_NAME = ?, PARTNER_PHONE = ?, GUARDIAN_NAME = ?, GUARDIAN_PHONE = ? WHERE PARTNER_ID = ?;";
    public static final String DELETE_STMT = "DELETE FROM FARM_TRAVEL_PARTNER WHERE PARTNER_ID = ?;";
    public static final String GET_ONE_STMT = "SELECT * FROM FARM_TRAVEL_PARTNER WHERE PARTNER_ID = ?;";
    public static final String GET_ALL_STMT = "SELECT * FROM FARM_TRAVEL_PARTNER WHERE ORDER_ID = ?;";

    Connection con = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    @Override
    public void add(Connection con, FarmTravelPartnerVO farm_travel_partner) {

        this.con = con;

        try {
            pstmt = con.prepareStatement(INSERT_STMT);

            pstmt.setInt(1, farm_travel_partner.getOrder_ID());
            pstmt.setString(2, farm_travel_partner.getPartner_name());
            pstmt.setString(3, farm_travel_partner.getPartner_phone());
            pstmt.setString(4, farm_travel_partner.getGuardian_name());
            pstmt.setString(5, farm_travel_partner.getGuardian_phone());
            pstmt.setInt(6, farm_travel_partner.getApply_mem_ID());

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
    public void update(Connection con, FarmTravelPartnerVO farm_travel_partner) {

        this.con = con;

        try {
            pstmt = con.prepareStatement(UPDATE_STMT);

            pstmt.setString(1, farm_travel_partner.getPartner_name());
            pstmt.setString(2, farm_travel_partner.getPartner_phone());
            pstmt.setString(3, farm_travel_partner.getGuardian_name());
            pstmt.setString(4, farm_travel_partner.getGuardian_phone());
            pstmt.setInt(5, farm_travel_partner.getPartner_ID());

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
    public void delete(Connection con, Integer partner_id) {

        this.con = con;

        try {
            pstmt = con.prepareStatement(DELETE_STMT);

            pstmt.setInt(1, partner_id);

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
    public FarmTravelPartnerVO findByPK(Connection con, Integer partner_ID) {

        this.con = con;
        FarmTravelPartnerVO farm_travel_partner = null;
        try {
            pstmt = con.prepareStatement(GET_ONE_STMT);

            pstmt.setInt(1, partner_ID);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                farm_travel_partner = new FarmTravelPartnerVO();
                farm_travel_partner.setPartner_ID(rs.getInt("PARTNER_ID"));
                farm_travel_partner.setOrder_ID(rs.getInt("ORDER_ID"));
                farm_travel_partner.setPartner_name(rs.getString("PARTNER_NAME"));
                farm_travel_partner.setPartner_phone(rs.getString("PARTNER_PHONE"));
                farm_travel_partner.setGuardian_name(rs.getString("GUARDIAN_NAME"));
                farm_travel_partner.setGuardian_phone(rs.getString("GUARDIAN_PHONE"));
                farm_travel_partner.setApply_mem_ID(rs.getInt("APPLY_MEM_ID"));
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
        return farm_travel_partner;
    }

    @Override
    public List<FarmTravelPartnerVO> getAll(Connection con, Integer order_ID) {

        this.con = con;
        List<FarmTravelPartnerVO> farm_travel_partner_list = new ArrayList<>();
        FarmTravelPartnerVO farm_travel_partner = null;
        try {
            pstmt = con.prepareStatement(GET_ALL_STMT);

            pstmt.setInt(1, order_ID);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                farm_travel_partner = new FarmTravelPartnerVO();
                farm_travel_partner.setPartner_ID(rs.getInt("PARTNER_ID"));
                farm_travel_partner.setOrder_ID(rs.getInt("ORDER_ID"));
                farm_travel_partner.setPartner_name(rs.getString("PARTNER_NAME"));
                farm_travel_partner.setPartner_phone(rs.getString("PARTNER_PHONE"));
                farm_travel_partner.setGuardian_name(rs.getString("GUARDIAN_NAME"));
                farm_travel_partner.setGuardian_phone(rs.getString("GUARDIAN_PHONE"));
                farm_travel_partner.setApply_mem_ID(rs.getInt("APPLY_MEM_ID"));
                farm_travel_partner_list.add(farm_travel_partner);
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
        return farm_travel_partner_list;
    }
}
