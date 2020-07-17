package org.tamisemi.iftmis.service.dto;

import io.swagger.annotations.ApiModel;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.tamisemi.iftmis.domain.OrganisationUnit;
import org.tamisemi.iftmis.domain.OrganisationUnitLevel;

@ApiModel(description = "The OrganisationUnit entity.\n@author Chris")
public class OrganisationUnitDTO extends AbstractAuditingDTO implements Serializable {
    private Long id;

    @Size(min = 1, max = 64)
    private String code;

    @NotNull
    @Size(min = 2, max = 200)
    private String name;

    private String address;

    private String phoneNumber;

    private String email;

    private String background;

    private byte[] logo;

    private String logoContentType;

    private Long organisationUnitLevelId;

    private String organisationUnitLevelName;

    private OrganisationUnitLevel organisationUnitLevel;

    private Long parentId;

    private String parentName;

    private OrganisationUnit parent;

    public OrganisationUnitDTO() {
    }

    public OrganisationUnitDTO(
        Long id,
        @Size(min = 1, max = 64) String code,
        @NotNull @Size(min = 2, max = 200) String name,
        String address,
        String phoneNumber,
        String email,
        String background,
        byte[] logo,
        String logoContentType,
        OrganisationUnitLevel organisationUnitLevel,
        OrganisationUnit parent,
        Long organisationUnitLevelId,
        String organisationUnitLevelName,
        Long parentId,
        String parentName
    ) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.background = background;
        this.logo = logo;
        this.logoContentType = logoContentType;
        this.organisationUnitLevelId = organisationUnitLevelId;
        this.organisationUnitLevelName = organisationUnitLevelName;
        this.organisationUnitLevel = organisationUnitLevel;
        this.parentId = parentId;
        this.parentName = parentName;
        this.parent = parent;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public byte[] getLogo() {
        return logo;
    }

    public void setLogo(byte[] logo) {
        this.logo = logo;
    }

    public String getLogoContentType() {
        return logoContentType;
    }

    public void setLogoContentType(String logoContentType) {
        this.logoContentType = logoContentType;
    }

    public Long getOrganisationUnitLevelId() {
        return organisationUnitLevelId;
    }

    public void setOrganisationUnitLevelId(Long organisationUnitLevelId) {
        this.organisationUnitLevelId = organisationUnitLevelId;
    }

    public String getOrganisationUnitLevelName() {
        return organisationUnitLevelName;
    }

    public void setOrganisationUnitLevelName(String organisationUnitLevelName) {
        this.organisationUnitLevelName = organisationUnitLevelName;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public OrganisationUnitLevel getOrganisationUnitLevel() {
        return organisationUnitLevel;
    }

    public void setOrganisationUnitLevel(OrganisationUnitLevel organisationUnitLevel) {
        this.organisationUnitLevel = organisationUnitLevel;
    }

    public OrganisationUnit getParent() {
        return parent;
    }

    public void setParent(OrganisationUnit parent) {
        this.parent = parent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrganisationUnitDTO)) return false;
        OrganisationUnitDTO that = (OrganisationUnitDTO) o;
        return (
            getId().equals(that.getId()) &&
                getCode().equals(that.getCode()) &&
                getName().equals(that.getName()) &&
                getAddress().equals(that.getAddress()) &&
                getPhoneNumber().equals(that.getPhoneNumber()) &&
                getEmail().equals(that.getEmail()) &&
                getBackground().equals(that.getBackground()) &&
                Arrays.equals(getLogo(), that.getLogo()) &&
                getLogoContentType().equals(that.getLogoContentType()) &&
                getOrganisationUnitLevelId().equals(that.getOrganisationUnitLevelId()) &&
                getOrganisationUnitLevelName().equals(that.getOrganisationUnitLevelName()) &&
                getOrganisationUnitLevel().equals(that.getOrganisationUnitLevel()) &&
                getParentId().equals(that.getParentId()) &&
                getParentName().equals(that.getParentName()) &&
                getParent().equals(that.getParent())
        );
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(
            getId(),
            getCode(),
            getName(),
            getAddress(),
            getPhoneNumber(),
            getEmail(),
            getBackground(),
            getLogoContentType(),
            getOrganisationUnitLevelId(),
            getOrganisationUnitLevelName(),
            getOrganisationUnitLevel(),
            getParentId(),
            getParentName(),
            getParent()
        );
        result = 31 * result + Arrays.hashCode(getLogo());
        return result;
    }

    @Override
    public String toString() {
        return "OrganisationUnitDTO{" +
            "id=" + id +
            ", code='" + code + '\'' +
            ", name='" + name + '\'' +
            ", address='" + address + '\'' +
            ", phoneNumber='" + phoneNumber + '\'' +
            ", email='" + email + '\'' +
            ", background='" + background + '\'' +
            ", logo=" + Arrays.toString(logo) +
            ", logoContentType='" + logoContentType + '\'' +
            ", organisationUnitLevelId=" + organisationUnitLevelId +
            ", organisationUnitLevelName='" + organisationUnitLevelName + '\'' +
            ", organisationUnitLevel=" + organisationUnitLevel +
            ", parentId=" + parentId +
            ", parentName='" + parentName + '\'' +
            ", parent=" + parent +
            '}';
    }
}
