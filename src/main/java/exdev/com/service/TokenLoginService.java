package exdev.com.service;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.Instant;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service("TokenLoginService")
public class TokenLoginService {
	
    @Autowired
    private Environment env;
    
	public Object autoLogin( Map<String, Object> map) throws Exception{
		
		String jwt = getJWT(map);
        String autoLoginAddress = env.getProperty("spring.autoLogin.address");
        String loginUrl	= autoLoginAddress+"?token="+jwt; 
		
        System.err.println(loginUrl);
		map.put("loginUrl", loginUrl);
		return map;
	}
	
	public String getJWT(Map<String, Object> map) throws Exception {
		
        String secret = "EXmysecretkeymysecretkeymysecretkeyPLAN"; // 32byte 이상
        Key secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));

        Instant now = Instant.now();

        return Jwts.builder()
                .setSubject("explan")
                .setIssuedAt(Date.from(now))                  		// 발급 시간 (iat)
                .setExpiration(Date.from(now.plusSeconds(30)))		// 만료 시간 30초 (exp)
                .setId(UUID.randomUUID().toString())				// 고유 토큰 ID (jti)
                .setIssuer("EXPLAN")
                .claim("id", map.get("id"))
                .claim("pageId", map.get("pageId"))
                .claim("param", map.get("jsonParam"))
                .claim("loginType", map.get("loginType"))
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();

    }
	
    // 간단한 클라이언트 IP 추출 함수 (필요에 따라 수정)
    private static String getClientIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For"); // 프록시 환경 대응
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }	
}
