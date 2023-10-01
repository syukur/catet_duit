package com.lylastudio.catetduit.config;

import com.lylastudio.catetduit.db.repository.TOneTimeAccessRepository;
import com.lylastudio.catetduit.filter.WebFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    private final TOneTimeAccessRepository tOneTimeAccessRepository;

    public FilterConfig(TOneTimeAccessRepository tOneTimeAccessRepository){
        this.tOneTimeAccessRepository = tOneTimeAccessRepository;
    }

    @Bean
    public FilterRegistrationBean<WebFilter> myFilter() {
        FilterRegistrationBean<WebFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new WebFilter(tOneTimeAccessRepository));
        registrationBean.addUrlPatterns("/web/v1/*"); // URL patterns to filter
        return registrationBean;
    }
}
