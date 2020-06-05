package org.dante.springboot;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dante.springboot.util.ConfigManager;

/**
 * Log4j2与Slf4j的最佳实践
 * https://zhuanlan.zhihu.com/p/36554554
 */
public class HelloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private static Logger logger = LogManager.getLogger(HelloServlet.class.getName());
	private static final ConfigManager cm = ConfigManager.getInstance("application");
	
    public HelloServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.info("Log4j2 to record log...");
		logger.warn("Log4j2 to record log...");
		logger.error("Log4j2 to record log...");
		response.setContentType("text/html; charset=UTF-8");
		response.getWriter()
			.append("<h1 align='center'>欢迎使用 CaaS 容器云！</h1>")
			.append("<h3 align='center'>".concat(cm.getPropertiesConfig("app.title")).concat("</h3>"))
			.append("<h3 align='center'>系统可用的CPU核信息: ".concat(Runtime.getRuntime().availableProcessors() + "").concat("</h3>"));
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
