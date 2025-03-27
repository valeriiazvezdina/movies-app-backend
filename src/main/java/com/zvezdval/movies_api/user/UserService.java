package com.zvezdval.movies_api.user;

import com.zvezdval.movies_api.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    private NotificationService notificationService;

    public UserDto login(String email, String password) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        notificationService.sendLoginMessage(user.getEmail());

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

        notificationService.sendWelcomeMessage(newUser.getEmail());

        userRepository.save(newUser);
    }
}