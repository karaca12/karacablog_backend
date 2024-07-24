package com.karacamehmet.karacablog.core.ratelimiter.service;

import io.github.resilience4j.ratelimiter.RateLimiter;
import io.github.resilience4j.ratelimiter.RateLimiterConfig;
import io.github.resilience4j.ratelimiter.RateLimiterRegistry;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.concurrent.ConcurrentHashMap;

@Service
@RequiredArgsConstructor
public class IpRateLimiterService {
    private final RateLimiterRegistry rateLimiterRegistry;
    private final ConcurrentHashMap<String, RateLimiter> rateLimiterCache = new ConcurrentHashMap<>();


    public RateLimiter rateLimiterCreator(String ipAddress) {
        return rateLimiterCache.computeIfAbsent(ipAddress, this::createRateLimiter);
    }

    private RateLimiter createRateLimiter(String ipAddress) {
        RateLimiterConfig config = RateLimiterConfig.custom()
                .limitForPeriod(10)
                .limitRefreshPeriod(Duration.ofMinutes(1))
                .timeoutDuration(Duration.ZERO)
                .build();
        return rateLimiterRegistry.rateLimiter(ipAddress, config);
    }
}
