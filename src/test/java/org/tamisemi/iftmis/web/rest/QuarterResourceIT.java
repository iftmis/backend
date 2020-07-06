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
import org.tamisemi.iftmis.domain.Quarter;
import org.tamisemi.iftmis.repository.QuarterRepository;
import org.tamisemi.iftmis.service.QuarterService;
import org.tamisemi.iftmis.service.dto.QuarterDTO;
import org.tamisemi.iftmis.service.mapper.QuarterMapper;

/**
 * Integration tests for the {@link QuarterResource} REST controller.
 */
@SpringBootTest(classes = IftmisApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class QuarterResourceIT {
    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_START_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_START_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_END_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_END_DATE = LocalDate.now(ZoneId.systemDefault());

    @Autowired
    private QuarterRepository quarterRepository;

    @Autowired
    private QuarterMapper quarterMapper;

    @Autowired
    private QuarterService quarterService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restQuarterMockMvc;

    private Quarter quarter;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Quarter createEntity(EntityManager em) {
        Quarter quarter = new Quarter().code(DEFAULT_CODE).name(DEFAULT_NAME).startDate(DEFAULT_START_DATE).endDate(DEFAULT_END_DATE);
        // Add required entity
        FinancialYear financialYear;
        if (TestUtil.findAll(em, FinancialYear.class).isEmpty()) {
            financialYear = FinancialYearResourceIT.createEntity(em);
            em.persist(financialYear);
            em.flush();
        } else {
            financialYear = TestUtil.findAll(em, FinancialYear.class).get(0);
        }
        quarter.setFinancialYear(financialYear);
        return quarter;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Quarter createUpdatedEntity(EntityManager em) {
        Quarter quarter = new Quarter().code(UPDATED_CODE).name(UPDATED_NAME).startDate(UPDATED_START_DATE).endDate(UPDATED_END_DATE);
        // Add required entity
        FinancialYear financialYear;
        if (TestUtil.findAll(em, FinancialYear.class).isEmpty()) {
            financialYear = FinancialYearResourceIT.createUpdatedEntity(em);
            em.persist(financialYear);
            em.flush();
        } else {
            financialYear = TestUtil.findAll(em, FinancialYear.class).get(0);
        }
        quarter.setFinancialYear(financialYear);
        return quarter;
    }

    @BeforeEach
    public void initTest() {
        quarter = createEntity(em);
    }

    @Test
    @Transactional
    public void createQuarter() throws Exception {
        int databaseSizeBeforeCreate = quarterRepository.findAll().size();
        // Create the Quarter
        QuarterDTO quarterDTO = quarterMapper.toDto(quarter);
        restQuarterMockMvc
            .perform(
                post("/api/quarters")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(quarterDTO))
            )
            .andExpect(status().isCreated());

        // Validate the Quarter in the database
        List<Quarter> quarterList = quarterRepository.findAll();
        assertThat(quarterList).hasSize(databaseSizeBeforeCreate + 1);
        Quarter testQuarter = quarterList.get(quarterList.size() - 1);
        assertThat(testQuarter.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testQuarter.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testQuarter.getStartDate()).isEqualTo(DEFAULT_START_DATE);
        assertThat(testQuarter.getEndDate()).isEqualTo(DEFAULT_END_DATE);
    }

    @Test
    @Transactional
    public void createQuarterWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = quarterRepository.findAll().size();

        // Create the Quarter with an existing ID
        quarter.setId(1L);
        QuarterDTO quarterDTO = quarterMapper.toDto(quarter);

        // An entity with an existing ID cannot be created, so this API call must fail
        restQuarterMockMvc
            .perform(
                post("/api/quarters")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(quarterDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Quarter in the database
        List<Quarter> quarterList = quarterRepository.findAll();
        assertThat(quarterList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = quarterRepository.findAll().size();
        // set the field null
        quarter.setName(null);

        // Create the Quarter, which fails.
        QuarterDTO quarterDTO = quarterMapper.toDto(quarter);

        restQuarterMockMvc
            .perform(
                post("/api/quarters")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(quarterDTO))
            )
            .andExpect(status().isBadRequest());

        List<Quarter> quarterList = quarterRepository.findAll();
        assertThat(quarterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStartDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = quarterRepository.findAll().size();
        // set the field null
        quarter.setStartDate(null);

        // Create the Quarter, which fails.
        QuarterDTO quarterDTO = quarterMapper.toDto(quarter);

        restQuarterMockMvc
            .perform(
                post("/api/quarters")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(quarterDTO))
            )
            .andExpect(status().isBadRequest());

        List<Quarter> quarterList = quarterRepository.findAll();
        assertThat(quarterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEndDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = quarterRepository.findAll().size();
        // set the field null
        quarter.setEndDate(null);

        // Create the Quarter, which fails.
        QuarterDTO quarterDTO = quarterMapper.toDto(quarter);

        restQuarterMockMvc
            .perform(
                post("/api/quarters")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(quarterDTO))
            )
            .andExpect(status().isBadRequest());

        List<Quarter> quarterList = quarterRepository.findAll();
        assertThat(quarterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllQuarters() throws Exception {
        // Initialize the database
        quarterRepository.saveAndFlush(quarter);

        // Get all the quarterList
        restQuarterMockMvc
            .perform(get("/api/quarters?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(quarter.getId().intValue())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].startDate").value(hasItem(DEFAULT_START_DATE.toString())))
            .andExpect(jsonPath("$.[*].endDate").value(hasItem(DEFAULT_END_DATE.toString())));
    }

    @Test
    @Transactional
    public void getQuarter() throws Exception {
        // Initialize the database
        quarterRepository.saveAndFlush(quarter);

        // Get the quarter
        restQuarterMockMvc
            .perform(get("/api/quarters/{id}", quarter.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(quarter.getId().intValue()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.startDate").value(DEFAULT_START_DATE.toString()))
            .andExpect(jsonPath("$.endDate").value(DEFAULT_END_DATE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingQuarter() throws Exception {
        // Get the quarter
        restQuarterMockMvc.perform(get("/api/quarters/{id}", Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateQuarter() throws Exception {
        // Initialize the database
        quarterRepository.saveAndFlush(quarter);

        int databaseSizeBeforeUpdate = quarterRepository.findAll().size();

        // Update the quarter
        Quarter updatedQuarter = quarterRepository.findById(quarter.getId()).get();
        // Disconnect from session so that the updates on updatedQuarter are not directly saved in db
        em.detach(updatedQuarter);
        updatedQuarter.code(UPDATED_CODE).name(UPDATED_NAME).startDate(UPDATED_START_DATE).endDate(UPDATED_END_DATE);
        QuarterDTO quarterDTO = quarterMapper.toDto(updatedQuarter);

        restQuarterMockMvc
            .perform(
                put("/api/quarters")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(quarterDTO))
            )
            .andExpect(status().isOk());

        // Validate the Quarter in the database
        List<Quarter> quarterList = quarterRepository.findAll();
        assertThat(quarterList).hasSize(databaseSizeBeforeUpdate);
        Quarter testQuarter = quarterList.get(quarterList.size() - 1);
        assertThat(testQuarter.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testQuarter.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testQuarter.getStartDate()).isEqualTo(UPDATED_START_DATE);
        assertThat(testQuarter.getEndDate()).isEqualTo(UPDATED_END_DATE);
    }

    @Test
    @Transactional
    public void updateNonExistingQuarter() throws Exception {
        int databaseSizeBeforeUpdate = quarterRepository.findAll().size();

        // Create the Quarter
        QuarterDTO quarterDTO = quarterMapper.toDto(quarter);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restQuarterMockMvc
            .perform(
                put("/api/quarters")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(quarterDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Quarter in the database
        List<Quarter> quarterList = quarterRepository.findAll();
        assertThat(quarterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteQuarter() throws Exception {
        // Initialize the database
        quarterRepository.saveAndFlush(quarter);

        int databaseSizeBeforeDelete = quarterRepository.findAll().size();

        // Delete the quarter
        restQuarterMockMvc
            .perform(delete("/api/quarters/{id}", quarter.getId()).with(csrf()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Quarter> quarterList = quarterRepository.findAll();
        assertThat(quarterList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
