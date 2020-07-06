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
import org.springframework.util.Base64Utils;
import org.tamisemi.iftmis.IftmisApp;
import org.tamisemi.iftmis.domain.OrganisationUnit;
import org.tamisemi.iftmis.domain.OrganisationUnitLevel;
import org.tamisemi.iftmis.repository.OrganisationUnitRepository;

/**
 * Integration tests for the {@link OrganisationUnitResource} REST controller.
 */
@SpringBootTest(classes = IftmisApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class OrganisationUnitResourceIT {
    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESS = "BBBBBBBBBB";

    private static final String DEFAULT_PHONE_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_PHONE_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_BACKGROUND = "AAAAAAAAAA";
    private static final String UPDATED_BACKGROUND = "BBBBBBBBBB";

    private static final byte[] DEFAULT_LOGO = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_LOGO = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_LOGO_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_LOGO_CONTENT_TYPE = "image/png";

    @Autowired
    private OrganisationUnitRepository organisationUnitRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restOrganisationUnitMockMvc;

    private OrganisationUnit organisationUnit;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OrganisationUnit createEntity(EntityManager em) {
        OrganisationUnit organisationUnit = new OrganisationUnit()
            .code(DEFAULT_CODE)
            .name(DEFAULT_NAME)
            .address(DEFAULT_ADDRESS)
            .phoneNumber(DEFAULT_PHONE_NUMBER)
            .email(DEFAULT_EMAIL)
            .background(DEFAULT_BACKGROUND)
            .logo(DEFAULT_LOGO)
            .logoContentType(DEFAULT_LOGO_CONTENT_TYPE);
        // Add required entity
        OrganisationUnitLevel organisationUnitLevel;
        if (TestUtil.findAll(em, OrganisationUnitLevel.class).isEmpty()) {
            organisationUnitLevel = OrganisationUnitLevelResourceIT.createEntity(em);
            em.persist(organisationUnitLevel);
            em.flush();
        } else {
            organisationUnitLevel = TestUtil.findAll(em, OrganisationUnitLevel.class).get(0);
        }
        organisationUnit.setOrganisationUnitLevel(organisationUnitLevel);
        return organisationUnit;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OrganisationUnit createUpdatedEntity(EntityManager em) {
        OrganisationUnit organisationUnit = new OrganisationUnit()
            .code(UPDATED_CODE)
            .name(UPDATED_NAME)
            .address(UPDATED_ADDRESS)
            .phoneNumber(UPDATED_PHONE_NUMBER)
            .email(UPDATED_EMAIL)
            .background(UPDATED_BACKGROUND)
            .logo(UPDATED_LOGO)
            .logoContentType(UPDATED_LOGO_CONTENT_TYPE);
        // Add required entity
        OrganisationUnitLevel organisationUnitLevel;
        if (TestUtil.findAll(em, OrganisationUnitLevel.class).isEmpty()) {
            organisationUnitLevel = OrganisationUnitLevelResourceIT.createUpdatedEntity(em);
            em.persist(organisationUnitLevel);
            em.flush();
        } else {
            organisationUnitLevel = TestUtil.findAll(em, OrganisationUnitLevel.class).get(0);
        }
        organisationUnit.setOrganisationUnitLevel(organisationUnitLevel);
        return organisationUnit;
    }

    @BeforeEach
    public void initTest() {
        organisationUnit = createEntity(em);
    }

    @Test
    @Transactional
    public void createOrganisationUnit() throws Exception {
        int databaseSizeBeforeCreate = organisationUnitRepository.findAll().size();
        // Create the OrganisationUnit
        restOrganisationUnitMockMvc
            .perform(
                post("/api/organisation-units")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(organisationUnit))
            )
            .andExpect(status().isCreated());

        // Validate the OrganisationUnit in the database
        List<OrganisationUnit> organisationUnitList = organisationUnitRepository.findAll();
        assertThat(organisationUnitList).hasSize(databaseSizeBeforeCreate + 1);
        OrganisationUnit testOrganisationUnit = organisationUnitList.get(organisationUnitList.size() - 1);
        assertThat(testOrganisationUnit.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testOrganisationUnit.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testOrganisationUnit.getAddress()).isEqualTo(DEFAULT_ADDRESS);
        assertThat(testOrganisationUnit.getPhoneNumber()).isEqualTo(DEFAULT_PHONE_NUMBER);
        assertThat(testOrganisationUnit.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testOrganisationUnit.getBackground()).isEqualTo(DEFAULT_BACKGROUND);
        assertThat(testOrganisationUnit.getLogo()).isEqualTo(DEFAULT_LOGO);
        assertThat(testOrganisationUnit.getLogoContentType()).isEqualTo(DEFAULT_LOGO_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void createOrganisationUnitWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = organisationUnitRepository.findAll().size();

        // Create the OrganisationUnit with an existing ID
        organisationUnit.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restOrganisationUnitMockMvc
            .perform(
                post("/api/organisation-units")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(organisationUnit))
            )
            .andExpect(status().isBadRequest());

        // Validate the OrganisationUnit in the database
        List<OrganisationUnit> organisationUnitList = organisationUnitRepository.findAll();
        assertThat(organisationUnitList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = organisationUnitRepository.findAll().size();
        // set the field null
        organisationUnit.setName(null);

        // Create the OrganisationUnit, which fails.

        restOrganisationUnitMockMvc
            .perform(
                post("/api/organisation-units")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(organisationUnit))
            )
            .andExpect(status().isBadRequest());

        List<OrganisationUnit> organisationUnitList = organisationUnitRepository.findAll();
        assertThat(organisationUnitList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllOrganisationUnits() throws Exception {
        // Initialize the database
        organisationUnitRepository.saveAndFlush(organisationUnit);

        // Get all the organisationUnitList
        restOrganisationUnitMockMvc
            .perform(get("/api/organisation-units?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(organisationUnit.getId().intValue())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].address").value(hasItem(DEFAULT_ADDRESS)))
            .andExpect(jsonPath("$.[*].phoneNumber").value(hasItem(DEFAULT_PHONE_NUMBER)))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL)))
            .andExpect(jsonPath("$.[*].background").value(hasItem(DEFAULT_BACKGROUND.toString())))
            .andExpect(jsonPath("$.[*].logoContentType").value(hasItem(DEFAULT_LOGO_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].logo").value(hasItem(Base64Utils.encodeToString(DEFAULT_LOGO))));
    }

    @Test
    @Transactional
    public void getOrganisationUnit() throws Exception {
        // Initialize the database
        organisationUnitRepository.saveAndFlush(organisationUnit);

        // Get the organisationUnit
        restOrganisationUnitMockMvc
            .perform(get("/api/organisation-units/{id}", organisationUnit.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(organisationUnit.getId().intValue()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.address").value(DEFAULT_ADDRESS))
            .andExpect(jsonPath("$.phoneNumber").value(DEFAULT_PHONE_NUMBER))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL))
            .andExpect(jsonPath("$.background").value(DEFAULT_BACKGROUND.toString()))
            .andExpect(jsonPath("$.logoContentType").value(DEFAULT_LOGO_CONTENT_TYPE))
            .andExpect(jsonPath("$.logo").value(Base64Utils.encodeToString(DEFAULT_LOGO)));
    }

    @Test
    @Transactional
    public void getNonExistingOrganisationUnit() throws Exception {
        // Get the organisationUnit
        restOrganisationUnitMockMvc.perform(get("/api/organisation-units/{id}", Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateOrganisationUnit() throws Exception {
        // Initialize the database
        organisationUnitRepository.saveAndFlush(organisationUnit);

        int databaseSizeBeforeUpdate = organisationUnitRepository.findAll().size();

        // Update the organisationUnit
        OrganisationUnit updatedOrganisationUnit = organisationUnitRepository.findById(organisationUnit.getId()).get();
        // Disconnect from session so that the updates on updatedOrganisationUnit are not directly saved in db
        em.detach(updatedOrganisationUnit);
        updatedOrganisationUnit
            .code(UPDATED_CODE)
            .name(UPDATED_NAME)
            .address(UPDATED_ADDRESS)
            .phoneNumber(UPDATED_PHONE_NUMBER)
            .email(UPDATED_EMAIL)
            .background(UPDATED_BACKGROUND)
            .logo(UPDATED_LOGO)
            .logoContentType(UPDATED_LOGO_CONTENT_TYPE);

        restOrganisationUnitMockMvc
            .perform(
                put("/api/organisation-units")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedOrganisationUnit))
            )
            .andExpect(status().isOk());

        // Validate the OrganisationUnit in the database
        List<OrganisationUnit> organisationUnitList = organisationUnitRepository.findAll();
        assertThat(organisationUnitList).hasSize(databaseSizeBeforeUpdate);
        OrganisationUnit testOrganisationUnit = organisationUnitList.get(organisationUnitList.size() - 1);
        assertThat(testOrganisationUnit.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testOrganisationUnit.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testOrganisationUnit.getAddress()).isEqualTo(UPDATED_ADDRESS);
        assertThat(testOrganisationUnit.getPhoneNumber()).isEqualTo(UPDATED_PHONE_NUMBER);
        assertThat(testOrganisationUnit.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testOrganisationUnit.getBackground()).isEqualTo(UPDATED_BACKGROUND);
        assertThat(testOrganisationUnit.getLogo()).isEqualTo(UPDATED_LOGO);
        assertThat(testOrganisationUnit.getLogoContentType()).isEqualTo(UPDATED_LOGO_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void updateNonExistingOrganisationUnit() throws Exception {
        int databaseSizeBeforeUpdate = organisationUnitRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOrganisationUnitMockMvc
            .perform(
                put("/api/organisation-units")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(organisationUnit))
            )
            .andExpect(status().isBadRequest());

        // Validate the OrganisationUnit in the database
        List<OrganisationUnit> organisationUnitList = organisationUnitRepository.findAll();
        assertThat(organisationUnitList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteOrganisationUnit() throws Exception {
        // Initialize the database
        organisationUnitRepository.saveAndFlush(organisationUnit);

        int databaseSizeBeforeDelete = organisationUnitRepository.findAll().size();

        // Delete the organisationUnit
        restOrganisationUnitMockMvc
            .perform(delete("/api/organisation-units/{id}", organisationUnit.getId()).with(csrf()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<OrganisationUnit> organisationUnitList = organisationUnitRepository.findAll();
        assertThat(organisationUnitList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
