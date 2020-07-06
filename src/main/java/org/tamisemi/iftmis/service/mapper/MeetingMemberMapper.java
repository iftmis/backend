package org.tamisemi.iftmis.service.mapper;

import org.mapstruct.*;
import org.tamisemi.iftmis.domain.*;
import org.tamisemi.iftmis.service.dto.MeetingMemberDTO;

/**
 * Mapper for the entity {@link MeetingMember} and its DTO {@link MeetingMemberDTO}.
 */
@Mapper(componentModel = "spring", uses = { MeetingMapper.class })
public interface MeetingMemberMapper extends EntityMapper<MeetingMemberDTO, MeetingMember> {
    @Mapping(source = "meeting.id", target = "meetingId")
    MeetingMemberDTO toDto(MeetingMember meetingMember);

    @Mapping(source = "meetingId", target = "meeting")
    MeetingMember toEntity(MeetingMemberDTO meetingMemberDTO);

    default MeetingMember fromId(Long id) {
        if (id == null) {
            return null;
        }
        MeetingMember meetingMember = new MeetingMember();
        meetingMember.setId(id);
        return meetingMember;
    }
}
