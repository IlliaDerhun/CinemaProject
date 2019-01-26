package cinemaproject.illiaderhun.com.github.filters;

import org.apache.log4j.Logger;

import javax.servlet.*;
import java.io.IOException;

public class EncodingFilter implements Filter {

    private static final Logger LOGGER = Logger.getLogger(EncodingFilter.class.getSimpleName());
    private String code;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        code = filterConfig.getInitParameter("encoding");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        LOGGER.info("EncodingFilter start");
        String codeRequest = request.getCharacterEncoding();
        if (code != null && !code.equalsIgnoreCase(codeRequest)) {
            request.setCharacterEncoding(code);
            response.setCharacterEncoding(code);
        }

        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        code = null;
    }
}
