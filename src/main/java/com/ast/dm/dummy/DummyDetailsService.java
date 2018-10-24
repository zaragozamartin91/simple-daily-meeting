package com.ast.dm.dummy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class DummyDetailsService implements UserDetailsService {
    private final InMemoryUserDetailsManager manager;

    public static final Map<String, Long> ids = new HashMap<String, Long>() {{
        put("martin", 1L);
        put("mauricio", 2L);
        put("sancho", 3L);
    }};

    @Autowired
    public DummyDetailsService(@Qualifier("hashPasswordEncoder") PasswordEncoder passwordEncoder) {
        // ensure the passwords are encoded properly
        manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withUsername("martin").password("zaragoza").authorities("ADMIN").passwordEncoder(passwordEncoder::encode).build());
        manager.createUser(User.withUsername("mauricio").password("meza").authorities("MEMBER").passwordEncoder(passwordEncoder::encode).build());
        manager.createUser(User.withUsername("sancho").password("sanchez").authorities("MEMBER").passwordEncoder(passwordEncoder::encode).build());
    }

    @Override public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return manager.loadUserByUsername(username);
    }
}
