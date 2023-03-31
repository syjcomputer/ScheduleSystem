package com.pers.ScheduleSystem.utils.tags;

import com.pers.ScheduleSystem.dao.Po.UserPo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.io.StringWriter;

/**
 * @description: Footer
 * @author: sunyujie
 * @time: 2023/2/14 21:20
 * @version: 1.0
 */
public class Footer extends SimpleTagSupport {

    @Override
    public void doTag()
            throws JspException, IOException
    {

        HttpServletRequest request=(HttpServletRequest) ((PageContext)this.getJspContext()).getRequest();

        String path = request.getContextPath();

        JspWriter out = getJspContext().getOut();

        String css = path + "/css/desc.css";
        out.println("<link rel=\"stylesheet\" href="+css+"/>");
        out.println("<div id=footer class=footer>");
        out.println("系统设计者:syj");
        out.println("</div>");

    }
}