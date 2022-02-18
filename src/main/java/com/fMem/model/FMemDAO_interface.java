package com.fMem.model;

import java.util.*;

public interface FMemDAO_interface {
          public void insert(FMemVO fMemVO);
          public void insertAll(FMemVO fMemVO);
          public void update(FMemVO fMemVO);
          public void delete(Integer f_mem_id);
          public FMemVO findByPrimaryKey(Integer f_mem_id);
          public List<FMemVO> getAll();
          //萬用複合查詢(傳入參數型態Map)(回傳 List)
        public List<FMemVO> getAll(Map<String, String[]> map);
}