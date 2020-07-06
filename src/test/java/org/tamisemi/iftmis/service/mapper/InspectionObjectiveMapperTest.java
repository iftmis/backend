package org.tamisemi.iftmis.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class InspectionObjectiveMapperTest {
    private InspectionObjectiveMapper inspectionObjectiveMapper;

    @BeforeEach
    public void setUp() {
        inspectionObjectiveMapper = new InspectionObjectiveMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(inspectionObjectiveMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(inspectionObjectiveMapper.fromId(null)).isNull();
    }
}
