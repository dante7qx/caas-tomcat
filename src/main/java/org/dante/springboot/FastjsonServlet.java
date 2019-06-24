package org.dante.springboot;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dante.springboot.vo.MsgVO;

import com.alibaba.fastjson.JSON;

/**
 * Servlet implementation class HelloServlet
 */
public class FastjsonServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FastjsonServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json; charset=UTF-8");
		MsgVO msg = new MsgVO();
		msg.setMsgId("1000001");
		msg.setInfo("com.alibaba.fastjson");
		response.getWriter().println(JSON.toJSONString(msg));
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
