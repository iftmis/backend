package org.tamisemi.iftmis.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.tamisemi.iftmis.web.rest.TestUtil;

public class FindingResponseTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(FindingResponse.class);
        FindingResponse findingResponse1 = new FindingResponse();
        findingResponse1.setId(1L);
        FindingResponse findingResponse2 = new FindingResponse();
        findingResponse2.setId(findingResponse1.getId());
        assertThat(findingResponse1).isEqualTo(findingResponse2);
        findingResponse2.setId(2L);
        assertThat(findingResponse1).isNotEqualTo(findingResponse2);
        findingResponse1.setId(null);
        assertThat(findingResponse1).isNotEqualTo(findingResponse2);
    }
}
