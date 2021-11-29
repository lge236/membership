package testProject.membership.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import testProject.membership.member.domain.MemberInfo;
import testProject.membership.member.dto.MemberInfoDTO;
import testProject.membership.member.repository.MemberRepository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MemberService implements UserDetailsService {

    private final MemberRepository memberRepository;

    /**
     * Spring Security 필수 메소드 구현
     *
     * @param id
     * @return UserDetails
     * @throws UsernameNotFoundException 유저가 없을 때 예외 발생
     */
    @Override // 기본적인 반환 타입은 UserDetails, UserDetails를 상속받은 UserInfo로 반환 타입 지정 (자동으로 다운 캐스팅됨)
    public MemberInfo loadUserByUsername(String id) throws UsernameNotFoundException { // 시큐리티에서 지정한 서비스이기 때문에 이 메소드를 필수로 구현
        return memberRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException((id)));
    }
    public Long save(MemberInfoDTO infoDto) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        infoDto.setPassword(encoder.encode(infoDto.getPassword()));

        return memberRepository.save(MemberInfo.builder()
                .id(infoDto.getId())
                .auth(infoDto.getAuth())
                .password(infoDto.getPassword()).build()).getCode();
    }
    //일반 회원조회 (회원 정보 수정)
    public Optional<MemberInfo> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }

    //전체 회원 조회
    public List<MemberInfo> findMembers(){
        List<MemberInfo> list = memberRepository.findAll();
        for (MemberInfo member: list) {
            System.out.println(member.getId());
        }
        return memberRepository.findAll();
    }
}