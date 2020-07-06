package org.tamisemi.iftmis.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FinancialYearMapperTest {
    private FinancialYearMapper financialYearMapper;

    @BeforeEach
    public void setUp() {
        financialYearMapper = new FinancialYearMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(financialYearMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(financialYearMapper.fromId(null)).isNull();
    }
}
