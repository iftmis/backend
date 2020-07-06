package org.tamisemi.iftmis.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MeetingAgendaMapperTest {
    private MeetingAgendaMapper meetingAgendaMapper;

    @BeforeEach
    public void setUp() {
        meetingAgendaMapper = new MeetingAgendaMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(meetingAgendaMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(meetingAgendaMapper.fromId(null)).isNull();
    }
}
