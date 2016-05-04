package com.core.controller.service.tab;

import com.core.controller.mapper.TTabCofBasicMapper;
import com.core.controller.service.IBaseService;
import com.core.models.TTabCofBasic;

import java.util.List;

/**
 * Created by 西 on 2015/8/10.
 */
public interface ITabCofBasicService extends IBaseService<TTabCofBasicMapper,TTabCofBasic> {

    List<TTabCofBasic> findByUnitid(String unitid)throws  Exception;
}
