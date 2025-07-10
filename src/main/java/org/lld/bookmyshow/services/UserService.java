package org.lld.bookmyshow.services;

import org.lld.bookmyshow.models.User;

public interface UserService {
    public User registerUser(String name, String email);
}
