package com.mem.model;

import java.util.*;

public interface MemDAO_interface {
          public void insert(MemVO memVO);
          public void update(MemVO memVO);
          public void updateMemInput(MemVO memVO);
          public void updateAccState(MemVO memVO);
          public void delete(Integer mem_id);
          public MemVO findByPrimaryKey(Integer mem_id);
          public List<MemVO> getAll();
          //萬用複合查詢(傳入參數型態Map)(回傳 List)
        public List<MemVO> getAll(Map<String, String[]> map); 
}