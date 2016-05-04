package com.business.pos;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.constant.CommonConstant;
import com.core.controller.common.SessionHandler;
import com.core.controller.service.pos.IPosInfoService;
import com.core.models.TPosInfo;
import com.core.models.common.JsonDataWrapper;
import com.core.models.common.Order;
import com.core.models.common.RollPage;
import com.toolbox.util.StringUtil;

@Service
public class PosInfoBusiness {

	@Autowired
	private IPosInfoService posInfoService;

	/**
	 * 分页列表
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public JsonDataWrapper<TPosInfo> lsit(HttpServletRequest request)throws Exception{

		String posid = request.getParameter("posid");
		String postype = request.getParameter("postype");
		String unitid = request.getParameter("unitid");
		String branchid = request.getParameter("branchid");
		String merseq = request.getParameter("merseq");
		String busid = request.getParameter("busid");
		String samid = request.getParameter("samid");
		String termseq = request.getParameter("termseq");
		String batchno = request.getParameter("batchno");
		String status = request.getParameter("status");
		String tmkdownflag = request.getParameter("tmkdownflag");
		String keyadownflag = request.getParameter("keyadownflag");
		String posbrand = request.getParameter("posbrand");

		if(SessionHandler.getCurrentUnitId() != null){
			unitid = SessionHandler.getCurrentUnitId().toString();
		}

		if(SessionHandler.getCurrentMerchantId() != null){
			merseq = SessionHandler.getCurrentMerchantId().toString();
		}

		Map<String , Object> params = new HashMap<String, Object>();

		params.put("posid", posid);
		params.put("postype", postype);
		params.put("branchid", branchid);
		params.put("busid", busid);
		params.put("samid", samid);
		params.put("termseq", termseq);
		params.put("batchno", batchno);
		params.put("status", status);
		params.put("merseq", merseq);
		params.put("unitid", unitid);
		params.put("tmkdownflag", tmkdownflag);
		params.put("posbrand", posbrand);
		params.put("keyadownflag", keyadownflag);

		String page = request.getParameter("page");
		String rows = request.getParameter("rows");
		String sortOrder = request.getParameter("order");
		String sortName = request.getParameter("sort");

		Integer pageNum = 1;
		if (page != null)
			pageNum = Integer.parseInt(page);
		Integer pageSize = 20;
		if (rows != null)
			pageSize = Integer.parseInt(rows);

		Order order = null;
		if (StringUtil.checkNull(false, sortOrder, sortName)) {
			if (sortOrder.equals("desc"))
				order = Order.desc(sortName);
			else
				order = Order.asc(sortName);
		}

		RollPage<TPosInfo> posInfoData = posInfoService.findListPageByParams(params, order, pageNum, pageSize);

		return new JsonDataWrapper<TPosInfo>(posInfoData);
	}

	/**
	 * 详情信息
	 *
	 * @param request
	 * @return
	 */
	public void detail(HttpServletRequest request,String posid)throws Exception{

		request.setAttribute("isModify", "true");

		TPosInfo tPosInfo = posInfoService.findObjByKey(posid);

		request.setAttribute("tPosInfo", tPosInfo);

	}

	/**
	 * 添加
	 *
	 * @param request
	 * @param tPosInfo
	 * @throws Exception
	 */
	public void add(HttpServletRequest request, TPosInfo tPosInfo) throws Exception {

		Map<String, Object> params = new HashMap<String, Object>();

		params.put("posid", tPosInfo.getPosid());

		TPosInfo obj = posInfoService.findObj(params);

		if (obj != null) {
			throw new Exception("E20007");
		}

		tPosInfo.setUnitid(SessionHandler.getCurrentUnitId());
		posInfoService.addBasic(tPosInfo);
	}

	/**
	 * 修改
	 * @param request
	 * @param tPosInfo
	 * @throws Exception
	 */
	public void update(HttpServletRequest request,TPosInfo tPosInfo)throws Exception{

		posInfoService.modifyBasic(tPosInfo);
	}

	/**
	 * 删除
	 * @param request
	 * @throws Exception
	 */
	public void del(HttpServletRequest request,String posid)throws Exception{

		TPosInfo tPosInfo = new TPosInfo();

		tPosInfo.setPosid(posid);

		posInfoService.delBasic(tPosInfo);
	}

	/**
	 * 修改主密钥下载标志状态为启用
	 * @param request
	 * @param posid
	 * @throws Exception
	 */
	public void edit(HttpServletRequest request,String posid)throws Exception{

		TPosInfo tPosInfo = new TPosInfo();

		tPosInfo.setPosid(posid);
		tPosInfo.setTmkdownflag(CommonConstant.TmkDownFlag.FALSE.toString());

		posInfoService.modifyBasic(tPosInfo);

	}

	/**
	 * 修改keya密钥下载标志状态为启用
	 * @param request
	 * @param posid
	 * @throws Exception
	 */
	public void downkeya(HttpServletRequest request, String posid)throws Exception {
		TPosInfo tPosInfo = new TPosInfo();
		tPosInfo.setPosid(posid);
		tPosInfo.setKeyadownflag(CommonConstant.KeyaDownFlag.FALSE.toString());
		posInfoService.modifyBasic(tPosInfo);
	}

	/**
	 * 终端  检查重复
	 * @param request
	 * @throws Exception
	 */
	public void checkRepeat(HttpServletRequest request)throws Exception{

		String posid = request.getParameter("posid");

		Map<String, Object> params = new HashMap<String, Object>();

		params.put("posid", posid);

		TPosInfo tPosInfo = posInfoService.findObj(params);

		if(tPosInfo != null){
			throw new Exception("E20007");
		}

	}
}