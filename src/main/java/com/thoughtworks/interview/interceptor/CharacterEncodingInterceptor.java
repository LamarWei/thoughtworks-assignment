package com.thoughtworks.interview.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * Character encoding interceptor
 * @author WeiWei
 *
 */
public class CharacterEncodingInterceptor implements HandlerInterceptor{
	
	private String characterEncoding = "utf-8";

	public String getCharacterEncoding() {
		return characterEncoding;
	}

	public void setCharacterEncoding(String characterEncoding) {
		this.characterEncoding = characterEncoding;
	}

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object object)
			throws Exception {
		request.setCharacterEncoding(characterEncoding);
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest paramHttpServletRequest,
			HttpServletResponse paramHttpServletResponse, Object paramObject,
			ModelAndView paramModelAndView) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterCompletion(HttpServletRequest paramHttpServletRequest,
			HttpServletResponse paramHttpServletResponse, Object paramObject,
			Exception paramException) throws Exception {
		// TODO Auto-generated method stub
		
	}
	

}
