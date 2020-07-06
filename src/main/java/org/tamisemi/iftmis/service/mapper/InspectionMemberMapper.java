package org.tamisemi.iftmis.service.mapper;

import org.mapstruct.*;
import org.tamisemi.iftmis.domain.*;
import org.tamisemi.iftmis.service.dto.InspectionMemberDTO;

/**
 * Mapper for the entity {@link InspectionMember} and its DTO {@link InspectionMemberDTO}.
 */
@Mapper(componentModel = "spring", uses = { UserMapper.class, FileResourceMapper.class })
public interface InspectionMemberMapper extends EntityMapper<InspectionMemberDTO, InspectionMember> {
    @Mapping(source = "user.id", target = "userId")
    @Mapping(
        target = "userFullName",
        expression = "java(inspectionMember.getUser().getFirstName() + \" \" + inspectionMember.getUser().getLastName())"
    )
    @Mapping(source = "letterAttachment.id", target = "letterAttachmentId")
    @Mapping(source = "letterAttachment.path", target = "letterAttachmentPath")
    @Mapping(source = "declarationAttachment.id", target = "declarationAttachmentId")
    @Mapping(source = "declarationAttachment.path", target = "declarationAttachmentPath")
    InspectionMemberDTO toDto(InspectionMember inspectionMember);

    @Mapping(source = "userId", target = "user")
    @Mapping(source = "letterAttachmentId", target = "letterAttachment")
    @Mapping(source = "declarationAttachmentId", target = "declarationAttachment")
    InspectionMember toEntity(InspectionMemberDTO inspectionMemberDTO);

    default InspectionMember fromId(Long id) {
        if (id == null) {
            return null;
        }
        InspectionMember inspectionMember = new InspectionMember();
        inspectionMember.setId(id);
        return inspectionMember;
    }
}
