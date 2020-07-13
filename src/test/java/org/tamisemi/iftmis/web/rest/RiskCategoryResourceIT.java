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
import org.tamisemi.iftmis.domain.RiskCategory;
import org.tamisemi.iftmis.repository.RiskCategoryRepository;
import org.tamisemi.iftmis.service.RiskCategoryService;
import org.tamisemi.iftmis.service.dto.RiskCategoryDTO;
import org.tamisemi.iftmis.service.mapper.RiskCategoryMapper;

/**
 * Integration tests for the {@link RiskCategoryResource} REST controller.
 */
@SpringBootTest(classes = IftmisApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class RiskCategoryResourceIT {
    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    @Autowired
    private RiskCategoryRepository riskCategoryRepository;

    @Autowired
    private RiskCategoryMapper riskCategoryMapper;

    @Autowired
    private RiskCategoryService riskCategoryService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restRiskCategoryMockMvc;

    private RiskCategory riskCategory;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static RiskCategory createEntity(EntityManager em) {
        RiskCategory riskCategory = new RiskCategory().code(DEFAULT_CODE).name(DEFAULT_NAME);
        return riskCategory;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static RiskCategory createUpdatedEntity(EntityManager em) {
        RiskCategory riskCategory = new RiskCategory().code(UPDATED_CODE).name(UPDATED_NAME);
        return riskCategory;
    }

    @BeforeEach
    public void initTest() {
        riskCategory = createEntity(em);
    }

    @Test
    @Transactional
    public void createRiskCategory() throws Exception {
        int databaseSizeBeforeCreate = riskCategoryRepository.findAll().size();
        // Create the RiskCategory
        RiskCategoryDTO riskCategoryDTO = riskCategoryMapper.toDto(riskCategory);
        restRiskCategoryMockMvc
            .perform(
                post("/api/risk-categories")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(riskCategoryDTO))
            )
            .andExpect(status().isCreated());

        // Validate the RiskCategory in the database
        List<RiskCategory> riskCategoryList = riskCategoryRepository.findAll();
        assertThat(riskCategoryList).hasSize(databaseSizeBeforeCreate + 1);
        RiskCategory testRiskCategory = riskCategoryList.get(riskCategoryList.size() - 1);
        assertThat(testRiskCategory.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testRiskCategory.getName()).isEqualTo(DEFAULT_NAME);
    }

    @Test
    @Transactional
    public void createRiskCategoryWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = riskCategoryRepository.findAll().size();

        // Create the RiskCategory with an existing ID
        riskCategory.setId(1L);
        RiskCategoryDTO riskCategoryDTO = riskCategoryMapper.toDto(riskCategory);

        // An entity with an existing ID cannot be created, so this API call must fail
        restRiskCategoryMockMvc
            .perform(
                post("/api/risk-categories")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(riskCategoryDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the RiskCategory in the database
        List<RiskCategory> riskCategoryList = riskCategoryRepository.findAll();
        assertThat(riskCategoryList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = riskCategoryRepository.findAll().size();
        // set the field null
        riskCategory.setName(null);

        // Create the RiskCategory, which fails.
        RiskCategoryDTO riskCategoryDTO = riskCategoryMapper.toDto(riskCategory);

        restRiskCategoryMockMvc
            .perform(
                post("/api/risk-categories")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(riskCategoryDTO))
            )
            .andExpect(status().isBadRequest());

        List<RiskCategory> riskCategoryList = riskCategoryRepository.findAll();
        assertThat(riskCategoryList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllRiskCategories() throws Exception {
        // Initialize the database
        riskCategoryRepository.saveAndFlush(riskCategory);

        // Get all the riskCategoryList
        restRiskCategoryMockMvc
            .perform(get("/api/risk-categories?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(riskCategory.getId().intValue())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)));
    }

    @Test
    @Transactional
    public void getRiskCategory() throws Exception {
        // Initialize the database
        riskCategoryRepository.saveAndFlush(riskCategory);

        // Get the riskCategory
        restRiskCategoryMockMvc
            .perform(get("/api/risk-categories/{id}", riskCategory.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(riskCategory.getId().intValue()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME));
    }

    @Test
    @Transactional
    public void getNonExistingRiskCategory() throws Exception {
        // Get the riskCategory
        restRiskCategoryMockMvc.perform(get("/api/risk-categories/{id}", Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateRiskCategory() throws Exception {
        // Initialize the database
        riskCategoryRepository.saveAndFlush(riskCategory);

        int databaseSizeBeforeUpdate = riskCategoryRepository.findAll().size();

        // Update the riskCategory
        RiskCategory updatedRiskCategory = riskCategoryRepository.findById(riskCategory.getId()).get();
        // Disconnect from session so that the updates on updatedRiskCategory are not directly saved in db
        em.detach(updatedRiskCategory);
        updatedRiskCategory.code(UPDATED_CODE).name(UPDATED_NAME);
        RiskCategoryDTO riskCategoryDTO = riskCategoryMapper.toDto(updatedRiskCategory);

        restRiskCategoryMockMvc
            .perform(
                put("/api/risk-categories")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(riskCategoryDTO))
            )
            .andExpect(status().isOk());

        // Validate the RiskCategory in the database
        List<RiskCategory> riskCategoryList = riskCategoryRepository.findAll();
        assertThat(riskCategoryList).hasSize(databaseSizeBeforeUpdate);
        RiskCategory testRiskCategory = riskCategoryList.get(riskCategoryList.size() - 1);
        assertThat(testRiskCategory.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testRiskCategory.getName()).isEqualTo(UPDATED_NAME);
    }

    @Test
    @Transactional
    public void updateNonExistingRiskCategory() throws Exception {
        int databaseSizeBeforeUpdate = riskCategoryRepository.findAll().size();

        // Create the RiskCategory
        RiskCategoryDTO riskCategoryDTO = riskCategoryMapper.toDto(riskCategory);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRiskCategoryMockMvc
            .perform(
                put("/api/risk-categories")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(riskCategoryDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the RiskCategory in the database
        List<RiskCategory> riskCategoryList = riskCategoryRepository.findAll();
        assertThat(riskCategoryList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteRiskCategory() throws Exception {
        // Initialize the database
        riskCategoryRepository.saveAndFlush(riskCategory);

        int databaseSizeBeforeDelete = riskCategoryRepository.findAll().size();

        // Delete the riskCategory
        restRiskCategoryMockMvc
            .perform(delete("/api/risk-categories/{id}", riskCategory.getId()).with(csrf()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<RiskCategory> riskCategoryList = riskCategoryRepository.findAll();
        assertThat(riskCategoryList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
