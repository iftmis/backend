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
import org.tamisemi.iftmis.domain.Indicator;
import org.tamisemi.iftmis.domain.Procedure;
import org.tamisemi.iftmis.repository.ProcedureRepository;

/**
 * Integration tests for the {@link ProcedureResource} REST controller.
 */
@SpringBootTest(classes = IftmisApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class ProcedureResourceIT {
    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    @Autowired
    private ProcedureRepository procedureRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restProcedureMockMvc;

    private Procedure procedure;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Procedure createEntity(EntityManager em) {
        Procedure procedure = new Procedure().name(DEFAULT_NAME);
        // Add required entity
        Indicator indicator;
        if (TestUtil.findAll(em, Indicator.class).isEmpty()) {
            indicator = IndicatorResourceIT.createEntity(em);
            em.persist(indicator);
            em.flush();
        } else {
            indicator = TestUtil.findAll(em, Indicator.class).get(0);
        }
        procedure.setIndicator(indicator);
        return procedure;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Procedure createUpdatedEntity(EntityManager em) {
        Procedure procedure = new Procedure().name(UPDATED_NAME);
        // Add required entity
        Indicator indicator;
        if (TestUtil.findAll(em, Indicator.class).isEmpty()) {
            indicator = IndicatorResourceIT.createUpdatedEntity(em);
            em.persist(indicator);
            em.flush();
        } else {
            indicator = TestUtil.findAll(em, Indicator.class).get(0);
        }
        procedure.setIndicator(indicator);
        return procedure;
    }

    @BeforeEach
    public void initTest() {
        procedure = createEntity(em);
    }

    @Test
    @Transactional
    public void createProcedure() throws Exception {
        int databaseSizeBeforeCreate = procedureRepository.findAll().size();
        // Create the Procedure
        restProcedureMockMvc
            .perform(
                post("/api/procedures")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(procedure))
            )
            .andExpect(status().isCreated());

        // Validate the Procedure in the database
        List<Procedure> procedureList = procedureRepository.findAll();
        assertThat(procedureList).hasSize(databaseSizeBeforeCreate + 1);
        Procedure testProcedure = procedureList.get(procedureList.size() - 1);
        assertThat(testProcedure.getName()).isEqualTo(DEFAULT_NAME);
    }

    @Test
    @Transactional
    public void createProcedureWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = procedureRepository.findAll().size();

        // Create the Procedure with an existing ID
        procedure.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restProcedureMockMvc
            .perform(
                post("/api/procedures")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(procedure))
            )
            .andExpect(status().isBadRequest());

        // Validate the Procedure in the database
        List<Procedure> procedureList = procedureRepository.findAll();
        assertThat(procedureList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = procedureRepository.findAll().size();
        // set the field null
        procedure.setName(null);

        // Create the Procedure, which fails.

        restProcedureMockMvc
            .perform(
                post("/api/procedures")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(procedure))
            )
            .andExpect(status().isBadRequest());

        List<Procedure> procedureList = procedureRepository.findAll();
        assertThat(procedureList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllProcedures() throws Exception {
        // Initialize the database
        procedureRepository.saveAndFlush(procedure);

        // Get all the procedureList
        restProcedureMockMvc
            .perform(get("/api/procedures?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(procedure.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)));
    }

    @Test
    @Transactional
    public void getProcedure() throws Exception {
        // Initialize the database
        procedureRepository.saveAndFlush(procedure);

        // Get the procedure
        restProcedureMockMvc
            .perform(get("/api/procedures/{id}", procedure.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(procedure.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME));
    }

    @Test
    @Transactional
    public void getNonExistingProcedure() throws Exception {
        // Get the procedure
        restProcedureMockMvc.perform(get("/api/procedures/{id}", Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateProcedure() throws Exception {
        // Initialize the database
        procedureRepository.saveAndFlush(procedure);

        int databaseSizeBeforeUpdate = procedureRepository.findAll().size();

        // Update the procedure
        Procedure updatedProcedure = procedureRepository.findById(procedure.getId()).get();
        // Disconnect from session so that the updates on updatedProcedure are not directly saved in db
        em.detach(updatedProcedure);
        updatedProcedure.name(UPDATED_NAME);

        restProcedureMockMvc
            .perform(
                put("/api/procedures")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedProcedure))
            )
            .andExpect(status().isOk());

        // Validate the Procedure in the database
        List<Procedure> procedureList = procedureRepository.findAll();
        assertThat(procedureList).hasSize(databaseSizeBeforeUpdate);
        Procedure testProcedure = procedureList.get(procedureList.size() - 1);
        assertThat(testProcedure.getName()).isEqualTo(UPDATED_NAME);
    }

    @Test
    @Transactional
    public void updateNonExistingProcedure() throws Exception {
        int databaseSizeBeforeUpdate = procedureRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProcedureMockMvc
            .perform(
                put("/api/procedures")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(procedure))
            )
            .andExpect(status().isBadRequest());

        // Validate the Procedure in the database
        List<Procedure> procedureList = procedureRepository.findAll();
        assertThat(procedureList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteProcedure() throws Exception {
        // Initialize the database
        procedureRepository.saveAndFlush(procedure);

        int databaseSizeBeforeDelete = procedureRepository.findAll().size();

        // Delete the procedure
        restProcedureMockMvc
            .perform(delete("/api/procedures/{id}", procedure.getId()).with(csrf()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Procedure> procedureList = procedureRepository.findAll();
        assertThat(procedureList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
