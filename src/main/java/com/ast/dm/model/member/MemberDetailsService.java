package com.ast.dm.model.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class MemberDetailsService implements UserDetailsService {
    private final MemberRepository memberRepository;

    @Autowired
    public MemberDetailsService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MemberModel member = Optional
                .ofNullable(memberRepository.findOneByUsername(username))
                .orElseThrow(() -> new UsernameNotFoundException("Usuario " + username + " no existe!"));

        return User
                .withUsername(member.getUsername())
                .password(member.getPassword())
                .authorities(member.getRole())
                .build();
    }
}
