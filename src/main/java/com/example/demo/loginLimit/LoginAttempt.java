package com.example.demo.loginLimit;

import java.time.LocalDateTime;

public class LoginAttempt {

    private String username;
    private int tentativas;
    private LocalDateTime lastError;

    public LoginAttempt(String username) {
        this.username = username;
        this.tentativas = 1;
        this.lastError = LocalDateTime.now();
    }

    public void incrementTentativas() {
        this.tentativas++;
        this.lastError = LocalDateTime.now();
    }

    public long getDelaySegundos() {
        return this.tentativas * 2L;
    }

    public boolean canTry() {
        return LocalDateTime.now().isAfter(lastError.plusSeconds(getDelaySegundos()));
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getTentativas() {
        return tentativas;
    }

    public void setTentativas(int tentativas) {
        this.tentativas = tentativas;
    }

    public LocalDateTime getLastError() {
        return lastError;
    }

    public void setLastError(LocalDateTime lastError) {
        this.lastError = lastError;
    }
}
