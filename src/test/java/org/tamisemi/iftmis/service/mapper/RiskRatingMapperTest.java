package org.tamisemi.iftmis.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RiskRatingMapperTest {
    private RiskRatingMapper riskRatingMapper;

    @BeforeEach
    public void setUp() {
        riskRatingMapper = new RiskRatingMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(riskRatingMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(riskRatingMapper.fromId(null)).isNull();
    }
}
