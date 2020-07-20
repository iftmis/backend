package org.tamisemi.iftmis.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class InspectionPlanMapperTest {
    private InspectionPlanMapper inspectionPlanMapper;

    @BeforeEach
    public void setUp() {
        inspectionPlanMapper = new InspectionPlanMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(inspectionPlanMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(inspectionPlanMapper.fromId(null)).isNull();
    }
}
