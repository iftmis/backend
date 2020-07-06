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
import org.tamisemi.iftmis.domain.OrganisationUnitLevel;
import org.tamisemi.iftmis.repository.OrganisationUnitLevelRepository;
import org.tamisemi.iftmis.service.OrganisationUnitLevelService;
import org.tamisemi.iftmis.service.dto.OrganisationUnitLevelDTO;
import org.tamisemi.iftmis.service.mapper.OrganisationUnitLevelMapper;

/**
 * Integration tests for the {@link OrganisationUnitLevelResource} REST controller.
 */
@SpringBootTest(classes = IftmisApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class OrganisationUnitLevelResourceIT {
    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final Integer DEFAULT_LEVEL = 1;
    private static final Integer UPDATED_LEVEL = 2;

    private static final Boolean DEFAULT_IS_INSPECTION_LEVEL = false;
    private static final Boolean UPDATED_IS_INSPECTION_LEVEL = true;

    @Autowired
    private OrganisationUnitLevelRepository organisationUnitLevelRepository;

    @Autowired
    private OrganisationUnitLevelMapper organisationUnitLevelMapper;

    @Autowired
    private OrganisationUnitLevelService organisationUnitLevelService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restOrganisationUnitLevelMockMvc;

    private OrganisationUnitLevel organisationUnitLevel;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OrganisationUnitLevel createEntity(EntityManager em) {
        OrganisationUnitLevel organisationUnitLevel = new OrganisationUnitLevel()
            .code(DEFAULT_CODE)
            .name(DEFAULT_NAME)
            .level(DEFAULT_LEVEL)
            .isInspectionLevel(DEFAULT_IS_INSPECTION_LEVEL);
        return organisationUnitLevel;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OrganisationUnitLevel createUpdatedEntity(EntityManager em) {
        OrganisationUnitLevel organisationUnitLevel = new OrganisationUnitLevel()
            .code(UPDATED_CODE)
            .name(UPDATED_NAME)
            .level(UPDATED_LEVEL)
            .isInspectionLevel(UPDATED_IS_INSPECTION_LEVEL);
        return organisationUnitLevel;
    }

    @BeforeEach
    public void initTest() {
        organisationUnitLevel = createEntity(em);
    }

    @Test
    @Transactional
    public void createOrganisationUnitLevel() throws Exception {
        int databaseSizeBeforeCreate = organisationUnitLevelRepository.findAll().size();
        // Create the OrganisationUnitLevel
        OrganisationUnitLevelDTO organisationUnitLevelDTO = organisationUnitLevelMapper.toDto(organisationUnitLevel);
        restOrganisationUnitLevelMockMvc
            .perform(
                post("/api/organisation-unit-levels")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(organisationUnitLevelDTO))
            )
            .andExpect(status().isCreated());

        // Validate the OrganisationUnitLevel in the database
        List<OrganisationUnitLevel> organisationUnitLevelList = organisationUnitLevelRepository.findAll();
        assertThat(organisationUnitLevelList).hasSize(databaseSizeBeforeCreate + 1);
        OrganisationUnitLevel testOrganisationUnitLevel = organisationUnitLevelList.get(organisationUnitLevelList.size() - 1);
        assertThat(testOrganisationUnitLevel.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testOrganisationUnitLevel.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testOrganisationUnitLevel.getLevel()).isEqualTo(DEFAULT_LEVEL);
        assertThat(testOrganisationUnitLevel.isIsInspectionLevel()).isEqualTo(DEFAULT_IS_INSPECTION_LEVEL);
    }

    @Test
    @Transactional
    public void createOrganisationUnitLevelWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = organisationUnitLevelRepository.findAll().size();

        // Create the OrganisationUnitLevel with an existing ID
        organisationUnitLevel.setId(1L);
        OrganisationUnitLevelDTO organisationUnitLevelDTO = organisationUnitLevelMapper.toDto(organisationUnitLevel);

        // An entity with an existing ID cannot be created, so this API call must fail
        restOrganisationUnitLevelMockMvc
            .perform(
                post("/api/organisation-unit-levels")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(organisationUnitLevelDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the OrganisationUnitLevel in the database
        List<OrganisationUnitLevel> organisationUnitLevelList = organisationUnitLevelRepository.findAll();
        assertThat(organisationUnitLevelList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = organisationUnitLevelRepository.findAll().size();
        // set the field null
        organisationUnitLevel.setName(null);

        // Create the OrganisationUnitLevel, which fails.
        OrganisationUnitLevelDTO organisationUnitLevelDTO = organisationUnitLevelMapper.toDto(organisationUnitLevel);

        restOrganisationUnitLevelMockMvc
            .perform(
                post("/api/organisation-unit-levels")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(organisationUnitLevelDTO))
            )
            .andExpect(status().isBadRequest());

        List<OrganisationUnitLevel> organisationUnitLevelList = organisationUnitLevelRepository.findAll();
        assertThat(organisationUnitLevelList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLevelIsRequired() throws Exception {
        int databaseSizeBeforeTest = organisationUnitLevelRepository.findAll().size();
        // set the field null
        organisationUnitLevel.setLevel(null);

        // Create the OrganisationUnitLevel, which fails.
        OrganisationUnitLevelDTO organisationUnitLevelDTO = organisationUnitLevelMapper.toDto(organisationUnitLevel);

        restOrganisationUnitLevelMockMvc
            .perform(
                post("/api/organisation-unit-levels")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(organisationUnitLevelDTO))
            )
            .andExpect(status().isBadRequest());

        List<OrganisationUnitLevel> organisationUnitLevelList = organisationUnitLevelRepository.findAll();
        assertThat(organisationUnitLevelList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIsInspectionLevelIsRequired() throws Exception {
        int databaseSizeBeforeTest = organisationUnitLevelRepository.findAll().size();
        // set the field null
        organisationUnitLevel.setIsInspectionLevel(null);

        // Create the OrganisationUnitLevel, which fails.
        OrganisationUnitLevelDTO organisationUnitLevelDTO = organisationUnitLevelMapper.toDto(organisationUnitLevel);

        restOrganisationUnitLevelMockMvc
            .perform(
                post("/api/organisation-unit-levels")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(organisationUnitLevelDTO))
            )
            .andExpect(status().isBadRequest());

        List<OrganisationUnitLevel> organisationUnitLevelList = organisationUnitLevelRepository.findAll();
        assertThat(organisationUnitLevelList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllOrganisationUnitLevels() throws Exception {
        // Initialize the database
        organisationUnitLevelRepository.saveAndFlush(organisationUnitLevel);

        // Get all the organisationUnitLevelList
        restOrganisationUnitLevelMockMvc
            .perform(get("/api/organisation-unit-levels?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(organisationUnitLevel.getId().intValue())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].level").value(hasItem(DEFAULT_LEVEL)))
            .andExpect(jsonPath("$.[*].isInspectionLevel").value(hasItem(DEFAULT_IS_INSPECTION_LEVEL.booleanValue())));
    }

    @Test
    @Transactional
    public void getOrganisationUnitLevel() throws Exception {
        // Initialize the database
        organisationUnitLevelRepository.saveAndFlush(organisationUnitLevel);

        // Get the organisationUnitLevel
        restOrganisationUnitLevelMockMvc
            .perform(get("/api/organisation-unit-levels/{id}", organisationUnitLevel.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(organisationUnitLevel.getId().intValue()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.level").value(DEFAULT_LEVEL))
            .andExpect(jsonPath("$.isInspectionLevel").value(DEFAULT_IS_INSPECTION_LEVEL.booleanValue()));
    }

    @Test
    @Transactional
    public void getNonExistingOrganisationUnitLevel() throws Exception {
        // Get the organisationUnitLevel
        restOrganisationUnitLevelMockMvc
            .perform(get("/api/organisation-unit-levels/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateOrganisationUnitLevel() throws Exception {
        // Initialize the database
        organisationUnitLevelRepository.saveAndFlush(organisationUnitLevel);

        int databaseSizeBeforeUpdate = organisationUnitLevelRepository.findAll().size();

        // Update the organisationUnitLevel
        OrganisationUnitLevel updatedOrganisationUnitLevel = organisationUnitLevelRepository.findById(organisationUnitLevel.getId()).get();
        // Disconnect from session so that the updates on updatedOrganisationUnitLevel are not directly saved in db
        em.detach(updatedOrganisationUnitLevel);
        updatedOrganisationUnitLevel
            .code(UPDATED_CODE)
            .name(UPDATED_NAME)
            .level(UPDATED_LEVEL)
            .isInspectionLevel(UPDATED_IS_INSPECTION_LEVEL);
        OrganisationUnitLevelDTO organisationUnitLevelDTO = organisationUnitLevelMapper.toDto(updatedOrganisationUnitLevel);

        restOrganisationUnitLevelMockMvc
            .perform(
                put("/api/organisation-unit-levels")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(organisationUnitLevelDTO))
            )
            .andExpect(status().isOk());

        // Validate the OrganisationUnitLevel in the database
        List<OrganisationUnitLevel> organisationUnitLevelList = organisationUnitLevelRepository.findAll();
        assertThat(organisationUnitLevelList).hasSize(databaseSizeBeforeUpdate);
        OrganisationUnitLevel testOrganisationUnitLevel = organisationUnitLevelList.get(organisationUnitLevelList.size() - 1);
        assertThat(testOrganisationUnitLevel.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testOrganisationUnitLevel.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testOrganisationUnitLevel.getLevel()).isEqualTo(UPDATED_LEVEL);
        assertThat(testOrganisationUnitLevel.isIsInspectionLevel()).isEqualTo(UPDATED_IS_INSPECTION_LEVEL);
    }

    @Test
    @Transactional
    public void updateNonExistingOrganisationUnitLevel() throws Exception {
        int databaseSizeBeforeUpdate = organisationUnitLevelRepository.findAll().size();

        // Create the OrganisationUnitLevel
        OrganisationUnitLevelDTO organisationUnitLevelDTO = organisationUnitLevelMapper.toDto(organisationUnitLevel);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOrganisationUnitLevelMockMvc
            .perform(
                put("/api/organisation-unit-levels")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(organisationUnitLevelDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the OrganisationUnitLevel in the database
        List<OrganisationUnitLevel> organisationUnitLevelList = organisationUnitLevelRepository.findAll();
        assertThat(organisationUnitLevelList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteOrganisationUnitLevel() throws Exception {
        // Initialize the database
        organisationUnitLevelRepository.saveAndFlush(organisationUnitLevel);

        int databaseSizeBeforeDelete = organisationUnitLevelRepository.findAll().size();

        // Delete the organisationUnitLevel
        restOrganisationUnitLevelMockMvc
            .perform(
                delete("/api/organisation-unit-levels/{id}", organisationUnitLevel.getId()).with(csrf()).accept(MediaType.APPLICATION_JSON)
            )
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<OrganisationUnitLevel> organisationUnitLevelList = organisationUnitLevelRepository.findAll();
        assertThat(organisationUnitLevelList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
