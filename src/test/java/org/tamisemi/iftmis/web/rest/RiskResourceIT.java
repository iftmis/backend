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
import org.tamisemi.iftmis.domain.Objective;
import org.tamisemi.iftmis.domain.OrganisationUnit;
import org.tamisemi.iftmis.domain.Risk;
import org.tamisemi.iftmis.domain.RiskCategory;
import org.tamisemi.iftmis.domain.RiskRegister;
import org.tamisemi.iftmis.repository.RiskRepository;
import org.tamisemi.iftmis.service.RiskQueryService;
import org.tamisemi.iftmis.service.RiskService;
import org.tamisemi.iftmis.service.dto.RiskCriteria;
import org.tamisemi.iftmis.service.dto.RiskDTO;
import org.tamisemi.iftmis.service.mapper.RiskMapper;

/**
 * Integration tests for the {@link RiskResource} REST controller.
 */
@SpringBootTest(classes = IftmisApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class RiskResourceIT {
    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    @Autowired
    private RiskRepository riskRepository;

    @Autowired
    private RiskMapper riskMapper;

    @Autowired
    private RiskService riskService;

    @Autowired
    private RiskQueryService riskQueryService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restRiskMockMvc;

    private Risk risk;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Risk createEntity(EntityManager em) {
        Risk risk = new Risk().code(DEFAULT_CODE).description(DEFAULT_DESCRIPTION);
        // Add required entity
        RiskRegister riskRegister;
        if (TestUtil.findAll(em, RiskRegister.class).isEmpty()) {
            riskRegister = RiskRegisterResourceIT.createEntity(em);
            em.persist(riskRegister);
            em.flush();
        } else {
            riskRegister = TestUtil.findAll(em, RiskRegister.class).get(0);
        }
        risk.setRiskRegister(riskRegister);
        // Add required entity
        Objective objective;
        if (TestUtil.findAll(em, Objective.class).isEmpty()) {
            objective = ObjectiveResourceIT.createEntity(em);
            em.persist(objective);
            em.flush();
        } else {
            objective = TestUtil.findAll(em, Objective.class).get(0);
        }
        risk.setObjective(objective);
        // Add required entity
        RiskCategory riskCategory;
        if (TestUtil.findAll(em, RiskCategory.class).isEmpty()) {
            riskCategory = RiskCategoryResourceIT.createEntity(em);
            em.persist(riskCategory);
            em.flush();
        } else {
            riskCategory = TestUtil.findAll(em, RiskCategory.class).get(0);
        }
        risk.setRiskCategory(riskCategory);
        // Add required entity
        OrganisationUnit OrganisationUnit;
        if (TestUtil.findAll(em, OrganisationUnit.class).isEmpty()) {
            OrganisationUnit = OrganisationUnitResourceIT.createEntity(em);
            em.persist(OrganisationUnit);
            em.flush();
        } else {
            OrganisationUnit = TestUtil.findAll(em, OrganisationUnit.class).get(0);
        }
        return risk;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Risk createUpdatedEntity(EntityManager em) {
        Risk risk = new Risk().code(UPDATED_CODE).description(UPDATED_DESCRIPTION);
        // Add required entity
        RiskRegister riskRegister;
        if (TestUtil.findAll(em, RiskRegister.class).isEmpty()) {
            riskRegister = RiskRegisterResourceIT.createUpdatedEntity(em);
            em.persist(riskRegister);
            em.flush();
        } else {
            riskRegister = TestUtil.findAll(em, RiskRegister.class).get(0);
        }
        risk.setRiskRegister(riskRegister);
        // Add required entity
        Objective objective;
        if (TestUtil.findAll(em, Objective.class).isEmpty()) {
            objective = ObjectiveResourceIT.createUpdatedEntity(em);
            em.persist(objective);
            em.flush();
        } else {
            objective = TestUtil.findAll(em, Objective.class).get(0);
        }
        risk.setObjective(objective);
        // Add required entity
        RiskCategory riskCategory;
        if (TestUtil.findAll(em, RiskCategory.class).isEmpty()) {
            riskCategory = RiskCategoryResourceIT.createUpdatedEntity(em);
            em.persist(riskCategory);
            em.flush();
        } else {
            riskCategory = TestUtil.findAll(em, RiskCategory.class).get(0);
        }
        risk.setRiskCategory(riskCategory);
        // Add required entity
        OrganisationUnit OrganisationUnit;
        if (TestUtil.findAll(em, OrganisationUnit.class).isEmpty()) {
            OrganisationUnit = OrganisationUnitResourceIT.createUpdatedEntity(em);
            em.persist(OrganisationUnit);
            em.flush();
        } else {
            OrganisationUnit = TestUtil.findAll(em, OrganisationUnit.class).get(0);
        }
        return risk;
    }

    @BeforeEach
    public void initTest() {
        risk = createEntity(em);
    }

    @Test
    @Transactional
    public void createRisk() throws Exception {
        int databaseSizeBeforeCreate = riskRepository.findAll().size();
        // Create the Risk
        RiskDTO riskDTO = riskMapper.toDto(risk);
        restRiskMockMvc
            .perform(
                post("/api/risks").with(csrf()).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(riskDTO))
            )
            .andExpect(status().isCreated());

        // Validate the Risk in the database
        List<Risk> riskList = riskRepository.findAll();
        assertThat(riskList).hasSize(databaseSizeBeforeCreate + 1);
        Risk testRisk = riskList.get(riskList.size() - 1);
        assertThat(testRisk.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testRisk.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
    }

    @Test
    @Transactional
    public void createRiskWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = riskRepository.findAll().size();

        // Create the Risk with an existing ID
        risk.setId(1L);
        RiskDTO riskDTO = riskMapper.toDto(risk);

        // An entity with an existing ID cannot be created, so this API call must fail
        restRiskMockMvc
            .perform(
                post("/api/risks").with(csrf()).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(riskDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Risk in the database
        List<Risk> riskList = riskRepository.findAll();
        assertThat(riskList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllRisks() throws Exception {
        // Initialize the database
        riskRepository.saveAndFlush(risk);

        // Get all the riskList
        restRiskMockMvc
            .perform(get("/api/risks?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(risk.getId().intValue())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())));
    }

    @Test
    @Transactional
    public void getRisk() throws Exception {
        // Initialize the database
        riskRepository.saveAndFlush(risk);

        // Get the risk
        restRiskMockMvc
            .perform(get("/api/risks/{id}", risk.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(risk.getId().intValue()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()));
    }

    @Test
    @Transactional
    public void getRisksByIdFiltering() throws Exception {
        // Initialize the database
        riskRepository.saveAndFlush(risk);

        Long id = risk.getId();

        defaultRiskShouldBeFound("id.equals=" + id);
        defaultRiskShouldNotBeFound("id.notEquals=" + id);

        defaultRiskShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultRiskShouldNotBeFound("id.greaterThan=" + id);

        defaultRiskShouldBeFound("id.lessThanOrEqual=" + id);
        defaultRiskShouldNotBeFound("id.lessThan=" + id);
    }

    @Test
    @Transactional
    public void getAllRisksByCodeIsEqualToSomething() throws Exception {
        // Initialize the database
        riskRepository.saveAndFlush(risk);

        // Get all the riskList where code equals to DEFAULT_CODE
        defaultRiskShouldBeFound("code.equals=" + DEFAULT_CODE);

        // Get all the riskList where code equals to UPDATED_CODE
        defaultRiskShouldNotBeFound("code.equals=" + UPDATED_CODE);
    }

    @Test
    @Transactional
    public void getAllRisksByCodeIsNotEqualToSomething() throws Exception {
        // Initialize the database
        riskRepository.saveAndFlush(risk);

        // Get all the riskList where code not equals to DEFAULT_CODE
        defaultRiskShouldNotBeFound("code.notEquals=" + DEFAULT_CODE);

        // Get all the riskList where code not equals to UPDATED_CODE
        defaultRiskShouldBeFound("code.notEquals=" + UPDATED_CODE);
    }

    @Test
    @Transactional
    public void getAllRisksByCodeIsInShouldWork() throws Exception {
        // Initialize the database
        riskRepository.saveAndFlush(risk);

        // Get all the riskList where code in DEFAULT_CODE or UPDATED_CODE
        defaultRiskShouldBeFound("code.in=" + DEFAULT_CODE + "," + UPDATED_CODE);

        // Get all the riskList where code equals to UPDATED_CODE
        defaultRiskShouldNotBeFound("code.in=" + UPDATED_CODE);
    }

    @Test
    @Transactional
    public void getAllRisksByCodeIsNullOrNotNull() throws Exception {
        // Initialize the database
        riskRepository.saveAndFlush(risk);

        // Get all the riskList where code is not null
        defaultRiskShouldBeFound("code.specified=true");

        // Get all the riskList where code is null
        defaultRiskShouldNotBeFound("code.specified=false");
    }

    @Test
    @Transactional
    public void getAllRisksByCodeContainsSomething() throws Exception {
        // Initialize the database
        riskRepository.saveAndFlush(risk);

        // Get all the riskList where code contains DEFAULT_CODE
        defaultRiskShouldBeFound("code.contains=" + DEFAULT_CODE);

        // Get all the riskList where code contains UPDATED_CODE
        defaultRiskShouldNotBeFound("code.contains=" + UPDATED_CODE);
    }

    @Test
    @Transactional
    public void getAllRisksByCodeNotContainsSomething() throws Exception {
        // Initialize the database
        riskRepository.saveAndFlush(risk);

        // Get all the riskList where code does not contain DEFAULT_CODE
        defaultRiskShouldNotBeFound("code.doesNotContain=" + DEFAULT_CODE);

        // Get all the riskList where code does not contain UPDATED_CODE
        defaultRiskShouldBeFound("code.doesNotContain=" + UPDATED_CODE);
    }

    @Test
    @Transactional
    public void getAllRisksByRiskRegisterIsEqualToSomething() throws Exception {
        // Get already existing entity
        RiskRegister riskRegister = risk.getRiskRegister();
        riskRepository.saveAndFlush(risk);
        Long riskRegisterId = riskRegister.getId();

        // Get all the riskList where riskRegister equals to riskRegisterId
        defaultRiskShouldBeFound("riskRegisterId.equals=" + riskRegisterId);

        // Get all the riskList where riskRegister equals to riskRegisterId + 1
        defaultRiskShouldNotBeFound("riskRegisterId.equals=" + (riskRegisterId + 1));
    }

    @Test
    @Transactional
    public void getAllRisksByObjectiveIsEqualToSomething() throws Exception {
        // Get already existing entity
        Objective objective = risk.getObjective();
        riskRepository.saveAndFlush(risk);
        Long objectiveId = objective.getId();

        // Get all the riskList where objective equals to objectiveId
        defaultRiskShouldBeFound("objectiveId.equals=" + objectiveId);

        // Get all the riskList where objective equals to objectiveId + 1
        defaultRiskShouldNotBeFound("objectiveId.equals=" + (objectiveId + 1));
    }

    @Test
    @Transactional
    public void getAllRisksByRiskCategoryIsEqualToSomething() throws Exception {
        // Get already existing entity
        RiskCategory riskCategory = risk.getRiskCategory();
        riskRepository.saveAndFlush(risk);
        Long riskCategoryId = riskCategory.getId();

        // Get all the riskList where riskCategory equals to riskCategoryId
        defaultRiskShouldBeFound("riskCategoryId.equals=" + riskCategoryId);

        // Get all the riskList where riskCategory equals to riskCategoryId + 1
        defaultRiskShouldNotBeFound("riskCategoryId.equals=" + (riskCategoryId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultRiskShouldBeFound(String filter) throws Exception {
        restRiskMockMvc
            .perform(get("/api/risks?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(risk.getId().intValue())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())));

        // Check, that the count call also returns 1
        restRiskMockMvc
            .perform(get("/api/risks/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultRiskShouldNotBeFound(String filter) throws Exception {
        restRiskMockMvc
            .perform(get("/api/risks?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restRiskMockMvc
            .perform(get("/api/risks/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    public void getNonExistingRisk() throws Exception {
        // Get the risk
        restRiskMockMvc.perform(get("/api/risks/{id}", Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateRisk() throws Exception {
        // Initialize the database
        riskRepository.saveAndFlush(risk);

        int databaseSizeBeforeUpdate = riskRepository.findAll().size();

        // Update the risk
        Risk updatedRisk = riskRepository.findById(risk.getId()).get();
        // Disconnect from session so that the updates on updatedRisk are not directly saved in db
        em.detach(updatedRisk);
        updatedRisk.code(UPDATED_CODE).description(UPDATED_DESCRIPTION);
        RiskDTO riskDTO = riskMapper.toDto(updatedRisk);

        restRiskMockMvc
            .perform(
                put("/api/risks").with(csrf()).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(riskDTO))
            )
            .andExpect(status().isOk());

        // Validate the Risk in the database
        List<Risk> riskList = riskRepository.findAll();
        assertThat(riskList).hasSize(databaseSizeBeforeUpdate);
        Risk testRisk = riskList.get(riskList.size() - 1);
        assertThat(testRisk.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testRisk.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void updateNonExistingRisk() throws Exception {
        int databaseSizeBeforeUpdate = riskRepository.findAll().size();

        // Create the Risk
        RiskDTO riskDTO = riskMapper.toDto(risk);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRiskMockMvc
            .perform(
                put("/api/risks").with(csrf()).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(riskDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Risk in the database
        List<Risk> riskList = riskRepository.findAll();
        assertThat(riskList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteRisk() throws Exception {
        // Initialize the database
        riskRepository.saveAndFlush(risk);

        int databaseSizeBeforeDelete = riskRepository.findAll().size();

        // Delete the risk
        restRiskMockMvc
            .perform(delete("/api/risks/{id}", risk.getId()).with(csrf()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Risk> riskList = riskRepository.findAll();
        assertThat(riskList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
