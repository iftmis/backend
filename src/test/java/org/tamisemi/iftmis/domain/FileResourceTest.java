package org.tamisemi.iftmis.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.tamisemi.iftmis.web.rest.TestUtil;

public class FileResourceTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(FileResource.class);
        FileResource fileResource1 = new FileResource();
        fileResource1.setId(1L);
        FileResource fileResource2 = new FileResource();
        fileResource2.setId(fileResource1.getId());
        assertThat(fileResource1).isEqualTo(fileResource2);
        fileResource2.setId(2L);
        assertThat(fileResource1).isNotEqualTo(fileResource2);
        fileResource1.setId(null);
        assertThat(fileResource1).isNotEqualTo(fileResource2);
    }
}
