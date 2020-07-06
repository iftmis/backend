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
import org.tamisemi.iftmis.IftmisApp;
import org.tamisemi.iftmis.domain.FinancialYear;
import org.tamisemi.iftmis.domain.Inspection;
import org.tamisemi.iftmis.domain.OrganisationUnit;
import org.tamisemi.iftmis.domain.enumeration.InspectionType;
import org.tamisemi.iftmis.repository.InspectionRepository;
import org.tamisemi.iftmis.service.InspectionService;
import org.tamisemi.iftmis.service.dto.InspectionDTO;
import org.tamisemi.iftmis.service.mapper.InspectionMapper;

/**
 * Integration tests for the {@link InspectionResource} REST controller.
 */
@SpringBootTest(classes = IftmisApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class InspectionResourceIT {
    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_START_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_START_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_END_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_END_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final InspectionType DEFAULT_INSPECTION_TYPE = InspectionType.PLANNED;
    private static final InspectionType UPDATED_INSPECTION_TYPE = InspectionType.SPECIAL;

    @Autowired
    private InspectionRepository inspectionRepository;

    @Autowired
    private InspectionMapper inspectionMapper;

    @Autowired
    private InspectionService inspectionService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restInspectionMockMvc;

    private Inspection inspection;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Inspection createEntity(EntityManager em) {
        Inspection inspection = new Inspection()
            .name(DEFAULT_NAME)
            .startDate(DEFAULT_START_DATE)
            .endDate(DEFAULT_END_DATE)
            .inspectionType(DEFAULT_INSPECTION_TYPE);
        // Add required entity
        FinancialYear financialYear;
        if (TestUtil.findAll(em, FinancialYear.class).isEmpty()) {
            financialYear = FinancialYearResourceIT.createEntity(em);
            em.persist(financialYear);
            em.flush();
        } else {
            financialYear = TestUtil.findAll(em, FinancialYear.class).get(0);
        }
        inspection.setFinancialYear(financialYear);
        // Add required entity
        OrganisationUnit organisationUnit;
        if (TestUtil.findAll(em, OrganisationUnit.class).isEmpty()) {
            organisationUnit = OrganisationUnitResourceIT.createEntity(em);
            em.persist(organisationUnit);
            em.flush();
        } else {
            organisationUnit = TestUtil.findAll(em, OrganisationUnit.class).get(0);
        }
        inspection.setOrganisationUnit(organisationUnit);
        return inspection;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Inspection createUpdatedEntity(EntityManager em) {
        Inspection inspection = new Inspection()
            .name(UPDATED_NAME)
            .startDate(UPDATED_START_DATE)
            .endDate(UPDATED_END_DATE)
            .inspectionType(UPDATED_INSPECTION_TYPE);
        // Add required entity
        FinancialYear financialYear;
        if (TestUtil.findAll(em, FinancialYear.class).isEmpty()) {
            financialYear = FinancialYearResourceIT.createUpdatedEntity(em);
            em.persist(financialYear);
            em.flush();
        } else {
            financialYear = TestUtil.findAll(em, FinancialYear.class).get(0);
        }
        inspection.setFinancialYear(financialYear);
        // Add required entity
        OrganisationUnit organisationUnit;
        if (TestUtil.findAll(em, OrganisationUnit.class).isEmpty()) {
            organisationUnit = OrganisationUnitResourceIT.createUpdatedEntity(em);
            em.persist(organisationUnit);
            em.flush();
        } else {
            organisationUnit = TestUtil.findAll(em, OrganisationUnit.class).get(0);
        }
        inspection.setOrganisationUnit(organisationUnit);
        return inspection;
    }

    @BeforeEach
    public void initTest() {
        inspection = createEntity(em);
    }

    @Test
    @Transactional
    public void createInspection() throws Exception {
        int databaseSizeBeforeCreate = inspectionRepository.findAll().size();
        // Create the Inspection
        InspectionDTO inspectionDTO = inspectionMapper.toDto(inspection);
        restInspectionMockMvc
            .perform(
                post("/api/inspections")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(inspectionDTO))
            )
            .andExpect(status().isCreated());

        // Validate the Inspection in the database
        List<Inspection> inspectionList = inspectionRepository.findAll();
        assertThat(inspectionList).hasSize(databaseSizeBeforeCreate + 1);
        Inspection testInspection = inspectionList.get(inspectionList.size() - 1);
        assertThat(testInspection.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testInspection.getStartDate()).isEqualTo(DEFAULT_START_DATE);
        assertThat(testInspection.getEndDate()).isEqualTo(DEFAULT_END_DATE);
        assertThat(testInspection.getInspectionType()).isEqualTo(DEFAULT_INSPECTION_TYPE);
    }

    @Test
    @Transactional
    public void createInspectionWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = inspectionRepository.findAll().size();

        // Create the Inspection with an existing ID
        inspection.setId(1L);
        InspectionDTO inspectionDTO = inspectionMapper.toDto(inspection);

        // An entity with an existing ID cannot be created, so this API call must fail
        restInspectionMockMvc
            .perform(
                post("/api/inspections")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(inspectionDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Inspection in the database
        List<Inspection> inspectionList = inspectionRepository.findAll();
        assertThat(inspectionList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = inspectionRepository.findAll().size();
        // set the field null
        inspection.setName(null);

        // Create the Inspection, which fails.
        InspectionDTO inspectionDTO = inspectionMapper.toDto(inspection);

        restInspectionMockMvc
            .perform(
                post("/api/inspections")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(inspectionDTO))
            )
            .andExpect(status().isBadRequest());

        List<Inspection> inspectionList = inspectionRepository.findAll();
        assertThat(inspectionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStartDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = inspectionRepository.findAll().size();
        // set the field null
        inspection.setStartDate(null);

        // Create the Inspection, which fails.
        InspectionDTO inspectionDTO = inspectionMapper.toDto(inspection);

        restInspectionMockMvc
            .perform(
                post("/api/inspections")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(inspectionDTO))
            )
            .andExpect(status().isBadRequest());

        List<Inspection> inspectionList = inspectionRepository.findAll();
        assertThat(inspectionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEndDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = inspectionRepository.findAll().size();
        // set the field null
        inspection.setEndDate(null);

        // Create the Inspection, which fails.
        InspectionDTO inspectionDTO = inspectionMapper.toDto(inspection);

        restInspectionMockMvc
            .perform(
                post("/api/inspections")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(inspectionDTO))
            )
            .andExpect(status().isBadRequest());

        List<Inspection> inspectionList = inspectionRepository.findAll();
        assertThat(inspectionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkInspectionTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = inspectionRepository.findAll().size();
        // set the field null
        inspection.setInspectionType(null);

        // Create the Inspection, which fails.
        InspectionDTO inspectionDTO = inspectionMapper.toDto(inspection);

        restInspectionMockMvc
            .perform(
                post("/api/inspections")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(inspectionDTO))
            )
            .andExpect(status().isBadRequest());

        List<Inspection> inspectionList = inspectionRepository.findAll();
        assertThat(inspectionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllInspections() throws Exception {
        // Initialize the database
        inspectionRepository.saveAndFlush(inspection);

        // Get all the inspectionList
        restInspectionMockMvc
            .perform(get("/api/inspections?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(inspection.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].startDate").value(hasItem(DEFAULT_START_DATE.toString())))
            .andExpect(jsonPath("$.[*].endDate").value(hasItem(DEFAULT_END_DATE.toString())))
            .andExpect(jsonPath("$.[*].inspectionType").value(hasItem(DEFAULT_INSPECTION_TYPE.toString())));
    }

    @Test
    @Transactional
    public void getInspection() throws Exception {
        // Initialize the database
        inspectionRepository.saveAndFlush(inspection);

        // Get the inspection
        restInspectionMockMvc
            .perform(get("/api/inspections/{id}", inspection.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(inspection.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.startDate").value(DEFAULT_START_DATE.toString()))
            .andExpect(jsonPath("$.endDate").value(DEFAULT_END_DATE.toString()))
            .andExpect(jsonPath("$.inspectionType").value(DEFAULT_INSPECTION_TYPE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingInspection() throws Exception {
        // Get the inspection
        restInspectionMockMvc.perform(get("/api/inspections/{id}", Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateInspection() throws Exception {
        // Initialize the database
        inspectionRepository.saveAndFlush(inspection);

        int databaseSizeBeforeUpdate = inspectionRepository.findAll().size();

        // Update the inspection
        Inspection updatedInspection = inspectionRepository.findById(inspection.getId()).get();
        // Disconnect from session so that the updates on updatedInspection are not directly saved in db
        em.detach(updatedInspection);
        updatedInspection
            .name(UPDATED_NAME)
            .startDate(UPDATED_START_DATE)
            .endDate(UPDATED_END_DATE)
            .inspectionType(UPDATED_INSPECTION_TYPE);
        InspectionDTO inspectionDTO = inspectionMapper.toDto(updatedInspection);

        restInspectionMockMvc
            .perform(
                put("/api/inspections")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(inspectionDTO))
            )
            .andExpect(status().isOk());

        // Validate the Inspection in the database
        List<Inspection> inspectionList = inspectionRepository.findAll();
        assertThat(inspectionList).hasSize(databaseSizeBeforeUpdate);
        Inspection testInspection = inspectionList.get(inspectionList.size() - 1);
        assertThat(testInspection.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testInspection.getStartDate()).isEqualTo(UPDATED_START_DATE);
        assertThat(testInspection.getEndDate()).isEqualTo(UPDATED_END_DATE);
        assertThat(testInspection.getInspectionType()).isEqualTo(UPDATED_INSPECTION_TYPE);
    }

    @Test
    @Transactional
    public void updateNonExistingInspection() throws Exception {
        int databaseSizeBeforeUpdate = inspectionRepository.findAll().size();

        // Create the Inspection
        InspectionDTO inspectionDTO = inspectionMapper.toDto(inspection);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restInspectionMockMvc
            .perform(
                put("/api/inspections")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(inspectionDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Inspection in the database
        List<Inspection> inspectionList = inspectionRepository.findAll();
        assertThat(inspectionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteInspection() throws Exception {
        // Initialize the database
        inspectionRepository.saveAndFlush(inspection);

        int databaseSizeBeforeDelete = inspectionRepository.findAll().size();

        // Delete the inspection
        restInspectionMockMvc
            .perform(delete("/api/inspections/{id}", inspection.getId()).with(csrf()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Inspection> inspectionList = inspectionRepository.findAll();
        assertThat(inspectionList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
