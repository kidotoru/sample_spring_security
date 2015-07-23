package net.treewoods.sample.spring.security;

import java.io.IOException;
import org.springframework.web.filter.GenericFilterBean;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class MyFilter extends GenericFilterBean {

	
	private static int cnt = 1;
	@Override
	public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain)
			throws IOException, ServletException {

		final HttpServletRequest httpRequest = (HttpServletRequest) request;
		final HttpServletResponse httpResponse = (HttpServletResponse) response;

		System.out.println("MyFillter." + cnt);
		
		cnt++;
		
		if ( cnt >= 10) {
			cnt = 1;
			HttpSession session = httpRequest.getSession(false);
			session.invalidate();

		}
		
		chain.doFilter(request, response);
	}
}
