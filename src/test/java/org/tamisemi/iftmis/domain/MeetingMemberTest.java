package org.tamisemi.iftmis.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.tamisemi.iftmis.web.rest.TestUtil;

public class MeetingMemberTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MeetingMember.class);
        MeetingMember meetingMember1 = new MeetingMember();
        meetingMember1.setId(1L);
        MeetingMember meetingMember2 = new MeetingMember();
        meetingMember2.setId(meetingMember1.getId());
        assertThat(meetingMember1).isEqualTo(meetingMember2);
        meetingMember2.setId(2L);
        assertThat(meetingMember1).isNotEqualTo(meetingMember2);
        meetingMember1.setId(null);
        assertThat(meetingMember1).isNotEqualTo(meetingMember2);
    }
}
