package com.farmTravelCollection.model;

import java.sql.Connection;
import java.util.List;

public interface FarmTravelCollectionDAO {
    void add(Connection con, FarmTravelCollectionVO farm_travel_collection);
//    void update(FarmTravelCollectionVO farm_travel_collection);
    void delete(Connection con, Integer mem_ID, Integer farm_travel_ID);
    boolean findByPK(Connection con, Integer mem_ID, Integer farm_travel_ID);
    List<FarmTravelCollectionVO> getAll(Connection con, Integer mem_ID);
}
