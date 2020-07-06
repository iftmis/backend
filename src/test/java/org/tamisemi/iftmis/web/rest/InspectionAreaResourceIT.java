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
import org.tamisemi.iftmis.domain.Inspection;
import org.tamisemi.iftmis.domain.InspectionArea;
import org.tamisemi.iftmis.repository.InspectionAreaRepository;

/**
 * Integration tests for the {@link InspectionAreaResource} REST controller.
 */
@SpringBootTest(classes = IftmisApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class InspectionAreaResourceIT {
    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    @Autowired
    private InspectionAreaRepository inspectionAreaRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restInspectionAreaMockMvc;

    private InspectionArea inspectionArea;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static InspectionArea createEntity(EntityManager em) {
        InspectionArea inspectionArea = new InspectionArea().name(DEFAULT_NAME);
        // Add required entity
        Inspection inspection;
        if (TestUtil.findAll(em, Inspection.class).isEmpty()) {
            inspection = InspectionResourceIT.createEntity(em);
            em.persist(inspection);
            em.flush();
        } else {
            inspection = TestUtil.findAll(em, Inspection.class).get(0);
        }
        inspectionArea.setInspection(inspection);
        return inspectionArea;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static InspectionArea createUpdatedEntity(EntityManager em) {
        InspectionArea inspectionArea = new InspectionArea().name(UPDATED_NAME);
        // Add required entity
        Inspection inspection;
        if (TestUtil.findAll(em, Inspection.class).isEmpty()) {
            inspection = InspectionResourceIT.createUpdatedEntity(em);
            em.persist(inspection);
            em.flush();
        } else {
            inspection = TestUtil.findAll(em, Inspection.class).get(0);
        }
        inspectionArea.setInspection(inspection);
        return inspectionArea;
    }

    @BeforeEach
    public void initTest() {
        inspectionArea = createEntity(em);
    }

    @Test
    @Transactional
    public void createInspectionArea() throws Exception {
        int databaseSizeBeforeCreate = inspectionAreaRepository.findAll().size();
        // Create the InspectionArea
        restInspectionAreaMockMvc
            .perform(
                post("/api/inspection-areas")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(inspectionArea))
            )
            .andExpect(status().isCreated());

        // Validate the InspectionArea in the database
        List<InspectionArea> inspectionAreaList = inspectionAreaRepository.findAll();
        assertThat(inspectionAreaList).hasSize(databaseSizeBeforeCreate + 1);
        InspectionArea testInspectionArea = inspectionAreaList.get(inspectionAreaList.size() - 1);
        assertThat(testInspectionArea.getName()).isEqualTo(DEFAULT_NAME);
    }

    @Test
    @Transactional
    public void createInspectionAreaWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = inspectionAreaRepository.findAll().size();

        // Create the InspectionArea with an existing ID
        inspectionArea.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restInspectionAreaMockMvc
            .perform(
                post("/api/inspection-areas")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(inspectionArea))
            )
            .andExpect(status().isBadRequest());

        // Validate the InspectionArea in the database
        List<InspectionArea> inspectionAreaList = inspectionAreaRepository.findAll();
        assertThat(inspectionAreaList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = inspectionAreaRepository.findAll().size();
        // set the field null
        inspectionArea.setName(null);

        // Create the InspectionArea, which fails.

        restInspectionAreaMockMvc
            .perform(
                post("/api/inspection-areas")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(inspectionArea))
            )
            .andExpect(status().isBadRequest());

        List<InspectionArea> inspectionAreaList = inspectionAreaRepository.findAll();
        assertThat(inspectionAreaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllInspectionAreas() throws Exception {
        // Initialize the database
        inspectionAreaRepository.saveAndFlush(inspectionArea);

        // Get all the inspectionAreaList
        restInspectionAreaMockMvc
            .perform(get("/api/inspection-areas?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(inspectionArea.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)));
    }

    @Test
    @Transactional
    public void getInspectionArea() throws Exception {
        // Initialize the database
        inspectionAreaRepository.saveAndFlush(inspectionArea);

        // Get the inspectionArea
        restInspectionAreaMockMvc
            .perform(get("/api/inspection-areas/{id}", inspectionArea.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(inspectionArea.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME));
    }

    @Test
    @Transactional
    public void getNonExistingInspectionArea() throws Exception {
        // Get the inspectionArea
        restInspectionAreaMockMvc.perform(get("/api/inspection-areas/{id}", Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateInspectionArea() throws Exception {
        // Initialize the database
        inspectionAreaRepository.saveAndFlush(inspectionArea);

        int databaseSizeBeforeUpdate = inspectionAreaRepository.findAll().size();

        // Update the inspectionArea
        InspectionArea updatedInspectionArea = inspectionAreaRepository.findById(inspectionArea.getId()).get();
        // Disconnect from session so that the updates on updatedInspectionArea are not directly saved in db
        em.detach(updatedInspectionArea);
        updatedInspectionArea.name(UPDATED_NAME);

        restInspectionAreaMockMvc
            .perform(
                put("/api/inspection-areas")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedInspectionArea))
            )
            .andExpect(status().isOk());

        // Validate the InspectionArea in the database
        List<InspectionArea> inspectionAreaList = inspectionAreaRepository.findAll();
        assertThat(inspectionAreaList).hasSize(databaseSizeBeforeUpdate);
        InspectionArea testInspectionArea = inspectionAreaList.get(inspectionAreaList.size() - 1);
        assertThat(testInspectionArea.getName()).isEqualTo(UPDATED_NAME);
    }

    @Test
    @Transactional
    public void updateNonExistingInspectionArea() throws Exception {
        int databaseSizeBeforeUpdate = inspectionAreaRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restInspectionAreaMockMvc
            .perform(
                put("/api/inspection-areas")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(inspectionArea))
            )
            .andExpect(status().isBadRequest());

        // Validate the InspectionArea in the database
        List<InspectionArea> inspectionAreaList = inspectionAreaRepository.findAll();
        assertThat(inspectionAreaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteInspectionArea() throws Exception {
        // Initialize the database
        inspectionAreaRepository.saveAndFlush(inspectionArea);

        int databaseSizeBeforeDelete = inspectionAreaRepository.findAll().size();

        // Delete the inspectionArea
        restInspectionAreaMockMvc
            .perform(delete("/api/inspection-areas/{id}", inspectionArea.getId()).with(csrf()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<InspectionArea> inspectionAreaList = inspectionAreaRepository.findAll();
        assertThat(inspectionAreaList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
