package org.tamisemi.iftmis.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FindingMapperTest {
    private FindingMapper findingMapper;

    @BeforeEach
    public void setUp() {
        findingMapper = new FindingMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(findingMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(findingMapper.fromId(null)).isNull();
    }
}
