package org.tamisemi.iftmis.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FindingResponseMapperTest {
    private FindingResponseMapper findingResponseMapper;

    @BeforeEach
    public void setUp() {
        findingResponseMapper = new FindingResponseMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(findingResponseMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(findingResponseMapper.fromId(null)).isNull();
    }
}
