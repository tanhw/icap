package com.business.card;

import com.core.controller.common.SessionHandler;
import com.core.controller.service.black.ICardInfoService;
import com.core.models.TCardInfo;
import com.core.models.common.JsonDataWrapper;
import com.core.models.common.Order;
import com.core.models.common.RollPage;
import com.toolbox.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 西 on 2015/10/19.
 */
@Service
public class CardInfoBusiness {

    @Autowired
    private ICardInfoService cardInfoService;

    public JsonDataWrapper<TCardInfo> list(HttpServletRequest request) throws Exception {

        String cardno = request.getParameter("cardno");

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("cardno", cardno);

        if (SessionHandler.getCurrentUnitId() == null) {
            String unitid = request.getParameter("unitid");
            params.put("unitid", unitid);

        } else {
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


        RollPage<TCardInfo> cardInfoData = cardInfoService.findListPageByParams(params, order, pageNum, pageSize);

        return new JsonDataWrapper<TCardInfo>(cardInfoData);
    }


    public void add(HttpServletRequest request, TCardInfo cardInfo) throws Exception {
        Long unitid = SessionHandler.getCurrentUnitId();
        cardInfo.setUnitid(unitid);
        cardInfoService.addBasic(cardInfo);
    }

    public void modify(HttpServletRequest request, TCardInfo cardInfo) throws Exception {
        cardInfoService.modifyBasic(cardInfo);
    }

    public void detail(HttpServletRequest request, String cardid) throws Exception {
        TCardInfo obj = cardInfoService.findObjByKey(cardid);
        request.setAttribute("cardinfo", obj);
    }

    public void del(HttpServletRequest request, String cardid) throws Exception {
        TCardInfo obj = new TCardInfo();
        obj.setCardid(Long.parseLong(cardid));
        cardInfoService.delBasic(obj);
    }
}
