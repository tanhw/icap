package com.core.controller.mapper;

import com.core.models.TPospInfo;

public interface TPospInfoMapper {
    int deleteByPrimaryKey(String posid);

    int insert(TPospInfo record);

    int insertSelective(TPospInfo record);

    TPospInfo selectByPrimaryKey(String posid);

    int updateByPrimaryKeySelective(TPospInfo record);

    int updateByPrimaryKey(TPospInfo record);
}