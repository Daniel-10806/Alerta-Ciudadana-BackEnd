package com.alerta_ciudadana.application.usecases;

import com.alerta_ciudadana.application.dto.LoginCommand;
import com.alerta_ciudadana.domain.repository.UserRepository;
import com.alerta_ciudadana.infrastructure.security.JwtTokenProvider;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginUseCase {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    public LoginUseCase(UserRepository userRepository,
                        PasswordEncoder passwordEncoder,
                        JwtTokenProvider jwtTokenProvider) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    public String execute(LoginCommand command) {

        var user = userRepository.findByDni(command.dni())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (!passwordEncoder.matches(command.password(), user.getPasswordHash())) {
            throw new RuntimeException("Credenciales inválidas");
        }

        return jwtTokenProvider.generateToken(
                user.getDni(),
                user.getRole().name()
        );
    }
}