

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FilterAutorisation extends HttpFilter implements Filter {
	private static final long serialVersionUID = 1L;

    public FilterAutorisation() {
        super();
    }

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		System.out.println("in filter");
		String nom=null;
		HttpServletRequest hrequest=(HttpServletRequest) request;
		HttpServletResponse hresponse=(HttpServletResponse) response;
		Cookie[] cookies=hrequest.getCookies();
		 if(cookies!=null) {
        	 for (Cookie cookie : cookies) {
                 if (cookie.getName().equals("nom")) {
                     nom = cookie.getValue();
                 } 
             }
        }
		 if(nom==null) {
	            hresponse.sendRedirect("InscriptionClient");
		 }
		 else {
			 chain.doFilter(hrequest, hresponse);
		 }
	}

	public void init(FilterConfig fConfig) throws ServletException {
		}

}
