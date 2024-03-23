package ifinit.com.vendas.rest.controller;

import ifinit.com.vendas.domain.entity.User;
import ifinit.com.vendas.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserServiceImpl userService;
    private final PasswordEncoder passwordEncoder;


    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public User save(@RequestBody @Valid User user){
        String passwordEncrypted = passwordEncoder.encode(user.getPassword());
        user.setPassword(passwordEncrypted);
    return userService.save(user);
    }
}
