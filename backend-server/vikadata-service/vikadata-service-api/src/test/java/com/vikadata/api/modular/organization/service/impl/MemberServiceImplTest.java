package com.vikadata.api.modular.organization.service.impl;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.vikadata.api.AbstractIntegrationTest;
import com.vikadata.api.enums.space.UserSpaceStatus;
import com.vikadata.api.mock.bean.MockUserSpace;
import com.vikadata.entity.MemberEntity;
import com.vikadata.entity.UserEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.util.Lists.list;

/**
 * member service test
 * @author Shawn Deng
 */
public class MemberServiceImplTest extends AbstractIntegrationTest {

    @Test
    public void testInvitationWithoutExistUser() {
        MockUserSpace mockUserSpace = createSingleUserAndSpace();

        List<String> emails = list("shawndgh@163.com");
        iMemberService.emailInvitation(mockUserSpace.getUserId(), mockUserSpace.getSpaceId(), emails);

        // check this member should join this space again
        MemberEntity member = iMemberService.getBySpaceIdAndEmail(mockUserSpace.getSpaceId(), "shawndgh@163.com");
        assertThat(member).isNotNull();
        assertThat(member.getIsActive()).isFalse();
        assertThat(member.getIsPoint()).isTrue();
        assertThat(member.getStatus()).isEqualTo(UserSpaceStatus.INACTIVE.getStatus());
    }

    @Test
    public void testInvitationWithExistUser() {
        MockUserSpace mockUserSpace = createSingleUserAndSpace();

        UserEntity user = createUserWithEmail("shawndgh@163.com");

        List<String> emails = list("shawndgh@163.com");
        iMemberService.emailInvitation(mockUserSpace.getUserId(), mockUserSpace.getSpaceId(), emails);

        // check this member should join this space again
        MemberEntity member = iMemberService.getByUserIdAndSpaceId(user.getId(), mockUserSpace.getSpaceId());
        assertThat(member).isNotNull();
        assertThat(member.getIsActive()).isFalse();
        assertThat(member.getIsPoint()).isTrue();
        assertThat(member.getStatus()).isEqualTo(UserSpaceStatus.INACTIVE.getStatus());
    }

    @Test
    public void testInvitationWithRestore() {
        MockUserSpace mockUserSpace = createSingleUserAndSpace();

        UserEntity user = createUserWithEmail("shawndgh@163.com");

        Long toDeletedMemberId = createMember(user.getId(), mockUserSpace.getSpaceId());
        iMemberService.removeByMemberIds(Collections.singletonList(toDeletedMemberId));

        List<String> emails = list("shawndgh@163.com");
        iMemberService.emailInvitation(mockUserSpace.getUserId(), mockUserSpace.getSpaceId(), emails);

        // check this member should join this space again
        MemberEntity member = iMemberService.getById(toDeletedMemberId);
        assertThat(member).isNotNull();
        assertThat(member.getIsActive()).isFalse();
        assertThat(member.getIsPoint()).isFalse();
        assertThat(member.getStatus()).isEqualTo(UserSpaceStatus.INACTIVE.getStatus());
    }

    @Test
    public void testInvitationWithHavingActive() {
        MockUserSpace mockUserSpace = createSingleUserAndSpace();

        UserEntity user = createUserWithEmail("shawndgh@163.com");

        Long existMemberId = createMember(user.getId(), mockUserSpace.getSpaceId());

        List<String> emails = list("shawndgh@163.com");
        iMemberService.emailInvitation(mockUserSpace.getUserId(), mockUserSpace.getSpaceId(), emails);

        // check this member should join this space again
        MemberEntity member = iMemberService.getById(existMemberId);
        assertThat(member).isNotNull();
        assertThat(member.getIsActive()).isTrue();
        assertThat(member.getIsPoint()).isTrue();
        assertThat(member.getStatus()).isEqualTo(UserSpaceStatus.ACTIVE.getStatus());
    }
}
