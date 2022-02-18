package com.farmTravelTag.model;

import java.sql.Connection;
import java.util.List;

public interface FarmTravelTagDAO {
    void add(Connection con, String tag_name);
    void update(Connection con, FarmTravelTagVO farm_travel_tag);
    void delete(Connection con, Integer tag_ID);
    FarmTravelTagVO findByTagName(Connection con, String tag_name);
    FarmTravelTagVO findByPK(Connection con, Integer tag_ID);
    List<FarmTravelTagVO> getAll(Connection con);
}
