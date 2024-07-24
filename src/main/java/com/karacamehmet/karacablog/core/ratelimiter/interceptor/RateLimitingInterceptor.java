package com.karacamehmet.karacablog.core.ratelimiter.interceptor;

import com.karacamehmet.karacablog.core.ratelimiter.service.IpRateLimiterService;
import io.github.resilience4j.ratelimiter.RateLimiter;
import io.github.resilience4j.ratelimiter.RequestNotPermitted;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@RequiredArgsConstructor
public class RateLimitingInterceptor implements HandlerInterceptor {
    private final IpRateLimiterService ipRateLimiterService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws RequestNotPermitted {
        String ipAddress = request.getRemoteAddr();
        RateLimiter rateLimiter=ipRateLimiterService.rateLimiterCreator(ipAddress);
        if (!rateLimiter.acquirePermission()){
            throw RequestNotPermitted.createRequestNotPermitted(rateLimiter);
        }
        return true;
    }
}
