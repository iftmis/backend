package org.tamisemi.iftmis.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class InspectionAreaMapperTest {
    private InspectionAreaMapper inspectionAreaMapper;

    @BeforeEach
    public void setUp() {
        inspectionAreaMapper = new InspectionAreaMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(inspectionAreaMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(inspectionAreaMapper.fromId(null)).isNull();
    }
}
