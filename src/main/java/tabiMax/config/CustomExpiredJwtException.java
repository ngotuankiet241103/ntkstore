package tabiMax.config;

public class CustomExpiredJwtException extends RuntimeException {
    public CustomExpiredJwtException(String message) {
        super(message);
    }
}
