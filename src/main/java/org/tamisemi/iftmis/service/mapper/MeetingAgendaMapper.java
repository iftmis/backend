package org.tamisemi.iftmis.service.mapper;

import org.mapstruct.*;
import org.tamisemi.iftmis.domain.*;
import org.tamisemi.iftmis.service.dto.MeetingAgendaDTO;

/**
 * Mapper for the entity {@link MeetingAgenda} and its DTO {@link MeetingAgendaDTO}.
 */
@Mapper(componentModel = "spring", uses = { MeetingMapper.class })
public interface MeetingAgendaMapper extends EntityMapper<MeetingAgendaDTO, MeetingAgenda> {
    @Mapping(source = "meeting.id", target = "meetingId")
    MeetingAgendaDTO toDto(MeetingAgenda meetingAgenda);

    @Mapping(source = "meetingId", target = "meeting")
    MeetingAgenda toEntity(MeetingAgendaDTO meetingAgendaDTO);

    default MeetingAgenda fromId(Long id) {
        if (id == null) {
            return null;
        }
        MeetingAgenda meetingAgenda = new MeetingAgenda();
        meetingAgenda.setId(id);
        return meetingAgenda;
    }
}
