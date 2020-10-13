package org.tamisemi.iftmis.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.LocalDate;
import java.time.ZoneId;
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
import org.tamisemi.iftmis.domain.Meeting;
import org.tamisemi.iftmis.domain.enumeration.MeetingType;
import org.tamisemi.iftmis.repository.MeetingRepository;
import org.tamisemi.iftmis.service.MeetingService;
import org.tamisemi.iftmis.service.dto.MeetingDTO;
import org.tamisemi.iftmis.service.mapper.MeetingMapper;

/**
 * Integration tests for the {@link MeetingResource} REST controller.
 */
@SpringBootTest(classes = IftmisApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class MeetingResourceIT {
    private static final MeetingType DEFAULT_TYPE = MeetingType.TEAM;
    private static final MeetingType UPDATED_TYPE = MeetingType.ENTRANCE;

    private static final LocalDate DEFAULT_MEETING_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_MEETING_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_VENUE = "AAAAAAAAAA";
    private static final String UPDATED_VENUE = "BBBBBBBBBB";

    private static final String DEFAULT_SUMMARY = "AAAAAAAAAA";
    private static final String UPDATED_SUMMARY = "BBBBBBBBBB";

    @Autowired
    private MeetingRepository meetingRepository;

    @Autowired
    private MeetingMapper meetingMapper;

    @Autowired
    private MeetingService meetingService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restMeetingMockMvc;

    private Meeting meeting;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Meeting createEntity(EntityManager em) {
        Meeting meeting = new Meeting().type(DEFAULT_TYPE).meetingDate(DEFAULT_MEETING_DATE).venue(DEFAULT_VENUE).summary(DEFAULT_SUMMARY);
        // Add required entity
        Inspection inspection;
        if (TestUtil.findAll(em, Inspection.class).isEmpty()) {
            inspection = InspectionResourceIT.createEntity(em);
            em.persist(inspection);
            em.flush();
        } else {
            inspection = TestUtil.findAll(em, Inspection.class).get(0);
        }
        meeting.setInspection(inspection);
        return meeting;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Meeting createUpdatedEntity(EntityManager em) {
        Meeting meeting = new Meeting().type(UPDATED_TYPE).meetingDate(UPDATED_MEETING_DATE).venue(UPDATED_VENUE).summary(UPDATED_SUMMARY);
        // Add required entity
        Inspection inspection;
        if (TestUtil.findAll(em, Inspection.class).isEmpty()) {
            inspection = InspectionResourceIT.createUpdatedEntity(em);
            em.persist(inspection);
            em.flush();
        } else {
            inspection = TestUtil.findAll(em, Inspection.class).get(0);
        }
        meeting.setInspection(inspection);
        return meeting;
    }

    @BeforeEach
    public void initTest() {
        meeting = createEntity(em);
    }

    @Test
    @Transactional
    public void createMeeting() throws Exception {
        int databaseSizeBeforeCreate = meetingRepository.findAll().size();
        // Create the Meeting
        MeetingDTO meetingDTO = meetingMapper.toDto(meeting);
        restMeetingMockMvc
            .perform(
                post("/api/meetings")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(meetingDTO))
            )
            .andExpect(status().isCreated());

        // Validate the Meeting in the database
        List<Meeting> meetingList = meetingRepository.findAll();
        assertThat(meetingList).hasSize(databaseSizeBeforeCreate + 1);
        Meeting testMeeting = meetingList.get(meetingList.size() - 1);
        assertThat(testMeeting.getType()).isEqualTo(DEFAULT_TYPE);
        assertThat(testMeeting.getMeetingDate()).isEqualTo(DEFAULT_MEETING_DATE);
        assertThat(testMeeting.getVenue()).isEqualTo(DEFAULT_VENUE);
        assertThat(testMeeting.getSummary()).isEqualTo(DEFAULT_SUMMARY);
    }

    @Test
    @Transactional
    public void createMeetingWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = meetingRepository.findAll().size();

        // Create the Meeting with an existing ID
        meeting.setId(1L);
        MeetingDTO meetingDTO = meetingMapper.toDto(meeting);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMeetingMockMvc
            .perform(
                post("/api/meetings")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(meetingDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Meeting in the database
        List<Meeting> meetingList = meetingRepository.findAll();
        assertThat(meetingList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = meetingRepository.findAll().size();
        // set the field null
        meeting.setType(null);

        // Create the Meeting, which fails.
        MeetingDTO meetingDTO = meetingMapper.toDto(meeting);

        restMeetingMockMvc
            .perform(
                post("/api/meetings")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(meetingDTO))
            )
            .andExpect(status().isBadRequest());

        List<Meeting> meetingList = meetingRepository.findAll();
        assertThat(meetingList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkMeetingDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = meetingRepository.findAll().size();
        // set the field null
        meeting.setMeetingDate(null);

        // Create the Meeting, which fails.
        MeetingDTO meetingDTO = meetingMapper.toDto(meeting);

        restMeetingMockMvc
            .perform(
                post("/api/meetings")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(meetingDTO))
            )
            .andExpect(status().isBadRequest());

        List<Meeting> meetingList = meetingRepository.findAll();
        assertThat(meetingList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkVenueIsRequired() throws Exception {
        int databaseSizeBeforeTest = meetingRepository.findAll().size();
        // set the field null
        meeting.setVenue(null);

        // Create the Meeting, which fails.
        MeetingDTO meetingDTO = meetingMapper.toDto(meeting);

        restMeetingMockMvc
            .perform(
                post("/api/meetings")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(meetingDTO))
            )
            .andExpect(status().isBadRequest());

        List<Meeting> meetingList = meetingRepository.findAll();
        assertThat(meetingList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMeetings() throws Exception {
        // Initialize the database
        meetingRepository.saveAndFlush(meeting);

        // Get all the meetingList
        restMeetingMockMvc
            .perform(get("/api/meetings?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(meeting.getId().intValue())))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE.toString())))
            .andExpect(jsonPath("$.[*].meetingDate").value(hasItem(DEFAULT_MEETING_DATE.toString())))
            .andExpect(jsonPath("$.[*].venue").value(hasItem(DEFAULT_VENUE)))
            .andExpect(jsonPath("$.[*].summary").value(hasItem(DEFAULT_SUMMARY.toString())));
    }

    @Test
    @Transactional
    public void getMeeting() throws Exception {
        // Initialize the database
        meetingRepository.saveAndFlush(meeting);

        // Get the meeting
        restMeetingMockMvc
            .perform(get("/api/meetings/{id}", meeting.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(meeting.getId().intValue()))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE.toString()))
            .andExpect(jsonPath("$.meetingDate").value(DEFAULT_MEETING_DATE.toString()))
            .andExpect(jsonPath("$.venue").value(DEFAULT_VENUE))
            .andExpect(jsonPath("$.summary").value(DEFAULT_SUMMARY.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingMeeting() throws Exception {
        // Get the meeting
        restMeetingMockMvc.perform(get("/api/meetings/{id}", Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMeeting() throws Exception {
        // Initialize the database
        meetingRepository.saveAndFlush(meeting);

        int databaseSizeBeforeUpdate = meetingRepository.findAll().size();

        // Update the meeting
        Meeting updatedMeeting = meetingRepository.findById(meeting.getId()).get();
        // Disconnect from session so that the updates on updatedMeeting are not directly saved in db
        em.detach(updatedMeeting);
        updatedMeeting.type(UPDATED_TYPE).meetingDate(UPDATED_MEETING_DATE).venue(UPDATED_VENUE).summary(UPDATED_SUMMARY);
        MeetingDTO meetingDTO = meetingMapper.toDto(updatedMeeting);

        restMeetingMockMvc
            .perform(
                put("/api/meetings")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(meetingDTO))
            )
            .andExpect(status().isOk());

        // Validate the Meeting in the database
        List<Meeting> meetingList = meetingRepository.findAll();
        assertThat(meetingList).hasSize(databaseSizeBeforeUpdate);
        Meeting testMeeting = meetingList.get(meetingList.size() - 1);
        assertThat(testMeeting.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testMeeting.getMeetingDate()).isEqualTo(UPDATED_MEETING_DATE);
        assertThat(testMeeting.getVenue()).isEqualTo(UPDATED_VENUE);
        assertThat(testMeeting.getSummary()).isEqualTo(UPDATED_SUMMARY);
    }

    @Test
    @Transactional
    public void updateNonExistingMeeting() throws Exception {
        int databaseSizeBeforeUpdate = meetingRepository.findAll().size();

        // Create the Meeting
        MeetingDTO meetingDTO = meetingMapper.toDto(meeting);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMeetingMockMvc
            .perform(
                put("/api/meetings")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(meetingDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Meeting in the database
        List<Meeting> meetingList = meetingRepository.findAll();
        assertThat(meetingList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMeeting() throws Exception {
        // Initialize the database
        meetingRepository.saveAndFlush(meeting);

        int databaseSizeBeforeDelete = meetingRepository.findAll().size();

        // Delete the meeting
        restMeetingMockMvc
            .perform(delete("/api/meetings/{id}", meeting.getId()).with(csrf()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Meeting> meetingList = meetingRepository.findAll();
        assertThat(meetingList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
