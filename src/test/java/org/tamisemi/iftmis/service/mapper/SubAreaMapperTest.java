package org.tamisemi.iftmis.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SubAreaMapperTest {
    private SubAreaMapper subAreaMapper;

    @BeforeEach
    public void setUp() {
        subAreaMapper = new SubAreaMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(subAreaMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(subAreaMapper.fromId(null)).isNull();
    }
}
