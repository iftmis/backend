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
import org.tamisemi.iftmis.domain.FindingCategory;
import org.tamisemi.iftmis.domain.FindingSubCategory;
import org.tamisemi.iftmis.domain.InspectionFinding;
import org.tamisemi.iftmis.domain.InspectionWorkDone;
import org.tamisemi.iftmis.domain.enumeration.ActionPlanCategory;
import org.tamisemi.iftmis.repository.InspectionFindingRepository;
import org.tamisemi.iftmis.service.InspectionFindingService;
import org.tamisemi.iftmis.service.dto.InspectionFindingDTO;
import org.tamisemi.iftmis.service.mapper.InspectionFindingMapper;

/**
 * Integration tests for the {@link InspectionFindingResource} REST controller.
 */
@SpringBootTest(classes = IftmisApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class InspectionFindingResourceIT {
    private static final String DEFAULT_CODE = "AAAAA";
    private static final String UPDATED_CODE = "BBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_CONDITION = "AAAAAAAAAA";
    private static final String UPDATED_CONDITION = "BBBBBBBBBB";

    private static final Boolean DEFAULT_DISCLOSED_LAST_INSPECTION = false;
    private static final Boolean UPDATED_DISCLOSED_LAST_INSPECTION = true;

    private static final String DEFAULT_CAUSES = "AAAAAAAAAA";
    private static final String UPDATED_CAUSES = "BBBBBBBBBB";

    private static final ActionPlanCategory DEFAULT_ACTION_PLAN_CATEGORY = ActionPlanCategory.LOW;
    private static final ActionPlanCategory UPDATED_ACTION_PLAN_CATEGORY = ActionPlanCategory.MEDIUM;

    private static final String DEFAULT_IMPLICATION = "AAAAAAAAAA";
    private static final String UPDATED_IMPLICATION = "BBBBBBBBBB";

    private static final Boolean DEFAULT_IS_CLOSED = false;
    private static final Boolean UPDATED_IS_CLOSED = true;

    @Autowired
    private InspectionFindingRepository inspectionFindingRepository;

    @Autowired
    private InspectionFindingMapper inspectionFindingMapper;

    @Autowired
    private InspectionFindingService inspectionFindingService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restInspectionFindingMockMvc;

    private InspectionFinding inspectionFinding;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static InspectionFinding createEntity(EntityManager em) {
        InspectionFinding inspectionFinding = new InspectionFinding()
            .code(DEFAULT_CODE)
            .description(DEFAULT_DESCRIPTION)
            .condition(DEFAULT_CONDITION)
            .disclosedLastInspection(DEFAULT_DISCLOSED_LAST_INSPECTION)
            .causes(DEFAULT_CAUSES)
            .actionPlanCategory(DEFAULT_ACTION_PLAN_CATEGORY)
            .implication(DEFAULT_IMPLICATION)
            .isClosed(DEFAULT_IS_CLOSED);
        // Add required entity
        InspectionWorkDone inspectionWorkDone;
        if (TestUtil.findAll(em, InspectionWorkDone.class).isEmpty()) {
            inspectionWorkDone = InspectionWorkDoneResourceIT.createEntity(em);
            em.persist(inspectionWorkDone);
            em.flush();
        } else {
            inspectionWorkDone = TestUtil.findAll(em, InspectionWorkDone.class).get(0);
        }
        inspectionFinding.setWorkDone(inspectionWorkDone);
        // Add required entity
        FindingCategory findingCategory;
        if (TestUtil.findAll(em, FindingCategory.class).isEmpty()) {
            findingCategory = FindingCategoryResourceIT.createEntity(em);
            em.persist(findingCategory);
            em.flush();
        } else {
            findingCategory = TestUtil.findAll(em, FindingCategory.class).get(0);
        }
        inspectionFinding.setCategory(findingCategory);
        // Add required entity
        FindingSubCategory findingSubCategory;
        if (TestUtil.findAll(em, FindingSubCategory.class).isEmpty()) {
            findingSubCategory = FindingSubCategoryResourceIT.createEntity(em);
            em.persist(findingSubCategory);
            em.flush();
        } else {
            findingSubCategory = TestUtil.findAll(em, FindingSubCategory.class).get(0);
        }
        inspectionFinding.setSubCategory(findingSubCategory);
        return inspectionFinding;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static InspectionFinding createUpdatedEntity(EntityManager em) {
        InspectionFinding inspectionFinding = new InspectionFinding()
            .code(UPDATED_CODE)
            .description(UPDATED_DESCRIPTION)
            .condition(UPDATED_CONDITION)
            .disclosedLastInspection(UPDATED_DISCLOSED_LAST_INSPECTION)
            .causes(UPDATED_CAUSES)
            .actionPlanCategory(UPDATED_ACTION_PLAN_CATEGORY)
            .implication(UPDATED_IMPLICATION)
            .isClosed(UPDATED_IS_CLOSED);
        // Add required entity
        InspectionWorkDone inspectionWorkDone;
        if (TestUtil.findAll(em, InspectionWorkDone.class).isEmpty()) {
            inspectionWorkDone = InspectionWorkDoneResourceIT.createUpdatedEntity(em);
            em.persist(inspectionWorkDone);
            em.flush();
        } else {
            inspectionWorkDone = TestUtil.findAll(em, InspectionWorkDone.class).get(0);
        }
        inspectionFinding.setWorkDone(inspectionWorkDone);
        // Add required entity
        FindingCategory findingCategory;
        if (TestUtil.findAll(em, FindingCategory.class).isEmpty()) {
            findingCategory = FindingCategoryResourceIT.createUpdatedEntity(em);
            em.persist(findingCategory);
            em.flush();
        } else {
            findingCategory = TestUtil.findAll(em, FindingCategory.class).get(0);
        }
        inspectionFinding.setCategory(findingCategory);
        // Add required entity
        FindingSubCategory findingSubCategory;
        if (TestUtil.findAll(em, FindingSubCategory.class).isEmpty()) {
            findingSubCategory = FindingSubCategoryResourceIT.createUpdatedEntity(em);
            em.persist(findingSubCategory);
            em.flush();
        } else {
            findingSubCategory = TestUtil.findAll(em, FindingSubCategory.class).get(0);
        }
        inspectionFinding.setSubCategory(findingSubCategory);
        return inspectionFinding;
    }

    @BeforeEach
    public void initTest() {
        inspectionFinding = createEntity(em);
    }

    @Test
    @Transactional
    public void createInspectionFinding() throws Exception {
        int databaseSizeBeforeCreate = inspectionFindingRepository.findAll().size();
        // Create the InspectionFinding
        InspectionFindingDTO inspectionFindingDTO = inspectionFindingMapper.toDto(inspectionFinding);
        restInspectionFindingMockMvc
            .perform(
                post("/api/inspection-findings")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(inspectionFindingDTO))
            )
            .andExpect(status().isCreated());

        // Validate the InspectionFinding in the database
        List<InspectionFinding> inspectionFindingList = inspectionFindingRepository.findAll();
        assertThat(inspectionFindingList).hasSize(databaseSizeBeforeCreate + 1);
        InspectionFinding testInspectionFinding = inspectionFindingList.get(inspectionFindingList.size() - 1);
        assertThat(testInspectionFinding.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testInspectionFinding.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testInspectionFinding.getCondition()).isEqualTo(DEFAULT_CONDITION);
        assertThat(testInspectionFinding.isDisclosedLastInspection()).isEqualTo(DEFAULT_DISCLOSED_LAST_INSPECTION);
        assertThat(testInspectionFinding.getCauses()).isEqualTo(DEFAULT_CAUSES);
        assertThat(testInspectionFinding.getActionPlanCategory()).isEqualTo(DEFAULT_ACTION_PLAN_CATEGORY);
        assertThat(testInspectionFinding.getImplication()).isEqualTo(DEFAULT_IMPLICATION);
        assertThat(testInspectionFinding.isIsClosed()).isEqualTo(DEFAULT_IS_CLOSED);
    }

    @Test
    @Transactional
    public void createInspectionFindingWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = inspectionFindingRepository.findAll().size();

        // Create the InspectionFinding with an existing ID
        inspectionFinding.setId(1L);
        InspectionFindingDTO inspectionFindingDTO = inspectionFindingMapper.toDto(inspectionFinding);

        // An entity with an existing ID cannot be created, so this API call must fail
        restInspectionFindingMockMvc
            .perform(
                post("/api/inspection-findings")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(inspectionFindingDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the InspectionFinding in the database
        List<InspectionFinding> inspectionFindingList = inspectionFindingRepository.findAll();
        assertThat(inspectionFindingList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = inspectionFindingRepository.findAll().size();
        // set the field null
        inspectionFinding.setCode(null);

        // Create the InspectionFinding, which fails.
        InspectionFindingDTO inspectionFindingDTO = inspectionFindingMapper.toDto(inspectionFinding);

        restInspectionFindingMockMvc
            .perform(
                post("/api/inspection-findings")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(inspectionFindingDTO))
            )
            .andExpect(status().isBadRequest());

        List<InspectionFinding> inspectionFindingList = inspectionFindingRepository.findAll();
        assertThat(inspectionFindingList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllInspectionFindings() throws Exception {
        // Initialize the database
        inspectionFindingRepository.saveAndFlush(inspectionFinding);

        // Get all the inspectionFindingList
        restInspectionFindingMockMvc
            .perform(get("/api/inspection-findings?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(inspectionFinding.getId().intValue())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].condition").value(hasItem(DEFAULT_CONDITION)))
            .andExpect(jsonPath("$.[*].disclosedLastInspection").value(hasItem(DEFAULT_DISCLOSED_LAST_INSPECTION.booleanValue())))
            .andExpect(jsonPath("$.[*].causes").value(hasItem(DEFAULT_CAUSES.toString())))
            .andExpect(jsonPath("$.[*].actionPlanCategory").value(hasItem(DEFAULT_ACTION_PLAN_CATEGORY.toString())))
            .andExpect(jsonPath("$.[*].implication").value(hasItem(DEFAULT_IMPLICATION.toString())))
            .andExpect(jsonPath("$.[*].isClosed").value(hasItem(DEFAULT_IS_CLOSED.booleanValue())));
    }

    @Test
    @Transactional
    public void getInspectionFinding() throws Exception {
        // Initialize the database
        inspectionFindingRepository.saveAndFlush(inspectionFinding);

        // Get the inspectionFinding
        restInspectionFindingMockMvc
            .perform(get("/api/inspection-findings/{id}", inspectionFinding.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(inspectionFinding.getId().intValue()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.condition").value(DEFAULT_CONDITION))
            .andExpect(jsonPath("$.disclosedLastInspection").value(DEFAULT_DISCLOSED_LAST_INSPECTION.booleanValue()))
            .andExpect(jsonPath("$.causes").value(DEFAULT_CAUSES.toString()))
            .andExpect(jsonPath("$.actionPlanCategory").value(DEFAULT_ACTION_PLAN_CATEGORY.toString()))
            .andExpect(jsonPath("$.implication").value(DEFAULT_IMPLICATION.toString()))
            .andExpect(jsonPath("$.isClosed").value(DEFAULT_IS_CLOSED.booleanValue()));
    }

    @Test
    @Transactional
    public void getNonExistingInspectionFinding() throws Exception {
        // Get the inspectionFinding
        restInspectionFindingMockMvc.perform(get("/api/inspection-findings/{id}", Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateInspectionFinding() throws Exception {
        // Initialize the database
        inspectionFindingRepository.saveAndFlush(inspectionFinding);

        int databaseSizeBeforeUpdate = inspectionFindingRepository.findAll().size();

        // Update the inspectionFinding
        InspectionFinding updatedInspectionFinding = inspectionFindingRepository.findById(inspectionFinding.getId()).get();
        // Disconnect from session so that the updates on updatedInspectionFinding are not directly saved in db
        em.detach(updatedInspectionFinding);
        updatedInspectionFinding
            .code(UPDATED_CODE)
            .description(UPDATED_DESCRIPTION)
            .condition(UPDATED_CONDITION)
            .disclosedLastInspection(UPDATED_DISCLOSED_LAST_INSPECTION)
            .causes(UPDATED_CAUSES)
            .actionPlanCategory(UPDATED_ACTION_PLAN_CATEGORY)
            .implication(UPDATED_IMPLICATION)
            .isClosed(UPDATED_IS_CLOSED);
        InspectionFindingDTO inspectionFindingDTO = inspectionFindingMapper.toDto(updatedInspectionFinding);

        restInspectionFindingMockMvc
            .perform(
                put("/api/inspection-findings")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(inspectionFindingDTO))
            )
            .andExpect(status().isOk());

        // Validate the InspectionFinding in the database
        List<InspectionFinding> inspectionFindingList = inspectionFindingRepository.findAll();
        assertThat(inspectionFindingList).hasSize(databaseSizeBeforeUpdate);
        InspectionFinding testInspectionFinding = inspectionFindingList.get(inspectionFindingList.size() - 1);
        assertThat(testInspectionFinding.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testInspectionFinding.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testInspectionFinding.getCondition()).isEqualTo(UPDATED_CONDITION);
        assertThat(testInspectionFinding.isDisclosedLastInspection()).isEqualTo(UPDATED_DISCLOSED_LAST_INSPECTION);
        assertThat(testInspectionFinding.getCauses()).isEqualTo(UPDATED_CAUSES);
        assertThat(testInspectionFinding.getActionPlanCategory()).isEqualTo(UPDATED_ACTION_PLAN_CATEGORY);
        assertThat(testInspectionFinding.getImplication()).isEqualTo(UPDATED_IMPLICATION);
        assertThat(testInspectionFinding.isIsClosed()).isEqualTo(UPDATED_IS_CLOSED);
    }

    @Test
    @Transactional
    public void updateNonExistingInspectionFinding() throws Exception {
        int databaseSizeBeforeUpdate = inspectionFindingRepository.findAll().size();

        // Create the InspectionFinding
        InspectionFindingDTO inspectionFindingDTO = inspectionFindingMapper.toDto(inspectionFinding);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restInspectionFindingMockMvc
            .perform(
                put("/api/inspection-findings")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(inspectionFindingDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the InspectionFinding in the database
        List<InspectionFinding> inspectionFindingList = inspectionFindingRepository.findAll();
        assertThat(inspectionFindingList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteInspectionFinding() throws Exception {
        // Initialize the database
        inspectionFindingRepository.saveAndFlush(inspectionFinding);

        int databaseSizeBeforeDelete = inspectionFindingRepository.findAll().size();

        // Delete the inspectionFinding
        restInspectionFindingMockMvc
            .perform(delete("/api/inspection-findings/{id}", inspectionFinding.getId()).with(csrf()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<InspectionFinding> inspectionFindingList = inspectionFindingRepository.findAll();
        assertThat(inspectionFindingList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
