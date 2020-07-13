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
import org.tamisemi.iftmis.domain.Risk;
import org.tamisemi.iftmis.domain.RiskRating;
import org.tamisemi.iftmis.domain.enumeration.RatingSource;
import org.tamisemi.iftmis.repository.RiskRatingRepository;
import org.tamisemi.iftmis.service.RiskRatingService;
import org.tamisemi.iftmis.service.dto.RiskRatingDTO;
import org.tamisemi.iftmis.service.mapper.RiskRatingMapper;

/**
 * Integration tests for the {@link RiskRatingResource} REST controller.
 */
@SpringBootTest(classes = IftmisApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class RiskRatingResourceIT {
    private static final RatingSource DEFAULT_SOURCE = RatingSource.COUNCIL;
    private static final RatingSource UPDATED_SOURCE = RatingSource.INSPECTOR;

    private static final Integer DEFAULT_IMPACT = 0;
    private static final Integer UPDATED_IMPACT = 1;

    private static final Integer DEFAULT_LIKELIHOOD = 0;
    private static final Integer UPDATED_LIKELIHOOD = 1;

    private static final String DEFAULT_COMMENTS = "AAAAAAAAAA";
    private static final String UPDATED_COMMENTS = "BBBBBBBBBB";

    @Autowired
    private RiskRatingRepository riskRatingRepository;

    @Autowired
    private RiskRatingMapper riskRatingMapper;

    @Autowired
    private RiskRatingService riskRatingService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restRiskRatingMockMvc;

    private RiskRating riskRating;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static RiskRating createEntity(EntityManager em) {
        RiskRating riskRating = new RiskRating()
            .source(DEFAULT_SOURCE)
            .impact(DEFAULT_IMPACT)
            .likelihood(DEFAULT_LIKELIHOOD)
            .comments(DEFAULT_COMMENTS);
        // Add required entity
        Risk risk;
        if (TestUtil.findAll(em, Risk.class).isEmpty()) {
            risk = RiskResourceIT.createEntity(em);
            em.persist(risk);
            em.flush();
        } else {
            risk = TestUtil.findAll(em, Risk.class).get(0);
        }
        riskRating.setRisk(risk);
        return riskRating;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static RiskRating createUpdatedEntity(EntityManager em) {
        RiskRating riskRating = new RiskRating()
            .source(UPDATED_SOURCE)
            .impact(UPDATED_IMPACT)
            .likelihood(UPDATED_LIKELIHOOD)
            .comments(UPDATED_COMMENTS);
        // Add required entity
        Risk risk;
        if (TestUtil.findAll(em, Risk.class).isEmpty()) {
            risk = RiskResourceIT.createUpdatedEntity(em);
            em.persist(risk);
            em.flush();
        } else {
            risk = TestUtil.findAll(em, Risk.class).get(0);
        }
        riskRating.setRisk(risk);
        return riskRating;
    }

    @BeforeEach
    public void initTest() {
        riskRating = createEntity(em);
    }

    @Test
    @Transactional
    public void createRiskRating() throws Exception {
        int databaseSizeBeforeCreate = riskRatingRepository.findAll().size();
        // Create the RiskRating
        RiskRatingDTO riskRatingDTO = riskRatingMapper.toDto(riskRating);
        restRiskRatingMockMvc
            .perform(
                post("/api/risk-ratings")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(riskRatingDTO))
            )
            .andExpect(status().isCreated());

        // Validate the RiskRating in the database
        List<RiskRating> riskRatingList = riskRatingRepository.findAll();
        assertThat(riskRatingList).hasSize(databaseSizeBeforeCreate + 1);
        RiskRating testRiskRating = riskRatingList.get(riskRatingList.size() - 1);
        assertThat(testRiskRating.getSource()).isEqualTo(DEFAULT_SOURCE);
        assertThat(testRiskRating.getImpact()).isEqualTo(DEFAULT_IMPACT);
        assertThat(testRiskRating.getLikelihood()).isEqualTo(DEFAULT_LIKELIHOOD);
        assertThat(testRiskRating.getComments()).isEqualTo(DEFAULT_COMMENTS);
    }

    @Test
    @Transactional
    public void createRiskRatingWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = riskRatingRepository.findAll().size();

        // Create the RiskRating with an existing ID
        riskRating.setId(1L);
        RiskRatingDTO riskRatingDTO = riskRatingMapper.toDto(riskRating);

        // An entity with an existing ID cannot be created, so this API call must fail
        restRiskRatingMockMvc
            .perform(
                post("/api/risk-ratings")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(riskRatingDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the RiskRating in the database
        List<RiskRating> riskRatingList = riskRatingRepository.findAll();
        assertThat(riskRatingList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkImpactIsRequired() throws Exception {
        int databaseSizeBeforeTest = riskRatingRepository.findAll().size();
        // set the field null
        riskRating.setImpact(null);

        // Create the RiskRating, which fails.
        RiskRatingDTO riskRatingDTO = riskRatingMapper.toDto(riskRating);

        restRiskRatingMockMvc
            .perform(
                post("/api/risk-ratings")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(riskRatingDTO))
            )
            .andExpect(status().isBadRequest());

        List<RiskRating> riskRatingList = riskRatingRepository.findAll();
        assertThat(riskRatingList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLikelihoodIsRequired() throws Exception {
        int databaseSizeBeforeTest = riskRatingRepository.findAll().size();
        // set the field null
        riskRating.setLikelihood(null);

        // Create the RiskRating, which fails.
        RiskRatingDTO riskRatingDTO = riskRatingMapper.toDto(riskRating);

        restRiskRatingMockMvc
            .perform(
                post("/api/risk-ratings")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(riskRatingDTO))
            )
            .andExpect(status().isBadRequest());

        List<RiskRating> riskRatingList = riskRatingRepository.findAll();
        assertThat(riskRatingList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllRiskRatings() throws Exception {
        // Initialize the database
        riskRatingRepository.saveAndFlush(riskRating);

        // Get all the riskRatingList
        restRiskRatingMockMvc
            .perform(get("/api/risk-ratings?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(riskRating.getId().intValue())))
            .andExpect(jsonPath("$.[*].source").value(hasItem(DEFAULT_SOURCE.toString())))
            .andExpect(jsonPath("$.[*].impact").value(hasItem(DEFAULT_IMPACT)))
            .andExpect(jsonPath("$.[*].likelihood").value(hasItem(DEFAULT_LIKELIHOOD)))
            .andExpect(jsonPath("$.[*].comments").value(hasItem(DEFAULT_COMMENTS.toString())));
    }

    @Test
    @Transactional
    public void getRiskRating() throws Exception {
        // Initialize the database
        riskRatingRepository.saveAndFlush(riskRating);

        // Get the riskRating
        restRiskRatingMockMvc
            .perform(get("/api/risk-ratings/{id}", riskRating.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(riskRating.getId().intValue()))
            .andExpect(jsonPath("$.source").value(DEFAULT_SOURCE.toString()))
            .andExpect(jsonPath("$.impact").value(DEFAULT_IMPACT))
            .andExpect(jsonPath("$.likelihood").value(DEFAULT_LIKELIHOOD))
            .andExpect(jsonPath("$.comments").value(DEFAULT_COMMENTS.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingRiskRating() throws Exception {
        // Get the riskRating
        restRiskRatingMockMvc.perform(get("/api/risk-ratings/{id}", Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateRiskRating() throws Exception {
        // Initialize the database
        riskRatingRepository.saveAndFlush(riskRating);

        int databaseSizeBeforeUpdate = riskRatingRepository.findAll().size();

        // Update the riskRating
        RiskRating updatedRiskRating = riskRatingRepository.findById(riskRating.getId()).get();
        // Disconnect from session so that the updates on updatedRiskRating are not directly saved in db
        em.detach(updatedRiskRating);
        updatedRiskRating.source(UPDATED_SOURCE).impact(UPDATED_IMPACT).likelihood(UPDATED_LIKELIHOOD).comments(UPDATED_COMMENTS);
        RiskRatingDTO riskRatingDTO = riskRatingMapper.toDto(updatedRiskRating);

        restRiskRatingMockMvc
            .perform(
                put("/api/risk-ratings")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(riskRatingDTO))
            )
            .andExpect(status().isOk());

        // Validate the RiskRating in the database
        List<RiskRating> riskRatingList = riskRatingRepository.findAll();
        assertThat(riskRatingList).hasSize(databaseSizeBeforeUpdate);
        RiskRating testRiskRating = riskRatingList.get(riskRatingList.size() - 1);
        assertThat(testRiskRating.getSource()).isEqualTo(UPDATED_SOURCE);
        assertThat(testRiskRating.getImpact()).isEqualTo(UPDATED_IMPACT);
        assertThat(testRiskRating.getLikelihood()).isEqualTo(UPDATED_LIKELIHOOD);
        assertThat(testRiskRating.getComments()).isEqualTo(UPDATED_COMMENTS);
    }

    @Test
    @Transactional
    public void updateNonExistingRiskRating() throws Exception {
        int databaseSizeBeforeUpdate = riskRatingRepository.findAll().size();

        // Create the RiskRating
        RiskRatingDTO riskRatingDTO = riskRatingMapper.toDto(riskRating);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRiskRatingMockMvc
            .perform(
                put("/api/risk-ratings")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(riskRatingDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the RiskRating in the database
        List<RiskRating> riskRatingList = riskRatingRepository.findAll();
        assertThat(riskRatingList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteRiskRating() throws Exception {
        // Initialize the database
        riskRatingRepository.saveAndFlush(riskRating);

        int databaseSizeBeforeDelete = riskRatingRepository.findAll().size();

        // Delete the riskRating
        restRiskRatingMockMvc
            .perform(delete("/api/risk-ratings/{id}", riskRating.getId()).with(csrf()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<RiskRating> riskRatingList = riskRatingRepository.findAll();
        assertThat(riskRatingList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
