package cuit9622.dms.security.util;

import cuit9622.dms.common.model.DMSUserDetail;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtil {
    public static Long getUserID() {
        DMSUserDetail userDetail = getUserDetail();
        return userDetail.getID();
    }

    public static DMSUserDetail getUserDetail() {
        return (DMSUserDetail) SecurityContextHolder.getContext().getAuthentication();
    }
}
