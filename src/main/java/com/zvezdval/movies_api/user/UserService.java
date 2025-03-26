package com.zvezdval.movies_api.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserDto login(String email, String password) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        return new UserDto(user.getEmail(), null);
    }

    public void register(UserDto userDto) {
        if (userRepository.findByEmail(userDto.email()).isPresent()) {
            throw new RuntimeException("Email is already taken");
        }

        String hashedPassword = passwordEncoder.encode(userDto.password());

        User newUser = User.builder()
                .email(userDto.email())
                .password(hashedPassword)
                .build();

        userRepository.save(newUser);
    }
}