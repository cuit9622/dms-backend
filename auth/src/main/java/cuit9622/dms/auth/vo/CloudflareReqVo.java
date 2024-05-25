package cuit9622.dms.auth.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
public class CloudflareReqVo {
    private String secret;
    private String response;
}
