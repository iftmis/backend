package org.tamisemi.iftmis.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RiskCategoryMapperTest {
    private RiskCategoryMapper riskCategoryMapper;

    @BeforeEach
    public void setUp() {
        riskCategoryMapper = new RiskCategoryMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(riskCategoryMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(riskCategoryMapper.fromId(null)).isNull();
    }
}
