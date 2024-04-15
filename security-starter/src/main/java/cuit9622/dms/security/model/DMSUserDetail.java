package cuit9622.dms.security.model;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.util.Set;

@Data
public class DMSUserDetail {
    private Long ID;
    private String userName;
    private Set<? extends GrantedAuthority> authorities;
}
