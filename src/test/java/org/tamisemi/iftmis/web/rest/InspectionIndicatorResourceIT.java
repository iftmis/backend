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
import org.tamisemi.iftmis.domain.InspectionIndicator;
import org.tamisemi.iftmis.domain.InspectionSubArea;
import org.tamisemi.iftmis.repository.InspectionIndicatorRepository;
import org.tamisemi.iftmis.service.InspectionIndicatorService;
import org.tamisemi.iftmis.service.dto.InspectionIndicatorDTO;
import org.tamisemi.iftmis.service.mapper.InspectionIndicatorMapper;

/**
 * Integration tests for the {@link InspectionIndicatorResource} REST controller.
 */
@SpringBootTest(classes = IftmisApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class InspectionIndicatorResourceIT {
    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    @Autowired
    private InspectionIndicatorRepository inspectionIndicatorRepository;

    @Autowired
    private InspectionIndicatorMapper inspectionIndicatorMapper;

    @Autowired
    private InspectionIndicatorService inspectionIndicatorService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restInspectionIndicatorMockMvc;

    private InspectionIndicator inspectionIndicator;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static InspectionIndicator createEntity(EntityManager em) {
        InspectionIndicator inspectionIndicator = new InspectionIndicator().name(DEFAULT_NAME);
        // Add required entity
        InspectionSubArea inspectionSubArea;
        if (TestUtil.findAll(em, InspectionSubArea.class).isEmpty()) {
            inspectionSubArea = InspectionSubAreaResourceIT.createEntity(em);
            em.persist(inspectionSubArea);
            em.flush();
        } else {
            inspectionSubArea = TestUtil.findAll(em, InspectionSubArea.class).get(0);
        }
        inspectionIndicator.setInspectionSubArea(inspectionSubArea);
        // Add required entity
        Indicator indicator;
        if (TestUtil.findAll(em, Indicator.class).isEmpty()) {
            indicator = IndicatorResourceIT.createEntity(em);
            em.persist(indicator);
            em.flush();
        } else {
            indicator = TestUtil.findAll(em, Indicator.class).get(0);
        }
        inspectionIndicator.setIndicator(indicator);
        return inspectionIndicator;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static InspectionIndicator createUpdatedEntity(EntityManager em) {
        InspectionIndicator inspectionIndicator = new InspectionIndicator().name(UPDATED_NAME);
        // Add required entity
        InspectionSubArea inspectionSubArea;
        if (TestUtil.findAll(em, InspectionSubArea.class).isEmpty()) {
            inspectionSubArea = InspectionSubAreaResourceIT.createUpdatedEntity(em);
            em.persist(inspectionSubArea);
            em.flush();
        } else {
            inspectionSubArea = TestUtil.findAll(em, InspectionSubArea.class).get(0);
        }
        inspectionIndicator.setInspectionSubArea(inspectionSubArea);
        // Add required entity
        Indicator indicator;
        if (TestUtil.findAll(em, Indicator.class).isEmpty()) {
            indicator = IndicatorResourceIT.createUpdatedEntity(em);
            em.persist(indicator);
            em.flush();
        } else {
            indicator = TestUtil.findAll(em, Indicator.class).get(0);
        }
        inspectionIndicator.setIndicator(indicator);
        return inspectionIndicator;
    }

    @BeforeEach
    public void initTest() {
        inspectionIndicator = createEntity(em);
    }

    @Test
    @Transactional
    public void createInspectionIndicator() throws Exception {
        int databaseSizeBeforeCreate = inspectionIndicatorRepository.findAll().size();
        // Create the InspectionIndicator
        InspectionIndicatorDTO inspectionIndicatorDTO = inspectionIndicatorMapper.toDto(inspectionIndicator);
        restInspectionIndicatorMockMvc
            .perform(
                post("/api/inspection-indicators")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(inspectionIndicatorDTO))
            )
            .andExpect(status().isCreated());

        // Validate the InspectionIndicator in the database
        List<InspectionIndicator> inspectionIndicatorList = inspectionIndicatorRepository.findAll();
        assertThat(inspectionIndicatorList).hasSize(databaseSizeBeforeCreate + 1);
        InspectionIndicator testInspectionIndicator = inspectionIndicatorList.get(inspectionIndicatorList.size() - 1);
        assertThat(testInspectionIndicator.getName()).isEqualTo(DEFAULT_NAME);
    }

    @Test
    @Transactional
    public void createInspectionIndicatorWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = inspectionIndicatorRepository.findAll().size();

        // Create the InspectionIndicator with an existing ID
        inspectionIndicator.setId(1L);
        InspectionIndicatorDTO inspectionIndicatorDTO = inspectionIndicatorMapper.toDto(inspectionIndicator);

        // An entity with an existing ID cannot be created, so this API call must fail
        restInspectionIndicatorMockMvc
            .perform(
                post("/api/inspection-indicators")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(inspectionIndicatorDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the InspectionIndicator in the database
        List<InspectionIndicator> inspectionIndicatorList = inspectionIndicatorRepository.findAll();
        assertThat(inspectionIndicatorList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = inspectionIndicatorRepository.findAll().size();
        // set the field null
        inspectionIndicator.setName(null);

        // Create the InspectionIndicator, which fails.
        InspectionIndicatorDTO inspectionIndicatorDTO = inspectionIndicatorMapper.toDto(inspectionIndicator);

        restInspectionIndicatorMockMvc
            .perform(
                post("/api/inspection-indicators")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(inspectionIndicatorDTO))
            )
            .andExpect(status().isBadRequest());

        List<InspectionIndicator> inspectionIndicatorList = inspectionIndicatorRepository.findAll();
        assertThat(inspectionIndicatorList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllInspectionIndicators() throws Exception {
        // Initialize the database
        inspectionIndicatorRepository.saveAndFlush(inspectionIndicator);

        // Get all the inspectionIndicatorList
        restInspectionIndicatorMockMvc
            .perform(get("/api/inspection-indicators?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(inspectionIndicator.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)));
    }

    @Test
    @Transactional
    public void getInspectionIndicator() throws Exception {
        // Initialize the database
        inspectionIndicatorRepository.saveAndFlush(inspectionIndicator);

        // Get the inspectionIndicator
        restInspectionIndicatorMockMvc
            .perform(get("/api/inspection-indicators/{id}", inspectionIndicator.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(inspectionIndicator.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME));
    }

    @Test
    @Transactional
    public void getNonExistingInspectionIndicator() throws Exception {
        // Get the inspectionIndicator
        restInspectionIndicatorMockMvc.perform(get("/api/inspection-indicators/{id}", Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateInspectionIndicator() throws Exception {
        // Initialize the database
        inspectionIndicatorRepository.saveAndFlush(inspectionIndicator);

        int databaseSizeBeforeUpdate = inspectionIndicatorRepository.findAll().size();

        // Update the inspectionIndicator
        InspectionIndicator updatedInspectionIndicator = inspectionIndicatorRepository.findById(inspectionIndicator.getId()).get();
        // Disconnect from session so that the updates on updatedInspectionIndicator are not directly saved in db
        em.detach(updatedInspectionIndicator);
        updatedInspectionIndicator.name(UPDATED_NAME);
        InspectionIndicatorDTO inspectionIndicatorDTO = inspectionIndicatorMapper.toDto(updatedInspectionIndicator);

        restInspectionIndicatorMockMvc
            .perform(
                put("/api/inspection-indicators")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(inspectionIndicatorDTO))
            )
            .andExpect(status().isOk());

        // Validate the InspectionIndicator in the database
        List<InspectionIndicator> inspectionIndicatorList = inspectionIndicatorRepository.findAll();
        assertThat(inspectionIndicatorList).hasSize(databaseSizeBeforeUpdate);
        InspectionIndicator testInspectionIndicator = inspectionIndicatorList.get(inspectionIndicatorList.size() - 1);
        assertThat(testInspectionIndicator.getName()).isEqualTo(UPDATED_NAME);
    }

    @Test
    @Transactional
    public void updateNonExistingInspectionIndicator() throws Exception {
        int databaseSizeBeforeUpdate = inspectionIndicatorRepository.findAll().size();

        // Create the InspectionIndicator
        InspectionIndicatorDTO inspectionIndicatorDTO = inspectionIndicatorMapper.toDto(inspectionIndicator);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restInspectionIndicatorMockMvc
            .perform(
                put("/api/inspection-indicators")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(inspectionIndicatorDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the InspectionIndicator in the database
        List<InspectionIndicator> inspectionIndicatorList = inspectionIndicatorRepository.findAll();
        assertThat(inspectionIndicatorList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteInspectionIndicator() throws Exception {
        // Initialize the database
        inspectionIndicatorRepository.saveAndFlush(inspectionIndicator);

        int databaseSizeBeforeDelete = inspectionIndicatorRepository.findAll().size();

        // Delete the inspectionIndicator
        restInspectionIndicatorMockMvc
            .perform(delete("/api/inspection-indicators/{id}", inspectionIndicator.getId()).with(csrf()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<InspectionIndicator> inspectionIndicatorList = inspectionIndicatorRepository.findAll();
        assertThat(inspectionIndicatorList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
