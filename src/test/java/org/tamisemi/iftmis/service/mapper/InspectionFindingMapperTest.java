package org.tamisemi.iftmis.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class InspectionFindingMapperTest {
    private InspectionFindingMapper inspectionFindingMapper;

    @BeforeEach
    public void setUp() {
        inspectionFindingMapper = new InspectionFindingMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(inspectionFindingMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(inspectionFindingMapper.fromId(null)).isNull();
    }
}
