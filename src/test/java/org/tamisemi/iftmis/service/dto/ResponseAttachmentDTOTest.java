package org.tamisemi.iftmis.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.tamisemi.iftmis.web.rest.TestUtil;

public class ResponseAttachmentDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ResponseAttachmentDTO.class);
        ResponseAttachmentDTO responseAttachmentDTO1 = new ResponseAttachmentDTO();
        responseAttachmentDTO1.setId(1L);
        ResponseAttachmentDTO responseAttachmentDTO2 = new ResponseAttachmentDTO();
        assertThat(responseAttachmentDTO1).isNotEqualTo(responseAttachmentDTO2);
        responseAttachmentDTO2.setId(responseAttachmentDTO1.getId());
        assertThat(responseAttachmentDTO1).isEqualTo(responseAttachmentDTO2);
        responseAttachmentDTO2.setId(2L);
        assertThat(responseAttachmentDTO1).isNotEqualTo(responseAttachmentDTO2);
        responseAttachmentDTO1.setId(null);
        assertThat(responseAttachmentDTO1).isNotEqualTo(responseAttachmentDTO2);
    }
}
