package org.tamisemi.iftmis.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class InspectionIndicatorMapperTest {
    private InspectionIndicatorMapper inspectionIndicatorMapper;

    @BeforeEach
    public void setUp() {
        inspectionIndicatorMapper = new InspectionIndicatorMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(inspectionIndicatorMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(inspectionIndicatorMapper.fromId(null)).isNull();
    }
}
