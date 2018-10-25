package com.ast.dm.dummy;

import com.ast.dm.model.member.MemberModel;
import com.ast.dm.model.member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DefaultUsersConfig {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public DefaultUsersConfig(MemberRepository memberRepository, PasswordEncoder passwordEncoder) {
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;

        MemberModel martin = memberRepository.findOneByUsername("martin");
        if (martin == null) {
            MemberModel memberModel = new MemberModel();
            memberModel.setId(1L);
            memberModel.setName("Martin Zaragoza");
            memberModel.setUsername("martin");
            memberModel.setPassword(passwordEncoder.encode("zaragoza"));
            memberRepository.save(memberModel);
        }

        MemberModel mauricio = memberRepository.findOneByUsername("mauricio");
        if (mauricio == null) {
            MemberModel memberModel = new MemberModel();
            memberModel.setId(2L);
            memberModel.setName("Mauricio Meza");
            memberModel.setUsername("mauricio");
            memberModel.setPassword(passwordEncoder.encode("meza"));
            memberRepository.save(memberModel);
        }

        MemberModel javier = memberRepository.findOneByUsername("javier");
        if (javier == null) {
            MemberModel memberModel = new MemberModel();
            memberModel.setId(3L);
            memberModel.setName("Javier Padin");
            memberModel.setUsername("javier");
            memberModel.setPassword(passwordEncoder.encode("padin"));
            memberRepository.save(memberModel);
        }
    }
}
