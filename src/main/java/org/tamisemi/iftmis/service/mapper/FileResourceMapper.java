package org.tamisemi.iftmis.service.mapper;

import org.mapstruct.*;
import org.tamisemi.iftmis.domain.*;
import org.tamisemi.iftmis.service.dto.FileResourceDTO;

/**
 * Mapper for the entity {@link FileResource} and its DTO {@link FileResourceDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface FileResourceMapper extends EntityMapper<FileResourceDTO, FileResource> {
    default FileResource fromId(Long id) {
        if (id == null) {
            return null;
        }
        FileResource fileResource = new FileResource();
        fileResource.setId(id);
        return fileResource;
    }
}
