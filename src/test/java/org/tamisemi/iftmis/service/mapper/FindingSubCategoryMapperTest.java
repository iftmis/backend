package org.tamisemi.iftmis.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FindingSubCategoryMapperTest {
    private FindingSubCategoryMapper findingSubCategoryMapper;

    @BeforeEach
    public void setUp() {
        findingSubCategoryMapper = new FindingSubCategoryMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(findingSubCategoryMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(findingSubCategoryMapper.fromId(null)).isNull();
    }
}
