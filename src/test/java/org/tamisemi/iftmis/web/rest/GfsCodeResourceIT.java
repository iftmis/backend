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
import org.tamisemi.iftmis.domain.GfsCode;
import org.tamisemi.iftmis.repository.GfsCodeRepository;

/**
 * Integration tests for the {@link GfsCodeResource} REST controller.
 */
@SpringBootTest(classes = IftmisApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class GfsCodeResourceIT {
    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    @Autowired
    private GfsCodeRepository gfsCodeRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restGfsCodeMockMvc;

    private GfsCode gfsCode;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static GfsCode createEntity(EntityManager em) {
        GfsCode gfsCode = new GfsCode().code(DEFAULT_CODE).description(DEFAULT_DESCRIPTION);
        return gfsCode;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static GfsCode createUpdatedEntity(EntityManager em) {
        GfsCode gfsCode = new GfsCode().code(UPDATED_CODE).description(UPDATED_DESCRIPTION);
        return gfsCode;
    }

    @BeforeEach
    public void initTest() {
        gfsCode = createEntity(em);
    }

    @Test
    @Transactional
    public void createGfsCode() throws Exception {
        int databaseSizeBeforeCreate = gfsCodeRepository.findAll().size();
        // Create the GfsCode
        restGfsCodeMockMvc
            .perform(
                post("/api/gfs-codes")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(gfsCode))
            )
            .andExpect(status().isCreated());

        // Validate the GfsCode in the database
        List<GfsCode> gfsCodeList = gfsCodeRepository.findAll();
        assertThat(gfsCodeList).hasSize(databaseSizeBeforeCreate + 1);
        GfsCode testGfsCode = gfsCodeList.get(gfsCodeList.size() - 1);
        assertThat(testGfsCode.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testGfsCode.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
    }

    @Test
    @Transactional
    public void createGfsCodeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = gfsCodeRepository.findAll().size();

        // Create the GfsCode with an existing ID
        gfsCode.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restGfsCodeMockMvc
            .perform(
                post("/api/gfs-codes")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(gfsCode))
            )
            .andExpect(status().isBadRequest());

        // Validate the GfsCode in the database
        List<GfsCode> gfsCodeList = gfsCodeRepository.findAll();
        assertThat(gfsCodeList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = gfsCodeRepository.findAll().size();
        // set the field null
        gfsCode.setCode(null);

        // Create the GfsCode, which fails.

        restGfsCodeMockMvc
            .perform(
                post("/api/gfs-codes")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(gfsCode))
            )
            .andExpect(status().isBadRequest());

        List<GfsCode> gfsCodeList = gfsCodeRepository.findAll();
        assertThat(gfsCodeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllGfsCodes() throws Exception {
        // Initialize the database
        gfsCodeRepository.saveAndFlush(gfsCode);

        // Get all the gfsCodeList
        restGfsCodeMockMvc
            .perform(get("/api/gfs-codes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(gfsCode.getId().intValue())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())));
    }

    @Test
    @Transactional
    public void getGfsCode() throws Exception {
        // Initialize the database
        gfsCodeRepository.saveAndFlush(gfsCode);

        // Get the gfsCode
        restGfsCodeMockMvc
            .perform(get("/api/gfs-codes/{id}", gfsCode.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(gfsCode.getId().intValue()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingGfsCode() throws Exception {
        // Get the gfsCode
        restGfsCodeMockMvc.perform(get("/api/gfs-codes/{id}", Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateGfsCode() throws Exception {
        // Initialize the database
        gfsCodeRepository.saveAndFlush(gfsCode);

        int databaseSizeBeforeUpdate = gfsCodeRepository.findAll().size();

        // Update the gfsCode
        GfsCode updatedGfsCode = gfsCodeRepository.findById(gfsCode.getId()).get();
        // Disconnect from session so that the updates on updatedGfsCode are not directly saved in db
        em.detach(updatedGfsCode);
        updatedGfsCode.code(UPDATED_CODE).description(UPDATED_DESCRIPTION);

        restGfsCodeMockMvc
            .perform(
                put("/api/gfs-codes")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedGfsCode))
            )
            .andExpect(status().isOk());

        // Validate the GfsCode in the database
        List<GfsCode> gfsCodeList = gfsCodeRepository.findAll();
        assertThat(gfsCodeList).hasSize(databaseSizeBeforeUpdate);
        GfsCode testGfsCode = gfsCodeList.get(gfsCodeList.size() - 1);
        assertThat(testGfsCode.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testGfsCode.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void updateNonExistingGfsCode() throws Exception {
        int databaseSizeBeforeUpdate = gfsCodeRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restGfsCodeMockMvc
            .perform(
                put("/api/gfs-codes")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(gfsCode))
            )
            .andExpect(status().isBadRequest());

        // Validate the GfsCode in the database
        List<GfsCode> gfsCodeList = gfsCodeRepository.findAll();
        assertThat(gfsCodeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteGfsCode() throws Exception {
        // Initialize the database
        gfsCodeRepository.saveAndFlush(gfsCode);

        int databaseSizeBeforeDelete = gfsCodeRepository.findAll().size();

        // Delete the gfsCode
        restGfsCodeMockMvc
            .perform(delete("/api/gfs-codes/{id}", gfsCode.getId()).with(csrf()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<GfsCode> gfsCodeList = gfsCodeRepository.findAll();
        assertThat(gfsCodeList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
