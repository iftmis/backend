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
import org.tamisemi.iftmis.domain.AuditableArea;
import org.tamisemi.iftmis.repository.AuditableAreaRepository;
import org.tamisemi.iftmis.service.AuditableAreaService;
import org.tamisemi.iftmis.service.dto.AuditableAreaDTO;
import org.tamisemi.iftmis.service.mapper.AuditableAreaMapper;

/**
 * Integration tests for the {@link AuditableAreaResource} REST controller.
 */
@SpringBootTest(classes = IftmisApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class AuditableAreaResourceIT {
    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    @Autowired
    private AuditableAreaRepository auditableAreaRepository;

    @Autowired
    private AuditableAreaMapper auditableAreaMapper;

    @Autowired
    private AuditableAreaService auditableAreaService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restAuditableAreaMockMvc;

    private AuditableArea auditableArea;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AuditableArea createEntity(EntityManager em) {
        AuditableArea auditableArea = new AuditableArea().code(DEFAULT_CODE).name(DEFAULT_NAME);
        return auditableArea;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AuditableArea createUpdatedEntity(EntityManager em) {
        AuditableArea auditableArea = new AuditableArea().code(UPDATED_CODE).name(UPDATED_NAME);
        return auditableArea;
    }

    @BeforeEach
    public void initTest() {
        auditableArea = createEntity(em);
    }

    @Test
    @Transactional
    public void createAuditableArea() throws Exception {
        int databaseSizeBeforeCreate = auditableAreaRepository.findAll().size();
        // Create the AuditableArea
        AuditableAreaDTO auditableAreaDTO = auditableAreaMapper.toDto(auditableArea);
        restAuditableAreaMockMvc
            .perform(
                post("/api/auditable-areas")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(auditableAreaDTO))
            )
            .andExpect(status().isCreated());

        // Validate the AuditableArea in the database
        List<AuditableArea> auditableAreaList = auditableAreaRepository.findAll();
        assertThat(auditableAreaList).hasSize(databaseSizeBeforeCreate + 1);
        AuditableArea testAuditableArea = auditableAreaList.get(auditableAreaList.size() - 1);
        assertThat(testAuditableArea.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testAuditableArea.getName()).isEqualTo(DEFAULT_NAME);
    }

    @Test
    @Transactional
    public void createAuditableAreaWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = auditableAreaRepository.findAll().size();

        // Create the AuditableArea with an existing ID
        auditableArea.setId(1L);
        AuditableAreaDTO auditableAreaDTO = auditableAreaMapper.toDto(auditableArea);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAuditableAreaMockMvc
            .perform(
                post("/api/auditable-areas")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(auditableAreaDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the AuditableArea in the database
        List<AuditableArea> auditableAreaList = auditableAreaRepository.findAll();
        assertThat(auditableAreaList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = auditableAreaRepository.findAll().size();
        // set the field null
        auditableArea.setName(null);

        // Create the AuditableArea, which fails.
        AuditableAreaDTO auditableAreaDTO = auditableAreaMapper.toDto(auditableArea);

        restAuditableAreaMockMvc
            .perform(
                post("/api/auditable-areas")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(auditableAreaDTO))
            )
            .andExpect(status().isBadRequest());

        List<AuditableArea> auditableAreaList = auditableAreaRepository.findAll();
        assertThat(auditableAreaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllAuditableAreas() throws Exception {
        // Initialize the database
        auditableAreaRepository.saveAndFlush(auditableArea);

        // Get all the auditableAreaList
        restAuditableAreaMockMvc
            .perform(get("/api/auditable-areas?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(auditableArea.getId().intValue())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)));
    }

    @Test
    @Transactional
    public void getAuditableArea() throws Exception {
        // Initialize the database
        auditableAreaRepository.saveAndFlush(auditableArea);

        // Get the auditableArea
        restAuditableAreaMockMvc
            .perform(get("/api/auditable-areas/{id}", auditableArea.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(auditableArea.getId().intValue()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME));
    }

    @Test
    @Transactional
    public void getNonExistingAuditableArea() throws Exception {
        // Get the auditableArea
        restAuditableAreaMockMvc.perform(get("/api/auditable-areas/{id}", Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAuditableArea() throws Exception {
        // Initialize the database
        auditableAreaRepository.saveAndFlush(auditableArea);

        int databaseSizeBeforeUpdate = auditableAreaRepository.findAll().size();

        // Update the auditableArea
        AuditableArea updatedAuditableArea = auditableAreaRepository.findById(auditableArea.getId()).get();
        // Disconnect from session so that the updates on updatedAuditableArea are not directly saved in db
        em.detach(updatedAuditableArea);
        updatedAuditableArea.code(UPDATED_CODE).name(UPDATED_NAME);
        AuditableAreaDTO auditableAreaDTO = auditableAreaMapper.toDto(updatedAuditableArea);

        restAuditableAreaMockMvc
            .perform(
                put("/api/auditable-areas")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(auditableAreaDTO))
            )
            .andExpect(status().isOk());

        // Validate the AuditableArea in the database
        List<AuditableArea> auditableAreaList = auditableAreaRepository.findAll();
        assertThat(auditableAreaList).hasSize(databaseSizeBeforeUpdate);
        AuditableArea testAuditableArea = auditableAreaList.get(auditableAreaList.size() - 1);
        assertThat(testAuditableArea.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testAuditableArea.getName()).isEqualTo(UPDATED_NAME);
    }

    @Test
    @Transactional
    public void updateNonExistingAuditableArea() throws Exception {
        int databaseSizeBeforeUpdate = auditableAreaRepository.findAll().size();

        // Create the AuditableArea
        AuditableAreaDTO auditableAreaDTO = auditableAreaMapper.toDto(auditableArea);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAuditableAreaMockMvc
            .perform(
                put("/api/auditable-areas")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(auditableAreaDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the AuditableArea in the database
        List<AuditableArea> auditableAreaList = auditableAreaRepository.findAll();
        assertThat(auditableAreaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteAuditableArea() throws Exception {
        // Initialize the database
        auditableAreaRepository.saveAndFlush(auditableArea);

        int databaseSizeBeforeDelete = auditableAreaRepository.findAll().size();

        // Delete the auditableArea
        restAuditableAreaMockMvc
            .perform(delete("/api/auditable-areas/{id}", auditableArea.getId()).with(csrf()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<AuditableArea> auditableAreaList = auditableAreaRepository.findAll();
        assertThat(auditableAreaList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
