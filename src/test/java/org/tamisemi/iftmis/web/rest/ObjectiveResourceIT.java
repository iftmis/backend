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
import org.tamisemi.iftmis.repository.ObjectiveRepository;
import org.tamisemi.iftmis.service.ObjectiveService;
import org.tamisemi.iftmis.service.dto.ObjectiveDTO;
import org.tamisemi.iftmis.service.mapper.ObjectiveMapper;

/**
 * Integration tests for the {@link ObjectiveResource} REST controller.
 */
@SpringBootTest(classes = IftmisApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class ObjectiveResourceIT {
    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    @Autowired
    private ObjectiveRepository objectiveRepository;

    @Autowired
    private ObjectiveMapper objectiveMapper;

    @Autowired
    private ObjectiveService objectiveService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restObjectiveMockMvc;

    private Objective objective;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Objective createEntity(EntityManager em) {
        Objective objective = new Objective().code(DEFAULT_CODE).description(DEFAULT_DESCRIPTION);
        return objective;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Objective createUpdatedEntity(EntityManager em) {
        Objective objective = new Objective().code(UPDATED_CODE).description(UPDATED_DESCRIPTION);
        return objective;
    }

    @BeforeEach
    public void initTest() {
        objective = createEntity(em);
    }

    @Test
    @Transactional
    public void createObjective() throws Exception {
        int databaseSizeBeforeCreate = objectiveRepository.findAll().size();
        // Create the Objective
        ObjectiveDTO objectiveDTO = objectiveMapper.toDto(objective);
        restObjectiveMockMvc
            .perform(
                post("/api/objectives")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(objectiveDTO))
            )
            .andExpect(status().isCreated());

        // Validate the Objective in the database
        List<Objective> objectiveList = objectiveRepository.findAll();
        assertThat(objectiveList).hasSize(databaseSizeBeforeCreate + 1);
        Objective testObjective = objectiveList.get(objectiveList.size() - 1);
        assertThat(testObjective.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testObjective.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
    }

    @Test
    @Transactional
    public void createObjectiveWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = objectiveRepository.findAll().size();

        // Create the Objective with an existing ID
        objective.setId(1L);
        ObjectiveDTO objectiveDTO = objectiveMapper.toDto(objective);

        // An entity with an existing ID cannot be created, so this API call must fail
        restObjectiveMockMvc
            .perform(
                post("/api/objectives")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(objectiveDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Objective in the database
        List<Objective> objectiveList = objectiveRepository.findAll();
        assertThat(objectiveList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = objectiveRepository.findAll().size();
        // set the field null
        objective.setCode(null);

        // Create the Objective, which fails.
        ObjectiveDTO objectiveDTO = objectiveMapper.toDto(objective);

        restObjectiveMockMvc
            .perform(
                post("/api/objectives")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(objectiveDTO))
            )
            .andExpect(status().isBadRequest());

        List<Objective> objectiveList = objectiveRepository.findAll();
        assertThat(objectiveList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllObjectives() throws Exception {
        // Initialize the database
        objectiveRepository.saveAndFlush(objective);

        // Get all the objectiveList
        restObjectiveMockMvc
            .perform(get("/api/objectives?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(objective.getId().intValue())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())));
    }

    @Test
    @Transactional
    public void getObjective() throws Exception {
        // Initialize the database
        objectiveRepository.saveAndFlush(objective);

        // Get the objective
        restObjectiveMockMvc
            .perform(get("/api/objectives/{id}", objective.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(objective.getId().intValue()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingObjective() throws Exception {
        // Get the objective
        restObjectiveMockMvc.perform(get("/api/objectives/{id}", Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateObjective() throws Exception {
        // Initialize the database
        objectiveRepository.saveAndFlush(objective);

        int databaseSizeBeforeUpdate = objectiveRepository.findAll().size();

        // Update the objective
        Objective updatedObjective = objectiveRepository.findById(objective.getId()).get();
        // Disconnect from session so that the updates on updatedObjective are not directly saved in db
        em.detach(updatedObjective);
        updatedObjective.code(UPDATED_CODE).description(UPDATED_DESCRIPTION);
        ObjectiveDTO objectiveDTO = objectiveMapper.toDto(updatedObjective);

        restObjectiveMockMvc
            .perform(
                put("/api/objectives")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(objectiveDTO))
            )
            .andExpect(status().isOk());

        // Validate the Objective in the database
        List<Objective> objectiveList = objectiveRepository.findAll();
        assertThat(objectiveList).hasSize(databaseSizeBeforeUpdate);
        Objective testObjective = objectiveList.get(objectiveList.size() - 1);
        assertThat(testObjective.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testObjective.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void updateNonExistingObjective() throws Exception {
        int databaseSizeBeforeUpdate = objectiveRepository.findAll().size();

        // Create the Objective
        ObjectiveDTO objectiveDTO = objectiveMapper.toDto(objective);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restObjectiveMockMvc
            .perform(
                put("/api/objectives")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(objectiveDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Objective in the database
        List<Objective> objectiveList = objectiveRepository.findAll();
        assertThat(objectiveList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteObjective() throws Exception {
        // Initialize the database
        objectiveRepository.saveAndFlush(objective);

        int databaseSizeBeforeDelete = objectiveRepository.findAll().size();

        // Delete the objective
        restObjectiveMockMvc
            .perform(delete("/api/objectives/{id}", objective.getId()).with(csrf()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Objective> objectiveList = objectiveRepository.findAll();
        assertThat(objectiveList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
