package org.tamisemi.iftmis.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ProcedureMapperTest {
    private ProcedureMapper procedureMapper;

    @BeforeEach
    public void setUp() {
        procedureMapper = new ProcedureMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(procedureMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(procedureMapper.fromId(null)).isNull();
    }
}
