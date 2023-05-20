package kr.ac.bokgpt.repository.querydsl;

import kr.ac.bokgpt.domain.Member;
import kr.ac.bokgpt.dto.MemberClassificationDto;

public interface MemberRepositoryCustom {
    Member findMemberByEmail(String email);

    MemberClassificationDto findClassificationByEmail(String email);
}
