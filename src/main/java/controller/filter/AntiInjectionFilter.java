package controller.filter;

import javax.servlet.*;
import java.io.IOException;
import java.util.Map;

public class AntiInjectionFilter implements Filter {

    private static final String DOES_NOT_CONTAIN = "^((?!<|>|script).)*$";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        StringBuilder stringBuilder = new StringBuilder();
        Map<String, String[]> parameters = servletRequest.getParameterMap();
        for (String[] v: parameters.values()){
            stringBuilder.append(v[0]);
        }
        if (stringBuilder.toString().trim().matches(DOES_NOT_CONTAIN))
            filterChain.doFilter(servletRequest, servletResponse);
        else servletRequest.getRequestDispatcher("antiInjection.jsp").forward(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }

}
