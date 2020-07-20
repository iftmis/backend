package org.tamisemi.iftmis.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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
import org.tamisemi.iftmis.IftmisApp;
import org.tamisemi.iftmis.domain.FinancialYear;
import org.tamisemi.iftmis.domain.InspectionPlan;
import org.tamisemi.iftmis.domain.OrganisationUnit;
import org.tamisemi.iftmis.repository.InspectionPlanRepository;
import org.tamisemi.iftmis.service.InspectionPlanService;
import org.tamisemi.iftmis.service.dto.InspectionPlanDTO;
import org.tamisemi.iftmis.service.mapper.InspectionPlanMapper;

/**
 * Integration tests for the {@link InspectionPlanResource} REST controller.
 */
@SpringBootTest(classes = IftmisApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class InspectionPlanResourceIT {
    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    @Autowired
    private InspectionPlanRepository inspectionPlanRepository;

    @Autowired
    private InspectionPlanMapper inspectionPlanMapper;

    @Autowired
    private InspectionPlanService inspectionPlanService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restInspectionPlanMockMvc;

    private InspectionPlan inspectionPlan;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static InspectionPlan createEntity(EntityManager em) {
        InspectionPlan inspectionPlan = new InspectionPlan().name(DEFAULT_NAME);
        // Add required entity
        OrganisationUnit organisationUnit;
        if (TestUtil.findAll(em, OrganisationUnit.class).isEmpty()) {
            organisationUnit = OrganisationUnitResourceIT.createEntity(em);
            em.persist(organisationUnit);
            em.flush();
        } else {
            organisationUnit = TestUtil.findAll(em, OrganisationUnit.class).get(0);
        }
        inspectionPlan.setOrganisationUnit(organisationUnit);
        // Add required entity
        FinancialYear financialYear;
        if (TestUtil.findAll(em, FinancialYear.class).isEmpty()) {
            financialYear = FinancialYearResourceIT.createEntity(em);
            em.persist(financialYear);
            em.flush();
        } else {
            financialYear = TestUtil.findAll(em, FinancialYear.class).get(0);
        }
        inspectionPlan.setFinancialYear(financialYear);
        return inspectionPlan;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static InspectionPlan createUpdatedEntity(EntityManager em) {
        InspectionPlan inspectionPlan = new InspectionPlan().name(UPDATED_NAME);
        // Add required entity
        OrganisationUnit organisationUnit;
        if (TestUtil.findAll(em, OrganisationUnit.class).isEmpty()) {
            organisationUnit = OrganisationUnitResourceIT.createUpdatedEntity(em);
            em.persist(organisationUnit);
            em.flush();
        } else {
            organisationUnit = TestUtil.findAll(em, OrganisationUnit.class).get(0);
        }
        inspectionPlan.setOrganisationUnit(organisationUnit);
        // Add required entity
        FinancialYear financialYear;
        if (TestUtil.findAll(em, FinancialYear.class).isEmpty()) {
            financialYear = FinancialYearResourceIT.createUpdatedEntity(em);
            em.persist(financialYear);
            em.flush();
        } else {
            financialYear = TestUtil.findAll(em, FinancialYear.class).get(0);
        }
        inspectionPlan.setFinancialYear(financialYear);
        return inspectionPlan;
    }

    @BeforeEach
    public void initTest() {
        inspectionPlan = createEntity(em);
    }

    @Test
    @Transactional
    public void createInspectionPlan() throws Exception {
        int databaseSizeBeforeCreate = inspectionPlanRepository.findAll().size();
        // Create the InspectionPlan
        InspectionPlanDTO inspectionPlanDTO = inspectionPlanMapper.toDto(inspectionPlan);
        restInspectionPlanMockMvc
            .perform(
                post("/api/inspection-plans")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(inspectionPlanDTO))
            )
            .andExpect(status().isCreated());

        // Validate the InspectionPlan in the database
        List<InspectionPlan> inspectionPlanList = inspectionPlanRepository.findAll();
        assertThat(inspectionPlanList).hasSize(databaseSizeBeforeCreate + 1);
        InspectionPlan testInspectionPlan = inspectionPlanList.get(inspectionPlanList.size() - 1);
        assertThat(testInspectionPlan.getName()).isEqualTo(DEFAULT_NAME);
    }

    @Test
    @Transactional
    public void createInspectionPlanWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = inspectionPlanRepository.findAll().size();

        // Create the InspectionPlan with an existing ID
        inspectionPlan.setId(1L);
        InspectionPlanDTO inspectionPlanDTO = inspectionPlanMapper.toDto(inspectionPlan);

        // An entity with an existing ID cannot be created, so this API call must fail
        restInspectionPlanMockMvc
            .perform(
                post("/api/inspection-plans")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(inspectionPlanDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the InspectionPlan in the database
        List<InspectionPlan> inspectionPlanList = inspectionPlanRepository.findAll();
        assertThat(inspectionPlanList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = inspectionPlanRepository.findAll().size();
        // set the field null
        inspectionPlan.setName(null);

        // Create the InspectionPlan, which fails.
        InspectionPlanDTO inspectionPlanDTO = inspectionPlanMapper.toDto(inspectionPlan);

        restInspectionPlanMockMvc
            .perform(
                post("/api/inspection-plans")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(inspectionPlanDTO))
            )
            .andExpect(status().isBadRequest());

        List<InspectionPlan> inspectionPlanList = inspectionPlanRepository.findAll();
        assertThat(inspectionPlanList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllInspectionPlans() throws Exception {
        // Initialize the database
        inspectionPlanRepository.saveAndFlush(inspectionPlan);

        // Get all the inspectionPlanList
        restInspectionPlanMockMvc
            .perform(get("/api/inspection-plans?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(inspectionPlan.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)));
    }

    @Test
    @Transactional
    public void getInspectionPlan() throws Exception {
        // Initialize the database
        inspectionPlanRepository.saveAndFlush(inspectionPlan);

        // Get the inspectionPlan
        restInspectionPlanMockMvc
            .perform(get("/api/inspection-plans/{id}", inspectionPlan.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(inspectionPlan.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME));
    }

    @Test
    @Transactional
    public void getNonExistingInspectionPlan() throws Exception {
        // Get the inspectionPlan
        restInspectionPlanMockMvc.perform(get("/api/inspection-plans/{id}", Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateInspectionPlan() throws Exception {
        // Initialize the database
        inspectionPlanRepository.saveAndFlush(inspectionPlan);

        int databaseSizeBeforeUpdate = inspectionPlanRepository.findAll().size();

        // Update the inspectionPlan
        InspectionPlan updatedInspectionPlan = inspectionPlanRepository.findById(inspectionPlan.getId()).get();
        // Disconnect from session so that the updates on updatedInspectionPlan are not directly saved in db
        em.detach(updatedInspectionPlan);
        updatedInspectionPlan.name(UPDATED_NAME);
        InspectionPlanDTO inspectionPlanDTO = inspectionPlanMapper.toDto(updatedInspectionPlan);

        restInspectionPlanMockMvc
            .perform(
                put("/api/inspection-plans")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(inspectionPlanDTO))
            )
            .andExpect(status().isOk());

        // Validate the InspectionPlan in the database
        List<InspectionPlan> inspectionPlanList = inspectionPlanRepository.findAll();
        assertThat(inspectionPlanList).hasSize(databaseSizeBeforeUpdate);
        InspectionPlan testInspectionPlan = inspectionPlanList.get(inspectionPlanList.size() - 1);
        assertThat(testInspectionPlan.getName()).isEqualTo(UPDATED_NAME);
    }

    @Test
    @Transactional
    public void updateNonExistingInspectionPlan() throws Exception {
        int databaseSizeBeforeUpdate = inspectionPlanRepository.findAll().size();

        // Create the InspectionPlan
        InspectionPlanDTO inspectionPlanDTO = inspectionPlanMapper.toDto(inspectionPlan);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restInspectionPlanMockMvc
            .perform(
                put("/api/inspection-plans")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(inspectionPlanDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the InspectionPlan in the database
        List<InspectionPlan> inspectionPlanList = inspectionPlanRepository.findAll();
        assertThat(inspectionPlanList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteInspectionPlan() throws Exception {
        // Initialize the database
        inspectionPlanRepository.saveAndFlush(inspectionPlan);

        int databaseSizeBeforeDelete = inspectionPlanRepository.findAll().size();

        // Delete the inspectionPlan
        restInspectionPlanMockMvc
            .perform(delete("/api/inspection-plans/{id}", inspectionPlan.getId()).with(csrf()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<InspectionPlan> inspectionPlanList = inspectionPlanRepository.findAll();
        assertThat(inspectionPlanList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
