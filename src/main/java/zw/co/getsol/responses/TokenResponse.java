package zw.co.getsol.responses;

import lombok.Getter;

import java.io.Serializable;

@Getter
public class TokenResponse implements Serializable {
    private final String token;

    public TokenResponse(String token) {
        this.token = token;
    }
}
