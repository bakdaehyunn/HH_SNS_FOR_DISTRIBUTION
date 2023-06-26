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
		
		if(userId == null) { // 로그인 상태가 아닐 경우
		
			logger.info("로그아웃 상태 -> controller method 실행 안됨");
			response.setContentType("text/html; charset=utf-8");// utf-8로 인코딩 설정
			
			if(isAjaxRequest(request)) { //Ajax 요청일 경우
				logger.info("ajax request 이다.");
				saveAjaxDestination(request); //접근하려던 경로 세션으로 저장
				response.sendError(400); //400 에러 전송
				return false; // 더 이상 진행 안됌
			}
			else { // Ajax 요청이 아닐 경우
				logger.info("ajax request 아니다.");
				saveDestination(request); // 접근하려던 경로 세션으로 저장
				PrintWriter out = response.getWriter();
				out.print("<script>alert('로그인이 필요합니다. !'); location.href='/ex06/user/login';</script>"); // 로그인 요청 알람 과 로그인화면으로 redirect
				out.flush();
				out.close();
				result = false; // 더 이상 진행 안됌
			}
			
		} else {
			logger.info("로그인 상태 -> controller method 실행");
			result = true; // 정상적으로 진행
		}
		return result;
	}
	private boolean isAjaxRequest(HttpServletRequest req) {
        String header = req.getHeader("AJAX");
        if ("true".equals(header)){ //AJAX가 true일 경우
         return true; //true를 전달
        }else{
         return false;// false를 전달
        }
    }

	private void saveDestination(HttpServletRequest request) {
		logger.info("saveDestination() 호출");
		
		String uri = request.getRequestURI(); //경로 불러오기
		logger.info("요청 URI : " + uri);
		
		String contextRoot = request.getContextPath();
		logger.info("contextRoot : " + contextRoot);
		uri = uri.replace(contextRoot, ""); // 경로에서 contextRoot 제거 
		logger.info("요청 URI : " + uri);
		
		String queryString = request.getQueryString();
		logger.info("쿼리 스트링 : " + queryString); // 쿼리스트링 불러오기
		
		String targetURL ="";
		if(queryString == null) { //쿼리 스트링이 없을 경우
			targetURL = uri; //경로 저장
		} else {
			targetURL = uri + "?"  + queryString;//쿼리 스트링을 추가하여 경로 저장
		}
		logger.info("targetURL : " + targetURL);
		request.getSession().setAttribute("targetURL", targetURL);// 세션에 경로 생성
		
	}
	
	private void saveAjaxDestination(HttpServletRequest request) {
		logger.info("saveAjaxDestination() 호출");
		String queryString = request.getQueryString(); //쿼리 스트링을 저장
		logger.info("쿼리 스트링 : " + queryString);
		String targetURL = "feed/mylist" + "?"  + queryString; // 경로 저장
		logger.info("targetURL : " + targetURL); 
		request.getSession().setAttribute("targetURL", targetURL); //세션에 경로 생성
		
	}
	
	
}
