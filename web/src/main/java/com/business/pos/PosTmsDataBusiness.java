package com.business.pos;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.core.controller.common.SessionHandler;
import com.core.controller.service.dict.IDictService;
import com.core.controller.service.pos.IPosTmsDataService;
import com.core.controller.service.tbl.ISysParaService;
import com.core.models.TDict;
import com.core.models.TPosTmsData;
import com.core.models.TSysPara;
import com.core.models.common.JsonDataWrapper;
import com.core.models.common.Order;
import com.core.models.common.RollPage;
import com.toolbox.log.LogUtil;
import com.toolbox.util.StringUtil;

@Service("PosInfoBusiness")
public class PosTmsDataBusiness {

	@Autowired
	private IPosTmsDataService posTmsDataService;

	@Autowired
	private ISysParaService blSysParaService;
	
	@Autowired
	private IDictService dictService;

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * 分页列表
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public JsonDataWrapper<TPosTmsData> lsit(HttpServletRequest request)
			throws Exception {

		String unitid = request.getParameter("unitid");
		String merseq = request.getParameter("merseq");

		if (SessionHandler.getCurrentUnitId() != null) {
			unitid = SessionHandler.getCurrentUnitId().toString();
		}

		if (SessionHandler.getCurrentMerchantId() != null) {
			merseq = SessionHandler.getCurrentMerchantId().toString();
		}

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("merseq", merseq);
		params.put("unitid", unitid);

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

		RollPage<TPosTmsData> posInfoData = posTmsDataService
				.findListPageByParams(params, order, pageNum, pageSize);

		return new JsonDataWrapper<TPosTmsData>(posInfoData);
	}

	public void upload(HttpServletRequest request,
			@RequestParam("catchfile") MultipartFile tmsFile) throws Exception {

		String fileName = tmsFile.getOriginalFilename();
		/** 验证文件名 **/
		if (!fileName
				.matches("^(([A-Z0-9_]{12})+((19|20)+\\d+\\d+(0[1-9]|1[012])+([012][0-9]|3[01])+\\w)).bin$")) {
			throw new Exception("E00008");
		}

		
		
		/**
		 * 查询根目录
		 */
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("uname", "FEP");
		params.put("paraName", "FILE");
		TSysPara blSysPara = blSysParaService.findObj(params);
		
		TDict dict =  dictService.findObjByKey("TMSFILENAME");
		
		String merseq = (String) request.getParameter("merseq");

		if (SessionHandler.getCurrentMerchantId() != null) {
			merseq = SessionHandler.getCurrentMerchantId().toString();
		}
		
		String branchid = (String) request.getParameter("branchid");
		String filefunc = new String(request.getParameter("filefunc").getBytes("iso8859-1"),"utf-8");
		String filesize = String.valueOf(tmsFile.getSize());
		String unitid = String.valueOf(SessionHandler.getCurrentUnitId());
		String version = fileName.substring(12, 20);
		String postype = request.getParameter("postype");
		String posbrand = request.getParameter("posbrand");
		String newFilePath = dict.getCvalue()+ "/" +   unitid + "/" + (merseq!=null&&!merseq.equals("")?(merseq+"/"):"") + postype +"/" + version + "/" + fileName;

		String lodPath = "";
		
		TPosTmsData tms = posTmsDataService.findObjByKey(fileName);
		boolean isupdate = false;
		if(tms != null){
			isupdate = true;
			lodPath = blSysPara.getParaValue().trim()+"/" + tms.getFilepath();
		}
		tms = new TPosTmsData();
		if(merseq != null && !merseq.equals("")) tms.setMerseq(Integer.parseInt(merseq));
		if(branchid != null && !branchid.equals("")) tms.setBranchid(branchid);
		tms.setFilename(fileName);
		tms.setUnitid(Integer.parseInt(unitid));
		tms.setFilesize(filesize);
		tms.setFilepath(newFilePath);
		tms.setVersion(version);
		tms.setFilefunc(filefunc);
		tms.setPosbrand(posbrand);
		
		File dirPath = new File(blSysPara.getParaValue().trim());
		if (!dirPath.exists()) {
			dirPath.mkdirs();
		}
		
		File file = new File(blSysPara.getParaValue().trim()+"/" + dict.getCvalue()+ "/" +  unitid + "/" + (merseq!=null&&!merseq.equals("")?(merseq+"/"):"") + postype +"/" + version + "/" + fileName);
		if (!file.exists()) {
			file.mkdirs();
		}

		try {
			System.out.println(lodPath);
			if(isupdate){
					File lodFile = new File(lodPath);
					lodFile.delete();
					tmsFile.transferTo(file);
					posTmsDataService.modifyBasic(tms);
			}else{
				tmsFile.transferTo(file);
				Long i = posTmsDataService.addBasic(tms);
				if (i != 1) {
					file.delete();
					throw new Exception("E99999");
				}
			}

		} catch (IllegalStateException | IOException e) {
			logger.info(LogUtil.getTrace(e));
			throw new Exception("E00007");
		} catch (Exception e) {
			throw e;
		}
	}

	
	/**
	 * 删除
	 * @param request
	 * @throws Exception
	 */
	public void del(HttpServletRequest request, String filename)
			throws Exception {

		/**
		 * 查询根目录
		 */
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("uname", "FEP");
		params.put("paraName", "FILE");
		TSysPara blSysPara = blSysParaService.findObj(params);
		
		TPosTmsData tms = posTmsDataService.findObjByKey(filename);

		File file = new File(blSysPara.getParaValue().trim() + "/"+ tms.getFilepath().trim());
		File parentFile = file.getParentFile();
		file.delete();
		if (parentFile != null && parentFile.listFiles() != null && parentFile.listFiles().length >= 0) {
			parentFile.delete();
		}

		posTmsDataService.delBasic(tms);
	}
	
}
