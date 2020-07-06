package org.tamisemi.iftmis.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class IndicatorMapperTest {
    private IndicatorMapper indicatorMapper;

    @BeforeEach
    public void setUp() {
        indicatorMapper = new IndicatorMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(indicatorMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(indicatorMapper.fromId(null)).isNull();
    }
}
