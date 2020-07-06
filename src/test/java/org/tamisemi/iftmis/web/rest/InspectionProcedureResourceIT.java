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
import org.tamisemi.iftmis.domain.InspectionIndicator;
import org.tamisemi.iftmis.domain.InspectionProcedure;
import org.tamisemi.iftmis.domain.Procedure;
import org.tamisemi.iftmis.repository.InspectionProcedureRepository;
import org.tamisemi.iftmis.service.InspectionProcedureService;
import org.tamisemi.iftmis.service.dto.InspectionProcedureDTO;
import org.tamisemi.iftmis.service.mapper.InspectionProcedureMapper;

/**
 * Integration tests for the {@link InspectionProcedureResource} REST controller.
 */
@SpringBootTest(classes = IftmisApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class InspectionProcedureResourceIT {
    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    @Autowired
    private InspectionProcedureRepository inspectionProcedureRepository;

    @Autowired
    private InspectionProcedureMapper inspectionProcedureMapper;

    @Autowired
    private InspectionProcedureService inspectionProcedureService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restInspectionProcedureMockMvc;

    private InspectionProcedure inspectionProcedure;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static InspectionProcedure createEntity(EntityManager em) {
        InspectionProcedure inspectionProcedure = new InspectionProcedure().name(DEFAULT_NAME);
        // Add required entity
        InspectionIndicator inspectionIndicator;
        if (TestUtil.findAll(em, InspectionIndicator.class).isEmpty()) {
            inspectionIndicator = InspectionIndicatorResourceIT.createEntity(em);
            em.persist(inspectionIndicator);
            em.flush();
        } else {
            inspectionIndicator = TestUtil.findAll(em, InspectionIndicator.class).get(0);
        }
        inspectionProcedure.setInspectionIndicator(inspectionIndicator);
        // Add required entity
        Procedure procedure;
        if (TestUtil.findAll(em, Procedure.class).isEmpty()) {
            procedure = ProcedureResourceIT.createEntity(em);
            em.persist(procedure);
            em.flush();
        } else {
            procedure = TestUtil.findAll(em, Procedure.class).get(0);
        }
        inspectionProcedure.setProcedure(procedure);
        return inspectionProcedure;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static InspectionProcedure createUpdatedEntity(EntityManager em) {
        InspectionProcedure inspectionProcedure = new InspectionProcedure().name(UPDATED_NAME);
        // Add required entity
        InspectionIndicator inspectionIndicator;
        if (TestUtil.findAll(em, InspectionIndicator.class).isEmpty()) {
            inspectionIndicator = InspectionIndicatorResourceIT.createUpdatedEntity(em);
            em.persist(inspectionIndicator);
            em.flush();
        } else {
            inspectionIndicator = TestUtil.findAll(em, InspectionIndicator.class).get(0);
        }
        inspectionProcedure.setInspectionIndicator(inspectionIndicator);
        // Add required entity
        Procedure procedure;
        if (TestUtil.findAll(em, Procedure.class).isEmpty()) {
            procedure = ProcedureResourceIT.createUpdatedEntity(em);
            em.persist(procedure);
            em.flush();
        } else {
            procedure = TestUtil.findAll(em, Procedure.class).get(0);
        }
        inspectionProcedure.setProcedure(procedure);
        return inspectionProcedure;
    }

    @BeforeEach
    public void initTest() {
        inspectionProcedure = createEntity(em);
    }

    @Test
    @Transactional
    public void createInspectionProcedure() throws Exception {
        int databaseSizeBeforeCreate = inspectionProcedureRepository.findAll().size();
        // Create the InspectionProcedure
        InspectionProcedureDTO inspectionProcedureDTO = inspectionProcedureMapper.toDto(inspectionProcedure);
        restInspectionProcedureMockMvc
            .perform(
                post("/api/inspection-procedures")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(inspectionProcedureDTO))
            )
            .andExpect(status().isCreated());

        // Validate the InspectionProcedure in the database
        List<InspectionProcedure> inspectionProcedureList = inspectionProcedureRepository.findAll();
        assertThat(inspectionProcedureList).hasSize(databaseSizeBeforeCreate + 1);
        InspectionProcedure testInspectionProcedure = inspectionProcedureList.get(inspectionProcedureList.size() - 1);
        assertThat(testInspectionProcedure.getName()).isEqualTo(DEFAULT_NAME);
    }

    @Test
    @Transactional
    public void createInspectionProcedureWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = inspectionProcedureRepository.findAll().size();

        // Create the InspectionProcedure with an existing ID
        inspectionProcedure.setId(1L);
        InspectionProcedureDTO inspectionProcedureDTO = inspectionProcedureMapper.toDto(inspectionProcedure);

        // An entity with an existing ID cannot be created, so this API call must fail
        restInspectionProcedureMockMvc
            .perform(
                post("/api/inspection-procedures")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(inspectionProcedureDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the InspectionProcedure in the database
        List<InspectionProcedure> inspectionProcedureList = inspectionProcedureRepository.findAll();
        assertThat(inspectionProcedureList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = inspectionProcedureRepository.findAll().size();
        // set the field null
        inspectionProcedure.setName(null);

        // Create the InspectionProcedure, which fails.
        InspectionProcedureDTO inspectionProcedureDTO = inspectionProcedureMapper.toDto(inspectionProcedure);

        restInspectionProcedureMockMvc
            .perform(
                post("/api/inspection-procedures")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(inspectionProcedureDTO))
            )
            .andExpect(status().isBadRequest());

        List<InspectionProcedure> inspectionProcedureList = inspectionProcedureRepository.findAll();
        assertThat(inspectionProcedureList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllInspectionProcedures() throws Exception {
        // Initialize the database
        inspectionProcedureRepository.saveAndFlush(inspectionProcedure);

        // Get all the inspectionProcedureList
        restInspectionProcedureMockMvc
            .perform(get("/api/inspection-procedures?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(inspectionProcedure.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)));
    }

    @Test
    @Transactional
    public void getInspectionProcedure() throws Exception {
        // Initialize the database
        inspectionProcedureRepository.saveAndFlush(inspectionProcedure);

        // Get the inspectionProcedure
        restInspectionProcedureMockMvc
            .perform(get("/api/inspection-procedures/{id}", inspectionProcedure.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(inspectionProcedure.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME));
    }

    @Test
    @Transactional
    public void getNonExistingInspectionProcedure() throws Exception {
        // Get the inspectionProcedure
        restInspectionProcedureMockMvc.perform(get("/api/inspection-procedures/{id}", Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateInspectionProcedure() throws Exception {
        // Initialize the database
        inspectionProcedureRepository.saveAndFlush(inspectionProcedure);

        int databaseSizeBeforeUpdate = inspectionProcedureRepository.findAll().size();

        // Update the inspectionProcedure
        InspectionProcedure updatedInspectionProcedure = inspectionProcedureRepository.findById(inspectionProcedure.getId()).get();
        // Disconnect from session so that the updates on updatedInspectionProcedure are not directly saved in db
        em.detach(updatedInspectionProcedure);
        updatedInspectionProcedure.name(UPDATED_NAME);
        InspectionProcedureDTO inspectionProcedureDTO = inspectionProcedureMapper.toDto(updatedInspectionProcedure);

        restInspectionProcedureMockMvc
            .perform(
                put("/api/inspection-procedures")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(inspectionProcedureDTO))
            )
            .andExpect(status().isOk());

        // Validate the InspectionProcedure in the database
        List<InspectionProcedure> inspectionProcedureList = inspectionProcedureRepository.findAll();
        assertThat(inspectionProcedureList).hasSize(databaseSizeBeforeUpdate);
        InspectionProcedure testInspectionProcedure = inspectionProcedureList.get(inspectionProcedureList.size() - 1);
        assertThat(testInspectionProcedure.getName()).isEqualTo(UPDATED_NAME);
    }

    @Test
    @Transactional
    public void updateNonExistingInspectionProcedure() throws Exception {
        int databaseSizeBeforeUpdate = inspectionProcedureRepository.findAll().size();

        // Create the InspectionProcedure
        InspectionProcedureDTO inspectionProcedureDTO = inspectionProcedureMapper.toDto(inspectionProcedure);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restInspectionProcedureMockMvc
            .perform(
                put("/api/inspection-procedures")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(inspectionProcedureDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the InspectionProcedure in the database
        List<InspectionProcedure> inspectionProcedureList = inspectionProcedureRepository.findAll();
        assertThat(inspectionProcedureList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteInspectionProcedure() throws Exception {
        // Initialize the database
        inspectionProcedureRepository.saveAndFlush(inspectionProcedure);

        int databaseSizeBeforeDelete = inspectionProcedureRepository.findAll().size();

        // Delete the inspectionProcedure
        restInspectionProcedureMockMvc
            .perform(delete("/api/inspection-procedures/{id}", inspectionProcedure.getId()).with(csrf()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<InspectionProcedure> inspectionProcedureList = inspectionProcedureRepository.findAll();
        assertThat(inspectionProcedureList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
