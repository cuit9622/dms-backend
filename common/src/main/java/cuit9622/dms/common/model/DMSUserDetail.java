package cuit9622.dms.common.model;

import lombok.Data;

import java.util.Set;

@Data
public class DMSUserDetail {
    private Long ID;
    private String userName;
    private Set<String> authorities;
}
