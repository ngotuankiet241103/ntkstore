package tabiMax.config;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import tabiMax.auth.CustomUserDetails;

@Service
public class JwtService {
	private final String secretKey = "404E635266556A586E3272357538782F413F4428472B4B6250645367566B5970";
	private final long jwtExpiration = 1500000;
	private final long refreshExpiration = 604800000;

	public String extractUsername(String token) {
		return extractClaim(token, Claims::getSubject);
	}
	public String extractId(String token) {
		return (String) extractClaim(token, claims -> claims.get("id"));
	}
	public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = extractAllClaims(token);
		return claimsResolver.apply(claims);
	}

	private Claims extractAllClaims(String token) {
		return Jwts.parserBuilder().setSigningKey(getSignInKey()).build().parseClaimsJws(token).getBody();
	}
	public String generateToken(CustomUserDetails userDetails) {
	    return generateToken(new HashMap<>(), userDetails);
	  }

	  public String generateToken(
	      Map<String, Object> extraClaims,
	      CustomUserDetails userDetails
	  ) {
	    return buildToken(extraClaims, userDetails, jwtExpiration);
	  }

	  public String generateRefreshToken(
	      CustomUserDetails userDetails
	  ) {
	    return buildToken(new HashMap<>(), userDetails, refreshExpiration);
	  }

	  private String buildToken(
	          Map<String, Object> extraClaims,
	          CustomUserDetails userDetails,
	          long expiration
	  ) {
		 
	    return Jwts
	            .builder()
	            .setClaims(extraClaims)
	            .setSubject(userDetails.getUsername())
	            .claim("id", userDetails.getUserId().toString())
	            .setIssuedAt(new Date(System.currentTimeMillis()))
	            .setExpiration(new Date(System.currentTimeMillis() + expiration))
	            .signWith(getSignInKey(), SignatureAlgorithm.HS256)
	            .compact();
	  }

	  public boolean isTokenValid(String token, UserDetails userDetails) {
	    final String username = extractUsername(token);
	    System.out.println(username.equals(userDetails.getUsername()));
	    System.out.println(isTokenExpired(token));
	    return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
	  }

	  public boolean isTokenExpired(String token) {
	    return extractExpiration(token).before(new java.util.Date());
	  }

	  private Date extractExpiration(String token) {
	    return (Date) extractClaim(token, Claims::getExpiration);
	  }
	private Key getSignInKey() {
		byte[] keyBytes = Decoders.BASE64.decode(secretKey);
		return Keys.hmacShaKeyFor(keyBytes);
	}
}
