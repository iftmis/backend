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
import org.tamisemi.iftmis.domain.InspectionObjective;
import org.tamisemi.iftmis.domain.InspectionSubArea;
import org.tamisemi.iftmis.domain.SubArea;
import org.tamisemi.iftmis.repository.InspectionSubAreaRepository;
import org.tamisemi.iftmis.service.InspectionSubAreaService;
import org.tamisemi.iftmis.service.dto.InspectionSubAreaDTO;
import org.tamisemi.iftmis.service.mapper.InspectionSubAreaMapper;

/**
 * Integration tests for the {@link InspectionSubAreaResource} REST controller.
 */
@SpringBootTest(classes = IftmisApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class InspectionSubAreaResourceIT {
    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    @Autowired
    private InspectionSubAreaRepository inspectionSubAreaRepository;

    @Autowired
    private InspectionSubAreaMapper inspectionSubAreaMapper;

    @Autowired
    private InspectionSubAreaService inspectionSubAreaService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restInspectionSubAreaMockMvc;

    private InspectionSubArea inspectionSubArea;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static InspectionSubArea createEntity(EntityManager em) {
        InspectionSubArea inspectionSubArea = new InspectionSubArea().name(DEFAULT_NAME);
        // Add required entity
        InspectionObjective inspectionObjective;
        if (TestUtil.findAll(em, InspectionObjective.class).isEmpty()) {
            inspectionObjective = InspectionObjectiveResourceIT.createEntity(em);
            em.persist(inspectionObjective);
            em.flush();
        } else {
            inspectionObjective = TestUtil.findAll(em, InspectionObjective.class).get(0);
        }
        inspectionSubArea.setInspectionObjective(inspectionObjective);
        // Add required entity
        SubArea subArea;
        if (TestUtil.findAll(em, SubArea.class).isEmpty()) {
            subArea = SubAreaResourceIT.createEntity(em);
            em.persist(subArea);
            em.flush();
        } else {
            subArea = TestUtil.findAll(em, SubArea.class).get(0);
        }
        inspectionSubArea.setSubArea(subArea);
        return inspectionSubArea;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static InspectionSubArea createUpdatedEntity(EntityManager em) {
        InspectionSubArea inspectionSubArea = new InspectionSubArea().name(UPDATED_NAME);
        // Add required entity
        InspectionObjective inspectionObjective;
        if (TestUtil.findAll(em, InspectionObjective.class).isEmpty()) {
            inspectionObjective = InspectionObjectiveResourceIT.createUpdatedEntity(em);
            em.persist(inspectionObjective);
            em.flush();
        } else {
            inspectionObjective = TestUtil.findAll(em, InspectionObjective.class).get(0);
        }
        inspectionSubArea.setInspectionObjective(inspectionObjective);
        // Add required entity
        SubArea subArea;
        if (TestUtil.findAll(em, SubArea.class).isEmpty()) {
            subArea = SubAreaResourceIT.createUpdatedEntity(em);
            em.persist(subArea);
            em.flush();
        } else {
            subArea = TestUtil.findAll(em, SubArea.class).get(0);
        }
        inspectionSubArea.setSubArea(subArea);
        return inspectionSubArea;
    }

    @BeforeEach
    public void initTest() {
        inspectionSubArea = createEntity(em);
    }

    @Test
    @Transactional
    public void createInspectionSubArea() throws Exception {
        int databaseSizeBeforeCreate = inspectionSubAreaRepository.findAll().size();
        // Create the InspectionSubArea
        InspectionSubAreaDTO inspectionSubAreaDTO = inspectionSubAreaMapper.toDto(inspectionSubArea);
        restInspectionSubAreaMockMvc
            .perform(
                post("/api/inspection-sub-areas")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(inspectionSubAreaDTO))
            )
            .andExpect(status().isCreated());

        // Validate the InspectionSubArea in the database
        List<InspectionSubArea> inspectionSubAreaList = inspectionSubAreaRepository.findAll();
        assertThat(inspectionSubAreaList).hasSize(databaseSizeBeforeCreate + 1);
        InspectionSubArea testInspectionSubArea = inspectionSubAreaList.get(inspectionSubAreaList.size() - 1);
        assertThat(testInspectionSubArea.getName()).isEqualTo(DEFAULT_NAME);
    }

    @Test
    @Transactional
    public void createInspectionSubAreaWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = inspectionSubAreaRepository.findAll().size();

        // Create the InspectionSubArea with an existing ID
        inspectionSubArea.setId(1L);
        InspectionSubAreaDTO inspectionSubAreaDTO = inspectionSubAreaMapper.toDto(inspectionSubArea);

        // An entity with an existing ID cannot be created, so this API call must fail
        restInspectionSubAreaMockMvc
            .perform(
                post("/api/inspection-sub-areas")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(inspectionSubAreaDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the InspectionSubArea in the database
        List<InspectionSubArea> inspectionSubAreaList = inspectionSubAreaRepository.findAll();
        assertThat(inspectionSubAreaList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = inspectionSubAreaRepository.findAll().size();
        // set the field null
        inspectionSubArea.setName(null);

        // Create the InspectionSubArea, which fails.
        InspectionSubAreaDTO inspectionSubAreaDTO = inspectionSubAreaMapper.toDto(inspectionSubArea);

        restInspectionSubAreaMockMvc
            .perform(
                post("/api/inspection-sub-areas")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(inspectionSubAreaDTO))
            )
            .andExpect(status().isBadRequest());

        List<InspectionSubArea> inspectionSubAreaList = inspectionSubAreaRepository.findAll();
        assertThat(inspectionSubAreaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllInspectionSubAreas() throws Exception {
        // Initialize the database
        inspectionSubAreaRepository.saveAndFlush(inspectionSubArea);

        // Get all the inspectionSubAreaList
        restInspectionSubAreaMockMvc
            .perform(get("/api/inspection-sub-areas?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(inspectionSubArea.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)));
    }

    @Test
    @Transactional
    public void getInspectionSubArea() throws Exception {
        // Initialize the database
        inspectionSubAreaRepository.saveAndFlush(inspectionSubArea);

        // Get the inspectionSubArea
        restInspectionSubAreaMockMvc
            .perform(get("/api/inspection-sub-areas/{id}", inspectionSubArea.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(inspectionSubArea.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME));
    }

    @Test
    @Transactional
    public void getNonExistingInspectionSubArea() throws Exception {
        // Get the inspectionSubArea
        restInspectionSubAreaMockMvc.perform(get("/api/inspection-sub-areas/{id}", Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateInspectionSubArea() throws Exception {
        // Initialize the database
        inspectionSubAreaRepository.saveAndFlush(inspectionSubArea);

        int databaseSizeBeforeUpdate = inspectionSubAreaRepository.findAll().size();

        // Update the inspectionSubArea
        InspectionSubArea updatedInspectionSubArea = inspectionSubAreaRepository.findById(inspectionSubArea.getId()).get();
        // Disconnect from session so that the updates on updatedInspectionSubArea are not directly saved in db
        em.detach(updatedInspectionSubArea);
        updatedInspectionSubArea.name(UPDATED_NAME);
        InspectionSubAreaDTO inspectionSubAreaDTO = inspectionSubAreaMapper.toDto(updatedInspectionSubArea);

        restInspectionSubAreaMockMvc
            .perform(
                put("/api/inspection-sub-areas")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(inspectionSubAreaDTO))
            )
            .andExpect(status().isOk());

        // Validate the InspectionSubArea in the database
        List<InspectionSubArea> inspectionSubAreaList = inspectionSubAreaRepository.findAll();
        assertThat(inspectionSubAreaList).hasSize(databaseSizeBeforeUpdate);
        InspectionSubArea testInspectionSubArea = inspectionSubAreaList.get(inspectionSubAreaList.size() - 1);
        assertThat(testInspectionSubArea.getName()).isEqualTo(UPDATED_NAME);
    }

    @Test
    @Transactional
    public void updateNonExistingInspectionSubArea() throws Exception {
        int databaseSizeBeforeUpdate = inspectionSubAreaRepository.findAll().size();

        // Create the InspectionSubArea
        InspectionSubAreaDTO inspectionSubAreaDTO = inspectionSubAreaMapper.toDto(inspectionSubArea);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restInspectionSubAreaMockMvc
            .perform(
                put("/api/inspection-sub-areas")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(inspectionSubAreaDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the InspectionSubArea in the database
        List<InspectionSubArea> inspectionSubAreaList = inspectionSubAreaRepository.findAll();
        assertThat(inspectionSubAreaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteInspectionSubArea() throws Exception {
        // Initialize the database
        inspectionSubAreaRepository.saveAndFlush(inspectionSubArea);

        int databaseSizeBeforeDelete = inspectionSubAreaRepository.findAll().size();

        // Delete the inspectionSubArea
        restInspectionSubAreaMockMvc
            .perform(delete("/api/inspection-sub-areas/{id}", inspectionSubArea.getId()).with(csrf()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<InspectionSubArea> inspectionSubAreaList = inspectionSubAreaRepository.findAll();
        assertThat(inspectionSubAreaList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
