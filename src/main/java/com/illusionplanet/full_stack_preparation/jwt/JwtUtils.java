package com.illusionplanet.full_stack_preparation.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Base64;
import java.util.Date;

@Slf4j
public class JwtUtils {
    @Value("${jwt.appSecret}")
    private String appSecret;

    private static String APP_SECRET;

    @PostConstruct
    public void init() {
        APP_SECRET = this.appSecret;
    }

    private static Key getKeyInstance(){
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        if (APP_SECRET == null) {
            log.warn("APP_SECRET为null，但仍有补救措施以使其初始化");
            APP_SECRET = "ukc8BDbRigUDaY6pZFfWus2jZWLPHO";
        }
        byte[] bytes = Base64.getDecoder().decode(APP_SECRET);
        return new SecretKeySpec(bytes,signatureAlgorithm.getJcaName());
    }

    public static String getJwtToken(JwtInfo jwtInfo, int expire){
        return Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setHeaderParam("alg", "HS256")
                .setSubject("guli-user")//主题
                .setIssuedAt(new Date())//颁发时间
                .setExpiration(DateTime.now().plusSeconds(expire).toDate())//过期时间
                .claim("username", jwtInfo.getUsername())
                .signWith(SignatureAlgorithm.HS256, getKeyInstance())
                .compact();
    }

    /**
     * 判断token是否存在与有效
     * @param jwtToken
     * @return
     */
    public static boolean checkJwtTToken(String jwtToken) {
        if(StringUtils.isEmpty(jwtToken)) return false;
        try {
            Jwts.parser().setSigningKey(getKeyInstance()).parseClaimsJws(jwtToken);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 判断token是否存在与有效
     * @param request
     * @return
     */
    public static boolean checkJwtTToken(HttpServletRequest request) {
        try {
            String jwtToken = request.getHeader("token");
            if(StringUtils.isEmpty(jwtToken)) return false;
            Jwts.parser().setSigningKey(getKeyInstance()).parseClaimsJws(jwtToken);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static JwtInfo getMemberIdByJwtToken(HttpServletRequest request) {
        String jwtToken = request.getHeader("token");
        if(StringUtils.isEmpty(jwtToken)) return null;
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(getKeyInstance()).parseClaimsJws(jwtToken);
        Claims claims = claimsJws.getBody();
        return new JwtInfo(claims.get("username").toString());
    }
}
