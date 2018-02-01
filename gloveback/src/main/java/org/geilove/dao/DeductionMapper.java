package org.geilove.dao;

import org.geilove.pojo.Deduction;

import java.util.List;
import java.util.Map;

public interface DeductionMapper {
    int deleteByPrimaryKey(Long deductionid);

    int insert(Deduction record);

    int insertSelective(Deduction record);

    Deduction selectByPrimaryKey(Long deductionid);

    int updateByPrimaryKeySelective(Deduction record);

    int updateByPrimaryKey(Deduction record);

    List<Deduction> getDeductionList(Map<String,Object> map);
}