package com.example.eventnotificator.service;

import com.example.eventnotificator.domain.Role;
import com.example.eventnotificator.domain.User;
import com.example.eventnotificator.entity.UserEntity;
import com.example.eventnotificator.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User findUserByLogin(String login) {
        UserEntity userEntity = userRepository.findByLogin(login)
                .orElseThrow(() -> new EntityNotFoundException("User with login: %s not founded".formatted(login)));

        return new User(
                userEntity.getId(),
                userEntity.getLogin(),
                userEntity.getAge(),
                Role.valueOf(userEntity.getRole()),
                userEntity.getPassword()
        );
    }
}
