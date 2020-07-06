package org.tamisemi.iftmis.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.tamisemi.iftmis.web.rest.TestUtil;

public class MeetingAgendaTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MeetingAgenda.class);
        MeetingAgenda meetingAgenda1 = new MeetingAgenda();
        meetingAgenda1.setId(1L);
        MeetingAgenda meetingAgenda2 = new MeetingAgenda();
        meetingAgenda2.setId(meetingAgenda1.getId());
        assertThat(meetingAgenda1).isEqualTo(meetingAgenda2);
        meetingAgenda2.setId(2L);
        assertThat(meetingAgenda1).isNotEqualTo(meetingAgenda2);
        meetingAgenda1.setId(null);
        assertThat(meetingAgenda1).isNotEqualTo(meetingAgenda2);
    }
}
