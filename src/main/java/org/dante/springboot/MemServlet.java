package org.dante.springboot;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * 测试内存 https://zhuanlan.zhihu.com/p/36554554
 */
public class MemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static Logger logger = LogManager.getLogger(MemServlet.class.getName());

	private final List<String> caches = new ArrayList<>();

	public MemServlet() {
		super();
		for (int i = 0; i < 5000; i++) {
			caches.add("数据 - " + i);
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		logger.info("Log4j2 to record log...");
		String countStr = request.getParameter("c");
		int count = Objects.isNull(countStr) ? 100 : Integer.parseInt(countStr);
		List<String> list = new ArrayList<>();
		for (int i = 0; i < count; i++) {
			list.add("Item-" + i);
		}
		response.setContentType("text/html; charset=UTF-8");
		response.getWriter().append("<h1 align='center'>永久缓存：" + caches.size() + ", 运行时缓存：" + list.size() + "</h1>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
