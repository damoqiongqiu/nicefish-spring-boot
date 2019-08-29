package com.nicefish;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author 大漠穷秋
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@ComponentScan("com.nicefish.*")
@EnableJpaRepositories("com.nicefish.*")
public class NiceFishCMSApplication extends SpringBootServletInitializer {

    //【不要删除】这里必须设置 CORS 相关的协议头，否则前端不能跨域过来请求
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
            registry.addMapping("/**")
                    .allowedOrigins("*")
                    .allowCredentials(true)
                    .allowedMethods("*")
                    .maxAge(Long.MAX_VALUE);
            }
        };
    }

    //【不要删除】为了打包springboot项目
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(this.getClass());
    }

    public static void main(String[] args) {
        System.setProperty("spring.devtools.restart.enabled", "true");
        SpringApplication.run(NiceFishCMSApplication.class, args);
        System.out.println("====== NiceFish Blog started ======");
    }

}
