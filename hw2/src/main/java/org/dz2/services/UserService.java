package org.dz2.services;

import org.dz2.entities.Role;
import org.dz2.entities.User;
import org.dz2.repositories.UsersRepository;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    private final UsersRepository usersRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
        this.bCryptPasswordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            return usersRepository.findByUsername(username);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public User findUserById(Integer userId) {
        Optional<User> userFromDb = usersRepository.findById(userId);
        return userFromDb.orElse(null);
    }

    public List<User> getAllUsers() {
        return usersRepository.findAll();
    }

    public boolean saveUser(User user) {
        User userFromDB = usersRepository.findByUsername(user.getUsername());
        if (userFromDB != null) {
            return false;
        }
        user.setIsActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        usersRepository.save(user);
        return true;
    }

    public boolean deleteUser(Integer userId) {
        if (usersRepository.findById(userId).isPresent()) {
            usersRepository.deleteById(userId);
            return true;
        }
        return false;
    }
}
