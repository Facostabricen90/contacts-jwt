package qasoft.cotacts_jwt.Auth;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import qasoft.cotacts_jwt.User.Role;
import qasoft.cotacts_jwt.User.User;
import qasoft.cotacts_jwt.User.UserRepository;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;

    public AuthResponse login(LoginRequest loginRequest) {
        return null;
    }

    public AuthResponse register(RegisterRequest registerRequest) {
        User user = User.builder()
                .username(registerRequest.getUsername())
                .password(registerRequest.getPassword())
                .firstName(registerRequest.getFirstName())
                .lastName(registerRequest.getLastName())
                .email(registerRequest.getEmail())
                .country(registerRequest.getCountry())
                .role(Role.USER)
                .build();

        userRepository.save(user);
        return AuthResponse.builder()
                .token(jwtService.getToken(user))
                .build();
    }
}
