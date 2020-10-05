package org.tamisemi.iftmis.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class InspectionTypesMapperTest {

    private InspectionTypesMapper inspectionTypesMapper;

    @BeforeEach
    public void setUp() {
        inspectionTypesMapper = new InspectionTypesMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(inspectionTypesMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(inspectionTypesMapper.fromId(null)).isNull();
    }
}
