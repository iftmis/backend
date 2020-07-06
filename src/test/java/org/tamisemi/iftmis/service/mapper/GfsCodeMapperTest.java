package org.tamisemi.iftmis.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GfsCodeMapperTest {
    private GfsCodeMapper gfsCodeMapper;

    @BeforeEach
    public void setUp() {
        gfsCodeMapper = new GfsCodeMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(gfsCodeMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(gfsCodeMapper.fromId(null)).isNull();
    }
}
