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
import org.tamisemi.iftmis.domain.Inspection;
import org.tamisemi.iftmis.domain.InspectionMember;
import org.tamisemi.iftmis.domain.User;
import org.tamisemi.iftmis.domain.enumeration.InspectionRole;
import org.tamisemi.iftmis.repository.InspectionMemberRepository;
import org.tamisemi.iftmis.service.InspectionMemberService;
import org.tamisemi.iftmis.service.dto.InspectionMemberDTO;
import org.tamisemi.iftmis.service.mapper.InspectionMemberMapper;

/**
 * Integration tests for the {@link InspectionMemberResource} REST controller.
 */
@SpringBootTest(classes = IftmisApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class InspectionMemberResourceIT {
    private static final InspectionRole DEFAULT_ROLE = InspectionRole.TEAM_LEAD;
    private static final InspectionRole UPDATED_ROLE = InspectionRole.MEMBER;

    @Autowired
    private InspectionMemberRepository inspectionMemberRepository;

    @Autowired
    private InspectionMemberMapper inspectionMemberMapper;

    @Autowired
    private InspectionMemberService inspectionMemberService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restInspectionMemberMockMvc;

    private InspectionMember inspectionMember;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static InspectionMember createEntity(EntityManager em) {
        InspectionMember inspectionMember = new InspectionMember().role(DEFAULT_ROLE);
        // Add required entity
        User user = UserResourceIT.createEntity(em);
        em.persist(user);
        em.flush();
        inspectionMember.setUser(user);
        // Add required entity
        Inspection inspection;
        if (TestUtil.findAll(em, Inspection.class).isEmpty()) {
            inspection = InspectionResourceIT.createEntity(em);
            em.persist(inspection);
            em.flush();
        } else {
            inspection = TestUtil.findAll(em, Inspection.class).get(0);
        }
        inspectionMember.setInspection(inspection);
        return inspectionMember;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static InspectionMember createUpdatedEntity(EntityManager em) {
        InspectionMember inspectionMember = new InspectionMember().role(UPDATED_ROLE);
        // Add required entity
        User user = UserResourceIT.createEntity(em);
        em.persist(user);
        em.flush();
        inspectionMember.setUser(user);
        // Add required entity
        Inspection inspection;
        if (TestUtil.findAll(em, Inspection.class).isEmpty()) {
            inspection = InspectionResourceIT.createUpdatedEntity(em);
            em.persist(inspection);
            em.flush();
        } else {
            inspection = TestUtil.findAll(em, Inspection.class).get(0);
        }
        inspectionMember.setInspection(inspection);
        return inspectionMember;
    }

    @BeforeEach
    public void initTest() {
        inspectionMember = createEntity(em);
    }

    @Test
    @Transactional
    public void createInspectionMember() throws Exception {
        int databaseSizeBeforeCreate = inspectionMemberRepository.findAll().size();
        // Create the InspectionMember
        InspectionMemberDTO inspectionMemberDTO = inspectionMemberMapper.toDto(inspectionMember);
        restInspectionMemberMockMvc
            .perform(
                post("/api/inspection-members")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(inspectionMemberDTO))
            )
            .andExpect(status().isCreated());

        // Validate the InspectionMember in the database
        List<InspectionMember> inspectionMemberList = inspectionMemberRepository.findAll();
        assertThat(inspectionMemberList).hasSize(databaseSizeBeforeCreate + 1);
        InspectionMember testInspectionMember = inspectionMemberList.get(inspectionMemberList.size() - 1);
        assertThat(testInspectionMember.getRole()).isEqualTo(DEFAULT_ROLE);
    }

    @Test
    @Transactional
    public void createInspectionMemberWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = inspectionMemberRepository.findAll().size();

        // Create the InspectionMember with an existing ID
        inspectionMember.setId(1L);
        InspectionMemberDTO inspectionMemberDTO = inspectionMemberMapper.toDto(inspectionMember);

        // An entity with an existing ID cannot be created, so this API call must fail
        restInspectionMemberMockMvc
            .perform(
                post("/api/inspection-members")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(inspectionMemberDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the InspectionMember in the database
        List<InspectionMember> inspectionMemberList = inspectionMemberRepository.findAll();
        assertThat(inspectionMemberList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkRoleIsRequired() throws Exception {
        int databaseSizeBeforeTest = inspectionMemberRepository.findAll().size();
        // set the field null
        inspectionMember.setRole(null);

        // Create the InspectionMember, which fails.
        InspectionMemberDTO inspectionMemberDTO = inspectionMemberMapper.toDto(inspectionMember);

        restInspectionMemberMockMvc
            .perform(
                post("/api/inspection-members")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(inspectionMemberDTO))
            )
            .andExpect(status().isBadRequest());

        List<InspectionMember> inspectionMemberList = inspectionMemberRepository.findAll();
        assertThat(inspectionMemberList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllInspectionMembers() throws Exception {
        // Initialize the database
        inspectionMemberRepository.saveAndFlush(inspectionMember);

        // Get all the inspectionMemberList
        restInspectionMemberMockMvc
            .perform(get("/api/inspection-members?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(inspectionMember.getId().intValue())))
            .andExpect(jsonPath("$.[*].role").value(hasItem(DEFAULT_ROLE.toString())));
    }

    @Test
    @Transactional
    public void getInspectionMember() throws Exception {
        // Initialize the database
        inspectionMemberRepository.saveAndFlush(inspectionMember);

        // Get the inspectionMember
        restInspectionMemberMockMvc
            .perform(get("/api/inspection-members/{id}", inspectionMember.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(inspectionMember.getId().intValue()))
            .andExpect(jsonPath("$.role").value(DEFAULT_ROLE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingInspectionMember() throws Exception {
        // Get the inspectionMember
        restInspectionMemberMockMvc.perform(get("/api/inspection-members/{id}", Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateInspectionMember() throws Exception {
        // Initialize the database
        inspectionMemberRepository.saveAndFlush(inspectionMember);

        int databaseSizeBeforeUpdate = inspectionMemberRepository.findAll().size();

        // Update the inspectionMember
        InspectionMember updatedInspectionMember = inspectionMemberRepository.findById(inspectionMember.getId()).get();
        // Disconnect from session so that the updates on updatedInspectionMember are not directly saved in db
        em.detach(updatedInspectionMember);
        updatedInspectionMember.role(UPDATED_ROLE);
        InspectionMemberDTO inspectionMemberDTO = inspectionMemberMapper.toDto(updatedInspectionMember);

        restInspectionMemberMockMvc
            .perform(
                put("/api/inspection-members")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(inspectionMemberDTO))
            )
            .andExpect(status().isOk());

        // Validate the InspectionMember in the database
        List<InspectionMember> inspectionMemberList = inspectionMemberRepository.findAll();
        assertThat(inspectionMemberList).hasSize(databaseSizeBeforeUpdate);
        InspectionMember testInspectionMember = inspectionMemberList.get(inspectionMemberList.size() - 1);
        assertThat(testInspectionMember.getRole()).isEqualTo(UPDATED_ROLE);
    }

    @Test
    @Transactional
    public void updateNonExistingInspectionMember() throws Exception {
        int databaseSizeBeforeUpdate = inspectionMemberRepository.findAll().size();

        // Create the InspectionMember
        InspectionMemberDTO inspectionMemberDTO = inspectionMemberMapper.toDto(inspectionMember);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restInspectionMemberMockMvc
            .perform(
                put("/api/inspection-members")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(inspectionMemberDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the InspectionMember in the database
        List<InspectionMember> inspectionMemberList = inspectionMemberRepository.findAll();
        assertThat(inspectionMemberList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteInspectionMember() throws Exception {
        // Initialize the database
        inspectionMemberRepository.saveAndFlush(inspectionMember);

        int databaseSizeBeforeDelete = inspectionMemberRepository.findAll().size();

        // Delete the inspectionMember
        restInspectionMemberMockMvc
            .perform(delete("/api/inspection-members/{id}", inspectionMember.getId()).with(csrf()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<InspectionMember> inspectionMemberList = inspectionMemberRepository.findAll();
        assertThat(inspectionMemberList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
