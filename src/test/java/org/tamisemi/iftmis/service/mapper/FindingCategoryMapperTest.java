package org.tamisemi.iftmis.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FindingCategoryMapperTest {
    private FindingCategoryMapper findingCategoryMapper;

    @BeforeEach
    public void setUp() {
        findingCategoryMapper = new FindingCategoryMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(findingCategoryMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(findingCategoryMapper.fromId(null)).isNull();
    }
}
