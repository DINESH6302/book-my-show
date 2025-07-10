package org.lld.bookmyshow.controllers;

import org.lld.bookmyshow.dto.ResponseStatus;
import org.lld.bookmyshow.dto.SignUpRequestDto;
import org.lld.bookmyshow.dto.SignUpResponseDto;
import org.lld.bookmyshow.models.User;
import org.lld.bookmyshow.services.UserServiceImpl;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {
    private final UserServiceImpl userServiceImpl;

    public UserController(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    public SignUpResponseDto registerUser(SignUpRequestDto signUpRequestDto) {
        SignUpResponseDto signUpResponseDto = new SignUpResponseDto();
        try {
            User user = userServiceImpl.registerUser(signUpRequestDto.getName(), signUpRequestDto.getEmail());

            signUpResponseDto.setUserId(user.getId());
            signUpResponseDto.setResponseStatus(ResponseStatus.SUCCESS);
        } catch (Exception e) {
            signUpResponseDto.setResponseStatus(ResponseStatus.FAILURE);
            signUpResponseDto.setFailureMessage("Error registering user: " + e.getMessage());
        }

        return signUpResponseDto;
    }
}
