package com.farmTravelPartner.model;

import com.core.connectionFactory.ConnectionFactory;

import java.sql.Connection;
import java.util.List;

public class FarmTravelPartnerService {
    private FarmTravelPartnerDAO dao;

    public FarmTravelPartnerService(){
        dao = new FarmTravelPartnerJDBCDAO();
    }

    ConnectionFactory connectionFactory = new ConnectionFactory();
    Connection con = connectionFactory.getConnection();

    public FarmTravelPartnerVO addFarmTravelPartner(Integer order_ID, String partner_name, String partner_phone, String guardian_name, String guardian_phone, Integer apply_mem_ID) {

        FarmTravelPartnerVO farm_travel_partner = new FarmTravelPartnerVO();
        farm_travel_partner.setOrder_ID(order_ID);
        farm_travel_partner.setPartner_name(partner_name);
        farm_travel_partner.setPartner_phone(partner_phone);
        farm_travel_partner.setGuardian_name(guardian_name);
        farm_travel_partner.setGuardian_phone(guardian_phone);
        farm_travel_partner.setApply_mem_ID(apply_mem_ID);
        dao.add(con, farm_travel_partner);

        return farm_travel_partner;
    }

    public FarmTravelPartnerVO updateFarmTravelPartner(String partner_name, String partner_phone, String guardian_name, String guardian_phone, Integer partner_ID) {

        FarmTravelPartnerVO farm_travel_partner = new FarmTravelPartnerVO();
        farm_travel_partner.setPartner_name(partner_name);
        farm_travel_partner.setPartner_phone(partner_phone);
        farm_travel_partner.setGuardian_name(guardian_name);
        farm_travel_partner.setGuardian_phone(guardian_phone);
        farm_travel_partner.setPartner_ID(partner_ID);

        dao.update(con, farm_travel_partner);

        return farm_travel_partner;
    }

    public void deleteFarmTravelPartner(Integer partner_ID) {
        dao.delete(con, partner_ID);
    }

    public FarmTravelPartnerVO getOneFarmTravelPartner(Integer partner_ID) {
        return dao.findByPK(con, partner_ID);
    }

    public List<FarmTravelPartnerVO> getAllFarmTravelPartner(Integer order_ID) {
        return dao.getAll(con, order_ID);
    }
}
