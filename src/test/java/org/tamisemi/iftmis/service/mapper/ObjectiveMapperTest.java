package org.tamisemi.iftmis.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ObjectiveMapperTest {
    private ObjectiveMapper objectiveMapper;

    @BeforeEach
    public void setUp() {
        objectiveMapper = new ObjectiveMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(objectiveMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(objectiveMapper.fromId(null)).isNull();
    }
}
