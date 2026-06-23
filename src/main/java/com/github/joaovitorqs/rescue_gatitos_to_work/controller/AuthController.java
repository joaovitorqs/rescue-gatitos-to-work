package com.github.joaovitorqs.rescue_gatitos_to_work.controller;

import com.github.joaovitorqs.rescue_gatitos_to_work.dto.LoginRequestDTO;
import com.github.joaovitorqs.rescue_gatitos_to_work.dto.LoginResponseDTO;
import com.github.joaovitorqs.rescue_gatitos_to_work.dto.RegisterResquestDTO;
import com.github.joaovitorqs.rescue_gatitos_to_work.model.User;
import com.github.joaovitorqs.rescue_gatitos_to_work.repository.UserRepository;
import com.github.joaovitorqs.rescue_gatitos_to_work.security.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginRequestDTO body){
        User user = this.userRepository.findByEmail(body.email()).orElseThrow(() -> new RuntimeException("User not found"));
        if (passwordEncoder.matches(body.password(), user.getPassword())){
            String token = this.tokenService.generateToken(user);
            return ResponseEntity.ok(new LoginResponseDTO(user.getNickName(), token));
        } else {
            return ResponseEntity.status(401).body("Invalid credentials");
        }
    }

    @PostMapping("/register")
    public ResponseEntity register (@RequestBody RegisterResquestDTO body){
        Optional<User> user = this.userRepository.findByEmail(body.email());

        if(user.isEmpty()){
            User newUser = new User();
            newUser.setEmail(body.email());
            newUser.setPassword(passwordEncoder.encode(body.password()));
            newUser.setNickName(body.nickName());
            this.userRepository.save(newUser);

            String token = this.tokenService.generateToken(newUser);
            return ResponseEntity.ok(new LoginResponseDTO(newUser.getNickName(), token));
        } else {
            return ResponseEntity.status(400).body("Email already in use");
        }
    }
}
