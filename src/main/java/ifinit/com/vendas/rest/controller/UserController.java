package ifinit.com.vendas.rest.controller;

import ifinit.com.vendas.domain.entity.User;
import ifinit.com.vendas.exception.InvalidPasswordException;
import ifinit.com.vendas.rest.dto.CredentialsDTO;
import ifinit.com.vendas.rest.dto.TokenDTO;
import ifinit.com.vendas.security.jwt.JwtService;
import ifinit.com.vendas.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserServiceImpl userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public User save(@RequestBody @Valid User user){
        String passwordEncrypted = passwordEncoder.encode(user.getPassword());
        user.setPassword(passwordEncrypted);
    return userService.save(user);
    }

    @PostMapping("/auth")
    public TokenDTO authentic(@RequestBody CredentialsDTO dto){
    try {
      User userDetailsAutentic = User
                .builder()
                .login(dto.getLogin())
                .password(dto.getPassword()).build();

      String token = jwtService.generateToken(userDetailsAutentic);
      return new TokenDTO(userDetailsAutentic.getLogin() ,token);

    } catch (UsernameNotFoundException  | InvalidPasswordException e){
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
    }
    }
}
