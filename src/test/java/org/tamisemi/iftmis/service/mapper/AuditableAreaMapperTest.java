package org.tamisemi.iftmis.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AuditableAreaMapperTest {
    private AuditableAreaMapper auditableAreaMapper;

    @BeforeEach
    public void setUp() {
        auditableAreaMapper = new AuditableAreaMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(auditableAreaMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(auditableAreaMapper.fromId(null)).isNull();
    }
}
