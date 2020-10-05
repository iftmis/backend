package org.tamisemi.iftmis.web.rest;

import org.tamisemi.iftmis.IftmisApp;
import org.tamisemi.iftmis.domain.InspectionTypes;
import org.tamisemi.iftmis.repository.InspectionTypesRepository;
import org.tamisemi.iftmis.service.InspectionTypesService;
import org.tamisemi.iftmis.service.dto.InspectionTypesDTO;
import org.tamisemi.iftmis.service.mapper.InspectionTypesMapper;
import org.tamisemi.iftmis.service.dto.InspectionTypesCriteria;
import org.tamisemi.iftmis.service.InspectionTypesQueryService;

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
 * Integration tests for the {@link InspectionTypesResource} REST controller.
 */
@SpringBootTest(classes = IftmisApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class InspectionTypesResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    @Autowired
    private InspectionTypesRepository inspectionTypesRepository;

    @Autowired
    private InspectionTypesMapper inspectionTypesMapper;

    @Autowired
    private InspectionTypesService inspectionTypesService;

    @Autowired
    private InspectionTypesQueryService inspectionTypesQueryService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restInspectionTypesMockMvc;

    private InspectionTypes inspectionTypes;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static InspectionTypes createEntity(EntityManager em) {
        InspectionTypes inspectionTypes = new InspectionTypes()
            .name(DEFAULT_NAME)
            .description(DEFAULT_DESCRIPTION);
        return inspectionTypes;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static InspectionTypes createUpdatedEntity(EntityManager em) {
        InspectionTypes inspectionTypes = new InspectionTypes()
            .name(UPDATED_NAME)
            .description(UPDATED_DESCRIPTION);
        return inspectionTypes;
    }

    @BeforeEach
    public void initTest() {
        inspectionTypes = createEntity(em);
    }

    @Test
    @Transactional
    public void createInspectionTypes() throws Exception {
        int databaseSizeBeforeCreate = inspectionTypesRepository.findAll().size();
        // Create the InspectionTypes
        InspectionTypesDTO inspectionTypesDTO = inspectionTypesMapper.toDto(inspectionTypes);
        restInspectionTypesMockMvc.perform(post("/api/inspection-types").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(inspectionTypesDTO)))
            .andExpect(status().isCreated());

        // Validate the InspectionTypes in the database
        List<InspectionTypes> inspectionTypesList = inspectionTypesRepository.findAll();
        assertThat(inspectionTypesList).hasSize(databaseSizeBeforeCreate + 1);
        InspectionTypes testInspectionTypes = inspectionTypesList.get(inspectionTypesList.size() - 1);
        assertThat(testInspectionTypes.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testInspectionTypes.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
    }

    @Test
    @Transactional
    public void createInspectionTypesWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = inspectionTypesRepository.findAll().size();

        // Create the InspectionTypes with an existing ID
        inspectionTypes.setId(1L);
        InspectionTypesDTO inspectionTypesDTO = inspectionTypesMapper.toDto(inspectionTypes);

        // An entity with an existing ID cannot be created, so this API call must fail
        restInspectionTypesMockMvc.perform(post("/api/inspection-types").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(inspectionTypesDTO)))
            .andExpect(status().isBadRequest());

        // Validate the InspectionTypes in the database
        List<InspectionTypes> inspectionTypesList = inspectionTypesRepository.findAll();
        assertThat(inspectionTypesList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = inspectionTypesRepository.findAll().size();
        // set the field null
        inspectionTypes.setName(null);

        // Create the InspectionTypes, which fails.
        InspectionTypesDTO inspectionTypesDTO = inspectionTypesMapper.toDto(inspectionTypes);


        restInspectionTypesMockMvc.perform(post("/api/inspection-types").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(inspectionTypesDTO)))
            .andExpect(status().isBadRequest());

        List<InspectionTypes> inspectionTypesList = inspectionTypesRepository.findAll();
        assertThat(inspectionTypesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllInspectionTypes() throws Exception {
        // Initialize the database
        inspectionTypesRepository.saveAndFlush(inspectionTypes);

        // Get all the inspectionTypesList
        restInspectionTypesMockMvc.perform(get("/api/inspection-types?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(inspectionTypes.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)));
    }
    
    @Test
    @Transactional
    public void getInspectionTypes() throws Exception {
        // Initialize the database
        inspectionTypesRepository.saveAndFlush(inspectionTypes);

        // Get the inspectionTypes
        restInspectionTypesMockMvc.perform(get("/api/inspection-types/{id}", inspectionTypes.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(inspectionTypes.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION));
    }


    @Test
    @Transactional
    public void getInspectionTypesByIdFiltering() throws Exception {
        // Initialize the database
        inspectionTypesRepository.saveAndFlush(inspectionTypes);

        Long id = inspectionTypes.getId();

        defaultInspectionTypesShouldBeFound("id.equals=" + id);
        defaultInspectionTypesShouldNotBeFound("id.notEquals=" + id);

        defaultInspectionTypesShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultInspectionTypesShouldNotBeFound("id.greaterThan=" + id);

        defaultInspectionTypesShouldBeFound("id.lessThanOrEqual=" + id);
        defaultInspectionTypesShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllInspectionTypesByNameIsEqualToSomething() throws Exception {
        // Initialize the database
        inspectionTypesRepository.saveAndFlush(inspectionTypes);

        // Get all the inspectionTypesList where name equals to DEFAULT_NAME
        defaultInspectionTypesShouldBeFound("name.equals=" + DEFAULT_NAME);

        // Get all the inspectionTypesList where name equals to UPDATED_NAME
        defaultInspectionTypesShouldNotBeFound("name.equals=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllInspectionTypesByNameIsNotEqualToSomething() throws Exception {
        // Initialize the database
        inspectionTypesRepository.saveAndFlush(inspectionTypes);

        // Get all the inspectionTypesList where name not equals to DEFAULT_NAME
        defaultInspectionTypesShouldNotBeFound("name.notEquals=" + DEFAULT_NAME);

        // Get all the inspectionTypesList where name not equals to UPDATED_NAME
        defaultInspectionTypesShouldBeFound("name.notEquals=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllInspectionTypesByNameIsInShouldWork() throws Exception {
        // Initialize the database
        inspectionTypesRepository.saveAndFlush(inspectionTypes);

        // Get all the inspectionTypesList where name in DEFAULT_NAME or UPDATED_NAME
        defaultInspectionTypesShouldBeFound("name.in=" + DEFAULT_NAME + "," + UPDATED_NAME);

        // Get all the inspectionTypesList where name equals to UPDATED_NAME
        defaultInspectionTypesShouldNotBeFound("name.in=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllInspectionTypesByNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        inspectionTypesRepository.saveAndFlush(inspectionTypes);

        // Get all the inspectionTypesList where name is not null
        defaultInspectionTypesShouldBeFound("name.specified=true");

        // Get all the inspectionTypesList where name is null
        defaultInspectionTypesShouldNotBeFound("name.specified=false");
    }
                @Test
    @Transactional
    public void getAllInspectionTypesByNameContainsSomething() throws Exception {
        // Initialize the database
        inspectionTypesRepository.saveAndFlush(inspectionTypes);

        // Get all the inspectionTypesList where name contains DEFAULT_NAME
        defaultInspectionTypesShouldBeFound("name.contains=" + DEFAULT_NAME);

        // Get all the inspectionTypesList where name contains UPDATED_NAME
        defaultInspectionTypesShouldNotBeFound("name.contains=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllInspectionTypesByNameNotContainsSomething() throws Exception {
        // Initialize the database
        inspectionTypesRepository.saveAndFlush(inspectionTypes);

        // Get all the inspectionTypesList where name does not contain DEFAULT_NAME
        defaultInspectionTypesShouldNotBeFound("name.doesNotContain=" + DEFAULT_NAME);

        // Get all the inspectionTypesList where name does not contain UPDATED_NAME
        defaultInspectionTypesShouldBeFound("name.doesNotContain=" + UPDATED_NAME);
    }


    @Test
    @Transactional
    public void getAllInspectionTypesByDescriptionIsEqualToSomething() throws Exception {
        // Initialize the database
        inspectionTypesRepository.saveAndFlush(inspectionTypes);

        // Get all the inspectionTypesList where description equals to DEFAULT_DESCRIPTION
        defaultInspectionTypesShouldBeFound("description.equals=" + DEFAULT_DESCRIPTION);

        // Get all the inspectionTypesList where description equals to UPDATED_DESCRIPTION
        defaultInspectionTypesShouldNotBeFound("description.equals=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllInspectionTypesByDescriptionIsNotEqualToSomething() throws Exception {
        // Initialize the database
        inspectionTypesRepository.saveAndFlush(inspectionTypes);

        // Get all the inspectionTypesList where description not equals to DEFAULT_DESCRIPTION
        defaultInspectionTypesShouldNotBeFound("description.notEquals=" + DEFAULT_DESCRIPTION);

        // Get all the inspectionTypesList where description not equals to UPDATED_DESCRIPTION
        defaultInspectionTypesShouldBeFound("description.notEquals=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllInspectionTypesByDescriptionIsInShouldWork() throws Exception {
        // Initialize the database
        inspectionTypesRepository.saveAndFlush(inspectionTypes);

        // Get all the inspectionTypesList where description in DEFAULT_DESCRIPTION or UPDATED_DESCRIPTION
        defaultInspectionTypesShouldBeFound("description.in=" + DEFAULT_DESCRIPTION + "," + UPDATED_DESCRIPTION);

        // Get all the inspectionTypesList where description equals to UPDATED_DESCRIPTION
        defaultInspectionTypesShouldNotBeFound("description.in=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllInspectionTypesByDescriptionIsNullOrNotNull() throws Exception {
        // Initialize the database
        inspectionTypesRepository.saveAndFlush(inspectionTypes);

        // Get all the inspectionTypesList where description is not null
        defaultInspectionTypesShouldBeFound("description.specified=true");

        // Get all the inspectionTypesList where description is null
        defaultInspectionTypesShouldNotBeFound("description.specified=false");
    }
                @Test
    @Transactional
    public void getAllInspectionTypesByDescriptionContainsSomething() throws Exception {
        // Initialize the database
        inspectionTypesRepository.saveAndFlush(inspectionTypes);

        // Get all the inspectionTypesList where description contains DEFAULT_DESCRIPTION
        defaultInspectionTypesShouldBeFound("description.contains=" + DEFAULT_DESCRIPTION);

        // Get all the inspectionTypesList where description contains UPDATED_DESCRIPTION
        defaultInspectionTypesShouldNotBeFound("description.contains=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllInspectionTypesByDescriptionNotContainsSomething() throws Exception {
        // Initialize the database
        inspectionTypesRepository.saveAndFlush(inspectionTypes);

        // Get all the inspectionTypesList where description does not contain DEFAULT_DESCRIPTION
        defaultInspectionTypesShouldNotBeFound("description.doesNotContain=" + DEFAULT_DESCRIPTION);

        // Get all the inspectionTypesList where description does not contain UPDATED_DESCRIPTION
        defaultInspectionTypesShouldBeFound("description.doesNotContain=" + UPDATED_DESCRIPTION);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultInspectionTypesShouldBeFound(String filter) throws Exception {
        restInspectionTypesMockMvc.perform(get("/api/inspection-types?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(inspectionTypes.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)));

        // Check, that the count call also returns 1
        restInspectionTypesMockMvc.perform(get("/api/inspection-types/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultInspectionTypesShouldNotBeFound(String filter) throws Exception {
        restInspectionTypesMockMvc.perform(get("/api/inspection-types?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restInspectionTypesMockMvc.perform(get("/api/inspection-types/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    public void getNonExistingInspectionTypes() throws Exception {
        // Get the inspectionTypes
        restInspectionTypesMockMvc.perform(get("/api/inspection-types/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateInspectionTypes() throws Exception {
        // Initialize the database
        inspectionTypesRepository.saveAndFlush(inspectionTypes);

        int databaseSizeBeforeUpdate = inspectionTypesRepository.findAll().size();

        // Update the inspectionTypes
        InspectionTypes updatedInspectionTypes = inspectionTypesRepository.findById(inspectionTypes.getId()).get();
        // Disconnect from session so that the updates on updatedInspectionTypes are not directly saved in db
        em.detach(updatedInspectionTypes);
        updatedInspectionTypes
            .name(UPDATED_NAME)
            .description(UPDATED_DESCRIPTION);
        InspectionTypesDTO inspectionTypesDTO = inspectionTypesMapper.toDto(updatedInspectionTypes);

        restInspectionTypesMockMvc.perform(put("/api/inspection-types").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(inspectionTypesDTO)))
            .andExpect(status().isOk());

        // Validate the InspectionTypes in the database
        List<InspectionTypes> inspectionTypesList = inspectionTypesRepository.findAll();
        assertThat(inspectionTypesList).hasSize(databaseSizeBeforeUpdate);
        InspectionTypes testInspectionTypes = inspectionTypesList.get(inspectionTypesList.size() - 1);
        assertThat(testInspectionTypes.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testInspectionTypes.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void updateNonExistingInspectionTypes() throws Exception {
        int databaseSizeBeforeUpdate = inspectionTypesRepository.findAll().size();

        // Create the InspectionTypes
        InspectionTypesDTO inspectionTypesDTO = inspectionTypesMapper.toDto(inspectionTypes);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restInspectionTypesMockMvc.perform(put("/api/inspection-types").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(inspectionTypesDTO)))
            .andExpect(status().isBadRequest());

        // Validate the InspectionTypes in the database
        List<InspectionTypes> inspectionTypesList = inspectionTypesRepository.findAll();
        assertThat(inspectionTypesList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteInspectionTypes() throws Exception {
        // Initialize the database
        inspectionTypesRepository.saveAndFlush(inspectionTypes);

        int databaseSizeBeforeDelete = inspectionTypesRepository.findAll().size();

        // Delete the inspectionTypes
        restInspectionTypesMockMvc.perform(delete("/api/inspection-types/{id}", inspectionTypes.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<InspectionTypes> inspectionTypesList = inspectionTypesRepository.findAll();
        assertThat(inspectionTypesList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
