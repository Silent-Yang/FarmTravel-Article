package com.farmTravel.model;

import java.sql.Connection;
import java.util.List;

public interface FarmTravelDAO {
    Integer add(Connection con, FarmTravelVO farm_travel);
    void update(Connection con, FarmTravelVO farm_travel);
    void delete(Connection con, Integer farm_travel_ID);
    FarmTravelVO findByPK(Connection con, Integer farm_travel_ID);
    List<FarmTravelVO> getAll(Connection con);
    List<FarmTravelVO> getAllMemCanApply(Connection con);

}
