package com.business.black;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.core.controller.common.SessionHandler;
import com.core.controller.service.black.IBlackInfoService;
import com.core.models.TBlackInfo;
import com.core.models.common.JsonDataWrapper;
import com.core.models.common.Order;
import com.core.models.common.RollPage;
import com.core.utils.FilePathUtils;
import com.toolbox.util.DateUtil;
import com.toolbox.util.StringUtil;

@Service("blackInfoBusiness")
public class BlackInfoBusiness {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private final String version = "1";
	
	@Autowired
	private IBlackInfoService blackInfoService;

	public JsonDataWrapper<TBlackInfo> list(HttpServletRequest request)throws Exception{
		
		String blackseq = request.getParameter("blackseq");
		String cardno = request.getParameter("cardno");
		String name = request.getParameter("name");
		String mark = request.getParameter("mark");
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("blackseq", blackseq);
		params.put("cardno", cardno);
		params.put("name", name);
		params.put("mark", mark);
		
		if (SessionHandler.getCurrentUnitId() == null) {
			String unitid = request.getParameter("unitid");
			params.put("unitid", unitid);
			
		}else{
			params.put("unitid", SessionHandler.getCurrentUnitId());
		}
		
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
		
		
		RollPage<TBlackInfo>  merData = blackInfoService.findListPageByParams(params, order, pageNum, pageSize);
		
		return new JsonDataWrapper<TBlackInfo>(merData);
	}
	
	/**
	 * 详细信息
	 * 
	 * @param request
	 * @throws Exception
	 */

	public void detail(HttpServletRequest request, String blackseq)
			throws Exception {

		request.setAttribute("isModify", "true");

		TBlackInfo blackInfo = blackInfoService.findObjByKey(Long
				.parseLong(blackseq));

		request.setAttribute("blackInfo", blackInfo);

	}

	/**
	 * 添加
	 * 
	 * @param request
	 * @throws Exception
	 */
	public void add(HttpServletRequest request,TBlackInfo blackInfo)throws Exception{

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("cardno", blackInfo.getCardno());
		
		TBlackInfo obj = blackInfoService.findObj(params);
		
		if(obj != null){
			throw new Exception("E20005");
		}
		
		blackInfo.setVersion(version);
		blackInfo.setUnitid(SessionHandler.getCurrentUnitId());
		blackInfoService.addBasic(blackInfo);
	}

	/**
	 * 修改
	 *
	 * @param request
	 * @throws Exception
	 */
	public void update(HttpServletRequest request, TBlackInfo blackInfo) throws Exception {

		try {
			blackInfoService.modifyBasic(blackInfo);
		} catch (DuplicateKeyException e) {
			throw new Exception("E20035");
		}

	}
	
	/**
	 * 删除
	 * @param request
	 * @throws Exception
	 */
	public void del(HttpServletRequest request,String blackseq)throws Exception{
		
		TBlackInfo blackInfo = new TBlackInfo();
		blackInfo.setBlackseq(Long.parseLong(blackseq.trim()));
		blackInfoService.delBasic(blackInfo);
	}
	
	/**
	 * 批量导入
	 * 
	 * @param uploadFile
	 * @param request
	 * @return
	 */
	public Map<String,Integer> blackBatchAdd(@RequestParam("blacks") MultipartFile uploadFile,
			HttpServletRequest request) throws Exception {
		int seccCount = 0;//计数：成功次数
		int fail = 0;//计数：失败次数
		int repeat = 0;//计数：重复次数
		String mark = request.getParameter("mark");
		Long unitid = SessionHandler.getCurrentUnitId(); 
		
		Workbook rwb = Workbook.getWorkbook(uploadFile.getInputStream());
		Sheet[] sheets = rwb.getSheets();
		Sheet sheet = sheets[0];
		int num = sheet.getRows();
		int col = sheet.getColumns();
		if(col != 2){
			throw new Exception("E20033");
		}
		TBlackInfo tBlackInfo = null;

		for (int i = 1; i < num; i++) {

			Cell[] cell = sheet.getRow(i);

			String cardno = cell[0].getContents();
			String name;

			try{
			name = cell[1].getContents();
			}catch(Exception e){
				name = null;// 不存在姓名余下信息导入
			}

			tBlackInfo = new TBlackInfo();
			tBlackInfo.setCardno(cardno);
			tBlackInfo.setUnitid(unitid);
			tBlackInfo.setName(name);
			tBlackInfo.setMark(mark);
			tBlackInfo.setVersion(version);

			try {
				long secc = blackInfoService.addBasic(tBlackInfo);
				if(secc==1L){
					seccCount++;
				}
			} catch(DuplicateKeyException oneKeyE){
				repeat++;
				logger.info("The black Info is repeat:" + cardno);// 重复不影响余下信息导入
			}catch (Exception e) {
				fail++;
				logger.info("The black Info is repeat:" + cardno);// 错误不影响余下信息导入
			}
		}
		
		if (rwb != null) {
			rwb.close();
		}
		Map<String,Integer>  map = new HashMap<String,Integer> ();
		map.put("seccCount", seccCount);
		map.put("fail", fail);
		map.put("repeat", repeat);
		
		return map;
	}
	
	/**
	 * 黑名单批量文件下载
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void downloadExcelModel(HttpServletRequest request,
			HttpServletResponse response) throws Exception { 

		String filePath = FilePathUtils.INSTANCE.getFilePath("reportDemo", "blacklist.xls");
		//String path = this.getClass().getResource("/reportDemo").getPath();

		String fileName = "blacklist.xls";
		//String filePath = path + fileName;

		StringUtil.downLoadFile(filePath, response, fileName, "xls");
	}
	/**
	 * 
	 * 黑名单 检查重复
	 * @param request
	 * @throws Exception
	 */
	public void checkRepeat(HttpServletRequest request)throws Exception{
		
		String cardno = request.getParameter("cardno");
		
		Map<String, Object> params = new HashMap<String, Object>();
		
		params.put("cardno", cardno);
		
		TBlackInfo tBlackInfo = blackInfoService.findObj(params);
		
		if(tBlackInfo != null){
			throw new Exception("E20005");
		}
	}
	
	public void blackClear(HttpServletRequest request) throws Exception {

		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		
		Map<String, Object> params = new HashMap<String, Object>();

		if (startTime != null) {
			params.put("start", DateUtil.formatDate(startTime,"yyyy-MM-dd HH:mm:ss", "yyyyMMddHHmmss"));
		}

		if (endTime != null) {
			params.put("end", DateUtil.formatDate(endTime,"yyyy-MM-dd HH:mm:ss", "yyyyMMddHHmmss"));
		}
		
		List<TBlackInfo> blackList = blackInfoService.findListByParams(params, null);
		
		blackInfoService.delBlackByList(blackList);
		
	}
}
