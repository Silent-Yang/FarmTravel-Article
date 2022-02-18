package com.farmTravelCollection.model;

import com.core.connectionFactory.ConnectionFactory;

import java.sql.Connection;
import java.util.List;

public class FarmTravelCollectionService {
    private FarmTravelCollectionDAO dao;

    public FarmTravelCollectionService(){
        dao = new FarmTravelCollectionJDBCDAO();
    }

    ConnectionFactory connectionFactory = new ConnectionFactory();
    Connection con = connectionFactory.getConnection();

    public void addFarmTravelCollection(Integer mem_ID, Integer farm_travel_ID) {
        FarmTravelCollectionVO farm_travel_collection = new FarmTravelCollectionVO();
        farm_travel_collection.setMem_ID(mem_ID);
        farm_travel_collection.setFarm_travel_ID(farm_travel_ID);
        dao.add(con, farm_travel_collection);
    }

    public void deleteFarmTravelCollection(Integer mem_ID, Integer farm_travel_ID) { dao.delete(con, mem_ID, farm_travel_ID); }

    public boolean getOneFarmTravelCollection(Integer mem_ID, Integer farm_travel_ID) { return dao.findByPK(con, mem_ID, farm_travel_ID); }

    public List<FarmTravelCollectionVO> getAllFarmTravelCollection(Integer mem_ID) {
        return dao.getAll(con, mem_ID);
    }
}
