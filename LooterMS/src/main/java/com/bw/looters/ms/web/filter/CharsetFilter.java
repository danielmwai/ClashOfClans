package com.bw.looters.ms.web.filter;

import java.io.IOException;
import java.nio.charset.Charset;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 编码过滤器, 对请求和相应的编码进行过滤
 *
 * @author zhouyou
 */
public class CharsetFilter implements Filter {

	private static final Logger logger = LoggerFactory.getLogger(CharsetFilter.class);
	private static String charsetName;

	public void init(FilterConfig filterConfig) throws ServletException {
		String charsetName = filterConfig.getInitParameter("charset");
		try {
			if (Charset.forName(charsetName) != null) {
				CharsetFilter.charsetName = charsetName;
			}
		} catch (Exception e) {
			logger.error("charset not found: " + charsetName, e);
			CharsetFilter.charsetName = null;
		}
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		if (CharsetFilter.charsetName != null) {
			request.setCharacterEncoding(CharsetFilter.charsetName);
			response.setCharacterEncoding(CharsetFilter.charsetName);
			chain.doFilter(request, response);
		}
	}

	public void destroy() {
	}
}
