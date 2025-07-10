package org.lld.bookmyshow.dto;

import lombok.Data;

@Data
public class SignUpResponseDto {
    private long userId;
    private ResponseStatus responseStatus;
    private String failureMessage;
}
