package org.tamisemi.iftmis.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.tamisemi.iftmis.web.rest.TestUtil;

public class MeetingMemberDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MeetingMemberDTO.class);
        MeetingMemberDTO meetingMemberDTO1 = new MeetingMemberDTO();
        meetingMemberDTO1.setId(1L);
        MeetingMemberDTO meetingMemberDTO2 = new MeetingMemberDTO();
        assertThat(meetingMemberDTO1).isNotEqualTo(meetingMemberDTO2);
        meetingMemberDTO2.setId(meetingMemberDTO1.getId());
        assertThat(meetingMemberDTO1).isEqualTo(meetingMemberDTO2);
        meetingMemberDTO2.setId(2L);
        assertThat(meetingMemberDTO1).isNotEqualTo(meetingMemberDTO2);
        meetingMemberDTO1.setId(null);
        assertThat(meetingMemberDTO1).isNotEqualTo(meetingMemberDTO2);
    }
}
