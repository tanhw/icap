package com.web.action.card;


import com.business.card.CardInfoBusiness;
import com.business.comm.CommParamsBusiness;
import com.constant.RespCodeConstant;
import com.core.annotation.LogAction;
import com.core.annotation.RightCode;
import com.core.controller.common.SessionHandler;
import com.core.controller.util.MenuUtil;
import com.core.models.TCardInfo;
import com.core.models.common.JsonDataWrapper;
import com.toolbox.log.LogUtil;
import com.web.queryFrame.engine.UiHandler;
import com.web.queryFrame.models.UiColumn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RequestMapping("/card")
@Controller
public class CardInfoAction {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CardInfoBusiness cardInfoBusiness;

    @Autowired
    private CommParamsBusiness commParamsBusiness;

    /**
     * 首页
     */
    @RequestMapping("/index.html")
    @RightCode(menuCode = "A60000")
    public String index(HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        String xmlFile = "cardInfoList";
        List<UiColumn> list = UiHandler.getUiListColumn(xmlFile);
        request.setAttribute("showColumn", list);

        request.setAttribute("childMenu", MenuUtil.getFormatMenu(
                SessionHandler.getCurrentRightCode(),
                SessionHandler.getAllMeun()));
        request.setAttribute("listParam", UiHandler.getUiListParam(xmlFile));
        request.setAttribute("importJs", UiHandler.getUiJs(xmlFile));

        return "queryList";
    }

    /**
     * 分页列表
     *
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("/list.html")
    @LogAction(logDesc = "名单管理分页查询", fieldName = "request")
    public JsonDataWrapper<TCardInfo> list(HttpServletRequest request) {

        JsonDataWrapper<TCardInfo> res;

        try {
            res = cardInfoBusiness.list(request);
        } catch (Exception e) {
            logger.info(LogUtil.getTrace(e));
            res = new JsonDataWrapper<TCardInfo>(false, e.getMessage());
        }

        return res;

    }

    /**
     * 查询
     *
     * @param request
     * @return
     */
    @RequestMapping("/query.html")
    public String query(HttpServletRequest request) throws Exception {
        commParamsBusiness.selectByUPParam("CARDLIST", request, "cardlist");

        String unitid = request.getParameter("unitid");

        if (SessionHandler.getCurrentUnitId() != null) {
            unitid = SessionHandler.getCurrentUnitId().toString();
        }

        request.setAttribute("unitid", unitid);
        return "card/cardInfoQuery";
    }

    /**
     * 详细信息
     *
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("/detail.html")
    public String detail(HttpServletRequest request) throws Exception {

        commParamsBusiness.selectByUPParam("CARDLIST", request, "cardlist");

        try {
            String cardid = request.getParameter("id");

            if (cardid == null || cardid.equals("")) {
                request.setAttribute("isModify", "false");
                return "card/cardInfoDetail";
            }
           cardInfoBusiness.detail(request, cardid);
            request.setAttribute("isModify", "true");
        } catch (Exception e) {
            logger.info(LogUtil.getTrace(e));
            throw e;
        }

        return "card/cardInfoDetail";
    }

    /**
     * 保存 修改
     * @param request
     * @return
     */
    @RequestMapping("/save.html")
    @ResponseBody
    @LogAction(logDesc = "名单列表增加或修改", fieldName = "cardno")
    public JsonDataWrapper<TCardInfo> save(HttpServletRequest request,TCardInfo cardInfo){

        JsonDataWrapper<TCardInfo> res = new JsonDataWrapper<TCardInfo>(
                true, RespCodeConstant.Success.toString());

        try {
            String isModify = request.getParameter("isModify");
            if(isModify.equals("false")){
                //添加
                cardInfoBusiness.add(request, cardInfo);
            }else{
                //修改
                cardInfoBusiness.modify(request, cardInfo);
            }

        } catch (Exception e) {
            logger.info(LogUtil.getTrace(e));
            res = new JsonDataWrapper<TCardInfo>(false,e.getMessage());
        }
        return res;
    }


    /**
     * 删除
     *
     * @param request
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping("/del.html")
    @LogAction(logDesc = "删除", fieldName = "campid,campname")
    public JsonDataWrapper<TCardInfo> del(HttpServletRequest request,
                                                 HttpServletResponse response) throws Exception {

        JsonDataWrapper<TCardInfo> res = new JsonDataWrapper<TCardInfo>(true, RespCodeConstant.Success.toString());
        try {
            String id = request.getParameter("id");
            cardInfoBusiness.del(request, id);
        } catch (Exception e) {
            logger.info(LogUtil.getTrace(e));
            res = new JsonDataWrapper<TCardInfo>(false, e.getMessage());
        }
        return res;
    }
}
