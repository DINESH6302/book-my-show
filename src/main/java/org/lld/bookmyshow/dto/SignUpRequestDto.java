package org.lld.bookmyshow.dto;

import lombok.Data;

@Data
public class SignUpRequestDto {
    private String name;
    private String email;
    private String phoneNumber;
}
