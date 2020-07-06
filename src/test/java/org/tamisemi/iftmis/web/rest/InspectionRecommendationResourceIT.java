package org.tamisemi.iftmis.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Base64Utils;
import org.tamisemi.iftmis.IftmisApp;
import org.tamisemi.iftmis.domain.InspectionRecommendation;
import org.tamisemi.iftmis.domain.enumeration.ImplementationStatus;
import org.tamisemi.iftmis.repository.InspectionRecommendationRepository;
import org.tamisemi.iftmis.service.InspectionRecommendationService;
import org.tamisemi.iftmis.service.dto.InspectionRecommendationDTO;
import org.tamisemi.iftmis.service.mapper.InspectionRecommendationMapper;

/**
 * Integration tests for the {@link InspectionRecommendationResource} REST controller.
 */
@SpringBootTest(classes = IftmisApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class InspectionRecommendationResourceIT {
    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final ImplementationStatus DEFAULT_IMPLEMENTATION_STATUS = ImplementationStatus.IMPLEMENTED;
    private static final ImplementationStatus UPDATED_IMPLEMENTATION_STATUS = ImplementationStatus.NOT_IMPLEMENTED;

    private static final LocalDate DEFAULT_COMPLETION_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_COMPLETION_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_COMPLIANCE_PLAN = "AAAAAAAAAA";
    private static final String UPDATED_COMPLIANCE_PLAN = "BBBBBBBBBB";

    @Autowired
    private InspectionRecommendationRepository inspectionRecommendationRepository;

    @Autowired
    private InspectionRecommendationMapper inspectionRecommendationMapper;

    @Autowired
    private InspectionRecommendationService inspectionRecommendationService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restInspectionRecommendationMockMvc;

    private InspectionRecommendation inspectionRecommendation;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static InspectionRecommendation createEntity(EntityManager em) {
        InspectionRecommendation inspectionRecommendation = new InspectionRecommendation()
            .description(DEFAULT_DESCRIPTION)
            .implementationStatus(DEFAULT_IMPLEMENTATION_STATUS)
            .completionDate(DEFAULT_COMPLETION_DATE)
            .compliancePlan(DEFAULT_COMPLIANCE_PLAN);
        return inspectionRecommendation;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static InspectionRecommendation createUpdatedEntity(EntityManager em) {
        InspectionRecommendation inspectionRecommendation = new InspectionRecommendation()
            .description(UPDATED_DESCRIPTION)
            .implementationStatus(UPDATED_IMPLEMENTATION_STATUS)
            .completionDate(UPDATED_COMPLETION_DATE)
            .compliancePlan(UPDATED_COMPLIANCE_PLAN);
        return inspectionRecommendation;
    }

    @BeforeEach
    public void initTest() {
        inspectionRecommendation = createEntity(em);
    }

    @Test
    @Transactional
    public void createInspectionRecommendation() throws Exception {
        int databaseSizeBeforeCreate = inspectionRecommendationRepository.findAll().size();
        // Create the InspectionRecommendation
        InspectionRecommendationDTO inspectionRecommendationDTO = inspectionRecommendationMapper.toDto(inspectionRecommendation);
        restInspectionRecommendationMockMvc
            .perform(
                post("/api/inspection-recommendations")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(inspectionRecommendationDTO))
            )
            .andExpect(status().isCreated());

        // Validate the InspectionRecommendation in the database
        List<InspectionRecommendation> inspectionRecommendationList = inspectionRecommendationRepository.findAll();
        assertThat(inspectionRecommendationList).hasSize(databaseSizeBeforeCreate + 1);
        InspectionRecommendation testInspectionRecommendation = inspectionRecommendationList.get(inspectionRecommendationList.size() - 1);
        assertThat(testInspectionRecommendation.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testInspectionRecommendation.getImplementationStatus()).isEqualTo(DEFAULT_IMPLEMENTATION_STATUS);
        assertThat(testInspectionRecommendation.getCompletionDate()).isEqualTo(DEFAULT_COMPLETION_DATE);
        assertThat(testInspectionRecommendation.getCompliancePlan()).isEqualTo(DEFAULT_COMPLIANCE_PLAN);
    }

    @Test
    @Transactional
    public void createInspectionRecommendationWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = inspectionRecommendationRepository.findAll().size();

        // Create the InspectionRecommendation with an existing ID
        inspectionRecommendation.setId(1L);
        InspectionRecommendationDTO inspectionRecommendationDTO = inspectionRecommendationMapper.toDto(inspectionRecommendation);

        // An entity with an existing ID cannot be created, so this API call must fail
        restInspectionRecommendationMockMvc
            .perform(
                post("/api/inspection-recommendations")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(inspectionRecommendationDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the InspectionRecommendation in the database
        List<InspectionRecommendation> inspectionRecommendationList = inspectionRecommendationRepository.findAll();
        assertThat(inspectionRecommendationList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkImplementationStatusIsRequired() throws Exception {
        int databaseSizeBeforeTest = inspectionRecommendationRepository.findAll().size();
        // set the field null
        inspectionRecommendation.setImplementationStatus(null);

        // Create the InspectionRecommendation, which fails.
        InspectionRecommendationDTO inspectionRecommendationDTO = inspectionRecommendationMapper.toDto(inspectionRecommendation);

        restInspectionRecommendationMockMvc
            .perform(
                post("/api/inspection-recommendations")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(inspectionRecommendationDTO))
            )
            .andExpect(status().isBadRequest());

        List<InspectionRecommendation> inspectionRecommendationList = inspectionRecommendationRepository.findAll();
        assertThat(inspectionRecommendationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllInspectionRecommendations() throws Exception {
        // Initialize the database
        inspectionRecommendationRepository.saveAndFlush(inspectionRecommendation);

        // Get all the inspectionRecommendationList
        restInspectionRecommendationMockMvc
            .perform(get("/api/inspection-recommendations?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(inspectionRecommendation.getId().intValue())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].implementationStatus").value(hasItem(DEFAULT_IMPLEMENTATION_STATUS.toString())))
            .andExpect(jsonPath("$.[*].completionDate").value(hasItem(DEFAULT_COMPLETION_DATE.toString())))
            .andExpect(jsonPath("$.[*].compliancePlan").value(hasItem(DEFAULT_COMPLIANCE_PLAN.toString())));
    }

    @Test
    @Transactional
    public void getInspectionRecommendation() throws Exception {
        // Initialize the database
        inspectionRecommendationRepository.saveAndFlush(inspectionRecommendation);

        // Get the inspectionRecommendation
        restInspectionRecommendationMockMvc
            .perform(get("/api/inspection-recommendations/{id}", inspectionRecommendation.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(inspectionRecommendation.getId().intValue()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.implementationStatus").value(DEFAULT_IMPLEMENTATION_STATUS.toString()))
            .andExpect(jsonPath("$.completionDate").value(DEFAULT_COMPLETION_DATE.toString()))
            .andExpect(jsonPath("$.compliancePlan").value(DEFAULT_COMPLIANCE_PLAN.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingInspectionRecommendation() throws Exception {
        // Get the inspectionRecommendation
        restInspectionRecommendationMockMvc
            .perform(get("/api/inspection-recommendations/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateInspectionRecommendation() throws Exception {
        // Initialize the database
        inspectionRecommendationRepository.saveAndFlush(inspectionRecommendation);

        int databaseSizeBeforeUpdate = inspectionRecommendationRepository.findAll().size();

        // Update the inspectionRecommendation
        InspectionRecommendation updatedInspectionRecommendation = inspectionRecommendationRepository
            .findById(inspectionRecommendation.getId())
            .get();
        // Disconnect from session so that the updates on updatedInspectionRecommendation are not directly saved in db
        em.detach(updatedInspectionRecommendation);
        updatedInspectionRecommendation
            .description(UPDATED_DESCRIPTION)
            .implementationStatus(UPDATED_IMPLEMENTATION_STATUS)
            .completionDate(UPDATED_COMPLETION_DATE)
            .compliancePlan(UPDATED_COMPLIANCE_PLAN);
        InspectionRecommendationDTO inspectionRecommendationDTO = inspectionRecommendationMapper.toDto(updatedInspectionRecommendation);

        restInspectionRecommendationMockMvc
            .perform(
                put("/api/inspection-recommendations")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(inspectionRecommendationDTO))
            )
            .andExpect(status().isOk());

        // Validate the InspectionRecommendation in the database
        List<InspectionRecommendation> inspectionRecommendationList = inspectionRecommendationRepository.findAll();
        assertThat(inspectionRecommendationList).hasSize(databaseSizeBeforeUpdate);
        InspectionRecommendation testInspectionRecommendation = inspectionRecommendationList.get(inspectionRecommendationList.size() - 1);
        assertThat(testInspectionRecommendation.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testInspectionRecommendation.getImplementationStatus()).isEqualTo(UPDATED_IMPLEMENTATION_STATUS);
        assertThat(testInspectionRecommendation.getCompletionDate()).isEqualTo(UPDATED_COMPLETION_DATE);
        assertThat(testInspectionRecommendation.getCompliancePlan()).isEqualTo(UPDATED_COMPLIANCE_PLAN);
    }

    @Test
    @Transactional
    public void updateNonExistingInspectionRecommendation() throws Exception {
        int databaseSizeBeforeUpdate = inspectionRecommendationRepository.findAll().size();

        // Create the InspectionRecommendation
        InspectionRecommendationDTO inspectionRecommendationDTO = inspectionRecommendationMapper.toDto(inspectionRecommendation);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restInspectionRecommendationMockMvc
            .perform(
                put("/api/inspection-recommendations")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(inspectionRecommendationDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the InspectionRecommendation in the database
        List<InspectionRecommendation> inspectionRecommendationList = inspectionRecommendationRepository.findAll();
        assertThat(inspectionRecommendationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteInspectionRecommendation() throws Exception {
        // Initialize the database
        inspectionRecommendationRepository.saveAndFlush(inspectionRecommendation);

        int databaseSizeBeforeDelete = inspectionRecommendationRepository.findAll().size();

        // Delete the inspectionRecommendation
        restInspectionRecommendationMockMvc
            .perform(
                delete("/api/inspection-recommendations/{id}", inspectionRecommendation.getId())
                    .with(csrf())
                    .accept(MediaType.APPLICATION_JSON)
            )
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<InspectionRecommendation> inspectionRecommendationList = inspectionRecommendationRepository.findAll();
        assertThat(inspectionRecommendationList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
