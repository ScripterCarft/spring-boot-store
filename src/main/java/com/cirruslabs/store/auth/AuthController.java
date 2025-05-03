package com.cirruslabs.store.auth;

import com.cirruslabs.store.users.UserDto;
import com.cirruslabs.store.users.UserMapper;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {
    private final UserMapper userMapper;
    private final JwtConfig jwtConfig;
    private final AuthService authService;

    @PostMapping("/login")
    public JwtResponse login(@RequestBody @Valid LoginUserRequest request,
                             HttpServletResponse response) {
        var loginResponse = authService.login(request);

        var refreshToken = loginResponse.getRefreshToken().toString();
        var cookie = new Cookie("refreshToken", refreshToken);
        cookie.setHttpOnly(true);
        cookie.setPath("/auth/refresh");
        cookie.setMaxAge(jwtConfig.getRefreshTokenExpiration());
        cookie.setSecure(true);
        response.addCookie(cookie);

        return new JwtResponse(loginResponse.getAccessToken().toString());
    }

    @PostMapping("/refresh")
    public JwtResponse refresh(
            @CookieValue(value = "refreshToken") String refreshToken
    ) {
        var accessToken = authService.refreshAccessToken(refreshToken);

        return new JwtResponse(accessToken.toString());
    }

    @GetMapping("/me")
    public ResponseEntity<UserDto> me() {
        var user = authService.getCurrentUser();
        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        var userDto = userMapper.toDto(user);
        return ResponseEntity.ok(userDto);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<Void> handleBadCredentialsException() {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}
