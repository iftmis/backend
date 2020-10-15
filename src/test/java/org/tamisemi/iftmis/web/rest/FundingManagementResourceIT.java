package org.tamisemi.iftmis.web.rest;

import org.tamisemi.iftmis.IftmisApp;
import org.tamisemi.iftmis.domain.FundingManagement;
import org.tamisemi.iftmis.repository.FundingManagementRepository;
import org.tamisemi.iftmis.service.FundingManagementService;
import org.tamisemi.iftmis.service.dto.FundingManagementDTO;
import org.tamisemi.iftmis.service.mapper.FundingManagementMapper;
import org.tamisemi.iftmis.service.dto.FundingManagementCriteria;
import org.tamisemi.iftmis.service.FundingManagementQueryService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link FundingManagementResource} REST controller.
 */
@SpringBootTest(classes = IftmisApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class FundingManagementResourceIT {

    private static final String DEFAULT_TITLE = "AAAAAAAAAA";
    private static final String UPDATED_TITLE = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    @Autowired
    private FundingManagementRepository fundingManagementRepository;

    @Autowired
    private FundingManagementMapper fundingManagementMapper;

    @Autowired
    private FundingManagementService fundingManagementService;

    @Autowired
    private FundingManagementQueryService fundingManagementQueryService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restFundingManagementMockMvc;

    private FundingManagement fundingManagement;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FundingManagement createEntity(EntityManager em) {
        FundingManagement fundingManagement = new FundingManagement()
            .title(DEFAULT_TITLE)
            .description(DEFAULT_DESCRIPTION);
        return fundingManagement;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FundingManagement createUpdatedEntity(EntityManager em) {
        FundingManagement fundingManagement = new FundingManagement()
            .title(UPDATED_TITLE)
            .description(UPDATED_DESCRIPTION);
        return fundingManagement;
    }

    @BeforeEach
    public void initTest() {
        fundingManagement = createEntity(em);
    }

    @Test
    @Transactional
    public void createFundingManagement() throws Exception {
        int databaseSizeBeforeCreate = fundingManagementRepository.findAll().size();
        // Create the FundingManagement
        FundingManagementDTO fundingManagementDTO = fundingManagementMapper.toDto(fundingManagement);
        restFundingManagementMockMvc.perform(post("/api/funding-managements").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(fundingManagementDTO)))
            .andExpect(status().isCreated());

        // Validate the FundingManagement in the database
        List<FundingManagement> fundingManagementList = fundingManagementRepository.findAll();
        assertThat(fundingManagementList).hasSize(databaseSizeBeforeCreate + 1);
        FundingManagement testFundingManagement = fundingManagementList.get(fundingManagementList.size() - 1);
        assertThat(testFundingManagement.getTitle()).isEqualTo(DEFAULT_TITLE);
        assertThat(testFundingManagement.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
    }

    @Test
    @Transactional
    public void createFundingManagementWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = fundingManagementRepository.findAll().size();

        // Create the FundingManagement with an existing ID
        fundingManagement.setId(1L);
        FundingManagementDTO fundingManagementDTO = fundingManagementMapper.toDto(fundingManagement);

        // An entity with an existing ID cannot be created, so this API call must fail
        restFundingManagementMockMvc.perform(post("/api/funding-managements").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(fundingManagementDTO)))
            .andExpect(status().isBadRequest());

        // Validate the FundingManagement in the database
        List<FundingManagement> fundingManagementList = fundingManagementRepository.findAll();
        assertThat(fundingManagementList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllFundingManagements() throws Exception {
        // Initialize the database
        fundingManagementRepository.saveAndFlush(fundingManagement);

        // Get all the fundingManagementList
        restFundingManagementMockMvc.perform(get("/api/funding-managements?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(fundingManagement.getId().intValue())))
            .andExpect(jsonPath("$.[*].title").value(hasItem(DEFAULT_TITLE)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)));
    }
    
    @Test
    @Transactional
    public void getFundingManagement() throws Exception {
        // Initialize the database
        fundingManagementRepository.saveAndFlush(fundingManagement);

        // Get the fundingManagement
        restFundingManagementMockMvc.perform(get("/api/funding-managements/{id}", fundingManagement.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(fundingManagement.getId().intValue()))
            .andExpect(jsonPath("$.title").value(DEFAULT_TITLE))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION));
    }


    @Test
    @Transactional
    public void getFundingManagementsByIdFiltering() throws Exception {
        // Initialize the database
        fundingManagementRepository.saveAndFlush(fundingManagement);

        Long id = fundingManagement.getId();

        defaultFundingManagementShouldBeFound("id.equals=" + id);
        defaultFundingManagementShouldNotBeFound("id.notEquals=" + id);

        defaultFundingManagementShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultFundingManagementShouldNotBeFound("id.greaterThan=" + id);

        defaultFundingManagementShouldBeFound("id.lessThanOrEqual=" + id);
        defaultFundingManagementShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllFundingManagementsByTitleIsEqualToSomething() throws Exception {
        // Initialize the database
        fundingManagementRepository.saveAndFlush(fundingManagement);

        // Get all the fundingManagementList where title equals to DEFAULT_TITLE
        defaultFundingManagementShouldBeFound("title.equals=" + DEFAULT_TITLE);

        // Get all the fundingManagementList where title equals to UPDATED_TITLE
        defaultFundingManagementShouldNotBeFound("title.equals=" + UPDATED_TITLE);
    }

    @Test
    @Transactional
    public void getAllFundingManagementsByTitleIsNotEqualToSomething() throws Exception {
        // Initialize the database
        fundingManagementRepository.saveAndFlush(fundingManagement);

        // Get all the fundingManagementList where title not equals to DEFAULT_TITLE
        defaultFundingManagementShouldNotBeFound("title.notEquals=" + DEFAULT_TITLE);

        // Get all the fundingManagementList where title not equals to UPDATED_TITLE
        defaultFundingManagementShouldBeFound("title.notEquals=" + UPDATED_TITLE);
    }

    @Test
    @Transactional
    public void getAllFundingManagementsByTitleIsInShouldWork() throws Exception {
        // Initialize the database
        fundingManagementRepository.saveAndFlush(fundingManagement);

        // Get all the fundingManagementList where title in DEFAULT_TITLE or UPDATED_TITLE
        defaultFundingManagementShouldBeFound("title.in=" + DEFAULT_TITLE + "," + UPDATED_TITLE);

        // Get all the fundingManagementList where title equals to UPDATED_TITLE
        defaultFundingManagementShouldNotBeFound("title.in=" + UPDATED_TITLE);
    }

    @Test
    @Transactional
    public void getAllFundingManagementsByTitleIsNullOrNotNull() throws Exception {
        // Initialize the database
        fundingManagementRepository.saveAndFlush(fundingManagement);

        // Get all the fundingManagementList where title is not null
        defaultFundingManagementShouldBeFound("title.specified=true");

        // Get all the fundingManagementList where title is null
        defaultFundingManagementShouldNotBeFound("title.specified=false");
    }
                @Test
    @Transactional
    public void getAllFundingManagementsByTitleContainsSomething() throws Exception {
        // Initialize the database
        fundingManagementRepository.saveAndFlush(fundingManagement);

        // Get all the fundingManagementList where title contains DEFAULT_TITLE
        defaultFundingManagementShouldBeFound("title.contains=" + DEFAULT_TITLE);

        // Get all the fundingManagementList where title contains UPDATED_TITLE
        defaultFundingManagementShouldNotBeFound("title.contains=" + UPDATED_TITLE);
    }

    @Test
    @Transactional
    public void getAllFundingManagementsByTitleNotContainsSomething() throws Exception {
        // Initialize the database
        fundingManagementRepository.saveAndFlush(fundingManagement);

        // Get all the fundingManagementList where title does not contain DEFAULT_TITLE
        defaultFundingManagementShouldNotBeFound("title.doesNotContain=" + DEFAULT_TITLE);

        // Get all the fundingManagementList where title does not contain UPDATED_TITLE
        defaultFundingManagementShouldBeFound("title.doesNotContain=" + UPDATED_TITLE);
    }


    @Test
    @Transactional
    public void getAllFundingManagementsByDescriptionIsEqualToSomething() throws Exception {
        // Initialize the database
        fundingManagementRepository.saveAndFlush(fundingManagement);

        // Get all the fundingManagementList where description equals to DEFAULT_DESCRIPTION
        defaultFundingManagementShouldBeFound("description.equals=" + DEFAULT_DESCRIPTION);

        // Get all the fundingManagementList where description equals to UPDATED_DESCRIPTION
        defaultFundingManagementShouldNotBeFound("description.equals=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllFundingManagementsByDescriptionIsNotEqualToSomething() throws Exception {
        // Initialize the database
        fundingManagementRepository.saveAndFlush(fundingManagement);

        // Get all the fundingManagementList where description not equals to DEFAULT_DESCRIPTION
        defaultFundingManagementShouldNotBeFound("description.notEquals=" + DEFAULT_DESCRIPTION);

        // Get all the fundingManagementList where description not equals to UPDATED_DESCRIPTION
        defaultFundingManagementShouldBeFound("description.notEquals=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllFundingManagementsByDescriptionIsInShouldWork() throws Exception {
        // Initialize the database
        fundingManagementRepository.saveAndFlush(fundingManagement);

        // Get all the fundingManagementList where description in DEFAULT_DESCRIPTION or UPDATED_DESCRIPTION
        defaultFundingManagementShouldBeFound("description.in=" + DEFAULT_DESCRIPTION + "," + UPDATED_DESCRIPTION);

        // Get all the fundingManagementList where description equals to UPDATED_DESCRIPTION
        defaultFundingManagementShouldNotBeFound("description.in=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllFundingManagementsByDescriptionIsNullOrNotNull() throws Exception {
        // Initialize the database
        fundingManagementRepository.saveAndFlush(fundingManagement);

        // Get all the fundingManagementList where description is not null
        defaultFundingManagementShouldBeFound("description.specified=true");

        // Get all the fundingManagementList where description is null
        defaultFundingManagementShouldNotBeFound("description.specified=false");
    }
                @Test
    @Transactional
    public void getAllFundingManagementsByDescriptionContainsSomething() throws Exception {
        // Initialize the database
        fundingManagementRepository.saveAndFlush(fundingManagement);

        // Get all the fundingManagementList where description contains DEFAULT_DESCRIPTION
        defaultFundingManagementShouldBeFound("description.contains=" + DEFAULT_DESCRIPTION);

        // Get all the fundingManagementList where description contains UPDATED_DESCRIPTION
        defaultFundingManagementShouldNotBeFound("description.contains=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllFundingManagementsByDescriptionNotContainsSomething() throws Exception {
        // Initialize the database
        fundingManagementRepository.saveAndFlush(fundingManagement);

        // Get all the fundingManagementList where description does not contain DEFAULT_DESCRIPTION
        defaultFundingManagementShouldNotBeFound("description.doesNotContain=" + DEFAULT_DESCRIPTION);

        // Get all the fundingManagementList where description does not contain UPDATED_DESCRIPTION
        defaultFundingManagementShouldBeFound("description.doesNotContain=" + UPDATED_DESCRIPTION);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultFundingManagementShouldBeFound(String filter) throws Exception {
        restFundingManagementMockMvc.perform(get("/api/funding-managements?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(fundingManagement.getId().intValue())))
            .andExpect(jsonPath("$.[*].title").value(hasItem(DEFAULT_TITLE)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)));

        // Check, that the count call also returns 1
        restFundingManagementMockMvc.perform(get("/api/funding-managements/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultFundingManagementShouldNotBeFound(String filter) throws Exception {
        restFundingManagementMockMvc.perform(get("/api/funding-managements?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restFundingManagementMockMvc.perform(get("/api/funding-managements/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    public void getNonExistingFundingManagement() throws Exception {
        // Get the fundingManagement
        restFundingManagementMockMvc.perform(get("/api/funding-managements/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateFundingManagement() throws Exception {
        // Initialize the database
        fundingManagementRepository.saveAndFlush(fundingManagement);

        int databaseSizeBeforeUpdate = fundingManagementRepository.findAll().size();

        // Update the fundingManagement
        FundingManagement updatedFundingManagement = fundingManagementRepository.findById(fundingManagement.getId()).get();
        // Disconnect from session so that the updates on updatedFundingManagement are not directly saved in db
        em.detach(updatedFundingManagement);
        updatedFundingManagement
            .title(UPDATED_TITLE)
            .description(UPDATED_DESCRIPTION);
        FundingManagementDTO fundingManagementDTO = fundingManagementMapper.toDto(updatedFundingManagement);

        restFundingManagementMockMvc.perform(put("/api/funding-managements").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(fundingManagementDTO)))
            .andExpect(status().isOk());

        // Validate the FundingManagement in the database
        List<FundingManagement> fundingManagementList = fundingManagementRepository.findAll();
        assertThat(fundingManagementList).hasSize(databaseSizeBeforeUpdate);
        FundingManagement testFundingManagement = fundingManagementList.get(fundingManagementList.size() - 1);
        assertThat(testFundingManagement.getTitle()).isEqualTo(UPDATED_TITLE);
        assertThat(testFundingManagement.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void updateNonExistingFundingManagement() throws Exception {
        int databaseSizeBeforeUpdate = fundingManagementRepository.findAll().size();

        // Create the FundingManagement
        FundingManagementDTO fundingManagementDTO = fundingManagementMapper.toDto(fundingManagement);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFundingManagementMockMvc.perform(put("/api/funding-managements").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(fundingManagementDTO)))
            .andExpect(status().isBadRequest());

        // Validate the FundingManagement in the database
        List<FundingManagement> fundingManagementList = fundingManagementRepository.findAll();
        assertThat(fundingManagementList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteFundingManagement() throws Exception {
        // Initialize the database
        fundingManagementRepository.saveAndFlush(fundingManagement);

        int databaseSizeBeforeDelete = fundingManagementRepository.findAll().size();

        // Delete the fundingManagement
        restFundingManagementMockMvc.perform(delete("/api/funding-managements/{id}", fundingManagement.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<FundingManagement> fundingManagementList = fundingManagementRepository.findAll();
        assertThat(fundingManagementList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
