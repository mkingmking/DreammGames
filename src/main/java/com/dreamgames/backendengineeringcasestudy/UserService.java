package com.dreamgames.backendengineeringcasestudy;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.dreamgames.Country;

import jakarta.transaction.Transactional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User createUser() {
        User newUser = new User(null, 0, 0, null);
        newUser.setLevel(1);
        newUser.setCoins(5000);
        newUser.setCountry(Country.randomCountry());
        return userRepository.save(newUser);
    }

    @Transactional
    public User updateLevel(Long userId) {
        // Retrieve the user from the database
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        // Update the user's level
        user.setLevel(user.getLevel() + 1);

        // Save the updated user entity
        return userRepository.save(user);
    }
}
