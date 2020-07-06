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
import org.tamisemi.iftmis.domain.Meeting;
import org.tamisemi.iftmis.domain.MeetingMember;
import org.tamisemi.iftmis.repository.MeetingMemberRepository;
import org.tamisemi.iftmis.service.MeetingMemberService;
import org.tamisemi.iftmis.service.dto.MeetingMemberDTO;
import org.tamisemi.iftmis.service.mapper.MeetingMemberMapper;

/**
 * Integration tests for the {@link MeetingMemberResource} REST controller.
 */
@SpringBootTest(classes = IftmisApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class MeetingMemberResourceIT {
    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_PHONE_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_PHONE_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_TITLE = "AAAAAAAAAA";
    private static final String UPDATED_TITLE = "BBBBBBBBBB";

    @Autowired
    private MeetingMemberRepository meetingMemberRepository;

    @Autowired
    private MeetingMemberMapper meetingMemberMapper;

    @Autowired
    private MeetingMemberService meetingMemberService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restMeetingMemberMockMvc;

    private MeetingMember meetingMember;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MeetingMember createEntity(EntityManager em) {
        MeetingMember meetingMember = new MeetingMember()
            .name(DEFAULT_NAME)
            .phoneNumber(DEFAULT_PHONE_NUMBER)
            .email(DEFAULT_EMAIL)
            .title(DEFAULT_TITLE);
        // Add required entity
        Meeting meeting;
        if (TestUtil.findAll(em, Meeting.class).isEmpty()) {
            meeting = MeetingResourceIT.createEntity(em);
            em.persist(meeting);
            em.flush();
        } else {
            meeting = TestUtil.findAll(em, Meeting.class).get(0);
        }
        meetingMember.setMeeting(meeting);
        return meetingMember;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MeetingMember createUpdatedEntity(EntityManager em) {
        MeetingMember meetingMember = new MeetingMember()
            .name(UPDATED_NAME)
            .phoneNumber(UPDATED_PHONE_NUMBER)
            .email(UPDATED_EMAIL)
            .title(UPDATED_TITLE);
        // Add required entity
        Meeting meeting;
        if (TestUtil.findAll(em, Meeting.class).isEmpty()) {
            meeting = MeetingResourceIT.createUpdatedEntity(em);
            em.persist(meeting);
            em.flush();
        } else {
            meeting = TestUtil.findAll(em, Meeting.class).get(0);
        }
        meetingMember.setMeeting(meeting);
        return meetingMember;
    }

    @BeforeEach
    public void initTest() {
        meetingMember = createEntity(em);
    }

    @Test
    @Transactional
    public void createMeetingMember() throws Exception {
        int databaseSizeBeforeCreate = meetingMemberRepository.findAll().size();
        // Create the MeetingMember
        MeetingMemberDTO meetingMemberDTO = meetingMemberMapper.toDto(meetingMember);
        restMeetingMemberMockMvc
            .perform(
                post("/api/meeting-members")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(meetingMemberDTO))
            )
            .andExpect(status().isCreated());

        // Validate the MeetingMember in the database
        List<MeetingMember> meetingMemberList = meetingMemberRepository.findAll();
        assertThat(meetingMemberList).hasSize(databaseSizeBeforeCreate + 1);
        MeetingMember testMeetingMember = meetingMemberList.get(meetingMemberList.size() - 1);
        assertThat(testMeetingMember.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testMeetingMember.getPhoneNumber()).isEqualTo(DEFAULT_PHONE_NUMBER);
        assertThat(testMeetingMember.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testMeetingMember.getTitle()).isEqualTo(DEFAULT_TITLE);
    }

    @Test
    @Transactional
    public void createMeetingMemberWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = meetingMemberRepository.findAll().size();

        // Create the MeetingMember with an existing ID
        meetingMember.setId(1L);
        MeetingMemberDTO meetingMemberDTO = meetingMemberMapper.toDto(meetingMember);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMeetingMemberMockMvc
            .perform(
                post("/api/meeting-members")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(meetingMemberDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the MeetingMember in the database
        List<MeetingMember> meetingMemberList = meetingMemberRepository.findAll();
        assertThat(meetingMemberList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = meetingMemberRepository.findAll().size();
        // set the field null
        meetingMember.setName(null);

        // Create the MeetingMember, which fails.
        MeetingMemberDTO meetingMemberDTO = meetingMemberMapper.toDto(meetingMember);

        restMeetingMemberMockMvc
            .perform(
                post("/api/meeting-members")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(meetingMemberDTO))
            )
            .andExpect(status().isBadRequest());

        List<MeetingMember> meetingMemberList = meetingMemberRepository.findAll();
        assertThat(meetingMemberList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMeetingMembers() throws Exception {
        // Initialize the database
        meetingMemberRepository.saveAndFlush(meetingMember);

        // Get all the meetingMemberList
        restMeetingMemberMockMvc
            .perform(get("/api/meeting-members?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(meetingMember.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].phoneNumber").value(hasItem(DEFAULT_PHONE_NUMBER)))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL)))
            .andExpect(jsonPath("$.[*].title").value(hasItem(DEFAULT_TITLE)));
    }

    @Test
    @Transactional
    public void getMeetingMember() throws Exception {
        // Initialize the database
        meetingMemberRepository.saveAndFlush(meetingMember);

        // Get the meetingMember
        restMeetingMemberMockMvc
            .perform(get("/api/meeting-members/{id}", meetingMember.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(meetingMember.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.phoneNumber").value(DEFAULT_PHONE_NUMBER))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL))
            .andExpect(jsonPath("$.title").value(DEFAULT_TITLE));
    }

    @Test
    @Transactional
    public void getNonExistingMeetingMember() throws Exception {
        // Get the meetingMember
        restMeetingMemberMockMvc.perform(get("/api/meeting-members/{id}", Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMeetingMember() throws Exception {
        // Initialize the database
        meetingMemberRepository.saveAndFlush(meetingMember);

        int databaseSizeBeforeUpdate = meetingMemberRepository.findAll().size();

        // Update the meetingMember
        MeetingMember updatedMeetingMember = meetingMemberRepository.findById(meetingMember.getId()).get();
        // Disconnect from session so that the updates on updatedMeetingMember are not directly saved in db
        em.detach(updatedMeetingMember);
        updatedMeetingMember.name(UPDATED_NAME).phoneNumber(UPDATED_PHONE_NUMBER).email(UPDATED_EMAIL).title(UPDATED_TITLE);
        MeetingMemberDTO meetingMemberDTO = meetingMemberMapper.toDto(updatedMeetingMember);

        restMeetingMemberMockMvc
            .perform(
                put("/api/meeting-members")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(meetingMemberDTO))
            )
            .andExpect(status().isOk());

        // Validate the MeetingMember in the database
        List<MeetingMember> meetingMemberList = meetingMemberRepository.findAll();
        assertThat(meetingMemberList).hasSize(databaseSizeBeforeUpdate);
        MeetingMember testMeetingMember = meetingMemberList.get(meetingMemberList.size() - 1);
        assertThat(testMeetingMember.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testMeetingMember.getPhoneNumber()).isEqualTo(UPDATED_PHONE_NUMBER);
        assertThat(testMeetingMember.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testMeetingMember.getTitle()).isEqualTo(UPDATED_TITLE);
    }

    @Test
    @Transactional
    public void updateNonExistingMeetingMember() throws Exception {
        int databaseSizeBeforeUpdate = meetingMemberRepository.findAll().size();

        // Create the MeetingMember
        MeetingMemberDTO meetingMemberDTO = meetingMemberMapper.toDto(meetingMember);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMeetingMemberMockMvc
            .perform(
                put("/api/meeting-members")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(meetingMemberDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the MeetingMember in the database
        List<MeetingMember> meetingMemberList = meetingMemberRepository.findAll();
        assertThat(meetingMemberList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMeetingMember() throws Exception {
        // Initialize the database
        meetingMemberRepository.saveAndFlush(meetingMember);

        int databaseSizeBeforeDelete = meetingMemberRepository.findAll().size();

        // Delete the meetingMember
        restMeetingMemberMockMvc
            .perform(delete("/api/meeting-members/{id}", meetingMember.getId()).with(csrf()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<MeetingMember> meetingMemberList = meetingMemberRepository.findAll();
        assertThat(meetingMemberList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
