package org.tamisemi.iftmis.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.tamisemi.iftmis.web.rest.TestUtil;

public class FindingResponseDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(FindingResponseDTO.class);
        FindingResponseDTO findingResponseDTO1 = new FindingResponseDTO();
        findingResponseDTO1.setId(1L);
        FindingResponseDTO findingResponseDTO2 = new FindingResponseDTO();
        assertThat(findingResponseDTO1).isNotEqualTo(findingResponseDTO2);
        findingResponseDTO2.setId(findingResponseDTO1.getId());
        assertThat(findingResponseDTO1).isEqualTo(findingResponseDTO2);
        findingResponseDTO2.setId(2L);
        assertThat(findingResponseDTO1).isNotEqualTo(findingResponseDTO2);
        findingResponseDTO1.setId(null);
        assertThat(findingResponseDTO1).isNotEqualTo(findingResponseDTO2);
    }
}
