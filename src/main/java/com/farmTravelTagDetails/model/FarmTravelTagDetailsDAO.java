package com.farmTravelTagDetails.model;

import java.sql.Connection;
import java.util.List;

public interface FarmTravelTagDetailsDAO {
    void add(Connection con, Integer farm_travel_ID, Integer tag_ID);
    void update(Connection con, FarmTravelTagDetailsVO farm_travel_tag_details);
    void delete(Connection con, Integer farm_travel_ID, Integer tag_ID);
    List<FarmTravelTagDetailsVO> getTagByFarmTravelID(Connection con, Integer farm_travel_ID);
    List<FarmTravelTagDetailsVO> getFarmTravelByTagID(Connection con, Integer tag_ID);
}
