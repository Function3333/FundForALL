package com.fundingForAll.www.utilTest;

import com.fundingForAll.www.utils.EmailUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

public class utilTest {

    @Test
    void initMyEmailTest() throws IOException {
        EmailUtil emailUtil = new EmailUtil();
        emailUtil.sendEmail("whddlsk994@gmail.com");
    }
}
