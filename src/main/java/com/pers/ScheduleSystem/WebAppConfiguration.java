package com.pers.ScheduleSystem;
import com.pers.ScheduleSystem.utils.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Web MVC 配置适配器
 * @ClassName: WebAppConfigurer
 * @Description:
 * @author syj
 *
 * WebAppConfigurer extends WebMvcConfigurerAdapter 在Spring Boot2.0版本已过时了，用官网说的新的类替换
 *
 */
@Configuration
public class WebAppConfiguration implements WebMvcConfigurer {
    /**
     * SpringBoot设置首页
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        WebMvcConfigurer.super.addViewControllers(registry);
        registry.addViewController("/").setViewName("index");
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/**")
                /**
                 * -->排除某一些请求不拦截,
                 *   "index.html"：登录页面不拦截，
                 *   "/"：首页不拦截
                 *   "user/login"：登录请求不拦截
                 *
                 * "/css/*"：静态资源(css,js..)：/css下的静态资源都放行，不拦截
                 */
                .excludePathPatterns("/index.jsp","/css/*","/userLogin","/js/**","/img/**");
    }
}