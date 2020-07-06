package org.tamisemi.iftmis.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.tamisemi.iftmis.web.rest.TestUtil;

public class MeetingAgendaDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MeetingAgendaDTO.class);
        MeetingAgendaDTO meetingAgendaDTO1 = new MeetingAgendaDTO();
        meetingAgendaDTO1.setId(1L);
        MeetingAgendaDTO meetingAgendaDTO2 = new MeetingAgendaDTO();
        assertThat(meetingAgendaDTO1).isNotEqualTo(meetingAgendaDTO2);
        meetingAgendaDTO2.setId(meetingAgendaDTO1.getId());
        assertThat(meetingAgendaDTO1).isEqualTo(meetingAgendaDTO2);
        meetingAgendaDTO2.setId(2L);
        assertThat(meetingAgendaDTO1).isNotEqualTo(meetingAgendaDTO2);
        meetingAgendaDTO1.setId(null);
        assertThat(meetingAgendaDTO1).isNotEqualTo(meetingAgendaDTO2);
    }
}
