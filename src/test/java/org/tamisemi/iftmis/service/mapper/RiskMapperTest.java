package org.tamisemi.iftmis.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RiskMapperTest {
    private RiskMapper riskMapper;

    @BeforeEach
    public void setUp() {
        riskMapper = new RiskMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(riskMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(riskMapper.fromId(null)).isNull();
    }
}
