package com.pers.ScheduleSystem.api.common.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pers.ScheduleSystem.api.common.respond.CommonResult;
import com.pers.ScheduleSystem.utils.auth.JwtGenerator;
import com.pers.ScheduleSystem.utils.exception.JwtException;
import com.pers.ScheduleSystem.utils.exception.NoAuthException;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <b>用户认证过滤器</b>
 *
 * @author lq
 * @version 1.0
 */
@Slf4j
@Component
public class AuthFilter extends OncePerRequestFilter {

    private static final String TRACE_ID_KEY_IN_REQUEST = "X-B3-Traceid";
    private static final String TRACE_ID_KEY_IN_LOG = "traceId";
    private static final String USER_ID_KEY_IN_LOG = "userId";
    private static final String USER_INFO_KEY_IN_HEADER = "Authorization";

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private JwtGenerator jwtGenerator;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //在日志中添加traceId和userId
        String traceId = String.valueOf(request.getHeader(TRACE_ID_KEY_IN_REQUEST));
        MDC.put(TRACE_ID_KEY_IN_LOG, traceId);
        response.addHeader(TRACE_ID_KEY_IN_REQUEST, traceId);

        //从Header中获取用户信息
        String token = request.getHeader(USER_INFO_KEY_IN_HEADER);
        try {
            Authentication auth = jwtGenerator.parseJwt(token, Authentication.class);
            SecurityContextHolder.set(auth);
        } catch (JwtException e) {
            log.info("{}: {}", e.getMessage(), e.getJwtMsg());
            //过滤器中抛出的异常是无法被全局异常处理器捕获的！所以只能手动设置返回值，不能依赖全局异常处理器。
            setAuthorizedErrorResponse(response, CommonResult.failure(e.getMessage()));
            return;
        } catch (NoAuthException e) {
            SecurityContextHolder.set(new Authentication(-1L));
        }

        //在日志中添加userId
        MDC.put(USER_ID_KEY_IN_LOG, String.valueOf(SecurityContextHolder.getUserId()));
        //打印HTTP请求入站日志
        log.info("入站请求 {} {}, 参数: {}, 用户: {}", request.getMethod(), request.getRequestURI(), request.getQueryString(), SecurityContextHolder.getUserId());

        //放行请求
        filterChain.doFilter(request, response);
        //清空上下文
        SecurityContextHolder.remove();
    }

    private void setAuthorizedErrorResponse(HttpServletResponse response, CommonResult result) throws IOException {
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.getWriter().print(objectMapper.writeValueAsString(result));
    }

}
