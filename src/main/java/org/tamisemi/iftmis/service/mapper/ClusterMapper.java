package org.tamisemi.iftmis.service.mapper;

import org.mapstruct.Mapper;
import org.tamisemi.iftmis.domain.Cluster;
import org.tamisemi.iftmis.service.dto.ClusterDTO;

/**
 * Mapper for the entity {@link Cluster} and its DTO {@link ClusterDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ClusterMapper extends EntityMapper<ClusterDTO, Cluster> {
    default Cluster fromId(Long id) {
        if (id == null) {
            return null;
        }
        Cluster cluster = new Cluster();
        cluster.setId(id);
        return cluster;
    }
}
