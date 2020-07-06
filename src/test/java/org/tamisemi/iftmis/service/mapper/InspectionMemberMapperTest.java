package org.tamisemi.iftmis.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class InspectionMemberMapperTest {
    private InspectionMemberMapper inspectionMemberMapper;

    @BeforeEach
    public void setUp() {
        inspectionMemberMapper = new InspectionMemberMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(inspectionMemberMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(inspectionMemberMapper.fromId(null)).isNull();
    }
}
