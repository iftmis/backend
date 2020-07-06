package org.tamisemi.iftmis.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class InspectionRecommendationMapperTest {
    private InspectionRecommendationMapper inspectionRecommendationMapper;

    @BeforeEach
    public void setUp() {
        inspectionRecommendationMapper = new InspectionRecommendationMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(inspectionRecommendationMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(inspectionRecommendationMapper.fromId(null)).isNull();
    }
}
