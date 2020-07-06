package org.tamisemi.iftmis.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.tamisemi.iftmis.web.rest.TestUtil;

public class MeetingAttachmentDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MeetingAttachmentDTO.class);
        MeetingAttachmentDTO meetingAttachmentDTO1 = new MeetingAttachmentDTO();
        meetingAttachmentDTO1.setId(1L);
        MeetingAttachmentDTO meetingAttachmentDTO2 = new MeetingAttachmentDTO();
        assertThat(meetingAttachmentDTO1).isNotEqualTo(meetingAttachmentDTO2);
        meetingAttachmentDTO2.setId(meetingAttachmentDTO1.getId());
        assertThat(meetingAttachmentDTO1).isEqualTo(meetingAttachmentDTO2);
        meetingAttachmentDTO2.setId(2L);
        assertThat(meetingAttachmentDTO1).isNotEqualTo(meetingAttachmentDTO2);
        meetingAttachmentDTO1.setId(null);
        assertThat(meetingAttachmentDTO1).isNotEqualTo(meetingAttachmentDTO2);
    }
}
