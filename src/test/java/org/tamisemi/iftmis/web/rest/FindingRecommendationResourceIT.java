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
import org.tamisemi.iftmis.domain.Finding;
import org.tamisemi.iftmis.domain.FindingRecommendation;
import org.tamisemi.iftmis.domain.enumeration.ImplementationStatus;
import org.tamisemi.iftmis.repository.FindingRecommendationRepository;
import org.tamisemi.iftmis.service.FindingRecommendationService;
import org.tamisemi.iftmis.service.dto.FindingRecommendationDTO;
import org.tamisemi.iftmis.service.mapper.FindingRecommendationMapper;

/**
 * Integration tests for the {@link FindingRecommendationResource} REST controller.
 */
@SpringBootTest(classes = IftmisApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class FindingRecommendationResourceIT {
    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final ImplementationStatus DEFAULT_IMPLEMENTATION_STATUS = ImplementationStatus.IMPLEMENTED;
    private static final ImplementationStatus UPDATED_IMPLEMENTATION_STATUS = ImplementationStatus.NOT_IMPLEMENTED;

    @Autowired
    private FindingRecommendationRepository findingRecommendationRepository;

    @Autowired
    private FindingRecommendationMapper findingRecommendationMapper;

    @Autowired
    private FindingRecommendationService findingRecommendationService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restFindingRecommendationMockMvc;

    private FindingRecommendation findingRecommendation;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FindingRecommendation createEntity(EntityManager em) {
        FindingRecommendation findingRecommendation = new FindingRecommendation()
            .description(DEFAULT_DESCRIPTION)
            .implementationStatus(DEFAULT_IMPLEMENTATION_STATUS);
        // Add required entity
        Finding finding;
        if (TestUtil.findAll(em, Finding.class).isEmpty()) {
            finding = FindingResourceIT.createEntity(em);
            em.persist(finding);
            em.flush();
        } else {
            finding = TestUtil.findAll(em, Finding.class).get(0);
        }
        findingRecommendation.setFinding(finding);
        return findingRecommendation;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FindingRecommendation createUpdatedEntity(EntityManager em) {
        FindingRecommendation findingRecommendation = new FindingRecommendation()
            .description(UPDATED_DESCRIPTION)
            .implementationStatus(UPDATED_IMPLEMENTATION_STATUS);
        // Add required entity
        Finding finding;
        if (TestUtil.findAll(em, Finding.class).isEmpty()) {
            finding = FindingResourceIT.createUpdatedEntity(em);
            em.persist(finding);
            em.flush();
        } else {
            finding = TestUtil.findAll(em, Finding.class).get(0);
        }
        findingRecommendation.setFinding(finding);
        return findingRecommendation;
    }

    @BeforeEach
    public void initTest() {
        findingRecommendation = createEntity(em);
    }

    @Test
    @Transactional
    public void createFindingRecommendation() throws Exception {
        int databaseSizeBeforeCreate = findingRecommendationRepository.findAll().size();
        // Create the FindingRecommendation
        FindingRecommendationDTO findingRecommendationDTO = findingRecommendationMapper.toDto(findingRecommendation);
        restFindingRecommendationMockMvc
            .perform(
                post("/api/finding-recommendations")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(findingRecommendationDTO))
            )
            .andExpect(status().isCreated());

        // Validate the FindingRecommendation in the database
        List<FindingRecommendation> findingRecommendationList = findingRecommendationRepository.findAll();
        assertThat(findingRecommendationList).hasSize(databaseSizeBeforeCreate + 1);
        FindingRecommendation testFindingRecommendation = findingRecommendationList.get(findingRecommendationList.size() - 1);
        assertThat(testFindingRecommendation.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testFindingRecommendation.getImplementationStatus()).isEqualTo(DEFAULT_IMPLEMENTATION_STATUS);
    }

    @Test
    @Transactional
    public void createFindingRecommendationWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = findingRecommendationRepository.findAll().size();

        // Create the FindingRecommendation with an existing ID
        findingRecommendation.setId(1L);
        FindingRecommendationDTO findingRecommendationDTO = findingRecommendationMapper.toDto(findingRecommendation);

        // An entity with an existing ID cannot be created, so this API call must fail
        restFindingRecommendationMockMvc
            .perform(
                post("/api/finding-recommendations")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(findingRecommendationDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the FindingRecommendation in the database
        List<FindingRecommendation> findingRecommendationList = findingRecommendationRepository.findAll();
        assertThat(findingRecommendationList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkImplementationStatusIsRequired() throws Exception {
        int databaseSizeBeforeTest = findingRecommendationRepository.findAll().size();
        // set the field null
        findingRecommendation.setImplementationStatus(null);

        // Create the FindingRecommendation, which fails.
        FindingRecommendationDTO findingRecommendationDTO = findingRecommendationMapper.toDto(findingRecommendation);

        restFindingRecommendationMockMvc
            .perform(
                post("/api/finding-recommendations")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(findingRecommendationDTO))
            )
            .andExpect(status().isBadRequest());

        List<FindingRecommendation> findingRecommendationList = findingRecommendationRepository.findAll();
        assertThat(findingRecommendationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllFindingRecommendations() throws Exception {
        // Initialize the database
        findingRecommendationRepository.saveAndFlush(findingRecommendation);

        // Get all the findingRecommendationList
        restFindingRecommendationMockMvc
            .perform(get("/api/finding-recommendations?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(findingRecommendation.getId().intValue())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].implementationStatus").value(hasItem(DEFAULT_IMPLEMENTATION_STATUS.toString())));
    }

    @Test
    @Transactional
    public void getFindingRecommendation() throws Exception {
        // Initialize the database
        findingRecommendationRepository.saveAndFlush(findingRecommendation);

        // Get the findingRecommendation
        restFindingRecommendationMockMvc
            .perform(get("/api/finding-recommendations/{id}", findingRecommendation.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(findingRecommendation.getId().intValue()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.implementationStatus").value(DEFAULT_IMPLEMENTATION_STATUS.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingFindingRecommendation() throws Exception {
        // Get the findingRecommendation
        restFindingRecommendationMockMvc.perform(get("/api/finding-recommendations/{id}", Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateFindingRecommendation() throws Exception {
        // Initialize the database
        findingRecommendationRepository.saveAndFlush(findingRecommendation);

        int databaseSizeBeforeUpdate = findingRecommendationRepository.findAll().size();

        // Update the findingRecommendation
        FindingRecommendation updatedFindingRecommendation = findingRecommendationRepository.findById(findingRecommendation.getId()).get();
        // Disconnect from session so that the updates on updatedFindingRecommendation are not directly saved in db
        em.detach(updatedFindingRecommendation);
        updatedFindingRecommendation.description(UPDATED_DESCRIPTION).implementationStatus(UPDATED_IMPLEMENTATION_STATUS);
        FindingRecommendationDTO findingRecommendationDTO = findingRecommendationMapper.toDto(updatedFindingRecommendation);

        restFindingRecommendationMockMvc
            .perform(
                put("/api/finding-recommendations")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(findingRecommendationDTO))
            )
            .andExpect(status().isOk());

        // Validate the FindingRecommendation in the database
        List<FindingRecommendation> findingRecommendationList = findingRecommendationRepository.findAll();
        assertThat(findingRecommendationList).hasSize(databaseSizeBeforeUpdate);
        FindingRecommendation testFindingRecommendation = findingRecommendationList.get(findingRecommendationList.size() - 1);
        assertThat(testFindingRecommendation.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testFindingRecommendation.getImplementationStatus()).isEqualTo(UPDATED_IMPLEMENTATION_STATUS);
    }

    @Test
    @Transactional
    public void updateNonExistingFindingRecommendation() throws Exception {
        int databaseSizeBeforeUpdate = findingRecommendationRepository.findAll().size();

        // Create the FindingRecommendation
        FindingRecommendationDTO findingRecommendationDTO = findingRecommendationMapper.toDto(findingRecommendation);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFindingRecommendationMockMvc
            .perform(
                put("/api/finding-recommendations")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(findingRecommendationDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the FindingRecommendation in the database
        List<FindingRecommendation> findingRecommendationList = findingRecommendationRepository.findAll();
        assertThat(findingRecommendationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteFindingRecommendation() throws Exception {
        // Initialize the database
        findingRecommendationRepository.saveAndFlush(findingRecommendation);

        int databaseSizeBeforeDelete = findingRecommendationRepository.findAll().size();

        // Delete the findingRecommendation
        restFindingRecommendationMockMvc
            .perform(
                delete("/api/finding-recommendations/{id}", findingRecommendation.getId()).with(csrf()).accept(MediaType.APPLICATION_JSON)
            )
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<FindingRecommendation> findingRecommendationList = findingRecommendationRepository.findAll();
        assertThat(findingRecommendationList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
