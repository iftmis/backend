package org.tamisemi.iftmis.service.dto;

import io.github.jhipster.service.Criteria;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;
import java.io.Serializable;
import java.util.Objects;

/**
 * Criteria class for the OrganisationUnit entity. This class is used
 * in OrganisationUnitResource to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /organisation-units?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class OrganisationUnitCriteria implements Serializable, Criteria {
    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter code;

    private StringFilter name;

    private StringFilter address;

    private StringFilter phoneNumber;

    private StringFilter email;

    private LongFilter organisationUnitLevelId;

    private LongFilter parentId;

    public OrganisationUnitCriteria() {}

    public OrganisationUnitCriteria(OrganisationUnitCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.code = other.code == null ? null : other.code.copy();
        this.name = other.name == null ? null : other.name.copy();
        this.address = other.address == null ? null : other.address.copy();
        this.phoneNumber = other.phoneNumber == null ? null : other.phoneNumber.copy();
        this.email = other.email == null ? null : other.email.copy();
        this.organisationUnitLevelId = other.organisationUnitLevelId == null ? null : other.organisationUnitLevelId.copy();
        this.parentId = other.parentId == null ? null : other.parentId.copy();
    }

    @Override
    public OrganisationUnitCriteria copy() {
        return new OrganisationUnitCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getCode() {
        return code;
    }

    public void setCode(StringFilter code) {
        this.code = code;
    }

    public StringFilter getName() {
        return name;
    }

    public void setName(StringFilter name) {
        this.name = name;
    }

    public StringFilter getAddress() {
        return address;
    }

    public void setAddress(StringFilter address) {
        this.address = address;
    }

    public StringFilter getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(StringFilter phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public StringFilter getEmail() {
        return email;
    }

    public void setEmail(StringFilter email) {
        this.email = email;
    }

    public LongFilter getOrganisationUnitLevelId() {
        return organisationUnitLevelId;
    }

    public void setOrganisationUnitLevelId(LongFilter organisationUnitLevelId) {
        this.organisationUnitLevelId = organisationUnitLevelId;
    }

    public LongFilter getParentId() {
        return parentId;
    }

    public void setParentId(LongFilter parentId) {
        this.parentId = parentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final OrganisationUnitCriteria that = (OrganisationUnitCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(code, that.code) &&
            Objects.equals(name, that.name) &&
            Objects.equals(address, that.address) &&
            Objects.equals(phoneNumber, that.phoneNumber) &&
            Objects.equals(email, that.email) &&
            Objects.equals(organisationUnitLevelId, that.organisationUnitLevelId) &&
            Objects.equals(parentId, that.parentId)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, code, name, address, phoneNumber, email, organisationUnitLevelId, parentId);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "OrganisationUnitCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (code != null ? "code=" + code + ", " : "") +
                (name != null ? "name=" + name + ", " : "") +
                (address != null ? "address=" + address + ", " : "") +
                (phoneNumber != null ? "phoneNumber=" + phoneNumber + ", " : "") +
                (email != null ? "email=" + email + ", " : "") +
                (organisationUnitLevelId != null ? "organisationUnitLevelId=" + organisationUnitLevelId + ", " : "") +
                (parentId != null ? "parentId=" + parentId + ", " : "") +
            "}";
    }
}
