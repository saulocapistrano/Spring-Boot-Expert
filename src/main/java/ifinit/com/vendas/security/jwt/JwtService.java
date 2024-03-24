package ifinit.com.vendas.security.jwt;

import ifinit.com.vendas.VendasApplication;
import ifinit.com.vendas.domain.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Service
public class JwtService {

    @Value("${security.jwt.expirytime}")
    private String expiryTime;
    @Value("${security.jwt.signedkey}")
    private String signedKey;


    public String generateToken(User user){
        long expToken = Long.valueOf(expiryTime);
        LocalDateTime dateHourExp = LocalDateTime.now().plusMinutes(expToken);
        Instant instant = dateHourExp.atZone(ZoneId.systemDefault()).toInstant();
        Date data = Date.from(instant);

        return Jwts
                .builder()
                .setSubject(user.getLogin())
                .setExpiration(data)
                .signWith( SignatureAlgorithm.HS512,  signedKey)
                .compact();
    }


    private Claims receiveClaims(String token) throws ExpiredJwtException {

        return Jwts
                .parser()
                .setSigningKey(signedKey)
                .parseClaimsJws(token)
                .getBody();

    }

    public boolean verifyValidateToken(String token){

        try {
                Claims claims = receiveClaims(token);
                Date expirateDate = claims.getExpiration();
                LocalDateTime localDateTime = expirateDate
                        .toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
                return !LocalDateTime.now().isAfter(localDateTime);

        } catch (Exception e){
            return false;
        }
    }

    public String obtanceLoginUser( String token)throws ExpiredJwtException {
        return (String) receiveClaims(token).getSubject();
    }
    public static void main(String[] args) {

         ConfigurableApplicationContext context =  SpringApplication.run(VendasApplication.class);
        JwtService service = context.getBean(JwtService.class);
        User user = User.builder().login("fulano").build();
        String token = service.generateToken(user);
        System.out.println(token);

        boolean isValidToken = service.verifyValidateToken(token);
        System.out.println("Tokens is valid? "+isValidToken);

        System.out.println(service.obtanceLoginUser(token));

    }
}
