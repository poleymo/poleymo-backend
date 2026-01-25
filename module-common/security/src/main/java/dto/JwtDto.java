package dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseCookie;

public class JwtDto {

    @Getter
    @RequiredArgsConstructor
    public static class RefreshToken {
        private final String id;
        private final ResponseCookie token;

        public String getTokenString() {
            return token.toString();
        }
    }
}
