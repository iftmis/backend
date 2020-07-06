package org.tamisemi.iftmis.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MeetingAttachmentMapperTest {
    private MeetingAttachmentMapper meetingAttachmentMapper;

    @BeforeEach
    public void setUp() {
        meetingAttachmentMapper = new MeetingAttachmentMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(meetingAttachmentMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(meetingAttachmentMapper.fromId(null)).isNull();
    }
}
