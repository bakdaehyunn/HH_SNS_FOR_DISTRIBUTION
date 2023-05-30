package edu.spring.ex06.interceptor;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AuthInterceptor extends HandlerInterceptorAdapter{
	private static final Logger logger = 
			LoggerFactory.getLogger(AuthInterceptor.class);
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		logger.info("===== preHandle 호출");
		boolean result =false;
		
		// 로그인 상태(세션 존재) : mapping된 url의 컨트롤러 메소드 실행
		// 로그아웃 상태(세션 없음) : login url로 이동. 목표 url 저장
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userId");
		
		if(userId == null) {
		
			logger.info("로그아웃 상태 -> controller method 실행 안됨");
			// 목표 url 세션에 저장(만약 request parameter가 존재하면 같이 저
			response.setContentType("text/html; charset=utf-8");
			
			if(isAjaxRequest(request)) {
				logger.info("ajax request 이다.");
				saveAjaxDestination(request);
				response.sendError(400);
				return false;
			}
			else {
				
				logger.info("ajax request 아니다.");
				saveDestination(request);
				PrintWriter out = response.getWriter();
				out.print("<script>alert('로그인이 필요합니다. !'); location.href='/ex06/user/login';</script>");
				out.flush();
				out.close();
				result = false;
			}
			
		} else {
			logger.info("로그인 상태 -> controller method 실행");
			result = true;
		}
		return result;
	}
	private boolean isAjaxRequest(HttpServletRequest req) {
        String header = req.getHeader("AJAX");
        if ("true".equals(header)){
         return true;
        }else{
         return false;
        }
    }

	private void saveDestination(HttpServletRequest request) {
		logger.info("saveDestination() 호출");
		
		String uri = request.getRequestURI();
		logger.info("요청 URI : " + uri);
		
		String contextRoot = request.getContextPath();
		logger.info("contextRoot : " + contextRoot);
		uri = uri.replace(contextRoot, "");
		logger.info("요청 URI : " + uri);
		
		String queryString = request.getQueryString();
		logger.info("쿼리 스트링 : " + queryString);
		
		String targetURL ="";
		if(queryString == null) {
			targetURL = uri;
		} else {
			targetURL = uri + "?"  + queryString;
		}
		logger.info("targetURL : " + targetURL);
		request.getSession().setAttribute("targetURL", targetURL);
		
	}
	
	private void saveAjaxDestination(HttpServletRequest request) {
		logger.info("saveAjaxDestination() 호출");
		
		String queryString = request.getQueryString();
		logger.info("쿼리 스트링 : " + queryString);
		
		String targetURL = "feed/mylist" + "?"  + queryString;
		
		logger.info("targetURL : " + targetURL);
		request.getSession().setAttribute("targetURL", targetURL);
		
	}
	
	
}
