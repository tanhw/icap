package com.core.controller.service.pos;

import com.core.controller.mapper.TPosTmsDataMapper;
import com.core.controller.service.IBaseService;
import com.core.models.TPosTmsData;

public interface IPosTmsDataService extends IBaseService<TPosTmsDataMapper, TPosTmsData>{

	TPosTmsData findByPosId(String posid);
}
