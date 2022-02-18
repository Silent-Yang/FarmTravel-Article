package com.farmTravelTagDetails.model;

import com.core.connectionFactory.ConnectionFactory;

import java.sql.Connection;
import java.util.List;

public class FarmTravelTagDetailsService {
    private FarmTravelTagDetailsDAO dao;

    public FarmTravelTagDetailsService(){
        dao = new FarmTravelTagDetailsJDBCDAO();
    }

    ConnectionFactory connectionFactory = new ConnectionFactory();
    Connection con = connectionFactory.getConnection();

    public void addFarmTravelTagDetails(Integer farm_travel_ID, Integer tag_ID) {
        dao.add(con, farm_travel_ID, tag_ID);
    }

    public void deleteFarmTravelTagDetails(Integer farm_travel_ID, Integer tag_ID) {
        dao.delete(con, farm_travel_ID, tag_ID);
    }

    public List<FarmTravelTagDetailsVO> getTagByFarmTravelID(Integer farm_travel_ID) {
        return dao.getTagByFarmTravelID(con, farm_travel_ID);
    }
    public List<FarmTravelTagDetailsVO> getFarmTravelByTagID(Integer tag_ID) {
        return dao.getFarmTravelByTagID(con, tag_ID);
    }
}
