package edu.icet.service;

import edu.icet.dto.UserDto;

public interface UserService {

    UserDto registerUser(UserDto userDTO);

    UserDto getUserByUsername(String username);
}
