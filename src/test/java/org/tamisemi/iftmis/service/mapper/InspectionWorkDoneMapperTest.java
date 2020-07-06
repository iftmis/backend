package org.tamisemi.iftmis.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class InspectionWorkDoneMapperTest {
    private InspectionWorkDoneMapper inspectionWorkDoneMapper;

    @BeforeEach
    public void setUp() {
        inspectionWorkDoneMapper = new InspectionWorkDoneMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(inspectionWorkDoneMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(inspectionWorkDoneMapper.fromId(null)).isNull();
    }
}
