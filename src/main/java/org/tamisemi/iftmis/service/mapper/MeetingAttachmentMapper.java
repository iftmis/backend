package org.tamisemi.iftmis.service.mapper;

import org.mapstruct.*;
import org.tamisemi.iftmis.domain.*;
import org.tamisemi.iftmis.service.dto.MeetingAttachmentDTO;

/**
 * Mapper for the entity {@link MeetingAttachment} and its DTO {@link MeetingAttachmentDTO}.
 */
@Mapper(componentModel = "spring", uses = { MeetingMapper.class, FileResourceMapper.class })
public interface MeetingAttachmentMapper extends EntityMapper<MeetingAttachmentDTO, MeetingAttachment> {
    @Mapping(source = "meeting.id", target = "meetingId")
    @Mapping(source = "attachment.id", target = "attachmentId")
    @Mapping(source = "attachment.path", target = "attachmentPath")
    MeetingAttachmentDTO toDto(MeetingAttachment meetingAttachment);

    @Mapping(source = "meetingId", target = "meeting")
    @Mapping(source = "attachmentId", target = "attachment")
    MeetingAttachment toEntity(MeetingAttachmentDTO meetingAttachmentDTO);

    default MeetingAttachment fromId(Long id) {
        if (id == null) {
            return null;
        }
        MeetingAttachment meetingAttachment = new MeetingAttachment();
        meetingAttachment.setId(id);
        return meetingAttachment;
    }
}
