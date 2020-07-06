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
import org.tamisemi.iftmis.domain.FileResource;
import org.tamisemi.iftmis.domain.Meeting;
import org.tamisemi.iftmis.domain.MeetingAttachment;
import org.tamisemi.iftmis.repository.MeetingAttachmentRepository;

/**
 * Integration tests for the {@link MeetingAttachmentResource} REST controller.
 */
@SpringBootTest(classes = IftmisApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class MeetingAttachmentResourceIT {
    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    @Autowired
    private MeetingAttachmentRepository meetingAttachmentRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restMeetingAttachmentMockMvc;

    private MeetingAttachment meetingAttachment;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MeetingAttachment createEntity(EntityManager em) {
        MeetingAttachment meetingAttachment = new MeetingAttachment().name(DEFAULT_NAME);
        // Add required entity
        Meeting meeting;
        if (TestUtil.findAll(em, Meeting.class).isEmpty()) {
            meeting = MeetingResourceIT.createEntity(em);
            em.persist(meeting);
            em.flush();
        } else {
            meeting = TestUtil.findAll(em, Meeting.class).get(0);
        }
        meetingAttachment.setMeeting(meeting);
        // Add required entity
        FileResource fileResource;
        if (TestUtil.findAll(em, FileResource.class).isEmpty()) {
            fileResource = FileResourceResourceIT.createEntity(em);
            em.persist(fileResource);
            em.flush();
        } else {
            fileResource = TestUtil.findAll(em, FileResource.class).get(0);
        }
        meetingAttachment.setAttachment(fileResource);
        return meetingAttachment;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MeetingAttachment createUpdatedEntity(EntityManager em) {
        MeetingAttachment meetingAttachment = new MeetingAttachment().name(UPDATED_NAME);
        // Add required entity
        Meeting meeting;
        if (TestUtil.findAll(em, Meeting.class).isEmpty()) {
            meeting = MeetingResourceIT.createUpdatedEntity(em);
            em.persist(meeting);
            em.flush();
        } else {
            meeting = TestUtil.findAll(em, Meeting.class).get(0);
        }
        meetingAttachment.setMeeting(meeting);
        // Add required entity
        FileResource fileResource;
        if (TestUtil.findAll(em, FileResource.class).isEmpty()) {
            fileResource = FileResourceResourceIT.createUpdatedEntity(em);
            em.persist(fileResource);
            em.flush();
        } else {
            fileResource = TestUtil.findAll(em, FileResource.class).get(0);
        }
        meetingAttachment.setAttachment(fileResource);
        return meetingAttachment;
    }

    @BeforeEach
    public void initTest() {
        meetingAttachment = createEntity(em);
    }

    @Test
    @Transactional
    public void createMeetingAttachment() throws Exception {
        int databaseSizeBeforeCreate = meetingAttachmentRepository.findAll().size();
        // Create the MeetingAttachment
        restMeetingAttachmentMockMvc
            .perform(
                post("/api/meeting-attachments")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(meetingAttachment))
            )
            .andExpect(status().isCreated());

        // Validate the MeetingAttachment in the database
        List<MeetingAttachment> meetingAttachmentList = meetingAttachmentRepository.findAll();
        assertThat(meetingAttachmentList).hasSize(databaseSizeBeforeCreate + 1);
        MeetingAttachment testMeetingAttachment = meetingAttachmentList.get(meetingAttachmentList.size() - 1);
        assertThat(testMeetingAttachment.getName()).isEqualTo(DEFAULT_NAME);
    }

    @Test
    @Transactional
    public void createMeetingAttachmentWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = meetingAttachmentRepository.findAll().size();

        // Create the MeetingAttachment with an existing ID
        meetingAttachment.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMeetingAttachmentMockMvc
            .perform(
                post("/api/meeting-attachments")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(meetingAttachment))
            )
            .andExpect(status().isBadRequest());

        // Validate the MeetingAttachment in the database
        List<MeetingAttachment> meetingAttachmentList = meetingAttachmentRepository.findAll();
        assertThat(meetingAttachmentList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = meetingAttachmentRepository.findAll().size();
        // set the field null
        meetingAttachment.setName(null);

        // Create the MeetingAttachment, which fails.

        restMeetingAttachmentMockMvc
            .perform(
                post("/api/meeting-attachments")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(meetingAttachment))
            )
            .andExpect(status().isBadRequest());

        List<MeetingAttachment> meetingAttachmentList = meetingAttachmentRepository.findAll();
        assertThat(meetingAttachmentList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMeetingAttachments() throws Exception {
        // Initialize the database
        meetingAttachmentRepository.saveAndFlush(meetingAttachment);

        // Get all the meetingAttachmentList
        restMeetingAttachmentMockMvc
            .perform(get("/api/meeting-attachments?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(meetingAttachment.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)));
    }

    @Test
    @Transactional
    public void getMeetingAttachment() throws Exception {
        // Initialize the database
        meetingAttachmentRepository.saveAndFlush(meetingAttachment);

        // Get the meetingAttachment
        restMeetingAttachmentMockMvc
            .perform(get("/api/meeting-attachments/{id}", meetingAttachment.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(meetingAttachment.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME));
    }

    @Test
    @Transactional
    public void getNonExistingMeetingAttachment() throws Exception {
        // Get the meetingAttachment
        restMeetingAttachmentMockMvc.perform(get("/api/meeting-attachments/{id}", Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMeetingAttachment() throws Exception {
        // Initialize the database
        meetingAttachmentRepository.saveAndFlush(meetingAttachment);

        int databaseSizeBeforeUpdate = meetingAttachmentRepository.findAll().size();

        // Update the meetingAttachment
        MeetingAttachment updatedMeetingAttachment = meetingAttachmentRepository.findById(meetingAttachment.getId()).get();
        // Disconnect from session so that the updates on updatedMeetingAttachment are not directly saved in db
        em.detach(updatedMeetingAttachment);
        updatedMeetingAttachment.name(UPDATED_NAME);

        restMeetingAttachmentMockMvc
            .perform(
                put("/api/meeting-attachments")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedMeetingAttachment))
            )
            .andExpect(status().isOk());

        // Validate the MeetingAttachment in the database
        List<MeetingAttachment> meetingAttachmentList = meetingAttachmentRepository.findAll();
        assertThat(meetingAttachmentList).hasSize(databaseSizeBeforeUpdate);
        MeetingAttachment testMeetingAttachment = meetingAttachmentList.get(meetingAttachmentList.size() - 1);
        assertThat(testMeetingAttachment.getName()).isEqualTo(UPDATED_NAME);
    }

    @Test
    @Transactional
    public void updateNonExistingMeetingAttachment() throws Exception {
        int databaseSizeBeforeUpdate = meetingAttachmentRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMeetingAttachmentMockMvc
            .perform(
                put("/api/meeting-attachments")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(meetingAttachment))
            )
            .andExpect(status().isBadRequest());

        // Validate the MeetingAttachment in the database
        List<MeetingAttachment> meetingAttachmentList = meetingAttachmentRepository.findAll();
        assertThat(meetingAttachmentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMeetingAttachment() throws Exception {
        // Initialize the database
        meetingAttachmentRepository.saveAndFlush(meetingAttachment);

        int databaseSizeBeforeDelete = meetingAttachmentRepository.findAll().size();

        // Delete the meetingAttachment
        restMeetingAttachmentMockMvc
            .perform(delete("/api/meeting-attachments/{id}", meetingAttachment.getId()).with(csrf()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<MeetingAttachment> meetingAttachmentList = meetingAttachmentRepository.findAll();
        assertThat(meetingAttachmentList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
