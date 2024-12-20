package com.study8.mini.rest.res;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RegisterRes {
    @Schema(description = "User Id", example = "1")
    private Long userId;

    @Schema(description = "Email", example = "it@study8.com")
    private String email;
}
