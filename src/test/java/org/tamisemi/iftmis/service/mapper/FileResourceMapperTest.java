package org.tamisemi.iftmis.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FileResourceMapperTest {
    private FileResourceMapper fileResourceMapper;

    @BeforeEach
    public void setUp() {
        fileResourceMapper = new FileResourceMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(fileResourceMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(fileResourceMapper.fromId(null)).isNull();
    }
}
