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
import org.tamisemi.iftmis.domain.InspectionArea;
import org.tamisemi.iftmis.domain.InspectionObjective;
import org.tamisemi.iftmis.repository.InspectionObjectiveRepository;
import org.tamisemi.iftmis.service.InspectionObjectiveService;
import org.tamisemi.iftmis.service.dto.InspectionObjectiveDTO;
import org.tamisemi.iftmis.service.mapper.InspectionObjectiveMapper;

/**
 * Integration tests for the {@link InspectionObjectiveResource} REST controller.
 */
@SpringBootTest(classes = IftmisApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class InspectionObjectiveResourceIT {
    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    @Autowired
    private InspectionObjectiveRepository inspectionObjectiveRepository;

    @Autowired
    private InspectionObjectiveMapper inspectionObjectiveMapper;

    @Autowired
    private InspectionObjectiveService inspectionObjectiveService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restInspectionObjectiveMockMvc;

    private InspectionObjective inspectionObjective;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static InspectionObjective createEntity(EntityManager em) {
        InspectionObjective inspectionObjective = new InspectionObjective().name(DEFAULT_NAME);
        // Add required entity
        InspectionArea inspectionArea;
        if (TestUtil.findAll(em, InspectionArea.class).isEmpty()) {
            inspectionArea = InspectionAreaResourceIT.createEntity(em);
            em.persist(inspectionArea);
            em.flush();
        } else {
            inspectionArea = TestUtil.findAll(em, InspectionArea.class).get(0);
        }
        inspectionObjective.setInspectionArea(inspectionArea);
        return inspectionObjective;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static InspectionObjective createUpdatedEntity(EntityManager em) {
        InspectionObjective inspectionObjective = new InspectionObjective().name(UPDATED_NAME);
        // Add required entity
        InspectionArea inspectionArea;
        if (TestUtil.findAll(em, InspectionArea.class).isEmpty()) {
            inspectionArea = InspectionAreaResourceIT.createUpdatedEntity(em);
            em.persist(inspectionArea);
            em.flush();
        } else {
            inspectionArea = TestUtil.findAll(em, InspectionArea.class).get(0);
        }
        inspectionObjective.setInspectionArea(inspectionArea);
        return inspectionObjective;
    }

    @BeforeEach
    public void initTest() {
        inspectionObjective = createEntity(em);
    }

    @Test
    @Transactional
    public void createInspectionObjective() throws Exception {
        int databaseSizeBeforeCreate = inspectionObjectiveRepository.findAll().size();
        // Create the InspectionObjective
        InspectionObjectiveDTO inspectionObjectiveDTO = inspectionObjectiveMapper.toDto(inspectionObjective);
        restInspectionObjectiveMockMvc
            .perform(
                post("/api/inspection-objectives")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(inspectionObjectiveDTO))
            )
            .andExpect(status().isCreated());

        // Validate the InspectionObjective in the database
        List<InspectionObjective> inspectionObjectiveList = inspectionObjectiveRepository.findAll();
        assertThat(inspectionObjectiveList).hasSize(databaseSizeBeforeCreate + 1);
        InspectionObjective testInspectionObjective = inspectionObjectiveList.get(inspectionObjectiveList.size() - 1);
        assertThat(testInspectionObjective.getName()).isEqualTo(DEFAULT_NAME);
    }

    @Test
    @Transactional
    public void createInspectionObjectiveWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = inspectionObjectiveRepository.findAll().size();

        // Create the InspectionObjective with an existing ID
        inspectionObjective.setId(1L);
        InspectionObjectiveDTO inspectionObjectiveDTO = inspectionObjectiveMapper.toDto(inspectionObjective);

        // An entity with an existing ID cannot be created, so this API call must fail
        restInspectionObjectiveMockMvc
            .perform(
                post("/api/inspection-objectives")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(inspectionObjectiveDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the InspectionObjective in the database
        List<InspectionObjective> inspectionObjectiveList = inspectionObjectiveRepository.findAll();
        assertThat(inspectionObjectiveList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = inspectionObjectiveRepository.findAll().size();
        // set the field null
        inspectionObjective.setName(null);

        // Create the InspectionObjective, which fails.
        InspectionObjectiveDTO inspectionObjectiveDTO = inspectionObjectiveMapper.toDto(inspectionObjective);

        restInspectionObjectiveMockMvc
            .perform(
                post("/api/inspection-objectives")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(inspectionObjectiveDTO))
            )
            .andExpect(status().isBadRequest());

        List<InspectionObjective> inspectionObjectiveList = inspectionObjectiveRepository.findAll();
        assertThat(inspectionObjectiveList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllInspectionObjectives() throws Exception {
        // Initialize the database
        inspectionObjectiveRepository.saveAndFlush(inspectionObjective);

        // Get all the inspectionObjectiveList
        restInspectionObjectiveMockMvc
            .perform(get("/api/inspection-objectives?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(inspectionObjective.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)));
    }

    @Test
    @Transactional
    public void getInspectionObjective() throws Exception {
        // Initialize the database
        inspectionObjectiveRepository.saveAndFlush(inspectionObjective);

        // Get the inspectionObjective
        restInspectionObjectiveMockMvc
            .perform(get("/api/inspection-objectives/{id}", inspectionObjective.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(inspectionObjective.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME));
    }

    @Test
    @Transactional
    public void getNonExistingInspectionObjective() throws Exception {
        // Get the inspectionObjective
        restInspectionObjectiveMockMvc.perform(get("/api/inspection-objectives/{id}", Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateInspectionObjective() throws Exception {
        // Initialize the database
        inspectionObjectiveRepository.saveAndFlush(inspectionObjective);

        int databaseSizeBeforeUpdate = inspectionObjectiveRepository.findAll().size();

        // Update the inspectionObjective
        InspectionObjective updatedInspectionObjective = inspectionObjectiveRepository.findById(inspectionObjective.getId()).get();
        // Disconnect from session so that the updates on updatedInspectionObjective are not directly saved in db
        em.detach(updatedInspectionObjective);
        updatedInspectionObjective.name(UPDATED_NAME);
        InspectionObjectiveDTO inspectionObjectiveDTO = inspectionObjectiveMapper.toDto(updatedInspectionObjective);

        restInspectionObjectiveMockMvc
            .perform(
                put("/api/inspection-objectives")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(inspectionObjectiveDTO))
            )
            .andExpect(status().isOk());

        // Validate the InspectionObjective in the database
        List<InspectionObjective> inspectionObjectiveList = inspectionObjectiveRepository.findAll();
        assertThat(inspectionObjectiveList).hasSize(databaseSizeBeforeUpdate);
        InspectionObjective testInspectionObjective = inspectionObjectiveList.get(inspectionObjectiveList.size() - 1);
        assertThat(testInspectionObjective.getName()).isEqualTo(UPDATED_NAME);
    }

    @Test
    @Transactional
    public void updateNonExistingInspectionObjective() throws Exception {
        int databaseSizeBeforeUpdate = inspectionObjectiveRepository.findAll().size();

        // Create the InspectionObjective
        InspectionObjectiveDTO inspectionObjectiveDTO = inspectionObjectiveMapper.toDto(inspectionObjective);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restInspectionObjectiveMockMvc
            .perform(
                put("/api/inspection-objectives")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(inspectionObjectiveDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the InspectionObjective in the database
        List<InspectionObjective> inspectionObjectiveList = inspectionObjectiveRepository.findAll();
        assertThat(inspectionObjectiveList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteInspectionObjective() throws Exception {
        // Initialize the database
        inspectionObjectiveRepository.saveAndFlush(inspectionObjective);

        int databaseSizeBeforeDelete = inspectionObjectiveRepository.findAll().size();

        // Delete the inspectionObjective
        restInspectionObjectiveMockMvc
            .perform(delete("/api/inspection-objectives/{id}", inspectionObjective.getId()).with(csrf()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<InspectionObjective> inspectionObjectiveList = inspectionObjectiveRepository.findAll();
        assertThat(inspectionObjectiveList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
