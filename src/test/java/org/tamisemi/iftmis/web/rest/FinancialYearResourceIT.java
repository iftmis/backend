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
import org.tamisemi.iftmis.repository.FinancialYearRepository;
import org.tamisemi.iftmis.service.FinancialYearService;
import org.tamisemi.iftmis.service.dto.FinancialYearDTO;
import org.tamisemi.iftmis.service.mapper.FinancialYearMapper;

/**
 * Integration tests for the {@link FinancialYearResource} REST controller.
 */
@SpringBootTest(classes = IftmisApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class FinancialYearResourceIT {
    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_START_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_START_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_END_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_END_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final Boolean DEFAULT_IS_OPENED = false;
    private static final Boolean UPDATED_IS_OPENED = true;

    @Autowired
    private FinancialYearRepository financialYearRepository;

    @Autowired
    private FinancialYearMapper financialYearMapper;

    @Autowired
    private FinancialYearService financialYearService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restFinancialYearMockMvc;

    private FinancialYear financialYear;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FinancialYear createEntity(EntityManager em) {
        FinancialYear financialYear = new FinancialYear()
            .name(DEFAULT_NAME)
            .startDate(DEFAULT_START_DATE)
            .endDate(DEFAULT_END_DATE)
            .isOpened(DEFAULT_IS_OPENED);
        return financialYear;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FinancialYear createUpdatedEntity(EntityManager em) {
        FinancialYear financialYear = new FinancialYear()
            .name(UPDATED_NAME)
            .startDate(UPDATED_START_DATE)
            .endDate(UPDATED_END_DATE)
            .isOpened(UPDATED_IS_OPENED);
        return financialYear;
    }

    @BeforeEach
    public void initTest() {
        financialYear = createEntity(em);
    }

    @Test
    @Transactional
    public void createFinancialYear() throws Exception {
        int databaseSizeBeforeCreate = financialYearRepository.findAll().size();
        // Create the FinancialYear
        FinancialYearDTO financialYearDTO = financialYearMapper.toDto(financialYear);
        restFinancialYearMockMvc
            .perform(
                post("/api/financial-years")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(financialYearDTO))
            )
            .andExpect(status().isCreated());

        // Validate the FinancialYear in the database
        List<FinancialYear> financialYearList = financialYearRepository.findAll();
        assertThat(financialYearList).hasSize(databaseSizeBeforeCreate + 1);
        FinancialYear testFinancialYear = financialYearList.get(financialYearList.size() - 1);
        assertThat(testFinancialYear.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testFinancialYear.getStartDate()).isEqualTo(DEFAULT_START_DATE);
        assertThat(testFinancialYear.getEndDate()).isEqualTo(DEFAULT_END_DATE);
        assertThat(testFinancialYear.isIsOpened()).isEqualTo(DEFAULT_IS_OPENED);
    }

    @Test
    @Transactional
    public void createFinancialYearWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = financialYearRepository.findAll().size();

        // Create the FinancialYear with an existing ID
        financialYear.setId(1L);
        FinancialYearDTO financialYearDTO = financialYearMapper.toDto(financialYear);

        // An entity with an existing ID cannot be created, so this API call must fail
        restFinancialYearMockMvc
            .perform(
                post("/api/financial-years")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(financialYearDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the FinancialYear in the database
        List<FinancialYear> financialYearList = financialYearRepository.findAll();
        assertThat(financialYearList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = financialYearRepository.findAll().size();
        // set the field null
        financialYear.setName(null);

        // Create the FinancialYear, which fails.
        FinancialYearDTO financialYearDTO = financialYearMapper.toDto(financialYear);

        restFinancialYearMockMvc
            .perform(
                post("/api/financial-years")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(financialYearDTO))
            )
            .andExpect(status().isBadRequest());

        List<FinancialYear> financialYearList = financialYearRepository.findAll();
        assertThat(financialYearList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStartDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = financialYearRepository.findAll().size();
        // set the field null
        financialYear.setStartDate(null);

        // Create the FinancialYear, which fails.
        FinancialYearDTO financialYearDTO = financialYearMapper.toDto(financialYear);

        restFinancialYearMockMvc
            .perform(
                post("/api/financial-years")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(financialYearDTO))
            )
            .andExpect(status().isBadRequest());

        List<FinancialYear> financialYearList = financialYearRepository.findAll();
        assertThat(financialYearList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEndDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = financialYearRepository.findAll().size();
        // set the field null
        financialYear.setEndDate(null);

        // Create the FinancialYear, which fails.
        FinancialYearDTO financialYearDTO = financialYearMapper.toDto(financialYear);

        restFinancialYearMockMvc
            .perform(
                post("/api/financial-years")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(financialYearDTO))
            )
            .andExpect(status().isBadRequest());

        List<FinancialYear> financialYearList = financialYearRepository.findAll();
        assertThat(financialYearList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIsOpenedIsRequired() throws Exception {
        int databaseSizeBeforeTest = financialYearRepository.findAll().size();
        // set the field null
        financialYear.setIsOpened(null);

        // Create the FinancialYear, which fails.
        FinancialYearDTO financialYearDTO = financialYearMapper.toDto(financialYear);

        restFinancialYearMockMvc
            .perform(
                post("/api/financial-years")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(financialYearDTO))
            )
            .andExpect(status().isBadRequest());

        List<FinancialYear> financialYearList = financialYearRepository.findAll();
        assertThat(financialYearList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllFinancialYears() throws Exception {
        // Initialize the database
        financialYearRepository.saveAndFlush(financialYear);

        // Get all the financialYearList
        restFinancialYearMockMvc
            .perform(get("/api/financial-years?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(financialYear.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].startDate").value(hasItem(DEFAULT_START_DATE.toString())))
            .andExpect(jsonPath("$.[*].endDate").value(hasItem(DEFAULT_END_DATE.toString())))
            .andExpect(jsonPath("$.[*].isOpened").value(hasItem(DEFAULT_IS_OPENED.booleanValue())));
    }

    @Test
    @Transactional
    public void getFinancialYear() throws Exception {
        // Initialize the database
        financialYearRepository.saveAndFlush(financialYear);

        // Get the financialYear
        restFinancialYearMockMvc
            .perform(get("/api/financial-years/{id}", financialYear.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(financialYear.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.startDate").value(DEFAULT_START_DATE.toString()))
            .andExpect(jsonPath("$.endDate").value(DEFAULT_END_DATE.toString()))
            .andExpect(jsonPath("$.isOpened").value(DEFAULT_IS_OPENED.booleanValue()));
    }

    @Test
    @Transactional
    public void getNonExistingFinancialYear() throws Exception {
        // Get the financialYear
        restFinancialYearMockMvc.perform(get("/api/financial-years/{id}", Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateFinancialYear() throws Exception {
        // Initialize the database
        financialYearRepository.saveAndFlush(financialYear);

        int databaseSizeBeforeUpdate = financialYearRepository.findAll().size();

        // Update the financialYear
        FinancialYear updatedFinancialYear = financialYearRepository.findById(financialYear.getId()).get();
        // Disconnect from session so that the updates on updatedFinancialYear are not directly saved in db
        em.detach(updatedFinancialYear);
        updatedFinancialYear.name(UPDATED_NAME).startDate(UPDATED_START_DATE).endDate(UPDATED_END_DATE).isOpened(UPDATED_IS_OPENED);
        FinancialYearDTO financialYearDTO = financialYearMapper.toDto(updatedFinancialYear);

        restFinancialYearMockMvc
            .perform(
                put("/api/financial-years")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(financialYearDTO))
            )
            .andExpect(status().isOk());

        // Validate the FinancialYear in the database
        List<FinancialYear> financialYearList = financialYearRepository.findAll();
        assertThat(financialYearList).hasSize(databaseSizeBeforeUpdate);
        FinancialYear testFinancialYear = financialYearList.get(financialYearList.size() - 1);
        assertThat(testFinancialYear.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testFinancialYear.getStartDate()).isEqualTo(UPDATED_START_DATE);
        assertThat(testFinancialYear.getEndDate()).isEqualTo(UPDATED_END_DATE);
        assertThat(testFinancialYear.isIsOpened()).isEqualTo(UPDATED_IS_OPENED);
    }

    @Test
    @Transactional
    public void updateNonExistingFinancialYear() throws Exception {
        int databaseSizeBeforeUpdate = financialYearRepository.findAll().size();

        // Create the FinancialYear
        FinancialYearDTO financialYearDTO = financialYearMapper.toDto(financialYear);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFinancialYearMockMvc
            .perform(
                put("/api/financial-years")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(financialYearDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the FinancialYear in the database
        List<FinancialYear> financialYearList = financialYearRepository.findAll();
        assertThat(financialYearList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteFinancialYear() throws Exception {
        // Initialize the database
        financialYearRepository.saveAndFlush(financialYear);

        int databaseSizeBeforeDelete = financialYearRepository.findAll().size();

        // Delete the financialYear
        restFinancialYearMockMvc
            .perform(delete("/api/financial-years/{id}", financialYear.getId()).with(csrf()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<FinancialYear> financialYearList = financialYearRepository.findAll();
        assertThat(financialYearList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
