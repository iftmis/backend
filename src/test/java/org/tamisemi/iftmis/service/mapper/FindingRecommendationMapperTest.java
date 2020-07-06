package org.tamisemi.iftmis.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FindingRecommendationMapperTest {
    private FindingRecommendationMapper findingRecommendationMapper;

    @BeforeEach
    public void setUp() {
        findingRecommendationMapper = new FindingRecommendationMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(findingRecommendationMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(findingRecommendationMapper.fromId(null)).isNull();
    }
}
