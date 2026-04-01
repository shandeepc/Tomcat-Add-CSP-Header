package dev.shandeep.tomcat.filters;

import javax.servlet.Filter;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

public class AddCSPFilter implements Filter {

    public static final String POLICY = "default-src 'self' 'unsafe-inline' 'unsafe-eval'; connect-src 'self' https://get.geojs.io; img-src 'self' data: https://*.tile.openstreetmap.org";

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        if (response instanceof HttpServletResponse) {
            ((HttpServletResponse) response).setHeader("Content-Security-Policy", AddCSPFilter.POLICY);
        }
        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void destroy() {
    }
}

// <filter>
// <filter-name>AddCSPFilter</filter-name>
// <filter-class>dev.shandeep.tomcat.filters.AddCSPFilter</filter-class>
// </filter>

// <filter-mapping>
// <filter-name>AddCSPFilter</filter-name>
// <url-pattern>/*</url-pattern>
// </filter-mapping>
