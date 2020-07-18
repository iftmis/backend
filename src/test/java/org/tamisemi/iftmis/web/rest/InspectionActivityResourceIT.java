package org.tamisemi.iftmis.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import org.tamisemi.iftmis.IftmisApp;
import org.tamisemi.iftmis.domain.AuditableArea;
import org.tamisemi.iftmis.domain.InspectionActivity;
import org.tamisemi.iftmis.domain.InspectionPlan;
import org.tamisemi.iftmis.domain.Objective;
import org.tamisemi.iftmis.domain.OrganisationUnit;
import org.tamisemi.iftmis.domain.Risk;
import org.tamisemi.iftmis.domain.SubArea;
import org.tamisemi.iftmis.repository.InspectionActivityRepository;
import org.tamisemi.iftmis.service.InspectionActivityQueryService;
import org.tamisemi.iftmis.service.InspectionActivityService;
import org.tamisemi.iftmis.service.dto.InspectionActivityCriteria;
import org.tamisemi.iftmis.service.dto.InspectionActivityDTO;
import org.tamisemi.iftmis.service.mapper.InspectionActivityMapper;

/**
 * Integration tests for the {@link InspectionActivityResource} REST controller.
 */
@SpringBootTest(classes = IftmisApp.class)
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
public class InspectionActivityResourceIT {
    private static final Integer DEFAULT_DAYS = 1;
    private static final Integer UPDATED_DAYS = 2;
    private static final Integer SMALLER_DAYS = 1 - 1;

    @Autowired
    private InspectionActivityRepository inspectionActivityRepository;

    @Mock
    private InspectionActivityRepository inspectionActivityRepositoryMock;

    @Autowired
    private InspectionActivityMapper inspectionActivityMapper;

    @Mock
    private InspectionActivityService inspectionActivityServiceMock;

    @Autowired
    private InspectionActivityService inspectionActivityService;

    @Autowired
    private InspectionActivityQueryService inspectionActivityQueryService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restInspectionActivityMockMvc;

    private InspectionActivity inspectionActivity;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static InspectionActivity createEntity(EntityManager em) {
        InspectionActivity inspectionActivity = new InspectionActivity().days(DEFAULT_DAYS);
        // Add required entity
        InspectionPlan inspectionPlan;
        if (TestUtil.findAll(em, InspectionPlan.class).isEmpty()) {
            inspectionPlan = InspectionPlanResourceIT.createEntity(em);
            em.persist(inspectionPlan);
            em.flush();
        } else {
            inspectionPlan = TestUtil.findAll(em, InspectionPlan.class).get(0);
        }
        inspectionActivity.setInspectionPlan(inspectionPlan);
        // Add required entity
        Objective objective;
        if (TestUtil.findAll(em, Objective.class).isEmpty()) {
            objective = ObjectiveResourceIT.createEntity(em);
            em.persist(objective);
            em.flush();
        } else {
            objective = TestUtil.findAll(em, Objective.class).get(0);
        }
        inspectionActivity.setObjective(objective);
        // Add required entity
        AuditableArea auditableArea;
        if (TestUtil.findAll(em, AuditableArea.class).isEmpty()) {
            auditableArea = AuditableAreaResourceIT.createEntity(em);
            em.persist(auditableArea);
            em.flush();
        } else {
            auditableArea = TestUtil.findAll(em, AuditableArea.class).get(0);
        }
        inspectionActivity.setAuditableArea(auditableArea);
        // Add required entity
        SubArea subArea;
        if (TestUtil.findAll(em, SubArea.class).isEmpty()) {
            subArea = SubAreaResourceIT.createEntity(em);
            em.persist(subArea);
            em.flush();
        } else {
            subArea = TestUtil.findAll(em, SubArea.class).get(0);
        }
        inspectionActivity.setSubArea(subArea);
        return inspectionActivity;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static InspectionActivity createUpdatedEntity(EntityManager em) {
        InspectionActivity inspectionActivity = new InspectionActivity().days(UPDATED_DAYS);
        // Add required entity
        InspectionPlan inspectionPlan;
        if (TestUtil.findAll(em, InspectionPlan.class).isEmpty()) {
            inspectionPlan = InspectionPlanResourceIT.createUpdatedEntity(em);
            em.persist(inspectionPlan);
            em.flush();
        } else {
            inspectionPlan = TestUtil.findAll(em, InspectionPlan.class).get(0);
        }
        inspectionActivity.setInspectionPlan(inspectionPlan);
        // Add required entity
        Objective objective;
        if (TestUtil.findAll(em, Objective.class).isEmpty()) {
            objective = ObjectiveResourceIT.createUpdatedEntity(em);
            em.persist(objective);
            em.flush();
        } else {
            objective = TestUtil.findAll(em, Objective.class).get(0);
        }
        inspectionActivity.setObjective(objective);
        // Add required entity
        AuditableArea auditableArea;
        if (TestUtil.findAll(em, AuditableArea.class).isEmpty()) {
            auditableArea = AuditableAreaResourceIT.createUpdatedEntity(em);
            em.persist(auditableArea);
            em.flush();
        } else {
            auditableArea = TestUtil.findAll(em, AuditableArea.class).get(0);
        }
        inspectionActivity.setAuditableArea(auditableArea);
        // Add required entity
        SubArea subArea;
        if (TestUtil.findAll(em, SubArea.class).isEmpty()) {
            subArea = SubAreaResourceIT.createUpdatedEntity(em);
            em.persist(subArea);
            em.flush();
        } else {
            subArea = TestUtil.findAll(em, SubArea.class).get(0);
        }
        inspectionActivity.setSubArea(subArea);
        return inspectionActivity;
    }

    @BeforeEach
    public void initTest() {
        inspectionActivity = createEntity(em);
    }

    @Test
    @Transactional
    public void createInspectionActivity() throws Exception {
        int databaseSizeBeforeCreate = inspectionActivityRepository.findAll().size();
        // Create the InspectionActivity
        InspectionActivityDTO inspectionActivityDTO = inspectionActivityMapper.toDto(inspectionActivity);
        restInspectionActivityMockMvc
            .perform(
                post("/api/inspection-activities")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(inspectionActivityDTO))
            )
            .andExpect(status().isCreated());

        // Validate the InspectionActivity in the database
        List<InspectionActivity> inspectionActivityList = inspectionActivityRepository.findAll();
        assertThat(inspectionActivityList).hasSize(databaseSizeBeforeCreate + 1);
        InspectionActivity testInspectionActivity = inspectionActivityList.get(inspectionActivityList.size() - 1);
        assertThat(testInspectionActivity.getDays()).isEqualTo(DEFAULT_DAYS);
    }

