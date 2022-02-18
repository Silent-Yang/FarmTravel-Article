package com.farmTravelTag.model;

import com.core.connectionFactory.ConnectionFactory;

import java.sql.Connection;
import java.util.List;

public class FarmTravelTagService {
    private FarmTravelTagDAO dao;

    public FarmTravelTagService(){
        dao = new FarmTravelTagJDBCDAO();
    }

    ConnectionFactory connectionFactory = new ConnectionFactory();
    Connection con = connectionFactory.getConnection();

    public void addFarmTravelTag(String tag_name) {
        dao.add(con, tag_name);
    }

    public void updateFarmTravelTag(FarmTravelTagVO farm_travel_tag) {
        dao.update(con, farm_travel_tag);
    }

    public void deleteFarmTravelTag(Integer tag_ID) {
        dao.delete(con, tag_ID);
    }

    public FarmTravelTagVO getOneFarmTravelTag(String tag_name) {
        return dao.findByTagName(con, tag_name);
    }

    public FarmTravelTagVO getOneFarmTravelTag(Integer tag_ID) {
        return dao.findByPK(con, tag_ID);
    }

    public List<FarmTravelTagVO> getAllFarmTravelTag() {
        return dao.getAll(con);
    }
}
