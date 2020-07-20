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
import org.tamisemi.iftmis.domain.InspectionActivity;
import org.tamisemi.iftmis.domain.InspectionActivityQuarter;
import org.tamisemi.iftmis.domain.Quarter;
import org.tamisemi.iftmis.repository.InspectionActivityQuarterRepository;
import org.tamisemi.iftmis.service.InspectionActivityQuarterService;
import org.tamisemi.iftmis.service.dto.InspectionActivityQuarterDTO;
import org.tamisemi.iftmis.service.mapper.InspectionActivityQuarterMapper;

/**
 * Integration tests for the {@link InspectionActivityQuarterResource} REST controller.
 */
@SpringBootTest(classes = IftmisApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class InspectionActivityQuarterResourceIT {
    private static final Integer DEFAULT_DAYS = 1;
    private static final Integer UPDATED_DAYS = 2;

    @Autowired
    private InspectionActivityQuarterRepository inspectionActivityQuarterRepository;

    @Autowired
    private InspectionActivityQuarterMapper inspectionActivityQuarterMapper;

    @Autowired
    private InspectionActivityQuarterService inspectionActivityQuarterService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restInspectionActivityQuarterMockMvc;

    private InspectionActivityQuarter inspectionActivityQuarter;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static InspectionActivityQuarter createEntity(EntityManager em) {
        InspectionActivityQuarter inspectionActivityQuarter = new InspectionActivityQuarter().days(DEFAULT_DAYS);
        // Add required entity
        InspectionActivity inspectionActivity;
        if (TestUtil.findAll(em, InspectionActivity.class).isEmpty()) {
            inspectionActivity = InspectionActivityResourceIT.createEntity(em);
            em.persist(inspectionActivity);
            em.flush();
        } else {
            inspectionActivity = TestUtil.findAll(em, InspectionActivity.class).get(0);
        }
        inspectionActivityQuarter.setActivity(inspectionActivity);
        // Add required entity
        Quarter quarter;
        if (TestUtil.findAll(em, Quarter.class).isEmpty()) {
            quarter = QuarterResourceIT.createEntity(em);
            em.persist(quarter);
            em.flush();
        } else {
            quarter = TestUtil.findAll(em, Quarter.class).get(0);
        }
        inspectionActivityQuarter.setQuarter(quarter);
        return inspectionActivityQuarter;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static InspectionActivityQuarter createUpdatedEntity(EntityManager em) {
        InspectionActivityQuarter inspectionActivityQuarter = new InspectionActivityQuarter().days(UPDATED_DAYS);
        // Add required entity
        InspectionActivity inspectionActivity;
        if (TestUtil.findAll(em, InspectionActivity.class).isEmpty()) {
            inspectionActivity = InspectionActivityResourceIT.createUpdatedEntity(em);
            em.persist(inspectionActivity);
            em.flush();
        } else {
            inspectionActivity = TestUtil.findAll(em, InspectionActivity.class).get(0);
        }
        inspectionActivityQuarter.setActivity(inspectionActivity);
        // Add required entity
        Quarter quarter;
        if (TestUtil.findAll(em, Quarter.class).isEmpty()) {
            quarter = QuarterResourceIT.createUpdatedEntity(em);
            em.persist(quarter);
            em.flush();
        } else {
            quarter = TestUtil.findAll(em, Quarter.class).get(0);
        }
        inspectionActivityQuarter.setQuarter(quarter);
        return inspectionActivityQuarter;
    }

    @BeforeEach
    public void initTest() {
        inspectionActivityQuarter = createEntity(em);
    }

    @Test
    @Transactional
    public void createInspectionActivityQuarter() throws Exception {
        int databaseSizeBeforeCreate = inspectionActivityQuarterRepository.findAll().size();
        // Create the InspectionActivityQuarter
        InspectionActivityQuarterDTO inspectionActivityQuarterDTO = inspectionActivityQuarterMapper.toDto(inspectionActivityQuarter);
        restInspectionActivityQuarterMockMvc
            .perform(
                post("/api/inspection-activity-quarters")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(inspectionActivityQuarterDTO))
            )
            .andExpect(status().isCreated());

        // Validate the InspectionActivityQuarter in the database
        List<InspectionActivityQuarter> inspectionActivityQuarterList = inspectionActivityQuarterRepository.findAll();
        assertThat(inspectionActivityQuarterList).hasSize(databaseSizeBeforeCreate + 1);
        InspectionActivityQuarter testInspectionActivityQuarter = inspectionActivityQuarterList.get(
            inspectionActivityQuarterList.size() - 1
        );
        assertThat(testInspectionActivityQuarter.getDays()).isEqualTo(DEFAULT_DAYS);
    }

    @Test
    @Transactional
    public void createInspectionActivityQuarterWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = inspectionActivityQuarterRepository.findAll().size();

        // Create the InspectionActivityQuarter with an existing ID
        inspectionActivityQuarter.setId(1L);
        InspectionActivityQuarterDTO inspectionActivityQuarterDTO = inspectionActivityQuarterMapper.toDto(inspectionActivityQuarter);

        // An entity with an existing ID cannot be created, so this API call must fail
        restInspectionActivityQuarterMockMvc
            .perform(
                post("/api/inspection-activity-quarters")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(inspectionActivityQuarterDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the InspectionActivityQuarter in the database
        List<InspectionActivityQuarter> inspectionActivityQuarterList = inspectionActivityQuarterRepository.findAll();
        assertThat(inspectionActivityQuarterList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllInspectionActivityQuarters() throws Exception {
        // Initialize the database
        inspectionActivityQuarterRepository.saveAndFlush(inspectionActivityQuarter);

        // Get all the inspectionActivityQuarterList
        restInspectionActivityQuarterMockMvc
            .perform(get("/api/inspection-activity-quarters?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(inspectionActivityQuarter.getId().intValue())))
            .andExpect(jsonPath("$.[*].days").value(hasItem(DEFAULT_DAYS)));
    }

    @Test
    @Transactional
    public void getInspectionActivityQuarter() throws Exception {
        // Initialize the database
        inspectionActivityQuarterRepository.saveAndFlush(inspectionActivityQuarter);

        // Get the inspectionActivityQuarter
        restInspectionActivityQuarterMockMvc
            .perform(get("/api/inspection-activity-quarters/{id}", inspectionActivityQuarter.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(inspectionActivityQuarter.getId().intValue()))
            .andExpect(jsonPath("$.days").value(DEFAULT_DAYS));
    }

    @Test
    @Transactional
    public void getNonExistingInspectionActivityQuarter() throws Exception {
        // Get the inspectionActivityQuarter
        restInspectionActivityQuarterMockMvc
            .perform(get("/api/inspection-activity-quarters/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateInspectionActivityQuarter() throws Exception {
        // Initialize the database
        inspectionActivityQuarterRepository.saveAndFlush(inspectionActivityQuarter);

        int databaseSizeBeforeUpdate = inspectionActivityQuarterRepository.findAll().size();

        // Update the inspectionActivityQuarter
        InspectionActivityQuarter updatedInspectionActivityQuarter = inspectionActivityQuarterRepository
            .findById(inspectionActivityQuarter.getId())
            .get();
        // Disconnect from session so that the updates on updatedInspectionActivityQuarter are not directly saved in db
        em.detach(updatedInspectionActivityQuarter);
        updatedInspectionActivityQuarter.days(UPDATED_DAYS);
        InspectionActivityQuarterDTO inspectionActivityQuarterDTO = inspectionActivityQuarterMapper.toDto(updatedInspectionActivityQuarter);

        restInspectionActivityQuarterMockMvc
            .perform(
                put("/api/inspection-activity-quarters")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(inspectionActivityQuarterDTO))
            )
            .andExpect(status().isOk());

        // Validate the InspectionActivityQuarter in the database
        List<InspectionActivityQuarter> inspectionActivityQuarterList = inspectionActivityQuarterRepository.findAll();
        assertThat(inspectionActivityQuarterList).hasSize(databaseSizeBeforeUpdate);
        InspectionActivityQuarter testInspectionActivityQuarter = inspectionActivityQuarterList.get(
            inspectionActivityQuarterList.size() - 1
        );
        assertThat(testInspectionActivityQuarter.getDays()).isEqualTo(UPDATED_DAYS);
    }

    @Test
    @Transactional
    public void updateNonExistingInspectionActivityQuarter() throws Exception {
        int databaseSizeBeforeUpdate = inspectionActivityQuarterRepository.findAll().size();

        // Create the InspectionActivityQuarter
        InspectionActivityQuarterDTO inspectionActivityQuarterDTO = inspectionActivityQuarterMapper.toDto(inspectionActivityQuarter);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restInspectionActivityQuarterMockMvc
            .perform(
                put("/api/inspection-activity-quarters")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(inspectionActivityQuarterDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the InspectionActivityQuarter in the database
        List<InspectionActivityQuarter> inspectionActivityQuarterList = inspectionActivityQuarterRepository.findAll();
        assertThat(inspectionActivityQuarterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteInspectionActivityQuarter() throws Exception {
        // Initialize the database
        inspectionActivityQuarterRepository.saveAndFlush(inspectionActivityQuarter);

        int databaseSizeBeforeDelete = inspectionActivityQuarterRepository.findAll().size();

        // Delete the inspectionActivityQuarter
        restInspectionActivityQuarterMockMvc
            .perform(
                delete("/api/inspection-activity-quarters/{id}", inspectionActivityQuarter.getId())
                    .with(csrf())
                    .accept(MediaType.APPLICATION_JSON)
            )
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<InspectionActivityQuarter> inspectionActivityQuarterList = inspectionActivityQuarterRepository.findAll();
        assertThat(inspectionActivityQuarterList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
