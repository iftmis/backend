package org.tamisemi.iftmis.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.tamisemi.iftmis.web.rest.TestUtil;

public class FileResourceDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(FileResourceDTO.class);
        FileResourceDTO fileResourceDTO1 = new FileResourceDTO();
        fileResourceDTO1.setId(1L);
        FileResourceDTO fileResourceDTO2 = new FileResourceDTO();
        assertThat(fileResourceDTO1).isNotEqualTo(fileResourceDTO2);
        fileResourceDTO2.setId(fileResourceDTO1.getId());
        assertThat(fileResourceDTO1).isEqualTo(fileResourceDTO2);
        fileResourceDTO2.setId(2L);
        assertThat(fileResourceDTO1).isNotEqualTo(fileResourceDTO2);
        fileResourceDTO1.setId(null);
        assertThat(fileResourceDTO1).isNotEqualTo(fileResourceDTO2);
    }
}
