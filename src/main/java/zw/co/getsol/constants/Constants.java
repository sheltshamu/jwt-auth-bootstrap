package zw.co.getsol.constants;

public final class Constants {

    private Constants(){
        throw new AssertionError();
    }

    public static final String BEARER = "Bearer ";
    public static final String AUTHORIZATION = "Authorization";
    public static final String UNAUTHORIZED = "Unauthorized";
    public static final long JWT_TOKEN_VALIDITY = 18000;

}
