package org.tamisemi.iftmis.service.mapper;

import org.mapstruct.*;
import org.tamisemi.iftmis.domain.*;
import org.tamisemi.iftmis.service.dto.MeetingDTO;

/**
 * Mapper for the entity {@link Meeting} and its DTO {@link MeetingDTO}.
 */
@Mapper(componentModel = "spring", uses = { InspectionMapper.class })
public interface MeetingMapper extends EntityMapper<MeetingDTO, Meeting> {
    @Mapping(source = "inspection.id", target = "inspectionId")
    MeetingDTO toDto(Meeting meeting);

    @Mapping(source = "inspectionId", target = "inspection")
    Meeting toEntity(MeetingDTO meetingDTO);

    default Meeting fromId(Long id) {
        if (id == null) {
            return null;
        }
        Meeting meeting = new Meeting();
        meeting.setId(id);
        return meeting;
    }
}
