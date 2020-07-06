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
import org.tamisemi.iftmis.domain.Finding;
import org.tamisemi.iftmis.domain.OrganisationUnit;
import org.tamisemi.iftmis.domain.enumeration.ActionPlanCategory;
import org.tamisemi.iftmis.domain.enumeration.FindingSource;
import org.tamisemi.iftmis.repository.FindingRepository;

/**
 * Integration tests for the {@link FindingResource} REST controller.
 */
@SpringBootTest(classes = IftmisApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class FindingResourceIT {
    private static final FindingSource DEFAULT_SOURCE = FindingSource.CAIG;
    private static final FindingSource UPDATED_SOURCE = FindingSource.PPRA;

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final ActionPlanCategory DEFAULT_ACTION_PLAN_CATEGORY = ActionPlanCategory.LOW;
    private static final ActionPlanCategory UPDATED_ACTION_PLAN_CATEGORY = ActionPlanCategory.MEDIUM;

    private static final Boolean DEFAULT_IS_CLOSED = false;
    private static final Boolean UPDATED_IS_CLOSED = true;

    @Autowired
    private FindingRepository findingRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restFindingMockMvc;

    private Finding finding;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Finding createEntity(EntityManager em) {
        Finding finding = new Finding()
            .source(DEFAULT_SOURCE)
            .code(DEFAULT_CODE)
            .description(DEFAULT_DESCRIPTION)
            .actionPlanCategory(DEFAULT_ACTION_PLAN_CATEGORY)
            .isClosed(DEFAULT_IS_CLOSED);
        // Add required entity
        OrganisationUnit organisationUnit;
        if (TestUtil.findAll(em, OrganisationUnit.class).isEmpty()) {
            organisationUnit = OrganisationUnitResourceIT.createEntity(em);
            em.persist(organisationUnit);
            em.flush();
        } else {
            organisationUnit = TestUtil.findAll(em, OrganisationUnit.class).get(0);
        }
        finding.setOrganisationUnit(organisationUnit);
        return finding;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Finding createUpdatedEntity(EntityManager em) {
        Finding finding = new Finding()
            .source(UPDATED_SOURCE)
            .code(UPDATED_CODE)
            .description(UPDATED_DESCRIPTION)
            .actionPlanCategory(UPDATED_ACTION_PLAN_CATEGORY)
            .isClosed(UPDATED_IS_CLOSED);
        // Add required entity
        OrganisationUnit organisationUnit;
        if (TestUtil.findAll(em, OrganisationUnit.class).isEmpty()) {
            organisationUnit = OrganisationUnitResourceIT.createUpdatedEntity(em);
            em.persist(organisationUnit);
            em.flush();
        } else {
            organisationUnit = TestUtil.findAll(em, OrganisationUnit.class).get(0);
        }
        finding.setOrganisationUnit(organisationUnit);
        return finding;
    }

    @BeforeEach
    public void initTest() {
        finding = createEntity(em);
    }

    @Test
    @Transactional
    public void createFinding() throws Exception {
        int databaseSizeBeforeCreate = findingRepository.findAll().size();
        // Create the Finding
        restFindingMockMvc
            .perform(
                post("/api/findings")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(finding))
            )
            .andExpect(status().isCreated());

        // Validate the Finding in the database
        List<Finding> findingList = findingRepository.findAll();
        assertThat(findingList).hasSize(databaseSizeBeforeCreate + 1);
        Finding testFinding = findingList.get(findingList.size() - 1);
        assertThat(testFinding.getSource()).isEqualTo(DEFAULT_SOURCE);
        assertThat(testFinding.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testFinding.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testFinding.getActionPlanCategory()).isEqualTo(DEFAULT_ACTION_PLAN_CATEGORY);
        assertThat(testFinding.isIsClosed()).isEqualTo(DEFAULT_IS_CLOSED);
    }

    @Test
    @Transactional
    public void createFindingWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = findingRepository.findAll().size();

        // Create the Finding with an existing ID
        finding.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restFindingMockMvc
            .perform(
                post("/api/findings")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(finding))
            )
            .andExpect(status().isBadRequest());

        // Validate the Finding in the database
        List<Finding> findingList = findingRepository.findAll();
        assertThat(findingList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkSourceIsRequired() throws Exception {
        int databaseSizeBeforeTest = findingRepository.findAll().size();
        // set the field null
        finding.setSource(null);

        // Create the Finding, which fails.

        restFindingMockMvc
            .perform(
                post("/api/findings")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(finding))
            )
            .andExpect(status().isBadRequest());

        List<Finding> findingList = findingRepository.findAll();
        assertThat(findingList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkActionPlanCategoryIsRequired() throws Exception {
        int databaseSizeBeforeTest = findingRepository.findAll().size();
        // set the field null
        finding.setActionPlanCategory(null);

        // Create the Finding, which fails.

        restFindingMockMvc
            .perform(
                post("/api/findings")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(finding))
            )
            .andExpect(status().isBadRequest());

        List<Finding> findingList = findingRepository.findAll();
        assertThat(findingList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllFindings() throws Exception {
        // Initialize the database
        findingRepository.saveAndFlush(finding);

        // Get all the findingList
        restFindingMockMvc
            .perform(get("/api/findings?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(finding.getId().intValue())))
            .andExpect(jsonPath("$.[*].source").value(hasItem(DEFAULT_SOURCE.toString())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].actionPlanCategory").value(hasItem(DEFAULT_ACTION_PLAN_CATEGORY.toString())))
            .andExpect(jsonPath("$.[*].isClosed").value(hasItem(DEFAULT_IS_CLOSED.booleanValue())));
    }

    @Test
    @Transactional
    public void getFinding() throws Exception {
        // Initialize the database
        findingRepository.saveAndFlush(finding);

        // Get the finding
        restFindingMockMvc
            .perform(get("/api/findings/{id}", finding.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(finding.getId().intValue()))
            .andExpect(jsonPath("$.source").value(DEFAULT_SOURCE.toString()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.actionPlanCategory").value(DEFAULT_ACTION_PLAN_CATEGORY.toString()))
            .andExpect(jsonPath("$.isClosed").value(DEFAULT_IS_CLOSED.booleanValue()));
    }

    @Test
    @Transactional
    public void getNonExistingFinding() throws Exception {
        // Get the finding
        restFindingMockMvc.perform(get("/api/findings/{id}", Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateFinding() throws Exception {
        // Initialize the database
        findingRepository.saveAndFlush(finding);

        int databaseSizeBeforeUpdate = findingRepository.findAll().size();

        // Update the finding
        Finding updatedFinding = findingRepository.findById(finding.getId()).get();
        // Disconnect from session so that the updates on updatedFinding are not directly saved in db
        em.detach(updatedFinding);
        updatedFinding
            .source(UPDATED_SOURCE)
            .code(UPDATED_CODE)
            .description(UPDATED_DESCRIPTION)
            .actionPlanCategory(UPDATED_ACTION_PLAN_CATEGORY)
            .isClosed(UPDATED_IS_CLOSED);

        restFindingMockMvc
            .perform(
                put("/api/findings")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedFinding))
            )
            .andExpect(status().isOk());

        // Validate the Finding in the database
        List<Finding> findingList = findingRepository.findAll();
        assertThat(findingList).hasSize(databaseSizeBeforeUpdate);
        Finding testFinding = findingList.get(findingList.size() - 1);
        assertThat(testFinding.getSource()).isEqualTo(UPDATED_SOURCE);
        assertThat(testFinding.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testFinding.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testFinding.getActionPlanCategory()).isEqualTo(UPDATED_ACTION_PLAN_CATEGORY);
        assertThat(testFinding.isIsClosed()).isEqualTo(UPDATED_IS_CLOSED);
    }

    @Test
    @Transactional
    public void updateNonExistingFinding() throws Exception {
        int databaseSizeBeforeUpdate = findingRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFindingMockMvc
            .perform(
                put("/api/findings")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(finding))
            )
            .andExpect(status().isBadRequest());

        // Validate the Finding in the database
        List<Finding> findingList = findingRepository.findAll();
        assertThat(findingList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteFinding() throws Exception {
        // Initialize the database
        findingRepository.saveAndFlush(finding);

        int databaseSizeBeforeDelete = findingRepository.findAll().size();

        // Delete the finding
        restFindingMockMvc
            .perform(delete("/api/findings/{id}", finding.getId()).with(csrf()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Finding> findingList = findingRepository.findAll();
        assertThat(findingList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
