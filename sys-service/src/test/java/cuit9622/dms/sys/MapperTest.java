package cuit9622.dms.sys;

import cuit9622.dms.sys.mapper.MenuMapper;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MapperTest {

    @Resource
    MenuMapper mapper;

    @Test
    public void test01(){
        System.out.println(mapper.getMenuByUserId(1L));
        System.out.println("-------------------");
        System.out.println(mapper.getMenuByUserId(1L));
    }
}
