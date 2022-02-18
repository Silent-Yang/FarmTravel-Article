package com.farmTravelOrder.model;

import java.sql.Connection;
import java.util.List;

public interface FarmTravelOrderDAO {
    Integer add(Connection con, FarmTravelOrderVO farm_travel_order);
    void update(Connection con, FarmTravelOrderVO farm_travel_order);
    FarmTravelOrderVO findByPK(Connection con, Integer order_ID);
    List<FarmTravelOrderVO> getAll(Connection con, Integer mem_ID);
}
