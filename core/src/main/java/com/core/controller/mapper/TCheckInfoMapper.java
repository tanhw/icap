package com.core.controller.mapper;

import com.core.models.TCheckInfo;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

public interface TCheckInfoMapper extends IBaseMapper<TCheckInfo> {

    int deleteByPrimaryDate(@Param("createtime")Date createtime);
}