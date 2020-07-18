package org.tamisemi.iftmis.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class InspectionActivityQuarterMapperTest {
    private InspectionActivityQuarterMapper inspectionActivityQuarterMapper;

    @BeforeEach
    public void setUp() {
        inspectionActivityQuarterMapper = new InspectionActivityQuarterMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(inspectionActivityQuarterMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(inspectionActivityQuarterMapper.fromId(null)).isNull();
    }
}
