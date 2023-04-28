package com.example.springredditclone.service;

import com.example.springredditclone.exception.InvalidRefreshTokenException;
import com.example.springredditclone.model.RefreshToken;
import com.example.springredditclone.model.User;
import com.example.springredditclone.repository.RefreshTokenRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
@AllArgsConstructor
public class RefreshTokenService {
    private final RefreshTokenRepository refreshTokenRepository;

    @Transactional
    public RefreshToken generateRefreshToken(User user) {
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setToken(UUID.randomUUID().toString());
        refreshToken.setCreated(Instant.now());
        refreshToken.setUser(user);
        return refreshTokenRepository.save(refreshToken);
    }

    @Transactional
    public RefreshToken validateRefreshToken(String token) throws InvalidRefreshTokenException {
        return refreshTokenRepository.findByToken(token).orElseThrow(InvalidRefreshTokenException::new);
    }

    @Transactional
    public void deleteRefreshToken(String token) {
        refreshTokenRepository.deleteByToken(token);
    }
}
