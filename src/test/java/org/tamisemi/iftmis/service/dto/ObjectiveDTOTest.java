package org.tamisemi.iftmis.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.tamisemi.iftmis.web.rest.TestUtil;

public class ObjectiveDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ObjectiveDTO.class);
        ObjectiveDTO objectiveDTO1 = new ObjectiveDTO();
        objectiveDTO1.setId(1L);
        ObjectiveDTO objectiveDTO2 = new ObjectiveDTO();
        assertThat(objectiveDTO1).isNotEqualTo(objectiveDTO2);
        objectiveDTO2.setId(objectiveDTO1.getId());
        assertThat(objectiveDTO1).isEqualTo(objectiveDTO2);
        objectiveDTO2.setId(2L);
        assertThat(objectiveDTO1).isNotEqualTo(objectiveDTO2);
        objectiveDTO1.setId(null);
        assertThat(objectiveDTO1).isNotEqualTo(objectiveDTO2);
    }
}
