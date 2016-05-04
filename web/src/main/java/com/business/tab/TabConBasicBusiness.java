package com.business.tab;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.core.controller.service.tab.IUnitTabConfService;
import com.core.models.TUnitTabConf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.core.controller.service.tab.ITabCofBasicService;
import com.core.models.TTabCofBasic;
import com.core.models.common.JsonDataWrapper;
import com.core.models.common.Order;
import com.core.models.common.RollPage;
import com.toolbox.util.StringUtil;

@Service("tabConBasicBusiness")
public class TabConBasicBusiness {

    @Autowired
    private ITabCofBasicService basicService;

    @Autowired
    private IUnitTabConfService unitTabConfService;

    /**
     * 列表
     *
     * @param request
     * @return
     * @throws Exception
     * @ author 许西
     */
    public JsonDataWrapper<TTabCofBasic> list(HttpServletRequest request)
            throws Exception {


        String confname = request.getParameter("confname");
        String title = request.getParameter("title");
        String isactive = request.getParameter("isactive");
        String busi = request.getParameter("busi");

        Map<String, Object> params = new HashMap<String, Object>();

        params.put("confname", confname);
        params.put("title", title);
        params.put("isactive", isactive);
        params.put("busi", busi);

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

        RollPage<TTabCofBasic> data = basicService.findListPageByParams(params,
                order, pageNum, pageSize);

        return new JsonDataWrapper<TTabCofBasic>(data);
    }


    /**
     * 选择详情
     *
     * @param request
     * @throws Exception
     * @ author 许西
     */
    public void detail(HttpServletRequest request, String id)
            throws Exception {

        // 修改
        request.setAttribute("isModify", "true");

        TTabCofBasic tTabCofBasic = basicService.findObjByKey(Integer.parseInt(id));

        request.setAttribute("tTabCofBasic", tTabCofBasic);
    }

    /**
     * 保存
     *
     * @param request
     * @throws Exception
     * @author 许西
     */
    public void add(HttpServletRequest request, TTabCofBasic busInfo)
            throws Exception {
        basicService.addBasic(busInfo); // ADD
    }

    /**
     * 更新
     *
     * @param request
     * @throws Exception
     * @author 许西
     */
    public void update(HttpServletRequest request, TTabCofBasic busInfo)
            throws Exception {
        basicService.modifyBasic(busInfo); // update

    }

    /**
     * 选择删除
     *
     * @param request
     * @throws Exception
     * @ author 许西
     */
    public void del(HttpServletRequest request, String id)
            throws Exception {

        /** 删除 **/
        TTabCofBasic obj = new TTabCofBasic();
        obj.setConfid(Integer.parseInt(id));
        basicService.delBasic(obj);

    }


    public List<TTabCofBasic> allConfBasic(HttpServletRequest request, String unitid)throws Exception {

        Map<String, Object> params = new HashMap<String, Object>();

        List<TTabCofBasic> confBasicList = basicService.findListByParams(params, null);

        params.put("unitid", unitid);
        List<TUnitTabConf> unitTabConf = unitTabConfService.findListByParams(params, null);

        String tempid  ="";
        for (TUnitTabConf temp : unitTabConf) {
            tempid += (temp.getConfid().toString() + ",");
        }

        for (TTabCofBasic obj : confBasicList) {
            String confid = obj.getConfid().toString();
            boolean result  = tempid.indexOf(confid) != -1;
            obj.setChecked(result);
        }

        if (confBasicList.size() == 0) {
            throw new Exception("E20029");
        }

        return confBasicList;
    }

}
