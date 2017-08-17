package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import utils.CharsetHttpServletRequest;

public class CharsetFilter implements Filter {

	public void init(FilterConfig filterConfig) throws ServletException {

	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		CharsetHttpServletRequest charsetHttpServletRequest = new CharsetHttpServletRequest(
				httpServletRequest);

		chain.doFilter(charsetHttpServletRequest, response);
	}

	public void destroy() {

	}
}
