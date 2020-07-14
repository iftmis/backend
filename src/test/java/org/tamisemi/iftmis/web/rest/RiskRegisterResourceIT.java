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
import org.tamisemi.iftmis.domain.OrganisationUnit;
import org.tamisemi.iftmis.domain.RiskRegister;
import org.tamisemi.iftmis.repository.RiskRegisterRepository;
import org.tamisemi.iftmis.service.RiskRegisterService;
import org.tamisemi.iftmis.service.dto.RiskRegisterDTO;
import org.tamisemi.iftmis.service.mapper.RiskRegisterMapper;

/**
 * Integration tests for the {@link RiskRegisterResource} REST controller.
 */
@SpringBootTest(classes = IftmisApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class RiskRegisterResourceIT {
    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final Boolean DEFAULT_IS_APPROVED = false;
    private static final Boolean UPDATED_IS_APPROVED = true;

    private static final LocalDate DEFAULT_APPROVED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_APPROVED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_APPROVED_BY = "AAAAAAAAAA";
    private static final String UPDATED_APPROVED_BY = "BBBBBBBBBB";

    @Autowired
    private RiskRegisterRepository riskRegisterRepository;

    @Autowired
    private RiskRegisterMapper riskRegisterMapper;

    @Autowired
    private RiskRegisterService riskRegisterService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restRiskRegisterMockMvc;

    private RiskRegister riskRegister;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static RiskRegister createEntity(EntityManager em) {
        RiskRegister riskRegister = new RiskRegister()
            .name(DEFAULT_NAME)
            .isApproved(DEFAULT_IS_APPROVED)
            .approvedDate(DEFAULT_APPROVED_DATE)
            .approvedBy(DEFAULT_APPROVED_BY);
        // Add required entity
        OrganisationUnit organisationUnit;
        if (TestUtil.findAll(em, OrganisationUnit.class).isEmpty()) {
            organisationUnit = OrganisationUnitResourceIT.createEntity(em);
            em.persist(organisationUnit);
            em.flush();
        } else {
            organisationUnit = TestUtil.findAll(em, OrganisationUnit.class).get(0);
        }
        riskRegister.setOrganisationUnit(organisationUnit);
        // Add required entity
        FinancialYear financialYear;
        if (TestUtil.findAll(em, FinancialYear.class).isEmpty()) {
            financialYear = FinancialYearResourceIT.createEntity(em);
            em.persist(financialYear);
            em.flush();
        } else {
            financialYear = TestUtil.findAll(em, FinancialYear.class).get(0);
        }
        riskRegister.setFinancialYear(financialYear);
        return riskRegister;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static RiskRegister createUpdatedEntity(EntityManager em) {
        RiskRegister riskRegister = new RiskRegister()
            .name(UPDATED_NAME)
            .isApproved(UPDATED_IS_APPROVED)
            .approvedDate(UPDATED_APPROVED_DATE)
            .approvedBy(UPDATED_APPROVED_BY);
        // Add required entity
        OrganisationUnit organisationUnit;
        if (TestUtil.findAll(em, OrganisationUnit.class).isEmpty()) {
            organisationUnit = OrganisationUnitResourceIT.createUpdatedEntity(em);
            em.persist(organisationUnit);
            em.flush();
        } else {
            organisationUnit = TestUtil.findAll(em, OrganisationUnit.class).get(0);
        }
        riskRegister.setOrganisationUnit(organisationUnit);
        // Add required entity
        FinancialYear financialYear;
        if (TestUtil.findAll(em, FinancialYear.class).isEmpty()) {
            financialYear = FinancialYearResourceIT.createUpdatedEntity(em);
            em.persist(financialYear);
            em.flush();
        } else {
            financialYear = TestUtil.findAll(em, FinancialYear.class).get(0);
        }
        riskRegister.setFinancialYear(financialYear);
        return riskRegister;
    }

    @BeforeEach
    public void initTest() {
        riskRegister = createEntity(em);
    }

    @Test
    @Transactional
    public void createRiskRegister() throws Exception {
        int databaseSizeBeforeCreate = riskRegisterRepository.findAll().size();
        // Create the RiskRegister
        RiskRegisterDTO riskRegisterDTO = riskRegisterMapper.toDto(riskRegister);
        restRiskRegisterMockMvc
            .perform(
                post("/api/risk-registers")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(riskRegisterDTO))
            )
            .andExpect(status().isCreated());

        // Validate the RiskRegister in the database
        List<RiskRegister> riskRegisterList = riskRegisterRepository.findAll();
        assertThat(riskRegisterList).hasSize(databaseSizeBeforeCreate + 1);
        RiskRegister testRiskRegister = riskRegisterList.get(riskRegisterList.size() - 1);
        assertThat(testRiskRegister.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testRiskRegister.isIsApproved()).isEqualTo(DEFAULT_IS_APPROVED);
        assertThat(testRiskRegister.getApprovedDate()).isEqualTo(DEFAULT_APPROVED_DATE);
        assertThat(testRiskRegister.getApprovedBy()).isEqualTo(DEFAULT_APPROVED_BY);
    }

    @Test
    @Transactional
    public void createRiskRegisterWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = riskRegisterRepository.findAll().size();

        // Create the RiskRegister with an existing ID
        riskRegister.setId(1L);
        RiskRegisterDTO riskRegisterDTO = riskRegisterMapper.toDto(riskRegister);

        // An entity with an existing ID cannot be created, so this API call must fail
        restRiskRegisterMockMvc
            .perform(
                post("/api/risk-registers")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(riskRegisterDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the RiskRegister in the database
        List<RiskRegister> riskRegisterList = riskRegisterRepository.findAll();
        assertThat(riskRegisterList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = riskRegisterRepository.findAll().size();
        // set the field null
        riskRegister.setName(null);

        // Create the RiskRegister, which fails.
        RiskRegisterDTO riskRegisterDTO = riskRegisterMapper.toDto(riskRegister);

        restRiskRegisterMockMvc
            .perform(
                post("/api/risk-registers")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(riskRegisterDTO))
            )
            .andExpect(status().isBadRequest());

        List<RiskRegister> riskRegisterList = riskRegisterRepository.findAll();
        assertThat(riskRegisterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllRiskRegisters() throws Exception {
        // Initialize the database
        riskRegisterRepository.saveAndFlush(riskRegister);

        // Get all the riskRegisterList
        restRiskRegisterMockMvc
            .perform(get("/api/risk-registers?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(riskRegister.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].isApproved").value(hasItem(DEFAULT_IS_APPROVED.booleanValue())))
            .andExpect(jsonPath("$.[*].approvedDate").value(hasItem(DEFAULT_APPROVED_DATE.toString())))
            .andExpect(jsonPath("$.[*].approvedBy").value(hasItem(DEFAULT_APPROVED_BY)));
    }

    @Test
    @Transactional
    public void getRiskRegister() throws Exception {
        // Initialize the database
        riskRegisterRepository.saveAndFlush(riskRegister);

        // Get the riskRegister
        restRiskRegisterMockMvc
            .perform(get("/api/risk-registers/{id}", riskRegister.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(riskRegister.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.isApproved").value(DEFAULT_IS_APPROVED.booleanValue()))
            .andExpect(jsonPath("$.approvedDate").value(DEFAULT_APPROVED_DATE.toString()))
            .andExpect(jsonPath("$.approvedBy").value(DEFAULT_APPROVED_BY));
    }

    @Test
    @Transactional
    public void getNonExistingRiskRegister() throws Exception {
        // Get the riskRegister
        restRiskRegisterMockMvc.perform(get("/api/risk-registers/{id}", Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateRiskRegister() throws Exception {
        // Initialize the database
        riskRegisterRepository.saveAndFlush(riskRegister);

        int databaseSizeBeforeUpdate = riskRegisterRepository.findAll().size();

        // Update the riskRegister
        RiskRegister updatedRiskRegister = riskRegisterRepository.findById(riskRegister.getId()).get();
        // Disconnect from session so that the updates on updatedRiskRegister are not directly saved in db
        em.detach(updatedRiskRegister);
        updatedRiskRegister
            .name(UPDATED_NAME)
            .isApproved(UPDATED_IS_APPROVED)
            .approvedDate(UPDATED_APPROVED_DATE)
            .approvedBy(UPDATED_APPROVED_BY);
        RiskRegisterDTO riskRegisterDTO = riskRegisterMapper.toDto(updatedRiskRegister);

        restRiskRegisterMockMvc
            .perform(
                put("/api/risk-registers")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(riskRegisterDTO))
            )
            .andExpect(status().isOk());

        // Validate the RiskRegister in the database
        List<RiskRegister> riskRegisterList = riskRegisterRepository.findAll();
        assertThat(riskRegisterList).hasSize(databaseSizeBeforeUpdate);
        RiskRegister testRiskRegister = riskRegisterList.get(riskRegisterList.size() - 1);
        assertThat(testRiskRegister.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testRiskRegister.isIsApproved()).isEqualTo(UPDATED_IS_APPROVED);
        assertThat(testRiskRegister.getApprovedDate()).isEqualTo(UPDATED_APPROVED_DATE);
        assertThat(testRiskRegister.getApprovedBy()).isEqualTo(UPDATED_APPROVED_BY);
    }

    @Test
    @Transactional
    public void updateNonExistingRiskRegister() throws Exception {
        int databaseSizeBeforeUpdate = riskRegisterRepository.findAll().size();

        // Create the RiskRegister
        RiskRegisterDTO riskRegisterDTO = riskRegisterMapper.toDto(riskRegister);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRiskRegisterMockMvc
            .perform(
                put("/api/risk-registers")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(riskRegisterDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the RiskRegister in the database
        List<RiskRegister> riskRegisterList = riskRegisterRepository.findAll();
        assertThat(riskRegisterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteRiskRegister() throws Exception {
        // Initialize the database
        riskRegisterRepository.saveAndFlush(riskRegister);

        int databaseSizeBeforeDelete = riskRegisterRepository.findAll().size();

        // Delete the riskRegister
        restRiskRegisterMockMvc
            .perform(delete("/api/risk-registers/{id}", riskRegister.getId()).with(csrf()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<RiskRegister> riskRegisterList = riskRegisterRepository.findAll();
        assertThat(riskRegisterList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
