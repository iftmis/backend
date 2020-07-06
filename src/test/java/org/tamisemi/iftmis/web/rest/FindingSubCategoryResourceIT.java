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
import org.tamisemi.iftmis.domain.FindingSubCategory;
import org.tamisemi.iftmis.repository.FindingSubCategoryRepository;

/**
 * Integration tests for the {@link FindingSubCategoryResource} REST controller.
 */
@SpringBootTest(classes = IftmisApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class FindingSubCategoryResourceIT {
    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    @Autowired
    private FindingSubCategoryRepository findingSubCategoryRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restFindingSubCategoryMockMvc;

    private FindingSubCategory findingSubCategory;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FindingSubCategory createEntity(EntityManager em) {
        FindingSubCategory findingSubCategory = new FindingSubCategory().code(DEFAULT_CODE).name(DEFAULT_NAME);
        return findingSubCategory;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FindingSubCategory createUpdatedEntity(EntityManager em) {
        FindingSubCategory findingSubCategory = new FindingSubCategory().code(UPDATED_CODE).name(UPDATED_NAME);
        return findingSubCategory;
    }

    @BeforeEach
    public void initTest() {
        findingSubCategory = createEntity(em);
    }

    @Test
    @Transactional
    public void createFindingSubCategory() throws Exception {
        int databaseSizeBeforeCreate = findingSubCategoryRepository.findAll().size();
        // Create the FindingSubCategory
        restFindingSubCategoryMockMvc
            .perform(
                post("/api/finding-sub-categories")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(findingSubCategory))
            )
            .andExpect(status().isCreated());

        // Validate the FindingSubCategory in the database
        List<FindingSubCategory> findingSubCategoryList = findingSubCategoryRepository.findAll();
        assertThat(findingSubCategoryList).hasSize(databaseSizeBeforeCreate + 1);
        FindingSubCategory testFindingSubCategory = findingSubCategoryList.get(findingSubCategoryList.size() - 1);
        assertThat(testFindingSubCategory.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testFindingSubCategory.getName()).isEqualTo(DEFAULT_NAME);
    }

    @Test
    @Transactional
    public void createFindingSubCategoryWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = findingSubCategoryRepository.findAll().size();

        // Create the FindingSubCategory with an existing ID
        findingSubCategory.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restFindingSubCategoryMockMvc
            .perform(
                post("/api/finding-sub-categories")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(findingSubCategory))
            )
            .andExpect(status().isBadRequest());

        // Validate the FindingSubCategory in the database
        List<FindingSubCategory> findingSubCategoryList = findingSubCategoryRepository.findAll();
        assertThat(findingSubCategoryList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = findingSubCategoryRepository.findAll().size();
        // set the field null
        findingSubCategory.setName(null);

        // Create the FindingSubCategory, which fails.

        restFindingSubCategoryMockMvc
            .perform(
                post("/api/finding-sub-categories")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(findingSubCategory))
            )
            .andExpect(status().isBadRequest());

        List<FindingSubCategory> findingSubCategoryList = findingSubCategoryRepository.findAll();
        assertThat(findingSubCategoryList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllFindingSubCategories() throws Exception {
        // Initialize the database
        findingSubCategoryRepository.saveAndFlush(findingSubCategory);

        // Get all the findingSubCategoryList
        restFindingSubCategoryMockMvc
            .perform(get("/api/finding-sub-categories?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(findingSubCategory.getId().intValue())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)));
    }

    @Test
    @Transactional
    public void getFindingSubCategory() throws Exception {
        // Initialize the database
        findingSubCategoryRepository.saveAndFlush(findingSubCategory);

        // Get the findingSubCategory
        restFindingSubCategoryMockMvc
            .perform(get("/api/finding-sub-categories/{id}", findingSubCategory.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(findingSubCategory.getId().intValue()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME));
    }

    @Test
    @Transactional
    public void getNonExistingFindingSubCategory() throws Exception {
        // Get the findingSubCategory
        restFindingSubCategoryMockMvc.perform(get("/api/finding-sub-categories/{id}", Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateFindingSubCategory() throws Exception {
        // Initialize the database
        findingSubCategoryRepository.saveAndFlush(findingSubCategory);

        int databaseSizeBeforeUpdate = findingSubCategoryRepository.findAll().size();

        // Update the findingSubCategory
        FindingSubCategory updatedFindingSubCategory = findingSubCategoryRepository.findById(findingSubCategory.getId()).get();
        // Disconnect from session so that the updates on updatedFindingSubCategory are not directly saved in db
        em.detach(updatedFindingSubCategory);
        updatedFindingSubCategory.code(UPDATED_CODE).name(UPDATED_NAME);

        restFindingSubCategoryMockMvc
            .perform(
                put("/api/finding-sub-categories")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedFindingSubCategory))
            )
            .andExpect(status().isOk());

        // Validate the FindingSubCategory in the database
        List<FindingSubCategory> findingSubCategoryList = findingSubCategoryRepository.findAll();
        assertThat(findingSubCategoryList).hasSize(databaseSizeBeforeUpdate);
        FindingSubCategory testFindingSubCategory = findingSubCategoryList.get(findingSubCategoryList.size() - 1);
        assertThat(testFindingSubCategory.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testFindingSubCategory.getName()).isEqualTo(UPDATED_NAME);
    }

    @Test
    @Transactional
    public void updateNonExistingFindingSubCategory() throws Exception {
        int databaseSizeBeforeUpdate = findingSubCategoryRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFindingSubCategoryMockMvc
            .perform(
                put("/api/finding-sub-categories")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(findingSubCategory))
            )
            .andExpect(status().isBadRequest());

        // Validate the FindingSubCategory in the database
        List<FindingSubCategory> findingSubCategoryList = findingSubCategoryRepository.findAll();
        assertThat(findingSubCategoryList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteFindingSubCategory() throws Exception {
        // Initialize the database
        findingSubCategoryRepository.saveAndFlush(findingSubCategory);

        int databaseSizeBeforeDelete = findingSubCategoryRepository.findAll().size();

        // Delete the findingSubCategory
        restFindingSubCategoryMockMvc
            .perform(delete("/api/finding-sub-categories/{id}", findingSubCategory.getId()).with(csrf()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<FindingSubCategory> findingSubCategoryList = findingSubCategoryRepository.findAll();
        assertThat(findingSubCategoryList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