    @Test
    @Transactional
    public void createInspectionActivityWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = inspectionActivityRepository.findAll().size();

        // Create the InspectionActivity with an existing ID
        inspectionActivity.setId(1L);
        InspectionActivityDTO inspectionActivityDTO = inspectionActivityMapper.toDto(inspectionActivity);

        // An entity with an existing ID cannot be created, so this API call must fail
        restInspectionActivityMockMvc
            .perform(
                post("/api/inspection-activities")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(inspectionActivityDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the InspectionActivity in the database
        List<InspectionActivity> inspectionActivityList = inspectionActivityRepository.findAll();
        assertThat(inspectionActivityList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkDaysIsRequired() throws Exception {
        int databaseSizeBeforeTest = inspectionActivityRepository.findAll().size();
        // set the field null
        inspectionActivity.setDays(null);

        // Create the InspectionActivity, which fails.
        InspectionActivityDTO inspectionActivityDTO = inspectionActivityMapper.toDto(inspectionActivity);

        restInspectionActivityMockMvc
            .perform(
                post("/api/inspection-activities")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(inspectionActivityDTO))
            )
            .andExpect(status().isBadRequest());

        List<InspectionActivity> inspectionActivityList = inspectionActivityRepository.findAll();
        assertThat(inspectionActivityList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllInspectionActivities() throws Exception {
        // Initialize the database
        inspectionActivityRepository.saveAndFlush(inspectionActivity);

        // Get all the inspectionActivityList
        restInspectionActivityMockMvc
            .perform(get("/api/inspection-activities?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(inspectionActivity.getId().intValue())))
            .andExpect(jsonPath("$.[*].days").value(hasItem(DEFAULT_DAYS)));
    }

    @SuppressWarnings({ "unchecked" })
    public void getAllInspectionActivitiesWithEagerRelationshipsIsEnabled() throws Exception {
        when(inspectionActivityServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restInspectionActivityMockMvc.perform(get("/api/inspection-activities?eagerload=true")).andExpect(status().isOk());

        verify(inspectionActivityServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({ "unchecked" })
    public void getAllInspectionActivitiesWithEagerRelationshipsIsNotEnabled() throws Exception {
        when(inspectionActivityServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restInspectionActivityMockMvc.perform(get("/api/inspection-activities?eagerload=true")).andExpect(status().isOk());

        verify(inspectionActivityServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @Test
    @Transactional
    public void getInspectionActivity() throws Exception {
        // Initialize the database
        inspectionActivityRepository.saveAndFlush(inspectionActivity);

        // Get the inspectionActivity
        restInspectionActivityMockMvc
            .perform(get("/api/inspection-activities/{id}", inspectionActivity.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(inspectionActivity.getId().intValue()))
            .andExpect(jsonPath("$.days").value(DEFAULT_DAYS));
    }

    @Test
    @Transactional
    public void getInspectionActivitiesByIdFiltering() throws Exception {
        // Initialize the database
        inspectionActivityRepository.saveAndFlush(inspectionActivity);

        Long id = inspectionActivity.getId();

        defaultInspectionActivityShouldBeFound("id.equals=" + id);
        defaultInspectionActivityShouldNotBeFound("id.notEquals=" + id);

        defaultInspectionActivityShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultInspectionActivityShouldNotBeFound("id.greaterThan=" + id);

        defaultInspectionActivityShouldBeFound("id.lessThanOrEqual=" + id);
        defaultInspectionActivityShouldNotBeFound("id.lessThan=" + id);
    }

    @Test
    @Transactional
    public void getAllInspectionActivitiesByDaysIsEqualToSomething() throws Exception {
        // Initialize the database
        inspectionActivityRepository.saveAndFlush(inspectionActivity);

        // Get all the inspectionActivityList where days equals to DEFAULT_DAYS
        defaultInspectionActivityShouldBeFound("days.equals=" + DEFAULT_DAYS);

        // Get all the inspectionActivityList where days equals to UPDATED_DAYS
        defaultInspectionActivityShouldNotBeFound("days.equals=" + UPDATED_DAYS);
    }

    @Test
    @Transactional
    public void getAllInspectionActivitiesByDaysIsNotEqualToSomething() throws Exception {
        // Initialize the database
        inspectionActivityRepository.saveAndFlush(inspectionActivity);

        // Get all the inspectionActivityList where days not equals to DEFAULT_DAYS
        defaultInspectionActivityShouldNotBeFound("days.notEquals=" + DEFAULT_DAYS);

        // Get all the inspectionActivityList where days not equals to UPDATED_DAYS
        defaultInspectionActivityShouldBeFound("days.notEquals=" + UPDATED_DAYS);
    }

    @Test
    @Transactional
    public void getAllInspectionActivitiesByDaysIsInShouldWork() throws Exception {
        // Initialize the database
        inspectionActivityRepository.saveAndFlush(inspectionActivity);

        // Get all the inspectionActivityList where days in DEFAULT_DAYS or UPDATED_DAYS
        defaultInspectionActivityShouldBeFound("days.in=" + DEFAULT_DAYS + "," + UPDATED_DAYS);

        // Get all the inspectionActivityList where days equals to UPDATED_DAYS
        defaultInspectionActivityShouldNotBeFound("days.in=" + UPDATED_DAYS);
    }

    @Test
    @Transactional
    public void getAllInspectionActivitiesByDaysIsNullOrNotNull() throws Exception {
        // Initialize the database
        inspectionActivityRepository.saveAndFlush(inspectionActivity);

        // Get all the inspectionActivityList where days is not null
        defaultInspectionActivityShouldBeFound("days.specified=true");

        // Get all the inspectionActivityList where days is null
        defaultInspectionActivityShouldNotBeFound("days.specified=false");
    }

    @Test
    @Transactional
    public void getAllInspectionActivitiesByDaysIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        inspectionActivityRepository.saveAndFlush(inspectionActivity);

        // Get all the inspectionActivityList where days is greater than or equal to DEFAULT_DAYS
        defaultInspectionActivityShouldBeFound("days.greaterThanOrEqual=" + DEFAULT_DAYS);

        // Get all the inspectionActivityList where days is greater than or equal to UPDATED_DAYS
        defaultInspectionActivityShouldNotBeFound("days.greaterThanOrEqual=" + UPDATED_DAYS);
    }

    @Test
    @Transactional
    public void getAllInspectionActivitiesByDaysIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        inspectionActivityRepository.saveAndFlush(inspectionActivity);

        // Get all the inspectionActivityList where days is less than or equal to DEFAULT_DAYS
        defaultInspectionActivityShouldBeFound("days.lessThanOrEqual=" + DEFAULT_DAYS);

        // Get all the inspectionActivityList where days is less than or equal to SMALLER_DAYS
        defaultInspectionActivityShouldNotBeFound("days.lessThanOrEqual=" + SMALLER_DAYS);
    }

    @Test
    @Transactional
    public void getAllInspectionActivitiesByDaysIsLessThanSomething() throws Exception {
        // Initialize the database
        inspectionActivityRepository.saveAndFlush(inspectionActivity);

        // Get all the inspectionActivityList where days is less than DEFAULT_DAYS
        defaultInspectionActivityShouldNotBeFound("days.lessThan=" + DEFAULT_DAYS);

        // Get all the inspectionActivityList where days is less than UPDATED_DAYS
        defaultInspectionActivityShouldBeFound("days.lessThan=" + UPDATED_DAYS);
    }

    @Test
    @Transactional
    public void getAllInspectionActivitiesByDaysIsGreaterThanSomething() throws Exception {
        // Initialize the database
        inspectionActivityRepository.saveAndFlush(inspectionActivity);

        // Get all the inspectionActivityList where days is greater than DEFAULT_DAYS
        defaultInspectionActivityShouldNotBeFound("days.greaterThan=" + DEFAULT_DAYS);

        // Get all the inspectionActivityList where days is greater than SMALLER_DAYS
        defaultInspectionActivityShouldBeFound("days.greaterThan=" + SMALLER_DAYS);
    }

    @Test
    @Transactional
    public void getAllInspectionActivitiesByInspectionPlanIsEqualToSomething() throws Exception {
        // Get already existing entity
        InspectionPlan inspectionPlan = inspectionActivity.getInspectionPlan();
        inspectionActivityRepository.saveAndFlush(inspectionActivity);
        Long inspectionPlanId = inspectionPlan.getId();

        // Get all the inspectionActivityList where inspectionPlan equals to inspectionPlanId
        defaultInspectionActivityShouldBeFound("inspectionPlanId.equals=" + inspectionPlanId);

        // Get all the inspectionActivityList where inspectionPlan equals to inspectionPlanId + 1
        defaultInspectionActivityShouldNotBeFound("inspectionPlanId.equals=" + (inspectionPlanId + 1));
    }

    @Test
    @Transactional
    public void getAllInspectionActivitiesByObjectiveIsEqualToSomething() throws Exception {
        // Get already existing entity
        Objective objective = inspectionActivity.getObjective();
        inspectionActivityRepository.saveAndFlush(inspectionActivity);
        Long objectiveId = objective.getId();

        // Get all the inspectionActivityList where objective equals to objectiveId
        defaultInspectionActivityShouldBeFound("objectiveId.equals=" + objectiveId);

        // Get all the inspectionActivityList where objective equals to objectiveId + 1
        defaultInspectionActivityShouldNotBeFound("objectiveId.equals=" + (objectiveId + 1));
    }

    @Test
    @Transactional
    public void getAllInspectionActivitiesByAuditableAreaIsEqualToSomething() throws Exception {
        // Get already existing entity
        AuditableArea auditableArea = inspectionActivity.getAuditableArea();
        inspectionActivityRepository.saveAndFlush(inspectionActivity);
        Long auditableAreaId = auditableArea.getId();

        // Get all the inspectionActivityList where auditableArea equals to auditableAreaId
        defaultInspectionActivityShouldBeFound("auditableAreaId.equals=" + auditableAreaId);

        // Get all the inspectionActivityList where auditableArea equals to auditableAreaId + 1
        defaultInspectionActivityShouldNotBeFound("auditableAreaId.equals=" + (auditableAreaId + 1));
    }

    @Test
    @Transactional
    public void getAllInspectionActivitiesBySubAreaIsEqualToSomething() throws Exception {
        // Get already existing entity
        SubArea subArea = inspectionActivity.getSubArea();
        inspectionActivityRepository.saveAndFlush(inspectionActivity);
        Long subAreaId = subArea.getId();

        // Get all the inspectionActivityList where subArea equals to subAreaId
        defaultInspectionActivityShouldBeFound("subAreaId.equals=" + subAreaId);

        // Get all the inspectionActivityList where subArea equals to subAreaId + 1
        defaultInspectionActivityShouldNotBeFound("subAreaId.equals=" + (subAreaId + 1));
    }

    @Test
    @Transactional
    public void getAllInspectionActivitiesByRiskIsEqualToSomething() throws Exception {
        // Initialize the database
        inspectionActivityRepository.saveAndFlush(inspectionActivity);
        Risk risk = RiskResourceIT.createEntity(em);
        em.persist(risk);
        em.flush();
        inspectionActivity.addRisk(risk);
        inspectionActivityRepository.saveAndFlush(inspectionActivity);
        Long riskId = risk.getId();

        // Get all the inspectionActivityList where risk equals to riskId
        defaultInspectionActivityShouldBeFound("riskId.equals=" + riskId);

        // Get all the inspectionActivityList where risk equals to riskId + 1
        defaultInspectionActivityShouldNotBeFound("riskId.equals=" + (riskId + 1));
    }

    @Test
    @Transactional
    public void getAllInspectionActivitiesByOrganisationUnitsIsEqualToSomething() throws Exception {
        // Initialize the database
        inspectionActivityRepository.saveAndFlush(inspectionActivity);
        OrganisationUnit organisationUnits = OrganisationUnitResourceIT.createEntity(em);
        em.persist(organisationUnits);
        em.flush();
        inspectionActivity.addOrganisationUnits(organisationUnits);
        inspectionActivityRepository.saveAndFlush(inspectionActivity);
        Long organisationUnitsId = organisationUnits.getId();

        // Get all the inspectionActivityList where organisationUnits equals to organisationUnitsId
        defaultInspectionActivityShouldBeFound("organisationUnitsId.equals=" + organisationUnitsId);

        // Get all the inspectionActivityList where organisationUnits equals to organisationUnitsId + 1
        defaultInspectionActivityShouldNotBeFound("organisationUnitsId.equals=" + (organisationUnitsId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultInspectionActivityShouldBeFound(String filter) throws Exception {
        restInspectionActivityMockMvc
            .perform(get("/api/inspection-activities?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(inspectionActivity.getId().intValue())))
            .andExpect(jsonPath("$.[*].days").value(hasItem(DEFAULT_DAYS)));

        // Check, that the count call also returns 1
        restInspectionActivityMockMvc
            .perform(get("/api/inspection-activities/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultInspectionActivityShouldNotBeFound(String filter) throws Exception {
        restInspectionActivityMockMvc
            .perform(get("/api/inspection-activities?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restInspectionActivityMockMvc
            .perform(get("/api/inspection-activities/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    public void getNonExistingInspectionActivity() throws Exception {
        // Get the inspectionActivity
        restInspectionActivityMockMvc.perform(get("/api/inspection-activities/{id}", Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateInspectionActivity() throws Exception {
        // Initialize the database
        inspectionActivityRepository.saveAndFlush(inspectionActivity);

        int databaseSizeBeforeUpdate = inspectionActivityRepository.findAll().size();

        // Update the inspectionActivity
        InspectionActivity updatedInspectionActivity = inspectionActivityRepository.findById(inspectionActivity.getId()).get();
        // Disconnect from session so that the updates on updatedInspectionActivity are not directly saved in db
        em.detach(updatedInspectionActivity);
        updatedInspectionActivity.days(UPDATED_DAYS);
        InspectionActivityDTO inspectionActivityDTO = inspectionActivityMapper.toDto(updatedInspectionActivity);

        restInspectionActivityMockMvc
            .perform(
                put("/api/inspection-activities")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(inspectionActivityDTO))
            )
            .andExpect(status().isOk());

        // Validate the InspectionActivity in the database
        List<InspectionActivity> inspectionActivityList = inspectionActivityRepository.findAll();
        assertThat(inspectionActivityList).hasSize(databaseSizeBeforeUpdate);
        InspectionActivity testInspectionActivity = inspectionActivityList.get(inspectionActivityList.size() - 1);
        assertThat(testInspectionActivity.getDays()).isEqualTo(UPDATED_DAYS);
    }

    @Test
    @Transactional
    public void updateNonExistingInspectionActivity() throws Exception {
        int databaseSizeBeforeUpdate = inspectionActivityRepository.findAll().size();

        // Create the InspectionActivity
        InspectionActivityDTO inspectionActivityDTO = inspectionActivityMapper.toDto(inspectionActivity);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restInspectionActivityMockMvc
            .perform(
                put("/api/inspection-activities")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(inspectionActivityDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the InspectionActivity in the database
        List<InspectionActivity> inspectionActivityList = inspectionActivityRepository.findAll();
        assertThat(inspectionActivityList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteInspectionActivity() throws Exception {
        // Initialize the database
        inspectionActivityRepository.saveAndFlush(inspectionActivity);

        int databaseSizeBeforeDelete = inspectionActivityRepository.findAll().size();

        // Delete the inspectionActivity
        restInspectionActivityMockMvc
            .perform(delete("/api/inspection-activities/{id}", inspectionActivity.getId()).with(csrf()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<InspectionActivity> inspectionActivityList = inspectionActivityRepository.findAll();
        assertThat(inspectionActivityList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
