package io.github.nguba.lunanera.application;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@SpringBootTest(classes = {LunaNeraApplication.class})
class LunaNeraApplicationTests {

    @Autowired
    LunaNeraConfig config;

    @Test
    void contextLoads() {
        System.out.println(config.getPid());
    }

}
