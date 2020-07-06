package org.tamisemi.iftmis.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.tamisemi.iftmis.web.rest.TestUtil;

public class MeetingAttachmentTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MeetingAttachment.class);
        MeetingAttachment meetingAttachment1 = new MeetingAttachment();
        meetingAttachment1.setId(1L);
        MeetingAttachment meetingAttachment2 = new MeetingAttachment();
        meetingAttachment2.setId(meetingAttachment1.getId());
        assertThat(meetingAttachment1).isEqualTo(meetingAttachment2);
        meetingAttachment2.setId(2L);
        assertThat(meetingAttachment1).isNotEqualTo(meetingAttachment2);
        meetingAttachment1.setId(null);
        assertThat(meetingAttachment1).isNotEqualTo(meetingAttachment2);
    }
}
