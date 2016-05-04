package com.business.txn;

import com.core.controller.common.SessionHandler;
import com.core.controller.service.tab.ITabCofBasicService;
import com.core.controller.service.unit.IUnitInfoService;
import com.core.models.TTabCofBasic;
import com.core.models.TUnitInfo;
import com.core.models.common.RollPage;
import com.toolbox.util.DateUtil;
import jxl.Workbook;
import jxl.format.Colour;
import jxl.write.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class BusTxnCollectBusiness {

	@Autowired
	private ITabCofBasicService tabCofBasicService;

	@Autowired
	private IUnitInfoService unitInfoService;

	//编码
	private final String ENC = "UTF-8";

	/**
	 * 交易统计主界面
	 *
	 * @param request
	 */
	public void mainCollect(HttpServletRequest request) throws Exception {
		Long unitid = SessionHandler.getCurrentUnitId();
		if (unitid != null) {
			List<TTabCofBasic> tabCofButtonList = tabCofBasicService.findByUnitid(SessionHandler.getCurrentUnitId().toString());
			request.setAttribute("tabCofList", tabCofButtonList); //主界面的报表菜单加载
		}
	}

	/**
	 * 报表菜单按钮查询
	 * @param request
	 * @throws Exception
	 */
	public void tableCollect(HttpServletRequest request,TTabCofBasic obj) throws Exception {

		boolean result = false;

		String unitid = request.getParameter("unitid");
		String branchid = request.getParameter("branchid");
		String merseq = request.getParameter("merseq");
		String busid = request.getParameter("busid");
		String posid = request.getParameter("posid");
		String crdkind = request.getParameter("crdkind");
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		String settlestat = request.getParameter("settlestat");
		String pageSize = request.getParameter("pageSize");
		String pageNum = request.getParameter("pageNum");
		String status = request.getParameter("status");

		String linename = request.getParameter("linename");
		String companyname = request.getParameter("companyname");

		if (linename != null && !"".equals(linename)) {
			linename = new String(linename.getBytes("iso-8859-1"), "UTF-8");
		}

		if (companyname != null && !"".equals(companyname)) {
			companyname = new String(companyname.getBytes("iso-8859-1"), "UTF-8");
		}

		if(unitid == null){
			unitid = SessionHandler.getCurrentUnitId().toString();

		}

		/** 子机构 **/
		TUnitInfo unit = unitInfoService.findObjByKey(Long.parseLong(unitid));

		if((SessionHandler.getCurrentUnitId().toString().equals(unitid)) || (unit != null && unit.getParentid() != null && unitid.indexOf(unit.getParentid()) != -1)) {

			if (SessionHandler.getCurrentUnitId() != null) {
				unitid = SessionHandler.getCurrentUnitId().toString();
			}

			if (SessionHandler.getCurrentMerchantId() != null) {
				merseq = SessionHandler.getCurrentMerchantId().toString();
			}


			if (startTime != null) {
				startTime = DateUtil.formatDate(startTime, "yyyy-MM-dd", "yyyyMMdd");
			} else {
				startTime = DateUtil.formatDate(DateUtil.getNowMonth(), "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd");
			}

			if (endTime != null) {
				endTime = DateUtil.formatDate(endTime, "yyyy-MM-dd", "yyyyMMdd");
			} else {
				endTime = DateUtil.formatTime(DateUtil.getTime(), "yyyy-MM-dd");
			}

			if (crdkind != null && !crdkind.equals("")) {
				crdkind = crdkind.substring(0, 2);
			}


			Map<String, Object> params = new HashMap<String, Object>();
			params.put("unitid", unitid);
			params.put("branchid", branchid);
			params.put("merseq", merseq);
			params.put("linename", linename);
			params.put("busid", busid);
			params.put("crdkind", crdkind);
			params.put("posid", posid);
			params.put("companyname", companyname);
			params.put("settlestat", settlestat);
			params.put("start", startTime);
			params.put("end", endTime);
			params.put("status", status);


			Integer size;
			if (pageSize != null && !pageSize.equals("")) {
				size = Integer.parseInt(pageSize);
			} else {
				size = 5;
			}

			Integer num = null;
			if (pageNum != null && !pageNum.equals("")) {
				num = Integer.parseInt(pageNum);
			}

			/*RollPage<TBustxnCollectCount> rollPagelist = service.findListByParamsCountPage(params, num, size, obj.getSql());

			*//**
			 * html报表
			 *//*
			String html;
			try {
				html = this.packageHtmlByTable(rollPagelist.getRecordList(), obj.getFiled(), obj.getCollectfiled());
			} catch (NoSuchFieldException e) {
				e.printStackTrace();
				throw new Exception("E50004"); //字段栏错误，无法解析
			}*/

			request.setAttribute("colectCss", obj.getFiled().split("\\|")[1]);
			request.setAttribute("colspan", obj.getFiled().split("\\|")[0].length());
			//request.setAttribute("rollPagelist", rollPagelist);
			request.setAttribute("title", obj.getTitle());
			//request.setAttribute("body", html);
			request.setAttribute("headline", obj.getConfname());
			request.setAttribute("startTime", startTime);
			request.setAttribute("endTime", endTime);

			result = true;
		}
		request.setAttribute("notfind",result);
	}


	/**
	 * 条件查询动态界面
	 * @param request
	 */
	public void whereCollect(HttpServletRequest request) throws Exception {
		String confid = request.getParameter("id"); //菜单按钮ID

		TTabCofBasic obj = tabCofBasicService.findObjByKey(Integer.parseInt(confid));

		String content = obj.getFileddesc();

		String[] arrayContent = content.split(",");

		if (arrayContent.length % 2 != 0) {
			//如果不是双数，说明值有问题
			throw new Exception();
		}

		Map<String, String> map = new LinkedHashMap<String, String>();

		for (int i = 0; i < arrayContent.length; i+=2) {
			map.put(arrayContent[i],arrayContent[i+1]);
		}

		request.setAttribute("whereMap", map);
		request.setAttribute("confid", confid);
		request.setAttribute("startTime", DateUtil.formatDate(DateUtil.getNowMonth(), "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd"));
		request.setAttribute("endTime", DateUtil.formatTime(DateUtil.getTime(), "yyyy-MM-dd"));

	}


	/**
	 * 根据数据封装HTML
	 *
	 * @param txnCollectCount 封装数据
	 * @param filed 展示字段
	 * @param filedBycount 统计字段
	 * @return 封装后的字段
	 * @throws NoSuchFieldException
	 * @throws IllegalAccessException
	 *//*
	private String packageHtmlByTable(List<TBustxnCollectCount> txnCollectCount, String filed, String filedBycount) throws NoSuchFieldException, IllegalAccessException {

		String[] fieldArray = filed.split("\\|")[0].split(",");
		String[] filedBycountArray = filedBycount.split(",");

		String TR = "<tr></tr>";
		String TD = "<td></td>";
		String TD_STYLE = "<td style=\"text-align: right;padding-right: 30px;\"></td>";

		StringBuffer html = new StringBuffer();
		for (TBustxnCollectCount obj : txnCollectCount) {
			StringBuffer childHtml = new StringBuffer(TR);
			StringBuffer childrenHtml = new StringBuffer();
			Class objClass = obj.getClass();
			for (String str : fieldArray) { //循环字段

				String value;
				if(str.equals("crdkind")){
					value = obj.getCrdkindDesc();
				}else if(str.equals("cardmodel")){
					value = obj.getPhsctypeDesc();
				}else{
					Field field = objClass.getDeclaredField(str);
					field.setAccessible(true);
					value = String.valueOf(field.get(obj));
				}
				//html元素添加值
				StringBuffer htmlTD = new StringBuffer(TD);
				htmlTD.insert(htmlTD.indexOf("><") + 1, value);

				childrenHtml.append(htmlTD.toString());//追加
			}
			for (String str : filedBycountArray) { //循环字段
				String value;
				if(str.equals("pboctxnsum")) {
					value = obj.getPboctxnsumDouble();
				}else{
					Field field = objClass.getDeclaredField(str);
					field.setAccessible(true);
					value = String.valueOf(field.get(obj));
				}

				//html元素添加值
				StringBuffer htmlTD = new StringBuffer(TD_STYLE);
				htmlTD.insert(htmlTD.indexOf("><") + 1, value);

				childrenHtml.append(htmlTD.toString());//追加
			}
			childHtml.insert(childHtml.indexOf("><") + 1, childrenHtml.toString());
			html.append(childHtml.toString());
		}

		return html.toString();
	}



	public boolean exportCollect(HttpServletRequest request,HttpServletResponse response)throws Exception{
		String confid = request.getParameter("id"); //菜单按钮ID

		String unitid = request.getParameter("unitid");
		String branchid = request.getParameter("branchid");
		String merseq = request.getParameter("merseq");
		String linename = request.getParameter("linename");
		String busid = request.getParameter("busid");
		String posid = request.getParameter("posid");
		String companyname = request.getParameter("companyname");
		String crdkind = request.getParameter("crdkind");
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		String settlestat = request.getParameter("settlestat");
		String pageSize = request.getParameter("pageSize");
		String pageNum = request.getParameter("pageNum");
		String status = request.getParameter("status");

		if(unitid == null || unitid.equals("")){
			unitid = SessionHandler.getCurrentUnitId().toString();

		}

		*//** 子机构 **//*
		TUnitInfo unit = unitInfoService.findObjByKey(Long.parseLong(unitid));

		if((!SessionHandler.getCurrentUnitId().toString().equals(unitid)) && (unit == null || unit.getParentid() == null || unitid.indexOf(unit.getParentid()) == -1)) {
			return false;
		}

		if(SessionHandler.getCurrentUnitId() != null ){
			unitid = SessionHandler.getCurrentUnitId().toString();
		}

		if(SessionHandler.getCurrentMerchantId() != null){
			merseq = SessionHandler.getCurrentMerchantId().toString();
		}


		if (startTime != null) {
			startTime = DateUtil.formatDate(startTime,"yyyy-MM-dd", "yyyyMMdd");
		} else {
			startTime = DateUtil.formatDate(DateUtil.getNowMonth(), "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd");
		}

		if (endTime != null) {
			endTime = DateUtil.formatDate(endTime,"yyyy-MM-dd", "yyyyMMdd");
		} else {
			endTime = DateUtil.formatTime(DateUtil.getTime(), "yyyy-MM-dd");
		}

		if(crdkind != null && !crdkind.equals("")) {
			crdkind = crdkind.substring(0,2);
		}


		Map<String, Object> params = new HashMap<String, Object>();
		params.put("unitid", unitid);
		params.put("branchid", branchid);
		params.put("merseq", merseq);
		params.put("linename", linename);
		params.put("busid", busid);
		params.put("crdkind", crdkind);
		params.put("posid", posid);
		params.put("companyname", companyname);
		params.put("settlestat", settlestat);
		params.put("start", startTime);
		params.put("end", endTime);
		params.put("status", status);


		Integer size;
		if(pageSize != null && !pageSize.equals("")){
			size = Integer.parseInt(pageSize);
		}else{
			size = 5000;
		}

		Integer num = null;
		if(pageNum != null && !pageNum.equals("")){
			num = Integer.parseInt(pageNum);
		}

		TTabCofBasic obj = tabCofBasicService.findObjByKey(Integer.parseInt(confid));

		List<TBustxnCollectCount> list = service.findListByParamsCount(params, obj.getSql());

		// 编写xls文件
		OutputStream os = response.getOutputStream();// 取得输出流
		response.reset();// 清空输出流
		response.setContentType("application/msexcel");
		response.setContentType("application/octet-stream");

		response.setHeader("Content-disposition", "attachment;  filename=" +  URLEncoder.encode("collect.xls", "utf-8"));

		WritableWorkbook book = Workbook.createWorkbook(os); // 建立excel文件



		String[] fieldArray = obj.getFiled().split("\\|")[0].split(",");
		String[] filedBycountArray = obj.getCollectfiled().split(",");

		HttpSession session = request.getSession();
		newCollectUtil(session,book,"汇总报表",obj.getConfname() + "(" + startTime + "~" + endTime + ")" ,obj.getExptitle(),list,fieldArray,filedBycountArray);

		book.write();
		book.close();
		return true;
	}

	*//**
	 *
	 * 导出报表工具
	 * @author xi.xu
	 * @date 2015年3月26日 下午4:25:31
	 * @param book exl文件
	 * @param SheetName 工作表名称
	 * @param title 要设置的标题
	 * @param list List<TBustxnCollectCount> 类型的数据源
	 * @return void
	 * @throws
	 *//*
	private void newCollectUtil(HttpSession session, WritableWorkbook book,String SheetName,String title,String exptitle, List<TBustxnCollectCount> list,String[] fieldArray,String[] filedBycountArray)throws Exception{

		WritableSheet sheet = book.createSheet(SheetName, 0);

		*//**
		 * 表格样式
		 *//*
		//标题加粗 字体20 加粗显示
		WritableFont font = new WritableFont(WritableFont.TIMES, 20,WritableFont.BOLD);
		WritableCellFormat format = new WritableCellFormat(font);
		format.setAlignment(jxl.format.Alignment.CENTRE);// 把水平对齐方式指定为居中
		format.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);// 把垂直对齐方式指定为居中
		format.setBackground(jxl.format.Colour.GREY_25_PERCENT);//灰色
		format.setBorder(jxl.format.Border.ALL,jxl.format.BorderLineStyle.THIN);
		//统计数据
		WritableFont font1 = new WritableFont(WritableFont.TIMES, 14,WritableFont.NO_BOLD);
		WritableCellFormat format1 = new WritableCellFormat(font1);
		format1.setAlignment(jxl.format.Alignment.CENTRE);// 把水平对齐方式指定为居中
		format1.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);// 把垂直对齐方式指定为居中
		format1.setBorder(jxl.format.Border.ALL,jxl.format.BorderLineStyle.THIN);
		//小计数据样式
		WritableFont font2 = new WritableFont(WritableFont.TIMES, 14,WritableFont.NO_BOLD);
		font2.setColour(Colour.RED);
		WritableCellFormat format2 = new WritableCellFormat(font2);
		format2.setAlignment(jxl.format.Alignment.CENTRE);// 把水平对齐方式指定为居中
		format2.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);// 把垂直对齐方式指定为居中
		format2.setBorder(jxl.format.Border.ALL,jxl.format.BorderLineStyle.THIN);
		//小计数据样式1
		WritableFont font4 = new WritableFont(WritableFont.TIMES, 14,WritableFont.NO_BOLD);
		font4.setColour(Colour.RED);
		WritableCellFormat format4 = new WritableCellFormat(font4);
		format4.setAlignment(jxl.format.Alignment.CENTRE);// 把水平对齐方式指定为居中
		format4.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);// 把垂直对齐方式指定为居中
		format4.setBackground(jxl.format.Colour.GREY_25_PERCENT);
		format4.setBorder(jxl.format.Border.ALL,jxl.format.BorderLineStyle.THIN);
		//标题数据样式
		WritableFont font3 = new WritableFont(WritableFont.TIMES, 14,WritableFont.NO_BOLD);
		WritableCellFormat format3 = new WritableCellFormat(font3);
		format3.setAlignment(jxl.format.Alignment.CENTRE);// 把水平对齐方式指定为居中
		format3.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);// 把垂直对齐方式指定为居中
		format3.setBorder(jxl.format.Border.ALL,jxl.format.BorderLineStyle.THIN);
		format3.setBackground(jxl.format.Colour.GREEN);//灰色
		//------------------------------------------------------------------------------------------

		String titleArry [] = exptitle.split(",");

		//设置标题
		Label titleLabel = new Label(0, 0, title, format);//设置数据
		sheet.addCell(titleLabel);
		sheet.mergeCells(0, 0, titleArry.length-1, 0);//合并单元格

		int j=0;
		for(String titleStr:titleArry){
			Label label = new Label(j++, 1, titleStr, format3);//设置数据
			sheet.addCell(label);
		}

		Map<String, RowSpan> fieldMap = new HashMap<>();
		for (String field : fieldArray) {
			RowSpan obj = new RowSpan();
			fieldMap.put(field, obj);
		}


		session.setAttribute("task",1);
		for (int i = 1; i <= list.size(); i++) {
			boolean isstyle = false;
			boolean islast = i +1 <=list.size()?false:true;
			TBustxnCollectCount obj = list.get(i - 1);
			Class objClass = obj.getClass();

			for (int num = 0; num < fieldArray.length;num++) {
				String fieldName = fieldArray[num];
				String value;
				if (fieldName.equals("crdkind")) {
					value = obj.getCrdkindDesc();
				} else if (fieldName.equals("cardmodel")) {
					value = obj.getPhsctypeDesc();
				} else {
					Field field = objClass.getDeclaredField(fieldName);
					field.setAccessible(true);
					value = String.valueOf(field.get(obj));
				}

				RowSpan objField = fieldMap.get(fieldName);
				// 在Label对象的构造子中指名单元格位置是第x列第x行(x,x)
				isstyle = objField.addCell(sheet, num, i + 1, value, islast, titleArry.length, isstyle);
				fieldMap.put(fieldName, objField);
			}
			Label label = null;


			WritableCellFormat nowCss = format2;
			if(isstyle){
				nowCss = format4;
			}

			for (int num = 1; num <= filedBycountArray.length; num++) {
				String filedName = filedBycountArray[num - 1];
				String value;
				if (filedName.equals("pboctxnsum")) {
					value = obj.getPboctxnsumDouble();
				} else {
					Field field = objClass.getDeclaredField(filedName);
					field.setAccessible(true);
					value = String.valueOf(field.get(obj));
				}

				label = new Label(num - 1 + fieldArray.length, i+1,value, nowCss);
				sheet.setColumnView(num - 1 + fieldArray.length, 22);
				sheet.addCell(label);
			}

			session.setAttribute("task",100 - list.size() / i );
		}
		session.setAttribute("task",100);
	}*/

	/**
	 * 操作exl单元格工具类
	 * @ClassName: RowSpan
	 * @author xi.xu
	 * @Description: TODO
	 * @author A18ccms a18ccms_gmail_com
	 * @date 2015年3月26日 上午11:01:51
	 *
	 */
	class RowSpan{
		private String text;
		private Integer row;
		private Integer col;

		/**
		 * 设置单元格工具类，动态比较合并单元格
		 * @author xi.xu
		 * @date 2015年3月26日 上午10:54:46
		 * @param row //要设置的列
		 * @param  col //要设置的行
		 * @param  text //比较或设置的文本值
		 * @param  islast //当然数据是否是最后一组
		 * @param  rowNum //除统计数据外的列数
		 * @return void
		 * @throws
		 */
		public boolean addCell(WritableSheet sheet,int row,int col,String text,boolean islast,int rowNum,boolean isstyle)throws Exception{
			// 表格样式
			//统计数据
			WritableFont font = new WritableFont(WritableFont.TIMES, 14,WritableFont.NO_BOLD);
			WritableCellFormat format = new WritableCellFormat(font);
			format.setAlignment(jxl.format.Alignment.CENTRE);// 把水平对齐方式指定为居中
			format.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);// 把垂直对齐方式指定为居中
			format.setBorder(jxl.format.Border.ALL,jxl.format.BorderLineStyle.THIN);
			//小计数据样式1
			WritableFont font1 = new WritableFont(WritableFont.TIMES, 14,WritableFont.NO_BOLD);
			font1.setColour(Colour.RED);
			WritableCellFormat format1 = new WritableCellFormat(font1);
			format1.setAlignment(jxl.format.Alignment.LEFT);// 把水平对齐方式指定为居中
			format1.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);// 把垂直对齐方式指定为居中
			format1.setBackground(jxl.format.Colour.GREY_25_PERCENT);
			format1.setBorder(jxl.format.Border.ALL,jxl.format.BorderLineStyle.THIN);

			if(this.text == null || this.text.equals("null")){//如暂存无数据，设置当前数据
				this.text = text;
				this.row = row;
				this.col = col;
			}else if(this.text.equals(text)){ //比较数据,如果一样，增加个数 //如果一样且为最后一数据，设置单元格合并
				if(islast){
					Label label = new Label(this.row, this.col, this.text, format);//设置数据
					sheet.addCell(label);
					sheet.setColumnView(this.row , 16 );
					sheet.mergeCells(this.row, this.col, row, col);//合并单元格
				}
			}else{//如果不一样，设置exl单元格值

				Label label = new Label(this.row, this.col, this.text, format);//设置数据
				sheet.addCell(label);
				sheet.setColumnView(this.row , 16 );
				sheet.mergeCells(this.row, this.col, row, col-1);//合并单元格

				this.text = text;
				this.row = row;
				this.col = col;
				if(text == null || text.equals("null")){
					if(islast){
						text = "总计:";
					}else{
						text = "小计:";
					}
					label = new Label(row, col, text,format1);//设置数据
					sheet.addCell(label);
					sheet.mergeCells(row, col, rowNum-7, col);//合并单元格
					return true;
				}
			}
			return isstyle;
		}
	}

}
