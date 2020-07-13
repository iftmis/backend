package org.tamisemi.iftmis.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RiskRegisterMapperTest {
    private RiskRegisterMapper riskRegisterMapper;

    @BeforeEach
    public void setUp() {
        riskRegisterMapper = new RiskRegisterMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(riskRegisterMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(riskRegisterMapper.fromId(null)).isNull();
    }
}
