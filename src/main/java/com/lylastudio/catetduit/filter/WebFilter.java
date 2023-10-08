package com.lylastudio.catetduit.filter;

import com.lylastudio.catetduit.db.entity.TOneTimeAccess;
import com.lylastudio.catetduit.db.repository.TOneTimeAccessRepository;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.http.HttpRequest;
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

       String requestMethod = request.getMethod();

       String signature ="";
       if("GET".equals(requestMethod)){
            signature = getSignature(request);
       }else if ("POST".equals(requestMethod)){
           signature = postSignature(request);
       }

       if( ("".equals(signature)) || (null == signature) ){
           log.info("BAD REQUEST: signature-not-send");
           response.sendError(HttpServletResponse.SC_BAD_REQUEST,"BAD REQUEST");
           return;
       }

       if (!validateSignature(signature)){
           response.sendError(HttpServletResponse.SC_UNAUTHORIZED,"BAD REQUEST");
           return;
       }

       chain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }

    private String getSignature(HttpServletRequest request){

        String requestParameter = request.getQueryString();

        String signature = "";
        String[] arrOfParam = requestParameter.split("&");
        for ( String param : arrOfParam ){
            String[] arrOfKeyValue = param.split("=");
            String key = arrOfKeyValue[0];
            if(key.equals("r")){
                signature =  arrOfKeyValue[1];
            }
        }

        return signature;

    }

    private String postSignature(HttpServletRequest request){
        return request.getParameter("token");
    }

    private boolean validateSignature(String signature){

        TOneTimeAccess access = tOneTimeAccessRepository.findBySignature(signature);

        boolean result = true;

        if(access == null){
            log.info("UNAUTHORIZED: signature-not-found");
            result = false;
        }else{

            Date now = new Date();

            if ( now.after(access.getExpired()) ){
                log.info("UNAUTHORIZED: signature-expired.");
                result = false;
            }
        }

        return  result;
    }
}
