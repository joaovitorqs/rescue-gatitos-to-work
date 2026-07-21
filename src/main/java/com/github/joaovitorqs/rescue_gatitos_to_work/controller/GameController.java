package com.github.joaovitorqs.rescue_gatitos_to_work.controller;

import com.github.joaovitorqs.rescue_gatitos_to_work.dto.GameStateDTO;
import com.github.joaovitorqs.rescue_gatitos_to_work.model.User;
import com.github.joaovitorqs.rescue_gatitos_to_work.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/game")
@RequiredArgsConstructor
public class GameController {

    private final UserRepository userRepository;

    @GetMapping("/state")
    public ResponseEntity<GameStateDTO> getState(@AuthenticationPrincipal User user){
        return ResponseEntity.ok(new GameStateDTO(
                user.getQtdGatitos(),
                user.getDinheiro(),
                user.getLevelClick(),
                user.getLevelAutoClick()));
    }

    @PutMapping("/state")
    public ResponseEntity<Void> saveState(@AuthenticationPrincipal User user, @RequestBody GameStateDTO dto){
        user.setQtdGatitos(dto.qtdGatitos());
        user.setDinheiro(dto.dinheiro());
        user.setLevelClick(dto.levelClick());
        user.setLevelAutoClick(dto.levelAutoClick());
        userRepository.save(user);
        return ResponseEntity.noContent().build();
    }
}
