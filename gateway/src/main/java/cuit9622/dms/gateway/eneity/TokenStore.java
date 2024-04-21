package cuit9622.dms.gateway.eneity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity(name = "sys_user_token")
@Data
public class TokenStore {
    @Id
    //相当于Mysql的AutoIncrement
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private String refreshToken;
}
