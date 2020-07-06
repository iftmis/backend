package org.tamisemi.iftmis.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MeetingMemberMapperTest {
    private MeetingMemberMapper meetingMemberMapper;

    @BeforeEach
    public void setUp() {
        meetingMemberMapper = new MeetingMemberMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(meetingMemberMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(meetingMemberMapper.fromId(null)).isNull();
    }
}
