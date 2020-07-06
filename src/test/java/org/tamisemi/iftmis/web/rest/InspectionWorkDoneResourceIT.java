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
import org.tamisemi.iftmis.domain.InspectionProcedure;
import org.tamisemi.iftmis.domain.InspectionWorkDone;
import org.tamisemi.iftmis.repository.InspectionWorkDoneRepository;
import org.tamisemi.iftmis.service.InspectionWorkDoneService;
import org.tamisemi.iftmis.service.dto.InspectionWorkDoneDTO;
import org.tamisemi.iftmis.service.mapper.InspectionWorkDoneMapper;

/**
 * Integration tests for the {@link InspectionWorkDoneResource} REST controller.
 */
@SpringBootTest(classes = IftmisApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class InspectionWorkDoneResourceIT {
    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final Boolean DEFAULT_IS_OK = false;
    private static final Boolean UPDATED_IS_OK = true;

    @Autowired
    private InspectionWorkDoneRepository inspectionWorkDoneRepository;

    @Autowired
    private InspectionWorkDoneMapper inspectionWorkDoneMapper;

    @Autowired
    private InspectionWorkDoneService inspectionWorkDoneService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restInspectionWorkDoneMockMvc;

    private InspectionWorkDone inspectionWorkDone;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static InspectionWorkDone createEntity(EntityManager em) {
        InspectionWorkDone inspectionWorkDone = new InspectionWorkDone().name(DEFAULT_NAME).isOk(DEFAULT_IS_OK);
        // Add required entity
        InspectionProcedure inspectionProcedure;
        if (TestUtil.findAll(em, InspectionProcedure.class).isEmpty()) {
            inspectionProcedure = InspectionProcedureResourceIT.createEntity(em);
            em.persist(inspectionProcedure);
            em.flush();
        } else {
            inspectionProcedure = TestUtil.findAll(em, InspectionProcedure.class).get(0);
        }
        inspectionWorkDone.setProcedure(inspectionProcedure);
        return inspectionWorkDone;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static InspectionWorkDone createUpdatedEntity(EntityManager em) {
        InspectionWorkDone inspectionWorkDone = new InspectionWorkDone().name(UPDATED_NAME).isOk(UPDATED_IS_OK);
        // Add required entity
        InspectionProcedure inspectionProcedure;
        if (TestUtil.findAll(em, InspectionProcedure.class).isEmpty()) {
            inspectionProcedure = InspectionProcedureResourceIT.createUpdatedEntity(em);
            em.persist(inspectionProcedure);
            em.flush();
        } else {
            inspectionProcedure = TestUtil.findAll(em, InspectionProcedure.class).get(0);
        }
        inspectionWorkDone.setProcedure(inspectionProcedure);
        return inspectionWorkDone;
    }

    @BeforeEach
    public void initTest() {
        inspectionWorkDone = createEntity(em);
    }

    @Test
    @Transactional
    public void createInspectionWorkDone() throws Exception {
        int databaseSizeBeforeCreate = inspectionWorkDoneRepository.findAll().size();
        // Create the InspectionWorkDone
        InspectionWorkDoneDTO inspectionWorkDoneDTO = inspectionWorkDoneMapper.toDto(inspectionWorkDone);
        restInspectionWorkDoneMockMvc
            .perform(
                post("/api/inspection-work-dones")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(inspectionWorkDoneDTO))
            )
            .andExpect(status().isCreated());

        // Validate the InspectionWorkDone in the database
        List<InspectionWorkDone> inspectionWorkDoneList = inspectionWorkDoneRepository.findAll();
        assertThat(inspectionWorkDoneList).hasSize(databaseSizeBeforeCreate + 1);
        InspectionWorkDone testInspectionWorkDone = inspectionWorkDoneList.get(inspectionWorkDoneList.size() - 1);
        assertThat(testInspectionWorkDone.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testInspectionWorkDone.isIsOk()).isEqualTo(DEFAULT_IS_OK);
    }

    @Test
    @Transactional
    public void createInspectionWorkDoneWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = inspectionWorkDoneRepository.findAll().size();

        // Create the InspectionWorkDone with an existing ID
        inspectionWorkDone.setId(1L);
        InspectionWorkDoneDTO inspectionWorkDoneDTO = inspectionWorkDoneMapper.toDto(inspectionWorkDone);

        // An entity with an existing ID cannot be created, so this API call must fail
        restInspectionWorkDoneMockMvc
            .perform(
                post("/api/inspection-work-dones")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(inspectionWorkDoneDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the InspectionWorkDone in the database
        List<InspectionWorkDone> inspectionWorkDoneList = inspectionWorkDoneRepository.findAll();
        assertThat(inspectionWorkDoneList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = inspectionWorkDoneRepository.findAll().size();
        // set the field null
        inspectionWorkDone.setName(null);

        // Create the InspectionWorkDone, which fails.
        InspectionWorkDoneDTO inspectionWorkDoneDTO = inspectionWorkDoneMapper.toDto(inspectionWorkDone);

        restInspectionWorkDoneMockMvc
            .perform(
                post("/api/inspection-work-dones")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(inspectionWorkDoneDTO))
            )
            .andExpect(status().isBadRequest());

        List<InspectionWorkDone> inspectionWorkDoneList = inspectionWorkDoneRepository.findAll();
        assertThat(inspectionWorkDoneList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllInspectionWorkDones() throws Exception {
        // Initialize the database
        inspectionWorkDoneRepository.saveAndFlush(inspectionWorkDone);

        // Get all the inspectionWorkDoneList
        restInspectionWorkDoneMockMvc
            .perform(get("/api/inspection-work-dones?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(inspectionWorkDone.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].isOk").value(hasItem(DEFAULT_IS_OK.booleanValue())));
    }

    @Test
    @Transactional
    public void getInspectionWorkDone() throws Exception {
        // Initialize the database
        inspectionWorkDoneRepository.saveAndFlush(inspectionWorkDone);

        // Get the inspectionWorkDone
        restInspectionWorkDoneMockMvc
            .perform(get("/api/inspection-work-dones/{id}", inspectionWorkDone.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(inspectionWorkDone.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.isOk").value(DEFAULT_IS_OK.booleanValue()));
    }

    @Test
    @Transactional
    public void getNonExistingInspectionWorkDone() throws Exception {
        // Get the inspectionWorkDone
        restInspectionWorkDoneMockMvc.perform(get("/api/inspection-work-dones/{id}", Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateInspectionWorkDone() throws Exception {
        // Initialize the database
        inspectionWorkDoneRepository.saveAndFlush(inspectionWorkDone);

        int databaseSizeBeforeUpdate = inspectionWorkDoneRepository.findAll().size();

        // Update the inspectionWorkDone
        InspectionWorkDone updatedInspectionWorkDone = inspectionWorkDoneRepository.findById(inspectionWorkDone.getId()).get();
        // Disconnect from session so that the updates on updatedInspectionWorkDone are not directly saved in db
        em.detach(updatedInspectionWorkDone);
        updatedInspectionWorkDone.name(UPDATED_NAME).isOk(UPDATED_IS_OK);
        InspectionWorkDoneDTO inspectionWorkDoneDTO = inspectionWorkDoneMapper.toDto(updatedInspectionWorkDone);

        restInspectionWorkDoneMockMvc
            .perform(
                put("/api/inspection-work-dones")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(inspectionWorkDoneDTO))
            )
            .andExpect(status().isOk());

        // Validate the InspectionWorkDone in the database
        List<InspectionWorkDone> inspectionWorkDoneList = inspectionWorkDoneRepository.findAll();
        assertThat(inspectionWorkDoneList).hasSize(databaseSizeBeforeUpdate);
        InspectionWorkDone testInspectionWorkDone = inspectionWorkDoneList.get(inspectionWorkDoneList.size() - 1);
        assertThat(testInspectionWorkDone.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testInspectionWorkDone.isIsOk()).isEqualTo(UPDATED_IS_OK);
    }

    @Test
    @Transactional
    public void updateNonExistingInspectionWorkDone() throws Exception {
        int databaseSizeBeforeUpdate = inspectionWorkDoneRepository.findAll().size();

        // Create the InspectionWorkDone
        InspectionWorkDoneDTO inspectionWorkDoneDTO = inspectionWorkDoneMapper.toDto(inspectionWorkDone);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restInspectionWorkDoneMockMvc
            .perform(
                put("/api/inspection-work-dones")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(inspectionWorkDoneDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the InspectionWorkDone in the database
        List<InspectionWorkDone> inspectionWorkDoneList = inspectionWorkDoneRepository.findAll();
        assertThat(inspectionWorkDoneList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteInspectionWorkDone() throws Exception {
        // Initialize the database
        inspectionWorkDoneRepository.saveAndFlush(inspectionWorkDone);

        int databaseSizeBeforeDelete = inspectionWorkDoneRepository.findAll().size();

        // Delete the inspectionWorkDone
        restInspectionWorkDoneMockMvc
            .perform(delete("/api/inspection-work-dones/{id}", inspectionWorkDone.getId()).with(csrf()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<InspectionWorkDone> inspectionWorkDoneList = inspectionWorkDoneRepository.findAll();
        assertThat(inspectionWorkDoneList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
