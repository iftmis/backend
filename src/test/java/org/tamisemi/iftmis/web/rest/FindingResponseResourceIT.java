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
import org.tamisemi.iftmis.domain.FindingRecommendation;
import org.tamisemi.iftmis.domain.FindingResponse;
import org.tamisemi.iftmis.domain.enumeration.ResponseType;
import org.tamisemi.iftmis.repository.FindingResponseRepository;

/**
 * Integration tests for the {@link FindingResponseResource} REST controller.
 */
@SpringBootTest(classes = IftmisApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class FindingResponseResourceIT {
    private static final ResponseType DEFAULT_SOURCE = ResponseType.AUDITOR;
    private static final ResponseType UPDATED_SOURCE = ResponseType.INSPECTOR;

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    @Autowired
    private FindingResponseRepository findingResponseRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restFindingResponseMockMvc;

    private FindingResponse findingResponse;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FindingResponse createEntity(EntityManager em) {
        FindingResponse findingResponse = new FindingResponse().source(DEFAULT_SOURCE).description(DEFAULT_DESCRIPTION);
        // Add required entity
        FindingRecommendation findingRecommendation;
        if (TestUtil.findAll(em, FindingRecommendation.class).isEmpty()) {
            findingRecommendation = FindingRecommendationResourceIT.createEntity(em);
            em.persist(findingRecommendation);
            em.flush();
        } else {
            findingRecommendation = TestUtil.findAll(em, FindingRecommendation.class).get(0);
        }
        findingResponse.setRecommendation(findingRecommendation);
        return findingResponse;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FindingResponse createUpdatedEntity(EntityManager em) {
        FindingResponse findingResponse = new FindingResponse().source(UPDATED_SOURCE).description(UPDATED_DESCRIPTION);
        // Add required entity
        FindingRecommendation findingRecommendation;
        if (TestUtil.findAll(em, FindingRecommendation.class).isEmpty()) {
            findingRecommendation = FindingRecommendationResourceIT.createUpdatedEntity(em);
            em.persist(findingRecommendation);
            em.flush();
        } else {
            findingRecommendation = TestUtil.findAll(em, FindingRecommendation.class).get(0);
        }
        findingResponse.setRecommendation(findingRecommendation);
        return findingResponse;
    }

    @BeforeEach
    public void initTest() {
        findingResponse = createEntity(em);
    }

    @Test
    @Transactional
    public void createFindingResponse() throws Exception {
        int databaseSizeBeforeCreate = findingResponseRepository.findAll().size();
        // Create the FindingResponse
        restFindingResponseMockMvc
            .perform(
                post("/api/finding-responses")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(findingResponse))
            )
            .andExpect(status().isCreated());

        // Validate the FindingResponse in the database
        List<FindingResponse> findingResponseList = findingResponseRepository.findAll();
        assertThat(findingResponseList).hasSize(databaseSizeBeforeCreate + 1);
        FindingResponse testFindingResponse = findingResponseList.get(findingResponseList.size() - 1);
        assertThat(testFindingResponse.getSource()).isEqualTo(DEFAULT_SOURCE);
        assertThat(testFindingResponse.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
    }

    @Test
    @Transactional
    public void createFindingResponseWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = findingResponseRepository.findAll().size();

        // Create the FindingResponse with an existing ID
        findingResponse.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restFindingResponseMockMvc
            .perform(
                post("/api/finding-responses")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(findingResponse))
            )
            .andExpect(status().isBadRequest());

        // Validate the FindingResponse in the database
        List<FindingResponse> findingResponseList = findingResponseRepository.findAll();
        assertThat(findingResponseList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkSourceIsRequired() throws Exception {
        int databaseSizeBeforeTest = findingResponseRepository.findAll().size();
        // set the field null
        findingResponse.setSource(null);

        // Create the FindingResponse, which fails.

        restFindingResponseMockMvc
            .perform(
                post("/api/finding-responses")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(findingResponse))
            )
            .andExpect(status().isBadRequest());

        List<FindingResponse> findingResponseList = findingResponseRepository.findAll();
        assertThat(findingResponseList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllFindingResponses() throws Exception {
        // Initialize the database
        findingResponseRepository.saveAndFlush(findingResponse);

        // Get all the findingResponseList
        restFindingResponseMockMvc
            .perform(get("/api/finding-responses?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(findingResponse.getId().intValue())))
            .andExpect(jsonPath("$.[*].source").value(hasItem(DEFAULT_SOURCE.toString())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())));
    }

    @Test
    @Transactional
    public void getFindingResponse() throws Exception {
        // Initialize the database
        findingResponseRepository.saveAndFlush(findingResponse);

        // Get the findingResponse
        restFindingResponseMockMvc
            .perform(get("/api/finding-responses/{id}", findingResponse.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(findingResponse.getId().intValue()))
            .andExpect(jsonPath("$.source").value(DEFAULT_SOURCE.toString()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingFindingResponse() throws Exception {
        // Get the findingResponse
        restFindingResponseMockMvc.perform(get("/api/finding-responses/{id}", Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateFindingResponse() throws Exception {
        // Initialize the database
        findingResponseRepository.saveAndFlush(findingResponse);

        int databaseSizeBeforeUpdate = findingResponseRepository.findAll().size();

        // Update the findingResponse
        FindingResponse updatedFindingResponse = findingResponseRepository.findById(findingResponse.getId()).get();
        // Disconnect from session so that the updates on updatedFindingResponse are not directly saved in db
        em.detach(updatedFindingResponse);
        updatedFindingResponse.source(UPDATED_SOURCE).description(UPDATED_DESCRIPTION);

        restFindingResponseMockMvc
            .perform(
                put("/api/finding-responses")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedFindingResponse))
            )
            .andExpect(status().isOk());

        // Validate the FindingResponse in the database
        List<FindingResponse> findingResponseList = findingResponseRepository.findAll();
        assertThat(findingResponseList).hasSize(databaseSizeBeforeUpdate);
        FindingResponse testFindingResponse = findingResponseList.get(findingResponseList.size() - 1);
        assertThat(testFindingResponse.getSource()).isEqualTo(UPDATED_SOURCE);
        assertThat(testFindingResponse.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void updateNonExistingFindingResponse() throws Exception {
        int databaseSizeBeforeUpdate = findingResponseRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFindingResponseMockMvc
            .perform(
                put("/api/finding-responses")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(findingResponse))
            )
            .andExpect(status().isBadRequest());

        // Validate the FindingResponse in the database
        List<FindingResponse> findingResponseList = findingResponseRepository.findAll();
        assertThat(findingResponseList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteFindingResponse() throws Exception {
        // Initialize the database
        findingResponseRepository.saveAndFlush(findingResponse);

        int databaseSizeBeforeDelete = findingResponseRepository.findAll().size();

        // Delete the findingResponse
        restFindingResponseMockMvc
            .perform(delete("/api/finding-responses/{id}", findingResponse.getId()).with(csrf()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<FindingResponse> findingResponseList = findingResponseRepository.findAll();
        assertThat(findingResponseList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
