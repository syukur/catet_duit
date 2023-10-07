package com.lylastudio.catetduit.filter;

import com.lylastudio.catetduit.db.entity.TOneTimeAccess;
import com.lylastudio.catetduit.db.repository.TOneTimeAccessRepository;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

@Slf4j
public class WebFilter implements Filter {

    private final TOneTimeAccessRepository tOneTimeAccessRepository;

    public WebFilter(
            TOneTimeAccessRepository tOneTimeAccessRepository
    ){
        this.tOneTimeAccessRepository = tOneTimeAccessRepository;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {

       HttpServletRequest request = (HttpServletRequest) servletRequest;
       HttpServletResponse response = (HttpServletResponse) servletResponse;

      log.info("method: {}", request.getMethod() );
//
//       String requestParameter = request.getQueryString();
//
//      String signature = "";
//      String[] arrOfParam = requestParameter.split("&");
//      for ( String param : arrOfParam ){
//          String[] arrOfKeyValue = param.split("=");
//          String key = arrOfKeyValue[0];
//          if(key.equals("r")){
//             signature =  arrOfKeyValue[1];
//          }
//      }
//
//      log.info("signature: {}", signature);
//
//      TOneTimeAccess access = tOneTimeAccessRepository.findBySignature(signature);
//
//      if(access == null){
//          log.info("UNAUTHORIZED: access-token-not-found");
//          response.sendError(HttpServletResponse.SC_UNAUTHORIZED,"UNAUTHORIZED");
//        return;
//      }else{
//
//        Date now = new Date();
//
//        if ( now.after(access.getExpired()) ){
//            log.info("UNAUTHORIZED: link-expired.");
//            response.sendError(HttpServletResponse.SC_UNAUTHORIZED,"UNAUTHORIZED");
//        }
//
//      }
//

      chain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
