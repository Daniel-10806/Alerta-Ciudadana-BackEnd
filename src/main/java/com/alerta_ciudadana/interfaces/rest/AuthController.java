package com.alerta_ciudadana.interfaces.rest;

import com.alerta_ciudadana.application.dto.LoginCommand;
import com.alerta_ciudadana.application.dto.RegisterUserCommand;
import com.alerta_ciudadana.application.usecases.LoginUseCase;
import com.alerta_ciudadana.application.usecases.RegisterUserUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final RegisterUserUseCase registerUserUseCase;
    private final LoginUseCase loginUseCase;

    public AuthController(RegisterUserUseCase registerUserUseCase, LoginUseCase loginUseCase) {
        this.registerUserUseCase = registerUserUseCase;
        this.loginUseCase = loginUseCase;
    }

    // -------------------------------------------------------------------------
    @Operation(
            summary = "Registrar usuario",
            description = "Crear un ciudadano u oficial.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(
                            examples = @ExampleObject(
                                    value = """
                                    {
                                      "fullName": "Juan Perez",
                                      "dni": "12345678",
                                      "password": "123456",
                                      "role": "CITIZEN"
                                    }
                                    """
                            )
                    )
            )
    )
    @PostMapping("/register")
    public String register(@RequestBody RegisterUserCommand command) {
        registerUserUseCase.execute(command);
        return "Usuario registrado";
    }

    // -------------------------------------------------------------------------
    @Operation(
            summary = "Inicio de sesión",
            description = "Devuelve un token JWT para usarlo en los demás endpoints.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(
                            examples = @ExampleObject(
                                    value = """
                                    {
                                      "dni": "12345678",
                                      "password": "123456"
                                    }
                                    """
                            )
                    )
            )
    )
    @PostMapping("/login")
    public String login(@RequestBody LoginCommand command) {
        return loginUseCase.execute(command);
    }
}