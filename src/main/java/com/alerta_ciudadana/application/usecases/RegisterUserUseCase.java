package com.alerta_ciudadana.application.usecases;

import com.alerta_ciudadana.application.dto.RegisterUserCommand;
import com.alerta_ciudadana.domain.aggregates.User;
import com.alerta_ciudadana.domain.repository.UserRepository;
import com.alerta_ciudadana.domain.valueobjects.Role;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegisterUserUseCase {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public RegisterUserUseCase(UserRepository repository,
                               PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public User execute(RegisterUserCommand command) {

        // VALIDAR SI EL DNI YA EXISTE
        if (repository.findByDni(command.dni()).isPresent()) {
            throw new RuntimeException("El DNI ya está registrado");
        }

        String hashedPassword = passwordEncoder.encode(command.password());

        User user;

        if (command.role().equalsIgnoreCase("CITIZEN")) {
            user = User.citizen(command.dni(), command.fullName(), hashedPassword);

        } else if (command.role().equalsIgnoreCase("OFFICER")) {
            user = User.officer(command.dni(), command.fullName(), hashedPassword);

        } else {
            throw new RuntimeException("Rol inválido");
        }

        return repository.save(user);
    }
}