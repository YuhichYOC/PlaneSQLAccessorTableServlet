package test07.eureka3.com;

import java.io.IOException;

import javax.servlet.*;

public class EncodingFilter implements Filter {
	
	private String encoding = "";
	
	@Override
	public void init(FilterConfig config) throws ServletException {
		
		this.encoding = config.getInitParameter("encoding");
		
	}
	
	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		
		req.setCharacterEncoding(this.encoding);
		
		chain.doFilter(req, res);
		
	}
	
	@Override
	public void destroy() {
	}
	
}
