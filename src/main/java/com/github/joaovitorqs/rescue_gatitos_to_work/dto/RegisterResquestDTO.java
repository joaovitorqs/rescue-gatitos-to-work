package com.github.joaovitorqs.rescue_gatitos_to_work.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegisterResquestDTO(@NotBlank String nickName,
                                  @NotBlank @Email String email,
                                  @NotBlank @Size(min = 6, max = 100) String password) {
}
