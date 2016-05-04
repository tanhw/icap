package com.core.controller.service.tbl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.core.controller.mapper.TSysParaMapper;
import com.core.controller.service.BaseService;
import com.core.models.TSysPara;

@Service("blSysParaService")
public class SysParaService extends
		BaseService<TSysParaMapper, TSysPara> implements ISysParaService {
	
	private TSysParaMapper mapper;
	
	@Autowired
	public void setTblSysParaMapper(TSysParaMapper mapper){
		this.mapper = mapper;
		super.mapper = mapper;
	}
	
	@Override
	public Long addBasic(TSysPara obj) throws Exception {
		return (long) mapper.insertSelective(obj);
	}

	@Override
	public void modifyBasic(TSysPara obj) throws Exception {
		mapper.updateByPrimaryKeySelective(obj);
	}

	@Override
	public void delBasic(TSysPara obj) throws Exception {
		mapper.deleteByPrimaryKey(obj);
		
	}

	/**
	 * 查询BlSys
	 */
	@Override
	public TSysPara findBlSysByKey(TSysPara tblSysPara) throws Exception {
		return mapper.selectBlSysByPrimaryKey(tblSysPara);
	}
	
	/**
	 * 根据主键查询前置参数配置表中软加加密钥
	 * 
	 * @param uName
	 * @param paraName 参数名称
	 * @param paraNo 参数编号
	 * @return
	 * @throws Exception
	 */
	public TSysPara selectBlSysParaByParam(String uName, String paraName, String paraNo)
			throws Exception {

		TSysPara sysPara = new TSysPara();
		
		sysPara.setUname(uName);
		sysPara.setParaName(paraName);
		sysPara.setParaNo(paraNo);

		sysPara = mapper.selectBlSysByPrimaryKey(sysPara);

		return sysPara;

	}
	
	
	public String getParaValue(String uname, String paraname){
		
		Map<String, Object> params = new HashMap<String, Object>();

		params.put("uname", uname);

		params.put("paraName", paraname);

		TSysPara obj = mapper.selectByParams(params);
		
		if(obj != null){
			return obj.getParaValue();
		}
		
		return "";
	}
	
}
