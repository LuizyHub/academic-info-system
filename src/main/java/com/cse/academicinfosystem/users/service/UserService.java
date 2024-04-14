package com.cse.academicinfosystem.users.service;

import com.cse.academicinfosystem.users.domain.User;
import com.cse.academicinfosystem.users.dto.UserAddDto;
import com.cse.academicinfosystem.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public boolean isUsernameExist(String username) {
        return userRepository.existsByUsername(username);
    }

    public void addUser(UserAddDto userAddDto) {
        User user = new User();
        user.setUsername(userAddDto.getUsername());
        user.setPassword(userAddDto.getPassword());
        userRepository.save(user);
    }

}
