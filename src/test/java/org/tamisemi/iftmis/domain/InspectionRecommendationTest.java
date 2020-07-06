package org.tamisemi.iftmis.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.tamisemi.iftmis.web.rest.TestUtil;

public class InspectionRecommendationTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(InspectionRecommendation.class);
        InspectionRecommendation inspectionRecommendation1 = new InspectionRecommendation();
        inspectionRecommendation1.setId(1L);
        InspectionRecommendation inspectionRecommendation2 = new InspectionRecommendation();
        inspectionRecommendation2.setId(inspectionRecommendation1.getId());
        assertThat(inspectionRecommendation1).isEqualTo(inspectionRecommendation2);
        inspectionRecommendation2.setId(2L);
        assertThat(inspectionRecommendation1).isNotEqualTo(inspectionRecommendation2);
        inspectionRecommendation1.setId(null);
        assertThat(inspectionRecommendation1).isNotEqualTo(inspectionRecommendation2);
    }
}
