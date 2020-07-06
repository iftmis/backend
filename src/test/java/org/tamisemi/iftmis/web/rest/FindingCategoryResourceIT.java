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
import org.tamisemi.iftmis.domain.FindingCategory;
import org.tamisemi.iftmis.repository.FindingCategoryRepository;

/**
 * Integration tests for the {@link FindingCategoryResource} REST controller.
 */
@SpringBootTest(classes = IftmisApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class FindingCategoryResourceIT {
    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    @Autowired
    private FindingCategoryRepository findingCategoryRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restFindingCategoryMockMvc;

    private FindingCategory findingCategory;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FindingCategory createEntity(EntityManager em) {
        FindingCategory findingCategory = new FindingCategory().code(DEFAULT_CODE).name(DEFAULT_NAME);
        return findingCategory;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FindingCategory createUpdatedEntity(EntityManager em) {
        FindingCategory findingCategory = new FindingCategory().code(UPDATED_CODE).name(UPDATED_NAME);
        return findingCategory;
    }

    @BeforeEach
    public void initTest() {
        findingCategory = createEntity(em);
    }

    @Test
    @Transactional
    public void createFindingCategory() throws Exception {
        int databaseSizeBeforeCreate = findingCategoryRepository.findAll().size();
        // Create the FindingCategory
        restFindingCategoryMockMvc
            .perform(
                post("/api/finding-categories")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(findingCategory))
            )
            .andExpect(status().isCreated());

        // Validate the FindingCategory in the database
        List<FindingCategory> findingCategoryList = findingCategoryRepository.findAll();
        assertThat(findingCategoryList).hasSize(databaseSizeBeforeCreate + 1);
        FindingCategory testFindingCategory = findingCategoryList.get(findingCategoryList.size() - 1);
        assertThat(testFindingCategory.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testFindingCategory.getName()).isEqualTo(DEFAULT_NAME);
    }

    @Test
    @Transactional
    public void createFindingCategoryWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = findingCategoryRepository.findAll().size();

        // Create the FindingCategory with an existing ID
        findingCategory.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restFindingCategoryMockMvc
            .perform(
                post("/api/finding-categories")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(findingCategory))
            )
            .andExpect(status().isBadRequest());

        // Validate the FindingCategory in the database
        List<FindingCategory> findingCategoryList = findingCategoryRepository.findAll();
        assertThat(findingCategoryList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = findingCategoryRepository.findAll().size();
        // set the field null
        findingCategory.setName(null);

        // Create the FindingCategory, which fails.

        restFindingCategoryMockMvc
            .perform(
                post("/api/finding-categories")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(findingCategory))
            )
            .andExpect(status().isBadRequest());

        List<FindingCategory> findingCategoryList = findingCategoryRepository.findAll();
        assertThat(findingCategoryList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllFindingCategories() throws Exception {
        // Initialize the database
        findingCategoryRepository.saveAndFlush(findingCategory);

        // Get all the findingCategoryList
        restFindingCategoryMockMvc
            .perform(get("/api/finding-categories?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(findingCategory.getId().intValue())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)));
    }

    @Test
    @Transactional
    public void getFindingCategory() throws Exception {
        // Initialize the database
        findingCategoryRepository.saveAndFlush(findingCategory);

        // Get the findingCategory
        restFindingCategoryMockMvc
            .perform(get("/api/finding-categories/{id}", findingCategory.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(findingCategory.getId().intValue()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME));
    }

    @Test
    @Transactional
    public void getNonExistingFindingCategory() throws Exception {
        // Get the findingCategory
        restFindingCategoryMockMvc.perform(get("/api/finding-categories/{id}", Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateFindingCategory() throws Exception {
        // Initialize the database
        findingCategoryRepository.saveAndFlush(findingCategory);

        int databaseSizeBeforeUpdate = findingCategoryRepository.findAll().size();

        // Update the findingCategory
        FindingCategory updatedFindingCategory = findingCategoryRepository.findById(findingCategory.getId()).get();
        // Disconnect from session so that the updates on updatedFindingCategory are not directly saved in db
        em.detach(updatedFindingCategory);
        updatedFindingCategory.code(UPDATED_CODE).name(UPDATED_NAME);

        restFindingCategoryMockMvc
            .perform(
                put("/api/finding-categories")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedFindingCategory))
            )
            .andExpect(status().isOk());

        // Validate the FindingCategory in the database
        List<FindingCategory> findingCategoryList = findingCategoryRepository.findAll();
        assertThat(findingCategoryList).hasSize(databaseSizeBeforeUpdate);
        FindingCategory testFindingCategory = findingCategoryList.get(findingCategoryList.size() - 1);
        assertThat(testFindingCategory.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testFindingCategory.getName()).isEqualTo(UPDATED_NAME);
    }

    @Test
    @Transactional
    public void updateNonExistingFindingCategory() throws Exception {
        int databaseSizeBeforeUpdate = findingCategoryRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFindingCategoryMockMvc
            .perform(
                put("/api/finding-categories")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(findingCategory))
            )
            .andExpect(status().isBadRequest());

        // Validate the FindingCategory in the database
        List<FindingCategory> findingCategoryList = findingCategoryRepository.findAll();
        assertThat(findingCategoryList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteFindingCategory() throws Exception {
        // Initialize the database
        findingCategoryRepository.saveAndFlush(findingCategory);

        int databaseSizeBeforeDelete = findingCategoryRepository.findAll().size();

        // Delete the findingCategory
        restFindingCategoryMockMvc
            .perform(delete("/api/finding-categories/{id}", findingCategory.getId()).with(csrf()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<FindingCategory> findingCategoryList = findingCategoryRepository.findAll();
        assertThat(findingCategoryList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
