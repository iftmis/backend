package org.tamisemi.iftmis.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.tamisemi.iftmis.web.rest.TestUtil;

public class ResponseAttachmentTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ResponseAttachment.class);
        ResponseAttachment responseAttachment1 = new ResponseAttachment();
        responseAttachment1.setId(1L);
        ResponseAttachment responseAttachment2 = new ResponseAttachment();
        responseAttachment2.setId(responseAttachment1.getId());
        assertThat(responseAttachment1).isEqualTo(responseAttachment2);
        responseAttachment2.setId(2L);
        assertThat(responseAttachment1).isNotEqualTo(responseAttachment2);
        responseAttachment1.setId(null);
        assertThat(responseAttachment1).isNotEqualTo(responseAttachment2);
    }
}
