package br.com.spread.qualicorp.coparticipacao.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
public class RequestFilter implements Filter {

	private static final Logger LOGGER = LogManager.getLogger(RequestFilter.class);

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		LOGGER.info("BEGIN");

		HttpServletResponse response = (HttpServletResponse) servletRequest;

		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "GET,POST");
		response.setHeader("Access-Control-Max-Age", "360");
		response.setHeader("Access-Control-Allow-Headers", "x-requested-with");

		filterChain.doFilter(servletRequest, servletResponse);

		LOGGER.info("END");
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

	}

}
