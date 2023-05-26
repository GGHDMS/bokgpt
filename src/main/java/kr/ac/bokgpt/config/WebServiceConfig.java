package kr.ac.bokgpt.config;

import kr.ac.bokgpt.interceptor.CommentAuthInterceptor;
import kr.ac.bokgpt.interceptor.PostAuthInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
@RequiredArgsConstructor
public class WebServiceConfig implements WebMvcConfigurer {

    private final CommentAuthInterceptor commentAuthInterceptor;
    private final PostAuthInterceptor postAuthInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(postAuthInterceptor)
                .addPathPatterns("posts/**")
                .excludePathPatterns("/posts/**/comments/**");

        registry.addInterceptor(commentAuthInterceptor)
                .addPathPatterns("/posts/**/comments/**");
    }
}
