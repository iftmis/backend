package org.tamisemi.iftmis.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class QuarterMapperTest {
    private QuarterMapper quarterMapper;

    @BeforeEach
    public void setUp() {
        quarterMapper = new QuarterMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(quarterMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(quarterMapper.fromId(null)).isNull();
    }
}
