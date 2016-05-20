package com.lzjf.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * session超时拦截器
 * 
 * @author ydd676
 */
public class SessionTimeoutInterceptor extends HandlerInterceptorAdapter {

	/**
	 * 允许通过路径集合
	 */
	private List<String> allowUrls;

	public List<String> getAllowUrls() {

		return allowUrls;
	}

	public void setAllowUrls(List<String> allowUrls) {

		this.allowUrls = allowUrls;
	}

	/**
	 * Session超时，拦截访问
	 */
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {

		/*String requestUrl = request.getRequestURI();

		for (String url : allowUrls) {
			if (Pattern.matches(url, requestUrl)) {
				return true;
			}
		}

		HttpSession session = request.getSession();
		System.out.println("$$$$$session=" + session);
		System.out.println("$$$$$session=" + session.getId());
		// if (session.getAttribute("123") == null) {
		if (session.getAttribute(session.getId()) == null) {
			if (request.getHeader("x-requested-with") != null
					&& request.getHeader("x-requested-with").equalsIgnoreCase(
							"XMLHttpRequest")) {

				response.setHeader("sessionstatus", "timeout");
				response.setHeader("redirectUrl", request.getContextPath()
						+ "/jsp/error/timeoutError.jsp");
				PrintWriter writer = response.getWriter();
				writer.append("{sessionstatus:timeout,redirectUrl:"
						+ request.getContextPath()
						+ "/jsp/error/timeoutError.jsp" + "}");
				return false;
			} else {
				throw new SessionTimeoutException();
			}
		}*/

		return true;
	}
}
