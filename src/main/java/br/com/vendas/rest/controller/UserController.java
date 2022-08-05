package br.com.vendas.rest.controller;

import br.com.vendas.domain.entity.AuthUser;
import br.com.vendas.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/usuario")
@RequiredArgsConstructor
public class UserController {


    private final UserServiceImpl usuarioService;

    private final PasswordEncoder passwordEncoder;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AuthUser salve( @RequestBody @Valid AuthUser user ){
        String encryptedPassword = passwordEncoder.encode(user.getSenha());
        user.setSenha(encryptedPassword);
        return usuarioService.salvar(user);
    }
}
