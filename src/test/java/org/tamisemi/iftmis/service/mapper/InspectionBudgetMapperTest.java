package org.tamisemi.iftmis.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class InspectionBudgetMapperTest {
    private InspectionBudgetMapper inspectionBudgetMapper;

    @BeforeEach
    public void setUp() {
        inspectionBudgetMapper = new InspectionBudgetMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(inspectionBudgetMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(inspectionBudgetMapper.fromId(null)).isNull();
    }
}
