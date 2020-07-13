package org.tamisemi.iftmis.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RiskRankMapperTest {
    private RiskRankMapper riskRankMapper;

    @BeforeEach
    public void setUp() {
        riskRankMapper = new RiskRankMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(riskRankMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(riskRankMapper.fromId(null)).isNull();
    }
}
