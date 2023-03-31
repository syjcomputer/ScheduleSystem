package com.pers.ScheduleSystem.utils.tags;

import com.pers.ScheduleSystem.dao.common.pojo.BasePersonPo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.io.StringWriter;

/**
 * @description: Banner
 * @author: sunyujie
 * @time: 2023/2/14 15:23
 * @version: 1.0
 */
public class Banner extends SimpleTagSupport {

    StringWriter sw = new StringWriter();

    @Override
    public void doTag()
            throws JspException, IOException
    {

        HttpSession session=((PageContext)this.getJspContext()).getSession();
        HttpServletRequest request=(HttpServletRequest) ((PageContext)this.getJspContext()).getRequest();

        BasePersonPo userPo = (BasePersonPo)session.getAttribute("loginUser");
        String path = request.getContextPath();

        JspWriter out = getJspContext().getOut();

        String css = path + "/css/desc.css";
        out.println("<link rel=\"stylesheet\" href="+css+"/>");
        out.println("<div id=banner class=banner>");
        if (!(userPo==null)) {
            /* 从属性中使用消息 */
            out.println("欢迎"+userPo.getName()+"!");
            out.println("</div>");

        }
        else {
            /* 从内容体中使用消息 */
//	          getJspBody().invoke(sw);
//	          getJspContext().getOut().println(sw.toString());

            out.println("请登录！");
            out.println("</div>");
        }
    }
}
