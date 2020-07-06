package org.tamisemi.iftmis.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class InspectionProcedureMapperTest {
    private InspectionProcedureMapper inspectionProcedureMapper;

    @BeforeEach
    public void setUp() {
        inspectionProcedureMapper = new InspectionProcedureMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(inspectionProcedureMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(inspectionProcedureMapper.fromId(null)).isNull();
    }
}
