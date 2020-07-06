package org.tamisemi.iftmis.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class InspectionMapperTest {
    private InspectionMapper inspectionMapper;

    @BeforeEach
    public void setUp() {
        inspectionMapper = new InspectionMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(inspectionMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(inspectionMapper.fromId(null)).isNull();
    }
}
