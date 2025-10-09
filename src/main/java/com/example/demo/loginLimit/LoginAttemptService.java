package com.example.demo.loginLimit;

import com.example.demo.configuration.CacheConfig;
import com.github.benmanes.caffeine.cache.Cache;
import org.springframework.stereotype.Service;

@Service
public class LoginAttemptService {

    private final Cache<String, LoginAttempt> loginAttemptCache;

    public LoginAttemptService(Cache<String, LoginAttempt> loginAttemptCacheConfig, Cache<String, LoginAttempt> loginAttemptCache) {
        this.loginAttemptCache = loginAttemptCache;
    }

    public long getDelaySeconds(String username) {
        return loginAttemptCache.getIfPresent(username).getDelaySegundos();
    }

    public boolean podeTentar(String username) {
        LoginAttempt loginAttempt = loginAttemptCache.getIfPresent(username);
        return (loginAttempt == null || loginAttempt.canTry());
    }

    public void registerFailure(String username) {
        LoginAttempt loginAttempt = loginAttemptCache.getIfPresent(username);
        if (loginAttempt == null) {
            loginAttempt = new LoginAttempt(username);
        } else {
            loginAttempt.incrementTentativas();
        }
        loginAttemptCache.put(username, loginAttempt);

    }

    public void reset(String username) {
        loginAttemptCache.invalidate(username);
    }


}
