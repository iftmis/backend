package org.tamisemi.iftmis.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.tamisemi.iftmis.web.rest.TestUtil;

public class ObjectiveTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Objective.class);
        Objective objective1 = new Objective();
        objective1.setId(1L);
        Objective objective2 = new Objective();
        objective2.setId(objective1.getId());
        assertThat(objective1).isEqualTo(objective2);
        objective2.setId(2L);
        assertThat(objective1).isNotEqualTo(objective2);
        objective1.setId(null);
        assertThat(objective1).isNotEqualTo(objective2);
    }
}
