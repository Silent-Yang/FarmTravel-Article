package com.farmTravelOrder.model;

import com.farmTravelPartner.model.FarmTravelPartnerDAO;
import com.farmTravelPartner.model.FarmTravelPartnerJDBCDAO;
import com.farmTravelPartner.model.FarmTravelPartnerService;
import com.farmTravelPartner.model.FarmTravelPartnerVO;
import com.farmTravelTag.model.FarmTravelTagJDBCDAO;
import com.farmTravelTag.model.FarmTravelTagVO;
import com.farmTravelTagDetails.model.FarmTravelTagDetailsJDBCDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FarmTravelOrderJDBCDAO implements FarmTravelOrderDAO{

    public static final String INSERT_STMT = "INSERT INTO FARM_TRAVEL_ORDER ( MEM_ID, FARM_TRAVEL_ID, F_MEM_ID, ORDER_TIME, PEOPLE_NUM, ORDER_FEE, FARM_TRAVEL_START, FARM_TRAVEL_END, ORDER_STATE, ORDER_PAYMENT, ORDER_MEMO ) VALUES ( ?, ?, ?, NOW(), ?, ?, ?, ?, '0', '0', ? );";
    public static final String UPDATE_STMT = "UPDATE FARM_TRAVEL_ORDER SET ORDER_STATE = ?, ORDER_PAYMENT = ?, REFUND_TIME = ?, FARM_TRAVEL_STARS = ?, MEM_ID_STARS = ?, ORDER_MEMO = ? WHERE ORDER_ID = ?;";
    public static final String GET_ONE_STMT = "SELECT * FROM FARM_TRAVEL_ORDER WHERE ORDER_ID = ?;";
    public static final String GET_ALL_STMT = "SELECT * FROM FARM_TRAVEL_ORDER WHERE MEM_ID = ?;";

    Connection con = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    @Override
    public Integer add(Connection con, FarmTravelOrderVO farm_travel_order) {

        this.con = con;

        Integer next_farm_travel_order_ID = null;
        try {
            con.setAutoCommit(false);

            pstmt = con.prepareStatement(INSERT_STMT, Statement.RETURN_GENERATED_KEYS);

            pstmt.setInt(1, farm_travel_order.getMem_ID());
            pstmt.setInt(2, farm_travel_order.getFarm_travel_ID());
            pstmt.setInt(3, farm_travel_order.getF_mem_ID());
            pstmt.setInt(4, farm_travel_order.getPeople_num());
            pstmt.setInt(5, farm_travel_order.getOrder_fee());
            pstmt.setTimestamp(6, farm_travel_order.getFarm_travel_start());
            pstmt.setTimestamp(7, farm_travel_order.getFarm_travel_end());
            pstmt.setString(8, farm_travel_order.getOrder_memo());

            pstmt.executeUpdate();

            rs = pstmt.getGeneratedKeys();

            if (rs.next()) {
                next_farm_travel_order_ID = rs.getInt(1);
            }
            rs.close();
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
        return next_farm_travel_order_ID;
    }

    @Override
    public void update(Connection con, FarmTravelOrderVO farm_travel_order) {

        this.con = con;

        try {
            pstmt = con.prepareStatement(UPDATE_STMT);

            pstmt.setInt(1, farm_travel_order.getOrder_state());
            pstmt.setInt(2, farm_travel_order.getOrder_payment());
            pstmt.setTimestamp(3, farm_travel_order.getRefund_time());
            pstmt.setInt(4, farm_travel_order.getFarm_travel_stars());
            pstmt.setInt(5, farm_travel_order.getMem_ID_stars());
            pstmt.setString(6, farm_travel_order.getOrder_memo());
            pstmt.setInt(7, farm_travel_order.getFarm_travel_ID());

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
    public FarmTravelOrderVO findByPK(Connection con, Integer order_ID) {

        this.con = con;
        FarmTravelOrderVO farm_travel_order = null;
        try {
            pstmt = con.prepareStatement(GET_ONE_STMT);

            pstmt.setInt(1, order_ID);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                farm_travel_order = new FarmTravelOrderVO();
                farm_travel_order.setOrder_ID(rs.getInt("ORDER_ID"));
                farm_travel_order.setMem_ID(rs.getInt("MEM_ID"));
                farm_travel_order.setFarm_travel_ID(rs.getInt("FARM_TRAVEL_ID"));
                farm_travel_order.setF_mem_ID(rs.getInt("F_MEM_ID"));
                farm_travel_order.setOrder_time(rs.getTimestamp("ORDER_TIME"));
                farm_travel_order.setPeople_num(rs.getInt("PEOPLE_NUM"));
                farm_travel_order.setOrder_fee(rs.getInt("ORDER_FEE"));
                farm_travel_order.setFarm_travel_start(rs.getTimestamp("FARM_TRAVEL_START"));
                farm_travel_order.setFarm_travel_end(rs.getTimestamp("FARM_TRAVEL_END"));
                farm_travel_order.setOrder_state(rs.getInt("ORDER_STATE"));
                farm_travel_order.setOrder_payment(rs.getInt("ORDER_PAYMENT"));
                farm_travel_order.setRefund_time(rs.getTimestamp("REFUND_TIME"));
                farm_travel_order.setFarm_travel_stars(rs.getInt("FARM_TRAVEL_STARS"));
                farm_travel_order.setMem_ID_stars(rs.getInt("MEM_ID_STARS"));
                farm_travel_order.setOrder_memo(rs.getString("ORDER_MEMO"));
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
        return farm_travel_order;
    }

    @Override
    public List<FarmTravelOrderVO> getAll(Connection con, Integer mem_ID) {

        this.con = con;
        List<FarmTravelOrderVO> farm_travel_order_list = new ArrayList<>();
        FarmTravelOrderVO farm_travel_order = null;
        try {
            pstmt = con.prepareStatement(GET_ALL_STMT);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                farm_travel_order = new FarmTravelOrderVO();
                farm_travel_order.setOrder_ID(rs.getInt("ORDER_ID"));
                farm_travel_order.setMem_ID(rs.getInt("MEM_ID"));
                farm_travel_order.setFarm_travel_ID(rs.getInt("FARM_TRAVEL_ID"));
                farm_travel_order.setF_mem_ID(rs.getInt("F_MEM_ID"));
                farm_travel_order.setOrder_time(rs.getTimestamp("ORDER_TIME"));
                farm_travel_order.setPeople_num(rs.getInt("PEOPLE_NUM"));
                farm_travel_order.setOrder_fee(rs.getInt("ORDER_FEE"));
                farm_travel_order.setFarm_travel_start(rs.getTimestamp("FARM_TRAVEL_START"));
                farm_travel_order.setFarm_travel_end(rs.getTimestamp("FARM_TRAVEL_END"));
                farm_travel_order.setOrder_state(rs.getInt("ORDER_STATE"));
                farm_travel_order.setOrder_payment(rs.getInt("ORDER_PAYMENT"));
                farm_travel_order.setRefund_time(rs.getTimestamp("REFUND_TIME"));
                farm_travel_order.setFarm_travel_stars(rs.getInt("FARM_TRAVEL_STARS"));
                farm_travel_order.setMem_ID_stars(rs.getInt("MEM_ID_STARS"));
                farm_travel_order.setOrder_memo(rs.getString("ORDER_MEMO"));
                farm_travel_order_list.add(farm_travel_order);
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
        return farm_travel_order_list;
    }
}
