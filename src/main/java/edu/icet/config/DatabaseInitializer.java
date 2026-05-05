package edu.icet.config;

import edu.icet.entity.UserEntity;
import edu.icet.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class DatabaseInitializer {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Value("${app.admin.username}")
    String username;

    @Value("${app.admin.password}")
    String password;

    @Override
    public void run(String... args) {

        if (username != null && password != null) {
            if (!userRepository.existsByUsername(username)) {
                UserEntity adminUserEntity = new UserEntity();
                adminUserEntity.setUsername(username);
                adminUserEntity.setPassword(passwordEncoder.encode(password));
                userRepository.save(adminUserEntity);

                log.info("Default admin user created with username: {}", username);
            } else {
                log.info("Admin user already exists, skipping initialization");
            }
        } else {
            log.warn("Admin credentials not configured, skipping default user creation");
        }
    }
}
