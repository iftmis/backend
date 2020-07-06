package org.tamisemi.iftmis.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class InspectionSubAreaMapperTest {
    private InspectionSubAreaMapper inspectionSubAreaMapper;

    @BeforeEach
    public void setUp() {
        inspectionSubAreaMapper = new InspectionSubAreaMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(inspectionSubAreaMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(inspectionSubAreaMapper.fromId(null)).isNull();
    }
}
