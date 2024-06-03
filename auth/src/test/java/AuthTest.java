import cuit9622.dms.common.util.DigestsUtils;
import org.junit.jupiter.api.Test;

public class AuthTest {

    @Test
    public void test(){
        System.out.println(DigestsUtils.encrypt("dms123456"));
    }
}
