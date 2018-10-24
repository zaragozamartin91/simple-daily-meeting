package com.ast.dm.config;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class HashPasswordEncoder implements PasswordEncoder {
    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override public String encode(CharSequence charSequence) {
        return passwordEncoder.encode(charSequence);
    }

    @Override public boolean matches(CharSequence charSequence, String s) {
        return passwordEncoder.matches(charSequence, s);
    }

    public static void main(String[] args) {
        HashPasswordEncoder passwordEncoder = new HashPasswordEncoder();
        String pwd = passwordEncoder.encode("padin");
        System.out.println(pwd);
    }
}
