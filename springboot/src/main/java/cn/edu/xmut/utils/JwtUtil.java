package cn.edu.xmut.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.context.annotation.Configuration;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * jwt工具类
 *
 * @author zhangmingcheng
 */
@Configuration
public class JwtUtil {
 
    private static long EXPIRATION_TIME=3600000; // 1 hour
 
    private static String SECRET="MDk4ZjZiY2Q0NjIxZDM3M2NhZGU0ZTgzMjY34DFDSSSd";// 秘钥

    private static final String USER_ID = "id";

 
    /**
     * 生成jwtToken
     *
     * @param id
     * @return
     */
    public static String generateToken(String id) {
        HashMap<String, Object> map = new HashMap<>();
        // you can put any data in the map
        map.put(USER_ID, id);
        String jwt = Jwts.builder().setClaims(map).setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET).compact();
        return jwt;
    }
 
    /**
     * 校验jwtToken
     *
     * @param token
     * @return
     */
    public static Result<String> validateToken(String token) {
        Result<String> result = new Result<>();
        try{
            if (token != null) {
                Map<String, Object> body = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
                String id = (String) (body.get(USER_ID));
                if (id == null || id.isEmpty()) {
                    result.fail("Wrong token without id");
                } else {
                    result.setData(id);
                    result.success();
                }
            } else {
                result.fail("Missing token");
            }
        }catch (Exception e){
            result.fail(e.toString());
        }


        return result;
    }
    public static String getUserIdByToken(String token){
        Map<String, Object> body = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
        return (String) (body.get(USER_ID));
    }
    public static void main(String[] args) {
        String id = "123QWE";

        String token = generateToken(id);
        System.out.println("token:"+token);

        //HashMap<String, Object> map = new HashMap<>();
        // you can put any data in the map
//        map.put("name", id);
//        token = Jwts.builder().setClaims(map).setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
//                .signWith(SignatureAlgorithm.HS512, SECRET).compact();

        Result result;
        try {
            result = validateToken(token);
            System.out.println(result.getData()+":"+result.getMessage());
        }catch (Exception e){
            System.out.println(e.toString());
        }

    }
}