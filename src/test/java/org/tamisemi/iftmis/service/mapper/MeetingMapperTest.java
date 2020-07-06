package org.tamisemi.iftmis.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MeetingMapperTest {
    private MeetingMapper meetingMapper;

    @BeforeEach
    public void setUp() {
        meetingMapper = new MeetingMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(meetingMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(meetingMapper.fromId(null)).isNull();
    }
}
