package org.tamisemi.iftmis.web.rest;

import org.tamisemi.iftmis.IftmisApp;
import org.tamisemi.iftmis.domain.TheClusters;
import org.tamisemi.iftmis.repository.TheClustersRepository;
import org.tamisemi.iftmis.service.TheClustersService;
import org.tamisemi.iftmis.service.dto.TheClustersDTO;
import org.tamisemi.iftmis.service.mapper.TheClustersMapper;
import org.tamisemi.iftmis.service.dto.TheClustersCriteria;
import org.tamisemi.iftmis.service.TheClustersQueryService;

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
 * Integration tests for the {@link TheClustersResource} REST controller.
 */
@SpringBootTest(classes = IftmisApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class TheClustersResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    @Autowired
    private TheClustersRepository theClustersRepository;

    @Autowired
    private TheClustersMapper theClustersMapper;

    @Autowired
    private TheClustersService theClustersService;

    @Autowired
    private TheClustersQueryService theClustersQueryService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTheClustersMockMvc;

    private TheClusters theClusters;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TheClusters createEntity(EntityManager em) {
        TheClusters theClusters = new TheClusters()
            .name(DEFAULT_NAME)
            .code(DEFAULT_CODE);
        return theClusters;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TheClusters createUpdatedEntity(EntityManager em) {
        TheClusters theClusters = new TheClusters()
            .name(UPDATED_NAME)
            .code(UPDATED_CODE);
        return theClusters;
    }

    @BeforeEach
    public void initTest() {
        theClusters = createEntity(em);
    }

    @Test
    @Transactional
    public void createTheClusters() throws Exception {
        int databaseSizeBeforeCreate = theClustersRepository.findAll().size();
        // Create the TheClusters
        TheClustersDTO theClustersDTO = theClustersMapper.toDto(theClusters);
        restTheClustersMockMvc.perform(post("/api/the-clusters").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(theClustersDTO)))
            .andExpect(status().isCreated());

        // Validate the TheClusters in the database
        List<TheClusters> theClustersList = theClustersRepository.findAll();
        assertThat(theClustersList).hasSize(databaseSizeBeforeCreate + 1);
        TheClusters testTheClusters = theClustersList.get(theClustersList.size() - 1);
        assertThat(testTheClusters.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testTheClusters.getCode()).isEqualTo(DEFAULT_CODE);
    }

    @Test
    @Transactional
    public void createTheClustersWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = theClustersRepository.findAll().size();

        // Create the TheClusters with an existing ID
        theClusters.setId(1L);
        TheClustersDTO theClustersDTO = theClustersMapper.toDto(theClusters);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTheClustersMockMvc.perform(post("/api/the-clusters").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(theClustersDTO)))
            .andExpect(status().isBadRequest());

        // Validate the TheClusters in the database
        List<TheClusters> theClustersList = theClustersRepository.findAll();
        assertThat(theClustersList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = theClustersRepository.findAll().size();
        // set the field null
        theClusters.setName(null);

        // Create the TheClusters, which fails.
        TheClustersDTO theClustersDTO = theClustersMapper.toDto(theClusters);


        restTheClustersMockMvc.perform(post("/api/the-clusters").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(theClustersDTO)))
            .andExpect(status().isBadRequest());

        List<TheClusters> theClustersList = theClustersRepository.findAll();
        assertThat(theClustersList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllTheClusters() throws Exception {
        // Initialize the database
        theClustersRepository.saveAndFlush(theClusters);

        // Get all the theClustersList
        restTheClustersMockMvc.perform(get("/api/the-clusters?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(theClusters.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)));
    }
    
    @Test
    @Transactional
    public void getTheClusters() throws Exception {
        // Initialize the database
        theClustersRepository.saveAndFlush(theClusters);

        // Get the theClusters
        restTheClustersMockMvc.perform(get("/api/the-clusters/{id}", theClusters.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(theClusters.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE));
    }


    @Test
    @Transactional
    public void getTheClustersByIdFiltering() throws Exception {
        // Initialize the database
        theClustersRepository.saveAndFlush(theClusters);

        Long id = theClusters.getId();

        defaultTheClustersShouldBeFound("id.equals=" + id);
        defaultTheClustersShouldNotBeFound("id.notEquals=" + id);

        defaultTheClustersShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultTheClustersShouldNotBeFound("id.greaterThan=" + id);

        defaultTheClustersShouldBeFound("id.lessThanOrEqual=" + id);
        defaultTheClustersShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllTheClustersByNameIsEqualToSomething() throws Exception {
        // Initialize the database
        theClustersRepository.saveAndFlush(theClusters);

        // Get all the theClustersList where name equals to DEFAULT_NAME
        defaultTheClustersShouldBeFound("name.equals=" + DEFAULT_NAME);

        // Get all the theClustersList where name equals to UPDATED_NAME
        defaultTheClustersShouldNotBeFound("name.equals=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllTheClustersByNameIsNotEqualToSomething() throws Exception {
        // Initialize the database
        theClustersRepository.saveAndFlush(theClusters);

        // Get all the theClustersList where name not equals to DEFAULT_NAME
        defaultTheClustersShouldNotBeFound("name.notEquals=" + DEFAULT_NAME);

        // Get all the theClustersList where name not equals to UPDATED_NAME
        defaultTheClustersShouldBeFound("name.notEquals=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllTheClustersByNameIsInShouldWork() throws Exception {
        // Initialize the database
        theClustersRepository.saveAndFlush(theClusters);

        // Get all the theClustersList where name in DEFAULT_NAME or UPDATED_NAME
        defaultTheClustersShouldBeFound("name.in=" + DEFAULT_NAME + "," + UPDATED_NAME);

        // Get all the theClustersList where name equals to UPDATED_NAME
        defaultTheClustersShouldNotBeFound("name.in=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllTheClustersByNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        theClustersRepository.saveAndFlush(theClusters);

        // Get all the theClustersList where name is not null
        defaultTheClustersShouldBeFound("name.specified=true");

        // Get all the theClustersList where name is null
        defaultTheClustersShouldNotBeFound("name.specified=false");
    }
                @Test
    @Transactional
    public void getAllTheClustersByNameContainsSomething() throws Exception {
        // Initialize the database
        theClustersRepository.saveAndFlush(theClusters);

        // Get all the theClustersList where name contains DEFAULT_NAME
        defaultTheClustersShouldBeFound("name.contains=" + DEFAULT_NAME);

        // Get all the theClustersList where name contains UPDATED_NAME
        defaultTheClustersShouldNotBeFound("name.contains=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllTheClustersByNameNotContainsSomething() throws Exception {
        // Initialize the database
        theClustersRepository.saveAndFlush(theClusters);

        // Get all the theClustersList where name does not contain DEFAULT_NAME
        defaultTheClustersShouldNotBeFound("name.doesNotContain=" + DEFAULT_NAME);

        // Get all the theClustersList where name does not contain UPDATED_NAME
        defaultTheClustersShouldBeFound("name.doesNotContain=" + UPDATED_NAME);
    }


    @Test
    @Transactional
    public void getAllTheClustersByCodeIsEqualToSomething() throws Exception {
        // Initialize the database
        theClustersRepository.saveAndFlush(theClusters);

        // Get all the theClustersList where code equals to DEFAULT_CODE
        defaultTheClustersShouldBeFound("code.equals=" + DEFAULT_CODE);

        // Get all the theClustersList where code equals to UPDATED_CODE
        defaultTheClustersShouldNotBeFound("code.equals=" + UPDATED_CODE);
    }

    @Test
    @Transactional
    public void getAllTheClustersByCodeIsNotEqualToSomething() throws Exception {
        // Initialize the database
        theClustersRepository.saveAndFlush(theClusters);

        // Get all the theClustersList where code not equals to DEFAULT_CODE
        defaultTheClustersShouldNotBeFound("code.notEquals=" + DEFAULT_CODE);

        // Get all the theClustersList where code not equals to UPDATED_CODE
        defaultTheClustersShouldBeFound("code.notEquals=" + UPDATED_CODE);
    }

    @Test
    @Transactional
    public void getAllTheClustersByCodeIsInShouldWork() throws Exception {
        // Initialize the database
        theClustersRepository.saveAndFlush(theClusters);

        // Get all the theClustersList where code in DEFAULT_CODE or UPDATED_CODE
        defaultTheClustersShouldBeFound("code.in=" + DEFAULT_CODE + "," + UPDATED_CODE);

        // Get all the theClustersList where code equals to UPDATED_CODE
        defaultTheClustersShouldNotBeFound("code.in=" + UPDATED_CODE);
    }

    @Test
    @Transactional
    public void getAllTheClustersByCodeIsNullOrNotNull() throws Exception {
        // Initialize the database
        theClustersRepository.saveAndFlush(theClusters);

        // Get all the theClustersList where code is not null
        defaultTheClustersShouldBeFound("code.specified=true");

        // Get all the theClustersList where code is null
        defaultTheClustersShouldNotBeFound("code.specified=false");
    }
                @Test
    @Transactional
    public void getAllTheClustersByCodeContainsSomething() throws Exception {
        // Initialize the database
        theClustersRepository.saveAndFlush(theClusters);

        // Get all the theClustersList where code contains DEFAULT_CODE
        defaultTheClustersShouldBeFound("code.contains=" + DEFAULT_CODE);

        // Get all the theClustersList where code contains UPDATED_CODE
        defaultTheClustersShouldNotBeFound("code.contains=" + UPDATED_CODE);
    }

    @Test
    @Transactional
    public void getAllTheClustersByCodeNotContainsSomething() throws Exception {
        // Initialize the database
        theClustersRepository.saveAndFlush(theClusters);

        // Get all the theClustersList where code does not contain DEFAULT_CODE
        defaultTheClustersShouldNotBeFound("code.doesNotContain=" + DEFAULT_CODE);

        // Get all the theClustersList where code does not contain UPDATED_CODE
        defaultTheClustersShouldBeFound("code.doesNotContain=" + UPDATED_CODE);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultTheClustersShouldBeFound(String filter) throws Exception {
        restTheClustersMockMvc.perform(get("/api/the-clusters?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(theClusters.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)));

        // Check, that the count call also returns 1
        restTheClustersMockMvc.perform(get("/api/the-clusters/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultTheClustersShouldNotBeFound(String filter) throws Exception {
        restTheClustersMockMvc.perform(get("/api/the-clusters?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restTheClustersMockMvc.perform(get("/api/the-clusters/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    public void getNonExistingTheClusters() throws Exception {
        // Get the theClusters
        restTheClustersMockMvc.perform(get("/api/the-clusters/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTheClusters() throws Exception {
        // Initialize the database
        theClustersRepository.saveAndFlush(theClusters);

        int databaseSizeBeforeUpdate = theClustersRepository.findAll().size();

        // Update the theClusters
        TheClusters updatedTheClusters = theClustersRepository.findById(theClusters.getId()).get();
        // Disconnect from session so that the updates on updatedTheClusters are not directly saved in db
        em.detach(updatedTheClusters);
        updatedTheClusters
            .name(UPDATED_NAME)
            .code(UPDATED_CODE);
        TheClustersDTO theClustersDTO = theClustersMapper.toDto(updatedTheClusters);

        restTheClustersMockMvc.perform(put("/api/the-clusters").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(theClustersDTO)))
            .andExpect(status().isOk());

        // Validate the TheClusters in the database
        List<TheClusters> theClustersList = theClustersRepository.findAll();
        assertThat(theClustersList).hasSize(databaseSizeBeforeUpdate);
        TheClusters testTheClusters = theClustersList.get(theClustersList.size() - 1);
        assertThat(testTheClusters.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testTheClusters.getCode()).isEqualTo(UPDATED_CODE);
    }

    @Test
    @Transactional
    public void updateNonExistingTheClusters() throws Exception {
        int databaseSizeBeforeUpdate = theClustersRepository.findAll().size();

        // Create the TheClusters
        TheClustersDTO theClustersDTO = theClustersMapper.toDto(theClusters);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTheClustersMockMvc.perform(put("/api/the-clusters").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(theClustersDTO)))
            .andExpect(status().isBadRequest());

        // Validate the TheClusters in the database
        List<TheClusters> theClustersList = theClustersRepository.findAll();
        assertThat(theClustersList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTheClusters() throws Exception {
        // Initialize the database
        theClustersRepository.saveAndFlush(theClusters);

        int databaseSizeBeforeDelete = theClustersRepository.findAll().size();

        // Delete the theClusters
        restTheClustersMockMvc.perform(delete("/api/the-clusters/{id}", theClusters.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TheClusters> theClustersList = theClustersRepository.findAll();
        assertThat(theClustersList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
