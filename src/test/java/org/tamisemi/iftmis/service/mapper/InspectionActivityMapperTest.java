package org.tamisemi.iftmis.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class InspectionActivityMapperTest {
    private InspectionActivityMapper inspectionActivityMapper;

    @BeforeEach
    public void setUp() {
        inspectionActivityMapper = new InspectionActivityMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(inspectionActivityMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(inspectionActivityMapper.fromId(null)).isNull();
    }
}
