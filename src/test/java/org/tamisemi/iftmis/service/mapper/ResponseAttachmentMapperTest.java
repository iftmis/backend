package org.tamisemi.iftmis.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ResponseAttachmentMapperTest {
    private ResponseAttachmentMapper responseAttachmentMapper;

    @BeforeEach
    public void setUp() {
        responseAttachmentMapper = new ResponseAttachmentMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(responseAttachmentMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(responseAttachmentMapper.fromId(null)).isNull();
    }
}
