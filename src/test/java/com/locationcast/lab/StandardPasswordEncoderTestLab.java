package com.locationcast.lab;


import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.testng.annotations.Test;

public class StandardPasswordEncoderTestLab {

    @Test
    public void encodeTest() {
        StandardPasswordEncoder encoder = new StandardPasswordEncoder();
        String encodedPwd = encoder.encode("welcome");
        System.out.println(encodedPwd);
    }
}