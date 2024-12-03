package com.study8.mini.rest.v1.res;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RegisterRes {
    private Long userId;
    private String email;
}
