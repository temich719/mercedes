package controller.filter;

import controller.ControllerStringsStorage;

import javax.servlet.*;
import java.io.IOException;

import static controller.ControllerStringsStorage.ENCODING;

public class EncodingFilter implements Filter {

    private static String ENCODING = "UTF-8";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String encoding = filterConfig.getInitParameter(ControllerStringsStorage.ENCODING);
        if (encoding != null) {
            ENCODING = encoding;
        }
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletRequest.setCharacterEncoding(ENCODING);
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }

}
