package org.tamisemi.iftmis.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class FundingManagementMapperTest {

    private FundingManagementMapper fundingManagementMapper;

    @BeforeEach
    public void setUp() {
        fundingManagementMapper = new FundingManagementMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(fundingManagementMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(fundingManagementMapper.fromId(null)).isNull();
    }
}
