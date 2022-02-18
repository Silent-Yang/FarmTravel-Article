package com.farmTravelCollection.model;

import java.sql.Timestamp;

public class FarmTravelCollectionVO {

    private Integer mem_ID;
    private Integer farm_travel_ID;
    private Timestamp collection_time;

    public Integer getMem_ID() {
        return mem_ID;
    }

    public void setMem_ID(Integer mem_ID) {
        this.mem_ID = mem_ID;
    }

    public Integer getFarm_travel_ID() {
        return farm_travel_ID;
    }

    public void setFarm_travel_ID(Integer farm_travel_ID) {
        this.farm_travel_ID = farm_travel_ID;
    }

    public Timestamp getCollection_time() {
        return collection_time;
    }

    public void setCollection_time(Timestamp collection_time) {
        this.collection_time = collection_time;
    }
}
