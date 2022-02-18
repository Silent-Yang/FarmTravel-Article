package com.farmTravelPartner.model;

import java.sql.Connection;
import java.util.List;

public interface FarmTravelPartnerDAO {
    void add(Connection con, FarmTravelPartnerVO farm_travel_partner);
    void update(Connection con, FarmTravelPartnerVO farm_travel_partner);
    void delete(Connection con, Integer partner_ID);
    FarmTravelPartnerVO findByPK(Connection con, Integer partner_ID);
    List<FarmTravelPartnerVO> getAll(Connection con, Integer order_ID);
}
