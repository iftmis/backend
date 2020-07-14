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
import org.tamisemi.iftmis.domain.RiskRank;
import org.tamisemi.iftmis.repository.RiskRankRepository;
import org.tamisemi.iftmis.service.RiskRankService;
import org.tamisemi.iftmis.service.dto.RiskRankDTO;
import org.tamisemi.iftmis.service.mapper.RiskRankMapper;

/**
 * Integration tests for the {@link RiskRankResource} REST controller.
 */
@SpringBootTest(classes = IftmisApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class RiskRankResourceIT {
    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final Integer DEFAULT_MIN_VALUE = 0;
    private static final Integer UPDATED_MIN_VALUE = 1;

    private static final Integer DEFAULT_MAX_VALUE = 30;
    private static final Integer UPDATED_MAX_VALUE = 29;

    private static final String DEFAULT_HEX_COLOR = "AAAAAAA";
    private static final String UPDATED_HEX_COLOR = "BBBBBBB";

    @Autowired
    private RiskRankRepository riskRankRepository;

    @Autowired
    private RiskRankMapper riskRankMapper;

    @Autowired
    private RiskRankService riskRankService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restRiskRankMockMvc;

    private RiskRank riskRank;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static RiskRank createEntity(EntityManager em) {
        RiskRank riskRank = new RiskRank()
            .name(DEFAULT_NAME)
            .minValue(DEFAULT_MIN_VALUE)
            .maxValue(DEFAULT_MAX_VALUE)
            .hexColor(DEFAULT_HEX_COLOR);
        return riskRank;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static RiskRank createUpdatedEntity(EntityManager em) {
        RiskRank riskRank = new RiskRank()
            .name(UPDATED_NAME)
            .minValue(UPDATED_MIN_VALUE)
            .maxValue(UPDATED_MAX_VALUE)
            .hexColor(UPDATED_HEX_COLOR);
        return riskRank;
    }

    @BeforeEach
    public void initTest() {
        riskRank = createEntity(em);
    }

    @Test
    @Transactional
    public void createRiskRank() throws Exception {
        int databaseSizeBeforeCreate = riskRankRepository.findAll().size();
        // Create the RiskRank
        RiskRankDTO riskRankDTO = riskRankMapper.toDto(riskRank);
        restRiskRankMockMvc
            .perform(
                post("/api/risk-ranks")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(riskRankDTO))
            )
            .andExpect(status().isCreated());

        // Validate the RiskRank in the database
        List<RiskRank> riskRankList = riskRankRepository.findAll();
        assertThat(riskRankList).hasSize(databaseSizeBeforeCreate + 1);
        RiskRank testRiskRank = riskRankList.get(riskRankList.size() - 1);
        assertThat(testRiskRank.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testRiskRank.getMinValue()).isEqualTo(DEFAULT_MIN_VALUE);
        assertThat(testRiskRank.getMaxValue()).isEqualTo(DEFAULT_MAX_VALUE);
        assertThat(testRiskRank.getHexColor()).isEqualTo(DEFAULT_HEX_COLOR);
    }

    @Test
    @Transactional
    public void createRiskRankWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = riskRankRepository.findAll().size();

        // Create the RiskRank with an existing ID
        riskRank.setId(1L);
        RiskRankDTO riskRankDTO = riskRankMapper.toDto(riskRank);

        // An entity with an existing ID cannot be created, so this API call must fail
        restRiskRankMockMvc
            .perform(
                post("/api/risk-ranks")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(riskRankDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the RiskRank in the database
        List<RiskRank> riskRankList = riskRankRepository.findAll();
        assertThat(riskRankList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = riskRankRepository.findAll().size();
        // set the field null
        riskRank.setName(null);

        // Create the RiskRank, which fails.
        RiskRankDTO riskRankDTO = riskRankMapper.toDto(riskRank);

        restRiskRankMockMvc
            .perform(
                post("/api/risk-ranks")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(riskRankDTO))
            )
            .andExpect(status().isBadRequest());

        List<RiskRank> riskRankList = riskRankRepository.findAll();
        assertThat(riskRankList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkMinValueIsRequired() throws Exception {
        int databaseSizeBeforeTest = riskRankRepository.findAll().size();
        // set the field null
        riskRank.setMinValue(null);

        // Create the RiskRank, which fails.
        RiskRankDTO riskRankDTO = riskRankMapper.toDto(riskRank);

        restRiskRankMockMvc
            .perform(
                post("/api/risk-ranks")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(riskRankDTO))
            )
            .andExpect(status().isBadRequest());

        List<RiskRank> riskRankList = riskRankRepository.findAll();
        assertThat(riskRankList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkMaxValueIsRequired() throws Exception {
        int databaseSizeBeforeTest = riskRankRepository.findAll().size();
        // set the field null
        riskRank.setMaxValue(null);

        // Create the RiskRank, which fails.
        RiskRankDTO riskRankDTO = riskRankMapper.toDto(riskRank);

        restRiskRankMockMvc
            .perform(
                post("/api/risk-ranks")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(riskRankDTO))
            )
            .andExpect(status().isBadRequest());

        List<RiskRank> riskRankList = riskRankRepository.findAll();
        assertThat(riskRankList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllRiskRanks() throws Exception {
        // Initialize the database
        riskRankRepository.saveAndFlush(riskRank);

        // Get all the riskRankList
        restRiskRankMockMvc
            .perform(get("/api/risk-ranks?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(riskRank.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].minValue").value(hasItem(DEFAULT_MIN_VALUE)))
            .andExpect(jsonPath("$.[*].maxValue").value(hasItem(DEFAULT_MAX_VALUE)))
            .andExpect(jsonPath("$.[*].hexColor").value(hasItem(DEFAULT_HEX_COLOR)));
    }

    @Test
    @Transactional
    public void getRiskRank() throws Exception {
        // Initialize the database
        riskRankRepository.saveAndFlush(riskRank);

        // Get the riskRank
        restRiskRankMockMvc
            .perform(get("/api/risk-ranks/{id}", riskRank.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(riskRank.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.minValue").value(DEFAULT_MIN_VALUE))
            .andExpect(jsonPath("$.maxValue").value(DEFAULT_MAX_VALUE))
            .andExpect(jsonPath("$.hexColor").value(DEFAULT_HEX_COLOR));
    }

    @Test
    @Transactional
    public void getNonExistingRiskRank() throws Exception {
        // Get the riskRank
        restRiskRankMockMvc.perform(get("/api/risk-ranks/{id}", Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateRiskRank() throws Exception {
        // Initialize the database
        riskRankRepository.saveAndFlush(riskRank);

        int databaseSizeBeforeUpdate = riskRankRepository.findAll().size();

        // Update the riskRank
        RiskRank updatedRiskRank = riskRankRepository.findById(riskRank.getId()).get();
        // Disconnect from session so that the updates on updatedRiskRank are not directly saved in db
        em.detach(updatedRiskRank);
        updatedRiskRank.name(UPDATED_NAME).minValue(UPDATED_MIN_VALUE).maxValue(UPDATED_MAX_VALUE).hexColor(UPDATED_HEX_COLOR);
        RiskRankDTO riskRankDTO = riskRankMapper.toDto(updatedRiskRank);

        restRiskRankMockMvc
            .perform(
                put("/api/risk-ranks")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(riskRankDTO))
            )
            .andExpect(status().isOk());

        // Validate the RiskRank in the database
        List<RiskRank> riskRankList = riskRankRepository.findAll();
        assertThat(riskRankList).hasSize(databaseSizeBeforeUpdate);
        RiskRank testRiskRank = riskRankList.get(riskRankList.size() - 1);
        assertThat(testRiskRank.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testRiskRank.getMinValue()).isEqualTo(UPDATED_MIN_VALUE);
        assertThat(testRiskRank.getMaxValue()).isEqualTo(UPDATED_MAX_VALUE);
        assertThat(testRiskRank.getHexColor()).isEqualTo(UPDATED_HEX_COLOR);
    }

    @Test
    @Transactional
    public void updateNonExistingRiskRank() throws Exception {
        int databaseSizeBeforeUpdate = riskRankRepository.findAll().size();

        // Create the RiskRank
        RiskRankDTO riskRankDTO = riskRankMapper.toDto(riskRank);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRiskRankMockMvc
            .perform(
                put("/api/risk-ranks")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(riskRankDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the RiskRank in the database
        List<RiskRank> riskRankList = riskRankRepository.findAll();
        assertThat(riskRankList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteRiskRank() throws Exception {
        // Initialize the database
        riskRankRepository.saveAndFlush(riskRank);

        int databaseSizeBeforeDelete = riskRankRepository.findAll().size();

        // Delete the riskRank
        restRiskRankMockMvc
            .perform(delete("/api/risk-ranks/{id}", riskRank.getId()).with(csrf()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<RiskRank> riskRankList = riskRankRepository.findAll();
        assertThat(riskRankList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
