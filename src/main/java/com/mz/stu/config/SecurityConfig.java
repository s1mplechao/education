package com.mz.stu.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)//开启细粒度控制
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    MyAuthenctiationSuccessHandler myAuthenctiationSuccessHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        .authorizeRequests()//HttpServletRequest请求认证
                            //放行路径 不需要认证
        .antMatchers(
                "/static/upload/**",
                "/static/**",
                "/goRegPage",
                "/file/uploadFile",
                "/user/regSaveUser")
                .permitAll()
                .anyRequest().authenticated()
        .and()
        .formLogin() //form表单登录方式
        .and()
        .csrf().disable() //关闭CSRF
        .formLogin().loginPage("/login") //表示登录时候 跳转的页面
        .loginProcessingUrl("/form") //form表单登录请求
        .successHandler(myAuthenctiationSuccessHandler)
        .failureUrl("/loginError").permitAll();//失败错误跳转

        //注销功能
        http.logout().logoutSuccessUrl("/login")
                .invalidateHttpSession(true);

        //权限不够返回处理
        http.exceptionHandling().accessDeniedHandler(new AccessDeniedHandler() {
            @Override
            public void handle(HttpServletRequest req, HttpServletResponse resp,
                               AccessDeniedException e) throws IOException, ServletException {
                String type = req.getHeader("X-Requested-With");
                if("XMLHttpRequest".equals(type)){
                    resp.getWriter().print("403");
                }else{
                    req.getRequestDispatcher("/error403").forward(req,resp);
                }
            }
        });
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        //基于内存方式登录
        //        auth.inMemoryAuthentication()
        //                .passwordEncoder(new BCryptPasswordEncoder())
        //                .withUser("admin")
        //                .password(new BCryptPasswordEncoder()
        //                        .encode("123456")).roles("USER");
        //基于数据库方式登录
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(new BCryptPasswordEncoder());

    }
}
