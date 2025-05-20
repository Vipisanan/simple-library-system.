package com.example.library.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BorrowerDto {

    @NotBlank
    private Long id;

    @NotBlank
    private String name;

    @Email
    @NotBlank
    private String email;
}

