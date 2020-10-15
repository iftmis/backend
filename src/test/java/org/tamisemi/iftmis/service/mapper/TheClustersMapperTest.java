package org.tamisemi.iftmis.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class TheClustersMapperTest {

    private TheClustersMapper theClustersMapper;

    @BeforeEach
    public void setUp() {
        theClustersMapper = new TheClustersMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(theClustersMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(theClustersMapper.fromId(null)).isNull();
    }
}
