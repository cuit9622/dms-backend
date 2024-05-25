package cuit9622.dms.auth.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CloudflareRepVo {
    private Boolean success;
    @JsonProperty("error-codes")
    private String[] errorCodes;
    private String challenge_ts;
    private String hostname;
    private String action;
    private String cdata;
}
