package com.core.controller.mapper;

import com.core.models.TTabCofBasic;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface TTabCofBasicMapper extends IBaseMapper<TTabCofBasic> {


    /**
     * 根据条件查询绑定的机构报表
     * @param unitid
     * @return
     */
    List<TTabCofBasic> selectByUnitid(@Param(value="unitid") String unitid);
}