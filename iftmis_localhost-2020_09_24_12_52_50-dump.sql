--
-- PostgreSQL database dump
--

-- Dumped from database version 12.4 (Ubuntu 12.4-0ubuntu0.20.04.1)
-- Dumped by pg_dump version 12.4 (Ubuntu 12.4-0ubuntu0.20.04.1)

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: auditable_areas; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.auditable_areas (
    id bigint NOT NULL,
    code character varying(64),
    name character varying(2000) NOT NULL,
    created_by character varying(50) NOT NULL,
    created_date timestamp without time zone NOT NULL,
    last_modified_by character varying(50),
    last_modified_date timestamp without time zone
);


ALTER TABLE public.auditable_areas OWNER TO postgres;

--
-- Name: TABLE auditable_areas; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON TABLE public.auditable_areas IS 'The AuditableArea entity. Area of audit used when create an audit engagement\n@author Chris';


--
-- Name: authorities; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.authorities (
    name character varying(50) NOT NULL
);


ALTER TABLE public.authorities OWNER TO postgres;

--
-- Name: cluster_reports; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.cluster_reports (
    id bigint NOT NULL,
    code character varying(64),
    description text,
    cluster_id bigint NOT NULL,
    created_by character varying(50) NOT NULL,
    created_date timestamp without time zone NOT NULL,
    last_modified_by character varying(50),
    last_modified_date timestamp without time zone
);


ALTER TABLE public.cluster_reports OWNER TO postgres;

--
-- Name: TABLE cluster_reports; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON TABLE public.cluster_reports IS 'The ClusterReport(cluster_reports) entity.\n@author Kachinga';


--
-- Name: clusters; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.clusters (
    id bigint NOT NULL,
    code character varying(64),
    name character varying(200) NOT NULL,
    created_by character varying(50) NOT NULL,
    created_date timestamp without time zone NOT NULL,
    last_modified_by character varying(50),
    last_modified_date timestamp without time zone
);


ALTER TABLE public.clusters OWNER TO postgres;

--
-- Name: TABLE clusters; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON TABLE public.clusters IS 'The Cluster(clusters) entity.\n@author Kachinga';


--
-- Name: databasechangelog; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.databasechangelog (
    id character varying(255) NOT NULL,
    author character varying(255) NOT NULL,
    filename character varying(255) NOT NULL,
    dateexecuted timestamp without time zone NOT NULL,
    orderexecuted integer NOT NULL,
    exectype character varying(10) NOT NULL,
    md5sum character varying(35),
    description character varying(255),
    comments character varying(255),
    tag character varying(255),
    liquibase character varying(20),
    contexts character varying(255),
    labels character varying(255),
    deployment_id character varying(10)
);


ALTER TABLE public.databasechangelog OWNER TO postgres;

--
-- Name: databasechangeloglock; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.databasechangeloglock (
    id integer NOT NULL,
    locked boolean NOT NULL,
    lockgranted timestamp without time zone,
    lockedby character varying(255)
);


ALTER TABLE public.databasechangeloglock OWNER TO postgres;

--
-- Name: file_resources; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.file_resources (
    id bigint NOT NULL,
    name character varying(200) NOT NULL,
    path character varying(300),
    content_type character varying(200),
    context_md_5 character varying(32) NOT NULL,
    size double precision NOT NULL,
    is_assigned boolean,
    type character varying(100) NOT NULL,
    created_by character varying(50) NOT NULL,
    created_date timestamp without time zone NOT NULL,
    last_modified_by character varying(50),
    last_modified_date timestamp without time zone
);


ALTER TABLE public.file_resources OWNER TO postgres;

--
-- Name: TABLE file_resources; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON TABLE public.file_resources IS 'The FileResource entity.\n@author Chris';


--
-- Name: financial_years; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.financial_years (
    id bigint NOT NULL,
    name character varying(200) NOT NULL,
    start_date date NOT NULL,
    end_date date NOT NULL,
    is_opened boolean NOT NULL,
    created_by character varying(50) NOT NULL,
    created_date timestamp without time zone NOT NULL,
    last_modified_by character varying(50),
    last_modified_date timestamp without time zone,
    closed boolean
);


ALTER TABLE public.financial_years OWNER TO postgres;

--
-- Name: TABLE financial_years; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON TABLE public.financial_years IS 'The FinancialYear(financial_years) entity.\n@author Chris';


--
-- Name: finding_categories; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.finding_categories (
    id bigint NOT NULL,
    code character varying(64),
    name character varying(200) NOT NULL,
    created_by character varying(50) NOT NULL,
    created_date timestamp without time zone NOT NULL,
    last_modified_by character varying(50),
    last_modified_date timestamp without time zone
);


ALTER TABLE public.finding_categories OWNER TO postgres;

--
-- Name: TABLE finding_categories; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON TABLE public.finding_categories IS 'The FindingCategory(finding_categories) entity.\n@author Chris';


--
-- Name: finding_recommendations; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.finding_recommendations (
    id bigint NOT NULL,
    description text NOT NULL,
    implementation_status character varying(255) NOT NULL,
    finding_id bigint NOT NULL,
    created_by character varying(50) NOT NULL,
    created_date timestamp without time zone NOT NULL,
    last_modified_by character varying(50),
    last_modified_date timestamp without time zone
);


ALTER TABLE public.finding_recommendations OWNER TO postgres;

--
-- Name: TABLE finding_recommendations; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON TABLE public.finding_recommendations IS 'The FindingRecommendation(finding_recommendations) entity.\n@author Chris';


--
-- Name: finding_responses; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.finding_responses (
    id bigint NOT NULL,
    source character varying(255) NOT NULL,
    description text NOT NULL,
    recommendation_id bigint NOT NULL,
    created_by character varying(50) NOT NULL,
    created_date timestamp without time zone NOT NULL,
    last_modified_by character varying(50),
    last_modified_date timestamp without time zone
);


ALTER TABLE public.finding_responses OWNER TO postgres;

--
-- Name: TABLE finding_responses; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON TABLE public.finding_responses IS 'The FindingResponse(inspection_responses) entity.\n@author Chris';


--
-- Name: finding_sub_categories; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.finding_sub_categories (
    id bigint NOT NULL,
    code character varying(64),
    name character varying(200) NOT NULL,
    created_by character varying(50) NOT NULL,
    created_date timestamp without time zone NOT NULL,
    last_modified_by character varying(50),
    last_modified_date timestamp without time zone
);


ALTER TABLE public.finding_sub_categories OWNER TO postgres;

--
-- Name: TABLE finding_sub_categories; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON TABLE public.finding_sub_categories IS 'The FindingSubCategory(finding_sub_categories) entity.\n@author Chris';


--
-- Name: findings; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.findings (
    id bigint NOT NULL,
    source character varying(255) NOT NULL,
    code character varying(255),
    description text NOT NULL,
    action_plan_category character varying(255) NOT NULL,
    is_closed boolean,
    organisation_unit_id bigint NOT NULL,
    created_by character varying(50) NOT NULL,
    created_date timestamp without time zone NOT NULL,
    last_modified_by character varying(50),
    last_modified_date timestamp without time zone
);


ALTER TABLE public.findings OWNER TO postgres;

--
-- Name: TABLE findings; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON TABLE public.findings IS 'The Finding(findings) entity.\n@author Chris';


--
-- Name: gfs_codes; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.gfs_codes (
    id bigint NOT NULL,
    code character varying(64) NOT NULL,
    description text NOT NULL,
    created_by character varying(50) NOT NULL,
    created_date timestamp without time zone NOT NULL,
    last_modified_by character varying(50),
    last_modified_date timestamp without time zone
);


ALTER TABLE public.gfs_codes OWNER TO postgres;

--
-- Name: TABLE gfs_codes; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON TABLE public.gfs_codes IS 'The GfsCodes(gfs_codes) entity.\n@author Chris';


--
-- Name: iftmis_entity_audit_event; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.iftmis_entity_audit_event (
    id bigint NOT NULL,
    entity_id bigint NOT NULL,
    entity_type character varying(255) NOT NULL,
    action character varying(20) NOT NULL,
    entity_value text,
    commit_version integer,
    modified_by character varying(100),
    modified_date timestamp without time zone NOT NULL
);


ALTER TABLE public.iftmis_entity_audit_event OWNER TO postgres;

--
-- Name: iftmis_persistent_audit_event; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.iftmis_persistent_audit_event (
    event_id bigint NOT NULL,
    principal character varying(50) NOT NULL,
    event_date timestamp without time zone,
    event_type character varying(255)
);


ALTER TABLE public.iftmis_persistent_audit_event OWNER TO postgres;

--
-- Name: iftmis_persistent_audit_evt_data; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.iftmis_persistent_audit_evt_data (
    event_id bigint NOT NULL,
    name character varying(150) NOT NULL,
    value character varying(255)
);


ALTER TABLE public.iftmis_persistent_audit_evt_data OWNER TO postgres;

--
-- Name: iftmis_persistent_token; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.iftmis_persistent_token (
    series character varying(20) NOT NULL,
    user_id bigint,
    token_value character varying(20) NOT NULL,
    token_date date,
    ip_address character varying(39),
    user_agent character varying(255)
);


ALTER TABLE public.iftmis_persistent_token OWNER TO postgres;

--
-- Name: indicators; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.indicators (
    id bigint NOT NULL,
    name character varying(1000) NOT NULL,
    sub_area_id bigint NOT NULL,
    created_by character varying(50) NOT NULL,
    created_date timestamp without time zone NOT NULL,
    last_modified_by character varying(50),
    last_modified_date timestamp without time zone
);


ALTER TABLE public.indicators OWNER TO postgres;

--
-- Name: TABLE indicators; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON TABLE public.indicators IS 'The Indicator(indicators) entity.\n@author Chris';


--
-- Name: inspection_activities; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.inspection_activities (
    id bigint NOT NULL,
    days integer NOT NULL,
    inspection_plan_id bigint NOT NULL,
    objective_id bigint NOT NULL,
    auditable_area_id bigint NOT NULL,
    sub_area_id bigint NOT NULL,
    created_by character varying(50) NOT NULL,
    created_date timestamp without time zone NOT NULL,
    last_modified_by character varying(50),
    last_modified_date timestamp without time zone
);


ALTER TABLE public.inspection_activities OWNER TO postgres;

--
-- Name: inspection_activities_organisation_units; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.inspection_activities_organisation_units (
    organisation_units_id bigint NOT NULL,
    inspection_activity_id bigint NOT NULL
);


ALTER TABLE public.inspection_activities_organisation_units OWNER TO postgres;

--
-- Name: inspection_activities_risks; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.inspection_activities_risks (
    risk_id bigint NOT NULL,
    inspection_activity_id bigint NOT NULL
);


ALTER TABLE public.inspection_activities_risks OWNER TO postgres;

--
-- Name: inspection_activity_quarters; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.inspection_activity_quarters (
    id bigint NOT NULL,
    days integer,
    activity_id bigint NOT NULL,
    quarter_id bigint NOT NULL,
    created_by character varying(50) NOT NULL,
    created_date timestamp without time zone NOT NULL,
    last_modified_by character varying(50),
    last_modified_date timestamp without time zone
);


ALTER TABLE public.inspection_activity_quarters OWNER TO postgres;

--
-- Name: inspection_areas; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.inspection_areas (
    id bigint NOT NULL,
    name character varying(2000) NOT NULL,
    inspection_id bigint NOT NULL,
    auditable_area_id bigint,
    created_by character varying(50) NOT NULL,
    created_date timestamp without time zone NOT NULL,
    last_modified_by character varying(50),
    last_modified_date timestamp without time zone
);


ALTER TABLE public.inspection_areas OWNER TO postgres;

--
-- Name: TABLE inspection_areas; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON TABLE public.inspection_areas IS 'The InspectionAuditableArea(inspection_auditable_areas) entity.\n@author Chris';


--
-- Name: inspection_budget; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.inspection_budget (
    id bigint NOT NULL,
    quantity real NOT NULL,
    frequency real NOT NULL,
    unit_price numeric(21,2) NOT NULL,
    gfs_code_id bigint NOT NULL,
    inspection_id bigint NOT NULL,
    created_by character varying(50) NOT NULL,
    created_date timestamp without time zone NOT NULL,
    last_modified_by character varying(50),
    last_modified_date timestamp without time zone
);


ALTER TABLE public.inspection_budget OWNER TO postgres;

--
-- Name: inspection_findings; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.inspection_findings (
    id bigint NOT NULL,
    code character varying(5) NOT NULL,
    description text,
    condition character varying(255),
    disclosed_last_inspection boolean,
    causes text,
    action_plan_category character varying(255),
    implication text,
    is_closed boolean,
    work_done_id bigint NOT NULL,
    category_id bigint NOT NULL,
    sub_category_id bigint NOT NULL,
    created_by character varying(50) NOT NULL,
    created_date timestamp without time zone NOT NULL,
    last_modified_by character varying(50),
    last_modified_date timestamp without time zone
);


ALTER TABLE public.inspection_findings OWNER TO postgres;

--
-- Name: TABLE inspection_findings; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON TABLE public.inspection_findings IS 'The InspectionFinding(inspection_findings) entity.\n@author Chris';


--
-- Name: inspection_indicators; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.inspection_indicators (
    id bigint NOT NULL,
    name character varying(1000) NOT NULL,
    inspection_sub_area_id bigint NOT NULL,
    indicator_id bigint NOT NULL,
    created_by character varying(50) NOT NULL,
    created_date timestamp without time zone NOT NULL,
    last_modified_by character varying(50),
    last_modified_date timestamp without time zone
);


ALTER TABLE public.inspection_indicators OWNER TO postgres;

--
-- Name: TABLE inspection_indicators; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON TABLE public.inspection_indicators IS 'The InspectionIndicator(inspection_indicators) entity.\n@author Chris';


--
-- Name: inspection_members; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.inspection_members (
    id bigint NOT NULL,
    role character varying(255) NOT NULL,
    user_id bigint NOT NULL,
    letter_attachment_id bigint,
    declaration_attachment_id bigint,
    created_by character varying(50) NOT NULL,
    created_date timestamp without time zone NOT NULL,
    last_modified_by character varying(50),
    last_modified_date timestamp without time zone,
    inspection_id bigint NOT NULL
);


ALTER TABLE public.inspection_members OWNER TO postgres;

--
-- Name: TABLE inspection_members; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON TABLE public.inspection_members IS 'The InspectionMember entity.\n@author Chris';


--
-- Name: inspection_plans; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.inspection_plans (
    id bigint NOT NULL,
    name character varying(255) NOT NULL,
    organisation_unit_id bigint NOT NULL,
    financial_year_id bigint NOT NULL,
    created_by character varying(50) NOT NULL,
    created_date timestamp without time zone NOT NULL,
    last_modified_by character varying(50),
    last_modified_date timestamp without time zone
);


ALTER TABLE public.inspection_plans OWNER TO postgres;

--
-- Name: inspection_procedures; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.inspection_procedures (
    id bigint NOT NULL,
    name character varying(1000) NOT NULL,
    inspection_indicator_id bigint NOT NULL,
    procedure_id bigint NOT NULL,
    created_by character varying(50) NOT NULL,
    created_date timestamp without time zone NOT NULL,
    last_modified_by character varying(50),
    last_modified_date timestamp without time zone
);


ALTER TABLE public.inspection_procedures OWNER TO postgres;

--
-- Name: TABLE inspection_procedures; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON TABLE public.inspection_procedures IS 'The InspectionProcedure entity.\n@author Chris';


--
-- Name: inspection_recommendations; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.inspection_recommendations (
    id bigint NOT NULL,
    description text NOT NULL,
    implementation_status character varying(255) NOT NULL,
    completion_date date,
    compliance_plan text,
    created_by character varying(50) NOT NULL,
    created_date timestamp without time zone NOT NULL,
    last_modified_by character varying(50),
    last_modified_date timestamp without time zone
);


ALTER TABLE public.inspection_recommendations OWNER TO postgres;

--
-- Name: TABLE inspection_recommendations; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON TABLE public.inspection_recommendations IS 'The InspectionRecommendation entity.\n@author Chris';


--
-- Name: inspection_sub_areas; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.inspection_sub_areas (
    id bigint NOT NULL,
    sub_area_id bigint NOT NULL,
    created_by character varying(50) NOT NULL,
    created_date timestamp without time zone NOT NULL,
    last_modified_by character varying(50),
    last_modified_date timestamp without time zone,
    general_objective character varying(100),
    inspection_area_id bigint NOT NULL
);


ALTER TABLE public.inspection_sub_areas OWNER TO postgres;

--
-- Name: TABLE inspection_sub_areas; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON TABLE public.inspection_sub_areas IS 'The InspectionSubArea(inspection_sub_areas) entity.\n@author Chris';


--
-- Name: inspection_work_dones; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.inspection_work_dones (
    id bigint NOT NULL,
    name character varying(255) NOT NULL,
    is_ok boolean,
    procedure_id bigint NOT NULL,
    created_by character varying(50) NOT NULL,
    created_date timestamp without time zone NOT NULL,
    last_modified_by character varying(50),
    last_modified_date timestamp without time zone
);


ALTER TABLE public.inspection_work_dones OWNER TO postgres;

--
-- Name: TABLE inspection_work_dones; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON TABLE public.inspection_work_dones IS 'The InspectionWorkDone(inspection_work_dones) entity.\n@author Chris';


--
-- Name: inspections; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.inspections (
    id bigint NOT NULL,
    name character varying(200) NOT NULL,
    start_date date NOT NULL,
    end_date date NOT NULL,
    inspection_type character varying(255) NOT NULL,
    financial_year_id bigint NOT NULL,
    organisation_unit_id bigint NOT NULL,
    created_by character varying(50) NOT NULL,
    created_date timestamp without time zone NOT NULL,
    last_modified_by character varying(50),
    last_modified_date timestamp without time zone
);


ALTER TABLE public.inspections OWNER TO postgres;

--
-- Name: TABLE inspections; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON TABLE public.inspections IS 'The Inspection(inspections) entity.\n@author Chris';


--
-- Name: meeting_agendas; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.meeting_agendas (
    id bigint NOT NULL,
    description text NOT NULL,
    meeting_id bigint NOT NULL,
    created_by character varying(50) NOT NULL,
    created_date timestamp without time zone NOT NULL,
    last_modified_by character varying(50),
    last_modified_date timestamp without time zone
);


ALTER TABLE public.meeting_agendas OWNER TO postgres;

--
-- Name: TABLE meeting_agendas; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON TABLE public.meeting_agendas IS 'The MeetingAgenda entity.\n@author Chris';


--
-- Name: meeting_attachments; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.meeting_attachments (
    id bigint NOT NULL,
    name character varying(200) NOT NULL,
    meeting_id bigint NOT NULL,
    attachment_id bigint NOT NULL,
    created_by character varying(50) NOT NULL,
    created_date timestamp without time zone NOT NULL,
    last_modified_by character varying(50),
    last_modified_date timestamp without time zone
);


ALTER TABLE public.meeting_attachments OWNER TO postgres;

--
-- Name: TABLE meeting_attachments; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON TABLE public.meeting_attachments IS 'The MeetingAttachment entity.\n@author Chris';


--
-- Name: meeting_members; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.meeting_members (
    id bigint NOT NULL,
    name character varying(200) NOT NULL,
    phone_number character varying(20),
    email character varying(200),
    title character varying(200),
    meeting_id bigint NOT NULL,
    created_by character varying(50) NOT NULL,
    created_date timestamp without time zone NOT NULL,
    last_modified_by character varying(50),
    last_modified_date timestamp without time zone
);


ALTER TABLE public.meeting_members OWNER TO postgres;

--
-- Name: TABLE meeting_members; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON TABLE public.meeting_members IS 'The MeetingMember entity.\n@author Chris';


--
-- Name: notifications; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.notifications (
    id bigint NOT NULL,
    email character varying(100) NOT NULL,
    subject character varying(200),
    body text,
    is_sent boolean,
    is_read boolean,
    attachments character varying(3000),
    created_by character varying(50) NOT NULL,
    created_date timestamp without time zone NOT NULL,
    last_modified_by character varying(50),
    last_modified_date timestamp without time zone
);


ALTER TABLE public.notifications OWNER TO postgres;

--
-- Name: TABLE notifications; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON TABLE public.notifications IS 'The Notification(notifications) entity.\n@author Chris';


--
-- Name: objectives; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.objectives (
    id bigint NOT NULL,
    code character varying(64) NOT NULL,
    description text NOT NULL,
    created_by character varying(50) NOT NULL,
    created_date timestamp without time zone NOT NULL,
    last_modified_by character varying(50),
    last_modified_date timestamp without time zone
);


ALTER TABLE public.objectives OWNER TO postgres;

--
-- Name: TABLE objectives; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON TABLE public.objectives IS 'The Objective(objectives) entity.\n@author Chris';


--
-- Name: organisation_unit_levels; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.organisation_unit_levels (
    id bigint NOT NULL,
    code character varying(64),
    name character varying(200) NOT NULL,
    level integer NOT NULL,
    is_inspection_level boolean,
    created_by character varying(50) NOT NULL,
    created_date timestamp without time zone NOT NULL,
    last_modified_by character varying(50),
    last_modified_date timestamp without time zone
);


ALTER TABLE public.organisation_unit_levels OWNER TO postgres;

--
-- Name: TABLE organisation_unit_levels; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON TABLE public.organisation_unit_levels IS 'The OrganisationUnitLevel(organisation_unit_levels) entity.\n@author Chris';


--
-- Name: organisation_units; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.organisation_units (
    id bigint NOT NULL,
    code character varying(64),
    name character varying(200) NOT NULL,
    address character varying(255),
    phone_number character varying(255),
    email character varying(255),
    background text,
    logo bytea,
    logo_content_type character varying(255),
    organisation_unit_level_id bigint NOT NULL,
    parent_id bigint,
    created_by character varying(50) NOT NULL,
    created_date timestamp without time zone NOT NULL,
    last_modified_by character varying(50),
    last_modified_date timestamp without time zone
);


ALTER TABLE public.organisation_units OWNER TO postgres;

--
-- Name: TABLE organisation_units; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON TABLE public.organisation_units IS 'The OrganisationUnit entity.\n@author Chris';


--
-- Name: procedures; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.procedures (
    id bigint NOT NULL,
    name character varying(2000) NOT NULL,
    indicator_id bigint NOT NULL,
    created_by character varying(50) NOT NULL,
    created_date timestamp without time zone NOT NULL,
    last_modified_by character varying(50),
    last_modified_date timestamp without time zone
);


ALTER TABLE public.procedures OWNER TO postgres;

--
-- Name: TABLE procedures; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON TABLE public.procedures IS 'The Procedure(procedures) entity.\n@author Chris';


--
-- Name: quarters; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.quarters (
    id bigint NOT NULL,
    code character varying(64),
    name character varying(200) NOT NULL,
    start_date date NOT NULL,
    end_date date NOT NULL,
    financial_year_id bigint NOT NULL,
    created_by character varying(50) NOT NULL,
    created_date timestamp without time zone NOT NULL,
    last_modified_by character varying(50),
    last_modified_date timestamp without time zone
);


ALTER TABLE public.quarters OWNER TO postgres;

--
-- Name: TABLE quarters; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON TABLE public.quarters IS 'The Quarter(quarters) entity.\n@author Chris';


--
-- Name: response_attachments; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.response_attachments (
    id bigint NOT NULL,
    name character varying(100) NOT NULL,
    attachment_id bigint NOT NULL,
    response_id bigint NOT NULL,
    created_by character varying(50) NOT NULL,
    created_date timestamp without time zone NOT NULL,
    last_modified_by character varying(50),
    last_modified_date timestamp without time zone
);


ALTER TABLE public.response_attachments OWNER TO postgres;

--
-- Name: TABLE response_attachments; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON TABLE public.response_attachments IS 'The InspectionResponseAttachment entity.\n@author Chris';


--
-- Name: risk_categories; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.risk_categories (
    id bigint NOT NULL,
    code character varying(64),
    name character varying(200) NOT NULL,
    created_by character varying(50) NOT NULL,
    created_date timestamp without time zone NOT NULL,
    last_modified_by character varying(50),
    last_modified_date timestamp without time zone
);


ALTER TABLE public.risk_categories OWNER TO postgres;

--
-- Name: TABLE risk_categories; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON TABLE public.risk_categories IS 'The RiskCategory(risk_categories) entity.\n@author Chris';


--
-- Name: risk_ranks; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.risk_ranks (
    id bigint NOT NULL,
    name character varying(200) NOT NULL,
    min_value integer NOT NULL,
    max_value integer NOT NULL,
    hex_color character varying(7),
    created_by character varying(50) NOT NULL,
    created_date timestamp without time zone NOT NULL,
    last_modified_by character varying(50),
    last_modified_date timestamp without time zone
);


ALTER TABLE public.risk_ranks OWNER TO postgres;

--
-- Name: TABLE risk_ranks; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON TABLE public.risk_ranks IS 'The RiskRank(risk_ranks) entity.\n@author Chris';


--
-- Name: risk_ratings; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.risk_ratings (
    id bigint NOT NULL,
    source character varying(255),
    impact integer NOT NULL,
    likelihood integer NOT NULL,
    comments text,
    risk_id bigint NOT NULL,
    created_by character varying(50) NOT NULL,
    created_date timestamp without time zone NOT NULL,
    last_modified_by character varying(50),
    last_modified_date timestamp without time zone
);


ALTER TABLE public.risk_ratings OWNER TO postgres;

--
-- Name: TABLE risk_ratings; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON TABLE public.risk_ratings IS 'The RiskRating(risk_ratings) entity.\n@author Chris';


--
-- Name: risk_registers; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.risk_registers (
    id bigint NOT NULL,
    name character varying(200) NOT NULL,
    is_approved boolean,
    approved_date date,
    approved_by character varying(200),
    organisation_unit_id bigint NOT NULL,
    financial_year_id bigint NOT NULL,
    created_by character varying(50) NOT NULL,
    created_date timestamp without time zone NOT NULL,
    last_modified_by character varying(50),
    last_modified_date timestamp without time zone
);


ALTER TABLE public.risk_registers OWNER TO postgres;

--
-- Name: TABLE risk_registers; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON TABLE public.risk_registers IS 'The RiskRegister(risk_registers) entity.\n@author Chris';


--
-- Name: risks; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.risks (
    id bigint NOT NULL,
    code character varying(64),
    description text,
    risk_register_id bigint NOT NULL,
    objective_id bigint NOT NULL,
    risk_category_id bigint NOT NULL,
    created_by character varying(50) NOT NULL,
    created_date timestamp without time zone NOT NULL,
    last_modified_by character varying(50),
    last_modified_date timestamp without time zone
);


ALTER TABLE public.risks OWNER TO postgres;

--
-- Name: TABLE risks; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON TABLE public.risks IS 'The Risk(risks) entity.\n@author Chris';


--
-- Name: sequence_generator; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.sequence_generator
    START WITH 1050
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.sequence_generator OWNER TO postgres;

--
-- Name: sub_areas; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.sub_areas (
    id bigint NOT NULL,
    name character varying(200) NOT NULL,
    area_id bigint NOT NULL,
    created_by character varying(50) NOT NULL,
    created_date timestamp without time zone NOT NULL,
    last_modified_by character varying(50),
    last_modified_date timestamp without time zone
);


ALTER TABLE public.sub_areas OWNER TO postgres;

--
-- Name: TABLE sub_areas; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON TABLE public.sub_areas IS 'The InspectionProgram(inspection_programs) entity.\n@author Chris';


--
-- Name: team_meetings; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.team_meetings (
    id bigint NOT NULL,
    type character varying(255) NOT NULL,
    meeting_date date NOT NULL,
    venue character varying(200) NOT NULL,
    summary text,
    inspection_id bigint NOT NULL,
    created_by character varying(50) NOT NULL,
    created_date timestamp without time zone NOT NULL,
    last_modified_by character varying(50),
    last_modified_date timestamp without time zone
);


ALTER TABLE public.team_meetings OWNER TO postgres;

--
-- Name: TABLE team_meetings; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON TABLE public.team_meetings IS 'The TeamMeeting entity.\n@author Chris';


--
-- Name: user_authorities; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.user_authorities (
    user_id bigint NOT NULL,
    authority_name character varying(50) NOT NULL
);


ALTER TABLE public.user_authorities OWNER TO postgres;

--
-- Name: users; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.users (
    id bigint NOT NULL,
    login character varying(50) NOT NULL,
    password_hash character varying(60) NOT NULL,
    first_name character varying(50),
    last_name character varying(50),
    email character varying(191),
    image_url character varying(256),
    activated boolean NOT NULL,
    lang_key character varying(10),
    activation_key character varying(20),
    reset_key character varying(20),
    created_by character varying(50) NOT NULL,
    created_date timestamp without time zone,
    reset_date timestamp without time zone,
    last_modified_by character varying(50),
    last_modified_date timestamp without time zone,
    organisation_unit_id bigint
);


ALTER TABLE public.users OWNER TO postgres;

--
-- Data for Name: auditable_areas; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.auditable_areas (id, code, name, created_by, created_date, last_modified_by, last_modified_date) FROM stdin;
1351	001	Governance	admin	2020-09-17 07:27:11.758276	admin	2020-09-17 07:27:11.758276
1352	002	Revenue and assets	admin	2020-09-17 07:28:54.931754	admin	2020-09-17 07:28:54.931754
\.


--
-- Data for Name: authorities; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.authorities (name) FROM stdin;
ROLE_ADMIN
ROLE_USER
\.


--
-- Data for Name: cluster_reports; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.cluster_reports (id, code, description, cluster_id, created_by, created_date, last_modified_by, last_modified_date) FROM stdin;
\.


--
-- Data for Name: clusters; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.clusters (id, code, name, created_by, created_date, last_modified_by, last_modified_date) FROM stdin;
3453	PPRA	PPRA	admin	2020-09-18 12:13:34.987	admin	2020-09-18 12:13:34.987
3454	LAAC	LAAC	admin	2020-09-18 12:13:43.211	admin	2020-09-18 12:13:43.211
3455	INSPECTION	INSPECTION	admin	2020-09-18 12:14:43.757	admin	2020-09-18 12:14:43.757
3456	IA	IA	admin	2020-09-18 12:14:50.091	admin	2020-09-18 12:14:50.091
3451	CAG	CAG	admin	2020-09-18 12:12:16.666	admin	2020-09-18 13:12:21.049
\.


--
-- Data for Name: databasechangelog; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) FROM stdin;
00000000000000	Chris	config/liquibase/changelog/00000000000000_initial_schema.xml	2020-09-17 09:42:55.588744	1	EXECUTED	8:b8c27d9dc8db18b5de87cdb8c38a416b	createSequence sequenceName=sequence_generator		\N	3.9.0	\N	\N	0324975463
00000000000001	Chris	config/liquibase/changelog/00000000000000_initial_schema.xml	2020-09-17 09:42:55.652389	2	EXECUTED	8:d324bb085104cefee88bc576bf3763ea	createTable tableName=users; createTable tableName=authorities; createTable tableName=user_authorities; addPrimaryKey tableName=user_authorities; createTable tableName=iftmis_persistent_token; addForeignKeyConstraint baseTableName=user_authorities...		\N	3.9.0	\N	\N	0324975463
20200706084725-1	Chris	config/liquibase/changelog/20200706084725_added_entity_ResponseAttachment.xml	2020-09-17 09:42:55.666317	3	EXECUTED	8:82a75534bd70b4683420ca63300f0098	createTable tableName=response_attachments		\N	3.9.0	\N	\N	0324975463
20200706084725-audit-1	Chris	config/liquibase/changelog/20200706084725_added_entity_ResponseAttachment.xml	2020-09-17 09:42:55.676772	4	EXECUTED	8:469e9698e0bf333edbc103232649a340	addColumn tableName=response_attachments		\N	3.9.0	\N	\N	0324975463
20200706082003	Chris	config/liquibase/changelog/20200706082003_added_entity_EntityAuditEvent.xml	2020-09-17 09:42:55.699871	5	EXECUTED	8:2cab29b4705cd10834ec5f398cfecafd	createTable tableName=iftmis_entity_audit_event; createIndex indexName=idx_entity_audit_event_entity_id, tableName=iftmis_entity_audit_event; createIndex indexName=idx_entity_audit_event_entity_type, tableName=iftmis_entity_audit_event; dropDefaul...		\N	3.9.0	\N	\N	0324975463
20200706081725-1	Chris	config/liquibase/changelog/20200706081725_added_entity_OrganisationUnitLevel.xml	2020-09-17 09:42:55.718479	6	EXECUTED	8:3d790f74ca408d984aa4ab72b10cb1ab	createTable tableName=organisation_unit_levels		\N	3.9.0	\N	\N	0324975463
20200706081725-audit-1	Chris	config/liquibase/changelog/20200706081725_added_entity_OrganisationUnitLevel.xml	2020-09-17 09:42:55.727507	7	EXECUTED	8:68f43d67deaa97e37a2b8e401db0a42c	addColumn tableName=organisation_unit_levels		\N	3.9.0	\N	\N	0324975463
20200706081725-5	chris	config/liquibase/changelog/20200706081725_added_entity_OrganisationUnitLevel.xml	2020-09-17 09:42:55.733767	8	EXECUTED	8:f408c8930cc020472cd0bedf3f189fb2	dropNotNullConstraint columnName=is_inspection_level, tableName=organisation_unit_levels		\N	3.9.0	\N	\N	0324975463
20200706081725-seed-1	chris	config/liquibase/changelog/20200706081725_added_entity_OrganisationUnitLevel.xml	2020-09-17 09:42:55.74167	9	EXECUTED	8:b6e2ce2645e7c64a2405868d944d0f9f	loadData tableName=organisation_unit_levels		\N	3.9.0	\N	\N	0324975463
20200706081825-1	Chris	config/liquibase/changelog/20200706081825_added_entity_OrganisationUnit.xml	2020-09-17 09:42:55.762777	10	EXECUTED	8:bd54e0310007adc298498f2f7c5699e4	createTable tableName=organisation_units		\N	3.9.0	\N	\N	0324975463
20200706081825-audit-1	Chris	config/liquibase/changelog/20200706081825_added_entity_OrganisationUnit.xml	2020-09-17 09:42:55.771929	11	EXECUTED	8:48330071b7d3c4a58734eeecbdbece7f	addColumn tableName=organisation_units		\N	3.9.0	\N	\N	0324975463
20200706081825-seed-1	chris	config/liquibase/changelog/20200706081825_added_entity_OrganisationUnit.xml	2020-09-17 09:42:55.779464	12	EXECUTED	8:963cd8ba8cd96a39afd2094e9edf8d52	loadData tableName=organisation_units		\N	3.9.0	\N	\N	0324975463
20200706081925-1	Chris	config/liquibase/changelog/20200706081925_added_entity_FinancialYear.xml	2020-09-17 09:42:55.793019	13	EXECUTED	8:710c900b4a9d8af6d021615a2dfe3175	createTable tableName=financial_years		\N	3.9.0	\N	\N	0324975463
20200706081925-audit-1	Chris	config/liquibase/changelog/20200706081925_added_entity_FinancialYear.xml	2020-09-17 09:42:55.801127	14	EXECUTED	8:76927419c5f90ee462cf5f24887d1ebe	addColumn tableName=financial_years		\N	3.9.0	\N	\N	0324975463
20200706082025-1	Chris	config/liquibase/changelog/20200706082025_added_entity_Quarter.xml	2020-09-17 09:42:55.81782	15	EXECUTED	8:1bf9b9ed3f992762e179022cdd8a9f88	createTable tableName=quarters		\N	3.9.0	\N	\N	0324975463
20200706082025-audit-1	Chris	config/liquibase/changelog/20200706082025_added_entity_Quarter.xml	2020-09-17 09:42:55.825585	16	EXECUTED	8:0da2882833a907338af9de1eae76b86a	addColumn tableName=quarters		\N	3.9.0	\N	\N	0324975463
20200706082125-1	Chris	config/liquibase/changelog/20200706082125_added_entity_FileResource.xml	2020-09-17 09:42:55.83933	17	EXECUTED	8:71d8251e91499feaf26e40d6678aca1c	createTable tableName=file_resources		\N	3.9.0	\N	\N	0324975463
20200706082125-audit-1	Chris	config/liquibase/changelog/20200706082125_added_entity_FileResource.xml	2020-09-17 09:42:55.848186	18	EXECUTED	8:96a39214a3c878a407aa37fb27647e8d	addColumn tableName=file_resources		\N	3.9.0	\N	\N	0324975463
20200706082225-1	Chris	config/liquibase/changelog/20200706082225_added_entity_GfsCode.xml	2020-09-17 09:42:55.866942	19	EXECUTED	8:f1ee1a86f0a5d8a99d500a1c1472de05	createTable tableName=gfs_codes		\N	3.9.0	\N	\N	0324975463
20200706082225-audit-1	Chris	config/liquibase/changelog/20200706082225_added_entity_GfsCode.xml	2020-09-17 09:42:55.876794	20	EXECUTED	8:ede167f6d62793a2bd8d87a11723e81b	addColumn tableName=gfs_codes		\N	3.9.0	\N	\N	0324975463
20200706082325-1	Chris	config/liquibase/changelog/20200706082325_added_entity_FindingCategory.xml	2020-09-17 09:42:55.894566	21	EXECUTED	8:1086015c44cc025a96f6b21055e3a087	createTable tableName=finding_categories		\N	3.9.0	\N	\N	0324975463
20200706082325-audit-1	Chris	config/liquibase/changelog/20200706082325_added_entity_FindingCategory.xml	2020-09-17 09:42:55.904328	22	EXECUTED	8:34d47615df53cda422e5fc59e9fd9226	addColumn tableName=finding_categories		\N	3.9.0	\N	\N	0324975463
20200706082425-1	Chris	config/liquibase/changelog/20200706082425_added_entity_FindingSubCategory.xml	2020-09-17 09:42:55.923955	23	EXECUTED	8:ad2ce1ec60f99ffa31719c718cbb98e6	createTable tableName=finding_sub_categories		\N	3.9.0	\N	\N	0324975463
20200706082425-audit-1	Chris	config/liquibase/changelog/20200706082425_added_entity_FindingSubCategory.xml	2020-09-17 09:42:55.933266	24	EXECUTED	8:6cc929a97357af8743df2937dcc251b0	addColumn tableName=finding_sub_categories		\N	3.9.0	\N	\N	0324975463
20200706082525-1	Chris	config/liquibase/changelog/20200706082525_added_entity_AuditableArea.xml	2020-09-17 09:42:55.950961	25	EXECUTED	8:fb08f5e86a2dd4244a33bf058e95d073	createTable tableName=auditable_areas		\N	3.9.0	\N	\N	0324975463
20200706082525-audit-1	Chris	config/liquibase/changelog/20200706082525_added_entity_AuditableArea.xml	2020-09-17 09:42:55.956967	26	EXECUTED	8:b6b561ce1291bcaa888650ae826a9739	addColumn tableName=auditable_areas		\N	3.9.0	\N	\N	0324975463
20200706082625-1	Chris	config/liquibase/changelog/20200706082625_added_entity_SubArea.xml	2020-09-17 09:42:55.968209	27	EXECUTED	8:218028a90e9e2a1890f2191c150d230e	createTable tableName=sub_areas		\N	3.9.0	\N	\N	0324975463
20200706082625-audit-1	Chris	config/liquibase/changelog/20200706082625_added_entity_SubArea.xml	2020-09-17 09:42:55.974342	28	EXECUTED	8:89c52c1c5db43ebf94d7035d653d44e3	addColumn tableName=sub_areas		\N	3.9.0	\N	\N	0324975463
20200706082725-1	Chris	config/liquibase/changelog/20200706082725_added_entity_Indicator.xml	2020-09-17 09:42:55.994414	29	EXECUTED	8:65d3025d5591778f22704d89ea8f5df1	createTable tableName=indicators		\N	3.9.0	\N	\N	0324975463
20200706082725-audit-1	Chris	config/liquibase/changelog/20200706082725_added_entity_Indicator.xml	2020-09-17 09:42:56.000419	30	EXECUTED	8:5f3a9b5802c9481714e0ed7860b5cf36	addColumn tableName=indicators		\N	3.9.0	\N	\N	0324975463
20200706082825-1	Chris	config/liquibase/changelog/20200706082825_added_entity_Procedure.xml	2020-09-17 09:42:56.014111	31	EXECUTED	8:93d7fdb1a2dd203d4affd819f3f9c6b8	createTable tableName=procedures		\N	3.9.0	\N	\N	0324975463
20200706082825-audit-1	Chris	config/liquibase/changelog/20200706082825_added_entity_Procedure.xml	2020-09-17 09:42:56.020239	32	EXECUTED	8:b49bee78cb483f45a9e3111056286303	addColumn tableName=procedures		\N	3.9.0	\N	\N	0324975463
20200706082925-1	Chris	config/liquibase/changelog/20200706082925_added_entity_Notification.xml	2020-09-17 09:42:56.031969	33	EXECUTED	8:b71e17208041d554e2b71e2b93522667	createTable tableName=notifications		\N	3.9.0	\N	\N	0324975463
20200706082925-audit-1	Chris	config/liquibase/changelog/20200706082925_added_entity_Notification.xml	2020-09-17 09:42:56.037799	34	EXECUTED	8:def1f8c916babc4cdba530225729ec58	addColumn tableName=notifications		\N	3.9.0	\N	\N	0324975463
20200706083025-1	Chris	config/liquibase/changelog/20200706083025_added_entity_Inspection.xml	2020-09-17 09:42:56.048791	35	EXECUTED	8:13a5a3e81480eb6a47c793bdb5ac98a6	createTable tableName=inspections		\N	3.9.0	\N	\N	0324975463
20200706083025-audit-1	Chris	config/liquibase/changelog/20200706083025_added_entity_Inspection.xml	2020-09-17 09:42:56.0608	36	EXECUTED	8:ac675322831a48c6f4d1307a7b364bcc	addColumn tableName=inspections		\N	3.9.0	\N	\N	0324975463
20200706083125-1	Chris	config/liquibase/changelog/20200706083125_added_entity_InspectionMember.xml	2020-09-17 09:42:56.071926	37	EXECUTED	8:80f4dfb25f6a0d60937e97ee659b6c1d	createTable tableName=inspection_members		\N	3.9.0	\N	\N	0324975463
20200706083125-audit-1	Chris	config/liquibase/changelog/20200706083125_added_entity_InspectionMember.xml	2020-09-17 09:42:56.084075	38	EXECUTED	8:be5d99c298a890d19e389834d797c4d4	addColumn tableName=inspection_members		\N	3.9.0	\N	\N	0324975463
20200706083125-4	chris	config/liquibase/changelog/20200706083125_added_entity_InspectionMember.xml	2020-09-17 09:42:56.089886	39	EXECUTED	8:95851f03bbeeebb95229541122abfacc	addColumn tableName=inspection_members		\N	3.9.0	\N	\N	0324975463
20200706083125-6	chris	config/liquibase/changelog/20200706083125_added_entity_InspectionMember.xml	2020-09-17 09:42:56.097953	40	EXECUTED	8:ed45138f8fb6af6b6eeb249088a8e50e	dropColumn columnName=full_name, tableName=inspection_members; dropColumn columnName=email, tableName=inspection_members		\N	3.9.0	\N	\N	0324975463
20200706083225-1	Chris	config/liquibase/changelog/20200706083225_added_entity_Meeting.xml	2020-09-17 09:42:56.113057	41	EXECUTED	8:cabf2321a08f647579e74ccef44d2d11	createTable tableName=team_meetings		\N	3.9.0	\N	\N	0324975463
20200706083225-audit-1	Chris	config/liquibase/changelog/20200706083225_added_entity_Meeting.xml	2020-09-17 09:42:56.121488	42	EXECUTED	8:a4d56b4740e7f33526c271c13ff3a702	addColumn tableName=team_meetings		\N	3.9.0	\N	\N	0324975463
20200706083325-1	Chris	config/liquibase/changelog/20200706083325_added_entity_MeetingMember.xml	2020-09-17 09:42:56.135171	43	EXECUTED	8:d5d7891f2621ed5cba007b1e2116a6d9	createTable tableName=meeting_members		\N	3.9.0	\N	\N	0324975463
20200706083325-audit-1	Chris	config/liquibase/changelog/20200706083325_added_entity_MeetingMember.xml	2020-09-17 09:42:56.143388	44	EXECUTED	8:ba52e00c262a6d660b1b69b2d3c3a45c	addColumn tableName=meeting_members		\N	3.9.0	\N	\N	0324975463
20200706083425-1	Chris	config/liquibase/changelog/20200706083425_added_entity_MeetingAgenda.xml	2020-09-17 09:42:56.156967	45	EXECUTED	8:27a69579789f78121198352be4950307	createTable tableName=meeting_agendas		\N	3.9.0	\N	\N	0324975463
20200706083425-audit-1	Chris	config/liquibase/changelog/20200706083425_added_entity_MeetingAgenda.xml	2020-09-17 09:42:56.165176	46	EXECUTED	8:729053f46500faa7e05b1daf26b14f70	addColumn tableName=meeting_agendas		\N	3.9.0	\N	\N	0324975463
20200706083525-1	Chris	config/liquibase/changelog/20200706083525_added_entity_MeetingAttachment.xml	2020-09-17 09:42:56.178437	47	EXECUTED	8:d69961afcf06a058cf6912105313b915	createTable tableName=meeting_attachments		\N	3.9.0	\N	\N	0324975463
20200706083525-audit-1	Chris	config/liquibase/changelog/20200706083525_added_entity_MeetingAttachment.xml	2020-09-17 09:42:56.18697	48	EXECUTED	8:24e0da8994aa08d4987d6a1b497c5d23	addColumn tableName=meeting_attachments		\N	3.9.0	\N	\N	0324975463
20200706083625-1	Chris	config/liquibase/changelog/20200706083625_added_entity_InspectionArea.xml	2020-09-17 09:42:56.201204	49	EXECUTED	8:ac34740dba3b72e84daf662a24d6fb8a	createTable tableName=inspection_areas		\N	3.9.0	\N	\N	0324975463
20200706083625-audit-1	Chris	config/liquibase/changelog/20200706083625_added_entity_InspectionArea.xml	2020-09-17 09:42:56.209913	50	EXECUTED	8:4bcc44ec8e72cb0a11e7f4d88d9a3d69	addColumn tableName=inspection_areas		\N	3.9.0	\N	\N	0324975463
20200706083725-1	Chris	config/liquibase/changelog/20200706083725_added_entity_InspectionObjective.xml	2020-09-17 09:42:56.223778	51	EXECUTED	8:046e5d01c662354a8506d7e7f20d40de	createTable tableName=inspection_objectives		\N	3.9.0	\N	\N	0324975463
20200706083725-audit-1	Chris	config/liquibase/changelog/20200706083725_added_entity_InspectionObjective.xml	2020-09-17 09:42:56.232394	52	EXECUTED	8:0d6d54f0c4c5a9061d2b9ee0bfca562e	addColumn tableName=inspection_objectives		\N	3.9.0	\N	\N	0324975463
20200706083825-1	Chris	config/liquibase/changelog/20200706083825_added_entity_InspectionSubArea.xml	2020-09-17 09:42:56.247011	53	EXECUTED	8:52cef1e04fffe6f39726c19f7d8778e2	createTable tableName=inspection_sub_areas		\N	3.9.0	\N	\N	0324975463
20200706083825-audit-1	Chris	config/liquibase/changelog/20200706083825_added_entity_InspectionSubArea.xml	2020-09-17 09:42:56.255155	54	EXECUTED	8:6a558ca7a9b2b0b89ef52dc1686866f1	addColumn tableName=inspection_sub_areas		\N	3.9.0	\N	\N	0324975463
20200706083825-7	chris	config/liquibase/changelog/20200706083825_added_entity_InspectionSubArea.xml	2020-09-17 09:42:56.260965	55	EXECUTED	8:f58e06acf69a2a8220242b8469a73eeb	addColumn tableName=inspection_sub_areas		\N	3.9.0	\N	\N	0324975463
20200706083925-1	Chris	config/liquibase/changelog/20200706083925_added_entity_InspectionIndicator.xml	2020-09-17 09:42:56.275212	56	EXECUTED	8:9a6885633797fb88090e6b892611d8d6	createTable tableName=inspection_indicators		\N	3.9.0	\N	\N	0324975463
20200706083925-audit-1	Chris	config/liquibase/changelog/20200706083925_added_entity_InspectionIndicator.xml	2020-09-17 09:42:56.284581	57	EXECUTED	8:24319a0ed2870ea4e049778a3dd102e5	addColumn tableName=inspection_indicators		\N	3.9.0	\N	\N	0324975463
20200706084025-1	Chris	config/liquibase/changelog/20200706084025_added_entity_InspectionProcedure.xml	2020-09-17 09:42:56.299481	58	EXECUTED	8:b2ff20801579221dd26563766b5114e8	createTable tableName=inspection_procedures		\N	3.9.0	\N	\N	0324975463
20200706084025-audit-1	Chris	config/liquibase/changelog/20200706084025_added_entity_InspectionProcedure.xml	2020-09-17 09:42:56.308263	59	EXECUTED	8:02c738e953a161e7083d3ae70b112fb1	addColumn tableName=inspection_procedures		\N	3.9.0	\N	\N	0324975463
20200706084125-1	Chris	config/liquibase/changelog/20200706084125_added_entity_InspectionWorkDone.xml	2020-09-17 09:42:56.319891	60	EXECUTED	8:c74803009ee2b235576c1ab6f5e267b5	createTable tableName=inspection_work_dones		\N	3.9.0	\N	\N	0324975463
20200706084125-audit-1	Chris	config/liquibase/changelog/20200706084125_added_entity_InspectionWorkDone.xml	2020-09-17 09:42:56.329935	61	EXECUTED	8:dcc92653c1e6b5fcddc164759a55f1f9	addColumn tableName=inspection_work_dones		\N	3.9.0	\N	\N	0324975463
20200706084225-1	Chris	config/liquibase/changelog/20200706084225_added_entity_InspectionFinding.xml	2020-09-17 09:42:56.342767	62	EXECUTED	8:0b35f267044e6e3804b12922599ba237	createTable tableName=inspection_findings		\N	3.9.0	\N	\N	0324975463
20200706084225-audit-1	Chris	config/liquibase/changelog/20200706084225_added_entity_InspectionFinding.xml	2020-09-17 09:42:56.348956	63	EXECUTED	8:f72a71ebd44f9260c936798bd57a33fd	addColumn tableName=inspection_findings		\N	3.9.0	\N	\N	0324975463
20200706084325-1	Chris	config/liquibase/changelog/20200706084325_added_entity_InspectionRecommendation.xml	2020-09-17 09:42:56.360243	64	EXECUTED	8:b1210b923e74dd2cd2e0ec60622774d8	createTable tableName=inspection_recommendations		\N	3.9.0	\N	\N	0324975463
20200706084325-audit-1	Chris	config/liquibase/changelog/20200706084325_added_entity_InspectionRecommendation.xml	2020-09-17 09:42:56.366473	65	EXECUTED	8:87952e8662cc232632c8b25365e39bec	addColumn tableName=inspection_recommendations		\N	3.9.0	\N	\N	0324975463
20200706084425-1	Chris	config/liquibase/changelog/20200706084425_added_entity_Finding.xml	2020-09-17 09:42:56.391613	66	EXECUTED	8:e9930367962df07a24906c4910ca8dcb	createTable tableName=findings		\N	3.9.0	\N	\N	0324975463
20200706084425-audit-1	Chris	config/liquibase/changelog/20200706084425_added_entity_Finding.xml	2020-09-17 09:42:56.399778	67	EXECUTED	8:8b2bef6392d5568e633210459a842167	addColumn tableName=findings		\N	3.9.0	\N	\N	0324975463
20200706084525-1	Chris	config/liquibase/changelog/20200706084525_added_entity_FindingRecommendation.xml	2020-09-17 09:42:56.415227	68	EXECUTED	8:c5430153769f3c8182cef96a4cecc2fd	createTable tableName=finding_recommendations		\N	3.9.0	\N	\N	0324975463
20200706084525-audit-1	Chris	config/liquibase/changelog/20200706084525_added_entity_FindingRecommendation.xml	2020-09-17 09:42:56.425395	69	EXECUTED	8:1f54d9e9a5bc071c75a4e5e52b369b0c	addColumn tableName=finding_recommendations		\N	3.9.0	\N	\N	0324975463
20200706084625-1	Chris	config/liquibase/changelog/20200706084625_added_entity_FindingResponse.xml	2020-09-17 09:42:56.445969	70	EXECUTED	8:3e0567f2bccdc43610ab496f9659e742	createTable tableName=finding_responses		\N	3.9.0	\N	\N	0324975463
20200706084625-audit-1	Chris	config/liquibase/changelog/20200706084625_added_entity_FindingResponse.xml	2020-09-17 09:42:56.451689	71	EXECUTED	8:85e7368232afcfe32547f84bf5ad0154	addColumn tableName=finding_responses		\N	3.9.0	\N	\N	0324975463
20200713104839-1	chris	config/liquibase/changelog/20200713104839_added_entity_RiskRank.xml	2020-09-17 09:42:56.463657	72	EXECUTED	8:9770435edea98764606a5db7482e7619	createTable tableName=risk_ranks		\N	3.9.0	\N	\N	0324975463
20200713104839-audit-1	chris	config/liquibase/changelog/20200713104839_added_entity_RiskRank.xml	2020-09-17 09:42:56.472302	73	EXECUTED	8:6e6fc6758e0657e4aff76d79d91571fd	addColumn tableName=risk_ranks		\N	3.9.0	\N	\N	0324975463
20200713104939-1	chris	config/liquibase/changelog/20200713104939_added_entity_RiskRegister.xml	2020-09-17 09:42:56.486511	74	EXECUTED	8:4f26d0a723df5546bad027f5c14193d0	createTable tableName=risk_registers		\N	3.9.0	\N	\N	0324975463
20200713104939-audit-1	chris	config/liquibase/changelog/20200713104939_added_entity_RiskRegister.xml	2020-09-17 09:42:56.498359	75	EXECUTED	8:a43171e32fd558f59e11ea816b3df5f2	addColumn tableName=risk_registers		\N	3.9.0	\N	\N	0324975463
20200713105039-1	chris	config/liquibase/changelog/20200713105039_added_entity_Objective.xml	2020-09-17 09:42:56.51634	76	EXECUTED	8:9a37417f957fd78b8f8beb94c04005d3	createTable tableName=objectives		\N	3.9.0	\N	\N	0324975463
20200713105039-audit-1	chris	config/liquibase/changelog/20200713105039_added_entity_Objective.xml	2020-09-17 09:42:56.525027	77	EXECUTED	8:5e4a5b8dc8154f1a9768886f6045b7f8	addColumn tableName=objectives		\N	3.9.0	\N	\N	0324975463
20200713105139-1	chris	config/liquibase/changelog/20200713105139_added_entity_RiskCategory.xml	2020-09-17 09:42:56.542106	78	EXECUTED	8:bca790c0c62641a2f932114c7f0cd7d1	createTable tableName=risk_categories		\N	3.9.0	\N	\N	0324975463
20200713105139-audit-1	chris	config/liquibase/changelog/20200713105139_added_entity_RiskCategory.xml	2020-09-17 09:42:56.550639	79	EXECUTED	8:8666761532fabb9087e249f80a505f45	addColumn tableName=risk_categories		\N	3.9.0	\N	\N	0324975463
20200713105239-1	chris	config/liquibase/changelog/20200713105239_added_entity_Risk.xml	2020-09-17 09:42:56.568354	80	EXECUTED	8:f0d3b1635ad98adad3780d1256e5b5ae	createTable tableName=risks		\N	3.9.0	\N	\N	0324975463
20200713105239-audit-1	chris	config/liquibase/changelog/20200713105239_added_entity_Risk.xml	2020-09-17 09:42:56.576677	81	EXECUTED	8:858d57613fff0823212a8fd6ff5af9a3	addColumn tableName=risks		\N	3.9.0	\N	\N	0324975463
20200713105339-1	chris	config/liquibase/changelog/20200713105339_added_entity_RiskRating.xml	2020-09-17 09:42:56.591155	82	EXECUTED	8:8bc478e0914f6ddc7bb9433928b5adc8	createTable tableName=risk_ratings		\N	3.9.0	\N	\N	0324975463
20200713105339-audit-1	chris	config/liquibase/changelog/20200713105339_added_entity_RiskRating.xml	2020-09-17 09:42:56.59905	83	EXECUTED	8:8b93d8156316d041cc75a10e5ad24120	addColumn tableName=risk_ratings		\N	3.9.0	\N	\N	0324975463
20200718085145-1	chris	config/liquibase/changelog/20200718085145_added_entity_InspectionPlan.xml	2020-09-17 09:42:56.609206	84	EXECUTED	8:d031081348dd79bfd37be793052239a6	createTable tableName=inspection_plans		\N	3.9.0	\N	\N	0324975463
20200718085145-audit-1	jhipster-entity-audit	config/liquibase/changelog/20200718085145_added_entity_InspectionPlan.xml	2020-09-17 09:42:56.617098	85	EXECUTED	8:732f2ebd3b4b2417a15af93fbd7fb100	addColumn tableName=inspection_plans		\N	3.9.0	\N	\N	0324975463
20200718085245-1	chris	config/liquibase/changelog/20200718085245_added_entity_InspectionActivity.xml	2020-09-17 09:42:56.627026	86	EXECUTED	8:471504892e9a4e93cd60a521a5744b19	createTable tableName=inspection_activities		\N	3.9.0	\N	\N	0324975463
20200718085245-1-relations	chris	config/liquibase/changelog/20200718085245_added_entity_InspectionActivity.xml	2020-09-17 09:42:56.643018	87	EXECUTED	8:d545a605cea6b162d2f76700e6d52e80	createTable tableName=inspection_activities_risks; addPrimaryKey tableName=inspection_activities_risks; createTable tableName=inspection_activities_organisation_units; addPrimaryKey tableName=inspection_activities_organisation_units		\N	3.9.0	\N	\N	0324975463
20200718085245-audit-1	jhipster-entity-audit	config/liquibase/changelog/20200718085245_added_entity_InspectionActivity.xml	2020-09-17 09:42:56.65158	88	EXECUTED	8:966c31d1b95c52dd4bcdc3f83e63b15a	addColumn tableName=inspection_activities		\N	3.9.0	\N	\N	0324975463
20200718085345-1	chris	config/liquibase/changelog/20200718085345_added_entity_InspectionActivityQuarter.xml	2020-09-17 09:42:56.661323	89	EXECUTED	8:bd258308b82903c9f54c1173bfe7625e	createTable tableName=inspection_activity_quarters		\N	3.9.0	\N	\N	0324975463
20200718085345-audit-1	jhipster-entity-audit	config/liquibase/changelog/20200718085345_added_entity_InspectionActivityQuarter.xml	2020-09-17 09:42:56.670521	90	EXECUTED	8:29437dd3a295b7f7abc6f389948a9f2b	addColumn tableName=inspection_activity_quarters		\N	3.9.0	\N	\N	0324975463
20200721070631-1	jhipster	config/liquibase/changelog/20200721070631_added_entity_InspectionBudget.xml	2020-09-17 09:42:56.681232	91	EXECUTED	8:788d461ac7062aa0222e6f647ca48490	createTable tableName=inspection_budget		\N	3.9.0	\N	\N	0324975463
20200721070631-1-relations	jhipster	config/liquibase/changelog/20200721070631_added_entity_InspectionBudget.xml	2020-09-17 09:42:56.68477	92	EXECUTED	8:d41d8cd98f00b204e9800998ecf8427e	empty		\N	3.9.0	\N	\N	0324975463
20200721070631-audit-1	jhipster-entity-audit	config/liquibase/changelog/20200721070631_added_entity_InspectionBudget.xml	2020-09-17 09:42:56.693388	93	EXECUTED	8:6fd975d443b8f2b6a63592033df80c0d	addColumn tableName=inspection_budget		\N	3.9.0	\N	\N	0324975463
20200706084725-2	Chris	config/liquibase/changelog/20200706084725_added_entity_constraints_ResponseAttachment.xml	2020-09-17 09:42:56.703706	94	EXECUTED	8:42b8eecaa70a5f69de0a182f36c18157	addForeignKeyConstraint baseTableName=response_attachments, constraintName=fk_response_attachments_attachment_id, referencedTableName=file_resources; addForeignKeyConstraint baseTableName=response_attachments, constraintName=fk_response_attachment...		\N	3.9.0	\N	\N	0324975463
20200706081825-2	Chris	config/liquibase/changelog/20200706081825_added_entity_constraints_OrganisationUnit.xml	2020-09-17 09:42:56.71414	95	EXECUTED	8:e9d2f45d7f6bb12f8e74139bf4fe4f91	addForeignKeyConstraint baseTableName=organisation_units, constraintName=fk_organisation_units_organisation_unit_level_id, referencedTableName=organisation_unit_levels; addForeignKeyConstraint baseTableName=organisation_units, constraintName=fk_or...		\N	3.9.0	\N	\N	0324975463
20200706082025-2	Chris	config/liquibase/changelog/20200706082025_added_entity_constraints_Quarter.xml	2020-09-17 09:42:56.722397	96	EXECUTED	8:1dc35034bd62ca38612b0afe2e5d92bd	addForeignKeyConstraint baseTableName=quarters, constraintName=fk_quarters_financial_year_id, referencedTableName=financial_years		\N	3.9.0	\N	\N	0324975463
20200706082625-2	Chris	config/liquibase/changelog/20200706082625_added_entity_constraints_SubArea.xml	2020-09-17 09:42:56.730232	97	EXECUTED	8:594b6b1755c79134cb515bad5b3e6bec	addForeignKeyConstraint baseTableName=sub_areas, constraintName=fk_sub_areas_area_id, referencedTableName=auditable_areas		\N	3.9.0	\N	\N	0324975463
20200706082725-2	Chris	config/liquibase/changelog/20200706082725_added_entity_constraints_Indicator.xml	2020-09-17 09:42:56.738394	98	EXECUTED	8:5a6dda7ce7f7abef5fce4d2ca3dc68e7	addForeignKeyConstraint baseTableName=indicators, constraintName=fk_indicators_sub_area_id, referencedTableName=auditable_areas		\N	3.9.0	\N	\N	0324975463
	Chris	config/liquibase/changelog/20200706082725_added_entity_constraints_Indicator.xml	2020-09-17 09:42:56.748103	99	EXECUTED	8:1959527ac077fd745c7ce0e87ab2ac9b	dropForeignKeyConstraint baseTableName=indicators, constraintName=fk_indicators_sub_area_id; addForeignKeyConstraint baseTableName=indicators, constraintName=fk_indicators_sub_area_id, referencedTableName=sub_areas		\N	3.9.0	\N	\N	0324975463
20200706082825-2	Chris	config/liquibase/changelog/20200706082825_added_entity_constraints_Procedure.xml	2020-09-17 09:42:56.755804	100	EXECUTED	8:751888704c0b3f10ed3e62e1ad5d35df	addForeignKeyConstraint baseTableName=procedures, constraintName=fk_procedures_indicator_id, referencedTableName=indicators		\N	3.9.0	\N	\N	0324975463
20200706083025-2	Chris	config/liquibase/changelog/20200706083025_added_entity_constraints_Inspection.xml	2020-09-17 09:42:56.766348	101	EXECUTED	8:9c213278cd5c6c68c733e3e5aab6938a	addForeignKeyConstraint baseTableName=inspections, constraintName=fk_inspections_financial_year_id, referencedTableName=financial_years; addForeignKeyConstraint baseTableName=inspections, constraintName=fk_inspections_organisation_unit_id, referen...		\N	3.9.0	\N	\N	0324975463
20200706083125-2	Chris	config/liquibase/changelog/20200706083125_added_entity_constraints_InspectionMember.xml	2020-09-17 09:42:56.779062	102	EXECUTED	8:048972218a2b9d431c2ed8d63130b4fc	addForeignKeyConstraint baseTableName=inspection_members, constraintName=fk_inspection_members_user_id, referencedTableName=users; addForeignKeyConstraint baseTableName=inspection_members, constraintName=fk_inspection_members_letter_attachment_id,...		\N	3.9.0	\N	\N	0324975463
20200706083125-5	chris	config/liquibase/changelog/20200706083125_added_entity_constraints_InspectionMember.xml	2020-09-17 09:42:56.787022	103	EXECUTED	8:f3164d4297a58e9d8d537f267093fc56	addForeignKeyConstraint baseTableName=inspection_members, constraintName=fk_inspection_members_inspection_id, referencedTableName=inspections		\N	3.9.0	\N	\N	0324975463
20200706083225-2	Chris	config/liquibase/changelog/20200706083225_added_entity_constraints_Meeting.xml	2020-09-17 09:42:56.79464	104	EXECUTED	8:2425b6377b5e614be810958edb2eb9fe	addForeignKeyConstraint baseTableName=team_meetings, constraintName=fk_team_meetings_inspection_id, referencedTableName=inspections		\N	3.9.0	\N	\N	0324975463
20200706083325-2	Chris	config/liquibase/changelog/20200706083325_added_entity_constraints_MeetingMember.xml	2020-09-17 09:42:56.80195	105	EXECUTED	8:92cc2e5e28ee02b3d9604d93719105d8	addForeignKeyConstraint baseTableName=meeting_members, constraintName=fk_meeting_members_meeting_id, referencedTableName=team_meetings		\N	3.9.0	\N	\N	0324975463
20200706083425-2	Chris	config/liquibase/changelog/20200706083425_added_entity_constraints_MeetingAgenda.xml	2020-09-17 09:42:56.80929	106	EXECUTED	8:f5fd0dea30d82c062d8473a63d871c77	addForeignKeyConstraint baseTableName=meeting_agendas, constraintName=fk_meeting_agendas_meeting_id, referencedTableName=team_meetings		\N	3.9.0	\N	\N	0324975463
20200706083525-2	Chris	config/liquibase/changelog/20200706083525_added_entity_constraints_MeetingAttachment.xml	2020-09-17 09:42:56.819137	107	EXECUTED	8:ab038023f63f8959405b207ab4cd7cf5	addForeignKeyConstraint baseTableName=meeting_attachments, constraintName=fk_meeting_attachments_meeting_id, referencedTableName=team_meetings; addForeignKeyConstraint baseTableName=meeting_attachments, constraintName=fk_meeting_attachments_attach...		\N	3.9.0	\N	\N	0324975463
20200706083625-2	Chris	config/liquibase/changelog/20200706083625_added_entity_constraints_InspectionArea.xml	2020-09-17 09:42:56.829152	108	EXECUTED	8:f1902d01924b4105584cc88ee769b2a3	addForeignKeyConstraint baseTableName=inspection_areas, constraintName=fk_inspection_areas_inspection_id, referencedTableName=inspections; addForeignKeyConstraint baseTableName=inspection_areas, constraintName=fk_inspection_areas_auditable_area_id...		\N	3.9.0	\N	\N	0324975463
20200706083725-2	Chris	config/liquibase/changelog/20200706083725_added_entity_constraints_InspectionObjective.xml	2020-09-17 09:42:56.837857	109	EXECUTED	8:4f6f5411006b971bc246228903fd79cd	addForeignKeyConstraint baseTableName=inspection_objectives, constraintName=fk_inspection_objectives_inspection_area_id, referencedTableName=inspection_areas		\N	3.9.0	\N	\N	0324975463
20200706083825-2	Chris	config/liquibase/changelog/20200706083825_added_entity_constraints_InspectionSubArea.xml	2020-09-17 09:42:56.848198	110	EXECUTED	8:8268203de327661ccbea9e463ee903d9	addForeignKeyConstraint baseTableName=inspection_sub_areas, constraintName=fk_inspection_sub_areas_inspection_objective_id, referencedTableName=inspection_objectives; addForeignKeyConstraint baseTableName=inspection_sub_areas, constraintName=fk_in...		\N	3.9.0	\N	\N	0324975463
20200706083825-6	chris	config/liquibase/changelog/20200706083825_added_entity_constraints_InspectionSubArea.xml	2020-09-17 09:42:56.859113	111	EXECUTED	8:765c90911b3d114f85abb74c3dca84df	dropColumn columnName=inspection_objective_id, tableName=inspection_sub_areas; addColumn tableName=inspection_sub_areas; addForeignKeyConstraint baseTableName=inspection_sub_areas, constraintName=fk_inspection_sub_area_area_id, referencedTableName...		\N	3.9.0	\N	\N	0324975463
20200706083825	chris	config/liquibase/changelog/20200706083825_added_entity_constraints_InspectionSubArea.xml	2020-09-17 09:42:56.865676	112	EXECUTED	8:d5a3102113de9accab7ab51aabb332c0	dropColumn columnName=name, tableName=inspection_sub_areas		\N	3.9.0	\N	\N	0324975463
20200706083925-2	Chris	config/liquibase/changelog/20200706083925_added_entity_constraints_InspectionIndicator.xml	2020-09-17 09:42:56.876353	113	EXECUTED	8:f8571e4d32bfd2771af8463c31a72026	addForeignKeyConstraint baseTableName=inspection_indicators, constraintName=fk_inspection_indicators_inspection_sub_area_id, referencedTableName=inspection_sub_areas; addForeignKeyConstraint baseTableName=inspection_indicators, constraintName=fk_i...		\N	3.9.0	\N	\N	0324975463
20200706084025-2	Chris	config/liquibase/changelog/20200706084025_added_entity_constraints_InspectionProcedure.xml	2020-09-17 09:42:56.886747	114	EXECUTED	8:83ba60a7d12de19844843953cf121d0b	addForeignKeyConstraint baseTableName=inspection_procedures, constraintName=fk_inspection_procedures_inspection_indicator_id, referencedTableName=inspection_indicators; addForeignKeyConstraint baseTableName=inspection_procedures, constraintName=fk...		\N	3.9.0	\N	\N	0324975463
20200706084125-2	Chris	config/liquibase/changelog/20200706084125_added_entity_constraints_InspectionWorkDone.xml	2020-09-17 09:42:56.894896	115	EXECUTED	8:ac039940c6d3394aad2ea6a308edd85b	addForeignKeyConstraint baseTableName=inspection_work_dones, constraintName=fk_inspection_work_dones_procedure_id, referencedTableName=inspection_procedures		\N	3.9.0	\N	\N	0324975463
20200706084225-2	Chris	config/liquibase/changelog/20200706084225_added_entity_constraints_InspectionFinding.xml	2020-09-17 09:42:56.90769	116	EXECUTED	8:118c828193e54fb7c5230abf259b7ca8	addForeignKeyConstraint baseTableName=inspection_findings, constraintName=fk_inspection_findings_work_done_id, referencedTableName=inspection_work_dones; addForeignKeyConstraint baseTableName=inspection_findings, constraintName=fk_inspection_findi...		\N	3.9.0	\N	\N	0324975463
20200706084425-2	Chris	config/liquibase/changelog/20200706084425_added_entity_constraints_Finding.xml	2020-09-17 09:42:56.916322	117	EXECUTED	8:579b0890c6be8c284eb042316b7ff058	addForeignKeyConstraint baseTableName=findings, constraintName=fk_findings_organisation_unit_id, referencedTableName=organisation_units		\N	3.9.0	\N	\N	0324975463
20200706084525-2	Chris	config/liquibase/changelog/20200706084525_added_entity_constraints_FindingRecommendation.xml	2020-09-17 09:42:56.924729	118	EXECUTED	8:7774ee1da0d2433e0b3858400ea1543c	addForeignKeyConstraint baseTableName=finding_recommendations, constraintName=fk_finding_recommendations_finding_id, referencedTableName=findings		\N	3.9.0	\N	\N	0324975463
20200706084625-2	Chris	config/liquibase/changelog/20200706084625_added_entity_constraints_FindingResponse.xml	2020-09-17 09:42:56.933382	119	EXECUTED	8:c9725431d1e4706699d746a631b925cd	addForeignKeyConstraint baseTableName=finding_responses, constraintName=fk_finding_responses_recommendation_id, referencedTableName=finding_recommendations		\N	3.9.0	\N	\N	0324975463
20200713105239-2	chris	config/liquibase/changelog/20200713105239_added_entity_constraints_Risk.xml	2020-09-17 09:42:56.949106	120	EXECUTED	8:9c0303bb9064660da746ea1d9b0ed80f	addForeignKeyConstraint baseTableName=risks, constraintName=fk_risks_risk_register_id, referencedTableName=risk_registers; addForeignKeyConstraint baseTableName=risks, constraintName=fk_risks_objective_id, referencedTableName=objectives; addForeig...		\N	3.9.0	\N	\N	0324975463
20200713105339-2	chris	config/liquibase/changelog/20200713105339_added_entity_constraints_RiskRating.xml	2020-09-17 09:42:56.95718	121	EXECUTED	8:65a3455e13e3892f3373b9f76476a152	addForeignKeyConstraint baseTableName=risk_ratings, constraintName=fk_risk_ratings_risk_id, referencedTableName=risks		\N	3.9.0	\N	\N	0324975463
20200713104939-2	chris	config/liquibase/changelog/20200713104939_added_entity_constraints_RiskRegister.xml	2020-09-17 09:42:56.966368	122	EXECUTED	8:e84160fe8fd6188af7b837e10f32b0d7	addForeignKeyConstraint baseTableName=risk_registers, constraintName=fk_risk_registers_organisation_unit_id, referencedTableName=organisation_units; addForeignKeyConstraint baseTableName=risk_registers, constraintName=fk_risk_registers_financial_y...		\N	3.9.0	\N	\N	0324975463
20200718085245-2	chris	config/liquibase/changelog/20200718085245_added_entity_constraints_InspectionActivity.xml	2020-09-17 09:42:56.990901	123	EXECUTED	8:d5f9a3f8e6882d82d760177e246ca083	addForeignKeyConstraint baseTableName=inspection_activities, constraintName=fk_inspection_activities_inspection_plan_id, referencedTableName=inspection_plans; addForeignKeyConstraint baseTableName=inspection_activities, constraintName=fk_inspectio...		\N	3.9.0	\N	\N	0324975463
20200718085345-2	chris	config/liquibase/changelog/20200718085345_added_entity_constraints_InspectionActivityQuarter.xml	2020-09-17 09:42:57.00356	124	EXECUTED	8:53ec8624a28196336c524dbb2c032915	addForeignKeyConstraint baseTableName=inspection_activity_quarters, constraintName=fk_inspection_activity_quarters_activity_id, referencedTableName=inspection_activities; addForeignKeyConstraint baseTableName=inspection_activity_quarters, constrai...		\N	3.9.0	\N	\N	0324975463
20200718085145-2	chris	config/liquibase/changelog/20200718085145_added_entity_constraints_InspectionPlan.xml	2020-09-17 09:42:57.015191	125	EXECUTED	8:7d435e907477f1350d0c7ec13bfc3f2a	addForeignKeyConstraint baseTableName=inspection_plans, constraintName=fk_inspection_plans_organisation_unit_id, referencedTableName=organisation_units; addForeignKeyConstraint baseTableName=inspection_plans, constraintName=fk_inspection_plans_fin...		\N	3.9.0	\N	\N	0324975463
20200721070631-2	jhipster	config/liquibase/changelog/20200721070631_added_entity_constraints_InspectionBudget.xml	2020-09-17 09:42:57.026976	126	EXECUTED	8:3dad8f114d06b3058422fecc54205a98	addForeignKeyConstraint baseTableName=inspection_budget, constraintName=fk_inspection_budget_gfs_code_id, referencedTableName=gfs_codes; addForeignKeyConstraint baseTableName=inspection_budget, constraintName=fk_inspection_budget_inspection_id, re...		\N	3.9.0	\N	\N	0324975463
20200721070632-1	chris	config/liquibase/changelog/20200721070632_remove_InspectionObjective.xml	2020-09-17 09:42:57.036797	127	EXECUTED	8:ea9e085e80df8964147ec0785876a55c	dropTable tableName=inspection_objectives		\N	3.9.0	\N	\N	0324975463
add_ou_to_user	chris	config/liquibase/changelog/20200721070633_add_ou_to_user.xml	2020-09-17 09:42:57.043626	128	EXECUTED	8:b9680f02fe0e1455015f76c39d9b9186	addColumn tableName=users; addForeignKeyConstraint baseTableName=users, constraintName=fk_uo, referencedTableName=organisation_units		\N	3.9.0	\N	\N	0324975463
add_closed_to_financial_years	kachinga	config/liquibase/changelog/20200721070634_add_closed_to_financial_year.xml	2020-09-17 09:42:57.050285	129	EXECUTED	8:77b58276b0d8b9ea9df99ef4eb01b236	addColumn tableName=financial_years		\N	3.9.0	\N	\N	0324975463
remove_risk_owner_id	kachinga	config/liquibase/changelog/20200721070638_drop_risk_owner_id_from_risks.xml	2020-09-17 14:36:40.272048	130	EXECUTED	8:9674c9edd1b50951f4678affa1a76a0b	dropColumn tableName=risks		\N	3.9.0	\N	\N	0342600127
20200706082425-1	Kachinga	config/liquibase/changelog/20200918082425_added_entity_clusters.xml	2020-09-18 09:30:50.814682	131	EXECUTED	8:3dc474166d24ce3dc62badeed38ff7a5	createTable tableName=clusters		\N	3.9.0	\N	\N	0410650675
20200706082425-audit-1	Kachinga	config/liquibase/changelog/20200918082425_added_entity_clusters.xml	2020-09-18 09:30:50.826365	132	EXECUTED	8:0e1af4e7c410926789cea94ddf1e908e	addColumn tableName=clusters		\N	3.9.0	\N	\N	0410650675
20200918105233-1	kachinga	config/liquibase/changelog/20200918105233_added_entity_ClusterAuditReport.xml	2020-09-18 09:52:16.150015	133	EXECUTED	8:32889f6c6d9f51fa5cce699f7ad92f48	createTable tableName=cluster_reports		\N	3.9.0	\N	\N	0411935994
20200918105236-audit-1	kachinga	config/liquibase/changelog/20200918105233_added_entity_ClusterAuditReport.xml	2020-09-18 09:52:53.42918	134	EXECUTED	8:8d3874e9b3b7ba034dfd105b418aeebd	addColumn tableName=cluster_reports		\N	3.9.0	\N	\N	0411973275
20200918105240-2	kachinga	config/liquibase/changelog/20200918105239_added_entity_constraints_ClusterReport.xml	2020-09-18 09:52:53.439304	135	EXECUTED	8:42c9d7de63cdcd55f08ba17a2a12f29d	addForeignKeyConstraint baseTableName=cluster_reports, constraintName=fk_cluster_reports_cluster_id, referencedTableName=clusters		\N	3.9.0	\N	\N	0411973275
\.


--
-- Data for Name: databasechangeloglock; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.databasechangeloglock (id, locked, lockgranted, lockedby) FROM stdin;
1	f	\N	\N
\.


--
-- Data for Name: file_resources; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.file_resources (id, name, path, content_type, context_md_5, size, is_assigned, type, created_by, created_date, last_modified_by, last_modified_date) FROM stdin;
\.


--
-- Data for Name: financial_years; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.financial_years (id, name, start_date, end_date, is_opened, created_by, created_date, last_modified_by, last_modified_date, closed) FROM stdin;
2501	2019/2020	2019-07-01	2020-07-01	f	admin	2020-09-17 09:22:38.368	admin	2020-09-17 09:22:38.368	t
2502	2020/2021	2020-07-01	2021-06-30	t	admin	2020-09-17 09:23:14.863	admin	2020-09-17 09:23:47.544	f
\.


--
-- Data for Name: finding_categories; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.finding_categories (id, code, name, created_by, created_date, last_modified_by, last_modified_date) FROM stdin;
1701	001	Financial Overview	admin	2020-09-17 08:36:53.619367	admin	2020-09-17 09:33:54.102
2701	002	Issues arising from the audit of Financial Statements	admin	2020-09-17 09:58:16.09	admin	2020-09-17 09:58:16.09
2702	003	Budget preparation and execution	admin	2020-09-17 09:59:05.276	admin	2020-09-17 09:59:05.276
2704	004	Human resources and payroll management	admin	2020-09-17 10:00:24.005	admin	2020-09-17 10:00:24.005
2705	005	Governance issues	admin	2020-09-17 10:01:06.275	admin	2020-09-17 10:01:06.275
2706	006	Revenue management 	admin	2020-09-17 10:01:24.582	admin	2020-09-17 10:01:24.582
2707	022	Expenditure management	admin	2020-09-17 10:04:18.918	admin	2020-09-17 10:04:18.918
2708	023	Missing payment vouchers	admin	2020-09-17 10:05:10.015	admin	2020-09-17 10:05:10.015
2709	024	Questionable EFD receipts	admin	2020-09-17 10:06:10.69	admin	2020-09-17 10:06:10.69
2710	025	Inadequately supported payments	admin	2020-09-17 10:06:35.818	admin	2020-09-17 10:06:35.818
2711	026	20% of GPG funds not transferred to lower levels 	admin	2020-09-17 10:06:54.313	admin	2020-09-17 10:06:54.313
2712	027	Late release of other charges funds	admin	2020-09-17 10:07:07.512	admin	2020-09-17 10:07:07.512
2714	028	Received funds not allocated to cost centres	admin	2020-09-17 10:07:33.671	admin	2020-09-17 10:07:33.671
2715	029	Unapplied payments not cleared	admin	2020-09-17 10:08:08.234	admin	2020-09-17 10:08:08.234
2716	030	Tax withheld not submitted to the commissioner 	admin	2020-09-17 10:08:26.813	admin	2020-09-17 10:08:26.813
2717	031	Payments not supported with electronic fiscal receipts	admin	2020-09-17 10:08:44.06	admin	2020-09-17 10:08:44.06
2718	032	Unretired imprest	admin	2020-09-17 10:08:57.822	admin	2020-09-17 10:08:57.822
\.


--
-- Data for Name: finding_recommendations; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.finding_recommendations (id, description, implementation_status, finding_id, created_by, created_date, last_modified_by, last_modified_date) FROM stdin;
3251	After close supervision revenue was banked to council own source revenue account	IMPLEMENTED	3201	admin	2020-09-18 06:16:35.971	admin	2020-09-18 06:16:35.971
\.


--
-- Data for Name: finding_responses; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.finding_responses (id, source, description, recommendation_id, created_by, created_date, last_modified_by, last_modified_date) FROM stdin;
3351	AUDITOR	Bana weeee	3251	admin	2020-09-18 06:17:02.137	admin	2020-09-18 06:17:02.137
3352	INSPECTOR	weee jamaaa helaaaa	3251	admin	2020-09-18 06:17:12.515	admin	2020-09-18 06:17:12.515
3353	CLIENT	Acha tu	3251	admin	2020-09-18 06:17:19.11	admin	2020-09-18 06:17:19.11
\.


--
-- Data for Name: finding_sub_categories; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.finding_sub_categories (id, code, name, created_by, created_date, last_modified_by, last_modified_date) FROM stdin;
1751	001	Own source revenue not collected	admin	2020-09-17 08:37:23.447774	admin	2020-09-17 09:34:30.397
1752	002	Long Outstanding Receivables	admin	2020-09-17 08:37:34.915427	admin	2020-09-17 09:36:12.467
2651	003	Long Outstanding Payables	admin	2020-09-17 09:36:25.485	admin	2020-09-17 09:36:25.485
2652	004	Financial Statements with material misstatements	admin	2020-09-17 09:37:19.245	admin	2020-09-17 09:37:19.245
2653	005	Outstanding litigation claims against the Council	admin	2020-09-17 09:37:57.214	admin	2020-09-17 09:37:57.214
2654	006	Collected Own source revenue not allocated fr development activities	admin	2020-09-17 09:39:05.77	admin	2020-09-17 09:39:05.77
2655	007	Development Funds used for payments of unbudgeted recurrent activities	admin	2020-09-17 09:39:59.986	admin	2020-09-17 09:39:59.986
2656	008	Reallocation of budgeted funds not approved	admin	2020-09-17 09:40:31.565	admin	2020-09-17 09:40:31.565
2657	009	Payments of funds signed by the officers not within their budgetary provision	admin	2020-09-17 09:41:11.593	admin	2020-09-17 09:41:11.593
2658	010	Expenditures charged to incorrect account codes	admin	2020-09-17 09:41:42.508	admin	2020-09-17 09:41:42.508
2659	011	Officers acting in vacant post for more than six months	admin	2020-09-17 09:42:30.209	admin	2020-09-17 09:42:30.209
2660	012	Inadequate number of staff	admin	2020-09-17 09:42:59.046	admin	2020-09-17 09:42:59.046
2661	013	Delay in approving the promoted staff in the system	admin	2020-09-17 09:43:47.15	admin	2020-09-17 09:43:47.15
2662	014	Unclaimed salaries not remitted to treasury	admin	2020-09-17 09:44:29.98	admin	2020-09-17 09:44:29.98
2663	015	Internal auditor limited access to audit revenue in the LGRCIS	admin	2020-09-17 09:46:58.601	admin	2020-09-17 09:46:58.601
2664	016	Collected Revenue not banked by revenue collectors	admin	2020-09-17 09:47:45.214	admin	2020-09-17 09:47:45.214
2665	017	Expired business license not renewed	admin	2020-09-17 10:01:56.509	admin	2020-09-17 10:01:56.509
2666	018	Inadequate controls over defective POS devices	admin	2020-09-17 10:02:22.561	admin	2020-09-17 10:02:22.561
2667	019	POS Devices in store not registered in the LGRCIS	admin	2020-09-17 10:02:43.441	admin	2020-09-17 10:02:43.441
2668	020	Unjustified revenue collected through offline POS machines  	admin	2020-09-17 10:03:34.173	admin	2020-09-17 10:03:34.173
2669	021	Adjustments, reversal and cancellation of revenue transactions not approved by the accounting officer	admin	2020-09-17 10:03:53.936	admin	2020-09-17 10:03:53.936
\.


--
-- Data for Name: findings; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.findings (id, source, code, description, action_plan_category, is_closed, organisation_unit_id, created_by, created_date, last_modified_by, last_modified_date) FROM stdin;
3201	CAG	001	Revenue collected but not banked timely	LOW	\N	3102	admin	2020-09-18 06:16:35.963	admin	2020-09-18 06:16:35.963
\.


--
-- Data for Name: gfs_codes; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.gfs_codes (id, code, description, created_by, created_date, last_modified_by, last_modified_date) FROM stdin;
\.


--
-- Data for Name: iftmis_entity_audit_event; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.iftmis_entity_audit_event (id, entity_id, entity_type, action, entity_value, commit_version, modified_by, modified_date) FROM stdin;
1101	1051	org.tamisemi.iftmis.domain.FinancialYear	CREATE	{"createdBy":"admin","createdDate":"2020-09-17T06:48:27.705700Z","lastModifiedBy":"admin","lastModifiedDate":"2020-09-17T06:48:27.705700Z","id":1051,"name":"2019/20","startDate":"2019-07-01","endDate":"2020-06-30","isOpened":true,"closed":null}	1	admin	2020-09-17 06:48:27.7057
1102	1052	org.tamisemi.iftmis.domain.FinancialYear	CREATE	{"createdBy":"admin","createdDate":"2020-09-17T06:48:57.057384Z","lastModifiedBy":"admin","lastModifiedDate":"2020-09-17T06:48:57.057384Z","id":1052,"name":"2020/21","startDate":"2020-07-01","endDate":"2021-06-30","isOpened":false,"closed":null}	1	admin	2020-09-17 06:48:57.057384
1151	1052	org.tamisemi.iftmis.domain.FinancialYear	UPDATE	{"createdBy":"admin","createdDate":"2020-09-17T06:48:57.057384Z","lastModifiedBy":"admin","lastModifiedDate":"2020-09-17T06:56:22.943641Z","id":1052,"name":"2020/21","startDate":"2020-07-01","endDate":"2021-06-30","isOpened":true,"closed":false}	2	admin	2020-09-17 06:56:22.943641
1152	1251	org.tamisemi.iftmis.domain.Quarter	CREATE	{"createdBy":"admin","createdDate":"2020-09-17T07:00:38.039333Z","lastModifiedBy":"admin","lastModifiedDate":"2020-09-17T07:00:38.039333Z","id":1251,"code":"Q1","name":"Quarter 1","startDate":"2020-07-01","endDate":"2020-09-30","financialYear":{"createdBy":null,"createdDate":"2020-09-17T07:00:38.028276Z","lastModifiedBy":null,"lastModifiedDate":"2020-09-17T07:00:38.028277Z","id":1052,"name":null,"startDate":null,"endDate":null,"isOpened":null,"closed":null}}	1	admin	2020-09-17 07:00:38.039333
1153	1252	org.tamisemi.iftmis.domain.Quarter	CREATE	{"createdBy":"admin","createdDate":"2020-09-17T07:01:12.649806Z","lastModifiedBy":"admin","lastModifiedDate":"2020-09-17T07:01:12.649806Z","id":1252,"code":"Q2","name":"Quarter 2","startDate":"2020-10-01","endDate":"2020-12-31","financialYear":{"createdBy":null,"createdDate":"2020-09-17T07:01:12.648436Z","lastModifiedBy":null,"lastModifiedDate":"2020-09-17T07:01:12.648438Z","id":1052,"name":null,"startDate":null,"endDate":null,"isOpened":null,"closed":null}}	1	admin	2020-09-17 07:01:12.649806
1154	1253	org.tamisemi.iftmis.domain.Quarter	CREATE	{"createdBy":"admin","createdDate":"2020-09-17T07:02:01.420759Z","lastModifiedBy":"admin","lastModifiedDate":"2020-09-17T07:02:01.420759Z","id":1253,"code":"Q3","name":"Quarter 3","startDate":"2021-01-01","endDate":"2021-03-31","financialYear":{"createdBy":null,"createdDate":"2020-09-17T07:02:01.419817Z","lastModifiedBy":null,"lastModifiedDate":"2020-09-17T07:02:01.419817Z","id":1052,"name":null,"startDate":null,"endDate":null,"isOpened":null,"closed":null}}	1	admin	2020-09-17 07:02:01.420759
1155	1254	org.tamisemi.iftmis.domain.Quarter	CREATE	{"createdBy":"admin","createdDate":"2020-09-17T07:02:28.775402Z","lastModifiedBy":"admin","lastModifiedDate":"2020-09-17T07:02:28.775402Z","id":1254,"code":"Q4","name":"Quarter 4","startDate":"2021-04-01","endDate":"2021-06-30","financialYear":{"createdBy":null,"createdDate":"2020-09-17T07:02:28.773457Z","lastModifiedBy":null,"lastModifiedDate":"2020-09-17T07:02:28.773459Z","id":1052,"name":null,"startDate":null,"endDate":null,"isOpened":null,"closed":null}}	1	admin	2020-09-17 07:02:28.775402
1156	1301	org.tamisemi.iftmis.domain.Objective	CREATE	{"createdBy":"admin","createdDate":"2020-09-17T07:23:37.582556Z","lastModifiedBy":"admin","lastModifiedDate":"2020-09-17T07:23:37.582556Z","id":1301,"code":"001","description":"  To insure LGA Meetings are conducted accordingly and resolutions are implemented timely"}	1	admin	2020-09-17 07:23:37.582556
1157	1302	org.tamisemi.iftmis.domain.Objective	CREATE	{"createdBy":"admin","createdDate":"2020-09-17T07:23:54.782167Z","lastModifiedBy":"admin","lastModifiedDate":"2020-09-17T07:23:54.782167Z","id":1302,"code":"002","description":"To insure Standing committees established accordingly and resolutions are implemented timely"}	1	admin	2020-09-17 07:23:54.782167
1158	1351	org.tamisemi.iftmis.domain.AuditableArea	CREATE	{"createdBy":"admin","createdDate":"2020-09-17T07:27:11.758276Z","lastModifiedBy":"admin","lastModifiedDate":"2020-09-17T07:27:11.758276Z","id":1351,"code":"001","name":"Governance"}	1	admin	2020-09-17 07:27:11.758276
1159	1401	org.tamisemi.iftmis.domain.SubArea	CREATE	{"createdBy":"admin","createdDate":"2020-09-17T07:27:30.137441Z","lastModifiedBy":"admin","lastModifiedDate":"2020-09-17T07:27:30.137441Z","id":1401,"name":"LGA Meetings and proceedings","area":{"createdBy":null,"createdDate":"2020-09-17T07:27:30.078056Z","lastModifiedBy":null,"lastModifiedDate":"2020-09-17T07:27:30.078057Z","id":1351,"code":null,"name":null}}	1	admin	2020-09-17 07:27:30.137441
1160	1402	org.tamisemi.iftmis.domain.SubArea	CREATE	{"createdBy":"admin","createdDate":"2020-09-17T07:27:43.857500Z","lastModifiedBy":"admin","lastModifiedDate":"2020-09-17T07:27:43.857500Z","id":1402,"name":"Standing committees","area":{"createdBy":null,"createdDate":"2020-09-17T07:27:43.854334Z","lastModifiedBy":null,"lastModifiedDate":"2020-09-17T07:27:43.854335Z","id":1351,"code":null,"name":null}}	1	admin	2020-09-17 07:27:43.8575
1161	1403	org.tamisemi.iftmis.domain.SubArea	CREATE	{"createdBy":"admin","createdDate":"2020-09-17T07:28:00.371589Z","lastModifiedBy":"admin","lastModifiedDate":"2020-09-17T07:28:00.371589Z","id":1403,"name":"Organization","area":{"createdBy":null,"createdDate":"2020-09-17T07:28:00.368811Z","lastModifiedBy":null,"lastModifiedDate":"2020-09-17T07:28:00.368812Z","id":1351,"code":null,"name":null}}	1	admin	2020-09-17 07:28:00.371589
1162	1404	org.tamisemi.iftmis.domain.SubArea	CREATE	{"createdBy":"admin","createdDate":"2020-09-17T07:28:12.089998Z","lastModifiedBy":"admin","lastModifiedDate":"2020-09-17T07:28:12.089998Z","id":1404,"name":"Meetings of lower level LGAs","area":{"createdBy":null,"createdDate":"2020-09-17T07:28:12.088353Z","lastModifiedBy":null,"lastModifiedDate":"2020-09-17T07:28:12.088353Z","id":1351,"code":null,"name":null}}	1	admin	2020-09-17 07:28:12.089998
1163	1405	org.tamisemi.iftmis.domain.SubArea	CREATE	{"createdBy":"admin","createdDate":"2020-09-17T07:28:24.010823Z","lastModifiedBy":"admin","lastModifiedDate":"2020-09-17T07:28:24.010823Z","id":1405,"name":"Adoption of by- laws/ legislative processes","area":{"createdBy":null,"createdDate":"2020-09-17T07:28:24.009466Z","lastModifiedBy":null,"lastModifiedDate":"2020-09-17T07:28:24.009466Z","id":1351,"code":null,"name":null}}	1	admin	2020-09-17 07:28:24.010823
1164	1406	org.tamisemi.iftmis.domain.SubArea	CREATE	{"createdBy":"admin","createdDate":"2020-09-17T07:28:34.527780Z","lastModifiedBy":"admin","lastModifiedDate":"2020-09-17T07:28:34.527780Z","id":1406,"name":"Records Management","area":{"createdBy":null,"createdDate":"2020-09-17T07:28:34.526459Z","lastModifiedBy":null,"lastModifiedDate":"2020-09-17T07:28:34.526459Z","id":1351,"code":null,"name":null}}	1	admin	2020-09-17 07:28:34.52778
1165	1352	org.tamisemi.iftmis.domain.AuditableArea	CREATE	{"createdBy":"admin","createdDate":"2020-09-17T07:28:54.931754Z","lastModifiedBy":"admin","lastModifiedDate":"2020-09-17T07:28:54.931754Z","id":1352,"code":"002","name":"Revenue and assets"}	1	admin	2020-09-17 07:28:54.931754
1166	1407	org.tamisemi.iftmis.domain.SubArea	CREATE	{"createdBy":"admin","createdDate":"2020-09-17T07:29:08.379215Z","lastModifiedBy":"admin","lastModifiedDate":"2020-09-17T07:29:08.379215Z","id":1407,"name":"Revenue collection","area":{"createdBy":null,"createdDate":"2020-09-17T07:29:08.377474Z","lastModifiedBy":null,"lastModifiedDate":"2020-09-17T07:29:08.377474Z","id":1352,"code":null,"name":null}}	1	admin	2020-09-17 07:29:08.379215
1167	1408	org.tamisemi.iftmis.domain.SubArea	CREATE	{"createdBy":"admin","createdDate":"2020-09-17T07:29:24.392976Z","lastModifiedBy":"admin","lastModifiedDate":"2020-09-17T07:29:24.392976Z","id":1408,"name":"Revenue in arrears","area":{"createdBy":null,"createdDate":"2020-09-17T07:29:24.391914Z","lastModifiedBy":null,"lastModifiedDate":"2020-09-17T07:29:24.391914Z","id":1352,"code":null,"name":null}}	1	admin	2020-09-17 07:29:24.392976
1168	1409	org.tamisemi.iftmis.domain.SubArea	CREATE	{"createdBy":"admin","createdDate":"2020-09-17T07:29:32.512742Z","lastModifiedBy":"admin","lastModifiedDate":"2020-09-17T07:29:32.512742Z","id":1409,"name":"Borrowing","area":{"createdBy":null,"createdDate":"2020-09-17T07:29:32.510778Z","lastModifiedBy":null,"lastModifiedDate":"2020-09-17T07:29:32.510778Z","id":1352,"code":null,"name":null}}	1	admin	2020-09-17 07:29:32.512742
1169	1410	org.tamisemi.iftmis.domain.SubArea	CREATE	{"createdBy":"admin","createdDate":"2020-09-17T07:29:42.802667Z","lastModifiedBy":"admin","lastModifiedDate":"2020-09-17T07:29:42.802667Z","id":1410,"name":"Investments","area":{"createdBy":null,"createdDate":"2020-09-17T07:29:42.801741Z","lastModifiedBy":null,"lastModifiedDate":"2020-09-17T07:29:42.801741Z","id":1352,"code":null,"name":null}}	1	admin	2020-09-17 07:29:42.802667
1170	1411	org.tamisemi.iftmis.domain.SubArea	CREATE	{"createdBy":"admin","createdDate":"2020-09-17T07:29:51.698643Z","lastModifiedBy":"admin","lastModifiedDate":"2020-09-17T07:29:51.698643Z","id":1411,"name":"Public Private Partnership ","area":{"createdBy":null,"createdDate":"2020-09-17T07:29:51.697855Z","lastModifiedBy":null,"lastModifiedDate":"2020-09-17T07:29:51.697855Z","id":1352,"code":null,"name":null}}	1	admin	2020-09-17 07:29:51.698643
1171	1412	org.tamisemi.iftmis.domain.SubArea	CREATE	{"createdBy":"admin","createdDate":"2020-09-17T07:30:02.190206Z","lastModifiedBy":"admin","lastModifiedDate":"2020-09-17T07:30:02.190206Z","id":1412,"name":"Fiscal Capacity","area":{"createdBy":null,"createdDate":"2020-09-17T07:30:02.186775Z","lastModifiedBy":null,"lastModifiedDate":"2020-09-17T07:30:02.186775Z","id":1352,"code":null,"name":null}}	1	admin	2020-09-17 07:30:02.190206
1172	1413	org.tamisemi.iftmis.domain.SubArea	CREATE	{"createdBy":"admin","createdDate":"2020-09-17T07:30:11.703872Z","lastModifiedBy":"admin","lastModifiedDate":"2020-09-17T07:30:11.703872Z","id":1413,"name":"Transfer to lower  level LGAs","area":{"createdBy":null,"createdDate":"2020-09-17T07:30:11.703347Z","lastModifiedBy":null,"lastModifiedDate":"2020-09-17T07:30:11.703347Z","id":1352,"code":null,"name":null}}	1	admin	2020-09-17 07:30:11.703872
1173	1414	org.tamisemi.iftmis.domain.SubArea	CREATE	{"createdBy":"admin","createdDate":"2020-09-17T07:30:21.433165Z","lastModifiedBy":"admin","lastModifiedDate":"2020-09-17T07:30:21.433165Z","id":1414,"name":"Custody of funds, cheque books  and cash equivalents","area":{"createdBy":null,"createdDate":"2020-09-17T07:30:21.431862Z","lastModifiedBy":null,"lastModifiedDate":"2020-09-17T07:30:21.431862Z","id":1352,"code":null,"name":null}}	1	admin	2020-09-17 07:30:21.433165
1174	1415	org.tamisemi.iftmis.domain.SubArea	CREATE	{"createdBy":"admin","createdDate":"2020-09-17T07:30:31.820425Z","lastModifiedBy":"admin","lastModifiedDate":"2020-09-17T07:30:31.820425Z","id":1415,"name":"Stores records","area":{"createdBy":null,"createdDate":"2020-09-17T07:30:31.819660Z","lastModifiedBy":null,"lastModifiedDate":"2020-09-17T07:30:31.819660Z","id":1352,"code":null,"name":null}}	1	admin	2020-09-17 07:30:31.820425
1175	1416	org.tamisemi.iftmis.domain.SubArea	CREATE	{"createdBy":"admin","createdDate":"2020-09-17T07:30:41.343535Z","lastModifiedBy":"admin","lastModifiedDate":"2020-09-17T07:30:41.343535Z","id":1416,"name":"Custody of stores","area":{"createdBy":null,"createdDate":"2020-09-17T07:30:41.342506Z","lastModifiedBy":null,"lastModifiedDate":"2020-09-17T07:30:41.342506Z","id":1352,"code":null,"name":null}}	1	admin	2020-09-17 07:30:41.343535
1176	1417	org.tamisemi.iftmis.domain.SubArea	CREATE	{"createdBy":"admin","createdDate":"2020-09-17T07:30:52.745438Z","lastModifiedBy":"admin","lastModifiedDate":"2020-09-17T07:30:52.745438Z","id":1417,"name":"Insurance of assets","area":{"createdBy":null,"createdDate":"2020-09-17T07:30:52.743759Z","lastModifiedBy":null,"lastModifiedDate":"2020-09-17T07:30:52.743759Z","id":1352,"code":null,"name":null}}	1	admin	2020-09-17 07:30:52.745438
1177	1418	org.tamisemi.iftmis.domain.SubArea	CREATE	{"createdBy":"admin","createdDate":"2020-09-17T07:31:01.628097Z","lastModifiedBy":"admin","lastModifiedDate":"2020-09-17T07:31:01.628097Z","id":1418,"name":"Vehicles","area":{"createdBy":null,"createdDate":"2020-09-17T07:31:01.627563Z","lastModifiedBy":null,"lastModifiedDate":"2020-09-17T07:31:01.627563Z","id":1352,"code":null,"name":null}}	1	admin	2020-09-17 07:31:01.628097
1178	1419	org.tamisemi.iftmis.domain.SubArea	CREATE	{"createdBy":"admin","createdDate":"2020-09-17T07:31:12.465629Z","lastModifiedBy":"admin","lastModifiedDate":"2020-09-17T07:31:12.465629Z","id":1419,"name":"Liquidation/ write-off of assets","area":{"createdBy":null,"createdDate":"2020-09-17T07:31:12.463902Z","lastModifiedBy":null,"lastModifiedDate":"2020-09-17T07:31:12.463902Z","id":1352,"code":null,"name":null}}	1	admin	2020-09-17 07:31:12.465629
1179	1451	org.tamisemi.iftmis.domain.Indicator	CREATE	{"createdBy":"admin","createdDate":"2020-09-17T07:46:17.453501Z","lastModifiedBy":"admin","lastModifiedDate":"2020-09-17T07:46:17.453501Z","id":1451,"name":"  Council meetings held at least once in every three months","subArea":{"createdBy":null,"createdDate":"2020-09-17T07:46:17.447236Z","lastModifiedBy":null,"lastModifiedDate":"2020-09-17T07:46:17.447236Z","id":1401,"name":null,"area":null}}	1	admin	2020-09-17 07:46:17.453501
1180	1452	org.tamisemi.iftmis.domain.Indicator	CREATE	{"createdBy":"admin","createdDate":"2020-09-17T07:46:36.537587Z","lastModifiedBy":"admin","lastModifiedDate":"2020-09-17T07:46:36.537587Z","id":1452,"name":"  Quorum for all decisions","subArea":{"createdBy":null,"createdDate":"2020-09-17T07:46:36.536211Z","lastModifiedBy":null,"lastModifiedDate":"2020-09-17T07:46:36.536211Z","id":1401,"name":null,"area":null}}	1	admin	2020-09-17 07:46:36.537587
1181	1453	org.tamisemi.iftmis.domain.Indicator	CREATE	{"createdBy":"admin","createdDate":"2020-09-17T07:46:46.473812Z","lastModifiedBy":"admin","lastModifiedDate":"2020-09-17T07:46:46.473812Z","id":1453,"name":"  Minutes produced regularly ","subArea":{"createdBy":null,"createdDate":"2020-09-17T07:46:46.472502Z","lastModifiedBy":null,"lastModifiedDate":"2020-09-17T07:46:46.472502Z","id":1401,"name":null,"area":null}}	1	admin	2020-09-17 07:46:46.473812
1182	1454	org.tamisemi.iftmis.domain.Indicator	CREATE	{"createdBy":"admin","createdDate":"2020-09-17T07:46:57.974600Z","lastModifiedBy":"admin","lastModifiedDate":"2020-09-17T07:46:57.974600Z","id":1454,"name":"  Meetings are held in public","subArea":{"createdBy":null,"createdDate":"2020-09-17T07:46:57.973036Z","lastModifiedBy":null,"lastModifiedDate":"2020-09-17T07:46:57.973037Z","id":1401,"name":null,"area":null}}	1	admin	2020-09-17 07:46:57.9746
1183	1455	org.tamisemi.iftmis.domain.Indicator	CREATE	{"createdBy":"admin","createdDate":"2020-09-17T07:47:20.266121Z","lastModifiedBy":"admin","lastModifiedDate":"2020-09-17T07:47:20.266121Z","id":1455,"name":"  Meetings resolutions are implemented timely","subArea":{"createdBy":null,"createdDate":"2020-09-17T07:47:20.265578Z","lastModifiedBy":null,"lastModifiedDate":"2020-09-17T07:47:20.265578Z","id":1401,"name":null,"area":null}}	1	admin	2020-09-17 07:47:20.266121
1184	1456	org.tamisemi.iftmis.domain.Indicator	CREATE	{"createdBy":"admin","createdDate":"2020-09-17T07:48:46.775262Z","lastModifiedBy":"admin","lastModifiedDate":"2020-09-17T07:48:46.775262Z","id":1456,"name":"Standing committees established according to procedures ","subArea":{"createdBy":null,"createdDate":"2020-09-17T07:48:46.765982Z","lastModifiedBy":null,"lastModifiedDate":"2020-09-17T07:48:46.765982Z","id":1402,"name":null,"area":null}}	1	admin	2020-09-17 07:48:46.775262
1185	1457	org.tamisemi.iftmis.domain.Indicator	CREATE	{"createdBy":"admin","createdDate":"2020-09-17T07:48:57.761293Z","lastModifiedBy":"admin","lastModifiedDate":"2020-09-17T07:48:57.761293Z","id":1457,"name":"  Standing Committees meetings are held regularly","subArea":{"createdBy":null,"createdDate":"2020-09-17T07:48:57.759432Z","lastModifiedBy":null,"lastModifiedDate":"2020-09-17T07:48:57.759433Z","id":1402,"name":null,"area":null}}	1	admin	2020-09-17 07:48:57.761293
1186	1458	org.tamisemi.iftmis.domain.Indicator	CREATE	{"createdBy":"admin","createdDate":"2020-09-17T07:49:11.130786Z","lastModifiedBy":"admin","lastModifiedDate":"2020-09-17T07:49:11.130786Z","id":1458,"name":"Minutes produced regularly ","subArea":{"createdBy":null,"createdDate":"2020-09-17T07:49:11.129997Z","lastModifiedBy":null,"lastModifiedDate":"2020-09-17T07:49:11.129997Z","id":1402,"name":null,"area":null}}	1	admin	2020-09-17 07:49:11.130786
1187	1459	org.tamisemi.iftmis.domain.Indicator	CREATE	{"createdBy":"admin","createdDate":"2020-09-17T07:49:31.668712Z","lastModifiedBy":"admin","lastModifiedDate":"2020-09-17T07:49:31.668712Z","id":1459,"name":"Meetings resolutions are implemented timely","subArea":{"createdBy":null,"createdDate":"2020-09-17T07:49:31.667820Z","lastModifiedBy":null,"lastModifiedDate":"2020-09-17T07:49:31.667820Z","id":1402,"name":null,"area":null}}	1	admin	2020-09-17 07:49:31.668712
1189	1461	org.tamisemi.iftmis.domain.Indicator	CREATE	{"createdBy":"admin","createdDate":"2020-09-17T07:50:12.112267Z","lastModifiedBy":"admin","lastModifiedDate":"2020-09-17T07:50:12.112267Z","id":1461,"name":"The Council Director leads and exercises control over the LGA","subArea":{"createdBy":null,"createdDate":"2020-09-17T07:50:12.106465Z","lastModifiedBy":null,"lastModifiedDate":"2020-09-17T07:50:12.106465Z","id":1403,"name":null,"area":null}}	1	admin	2020-09-17 07:50:12.112267
1191	1463	org.tamisemi.iftmis.domain.Indicator	CREATE	{"createdBy":"admin","createdDate":"2020-09-17T07:50:38.595032Z","lastModifiedBy":"admin","lastModifiedDate":"2020-09-17T07:50:38.595032Z","id":1463,"name":"  Relevant agenda tabled and discussed (Revenue and Expenditure, Land matters, Social Services and Security affairs)","subArea":{"createdBy":null,"createdDate":"2020-09-17T07:50:38.593792Z","lastModifiedBy":null,"lastModifiedDate":"2020-09-17T07:50:38.593792Z","id":1404,"name":null,"area":null}}	1	admin	2020-09-17 07:50:38.595032
1192	1464	org.tamisemi.iftmis.domain.Indicator	CREATE	{"createdBy":"admin","createdDate":"2020-09-17T07:50:51.082587Z","lastModifiedBy":"admin","lastModifiedDate":"2020-09-17T07:50:51.082587Z","id":1464,"name":"Village Council meetings held at least every month ","subArea":{"createdBy":null,"createdDate":"2020-09-17T07:50:51.081161Z","lastModifiedBy":null,"lastModifiedDate":"2020-09-17T07:50:51.081162Z","id":1404,"name":null,"area":null}}	1	admin	2020-09-17 07:50:51.082587
1193	1465	org.tamisemi.iftmis.domain.Indicator	CREATE	{"createdBy":"admin","createdDate":"2020-09-17T07:51:00.332163Z","lastModifiedBy":"admin","lastModifiedDate":"2020-09-17T07:51:00.332163Z","id":1465,"name":"  Quorum for all decisions at Village Council","subArea":{"createdBy":null,"createdDate":"2020-09-17T07:51:00.330665Z","lastModifiedBy":null,"lastModifiedDate":"2020-09-17T07:51:00.330665Z","id":1404,"name":null,"area":null}}	1	admin	2020-09-17 07:51:00.332163
1194	1466	org.tamisemi.iftmis.domain.Indicator	CREATE	{"createdBy":"admin","createdDate":"2020-09-17T07:51:46.579208Z","lastModifiedBy":"admin","lastModifiedDate":"2020-09-17T07:51:46.579208Z","id":1466,"name":"Relevant by-laws adopted and enacted","subArea":{"createdBy":null,"createdDate":"2020-09-17T07:51:46.577680Z","lastModifiedBy":null,"lastModifiedDate":"2020-09-17T07:51:46.577680Z","id":1405,"name":null,"area":null}}	1	admin	2020-09-17 07:51:46.579208
1196	1468	org.tamisemi.iftmis.domain.Indicator	CREATE	{"createdBy":"admin","createdDate":"2020-09-17T07:52:29.361029Z","lastModifiedBy":"admin","lastModifiedDate":"2020-09-17T07:52:29.361029Z","id":1468,"name":"Public duly consulte","subArea":{"createdBy":null,"createdDate":"2020-09-17T07:52:29.360046Z","lastModifiedBy":null,"lastModifiedDate":"2020-09-17T07:52:29.360047Z","id":1405,"name":null,"area":null}}	1	admin	2020-09-17 07:52:29.361029
1188	1460	org.tamisemi.iftmis.domain.Indicator	CREATE	{"createdBy":"admin","createdDate":"2020-09-17T07:49:55.560214Z","lastModifiedBy":"admin","lastModifiedDate":"2020-09-17T07:49:55.560214Z","id":1460,"name":"Clear, effective and efficient division of labour maintained","subArea":{"createdBy":null,"createdDate":"2020-09-17T07:49:55.559447Z","lastModifiedBy":null,"lastModifiedDate":"2020-09-17T07:49:55.559447Z","id":1403,"name":null,"area":null}}	1	admin	2020-09-17 07:49:55.560214
1190	1462	org.tamisemi.iftmis.domain.Indicator	CREATE	{"createdBy":"admin","createdDate":"2020-09-17T07:50:25.170987Z","lastModifiedBy":"admin","lastModifiedDate":"2020-09-17T07:50:25.170987Z","id":1462,"name":"  Village General Assembly meetings held every three months","subArea":{"createdBy":null,"createdDate":"2020-09-17T07:50:25.169879Z","lastModifiedBy":null,"lastModifiedDate":"2020-09-17T07:50:25.169879Z","id":1404,"name":null,"area":null}}	1	admin	2020-09-17 07:50:25.170987
1195	1467	org.tamisemi.iftmis.domain.Indicator	CREATE	{"createdBy":"admin","createdDate":"2020-09-17T07:52:14.359585Z","lastModifiedBy":"admin","lastModifiedDate":"2020-09-17T07:52:14.359585Z","id":1467,"name":"  By-laws duly signed and authorised","subArea":{"createdBy":null,"createdDate":"2020-09-17T07:52:14.358857Z","lastModifiedBy":null,"lastModifiedDate":"2020-09-17T07:52:14.358857Z","id":1405,"name":null,"area":null}}	1	admin	2020-09-17 07:52:14.359585
1197	1469	org.tamisemi.iftmis.domain.Indicator	CREATE	{"createdBy":"admin","createdDate":"2020-09-17T07:52:41.015334Z","lastModifiedBy":"admin","lastModifiedDate":"2020-09-17T07:52:41.015334Z","id":1469,"name":"  Registration of incoming / outgoing mail","subArea":{"createdBy":null,"createdDate":"2020-09-17T07:52:41.013862Z","lastModifiedBy":null,"lastModifiedDate":"2020-09-17T07:52:41.013862Z","id":1406,"name":null,"area":null}}	1	admin	2020-09-17 07:52:41.015334
1198	1470	org.tamisemi.iftmis.domain.Indicator	CREATE	{"createdBy":"admin","createdDate":"2020-09-17T07:53:39.581750Z","lastModifiedBy":"admin","lastModifiedDate":"2020-09-17T07:53:39.581750Z","id":1470,"name":"LGA has functional, orderly archive","subArea":{"createdBy":null,"createdDate":"2020-09-17T07:53:39.580175Z","lastModifiedBy":null,"lastModifiedDate":"2020-09-17T07:53:39.580175Z","id":1406,"name":null,"area":null}}	1	admin	2020-09-17 07:53:39.58175
1199	1471	org.tamisemi.iftmis.domain.Indicator	CREATE	{"createdBy":"admin","createdDate":"2020-09-17T07:53:53.167088Z","lastModifiedBy":"admin","lastModifiedDate":"2020-09-17T07:53:53.167088Z","id":1471,"name":"Files are kept for minimum period provided by legislation and records ret","subArea":{"createdBy":null,"createdDate":"2020-09-17T07:53:53.166493Z","lastModifiedBy":null,"lastModifiedDate":"2020-09-17T07:53:53.166493Z","id":1406,"name":null,"area":null}}	1	admin	2020-09-17 07:53:53.167088
1200	1501	org.tamisemi.iftmis.domain.Procedure	CREATE	{"createdBy":"admin","createdDate":"2020-09-17T07:55:09.241984Z","lastModifiedBy":"admin","lastModifiedDate":"2020-09-17T07:55:09.241984Z","id":1501,"name":"Review minutes and annual meetings timetable","indicator":{"createdBy":null,"createdDate":"2020-09-17T07:55:09.224024Z","lastModifiedBy":null,"lastModifiedDate":"2020-09-17T07:55:09.224024Z","id":1451,"name":null,"subArea":null}}	1	admin	2020-09-17 07:55:09.241984
1551	1502	org.tamisemi.iftmis.domain.Procedure	CREATE	{"createdBy":"admin","createdDate":"2020-09-17T08:01:50.845483Z","lastModifiedBy":"admin","lastModifiedDate":"2020-09-17T08:01:50.845483Z","id":1502,"name":"Review minutes","indicator":{"createdBy":null,"createdDate":"2020-09-17T08:01:50.844250Z","lastModifiedBy":null,"lastModifiedDate":"2020-09-17T08:01:50.844250Z","id":1451,"name":null,"subArea":null}}	1	admin	2020-09-17 08:01:50.845483
1552	1503	org.tamisemi.iftmis.domain.Procedure	CREATE	{"createdBy":"admin","createdDate":"2020-09-17T08:02:13.176705Z","lastModifiedBy":"admin","lastModifiedDate":"2020-09-17T08:02:13.176705Z","id":1503,"name":"Review minutes","indicator":{"createdBy":null,"createdDate":"2020-09-17T08:02:13.175331Z","lastModifiedBy":null,"lastModifiedDate":"2020-09-17T08:02:13.175332Z","id":1451,"name":null,"subArea":null}}	1	admin	2020-09-17 08:02:13.176705
1553	1504	org.tamisemi.iftmis.domain.Procedure	CREATE	{"createdBy":"admin","createdDate":"2020-09-17T08:02:28.453623Z","lastModifiedBy":"admin","lastModifiedDate":"2020-09-17T08:02:28.453623Z","id":1504,"name":"Review minutes","indicator":{"createdBy":null,"createdDate":"2020-09-17T08:02:28.452533Z","lastModifiedBy":null,"lastModifiedDate":"2020-09-17T08:02:28.452534Z","id":1451,"name":null,"subArea":null}}	1	admin	2020-09-17 08:02:28.453623
1554	1502	org.tamisemi.iftmis.domain.Procedure	UPDATE	{"createdBy":null,"createdDate":"2020-09-17T08:03:22.747269Z","lastModifiedBy":"admin","lastModifiedDate":"2020-09-17T08:03:22.750840Z","id":1502,"name":"Review minutes","indicator":{"createdBy":"admin","createdDate":"2020-09-17T07:46:36.537587Z","lastModifiedBy":"admin","lastModifiedDate":"2020-09-17T07:46:36.537587Z","id":1452,"name":"  Quorum for all decisions","subArea":{"createdBy":"admin","createdDate":"2020-09-17T07:27:30.137441Z","lastModifiedBy":"admin","lastModifiedDate":"2020-09-17T07:27:30.137441Z","id":1401,"name":"LGA Meetings and proceedings","area":{"createdBy":"admin","createdDate":"2020-09-17T07:27:11.758276Z","lastModifiedBy":"admin","lastModifiedDate":"2020-09-17T07:27:11.758276Z","id":1351,"code":"001","name":"Governance"}}}}	2	admin	2020-09-17 08:03:22.75084
1555	1505	org.tamisemi.iftmis.domain.Procedure	CREATE	{"createdBy":"admin","createdDate":"2020-09-17T08:05:32.076194Z","lastModifiedBy":"admin","lastModifiedDate":"2020-09-17T08:05:32.076194Z","id":1505,"name":"Review minutes","indicator":{"createdBy":null,"createdDate":"2020-09-17T08:05:32.075183Z","lastModifiedBy":null,"lastModifiedDate":"2020-09-17T08:05:32.075183Z","id":1453,"name":null,"subArea":null}}	1	admin	2020-09-17 08:05:32.076194
1556	1506	org.tamisemi.iftmis.domain.Procedure	CREATE	{"createdBy":"admin","createdDate":"2020-09-17T08:06:11.707313Z","lastModifiedBy":"admin","lastModifiedDate":"2020-09-17T08:06:11.707313Z","id":1506,"name":"Review minutes","indicator":{"createdBy":null,"createdDate":"2020-09-17T08:06:11.706457Z","lastModifiedBy":null,"lastModifiedDate":"2020-09-17T08:06:11.706457Z","id":1453,"name":null,"subArea":null}}	1	admin	2020-09-17 08:06:11.707313
1601	1502	org.tamisemi.iftmis.domain.Procedure	UPDATE	{"createdBy":null,"createdDate":"2020-09-17T08:30:24.002951Z","lastModifiedBy":"admin","lastModifiedDate":"2020-09-17T08:30:24.073391Z","id":1502,"name":"Review minutes","indicator":{"createdBy":"admin","createdDate":"2020-09-17T07:46:36.537587Z","lastModifiedBy":"admin","lastModifiedDate":"2020-09-17T07:46:36.537587Z","id":1452,"name":"  Quorum for all decisions","subArea":{"createdBy":"admin","createdDate":"2020-09-17T07:27:30.137441Z","lastModifiedBy":"admin","lastModifiedDate":"2020-09-17T07:27:30.137441Z","id":1401,"name":"LGA Meetings and proceedings","area":{"createdBy":"admin","createdDate":"2020-09-17T07:27:11.758276Z","lastModifiedBy":"admin","lastModifiedDate":"2020-09-17T07:27:11.758276Z","id":1351,"code":"001","name":"Governance"}}}}	3	admin	2020-09-17 08:30:24.073391
1602	1651	org.tamisemi.iftmis.domain.Procedure	CREATE	{"createdBy":"admin","createdDate":"2020-09-17T08:30:44.502259Z","lastModifiedBy":"admin","lastModifiedDate":"2020-09-17T08:30:44.502259Z","id":1651,"name":"Interview CD","indicator":{"createdBy":null,"createdDate":"2020-09-17T08:30:44.500636Z","lastModifiedBy":null,"lastModifiedDate":"2020-09-17T08:30:44.500637Z","id":1454,"name":null,"subArea":null}}	1	admin	2020-09-17 08:30:44.502259
1603	1652	org.tamisemi.iftmis.domain.Procedure	CREATE	{"createdBy":"admin","createdDate":"2020-09-17T08:31:02.457329Z","lastModifiedBy":"admin","lastModifiedDate":"2020-09-17T08:31:02.457329Z","id":1652,"name":"Review minutes","indicator":{"createdBy":null,"createdDate":"2020-09-17T08:31:02.454891Z","lastModifiedBy":null,"lastModifiedDate":"2020-09-17T08:31:02.454892Z","id":1455,"name":null,"subArea":null}}	1	admin	2020-09-17 08:31:02.457329
1604	1653	org.tamisemi.iftmis.domain.Procedure	CREATE	{"createdBy":"admin","createdDate":"2020-09-17T08:31:23.066575Z","lastModifiedBy":"admin","lastModifiedDate":"2020-09-17T08:31:23.066575Z","id":1653,"name":"Review Standing Committees formation proceedings if it comply with Standing Committees Directives ","indicator":{"createdBy":null,"createdDate":"2020-09-17T08:31:23.065761Z","lastModifiedBy":null,"lastModifiedDate":"2020-09-17T08:31:23.065761Z","id":1456,"name":null,"subArea":null}}	1	admin	2020-09-17 08:31:23.066575
1605	1654	org.tamisemi.iftmis.domain.Procedure	CREATE	{"createdBy":"admin","createdDate":"2020-09-17T08:32:20.881767Z","lastModifiedBy":"admin","lastModifiedDate":"2020-09-17T08:32:20.881767Z","id":1654,"name":"Interview CD","indicator":{"createdBy":null,"createdDate":"2020-09-17T08:32:20.880223Z","lastModifiedBy":null,"lastModifiedDate":"2020-09-17T08:32:20.880223Z","id":1457,"name":null,"subArea":null}}	1	admin	2020-09-17 08:32:20.881767
1606	1651	org.tamisemi.iftmis.domain.Procedure	UPDATE	{"createdBy":null,"createdDate":"2020-09-17T08:32:37.743154Z","lastModifiedBy":"admin","lastModifiedDate":"2020-09-17T08:32:37.746589Z","id":1651,"name":"Interview Council Director","indicator":{"createdBy":"admin","createdDate":"2020-09-17T07:46:57.974600Z","lastModifiedBy":"admin","lastModifiedDate":"2020-09-17T07:46:57.974600Z","id":1454,"name":"  Meetings are held in public","subArea":{"createdBy":"admin","createdDate":"2020-09-17T07:27:30.137441Z","lastModifiedBy":"admin","lastModifiedDate":"2020-09-17T07:27:30.137441Z","id":1401,"name":"LGA Meetings and proceedings","area":{"createdBy":"admin","createdDate":"2020-09-17T07:27:11.758276Z","lastModifiedBy":"admin","lastModifiedDate":"2020-09-17T07:27:11.758276Z","id":1351,"code":"001","name":"Governance"}}}}	2	admin	2020-09-17 08:32:37.746589
1607	1654	org.tamisemi.iftmis.domain.Procedure	UPDATE	{"createdBy":null,"createdDate":"2020-09-17T08:32:59.452819Z","lastModifiedBy":"admin","lastModifiedDate":"2020-09-17T08:32:59.454422Z","id":1654,"name":"Interview Council Director","indicator":{"createdBy":"admin","createdDate":"2020-09-17T07:48:57.761293Z","lastModifiedBy":"admin","lastModifiedDate":"2020-09-17T07:48:57.761293Z","id":1457,"name":"  Standing Committees meetings are held regularly","subArea":{"createdBy":"admin","createdDate":"2020-09-17T07:27:43.857500Z","lastModifiedBy":"admin","lastModifiedDate":"2020-09-17T07:27:43.857500Z","id":1402,"name":"Standing committees","area":{"createdBy":"admin","createdDate":"2020-09-17T07:27:11.758276Z","lastModifiedBy":"admin","lastModifiedDate":"2020-09-17T07:27:11.758276Z","id":1351,"code":"001","name":"Governance"}}}}	2	admin	2020-09-17 08:32:59.454422
1608	1701	org.tamisemi.iftmis.domain.FindingCategory	CREATE	{"createdBy":"admin","createdDate":"2020-09-17T08:36:53.619367Z","lastModifiedBy":"admin","lastModifiedDate":"2020-09-17T08:36:53.619367Z","id":1701,"code":"001","name":"Category 1"}	1	admin	2020-09-17 08:36:53.619367
1609	1702	org.tamisemi.iftmis.domain.FindingCategory	CREATE	{"createdBy":"admin","createdDate":"2020-09-17T08:37:03.759951Z","lastModifiedBy":"admin","lastModifiedDate":"2020-09-17T08:37:03.759951Z","id":1702,"code":"002","name":"Category 2"}	1	admin	2020-09-17 08:37:03.759951
1610	1751	org.tamisemi.iftmis.domain.FindingSubCategory	CREATE	{"createdBy":"admin","createdDate":"2020-09-17T08:37:23.447774Z","lastModifiedBy":"admin","lastModifiedDate":"2020-09-17T08:37:23.447774Z","id":1751,"code":"001","name":"Sub-Category 1"}	1	admin	2020-09-17 08:37:23.447774
1611	1752	org.tamisemi.iftmis.domain.FindingSubCategory	CREATE	{"createdBy":"admin","createdDate":"2020-09-17T08:37:34.915427Z","lastModifiedBy":"admin","lastModifiedDate":"2020-09-17T08:37:34.915427Z","id":1752,"code":"002","name":"Sub-Categry 2"}	1	admin	2020-09-17 08:37:34.915427
1901	1851	org.tamisemi.iftmis.domain.RiskRank	CREATE	{"createdBy":"admin","createdDate":"2020-09-17T08:46:52.807669Z","lastModifiedBy":"admin","lastModifiedDate":"2020-09-17T08:46:52.807669Z","id":1851,"name":"Low(L)","minValue":1,"maxValue":4,"hexColor":"#00ff00"}	1	admin	2020-09-17 08:46:52.807669
1902	1852	org.tamisemi.iftmis.domain.RiskRank	CREATE	{"createdBy":"admin","createdDate":"2020-09-17T08:47:48.459659Z","lastModifiedBy":"admin","lastModifiedDate":"2020-09-17T08:47:48.459659Z","id":1852,"name":"Moderate(M)","minValue":5,"maxValue":9,"hexColor":"#ffff00"}	1	admin	2020-09-17 08:47:48.459659
1903	1853	org.tamisemi.iftmis.domain.RiskRank	CREATE	{"createdBy":"admin","createdDate":"2020-09-17T08:48:26.287914Z","lastModifiedBy":"admin","lastModifiedDate":"2020-09-17T08:48:26.287914Z","id":1853,"name":"High(H)","minValue":10,"maxValue":14,"hexColor":"#ff9933"}	1	admin	2020-09-17 08:48:26.287914
1904	1854	org.tamisemi.iftmis.domain.RiskRank	CREATE	{"createdBy":"admin","createdDate":"2020-09-17T08:49:28.664910Z","lastModifiedBy":"admin","lastModifiedDate":"2020-09-17T08:49:28.664910Z","id":1854,"name":"Extreme or severe (E","minValue":15,"maxValue":25,"hexColor":"#ff0000"}	1	admin	2020-09-17 08:49:28.66491
1905	1951	org.tamisemi.iftmis.domain.RiskCategory	CREATE	{"createdBy":"admin","createdDate":"2020-09-17T08:55:04.979248Z","lastModifiedBy":"admin","lastModifiedDate":"2020-09-17T08:55:04.979248Z","id":1951,"code":"001","name":"Operational Risks"}	1	admin	2020-09-17 08:55:04.979248
1906	1952	org.tamisemi.iftmis.domain.RiskCategory	CREATE	{"createdBy":"admin","createdDate":"2020-09-17T08:55:16.719808Z","lastModifiedBy":"admin","lastModifiedDate":"2020-09-17T08:55:16.719808Z","id":1952,"code":"002","name":"Compliance Risks"}	1	admin	2020-09-17 08:55:16.719808
1907	1953	org.tamisemi.iftmis.domain.RiskCategory	CREATE	{"createdBy":"admin","createdDate":"2020-09-17T08:55:26.715645Z","lastModifiedBy":"admin","lastModifiedDate":"2020-09-17T08:55:26.715645Z","id":1953,"code":"003","name":"Strategic Risks"}	1	admin	2020-09-17 08:55:26.715645
1908	1954	org.tamisemi.iftmis.domain.RiskCategory	CREATE	{"createdBy":"admin","createdDate":"2020-09-17T08:55:37.850526Z","lastModifiedBy":"admin","lastModifiedDate":"2020-09-17T08:55:37.850526Z","id":1954,"code":"004","name":"Financial risks"}	1	admin	2020-09-17 08:55:37.850526
1909	2001	org.tamisemi.iftmis.domain.FinancialYear	CREATE	{"createdBy":"admin","createdDate":"2020-09-17T08:58:37.052803Z","lastModifiedBy":"admin","lastModifiedDate":"2020-09-17T08:58:37.052803Z","id":2001,"name":"2021/22","startDate":"2021-07-01","endDate":"2022-06-30","isOpened":false,"closed":false}	1	admin	2020-09-17 08:58:37.052803
2151	2101	org.tamisemi.iftmis.domain.FinancialYear	CREATE	{"createdBy":"admin","createdDate":"2020-09-17T09:06:48.277704Z","lastModifiedBy":"admin","lastModifiedDate":"2020-09-17T09:06:48.277704Z","id":2101,"name":"2019/20","startDate":"2019-07-01","endDate":"2020-06-30","isOpened":false,"closed":false}	1	admin	2020-09-17 09:06:48.277704
2559	1751	org.tamisemi.iftmis.domain.FindingSubCategory	UPDATE	{\n  "createdBy" : null,\n  "createdDate" : "2020-09-17T09:34:30.381Z",\n  "lastModifiedBy" : "admin",\n  "lastModifiedDate" : "2020-09-17T09:34:30.397Z",\n  "id" : 1751,\n  "code" : "001",\n  "name" : "Own source revenue not collected"\n}	2	admin	2020-09-17 09:34:30.397
2251	2201	org.tamisemi.iftmis.domain.FinancialYear	CREATE	{"createdBy":"admin","createdDate":"2020-09-17T09:12:50.199069Z","lastModifiedBy":"admin","lastModifiedDate":"2020-09-17T09:12:50.199069Z","id":2201,"name":"2019/2020","startDate":"2019-07-01","endDate":"2020-06-30","isOpened":false,"closed":false}	1	admin	2020-09-17 09:12:50.199069
2351	2301	org.tamisemi.iftmis.domain.FinancialYear	CREATE	{"createdBy":"admin","createdDate":"2020-09-17T09:15:04.233503Z","lastModifiedBy":"admin","lastModifiedDate":"2020-09-17T09:15:04.233503Z","id":2301,"name":"2020/2021","startDate":"2020-07-01","endDate":"2021-06-30","isOpened":true,"closed":false}	1	admin	2020-09-17 09:15:04.233503
2352	2301	org.tamisemi.iftmis.domain.FinancialYear	UPDATE	{"createdBy":"admin","createdDate":"2020-09-17T09:15:04.233503Z","lastModifiedBy":"admin","lastModifiedDate":"2020-09-17T09:15:10.751480Z","id":2301,"name":"2020/2021","startDate":"2020-07-01","endDate":"2021-06-30","isOpened":true,"closed":false}	2	admin	2020-09-17 09:15:10.75148
2353	2201	org.tamisemi.iftmis.domain.FinancialYear	UPDATE	{"createdBy":"admin","createdDate":"2020-09-17T09:12:50.199069Z","lastModifiedBy":"admin","lastModifiedDate":"2020-09-17T09:15:13.238646Z","id":2201,"name":"2019/2020","startDate":"2019-07-01","endDate":"2020-06-30","isOpened":true,"closed":false}	2	admin	2020-09-17 09:15:13.238646
2354	2301	org.tamisemi.iftmis.domain.FinancialYear	UPDATE	{"createdBy":null,"createdDate":"2020-09-17T09:15:25.760968Z","lastModifiedBy":"admin","lastModifiedDate":"2020-09-17T09:15:25.763519Z","id":2301,"name":"2020/2021","startDate":"2020-07-01","endDate":"2021-06-30","isOpened":true,"closed":null}	3	admin	2020-09-17 09:15:25.763519
2355	2201	org.tamisemi.iftmis.domain.FinancialYear	UPDATE	{"createdBy":null,"createdDate":"2020-09-17T09:15:31.664322Z","lastModifiedBy":"admin","lastModifiedDate":"2020-09-17T09:15:31.670101Z","id":2201,"name":"2019/2020","startDate":"2019-07-01","endDate":"2020-06-30","isOpened":false,"closed":null}	3	admin	2020-09-17 09:15:31.670101
2356	2301	org.tamisemi.iftmis.domain.FinancialYear	UPDATE	{"createdBy":null,"createdDate":"2020-09-17T09:15:39.463485Z","lastModifiedBy":"admin","lastModifiedDate":"2020-09-17T09:15:39.468870Z","id":2301,"name":"2020/2021","startDate":"2020-07-01","endDate":"2021-06-30","isOpened":false,"closed":null}	4	admin	2020-09-17 09:15:39.46887
2357	2201	org.tamisemi.iftmis.domain.FinancialYear	UPDATE	{"createdBy":null,"createdDate":"2020-09-17T09:15:45.745567Z","lastModifiedBy":"admin","lastModifiedDate":"2020-09-17T09:15:45.747310Z","id":2201,"name":"2019/2020","startDate":"2019-07-01","endDate":"2020-06-30","isOpened":true,"closed":null}	4	admin	2020-09-17 09:15:45.74731
2451	2301	org.tamisemi.iftmis.domain.FinancialYear	UPDATE	{\n  "createdBy" : "admin",\n  "createdDate" : "2020-09-17T09:15:04.233503Z",\n  "lastModifiedBy" : "admin",\n  "lastModifiedDate" : "2020-09-17T09:19:02.797Z",\n  "id" : 2301,\n  "name" : "2020/2021",\n  "startDate" : "2020-07-01",\n  "endDate" : "2021-06-30",\n  "isOpened" : true,\n  "closed" : false\n}	5	admin	2020-09-17 09:19:02.797
2551	2501	org.tamisemi.iftmis.domain.FinancialYear	CREATE	{\n  "createdBy" : "admin",\n  "createdDate" : "2020-09-17T09:22:38.368Z",\n  "lastModifiedBy" : "admin",\n  "lastModifiedDate" : "2020-09-17T09:22:38.368Z",\n  "id" : 2501,\n  "name" : "2019/2020",\n  "startDate" : "2019-07-01",\n  "endDate" : "2020-07-01",\n  "isOpened" : false,\n  "closed" : false\n}	1	admin	2020-09-17 09:22:38.368
2552	2601	org.tamisemi.iftmis.domain.RiskRegister	CREATE	{\n  "createdBy" : "admin",\n  "createdDate" : "2020-09-17T09:22:38.434Z",\n  "lastModifiedBy" : "admin",\n  "lastModifiedDate" : "2020-09-17T09:22:38.434Z",\n  "id" : 2601,\n  "name" : "DIFT PORALG - 2019/2020",\n  "isApproved" : false,\n  "approvedDate" : null,\n  "approvedBy" : null,\n  "organisationUnit" : {\n    "createdBy" : null,\n    "createdDate" : "2020-09-17T09:22:38.428Z",\n    "lastModifiedBy" : null,\n    "lastModifiedDate" : "2020-09-17T09:22:38.428Z",\n    "id" : 1,\n    "code" : null,\n    "name" : null,\n    "address" : null,\n    "phoneNumber" : null,\n    "email" : null,\n    "background" : null,\n    "logo" : null,\n    "logoContentType" : null,\n    "organisationUnitLevel" : null,\n    "parent" : null\n  },\n  "financialYear" : {\n    "createdBy" : null,\n    "createdDate" : "2020-09-17T09:22:38.428Z",\n    "lastModifiedBy" : null,\n    "lastModifiedDate" : "2020-09-17T09:22:38.428Z",\n    "id" : 2501,\n    "name" : null,\n    "startDate" : null,\n    "endDate" : null,\n    "isOpened" : null,\n    "closed" : null\n  }\n}	1	admin	2020-09-17 09:22:38.434
2553	2502	org.tamisemi.iftmis.domain.FinancialYear	CREATE	{\n  "createdBy" : "admin",\n  "createdDate" : "2020-09-17T09:23:14.863Z",\n  "lastModifiedBy" : "admin",\n  "lastModifiedDate" : "2020-09-17T09:23:14.863Z",\n  "id" : 2502,\n  "name" : "2020/2021",\n  "startDate" : "2020-07-01",\n  "endDate" : "2021-06-30",\n  "isOpened" : true,\n  "closed" : false\n}	1	admin	2020-09-17 09:23:14.863
2554	2602	org.tamisemi.iftmis.domain.RiskRegister	CREATE	{\n  "createdBy" : "admin",\n  "createdDate" : "2020-09-17T09:23:14.892Z",\n  "lastModifiedBy" : "admin",\n  "lastModifiedDate" : "2020-09-17T09:23:14.892Z",\n  "id" : 2602,\n  "name" : "DIFT PORALG - 2020/2021",\n  "isApproved" : false,\n  "approvedDate" : null,\n  "approvedBy" : null,\n  "organisationUnit" : {\n    "createdBy" : null,\n    "createdDate" : "2020-09-17T09:23:14.892Z",\n    "lastModifiedBy" : null,\n    "lastModifiedDate" : "2020-09-17T09:23:14.892Z",\n    "id" : 1,\n    "code" : null,\n    "name" : null,\n    "address" : null,\n    "phoneNumber" : null,\n    "email" : null,\n    "background" : null,\n    "logo" : null,\n    "logoContentType" : null,\n    "organisationUnitLevel" : null,\n    "parent" : null\n  },\n  "financialYear" : {\n    "createdBy" : null,\n    "createdDate" : "2020-09-17T09:23:14.892Z",\n    "lastModifiedBy" : null,\n    "lastModifiedDate" : "2020-09-17T09:23:14.892Z",\n    "id" : 2502,\n    "name" : null,\n    "startDate" : null,\n    "endDate" : null,\n    "isOpened" : null,\n    "closed" : null\n  }\n}	1	admin	2020-09-17 09:23:14.892
2555	2502	org.tamisemi.iftmis.domain.FinancialYear	UPDATE	{\n  "createdBy" : "admin",\n  "createdDate" : "2020-09-17T09:23:14.863Z",\n  "lastModifiedBy" : "admin",\n  "lastModifiedDate" : "2020-09-17T09:23:47.544Z",\n  "id" : 2502,\n  "name" : "2020/2021",\n  "startDate" : "2020-07-01",\n  "endDate" : "2021-06-30",\n  "isOpened" : true,\n  "closed" : false\n}	2	admin	2020-09-17 09:23:47.544
2556	1701	org.tamisemi.iftmis.domain.FindingCategory	UPDATE	{\n  "createdBy" : null,\n  "createdDate" : "2020-09-17T09:33:35.437Z",\n  "lastModifiedBy" : "admin",\n  "lastModifiedDate" : "2020-09-17T09:33:35.449Z",\n  "id" : 1701,\n  "code" : "001",\n  "name" : "Financial"\n}	2	admin	2020-09-17 09:33:35.449
2557	1702	org.tamisemi.iftmis.domain.FindingCategory	DELETE	{\n  "createdBy" : "admin",\n  "createdDate" : "2020-09-17T08:37:03.759951Z",\n  "lastModifiedBy" : "admin",\n  "lastModifiedDate" : "2020-09-17T08:37:03.759951Z",\n  "id" : 1702,\n  "code" : "002",\n  "name" : "Category 2"\n}	2	admin	2020-09-17 08:37:03.759951
2558	1701	org.tamisemi.iftmis.domain.FindingCategory	UPDATE	{\n  "createdBy" : null,\n  "createdDate" : "2020-09-17T09:33:54.096Z",\n  "lastModifiedBy" : "admin",\n  "lastModifiedDate" : "2020-09-17T09:33:54.102Z",\n  "id" : 1701,\n  "code" : "001",\n  "name" : "Financial Overview"\n}	3	admin	2020-09-17 09:33:54.102
2560	1752	org.tamisemi.iftmis.domain.FindingSubCategory	UPDATE	{\n  "createdBy" : null,\n  "createdDate" : "2020-09-17T09:35:57.098Z",\n  "lastModifiedBy" : "admin",\n  "lastModifiedDate" : "2020-09-17T09:35:57.103Z",\n  "id" : 1752,\n  "code" : "002",\n  "name" : "Long Outstanding Payables"\n}	2	admin	2020-09-17 09:35:57.103
2561	1752	org.tamisemi.iftmis.domain.FindingSubCategory	UPDATE	{\n  "createdBy" : null,\n  "createdDate" : "2020-09-17T09:36:12.459Z",\n  "lastModifiedBy" : "admin",\n  "lastModifiedDate" : "2020-09-17T09:36:12.467Z",\n  "id" : 1752,\n  "code" : "002",\n  "name" : "Long Outstanding Receivables"\n}	3	admin	2020-09-17 09:36:12.467
2562	2651	org.tamisemi.iftmis.domain.FindingSubCategory	CREATE	{\n  "createdBy" : "admin",\n  "createdDate" : "2020-09-17T09:36:25.485Z",\n  "lastModifiedBy" : "admin",\n  "lastModifiedDate" : "2020-09-17T09:36:25.485Z",\n  "id" : 2651,\n  "code" : "003",\n  "name" : "Long Outstanding Payables"\n}	1	admin	2020-09-17 09:36:25.485
2563	2652	org.tamisemi.iftmis.domain.FindingSubCategory	CREATE	{\n  "createdBy" : "admin",\n  "createdDate" : "2020-09-17T09:37:19.245Z",\n  "lastModifiedBy" : "admin",\n  "lastModifiedDate" : "2020-09-17T09:37:19.245Z",\n  "id" : 2652,\n  "code" : "004",\n  "name" : "Financial Statements with material misstatements"\n}	1	admin	2020-09-17 09:37:19.245
2564	2653	org.tamisemi.iftmis.domain.FindingSubCategory	CREATE	{\n  "createdBy" : "admin",\n  "createdDate" : "2020-09-17T09:37:57.214Z",\n  "lastModifiedBy" : "admin",\n  "lastModifiedDate" : "2020-09-17T09:37:57.214Z",\n  "id" : 2653,\n  "code" : "005",\n  "name" : "Outstanding litigation claims against the Council"\n}	1	admin	2020-09-17 09:37:57.214
2565	2654	org.tamisemi.iftmis.domain.FindingSubCategory	CREATE	{\n  "createdBy" : "admin",\n  "createdDate" : "2020-09-17T09:39:05.770Z",\n  "lastModifiedBy" : "admin",\n  "lastModifiedDate" : "2020-09-17T09:39:05.770Z",\n  "id" : 2654,\n  "code" : "006",\n  "name" : "Collected Own source revenue not allocated fr development activities"\n}	1	admin	2020-09-17 09:39:05.77
2566	2655	org.tamisemi.iftmis.domain.FindingSubCategory	CREATE	{\n  "createdBy" : "admin",\n  "createdDate" : "2020-09-17T09:39:59.986Z",\n  "lastModifiedBy" : "admin",\n  "lastModifiedDate" : "2020-09-17T09:39:59.986Z",\n  "id" : 2655,\n  "code" : "007",\n  "name" : "Development Funds used for payments of unbudgeted recurrent activities"\n}	1	admin	2020-09-17 09:39:59.986
2567	2656	org.tamisemi.iftmis.domain.FindingSubCategory	CREATE	{\n  "createdBy" : "admin",\n  "createdDate" : "2020-09-17T09:40:31.565Z",\n  "lastModifiedBy" : "admin",\n  "lastModifiedDate" : "2020-09-17T09:40:31.565Z",\n  "id" : 2656,\n  "code" : "008",\n  "name" : "Reallocation of budgeted funds not approved"\n}	1	admin	2020-09-17 09:40:31.565
2568	2657	org.tamisemi.iftmis.domain.FindingSubCategory	CREATE	{\n  "createdBy" : "admin",\n  "createdDate" : "2020-09-17T09:41:11.593Z",\n  "lastModifiedBy" : "admin",\n  "lastModifiedDate" : "2020-09-17T09:41:11.593Z",\n  "id" : 2657,\n  "code" : "009",\n  "name" : "Payments of funds signed by the officers not within their budgetary provision"\n}	1	admin	2020-09-17 09:41:11.593
2569	2658	org.tamisemi.iftmis.domain.FindingSubCategory	CREATE	{\n  "createdBy" : "admin",\n  "createdDate" : "2020-09-17T09:41:42.508Z",\n  "lastModifiedBy" : "admin",\n  "lastModifiedDate" : "2020-09-17T09:41:42.508Z",\n  "id" : 2658,\n  "code" : "010",\n  "name" : "Expenditures charged to incorrect account codes"\n}	1	admin	2020-09-17 09:41:42.508
2570	2659	org.tamisemi.iftmis.domain.FindingSubCategory	CREATE	{\n  "createdBy" : "admin",\n  "createdDate" : "2020-09-17T09:42:30.209Z",\n  "lastModifiedBy" : "admin",\n  "lastModifiedDate" : "2020-09-17T09:42:30.209Z",\n  "id" : 2659,\n  "code" : "011",\n  "name" : "Officers acting in vacant post for more than six months"\n}	1	admin	2020-09-17 09:42:30.209
2571	2660	org.tamisemi.iftmis.domain.FindingSubCategory	CREATE	{\n  "createdBy" : "admin",\n  "createdDate" : "2020-09-17T09:42:59.046Z",\n  "lastModifiedBy" : "admin",\n  "lastModifiedDate" : "2020-09-17T09:42:59.046Z",\n  "id" : 2660,\n  "code" : "012",\n  "name" : "Inadequate number of staff"\n}	1	admin	2020-09-17 09:42:59.046
2572	2661	org.tamisemi.iftmis.domain.FindingSubCategory	CREATE	{\n  "createdBy" : "admin",\n  "createdDate" : "2020-09-17T09:43:47.150Z",\n  "lastModifiedBy" : "admin",\n  "lastModifiedDate" : "2020-09-17T09:43:47.150Z",\n  "id" : 2661,\n  "code" : "013",\n  "name" : "Delay in approving the promoted staff in the system"\n}	1	admin	2020-09-17 09:43:47.15
2573	2662	org.tamisemi.iftmis.domain.FindingSubCategory	CREATE	{\n  "createdBy" : "admin",\n  "createdDate" : "2020-09-17T09:44:29.980Z",\n  "lastModifiedBy" : "admin",\n  "lastModifiedDate" : "2020-09-17T09:44:29.980Z",\n  "id" : 2662,\n  "code" : "014",\n  "name" : "Unclaimed salaries not remitted to treasury"\n}	1	admin	2020-09-17 09:44:29.98
2574	2663	org.tamisemi.iftmis.domain.FindingSubCategory	CREATE	{\n  "createdBy" : "admin",\n  "createdDate" : "2020-09-17T09:46:58.601Z",\n  "lastModifiedBy" : "admin",\n  "lastModifiedDate" : "2020-09-17T09:46:58.601Z",\n  "id" : 2663,\n  "code" : "015",\n  "name" : "Internal auditor limited access to audit revenue in the LGRCIS"\n}	1	admin	2020-09-17 09:46:58.601
2575	2664	org.tamisemi.iftmis.domain.FindingSubCategory	CREATE	{\n  "createdBy" : "admin",\n  "createdDate" : "2020-09-17T09:47:45.214Z",\n  "lastModifiedBy" : "admin",\n  "lastModifiedDate" : "2020-09-17T09:47:45.214Z",\n  "id" : 2664,\n  "code" : "016",\n  "name" : "Collected Revenue not banked by revenue collectors"\n}	1	admin	2020-09-17 09:47:45.214
2576	2701	org.tamisemi.iftmis.domain.FindingCategory	CREATE	{\n  "createdBy" : "admin",\n  "createdDate" : "2020-09-17T09:58:16.090Z",\n  "lastModifiedBy" : "admin",\n  "lastModifiedDate" : "2020-09-17T09:58:16.090Z",\n  "id" : 2701,\n  "code" : "002",\n  "name" : "Issues arising from the audit of Financial Statements"\n}	1	admin	2020-09-17 09:58:16.09
2577	2702	org.tamisemi.iftmis.domain.FindingCategory	CREATE	{\n  "createdBy" : "admin",\n  "createdDate" : "2020-09-17T09:59:05.276Z",\n  "lastModifiedBy" : "admin",\n  "lastModifiedDate" : "2020-09-17T09:59:05.276Z",\n  "id" : 2702,\n  "code" : "003",\n  "name" : "Budget preparation and execution"\n}	1	admin	2020-09-17 09:59:05.276
2578	2703	org.tamisemi.iftmis.domain.FindingCategory	CREATE	{\n  "createdBy" : "admin",\n  "createdDate" : "2020-09-17T10:00:08.355Z",\n  "lastModifiedBy" : "admin",\n  "lastModifiedDate" : "2020-09-17T10:00:08.355Z",\n  "id" : 2703,\n  "code" : "004",\n  "name" : "Budget preparation and execution"\n}	1	admin	2020-09-17 10:00:08.355
2579	2704	org.tamisemi.iftmis.domain.FindingCategory	CREATE	{\n  "createdBy" : "admin",\n  "createdDate" : "2020-09-17T10:00:24.005Z",\n  "lastModifiedBy" : "admin",\n  "lastModifiedDate" : "2020-09-17T10:00:24.005Z",\n  "id" : 2704,\n  "code" : "004",\n  "name" : "Human resources and payroll management"\n}	1	admin	2020-09-17 10:00:24.005
2580	2705	org.tamisemi.iftmis.domain.FindingCategory	CREATE	{\n  "createdBy" : "admin",\n  "createdDate" : "2020-09-17T10:01:06.275Z",\n  "lastModifiedBy" : "admin",\n  "lastModifiedDate" : "2020-09-17T10:01:06.275Z",\n  "id" : 2705,\n  "code" : "005",\n  "name" : "Governance issues"\n}	1	admin	2020-09-17 10:01:06.275
2581	2706	org.tamisemi.iftmis.domain.FindingCategory	CREATE	{\n  "createdBy" : "admin",\n  "createdDate" : "2020-09-17T10:01:24.582Z",\n  "lastModifiedBy" : "admin",\n  "lastModifiedDate" : "2020-09-17T10:01:24.582Z",\n  "id" : 2706,\n  "code" : "006",\n  "name" : "Revenue management "\n}	1	admin	2020-09-17 10:01:24.582
2582	2665	org.tamisemi.iftmis.domain.FindingSubCategory	CREATE	{\n  "createdBy" : "admin",\n  "createdDate" : "2020-09-17T10:01:56.509Z",\n  "lastModifiedBy" : "admin",\n  "lastModifiedDate" : "2020-09-17T10:01:56.509Z",\n  "id" : 2665,\n  "code" : "017",\n  "name" : "Expired business license not renewed"\n}	1	admin	2020-09-17 10:01:56.509
2583	2666	org.tamisemi.iftmis.domain.FindingSubCategory	CREATE	{\n  "createdBy" : "admin",\n  "createdDate" : "2020-09-17T10:02:22.561Z",\n  "lastModifiedBy" : "admin",\n  "lastModifiedDate" : "2020-09-17T10:02:22.561Z",\n  "id" : 2666,\n  "code" : "018",\n  "name" : "Inadequate controls over defective POS devices"\n}	1	admin	2020-09-17 10:02:22.561
2584	2667	org.tamisemi.iftmis.domain.FindingSubCategory	CREATE	{\n  "createdBy" : "admin",\n  "createdDate" : "2020-09-17T10:02:43.441Z",\n  "lastModifiedBy" : "admin",\n  "lastModifiedDate" : "2020-09-17T10:02:43.441Z",\n  "id" : 2667,\n  "code" : "019",\n  "name" : "POS Devices in store not registered in the LGRCIS"\n}	1	admin	2020-09-17 10:02:43.441
2585	2668	org.tamisemi.iftmis.domain.FindingSubCategory	CREATE	{\n  "createdBy" : "admin",\n  "createdDate" : "2020-09-17T10:03:34.173Z",\n  "lastModifiedBy" : "admin",\n  "lastModifiedDate" : "2020-09-17T10:03:34.173Z",\n  "id" : 2668,\n  "code" : "020",\n  "name" : "Unjustified revenue collected through offline POS machines  "\n}	1	admin	2020-09-17 10:03:34.173
2586	2669	org.tamisemi.iftmis.domain.FindingSubCategory	CREATE	{\n  "createdBy" : "admin",\n  "createdDate" : "2020-09-17T10:03:53.936Z",\n  "lastModifiedBy" : "admin",\n  "lastModifiedDate" : "2020-09-17T10:03:53.936Z",\n  "id" : 2669,\n  "code" : "021",\n  "name" : "Adjustments, reversal and cancellation of revenue transactions not approved by the accounting officer"\n}	1	admin	2020-09-17 10:03:53.936
2587	2707	org.tamisemi.iftmis.domain.FindingCategory	CREATE	{\n  "createdBy" : "admin",\n  "createdDate" : "2020-09-17T10:04:18.918Z",\n  "lastModifiedBy" : "admin",\n  "lastModifiedDate" : "2020-09-17T10:04:18.918Z",\n  "id" : 2707,\n  "code" : "022",\n  "name" : "Expenditure management"\n}	1	admin	2020-09-17 10:04:18.918
2588	2708	org.tamisemi.iftmis.domain.FindingCategory	CREATE	{\n  "createdBy" : "admin",\n  "createdDate" : "2020-09-17T10:05:10.015Z",\n  "lastModifiedBy" : "admin",\n  "lastModifiedDate" : "2020-09-17T10:05:10.015Z",\n  "id" : 2708,\n  "code" : "023",\n  "name" : "Missing payment vouchers"\n}	1	admin	2020-09-17 10:05:10.015
2589	2709	org.tamisemi.iftmis.domain.FindingCategory	CREATE	{\n  "createdBy" : "admin",\n  "createdDate" : "2020-09-17T10:06:10.690Z",\n  "lastModifiedBy" : "admin",\n  "lastModifiedDate" : "2020-09-17T10:06:10.690Z",\n  "id" : 2709,\n  "code" : "024",\n  "name" : "Questionable EFD receipts"\n}	1	admin	2020-09-17 10:06:10.69
2590	2710	org.tamisemi.iftmis.domain.FindingCategory	CREATE	{\n  "createdBy" : "admin",\n  "createdDate" : "2020-09-17T10:06:35.818Z",\n  "lastModifiedBy" : "admin",\n  "lastModifiedDate" : "2020-09-17T10:06:35.818Z",\n  "id" : 2710,\n  "code" : "025",\n  "name" : "Inadequately supported payments"\n}	1	admin	2020-09-17 10:06:35.818
2591	2711	org.tamisemi.iftmis.domain.FindingCategory	CREATE	{\n  "createdBy" : "admin",\n  "createdDate" : "2020-09-17T10:06:54.313Z",\n  "lastModifiedBy" : "admin",\n  "lastModifiedDate" : "2020-09-17T10:06:54.313Z",\n  "id" : 2711,\n  "code" : "026",\n  "name" : "20% of GPG funds not transferred to lower levels "\n}	1	admin	2020-09-17 10:06:54.313
2592	2712	org.tamisemi.iftmis.domain.FindingCategory	CREATE	{\n  "createdBy" : "admin",\n  "createdDate" : "2020-09-17T10:07:07.512Z",\n  "lastModifiedBy" : "admin",\n  "lastModifiedDate" : "2020-09-17T10:07:07.512Z",\n  "id" : 2712,\n  "code" : "027",\n  "name" : "Late release of other charges funds"\n}	1	admin	2020-09-17 10:07:07.512
2593	2713	org.tamisemi.iftmis.domain.FindingCategory	CREATE	{\n  "createdBy" : "admin",\n  "createdDate" : "2020-09-17T10:07:27.948Z",\n  "lastModifiedBy" : "admin",\n  "lastModifiedDate" : "2020-09-17T10:07:27.948Z",\n  "id" : 2713,\n  "code" : "027",\n  "name" : "Received funds not allocated to cost centres"\n}	1	admin	2020-09-17 10:07:27.948
2594	2714	org.tamisemi.iftmis.domain.FindingCategory	CREATE	{\n  "createdBy" : "admin",\n  "createdDate" : "2020-09-17T10:07:33.671Z",\n  "lastModifiedBy" : "admin",\n  "lastModifiedDate" : "2020-09-17T10:07:33.671Z",\n  "id" : 2714,\n  "code" : "028",\n  "name" : "Received funds not allocated to cost centres"\n}	1	admin	2020-09-17 10:07:33.671
2595	2715	org.tamisemi.iftmis.domain.FindingCategory	CREATE	{\n  "createdBy" : "admin",\n  "createdDate" : "2020-09-17T10:08:08.234Z",\n  "lastModifiedBy" : "admin",\n  "lastModifiedDate" : "2020-09-17T10:08:08.234Z",\n  "id" : 2715,\n  "code" : "029",\n  "name" : "Unapplied payments not cleared"\n}	1	admin	2020-09-17 10:08:08.234
2596	2716	org.tamisemi.iftmis.domain.FindingCategory	CREATE	{\n  "createdBy" : "admin",\n  "createdDate" : "2020-09-17T10:08:26.813Z",\n  "lastModifiedBy" : "admin",\n  "lastModifiedDate" : "2020-09-17T10:08:26.813Z",\n  "id" : 2716,\n  "code" : "030",\n  "name" : "Tax withheld not submitted to the commissioner "\n}	1	admin	2020-09-17 10:08:26.813
2597	2717	org.tamisemi.iftmis.domain.FindingCategory	CREATE	{\n  "createdBy" : "admin",\n  "createdDate" : "2020-09-17T10:08:44.060Z",\n  "lastModifiedBy" : "admin",\n  "lastModifiedDate" : "2020-09-17T10:08:44.060Z",\n  "id" : 2717,\n  "code" : "031",\n  "name" : "Payments not supported with electronic fiscal receipts"\n}	1	admin	2020-09-17 10:08:44.06
2598	2718	org.tamisemi.iftmis.domain.FindingCategory	CREATE	{\n  "createdBy" : "admin",\n  "createdDate" : "2020-09-17T10:08:57.822Z",\n  "lastModifiedBy" : "admin",\n  "lastModifiedDate" : "2020-09-17T10:08:57.822Z",\n  "id" : 2718,\n  "code" : "032",\n  "name" : "Unretired imprest"\n}	1	admin	2020-09-17 10:08:57.822
3302	3201	org.tamisemi.iftmis.domain.Finding	CREATE	{\n  "createdBy" : "admin",\n  "createdDate" : "2020-09-18T06:16:35.963Z",\n  "lastModifiedBy" : "admin",\n  "lastModifiedDate" : "2020-09-18T06:16:35.963Z",\n  "id" : 3201,\n  "source" : "CAG",\n  "code" : "001",\n  "description" : "Revenue collected but not banked timely",\n  "actionPlanCategory" : "LOW",\n  "isClosed" : null,\n  "organisationUnit" : {\n    "createdBy" : null,\n    "createdDate" : "2020-09-18T06:16:35.922Z",\n    "lastModifiedBy" : null,\n    "lastModifiedDate" : "2020-09-18T06:16:35.922Z",\n    "id" : 3102,\n    "code" : null,\n    "name" : null,\n    "address" : null,\n    "phoneNumber" : null,\n    "email" : null,\n    "background" : null,\n    "logo" : null,\n    "logoContentType" : null,\n    "organisationUnitLevel" : null,\n    "parent" : null\n  },\n  "findingRecommendations" : [ {\n    "createdBy" : "admin",\n    "createdDate" : "2020-09-18T06:16:35.971Z",\n    "lastModifiedBy" : "admin",\n    "lastModifiedDate" : "2020-09-18T06:16:35.971Z",\n    "id" : 3251,\n    "description" : "After close supervision revenue was banked to council own source revenue account",\n    "implementationStatus" : "IMPLEMENTED",\n    "findingResponses" : [ ]\n  } ]\n}	1	admin	2020-09-18 06:16:35.963
2599	2801	org.tamisemi.iftmis.domain.Risk	CREATE	{\n  "createdBy" : "admin",\n  "createdDate" : "2020-09-17T11:14:35.402Z",\n  "lastModifiedBy" : "admin",\n  "lastModifiedDate" : "2020-09-17T11:14:35.402Z",\n  "id" : 2801,\n  "code" : "001",\n  "description" : "Revenue Collected and not not banked timely",\n  "riskRegister" : {\n    "createdBy" : null,\n    "createdDate" : "2020-09-17T11:14:35.391Z",\n    "lastModifiedBy" : null,\n    "lastModifiedDate" : "2020-09-17T11:14:35.391Z",\n    "id" : 2602,\n    "name" : null,\n    "isApproved" : null,\n    "approvedDate" : null,\n    "approvedBy" : null,\n    "organisationUnit" : null,\n    "financialYear" : null\n  },\n  "objective" : {\n    "createdBy" : null,\n    "createdDate" : "2020-09-17T11:14:35.391Z",\n    "lastModifiedBy" : null,\n    "lastModifiedDate" : "2020-09-17T11:14:35.391Z",\n    "id" : 1301,\n    "code" : null,\n    "description" : null\n  },\n  "riskCategory" : {\n    "createdBy" : null,\n    "createdDate" : "2020-09-17T11:14:35.391Z",\n    "lastModifiedBy" : null,\n    "lastModifiedDate" : "2020-09-17T11:14:35.391Z",\n    "id" : 1952,\n    "code" : null,\n    "name" : null\n  },\n  "riskRatings" : [ {\n    "createdBy" : "admin",\n    "createdDate" : "2020-09-17T11:14:35.409Z",\n    "lastModifiedBy" : "admin",\n    "lastModifiedDate" : "2020-09-17T11:14:35.409Z",\n    "id" : 2851,\n    "source" : "COUNCIL",\n    "impact" : 1,\n    "likelihood" : 1,\n    "comments" : "Revenue haziendi bank kwa wakati"\n  }, {\n    "createdBy" : "admin",\n    "createdDate" : "2020-09-17T11:14:35.411Z",\n    "lastModifiedBy" : "admin",\n    "lastModifiedDate" : "2020-09-17T11:14:35.411Z",\n    "id" : 2852,\n    "source" : "INSPECTOR",\n    "impact" : 2,\n    "likelihood" : 4,\n    "comments" : "Makusanyo hayarekodiwi ipasavyo"\n  } ]\n}	1	admin	2020-09-17 11:14:35.402
2600	2802	org.tamisemi.iftmis.domain.Risk	CREATE	{\n  "createdBy" : "admin",\n  "createdDate" : "2020-09-17T11:30:08.481Z",\n  "lastModifiedBy" : "admin",\n  "lastModifiedDate" : "2020-09-17T11:30:08.481Z",\n  "id" : 2802,\n  "code" : "001",\n  "description" : "Collecting Revenue and Not banking timely",\n  "riskRegister" : {\n    "createdBy" : null,\n    "createdDate" : "2020-09-17T11:30:08.480Z",\n    "lastModifiedBy" : null,\n    "lastModifiedDate" : "2020-09-17T11:30:08.480Z",\n    "id" : 2602,\n    "name" : null,\n    "isApproved" : null,\n    "approvedDate" : null,\n    "approvedBy" : null,\n    "organisationUnit" : null,\n    "financialYear" : null\n  },\n  "objective" : {\n    "createdBy" : null,\n    "createdDate" : "2020-09-17T11:30:08.480Z",\n    "lastModifiedBy" : null,\n    "lastModifiedDate" : "2020-09-17T11:30:08.480Z",\n    "id" : 1301,\n    "code" : null,\n    "description" : null\n  },\n  "riskCategory" : {\n    "createdBy" : null,\n    "createdDate" : "2020-09-17T11:30:08.480Z",\n    "lastModifiedBy" : null,\n    "lastModifiedDate" : "2020-09-17T11:30:08.480Z",\n    "id" : 1952,\n    "code" : null,\n    "name" : null\n  },\n  "riskRatings" : [ {\n    "createdBy" : "admin",\n    "createdDate" : "2020-09-17T11:30:08.482Z",\n    "lastModifiedBy" : "admin",\n    "lastModifiedDate" : "2020-09-17T11:30:08.482Z",\n    "id" : 2853,\n    "source" : "INSPECTOR",\n    "impact" : 3,\n    "likelihood" : 2,\n    "comments" : "xxxxxxx"\n  }, {\n    "createdBy" : "admin",\n    "createdDate" : "2020-09-17T11:30:08.483Z",\n    "lastModifiedBy" : "admin",\n    "lastModifiedDate" : "2020-09-17T11:30:08.483Z",\n    "id" : 2854,\n    "source" : "COUNCIL",\n    "impact" : 2,\n    "likelihood" : 2,\n    "comments" : "xxxxxxx"\n  } ]\n}	1	admin	2020-09-17 11:30:08.481
3001	2951	org.tamisemi.iftmis.domain.RiskRating	CREATE	{\n  "createdBy" : "admin",\n  "createdDate" : "2020-09-17T11:45:43.503Z",\n  "lastModifiedBy" : "admin",\n  "lastModifiedDate" : "2020-09-17T11:45:43.503Z",\n  "id" : 2951,\n  "source" : "COUNCIL",\n  "impact" : 1,\n  "likelihood" : 1,\n  "comments" : "No Comment",\n  "risk" : {\n    "createdBy" : "admin",\n    "createdDate" : "2020-09-17T11:45:43.463Z",\n    "lastModifiedBy" : "admin",\n    "lastModifiedDate" : "2020-09-17T11:45:43.463Z",\n    "id" : 2901,\n    "code" : "001",\n    "description" : "Collecting revenue and not banking in time",\n    "riskRegister" : {\n      "createdBy" : null,\n      "createdDate" : "2020-09-17T11:45:43.359Z",\n      "lastModifiedBy" : null,\n      "lastModifiedDate" : "2020-09-17T11:45:43.359Z",\n      "id" : 2602,\n      "name" : null,\n      "isApproved" : null,\n      "approvedDate" : null,\n      "approvedBy" : null,\n      "organisationUnit" : null,\n      "financialYear" : null\n    },\n    "objective" : {\n      "createdBy" : null,\n      "createdDate" : "2020-09-17T11:45:43.359Z",\n      "lastModifiedBy" : null,\n      "lastModifiedDate" : "2020-09-17T11:45:43.359Z",\n      "id" : 1301,\n      "code" : null,\n      "description" : null\n    },\n    "riskCategory" : {\n      "createdBy" : null,\n      "createdDate" : "2020-09-17T11:45:43.359Z",\n      "lastModifiedBy" : null,\n      "lastModifiedDate" : "2020-09-17T11:45:43.359Z",\n      "id" : 1951,\n      "code" : null,\n      "name" : null\n    }\n  }\n}	1	admin	2020-09-17 11:45:43.503
3002	2952	org.tamisemi.iftmis.domain.RiskRating	CREATE	{\n  "createdBy" : "admin",\n  "createdDate" : "2020-09-17T11:45:43.507Z",\n  "lastModifiedBy" : "admin",\n  "lastModifiedDate" : "2020-09-17T11:45:43.507Z",\n  "id" : 2952,\n  "source" : "INSPECTOR",\n  "impact" : 1,\n  "likelihood" : 2,\n  "comments" : "No comment",\n  "risk" : {\n    "createdBy" : "admin",\n    "createdDate" : "2020-09-17T11:45:43.463Z",\n    "lastModifiedBy" : "admin",\n    "lastModifiedDate" : "2020-09-17T11:45:43.463Z",\n    "id" : 2901,\n    "code" : "001",\n    "description" : "Collecting revenue and not banking in time",\n    "riskRegister" : {\n      "createdBy" : null,\n      "createdDate" : "2020-09-17T11:45:43.359Z",\n      "lastModifiedBy" : null,\n      "lastModifiedDate" : "2020-09-17T11:45:43.359Z",\n      "id" : 2602,\n      "name" : null,\n      "isApproved" : null,\n      "approvedDate" : null,\n      "approvedBy" : null,\n      "organisationUnit" : null,\n      "financialYear" : null\n    },\n    "objective" : {\n      "createdBy" : null,\n      "createdDate" : "2020-09-17T11:45:43.359Z",\n      "lastModifiedBy" : null,\n      "lastModifiedDate" : "2020-09-17T11:45:43.359Z",\n      "id" : 1301,\n      "code" : null,\n      "description" : null\n    },\n    "riskCategory" : {\n      "createdBy" : null,\n      "createdDate" : "2020-09-17T11:45:43.359Z",\n      "lastModifiedBy" : null,\n      "lastModifiedDate" : "2020-09-17T11:45:43.359Z",\n      "id" : 1951,\n      "code" : null,\n      "name" : null\n    }\n  }\n}	1	admin	2020-09-17 11:45:43.507
3303	3351	org.tamisemi.iftmis.domain.FindingResponse	CREATE	{\n  "createdBy" : "admin",\n  "createdDate" : "2020-09-18T06:17:02.137Z",\n  "lastModifiedBy" : "admin",\n  "lastModifiedDate" : "2020-09-18T06:17:02.137Z",\n  "id" : 3351,\n  "source" : "AUDITOR",\n  "description" : "Bana weeee",\n  "recommendation" : {\n    "createdBy" : null,\n    "createdDate" : "2020-09-18T06:17:02.129Z",\n    "lastModifiedBy" : null,\n    "lastModifiedDate" : "2020-09-18T06:17:02.129Z",\n    "id" : 3251,\n    "description" : null,\n    "implementationStatus" : null,\n    "finding" : null\n  }\n}	1	admin	2020-09-18 06:17:02.137
3304	3352	org.tamisemi.iftmis.domain.FindingResponse	CREATE	{\n  "createdBy" : "admin",\n  "createdDate" : "2020-09-18T06:17:12.515Z",\n  "lastModifiedBy" : "admin",\n  "lastModifiedDate" : "2020-09-18T06:17:12.515Z",\n  "id" : 3352,\n  "source" : "INSPECTOR",\n  "description" : "weee jamaaa helaaaa",\n  "recommendation" : {\n    "createdBy" : null,\n    "createdDate" : "2020-09-18T06:17:12.513Z",\n    "lastModifiedBy" : null,\n    "lastModifiedDate" : "2020-09-18T06:17:12.513Z",\n    "id" : 3251,\n    "description" : null,\n    "implementationStatus" : null,\n    "finding" : null\n  }\n}	1	admin	2020-09-18 06:17:12.515
3003	2901	org.tamisemi.iftmis.domain.Risk	CREATE	{\n  "createdBy" : "admin",\n  "createdDate" : "2020-09-17T11:45:43.463Z",\n  "lastModifiedBy" : "admin",\n  "lastModifiedDate" : "2020-09-17T11:45:43.463Z",\n  "id" : 2901,\n  "code" : "001",\n  "description" : "Collecting revenue and not banking in time",\n  "riskRegister" : {\n    "createdBy" : null,\n    "createdDate" : "2020-09-17T11:45:43.359Z",\n    "lastModifiedBy" : null,\n    "lastModifiedDate" : "2020-09-17T11:45:43.359Z",\n    "id" : 2602,\n    "name" : null,\n    "isApproved" : null,\n    "approvedDate" : null,\n    "approvedBy" : null,\n    "organisationUnit" : null,\n    "financialYear" : null\n  },\n  "objective" : {\n    "createdBy" : null,\n    "createdDate" : "2020-09-17T11:45:43.359Z",\n    "lastModifiedBy" : null,\n    "lastModifiedDate" : "2020-09-17T11:45:43.359Z",\n    "id" : 1301,\n    "code" : null,\n    "description" : null\n  },\n  "riskCategory" : {\n    "createdBy" : null,\n    "createdDate" : "2020-09-17T11:45:43.359Z",\n    "lastModifiedBy" : null,\n    "lastModifiedDate" : "2020-09-17T11:45:43.359Z",\n    "id" : 1951,\n    "code" : null,\n    "name" : null\n  },\n  "riskRatings" : [ {\n    "createdBy" : "admin",\n    "createdDate" : "2020-09-17T11:45:43.503Z",\n    "lastModifiedBy" : "admin",\n    "lastModifiedDate" : "2020-09-17T11:45:43.503Z",\n    "id" : 2951,\n    "source" : "COUNCIL",\n    "impact" : 1,\n    "likelihood" : 1,\n    "comments" : "No Comment"\n  }, {\n    "createdBy" : "admin",\n    "createdDate" : "2020-09-17T11:45:43.507Z",\n    "lastModifiedBy" : "admin",\n    "lastModifiedDate" : "2020-09-17T11:45:43.507Z",\n    "id" : 2952,\n    "source" : "INSPECTOR",\n    "impact" : 1,\n    "likelihood" : 2,\n    "comments" : "No comment"\n  } ]\n}	1	admin	2020-09-17 11:45:43.463
3004	1	org.tamisemi.iftmis.domain.OrganisationUnitLevel	UPDATE	{\n  "createdBy" : null,\n  "createdDate" : "2020-09-17T12:08:49.129Z",\n  "lastModifiedBy" : "admin",\n  "lastModifiedDate" : "2020-09-17T12:08:49.142Z",\n  "id" : 1,\n  "code" : "PORALG",\n  "name" : "Default Level",\n  "level" : 1,\n  "isInspectionLevel" : true\n}	1	admin	2020-09-17 12:08:49.142
3005	3051	org.tamisemi.iftmis.domain.OrganisationUnitLevel	CREATE	{\n  "createdBy" : "admin",\n  "createdDate" : "2020-09-17T12:09:13.732Z",\n  "lastModifiedBy" : "admin",\n  "lastModifiedDate" : "2020-09-17T12:09:13.732Z",\n  "id" : 3051,\n  "code" : "REGION",\n  "name" : "Region",\n  "level" : 2,\n  "isInspectionLevel" : true\n}	1	admin	2020-09-17 12:09:13.732
3006	3052	org.tamisemi.iftmis.domain.OrganisationUnitLevel	CREATE	{\n  "createdBy" : "admin",\n  "createdDate" : "2020-09-17T12:09:51.934Z",\n  "lastModifiedBy" : "admin",\n  "lastModifiedDate" : "2020-09-17T12:09:51.934Z",\n  "id" : 3052,\n  "code" : "COUNCIL",\n  "name" : "Council",\n  "level" : 3,\n  "isInspectionLevel" : true\n}	1	admin	2020-09-17 12:09:51.934
3007	3101	org.tamisemi.iftmis.domain.OrganisationUnit	CREATE	{\n  "createdBy" : "admin",\n  "createdDate" : "2020-09-17T12:12:11.293Z",\n  "lastModifiedBy" : "admin",\n  "lastModifiedDate" : "2020-09-17T12:12:11.293Z",\n  "id" : 3101,\n  "code" : "DOM",\n  "name" : "Dodoma",\n  "address" : "Dodoma",\n  "phoneNumber" : null,\n  "email" : "ras@dodoma.go.tz",\n  "background" : "Dodoma",\n  "logo" : "iVBORw0KGgoAAAANSUhEUgAAAgAAAAIACAYAAAD0eNT6AAAABHNCSVQICAgIfAhkiAAAAAlwSFlzAAALEwAACxMBAJqcGAAAABl0RVh0U29mdHdhcmUAd3d3Lmlua3NjYXBlLm9yZ5vuPBoAACAASURBVHic7d13vN9Vffjx180GAiEQtoCAEgdYAgLaWgwhOH/aOkAqbi2utrZ11VpbbWudrdY6W61W3AsV26rIqjhAjYNhCENG2IlhhBjIuL8/zv2au+/33nM+53zG6/l4fB65Gfd835+T+/2e9+dMkCRJkiRJkiRJkiRJkiRJkiRJkiRJkiRJkiRJkiRJkiRJkiRJKQ2UDkBS480CjgNWAMcAS4H9gYVDf78RuBm4EvgxcD5wCbA9e6SSJCnaA4C3AWuBwWleNw597wOyRy1JkmZkT+BDwH1Mv+EffW0GPjhUpiRJqqnTgHXEN/yjrzuAUzPehyRJ6sMcwlN/6oZ/9PWBodeSJEmF7QScTfWNf+/6+tBrSpKkQmYDXyFf49+7voE9AZIkFZOj23+i6/0Z7k+SJI1yGuUa/951SuV3KUmSfmsPwsz80gnAOmBJxfcqSZKGlOz6H339W8X3KkmSCLvzpdjkJ9W1GTig0juWOmhW6QAk1c4rgXmlgxhmPvCK0kFIktRms4AbKP/UP/paS1iSKEmSKvAoyjf2E13HVXjfUuc4BCBpuJNKBzCJE0sHILWJCYCk4VI0smsJ6/d3G7qeAVyVoNwVCcqQJEnjWE9cN/2NhD0ERtuDkBjElH1H4nuVJElDthLXSD9zkrJPjSx7a7K7lCRJI8RO1Nt1krIXJShfUiIDpQOQVCuxjexUnylVly+pT04ClCSpg0wAJEnqIBMASZI6yPE0lTAAHEVY133c0LU/MBd/JqW62wZsBC4FzgPOB74P3F8yKE2fH7bKaQnwcuD5wGGFY5GUznrgs8D7gSsLx6I+mQAoh4XAmwinzO1SOBZJ1dkOfAF4PeFQKdWYp2upao8Hvg08kXodMSspvQHgCOAMwjDBJWXD0WTsAVBVBoC3AG/EyaZSV50FPI+QDKhmTABUhdnAR4EXFI5DUnmXAE8G1pUORCOZACi1AULj/6LSgUiqjR8TVv3cUzoQ7WDXrFL7G2z8JY30SMIqAR86a8RJgErpROBjmFhKGutwYBPwvdKBKDAbUyo7ETYGcX2/pIncBxwJXFU6EPmkpnReg42/pMnNB95TOggF9gAohV2B64A9CschqRmOJUwMVEH2ACiF52PjL6l/f1k6ANkDoDR+RJjlK0n9+A2wL3B36UC6zB4AxToQG39J07MT8ITSQXSdCYBirSgdgKRG8rNDarj3AYOR1zrC5kGLM8cuaXp2Ak4mbO8b+753EqDUcN8i7kPgTuCBuYOWFGU+sIq49/7dOA9NarRfEPch8Kr8IUtK4FjiewF2zR61pGSuJe4D4CH5Q5aUyHri3v/75w9ZPU4CVGl2AUpSASYAirUx8vtXJolCUm7HEL8BmMcDSw0WOwnwVuDg7FFLirGAcKqfkwClDkuxDHA98GLcTliqu52BxxOW8MW+710GWNic0gGo8VK8ifcAPjp0SeoGE4DCnAOgWOeXDkBSI51XOoCuc/xFKVxCWBMsSf3YBOyHhwEVZQ+AUvhk6QAkNcpXsfEvzh4ApbArcB1O4pPUn0cCPykdRNfZA6AU7gHeUzoISY3wDWz8a8EeAKWyALgMOKx0IJJqazNwJHB16UBkD4DS2UxYy7+tdCCSautN2PjXxuzSAahVrickACtKByKpds4G/qx0ENrBBECpXQQ8ADi6dCCSauNHwFOB+0sHoh1MAFSF/wYOBJaVDkRScRcDTwLuKh2IRjIBUBUGga8PfX0CTjaVuurLwNOw8a8lP5hVtZXAx4CDSgciKZuNwF8BHygdiCZmD4Cqdi3wH0NfLwPmFYxFUrW2AZ8hPPWfWzgWTcEeAOW0J/AywpPBwsKxSEpnO/BBwvHgVxWORX0yAVBui4FbgPmlA5GU1OOBb5cOQlJ9vZQwSbCK68yM99FW24j7P5hqc7HY/2PFmQ3cRjXvv/9Akibxf1SXAGzEoYVYJgDtdhLVvf/WAXPy3YpiuRWwcjoIeEyF5e9C2GxE0vhOqbDsPXEX0EYxAVBOp1P9vJPTKy5faqrZhNn5VaoywZDUYJdSXfdj79oC7JPrhlrIIYD2qrL732GABrIHQLkcBRyR4XXmAKdmeB2paXI8ne8JnJjhdZSACYByydk17zCANFKO7v8ehwEk/dYs4Eaq734cfh2e5c7axyGAdsrR/e8wQMPYA6AcTiQcEZzTH2V+PanOcj6VOwzQECYAyqFEl/xzcKdLCfJ2//c4DCCJBcAG8nb/967jMtxf2zgE0D45u/8dBmgQewBUtacAuxd6bScDSmWexh0GkMRXKfP0P0jY89ynkOmxB6Bdqtz7f6rr3zPcn6SaWgxsplwCMAg8ofK7bBcTgHYp0f3fuxwGqDmHAFSlUyl/7K/DAOqykpPxHAaQOizFyX/3RH6/JwROjz0A7VGy+793OQwgddBBwHbiPjyuBv4zsoxB4NkV32ubmAC0R4ru/02R338HDgPUlkMAqkqKdfifAj6dIBaHAdRFKbr/z438/iU4DCB1ToqT/w4nJKlrI8vxhMD+2QPQDim6/+8ATo4sYxCHAaROOYr4D40fDivv3QnK+5OK7rVtTADaIUX3/0cIicStkeU4DFBTDgGoCim63D89wdcz5TCAuiRF9/8XCQnhWZHlOAwgdUSKk//G67JPNaSgydkD0Hypuv97T+0rIssaxGGAWrIHQKmlOPnvHMIH2HCfjSwT4LQEZUh1txzYO7KMrwBbh76+kLHvx+l6Gg4DSK2XYtneeN31BxO/rPAqPCFwKvYANN+Hia/nlaPK/FCCMk+u4F4l1USKk/8m27jnu5FlDwLHprvdVjIBaLbU3f89DgO0kEMASinFyX9fJSQB43EyoDS55aTt/u9xGEDSpFKc/PfEScrfg/jDhTwhcHL2ADRbFd3/PQ4DSBpXipP/bmfqxvlrka8xCDw+/nZbywSguarq/u9xGKBlHAJQKilO/vssY7seR3MYQBrfcqrp/u9xGEDSuFKc/HdcH6+zALgz8nU8IXBi9gA0V5Xd/z0OA0gaIdXJf/0u0ft45GsNAn8089ttNROAZqq6+7/HYYAWcQhAKaQ6+a/fD3iHAaSRllNt93/PhYS5OjEcBpBaJPc2vZ4QWB17AJopR/d/j8MAkoD0J//1658TvK4nBI5lAtA8ubr/exwGkATAu4j/MPjTGbzu0Qle9wczeN22MwFonlRH//Yrd8IhqYaqOvmvX5dFvvYg8OAZvnZbmQA0T87u/54UwwDTfU0l5iRAxajq5L9+pTgh0NUAarLZhEl1MdYBF0zze74Y+ZoApyQoQ1IhVZ3816+DSXNCoHawB6BZcnf/9zgMIHVY1Sf/9euiyBgGgUdGxtAmJgDNUqL7v8dhgIZzCEAzVfXJf/1yTwB1Vanu/x6HAaSOqvrkv37tAdwXGYcnBO5gD0BzlOr+73EYQOqgXCf/9evrkbEM4gmBPSYAzVGy+79OMWiGHALQTOQ6+a9fDgOoa0p3//c4DCB1TK6T//qV6oTAXRLG1FT2ADRD6e7/HocBGsweAE3XQcBjIsu4BvhRglh6NgNnRZaxC/DUBLFIOaR4ak7x9L6N+PfeEsJhRsrMBEDTlfvkv345DKCuqEv3f4/DAFJH5D75r1+eEJiGQwD1V5fu/x6HARrKHgBNxzLgiMgyLgbWJIhltO3A5yPLmAM8M0EsUpXq0v3f4zCA1AHvJv7JYyYn//UrxQmB368wviawB6De6vq0XbdeCUkJlT75r1+eEBjHBKDe6trQ1jUx0SQcAlC/Sp/8168UJwSelqAMqQp16/7vcRhAarHSJ//162DiTwisYo5CU9gDUF91f8qua++EpAh1OfmvX54QOHMmAPVV9wa27gmKRnEIQP2oy8l//XJPALVRXbv/exwGkFqoLif/9csTAmfOHoB6asrTdd17KSRNQ91O/utXihMCH5c55jowAainpjSsTUlUhEMAmtqziD/573OkO/mvXw4DqE3q3v3f4zCA1CLfJf7J4/jsUXtC4EzZA1A/TXuqbkpvhaRJHET8krqriT88aKY+0Ud8U11d2xPABKB+mtagNi1h6SyHADSZup781y+HAdQGTen+73EYQGqBup78169UJwTunTvwguwBqJemPk03rddC0jDLiH8D/zB71GP9C/H38YrsUZdjAlAvTW1Im5q4dIpDAJpIiq7vFF3wsRwGUJM1rfu/x2EAqaGacvJfvzwhsH/2ANRH05+im9p70Rn2AGg8TTn5r1+fS1BG11YDqLzlxM8/+Qr59+DouYCwCViMp+MwgJRVU07+69fBeEJgv+wBqI8PE19fK7NHPVKKezgpe9RSRzXt5L9+fY/4D6JjskednwlAPTS9+78nxTDAh7NH3REOAWi0pp381y8nA6pJltPs7v+eC4gfBngG5RMZqROadvJfvzwhsD/2ANRDG7r/exwGkBqgqSf/9ets4j+I2n5CoAlAeW3p/u9xGKCmHALQcE09+a9fDgOoCZbTju7/ngtwGECqvaae/NcvTwicmj0A5bWp+7/nI8Tfk8MAUkWafvJfvz5B/AdRm/cEMAEoq23d/z0rif+/dxggMYcA1NP0k//65TCA6mw57er+7zkfhwGk2mr6yX/98oTAydkDUFYbu/97HAaQaqgtJ//1yxMCJ2YCUE5bu/97HAaQaujdxL8x/zR71DN3DPH3+73sUedhAlBO2w/PaXuCIzVO207+69flxN3zduDQ7FFXzwSgnDZ3//c4DFAjTgJU207+61fsCYEDwLNTBCIRno6fFlnGOsKa+zr7YoIyTklQhiTad/Jfvw4mftnjL7NHXT17AMpoe/d/j8MAUk2k2hinbif/9csTAscyASijC93/PQ4D1IRDAN32FGBRZBl1PPmvX+4JoDroSvd/j8MAUg209eS/fqU4IfBmwgd4W9gDkF9Xuv97HAaQCmv7yX/9SnFC4MnZo66OCUB+Xer+73EYoAYcAuiutp/81y+HAVRS17r/exwGkApq+8l//dqJ+ImQdwM75w68IvYA5NW17v8ehwGkQg6mGyf/9eu/iP8Qflb2qKthApBXF7v/exwGKMwhgG46nW6c/NcvhwFUQle7/3scBpAK6MrJf/1KcULg/cCS3IFXwB6AfLra/d/jMEBh9gB0zzLgiMgyLgbWJIilLrYT/zQyF59GND0pfl5SPEWXso2wFDnGEuCxCWKROqFrJ//165HE18tF2aNOzx6APHz6DTwiWMqkqyf/9csTAk0AcknR8DW5+7/HRKgghwC6pasn//XLEwKVS9e7/3scBpAy6erJf/16IJ4QaA9A9XzqHclhAKliXT/5r1/fJ/7D6OjsUadjAlA9G7yRTIgKcQigO7p+8l+/3BNAVbP7f6RUwwAnJIilU9qyk5um9lXgDyLL2EiYBNhmA8DukWXcAhxI+GBrmm3EPRjMJgyjTCT2Kb7pn1mzCSdI7h1RxjpgP5p/DsdwKwnzi2J8GHh5glikVklx8p/X9K6mbs/qEEC17P4fn8MABTgE0A2nEX/yn6bnJaUDUC3Z/T8+hwGkiqyn/BNx167tNPOEQHsAquNT7uRS9I58KHvUDWYPQPutBPYoHUQHDeCHkUY6kbixf4Av066x/+HOB26PLOOZtDdBSs4EoP3+tXQAHebZABrO7v/JbQO+FlmGwwDTYALQfg8qHUCH7UTzZ60rjdnAH0aWsQ64MEEsdeYRwRmZALTf3NIBdNzDSgegWrD7vz/n4TBANlaSVK3YzZfUDimeSp8NnJqgnLqLnTzbGwY4L0EsrWYC0H7bCd2PKuMXpQNQcSm6/wF2TVBGV5yCCcCUHAJov3tKB9Bx95YOQMWl6P7X9DwbH3CnZALQfqtLB9Bhvd0X1W3vKh1AB+1GN4ZLopgAtN/a0gF02LrSAai43YGjSgfRUe8pHUDdmQC0302lA+gwky+5D0c5e+O8iUmZALTflaUD6LA1pQNQcY8rHUDH/VnpAOrMBKD9TADKcf6FXAZa1nGlA6gzZ0m2X4oE4OnABQnKaZJFwNXELaE0+ZIPWWV5CuokTADa72bgbsKs2Jl6FHBWmnAaYwXx+yeYAOg32AiV5HtwEman7TcIfD+yjGfTvZ+VZ0d+/6+BX6YIRI12Y+kAOi72s6/Vuvah3lXnR37/A4DHpgikIRYDT44s43zCLozqtp+VDqDj7iwdQJ2ZAHTDuQnKeFGCMpridOK7bWOTLrXDXaUD6Dh3Qp2ECUA3/AzYEFnGs4BDEsRSd3OBv0xQToqkS813c+kAOs69OCZhAtAN24BvR5YxF3htgljq7jnEJzrX4RJABf4clLMJE4BJmQB0x6cSlPFC4NAE5dTVAuCNCcpJUddqB2ehl7MG5+FMymWA3fFN4DZgn4gyFgAfBJ6QJKL6eT1wWIJyTADUczWwlbjP2pcBX0gTTqN8ETgp4vtNvqRh3ktYFhh7PS134BkcRlizHVs3P8wdeGLbiLv/qXoVY+u3idYQd8+fyB5xeQsIE/hi6u3NuYOW6mwZaRKAW4H9MsdepbnA90hTNy/PHHtqJgDpfYK4e74L2Dl30IWdQvzPysnZo5Zq7lzSNHQXEL9TXl28kzR1sg5YmDn21EwA0ns+8ff9rOxRl/V14urrPmCX7FFLNbeCNI3dIPCOzLFX4RmEiUIp6uMNmWOvgglAegcSf9/fzB51OfsC9xNXXxdmj1pqiItIlwT8RebYUzqBNOP+g4Qdx3bPG34lTACqcRXx935M9qjLSNEj93fZo5Ya4smkSwC2EXbOa5plhM2RUtXDm7NGXx0TgGq8j/h7/2L2qPPbk/jJf4PA8bkDl5rkO6Rr/LaTZve8XB5LeGJPdf+30J5z300AqnEc8fe+DTgqd+CZvYP4enL5nzSFpYSJMqkawUHgH4GBnDcxA6cCm0l736dmvYNqmQBU55fE3//FtHcDt8NJ8978m9yBS030VtI2hIOEnoV9c95En+YAbyfdhL/edU7Om8jABKA6byTNz9yLcweeSYoVSttp926lUjI7A78ifRKwlnqtwX0IcAnp73Mz4amlTUwAqnMQYVfA2DrYQPsauT8lzXvS2f/SNBxP/JKbia6zCR96pexMmJyXusu/d/1JtjvJxwSgWp8nzc/ej4B5mWOvyiNJ9x59aubYpcZ7PdU0kIPA3YShhr2y3U3YRvSVwA0J72P09aVsd5OXCUC1HkG6YagPZo69CnsB15CmPn5O/ecgSbUzAPw31TWWg8BGwlkED6/wPvYnJDM3V3wvV9OeWf+jmQBUL3aXu+FXitMrS9kZ+D7p6qJNk3GlrJZQzXyA8a6fAK8mTTJwIOGY4m+RZnx1qute2r0hiwlA9VIsCexd24Ez8oafxHzC7oap6mE17dmaXCricOB28iQBvetW4HPAXwPPBI4kdAvuNCyuecAehMl8TyYkDx8l/pS16V5bhl6/zUwA8kg1F6CXBLwub/hRFhIS9pTvzadkvQOppY4lzU5cKa6tpN+rIOZD9gUzr9bGMAHI4wDC/JiUP6PvpP57BOwHrCLtfX896x1ILbeC6mbON/Vq0hNWDBOAfF5N+p/Tc6nnPhwAy0k/P2cTcEjGe5A64STSP6E08dpOe/b574cJQD5zgF+Q/md2LSGJr4u5hPdQFXN0mjwJUqq1Y8k/J6BO11bgJdG12CwmAHkdSXiKreLn92zgAfluZVwnAJdSzf1dTHv2QpBq6SHA9ZRvjHNf99L+CX/jMQHI7wyq+zneQHhKzr1s9UjgC6Tfert3rafsJmNSZ+xLmn26m3Ktof2nrk3EBKCMz1Dtz/SdhMO6qmw0BwhP/GdRXcM/OFS2s/6ljAYIm+zkWGtf8voysHuiOmsiE4AydiXNaYFTXduA8wh7Z6SYLDgA/A7w98C1GeIfJBwZLKmAFVS/016JaxPwsoT11FQmAOUcTJjAl/Pn/jLgfcCLgN8F9pwkvlmEGfdPAP6csJdB7jlCn6X+Sx2lVlsE/Cvt6Q04G5cS9ZgAlHUE8GvKvh9+A9xB2KP/l8B1QzHF/mzEXufgpD+pNo4i7V7eua+1wPOS10qzmQCUdzzh7IzS7486XT8mDJNIqpFZwHOAKyj/IdHvdSthY5+dK6iPpjMBqIcVwF2Uf6/U4foBkw9NSCpsFmFm7iWU/8CY6LqVMJHRhn9iJgD1cSRwE+XfNyWvc/DJX2qMAeDxhMk6VW1wMp1rG/Bt4Lk4ftgPE4B6OYT8h17V5TqTsIugpAZaBLwYuID8EwZ/TujmP6Dqm2wZE4D62ZvwJFy6Qc51bQHeQHiYkNQCC4GVwNsJE3pSzyi+mbDz2Bm4Q1gME4B6GgBeBdxP+Qa6ymst8PuJ6kxTMMNSKYuBhxK2Gj586Dpo6M8XDl29sfr7CbOi7yQcVXwrsBq4ktA9upowVqp424hbZz2bsFPbRGIb8a5/Zi0HPg3sXziOKvwvYVXOutKBSCpvNo7b52YPQP3tTrv24VhP6LnrenInSUWZADTHMsISudIN+EyvbcAngSWpK0aSNH0mAM0yizDR9hrKN+j9XtuB/waOqaA+JEkzZALQTLOAU4DLKd/AT9bwnw0cW1EdSJIimAA02yzg6YQn7C2Ub/QHCZP63g88vML7liRFMgFojz0Ik+suIn+jv5nwtH8KTuStLWddShrOZYDttB/wGMJeHE+gmr0yrgW+M3SdQ1i2qxrzzSRpOBOAbjiccNbA4cBSwn4chxD24Zhs+917CV36awj7cKwe+noVYTmfGsQ3k6ThTAA0n7AR1yJC9/0mwgZcd+IwTKv4ZpI0nAmA1BExb3RJktRQJgCSJHWQCYAkSR1kAiBJUgeZAEiS1EEmAJIkdZAJgCRJHWQCIElSB5kASJLUQSYAkiR1kAmAJEkdZAIgSVIHmQBIktRBJgCSJHWQCYAkSR1kAiBJUgeZAEiS1EEmAJIkdZAJgCRJHWQCIElSB5kASJLUQSYAkiR10Jxx/mwf4I+AlcDBwM5ZI2qfe4Hrge8AnwVuLxuOJEkjDQCvB+4BBr0que4BXjtU11IdbSPuZ3yqXsXY95CkxGYBn6Z8A9mV60xMAlRPJgBSx7yF8o1i16439fU/I+VlAiB1xADwQGA1ML9sKJ1zP/BQ4NrSgUjDbCNucvBsYPskfx/biNtzJiUyC3gpNv4lzANeVDoISVI3zQKeUDqIDnti6QAkSd00CzikdBAddmjpACRJ3TSAE2tKc0yz/nYDfg94FHAMcDSwmDCMA2HMeytwH5OPfzfB4sjv31C4/LqbRRhyncOOuRb3A78GVgE/AX4IfJewh4hUGROA8kwA6mkn4FnAacBJjL9pllSVLcB5wKeArwCbyoajNjIBKM8EoF52B14DvAzYs3AsEsB64H3Av9H8HhDViAlAeSYA9TALeDlhTwwbftXReuANwMdo/lCTasAEoDwTgPIOAT4BnFA4DqkfFwKnAzeVDkTNZgJQnglAWScAXwT2Lh2INA13EOannFc6EDWXxwGry04HzsXGX82zF/A/wCmlA1FzmQCoq14AfBJn96u55gOfI6xWkaYtxRDAYSkCabBrIr+/a2cBzAZ2JSyzm0OYzHQ3cCnwBeAC4CqqneT0eOAb2PirHe4HnkTozZL6liIB6PoYtnMo0tsI/IywMcpPh369grDZTqxDh8pblKAsqS7WA8uAG0sHouYwAYhnApDHZkIvwaph16WE3ff6NYeww9qjkkcnlXcR8FhcIqg+mQDEMwEoZwuhZ6CXEPwU+DmhB2E8fwn8c57QpCJeAXyodBBqBhOAeCYA9XMLYU/13vVDwoTXNYR9/aW22gAcDqwrHYjqzwQgnglAM9wL7FI6CCmDfwLeWDoI1Z8JQDwTAEl1chdw8NCv0oTcB0CS2mURcGrpIFR/9gDEswegW+4APkvYPvhG4DeEyYhd0HtgqGqWedXl181cwn4YBwAnA88nnEuRwkXA7ycqSy1lAhDPBKCb7iWsOBi+AuFyupMMKL15wGsJJ1LOjixrO7APTgbUJEwA4pkAqGcLYRfD4SsQVgGbSgalxnkq8BXik4BTgC/Fh6O2MgGIZwKgyWwl7FXQ29FwFaHn4J6SQan2Xge8I7KMfwFenSAWtZQJQDwTAE3XdkJPwfCtjlcR1nBLEOYHXEZY0z9TFwLLk0SjVjIBiGcCoFR+xdik4LaiEamk1wDvivj+DcAeiWJRC5kAxDMBUJVuZuREw1XADUUjUi5HAr+I+P5teOKlJmECEM8EQLmtY2QvwSrCsdT+LLbLQuLninT981mTMAGIF1t/hyWJonlmE47mPQA4CDgGeDSwZ8mgGuwewtNib/XB5YTTEu8vGZSixX6+zKY7+ypomkwA4ll/ae1PSAZ617GE9cyavvGWJf6YcLSymsEEQJUxAYhn/VVvdFLwMELvgaZvK+FURPcqaIbtxH1GmABoQiYA8ay/MhYDD2dkYvBQrM+Z2AZcSRg2uIKQFHwfWF8yKAHh/ybmzBYTAE3IBCCe9Vcfiwgzp4cnBQ/BQ69m6hZG9hRcDNxeNKLuMQFQZUwA4ll/9bYQOIowbPBw4LHAEcRvs9pVo5OCy4Fri0bUbiYAqowJQDzrr3nmEnZYOwFYRkgOjiaczKbp28COoYPedQUuS0zBBECVMQGIZ/21wxxgKSOHD44CdikZVIPdRdjKdnhS8EtsjKbLBECVMQGIZ/2112xCUnD00LVs6FpUMqgG2whcD9xE6CH4GnBByYAawARAlTEBiGf9dcsAYfOmZexIDI4GlpQMqsG2Aj8jHH/7dcKcAu1gAqDKmADEs/4EYTfD4UnBMsIuh5qeq4CzgI8DqwvHUgcmAKqMCUA8608T2Z2w4sC9CqZvEDgf+CBhqGBr2XCKMQFQZUwA4ll/mo7F7Ogh6PUWPBj3KpjMlcDfAl+keysLTABUGROAeNafYvX2KhieGDwMj3Id7WfAX9CtiYMmAKqMCUA8fVnFfAAAFhRJREFU609VWEDY1XD4RMMjgfklg6qBQeBThESgC1sVmwCoMiYA8aw/5TLeXgW/Q+hB6JpbgRcC3ywdSMVMAFQZE4B41p9Kmk3Y1XD0CoTdSwaVyXbgH4G30N5GzgSgvnYCTgROIqwC2g3YFdgX2GPo7wcJx2/fTtgy+wbCaZyrCXNbriX8HxdhAhDP+lMdjT5C+ZGED6Y2+hZwKnB36UAqYAJQ1gChcT986Hro0K9HAXslKH8DcCFhxcu5ZN4HwwQgnvWnpngAI3sJjh76szZYBTyR9p1WaAKQx26Ehn0p4QTRw4f9PucZIauBMwnzXG6o+sVMAOJZf2qyvRm7q+GhRSOauTXA4wjbDbeFCUA6s4FDCA17r5FfOnTtVzCu8WwHzgPeCZxT1YuYAMSz/tQ2uzN2TsHhNOMI5WuAxxAmCbaBCcD07cmORn4pOxr6BwHzCsY1U5cAbwXOJvE+GCYA8aw/dcEuhHHP4YnBwwhHK9fNT4HltGNOgAnA+OYRzuToPcEPb/D3LBhXlS4CXgFcmqpAE4B41p+6aj7j71WwoGRQQ84FHk/BGdaJdD0BWEwYkno4IeE8dNjv6/BzlttW4H3Am4F7YgszAYhn/Uk7zCF8UPeGDo4BjqVM1+vbgL8u8LopdSEB2Ikdk+5Gd9179Pb4rgdOA34YU4gJQDzrT5rcLOARwOmE8fmDCZMPq55TMAg8FfhGxa9TpTYlAIsZ+STf+/pgmjG/pG7uA14DvH+mBZgAxLP+pOlbSFi29yxCI13VXIJ1hEbmjorKr1rTEoD5hMl2oxv5pXRzx8ocPkPYFfP+6X6jCUA860+Ksz/wZ8CfECYbpvYZQu9DE9UxAZgNPJDxZ9rvn/i11J/zgKcxzYmvJgDxrD8pjX0J4/bPJ/374snA/yQuM4eSCcDuhJn2oyfhPRTYOSImVePHwJOYRm+XCUA8609KayXwX6R9mlxDaMS2Jiwzh6oTgLnAgYw/076pG0J12SrC+QR99QSYAMSz/qT09gE+Dzw2YZl/DHw0YXk5pEoA9mHkFre9rvtDCCs31B7nEnq87uvnHw9GXl1n/UnVmEdIAmLfY71rLXn3dU9hG3H3fDHhwJlUdejVjOvz9PlwGftCXWf9SdWZTdok4CV5w48WmwB4dfd6NVNwCCCe9SdVax7wv8CKBGX9jLBBUd3tSuiq/xF+RtTZVuDOoes2wjbU3wZuJvy/zR76N3sS5lQ8BHg0YYOsqvc+2AKcwCSbBZkAxLP+pOrtAfyEsPws1mOA7yUoJ4X9GbvF7cMI9xkz9q+0NgDXAlcAlw99fe3Q15tnUN5uhBn7zyWcYFnVPIzrCQnvhvH+0gQgnvUn5fH7wAXEN4wfB14UHU3/JlpO9xCq2fdAM3M/YZ7I6Eb+UsLTfVUOInTXv4Rqlld+GHj5eH9hAhDP+pPy+QDhRLQY6wl7DqRcEjiH8EHucrr628DYRv4KYDVlD4/aH3g7oVcgpe3A7zHOUIAJQDzrT8pnd+AawpBAjJMIu6dNl6fTNcN9hJ+T0Y38z0lwil7F/gD4T+J/xof7CXA8oxIcE4B41p+U1xuAf4os433Aqyb4u3nAAxjbyB9JWE+v+riFsY385cB11OcQpJk4CPgy8MiEZT4POHP4H5gAxLP+pLwWATcRN36+irDj4HhP8kvxdLo6uZPwND+6kV8N3FswrqrtAnwJeEKi8lYTfr5/mxiZAMSz/qT8PgKcUToIJbMVuIGxjfy1wK+I/5xtqnnA10iXBJxCSCoAE4AUrD8pvxOZ2Ri+yrqD8CR6JeF8hiuHfv8rwrp1jbULYXvf4xOUtYqwBwFgApCC9SflNwe4lbDBiurlPuAqxjbya5hgPbqmtA9hE6t9E5S1bKgsD4GQ1Ehbge8Cf1g6kA5by44GvnetIWw+U3I5XRvdBryQcKR17EPjcxlKAOwBiGf9SWW8DnhH6SBa7l5GPskPb+g3Foyrqz5J/D4BtxFWuWw1AYhn/Ull/D/g7NJBtMB2wlP76O76NcCNBePSWPsQ/o8WRZazAjjfIQBJTXVN6QAa5i7garq3nK5NbiPshvnXkeWsAM63ByCe9SeVsYQwq1w7DBJm1F/O2El4txeMS+ksIfTYxJwb8APgdyH+zOGus/6kMhZQ/sz1OlxbCZO6XkVYN672+yRxPzNbgN3sAYhn/UllzCWc4NZWWwhj8KM3xrkM+DWw39C/u4V214PGWgmcE1nGcucASGqq3UoHkMhNjD/TfqrldNdXH5pq6nzCFsm7R5Sx1ARAUlPFzoTO6V52zKwfPdO+7qfTqX62EfbBeEpEGSYAkhrrsNIBjKOtp9Opfr6HCYCkjlpa6HXvYuymOL1fNxeKSd3zy8jvP8AEQFJTHVdh2VsJy+lGN/SrCWuxpdKuivz+XU0AJDXViQnKWMfYMfnVhO57Z9arztZHfv+u4Dr2WNaflN8jiH/vnZo9aimdnYj7+f/NrPwxS1K02ANRIMyilpoqdlLpHPAJNpb1J+U1H7iZuPfdFdmjltLal7j3wD32AEhqmhewYxe8mTo3QRxSSYsjv3+TCYCkJpkPvD5BOecnKEMqaf/I719nAiCpSV4HHBJZxibi91GXSjsm8vtvMgGQ1BQPBt6QoJyzcPtdNd/Rkd9vAiCpEeYDnyMsfYp1ZoIypJIGgEdHlnEFOIs9lvUnVe9jxL/XBoG1wOzMsUupPZr498IT3QlQTbMX8EJgOXAo4cN8PWFHt/XDvr5j2J8N/3Wy41VVT/8AvChRWe/BnwE137MSlPHTAeKfQgcSBNJk1l8es4BXA38PLIgoZwMhORidNNwx7M9HJw321JTzd8CbE5W1Dngg4WheqakWEM6p2DeijDV4GqAaYhbwX8BzEpS1mOmvn91MSBxuJhz3umHUNfrPb8OnzFhzgX8DXpqwzPdi46/mewlxjT/A/0F4+vQJNo71V703E54Em2IbY4ckRg9LjP7zu4tEWk8PJEz4Oz5hmbcAD8F6VrPNJZwCeHBkOX8IfM0EIJ71V61DCedezysdSMW2MHLYoZcYjDe/4fahrzcWibQ6swhPN+8Cdktc9rOBzyYuU8rtr4C3RZbxG2AJsMkhANXdGbS/8YeQ2e/H9La43cz4icHoOQzDex42pQs5mVnAM4C/BY6ooPzzsPFX8z2UND2hZzP0OWACoLo7uXQANbYAOGDo6ldvPsNU8xh611rgrnQhj3AAcDphhv/Sil5jE/DKisqWcpkHfJK4CdA9n+h94RBAPOuvWvcAC0sH0XEbCb0HvR6G0csrez0MGwhj7GsJQxrDzQMOI4zDPwpYQdjJrOrNyF4M/GfFryFVaYDQaD8vQVk3EIZVfztJ2Y1s4lh/1dpImg1gvPJe2wkfMluGrhIxfBKp+d5CuvfEX4wuPLbArrP+qrWG8o2ZV/OuXwC7IDXbmwjJdIr3xAZg1+GFexaA6u67pQNQ49wAPBHX/Ku5ZgMfImx8lmqY+N2McwhWbFbRddZftY6l/NOkV3OudYR5BlJTHQJcQNr3xa1M0CMWW3DXWX/V+xzlGxav+l/rgeOQmmk28O/AVtK/NyY8SyO24K6z/qq3GPgp5RsYr/peNwOPQGqm0wgraKp4b1zIJMMIsYV3nfWXx2Lga5RvaLzqd10OHIjUPMcT5jlV9d64lymGxGJfoOusv7yeCHyVMKO1dMPjVf46C9gdqVkOB75Auhn+E10vmywINwKKZ/2Vsz9hT+slwN7AnkPXkmG/7jXs9zuXCVMV2Ew4HvqDpQORpmE/wna+L6b6nXi/AjyTSdooE4B41l9zLAD2YMeRwL1rP0IyMfrPDwAWFYlUk7mYcEzwz0sHIvVpEfBa4M/Jsz/FZcCjmeLAMBOAeNZfuy0k9B70ehJ6vQnj9TD0fu3C4UUlbCDsivZ+hm1lKtXYPOAFhPX8+2R6zduA3wWuneofmgDEs/402m6Mnxj0Eom9GDtc4cFcE9sIfAR4O2Gdv1R3swgHXf0DcHDG170TWE6fvWMmAPGsP6WwmJE9DBPNYxj+d23fyfMuwtP+e7HhV3M8CXgb+Zel3jX02t/v9xtMAOJZfyphgLG9C70EYq8J/m7PIpFOzzbgXOBMwgx/t/NVUxwLvAM4scBr/5qwQuqS6XyTCUA8609NMZvxE4PJVlDkmAT5a8LWp+cSlnjenOE1pVSWAm8Fnk6Zz/MrgacAV033G00A4ll/arO5jE0a9h71+8MJZ4wvGvr3E/1M30to3K8EVhNOevwJ8DPCemipSXIu6ZvItwi7CN450wJiNxroOutPGmsX4Iiha7fCsUgpLSI88W+k3AZY24A3k2AekA1YHOtPktpvHnAG4WS9Ug3/IOHQq2NT3ZQNWBzrT5LaaxbwXOA6yjb824EvEYbZkrEBi2P9SVI7rQRWUbbh30Y40e/QKm7QBiyO9SdJ7XIscB7ln/i/ADyoyhu1AYtj/UlSO+Q6pW+q6xzg6IrvFRIE2nXWnyQ1217AvwJbKNvw/wg4qeJ7HcEGLI71J0nNtBB4PXA3ZRv+6wgrDLJv720DFsf6k6RmqcuSvjsICcj8am93YjZgcaw/SWqGWcApwDWUbfg3Ek63LL5Jlg1YHOtPkuqvDkv67iccbb1vxffaNxuwONafJNVXZ5b0zYQNWBzrT5LqZykdW9I3EzZgcaw/SaqPzi7pmwkbsDjWnySV1/klfTNhAxbH+pOkcnpL+m6jbMNffEnfTNiAxbH+JCk/l/QlYAMWx/qTpLxWAj+lbMNfuyV9M2EDFsf6k6Q8jqM+S/oOq/hes7ABi2P9SVK1XNJXERuwONafJFXDJX0VswGLY/1JUlou6cvEBiyO9SdJabikLzMbsDjWnyTFcUlfITZgcaw/SZq5Oi3p26fie60dG7A41p8kTZ9L+mrABiyO9SdJ/XNJX43YgMWx/iRpai7pqyEbsDjWnyRNzCV9NWYDFsf6k6SxXNLXADZgcaw/SdrBJX0NYgMWx/qTpMAlfQ1jAxbH+pPUdS7paygbsDjWn6Sucklfw9mAxbH+JHWNS/pawgYsjvUnqStc0tcyNmBxrD9JbeeSvpayAYtj/UlqK5f0tZwNWBzrT1IbuaSvA2zA4lh/ktrEJX0dYgMWx/qT1AYu6esgG7A41p+kJqvTkr4VFd+rRrEBi2P9SWqiOi3pex4wUOndalw2YHGsP0lN4pI+/ZYNWBzrT1ITuKRPY9iAxbH+JNWdS/o0LhuwONafpLpySZ8mZQMWx/qTVDcu6VNfbMDiWH+S6sIlfZoWG7A41p+k0lzSpxmxAYtj/UkqxSV9imIDFsf6k5RbXZb03YNL+hrNBiyO9ScpJ5f0KRkbsDjWn6QcXNKn5GzA4lh/kqrkkj5VxgYsjvUnqQr7E7rZSy/puwSX9LWWDVgc609SSi7pUzY2YHGsP0kpuKRP2dmAxbH+JMVwSZ+KsQGLY/1JmimX9KkoG7A41p+k6XJJn2rBBiyO9SepXy7pU63YgMWx/iRNxSV9qiUbsDjWn6SJuKRPtWYDFsf6kzSaS/rUCDZgcaw/ST0u6VOj2IDFsf4kgUv61EA2YHGsP6nbXNKnxrIBi2P9Sd3kkj41ng1YHOtP6pYDcEmfWsIGLI71J3VDXZb0/QqX9CkRG7A41p/Ubi7pU2s1rQFbCLwY+BKwCriCMAHn74GHFoinafUnqT8u6VPrNakBOw24ZZJYtgEfBXbJGFOT6k9Sf1zSp05oSgP2BvqfbftTYHGmuJpSf5KmdhxwPmUbfpf0KZsmNGBPZ/pLbb5JnkkyTag/SZOr05K+ZRXfq/RbdW/AFgDXzzC2Z2aIr+71J2liLulTp9W9ATs1IrYLM8RX9/qTNJZL+iTq34B9LCK2rVQ/c7bu9SdpB5f0ScPUvQGL3WP7iIrjq3v9SXJJnzSuujdg/xcZX9V7ZNe9/qSuc0mfNI45pQPow82R3782SRTVuZQwDnjt0K/Dv763YFxS0x0HvANYXjCGQcKmZW8g9D5ItVL3J9iXRsR2WYb4qnxquA34IfAZ4K3AS4CTgENoRvImleCSPqlPdU8AlgB3zTC212aIr+QHzK+BHxM+7N5OmNy0EjgUZxWre1zSJ01T3RMACA35dOO6mrCHQNVKftBMdm0kDC98HXgv8CrgqYRJkTm3Spaq5pI+aYaakAAMAJ+fRkx3Uv3s/57SDf1ML4cX1HQu6ZMiNSEBAJgNvItw4M9k8VxB3lMBSzfkVV0OL6iuXNInJdKUBKDnCMLmQMOz/i2EXf/OAOZmjqd0Q13icnhBpbikT0pkgPhGvOTT4EJC9n0boWeghBJJUN3dztiljb3f30jYoVGaDpf0SYk1PQGoAxOA6dtASAbGu36FdaodlgL/QDjYq+RnzXeA1xF6H6RWMAGIZ2OV1r2M3RBp+NdujtQNBwB/C7yIspNSfwT8FWFLcqlVTADimQDk5fBCuy0EXgm8Edi1YBzXAX8HnInvcbWUCUA8PxzqxeGFZpoHvIDQ3b93wTjWAe8G3kOY7Ce1lglAPBuU5rgPuInxk4OrCBvJKK9ZwDMIE/wOKRjHRuADwD/hz4E6wgQgnglAezi8kNdKwt4eRxWMYQvwccJ8g9sKxiFlZwIQzwSgG7YSkoCJEoTby4XWOC7pk2ogRQJwbYpAGuzQ0gGoFly9MDWX9Ek1kiIBUJyTCUnE8OtBwKKSQSm5ySYn3kC7hxdc0ifVkAlAeRM9CS1mbGKwP7Af8DBgpyzRKZc2rl6oy5K+NcDfELr8m1iPUiVMAMqbaVfoeAlC73ogYXa12qFpqxdc0ic1gAlAeVWMhc4ndLuOlxw4vNA+dRlecEmf1CAmAOWVmAzl8EK35BhecEmf1DAmAOXVcRmlwwvdETu84JI+qaFMAMqrYwIwGYcXumWi3gOAlxK6/F3SJzXQAGF98s6lA+moTcAupYNIbC/C+G/vOnTY1wcBc8uFphZxSZ8UaYCwRObBpQPpqDWEzVG6YjZwICMThOFJwr7lQlNDrCEsK/wy9l5KUeYAF2ACUErXnl62EY5ZvQ44f5y/34mRPQbDvz6EsmvJVdYdwD/jkj4pmQHg94CLSgfSUb8L/KB0EA3i8EL33A28E3gvbqcsJdWbvPMlwmQe5fN54LTSQbSIwwvtch/wIeCthA19JCXWSwAWAd8DHl4wli65jNDz4iYl+bh6oRm2A58B3kQYKpJUkeHLdxYDnwKeVCiWrvgG8FzgztKBaAQ3RyrPJX1SRuOt330S8MfA43B5YCr3AucA/w78b+FYNH0OL1TrEsKSvvEmhkqqyFQbeCwBdssRSIvdjWOYbefwwsy4pE8qqGm70ElN5OqFkVzSJ9WACYBUVpeGF1zSJ9WICYBUb23YHMklfVINmQBIzVbn1Qsu6ZNqzARAaq+SwwvfAV4D/LzC15AUwQRA6q4qhhfuBF4IfDVRjJIqYgIgaSLjrV44hnCC5fBjrAeBW4EPEsb5XdInNYAJgKSZmA0cNvT1mpKBSJIkSZIkSZIkSZIkSZIkSZIkSZIkSZIkSZIkSZIkSZIkSTX0/wGrYz5Tt4o6PgAAAABJRU5ErkJggg==",\n  "logoContentType" : "image/png",\n  "organisationUnitLevel" : {\n    "createdBy" : null,\n    "createdDate" : "2020-09-17T12:12:11.257Z",\n    "lastModifiedBy" : null,\n    "lastModifiedDate" : "2020-09-17T12:12:11.257Z",\n    "id" : 3051,\n    "code" : null,\n    "name" : null,\n    "level" : null,\n    "isInspectionLevel" : null\n  },\n  "parent" : {\n    "createdBy" : null,\n    "createdDate" : "2020-09-17T12:12:11.257Z",\n    "lastModifiedBy" : null,\n    "lastModifiedDate" : "2020-09-17T12:12:11.257Z",\n    "id" : 1,\n    "code" : null,\n    "name" : null,\n    "address" : null,\n    "phoneNumber" : null,\n    "email" : null,\n    "background" : null,\n    "logo" : null,\n    "logoContentType" : null,\n    "organisationUnitLevel" : null,\n    "parent" : null\n  }\n}	1	admin	2020-09-17 12:12:11.293
3008	3102	org.tamisemi.iftmis.domain.OrganisationUnit	CREATE	{\n  "createdBy" : "admin",\n  "createdDate" : "2020-09-17T12:14:19.457Z",\n  "lastModifiedBy" : "admin",\n  "lastModifiedDate" : "2020-09-17T12:14:19.457Z",\n  "id" : 3102,\n  "code" : "DOM_MC",\n  "name" : "Dodoma MC",\n  "address" : "Dodoma Mjini",\n  "phoneNumber" : null,\n  "email" : "dodomamc@dodomamc.go.tz",\n  "background" : "Dodoma Mjini",\n  "logo" : "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",\n  "logoContentType" : "image/png",\n  "organisationUnitLevel" : {\n    "createdBy" : null,\n    "createdDate" : "2020-09-17T12:14:19.456Z",\n    "lastModifiedBy" : null,\n    "lastModifiedDate" : "2020-09-17T12:14:19.456Z",\n    "id" : 3052,\n    "code" : null,\n    "name" : null,\n    "level" : null,\n    "isInspectionLevel" : null\n  },\n  "parent" : {\n    "createdBy" : null,\n    "createdDate" : "2020-09-17T12:14:19.456Z",\n    "lastModifiedBy" : null,\n    "lastModifiedDate" : "2020-09-17T12:14:19.456Z",\n    "id" : 3101,\n    "code" : null,\n    "name" : null,\n    "address" : null,\n    "phoneNumber" : null,\n    "email" : null,\n    "background" : null,\n    "logo" : null,\n    "logoContentType" : null,\n    "organisationUnitLevel" : null,\n    "parent" : null\n  }\n}	1	admin	2020-09-17 12:14:19.457
3009	2952	org.tamisemi.iftmis.domain.RiskRating	DELETE	{\n  "createdBy" : "admin",\n  "createdDate" : "2020-09-17T11:45:43.507Z",\n  "lastModifiedBy" : "admin",\n  "lastModifiedDate" : "2020-09-17T11:45:43.507Z",\n  "id" : 2952,\n  "source" : "INSPECTOR",\n  "impact" : 1,\n  "likelihood" : 2,\n  "comments" : "No comment",\n  "risk" : {\n    "createdBy" : "admin",\n    "createdDate" : "2020-09-17T11:45:43.463Z",\n    "lastModifiedBy" : "admin",\n    "lastModifiedDate" : "2020-09-17T11:45:43.463Z",\n    "id" : 2901,\n    "code" : "001",\n    "description" : "Collecting revenue and not banking in time",\n    "riskRegister" : {\n      "createdBy" : "admin",\n      "createdDate" : "2020-09-17T09:23:14.892Z",\n      "lastModifiedBy" : "admin",\n      "lastModifiedDate" : "2020-09-17T09:23:14.892Z",\n      "id" : 2602,\n      "name" : "DIFT PORALG - 2020/2021",\n      "isApproved" : false,\n      "approvedDate" : null,\n      "approvedBy" : null,\n      "organisationUnit" : {\n        "createdBy" : "system",\n        "createdDate" : "2020-07-21T00:00:00Z",\n        "lastModifiedBy" : "system",\n        "lastModifiedDate" : null,\n        "id" : 1,\n        "code" : null,\n        "name" : "DIFT PORALG",\n        "address" : null,\n        "phoneNumber" : null,\n        "email" : null,\n        "background" : null,\n        "logo" : null,\n        "logoContentType" : null,\n        "organisationUnitLevel" : {\n          "createdBy" : null,\n          "createdDate" : "2020-09-17T12:08:49.129Z",\n          "lastModifiedBy" : "admin",\n          "lastModifiedDate" : "2020-09-17T12:08:49.142Z",\n          "id" : 1,\n          "code" : "PORALG",\n          "name" : "Default Level",\n          "level" : 1,\n          "isInspectionLevel" : true\n        },\n        "parent" : null\n      },\n      "financialYear" : {\n        "createdBy" : "admin",\n        "createdDate" : "2020-09-17T09:23:14.863Z",\n        "lastModifiedBy" : "admin",\n        "lastModifiedDate" : "2020-09-17T09:23:47.544Z",\n        "id" : 2502,\n        "name" : "2020/2021",\n        "startDate" : "2020-07-01",\n        "endDate" : "2021-06-30",\n        "isOpened" : true,\n        "closed" : false\n      }\n    },\n    "objective" : {\n      "createdBy" : "admin",\n      "createdDate" : "2020-09-17T07:23:37.582556Z",\n      "lastModifiedBy" : "admin",\n      "lastModifiedDate" : "2020-09-17T07:23:37.582556Z",\n      "id" : 1301,\n      "code" : "001",\n      "description" : "  To insure LGA Meetings are conducted accordingly and resolutions are implemented timely"\n    },\n    "riskCategory" : {\n      "createdBy" : "admin",\n      "createdDate" : "2020-09-17T08:55:04.979248Z",\n      "lastModifiedBy" : "admin",\n      "lastModifiedDate" : "2020-09-17T08:55:04.979248Z",\n      "id" : 1951,\n      "code" : "001",\n      "name" : "Operational Risks"\n    }\n  }\n}	2	admin	2020-09-17 11:45:43.507
3010	2951	org.tamisemi.iftmis.domain.RiskRating	DELETE	{\n  "createdBy" : "admin",\n  "createdDate" : "2020-09-17T11:45:43.503Z",\n  "lastModifiedBy" : "admin",\n  "lastModifiedDate" : "2020-09-17T11:45:43.503Z",\n  "id" : 2951,\n  "source" : "COUNCIL",\n  "impact" : 1,\n  "likelihood" : 1,\n  "comments" : "No Comment",\n  "risk" : {\n    "createdBy" : "admin",\n    "createdDate" : "2020-09-17T11:45:43.463Z",\n    "lastModifiedBy" : "admin",\n    "lastModifiedDate" : "2020-09-17T11:45:43.463Z",\n    "id" : 2901,\n    "code" : "001",\n    "description" : "Collecting revenue and not banking in time",\n    "riskRegister" : {\n      "createdBy" : "admin",\n      "createdDate" : "2020-09-17T09:23:14.892Z",\n      "lastModifiedBy" : "admin",\n      "lastModifiedDate" : "2020-09-17T09:23:14.892Z",\n      "id" : 2602,\n      "name" : "DIFT PORALG - 2020/2021",\n      "isApproved" : false,\n      "approvedDate" : null,\n      "approvedBy" : null,\n      "organisationUnit" : {\n        "createdBy" : "system",\n        "createdDate" : "2020-07-21T00:00:00Z",\n        "lastModifiedBy" : "system",\n        "lastModifiedDate" : null,\n        "id" : 1,\n        "code" : null,\n        "name" : "DIFT PORALG",\n        "address" : null,\n        "phoneNumber" : null,\n        "email" : null,\n        "background" : null,\n        "logo" : null,\n        "logoContentType" : null,\n        "organisationUnitLevel" : {\n          "createdBy" : null,\n          "createdDate" : "2020-09-17T12:08:49.129Z",\n          "lastModifiedBy" : "admin",\n          "lastModifiedDate" : "2020-09-17T12:08:49.142Z",\n          "id" : 1,\n          "code" : "PORALG",\n          "name" : "Default Level",\n          "level" : 1,\n          "isInspectionLevel" : true\n        },\n        "parent" : null\n      },\n      "financialYear" : {\n        "createdBy" : "admin",\n        "createdDate" : "2020-09-17T09:23:14.863Z",\n        "lastModifiedBy" : "admin",\n        "lastModifiedDate" : "2020-09-17T09:23:47.544Z",\n        "id" : 2502,\n        "name" : "2020/2021",\n        "startDate" : "2020-07-01",\n        "endDate" : "2021-06-30",\n        "isOpened" : true,\n        "closed" : false\n      }\n    },\n    "objective" : {\n      "createdBy" : "admin",\n      "createdDate" : "2020-09-17T07:23:37.582556Z",\n      "lastModifiedBy" : "admin",\n      "lastModifiedDate" : "2020-09-17T07:23:37.582556Z",\n      "id" : 1301,\n      "code" : "001",\n      "description" : "  To insure LGA Meetings are conducted accordingly and resolutions are implemented timely"\n    },\n    "riskCategory" : {\n      "createdBy" : "admin",\n      "createdDate" : "2020-09-17T08:55:04.979248Z",\n      "lastModifiedBy" : "admin",\n      "lastModifiedDate" : "2020-09-17T08:55:04.979248Z",\n      "id" : 1951,\n      "code" : "001",\n      "name" : "Operational Risks"\n    }\n  }\n}	2	admin	2020-09-17 11:45:43.503
3011	2901	org.tamisemi.iftmis.domain.Risk	DELETE	{\n  "createdBy" : "admin",\n  "createdDate" : "2020-09-17T11:45:43.463Z",\n  "lastModifiedBy" : "admin",\n  "lastModifiedDate" : "2020-09-17T11:45:43.463Z",\n  "id" : 2901,\n  "code" : "001",\n  "description" : "Collecting revenue and not banking in time",\n  "riskRegister" : {\n    "createdBy" : "admin",\n    "createdDate" : "2020-09-17T09:23:14.892Z",\n    "lastModifiedBy" : "admin",\n    "lastModifiedDate" : "2020-09-17T09:23:14.892Z",\n    "id" : 2602,\n    "name" : "DIFT PORALG - 2020/2021",\n    "isApproved" : false,\n    "approvedDate" : null,\n    "approvedBy" : null,\n    "organisationUnit" : {\n      "createdBy" : "system",\n      "createdDate" : "2020-07-21T00:00:00Z",\n      "lastModifiedBy" : "system",\n      "lastModifiedDate" : null,\n      "id" : 1,\n      "code" : null,\n      "name" : "DIFT PORALG",\n      "address" : null,\n      "phoneNumber" : null,\n      "email" : null,\n      "background" : null,\n      "logo" : null,\n      "logoContentType" : null,\n      "organisationUnitLevel" : {\n        "createdBy" : null,\n        "createdDate" : "2020-09-17T12:08:49.129Z",\n        "lastModifiedBy" : "admin",\n        "lastModifiedDate" : "2020-09-17T12:08:49.142Z",\n        "id" : 1,\n        "code" : "PORALG",\n        "name" : "Default Level",\n        "level" : 1,\n        "isInspectionLevel" : true\n      },\n      "parent" : null\n    },\n    "financialYear" : {\n      "createdBy" : "admin",\n      "createdDate" : "2020-09-17T09:23:14.863Z",\n      "lastModifiedBy" : "admin",\n      "lastModifiedDate" : "2020-09-17T09:23:47.544Z",\n      "id" : 2502,\n      "name" : "2020/2021",\n      "startDate" : "2020-07-01",\n      "endDate" : "2021-06-30",\n      "isOpened" : true,\n      "closed" : false\n    }\n  },\n  "objective" : {\n    "createdBy" : "admin",\n    "createdDate" : "2020-09-17T07:23:37.582556Z",\n    "lastModifiedBy" : "admin",\n    "lastModifiedDate" : "2020-09-17T07:23:37.582556Z",\n    "id" : 1301,\n    "code" : "001",\n    "description" : "  To insure LGA Meetings are conducted accordingly and resolutions are implemented timely"\n  },\n  "riskCategory" : {\n    "createdBy" : "admin",\n    "createdDate" : "2020-09-17T08:55:04.979248Z",\n    "lastModifiedBy" : "admin",\n    "lastModifiedDate" : "2020-09-17T08:55:04.979248Z",\n    "id" : 1951,\n    "code" : "001",\n    "name" : "Operational Risks"\n  },\n  "riskRatings" : [ {\n    "createdBy" : "admin",\n    "createdDate" : "2020-09-17T11:45:43.503Z",\n    "lastModifiedBy" : "admin",\n    "lastModifiedDate" : "2020-09-17T11:45:43.503Z",\n    "id" : 2951,\n    "source" : "COUNCIL",\n    "impact" : 1,\n    "likelihood" : 1,\n    "comments" : "No Comment"\n  }, {\n    "createdBy" : "admin",\n    "createdDate" : "2020-09-17T11:45:43.507Z",\n    "lastModifiedBy" : "admin",\n    "lastModifiedDate" : "2020-09-17T11:45:43.507Z",\n    "id" : 2952,\n    "source" : "INSPECTOR",\n    "impact" : 1,\n    "likelihood" : 2,\n    "comments" : "No comment"\n  } ]\n}	2	admin	2020-09-17 11:45:43.463
3301	3251	org.tamisemi.iftmis.domain.FindingRecommendation	CREATE	{\n  "createdBy" : "admin",\n  "createdDate" : "2020-09-18T06:16:35.971Z",\n  "lastModifiedBy" : "admin",\n  "lastModifiedDate" : "2020-09-18T06:16:35.971Z",\n  "id" : 3251,\n  "description" : "After close supervision revenue was banked to council own source revenue account",\n  "implementationStatus" : "IMPLEMENTED",\n  "finding" : {\n    "createdBy" : "admin",\n    "createdDate" : "2020-09-18T06:16:35.963Z",\n    "lastModifiedBy" : "admin",\n    "lastModifiedDate" : "2020-09-18T06:16:35.963Z",\n    "id" : 3201,\n    "source" : "CAG",\n    "code" : "001",\n    "description" : "Revenue collected but not banked timely",\n    "actionPlanCategory" : "LOW",\n    "isClosed" : null,\n    "organisationUnit" : {\n      "createdBy" : null,\n      "createdDate" : "2020-09-18T06:16:35.922Z",\n      "lastModifiedBy" : null,\n      "lastModifiedDate" : "2020-09-18T06:16:35.922Z",\n      "id" : 3102,\n      "code" : null,\n      "name" : null,\n      "address" : null,\n      "phoneNumber" : null,\n      "email" : null,\n      "background" : null,\n      "logo" : null,\n      "logoContentType" : null,\n      "organisationUnitLevel" : null,\n      "parent" : null\n    }\n  },\n  "findingResponses" : [ ]\n}	1	admin	2020-09-18 06:16:35.971
3305	3353	org.tamisemi.iftmis.domain.FindingResponse	CREATE	{\n  "createdBy" : "admin",\n  "createdDate" : "2020-09-18T06:17:19.110Z",\n  "lastModifiedBy" : "admin",\n  "lastModifiedDate" : "2020-09-18T06:17:19.110Z",\n  "id" : 3353,\n  "source" : "CLIENT",\n  "description" : "Acha tu",\n  "recommendation" : {\n    "createdBy" : null,\n    "createdDate" : "2020-09-18T06:17:19.108Z",\n    "lastModifiedBy" : null,\n    "lastModifiedDate" : "2020-09-18T06:17:19.108Z",\n    "id" : 3251,\n    "description" : null,\n    "implementationStatus" : null,\n    "finding" : null\n  }\n}	1	admin	2020-09-18 06:17:19.11
3501	3451	org.tamisemi.iftmis.domain.Cluster	CREATE	{\n  "createdBy" : "admin",\n  "createdDate" : "2020-09-18T12:12:16.666Z",\n  "lastModifiedBy" : "admin",\n  "lastModifiedDate" : "2020-09-18T12:12:16.666Z",\n  "id" : 3451,\n  "code" : "CAG",\n  "name" : "CAG"\n}	1	admin	2020-09-18 12:12:16.666
3502	3453	org.tamisemi.iftmis.domain.Cluster	CREATE	{\n  "createdBy" : "admin",\n  "createdDate" : "2020-09-18T12:13:34.987Z",\n  "lastModifiedBy" : "admin",\n  "lastModifiedDate" : "2020-09-18T12:13:34.987Z",\n  "id" : 3453,\n  "code" : "PPRA",\n  "name" : "PPRA"\n}	1	admin	2020-09-18 12:13:34.987
3503	3454	org.tamisemi.iftmis.domain.Cluster	CREATE	{\n  "createdBy" : "admin",\n  "createdDate" : "2020-09-18T12:13:43.211Z",\n  "lastModifiedBy" : "admin",\n  "lastModifiedDate" : "2020-09-18T12:13:43.211Z",\n  "id" : 3454,\n  "code" : "LAAC",\n  "name" : "LAAC"\n}	1	admin	2020-09-18 12:13:43.211
3504	3455	org.tamisemi.iftmis.domain.Cluster	CREATE	{\n  "createdBy" : "admin",\n  "createdDate" : "2020-09-18T12:14:43.757Z",\n  "lastModifiedBy" : "admin",\n  "lastModifiedDate" : "2020-09-18T12:14:43.757Z",\n  "id" : 3455,\n  "code" : "INSPECTION",\n  "name" : "INSPECTION"\n}	1	admin	2020-09-18 12:14:43.757
3505	3456	org.tamisemi.iftmis.domain.Cluster	CREATE	{\n  "createdBy" : "admin",\n  "createdDate" : "2020-09-18T12:14:50.091Z",\n  "lastModifiedBy" : "admin",\n  "lastModifiedDate" : "2020-09-18T12:14:50.091Z",\n  "id" : 3456,\n  "code" : "IA",\n  "name" : "IA"\n}	1	admin	2020-09-18 12:14:50.091
3506	3451	org.tamisemi.iftmis.domain.Cluster	UPDATE	{\n  "createdBy" : null,\n  "createdDate" : "2020-09-18T13:12:21.014Z",\n  "lastModifiedBy" : "admin",\n  "lastModifiedDate" : "2020-09-18T13:12:21.049Z",\n  "id" : 3451,\n  "code" : "CAG",\n  "name" : "CAG"\n}	2	admin	2020-09-18 13:12:21.049
\.


--
-- Data for Name: iftmis_persistent_audit_event; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.iftmis_persistent_audit_event (event_id, principal, event_date, event_type) FROM stdin;
1001	admin	2020-09-17 06:44:34.880121	AUTHENTICATION_SUCCESS
1201	admin	2020-09-17 06:59:24.842437	AUTHENTICATION_SUCCESS
1801	admin	2020-09-17 08:46:13.487608	AUTHENTICATION_SUCCESS
2051	admin	2020-09-17 09:06:01.487229	AUTHENTICATION_SUCCESS
2401	admin	2020-09-17 09:18:43.832	AUTHENTICATION_SUCCESS
2751	adminadmin	2020-09-17 11:07:20.86	AUTHENTICATION_FAILURE
2752	admin	2020-09-17 11:07:28.497	AUTHENTICATION_SUCCESS
3151	admin	2020-09-18 06:12:49.996	AUTHENTICATION_SUCCESS
3401	admin	2020-09-18 11:27:21.354	AUTHENTICATION_SUCCESS
\.


--
-- Data for Name: iftmis_persistent_audit_evt_data; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.iftmis_persistent_audit_evt_data (event_id, name, value) FROM stdin;
1001	remoteAddress	127.0.0.1
1201	remoteAddress	127.0.0.1
1801	remoteAddress	127.0.0.1
2051	remoteAddress	127.0.0.1
2401	remoteAddress	127.0.0.1
2751	type	org.springframework.security.authentication.BadCredentialsException
2751	message	Bad credentials
2751	remoteAddress	127.0.0.1
2752	remoteAddress	127.0.0.1
3151	remoteAddress	127.0.0.1
3401	remoteAddress	127.0.0.1
\.


--
-- Data for Name: iftmis_persistent_token; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.iftmis_persistent_token (series, user_id, token_value, token_date, ip_address, user_agent) FROM stdin;
\.


--
-- Data for Name: indicators; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.indicators (id, name, sub_area_id, created_by, created_date, last_modified_by, last_modified_date) FROM stdin;
1451	  Council meetings held at least once in every three months	1401	admin	2020-09-17 07:46:17.453501	admin	2020-09-17 07:46:17.453501
1452	  Quorum for all decisions	1401	admin	2020-09-17 07:46:36.537587	admin	2020-09-17 07:46:36.537587
1453	  Minutes produced regularly 	1401	admin	2020-09-17 07:46:46.473812	admin	2020-09-17 07:46:46.473812
1454	  Meetings are held in public	1401	admin	2020-09-17 07:46:57.9746	admin	2020-09-17 07:46:57.9746
1455	  Meetings resolutions are implemented timely	1401	admin	2020-09-17 07:47:20.266121	admin	2020-09-17 07:47:20.266121
1456	Standing committees established according to procedures 	1402	admin	2020-09-17 07:48:46.775262	admin	2020-09-17 07:48:46.775262
1457	  Standing Committees meetings are held regularly	1402	admin	2020-09-17 07:48:57.761293	admin	2020-09-17 07:48:57.761293
1458	Minutes produced regularly 	1402	admin	2020-09-17 07:49:11.130786	admin	2020-09-17 07:49:11.130786
1459	Meetings resolutions are implemented timely	1402	admin	2020-09-17 07:49:31.668712	admin	2020-09-17 07:49:31.668712
1460	Clear, effective and efficient division of labour maintained	1403	admin	2020-09-17 07:49:55.560214	admin	2020-09-17 07:49:55.560214
1461	The Council Director leads and exercises control over the LGA	1403	admin	2020-09-17 07:50:12.112267	admin	2020-09-17 07:50:12.112267
1462	  Village General Assembly meetings held every three months	1404	admin	2020-09-17 07:50:25.170987	admin	2020-09-17 07:50:25.170987
1463	  Relevant agenda tabled and discussed (Revenue and Expenditure, Land matters, Social Services and Security affairs)	1404	admin	2020-09-17 07:50:38.595032	admin	2020-09-17 07:50:38.595032
1464	Village Council meetings held at least every month 	1404	admin	2020-09-17 07:50:51.082587	admin	2020-09-17 07:50:51.082587
1465	  Quorum for all decisions at Village Council	1404	admin	2020-09-17 07:51:00.332163	admin	2020-09-17 07:51:00.332163
1466	Relevant by-laws adopted and enacted	1405	admin	2020-09-17 07:51:46.579208	admin	2020-09-17 07:51:46.579208
1467	  By-laws duly signed and authorised	1405	admin	2020-09-17 07:52:14.359585	admin	2020-09-17 07:52:14.359585
1468	Public duly consulte	1405	admin	2020-09-17 07:52:29.361029	admin	2020-09-17 07:52:29.361029
1469	  Registration of incoming / outgoing mail	1406	admin	2020-09-17 07:52:41.015334	admin	2020-09-17 07:52:41.015334
1470	LGA has functional, orderly archive	1406	admin	2020-09-17 07:53:39.58175	admin	2020-09-17 07:53:39.58175
1471	Files are kept for minimum period provided by legislation and records ret	1406	admin	2020-09-17 07:53:53.167088	admin	2020-09-17 07:53:53.167088
\.


--
-- Data for Name: inspection_activities; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.inspection_activities (id, days, inspection_plan_id, objective_id, auditable_area_id, sub_area_id, created_by, created_date, last_modified_by, last_modified_date) FROM stdin;
\.


--
-- Data for Name: inspection_activities_organisation_units; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.inspection_activities_organisation_units (organisation_units_id, inspection_activity_id) FROM stdin;
\.


--
-- Data for Name: inspection_activities_risks; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.inspection_activities_risks (risk_id, inspection_activity_id) FROM stdin;
\.


--
-- Data for Name: inspection_activity_quarters; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.inspection_activity_quarters (id, days, activity_id, quarter_id, created_by, created_date, last_modified_by, last_modified_date) FROM stdin;
\.


--
-- Data for Name: inspection_areas; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.inspection_areas (id, name, inspection_id, auditable_area_id, created_by, created_date, last_modified_by, last_modified_date) FROM stdin;
\.


--
-- Data for Name: inspection_budget; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.inspection_budget (id, quantity, frequency, unit_price, gfs_code_id, inspection_id, created_by, created_date, last_modified_by, last_modified_date) FROM stdin;
\.


--
-- Data for Name: inspection_findings; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.inspection_findings (id, code, description, condition, disclosed_last_inspection, causes, action_plan_category, implication, is_closed, work_done_id, category_id, sub_category_id, created_by, created_date, last_modified_by, last_modified_date) FROM stdin;
\.


--
-- Data for Name: inspection_indicators; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.inspection_indicators (id, name, inspection_sub_area_id, indicator_id, created_by, created_date, last_modified_by, last_modified_date) FROM stdin;
\.


--
-- Data for Name: inspection_members; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.inspection_members (id, role, user_id, letter_attachment_id, declaration_attachment_id, created_by, created_date, last_modified_by, last_modified_date, inspection_id) FROM stdin;
\.


--
-- Data for Name: inspection_plans; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.inspection_plans (id, name, organisation_unit_id, financial_year_id, created_by, created_date, last_modified_by, last_modified_date) FROM stdin;
\.


--
-- Data for Name: inspection_procedures; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.inspection_procedures (id, name, inspection_indicator_id, procedure_id, created_by, created_date, last_modified_by, last_modified_date) FROM stdin;
\.


--
-- Data for Name: inspection_recommendations; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.inspection_recommendations (id, description, implementation_status, completion_date, compliance_plan, created_by, created_date, last_modified_by, last_modified_date) FROM stdin;
\.


--
-- Data for Name: inspection_sub_areas; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.inspection_sub_areas (id, sub_area_id, created_by, created_date, last_modified_by, last_modified_date, general_objective, inspection_area_id) FROM stdin;
\.


--
-- Data for Name: inspection_work_dones; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.inspection_work_dones (id, name, is_ok, procedure_id, created_by, created_date, last_modified_by, last_modified_date) FROM stdin;
\.


--
-- Data for Name: inspections; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.inspections (id, name, start_date, end_date, inspection_type, financial_year_id, organisation_unit_id, created_by, created_date, last_modified_by, last_modified_date) FROM stdin;
\.


--
-- Data for Name: meeting_agendas; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.meeting_agendas (id, description, meeting_id, created_by, created_date, last_modified_by, last_modified_date) FROM stdin;
\.


--
-- Data for Name: meeting_attachments; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.meeting_attachments (id, name, meeting_id, attachment_id, created_by, created_date, last_modified_by, last_modified_date) FROM stdin;
\.


--
-- Data for Name: meeting_members; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.meeting_members (id, name, phone_number, email, title, meeting_id, created_by, created_date, last_modified_by, last_modified_date) FROM stdin;
\.


--
-- Data for Name: notifications; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.notifications (id, email, subject, body, is_sent, is_read, attachments, created_by, created_date, last_modified_by, last_modified_date) FROM stdin;
\.


--
-- Data for Name: objectives; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.objectives (id, code, description, created_by, created_date, last_modified_by, last_modified_date) FROM stdin;
1301	001	  To insure LGA Meetings are conducted accordingly and resolutions are implemented timely	admin	2020-09-17 07:23:37.582556	admin	2020-09-17 07:23:37.582556
1302	002	To insure Standing committees established accordingly and resolutions are implemented timely	admin	2020-09-17 07:23:54.782167	admin	2020-09-17 07:23:54.782167
\.


--
-- Data for Name: organisation_unit_levels; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.organisation_unit_levels (id, code, name, level, is_inspection_level, created_by, created_date, last_modified_by, last_modified_date) FROM stdin;
1	PORALG	Default Level	1	t	system	2020-07-21 00:00:00	admin	2020-09-17 12:08:49.142
3051	REGION	Region	2	t	admin	2020-09-17 12:09:13.732	admin	2020-09-17 12:09:13.732
3052	COUNCIL	Council	3	t	admin	2020-09-17 12:09:51.934	admin	2020-09-17 12:09:51.934
\.


--
-- Data for Name: organisation_units; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.organisation_units (id, code, name, address, phone_number, email, background, logo, logo_content_type, organisation_unit_level_id, parent_id, created_by, created_date, last_modified_by, last_modified_date) FROM stdin;
1	\N	DIFT PORALG	\N	\N	\N	\N	\N	\N	1	\N	system	2020-07-21 00:00:00	system	\N
3101	DOM	Dodoma	Dodoma	\N	ras@dodoma.go.tz	Dodoma	\\x89504e470d0a1a0a0000000d4948445200000200000002000806000000f478d4fa0000000473424954080808087c086488000000097048597300000b1300000b1301009a9c180000001974455874536f667477617265007777772e696e6b73636170652e6f72679bee3c1a0000200049444154789ceddd77bcdf557df8f1d7cd06022110b680801207580202da5a0c21387fda3a402a6e2daeb6b675d55a5b6d6b9dadd63a5bad56dc0b15dbaac8aa38408d8361084346d889618418c8b8bf3fcefd9abbeff7de733ee77cc6ebf9787c1eb919f77cdf9f93fbfd9ef7e74c902449922449922449922449922449922449922449922449922449922449922449922449290d940e4052e3cd028e035600c7004b81fd8185437fbf11b819b812f831703e7009b03d7ba4922429da0380b7016b81c1695e370e7def03b2472d499266644fe043c07d4cbfe11f7d6d063e3854a62449aaa9d38075c437fca3af3b805333de872449eac31cc2537fea867ff4f581a1d792244985ed049c4df58d7feffafad06b4a92a44266035f215fe3dfbbbe813d0192241593a3db7fa2ebfd19ee4f92248d721ae51affde754ae5772949927e6b0fc2ccfcd209c03a6049c5f72a49928694ecfa1f7dfd5bc5f72a499208bbf3a5d8e427d5b51938a0d23b963a6856e90024d5ce2b8179a58318663ef08ad2414892d466b3801b28ffd43ffa5a4b58922849922af028ca37f6135dc75578df52e738042069b8934a073089134b0720b5890980a4e15234b26b09ebf7771bba9e015c95a0dc1509ca902449e3584f5c37fd8d843d0446db839018c4947d47e27b95244943b612d7483f7392b24f8d2c7b6bb2bb94244923c44ed4db7592b21725285f522203a50390542bb18dec549f2955972fa94f4e029424a9834c002449ea20130049923ac8f1349530001c4558d77ddcd0b53f30177f26a5badb066c042e05ce03ce07be0fdc5f32284d9f1fb6ca6909f072e0f9c06185639194ce7ae0b3c0fb812b0bc7a23e9900288785c09b08a7cced52381649d5d90e7c01783de15029d598a76ba96a8f07be0d3c917a1d312b29bd01e008e00cc230c12565c3d164ec0150550680b7006fc4c9a652579d053c8f900ca8664c005485d9c0478117148e43527997004f06d6950e4423990028b50142e3ffa2d28148aa8d1f1356fddc533a10ed60d7ac52fb1b6cfc258df448c22a011f3a6bc449804ae944e06398584a1aeb706013f0bdd28128301b532a3b113606717dbfa489dc071c095c553a10f9a4a6745e838dbfa4c9cd07de533a0805f60028855d81eb803d0ac721a9198e254c0c5441f6002885e763e32fa97f7f593a00d903a0347e4498e52b49fdf80db02f7077e940bacc1e00c53a101b7f49d3b313f084d241749d098062ad281d80a446f2b3436ab8f7018391d73ac2e6418b33c72e697a76024e266cef1bfbbe7712a0d470df22ee43e04ee081b9839614653eb08ab8f7fedd380f4d6ab45f10f721f0aafc214b4ae058e27b0176cd1eb5a464ae25ee03e021f9439694c87ae2defffbe70f593d4e02546976014a520126008ab531f2fb57268942526ec710bf0198c7034b0d163b09f056e0e0ec514b8ab18070aa9f9300a50e4bb10c703df062dc4e58aabb9d81c71396f0c5beef5d0658d89cd201a8f152bc89f7003e3a7449ea061380c29c03a058e7970e4052239d573a80ae73fc45295c4258132c49fdd804ec87870115650f8052f864e9002435ca57b1f12fce1e00a5b02b701d4ee293d49f47023f291d44d7d903a014ee01de533a08498df00d6cfc6bc11e00a5b200b80c38ac7420926a6b3370247075e940640f80d2d94c58cbbfad7420926aeb4dd8f8d7c6ecd201a855ae2724002b4a0722a976ce06feac7410dac10440a95d043c0038ba7420926ae347c05381fb4b07a21d4c005485ff060e0496950e44527117034f02ee2a1d8846320150150681af0f7d7d024e3695baeacbc0d3b0f1af253f9855b595c0c780834a0722299b8dc05f011f281d8826660f80aa762df01f435f2f03e6158c4552b5b6019f213cf59f5b38164dc11e00e5b427f032c293c1c2c2b1484a673bf041c2f1e057158e457d3201506e8b815b80f9a5039194d4e3816f970e42527dbd943049b08aebcc8cf7d156db88fb3f986a73b1d8ff63c5990ddc4635efbfff409226f17f5497006cc4a185582600ed7612d5bdffd60173f2dd8a62b915b0723a08784c85e5ef42d86c44d2f84ea9b0ec3d7117d0463101504ea753fdbc93d32b2e5f6aaad984d9f955aa32c190d46097525df763efda02ec93eb865ac82180f6aab2fbdf618006b20740b91c051c91e175e600a766781da969723c9def099c98e1759480098072c9d935ef308034528eeeff1e870124fdd62ce046aaef7e1c7e1d9ee5cedac7218076cad1fdef3040c3d803a01c4e241c119cd31f657e3da9ce723e953b0cd0102600caa14497fc7370a74b09f276fff7380c208905c006f276fff7aee332dc5fdb3804d03e39bbff1d0668107b0054b5a700bb177a6d27034a659ec61d0690c45729f3f43f48d8f3dca790e9b107a05daadcfb7faaebdf33dc9fa49a5a0c6ca65c0230083ca1f2bb6c1713807629d1fddfbb1c06a839870054a553297fecafc300eab29293f11c06903a2cc5c97ff7447ebf27044e8f3d00ed51b2fbbf77390c2075d041c076e23e3cae06fe33b28c41e0d915df6b9b9800b4478aeeff4d91df7f070e03d4964300aa4a8a75f89f023e9d20168701d44529baffcf8dfcfe25380c20754e8a93ff0e2724a96b23cbf184c0fed903d00e29baffef004e8e2c63108701a44e398af80f8d1f0e2befdd09cafb938aeeb56d4c00da2145f7ff470889c4ad91e5380c50530e01a80a29badc3f3dc1d733e53080ba2445f7ff170909e15991e5380c2075448a93ffc6ebb24f35a4a0c9d903d07ca9baff7b4fed2b22cb1ac461805ab20740a9a538f9ef1cc207d8709f8d2c13e0b404654875b71cd83bb28caf005b87bebe90b1efc7e97a1a0e0348ad9762d9de78ddf50713bfacf02a3c21702af60034df8789afe795a3cafc5082324faee05e25d5448a93ff26dbb8e7bb91650f02c7a6bbdd56320168b6d4ddff3d0e03b49043004a29c5c97f5f252401e37132a034b9e5a4edfeef711840d2a4529cfcf7c449cadf83f8c3853c217072f600345b15ddff3d0e03481a578a93ff6e67eac6f96b91af31083c3efe765bcb04a0b9aaeafeef7118a0651c02502a294efefb2c63bb1e477318401adf72aae9feef711840d2b8529cfc775c1fafb300b833f2753c217062f600345795ddff3d0e03481a21d5c97ffd2ed1fb78e46b0d027f34f3db6d35138066aabafbbfc761801671084029a43af9afdf0f788701a49196536df77fcf8584b93a311c06905a24f736bd9e10581d7b009a2947f77f8fc3009280f427fff5eb9f13bcae27048e6502d03cb9baff7b1c069004c0bb88ff30f8d319bceed1095ef7073378ddb63301689e5447fff62b77c221a986aa3af9af5f9745bef620f0e019be765b9900344fceeeff9e14c300d37d4d25e62440c5a8eae4bf7ea53821d0d5006ab2d984497531d601174cf37bbe18f99a00a72428435221559dfcd7af83497342a076b007a0597277fff7380c207558d527fff5eba2c81806814746c6d0262600cd52a2fbbfc76180867308403355f5c97ffd724f007555a9eeff1e8701a48eaafae4bf7eed01dc1719872704ee600f407394eafeef711840eaa05c27fff5ebeb91b10ce209813d2600cd51b2fbbf4e3168861c02d04ce43af9af5f0e03a86b4a77fff7380c20754cae93fffa95ea84c05d12c6d454f6003443e9eeff1e87011acc1e004dd741c06322cbb806f85182587a3603674596b10bf0d404b14839a4786a4ef1f4be8df8f7de12c26146cacc0440d395fbe4bf7e390ca0aea84bf77f8fc3005247e43ef9af5f9e1098864300f55797eeff1e87011aca1e004dc732e088c8322e06d6248865b4edc0e723cb98033c33412c5295ead2fddfe33080d401ef26fec9632627fff52bc50981dfaf30be26b007a0deeafab45db75e094909953ef9af5f9e1018c704a0deeadad0d63531d1241c0250bf4a9ffcd7af1427049e96a00ca90a75ebfeef7118406ab1d227fff5eb60e24f08ac628e4253d803505f757fcaae6bef84a4087539f9af5f9e1038732600f555f706b6ee098a46710840fda8cbc97ffd724f00b5515dbbff7b1c06905aa82e27fff5cb130267ce1e807a6acad375dd7b29244d43dd4efeeb578a13021f9739e63a3001a8a7a634ac4d49548443009adab3883ff9ef73a43bf9af5f0e03a84deadefddfe33080d422df25fec9e3f8ec517b42e04cd903503f4d7baa6e4a6f85a4491c44fc92baab893f3c68a63ed1477c535d5ddb13c004a07e9ad6a0362d61e92c87003499ba9efcd72f8701d4064de9feef7118406a81ba9efcd7af542704ee9d3bf082ec01a897a63e4d37add742d230cb887f03ff307bd463fd0bf1f7f18aec51976302502f4d6d489b9ab8748a43009a488aaeef145df0b11c06509335adfbbfc76100a9a19a72f25fbf3c21b07ff600d447d39fa29bda7bd119f600683c4d39f9af5f9f4b5046d75603a8bce5c4cf3ff90af9f7e0e8b980b009588ca7e330809455534efeebd7c1784260bfec01a88f0f135f5f2bb3473d528a7b38297bd4524735ede4bf7e7d8ff80fa263b2479d9f09403d34bdfbbf27c530c087b347dd110e0168b4a69dfcd72f2703aa4996d3eceeff9e0b881f067806e51319a9139a76f25fbf3c21b03ff600d4431bbaff7b1c06901aa0a927fff5eb6ce23f88da7e42a00940796de9feef7118a0a61c02d0704d3df9af5f0e03a80996d38eeeff9e0b701840aabda69efcd72f4f089c9a3d00e5b5a9fbbfe723c4df93c30052459a7ef25fbf3e41fc07519bf704300128ab6dddff3d2b89ffbf771820318700d4d3f493fffae53080ea6c39edeafeef391f8701a4da6afac97ffdf284c0c9d90350561bbbff7b1c06906aa82d27fff5cb1302276602504e5bbbff7b1c06906ae8ddc4bf31ff347bd433770cf1f7fbbdec51e76102504edb0fcf697b8223354edb4efeebd7e5c4ddf376e0d0ec5157cf04a09c3677fff7380c50234e0254db4efeeb57ec098103c0b3530422119e8e9f1659c63ac29afb3afb6282324e49508624da77f25fbf0e267ed9e32fb3475d3d7b00ca687bf77f8fc300524da4da18a76e27fff5cb1302c7320128a30bddff3d0e03d4844300ddf61460516419753cf9af5fee09a03ae84af77f8fc300520db4f5e4bf7ea53821f066c207785bd803905f57baff7b1c06900a6bfbc97ffd4a7142e0c9d9a3ae8e09407e5deafeef7118a0061c02e8aeb69ffcd72f87015452d7baff7b1c06900a6afbc97ffdda89f8899077033be70ebc22f600e4d5b5eeff1e8701a4420ea61b27fff5ebbf88ff107e56f6a8ab6102905717bbff7b1c0628cc21806e3a9d6e9cfcd72f8701544257bbff7b1c06900ae8cac97ffd4a7142e0fdc092dc8157c01e807cbadafddfe3304061f60074cf32e088c8322e06d62488a52eb613ff3432179f46343d297e5e523c4597b28db01439c612e0b10962913aa16b27fff5eb91c4d7cb45d9a34ecf1e803c7cfa0d3c2258caa4ab27fff5cb13024d007249d1f035b9fbbfc744a8208700baa5ab27fff5cb1302954bd7bbff7b1c069032e9eac97ffd7a209e10680f40f57cea1dc96100a9625d3ff9af5fdf27fec3e8e8ec51a76302503d1bbc914c880a7108a03bba7ef25fbfdc134055b3fb7fa454c300272488a553dab2939ba6f655e00f22cbd8489804d86603c0ee9165dc021c48f8606b9a6dc43d18cc260ca34c24f629bee99f59b3092748ee1d51c63a603f9a7f0ec7702b09f38b627c1878798258a4564971f29fd7f4aea66ecfea1040b5ecfe1f9fc300053804d00da7117ff29fa6e725a503502dd9fd3f3e8701a48aaca7fc1371d7aeed34f384407b00aae353eee452f48e7c287bd40d660f40fbad04f6281d44070de08791463a91b8b17f802fd3aeb1ffe1ce076e8f2ce399b437414ace04a0fdfeb574001de6d9001aceeeffc96d03be165986c300d36002d07e0f2a1d4087ed44f367ad2b8dd9c01f4696b10eb830412c75e611c1199900b4dfdcd20174dcc34a07a05ab0fbbf3fe7e1304036569254add8cd97d40e299e4a9f0d9c9aa09cba8b9d3cdb1b06382f412cad6602d07edb09dd8f2ae317a503507129baff01764d5046579c8209c0941c0268bf7b4a07d071f7960e40c5a5e8fed7f43c1b1f70a76402d07eab4b07d061bddd17d56def2a1d4007ed4637864ba29800b4dfdad20174d8bad201a8b8dd81a34a07d151ef291d40dd9900b4df4da503e830932fb90f47397be3bc89499900b4df95a503e8b035a50350718f2b1d40c7fd59e900eacc04a0fd4c00ca71fe855c065ad671a503a8336749b65f8a04e0e9c00509ca699245c0d5c42da134f9920f5965790aea244c00daef66e06ec2acd8997a1470569a701a6305f1fb279800e837d80895e47b701266a7ed37087c3fb28c67d3bd9f9567477effaf815fa608448d7663e9003a2ef6b3afd5baf6a1de55e7477eff0380c7a608a42116034f8e2ce37cc22e8ceab69f950ea0e3ee2c1d409d990074c3b909ca785182329ae274e2bb6d63932eb5c35da503e83877429d84094037fc0cd81059c6b3804312c452777381bf4c504e8aa44bcd7773e9003acebd38266102d00ddb806f47963117786d8258eaee39c4273ad7e1124005fe1c94b309138049990074c7a71294f142e0d004e5d4d502e08d09ca4951d76a0767a197b306e7e14cca6580ddf14de036609f883216001f049e9024a2fa793d705882724c00d47335b095b8cfda97015f48134ea37c113829e2fb4dbea461de4b5816187b3d2d77e0191c4658b31d5b373fcc1d7862db88bbffa97a1563ebb789d61077cf9fc81e71790b0813f862eaedcdb98396ea6c196912805b81fd32c75ea5b9c0f74853372fcf1c7b6a2600e97d82b87bbe0bd83977d0859d42fccfcac9d9a3966aee5cd234741710bf535e5dbc933475b20e589839f6d44c00d27b3ef1f7fdacec5197f575e2eaeb3e6097ec514b35b782348ddd20f08eccb157e11984894229eae30d9963af8209407a07127fdfdfcc1e7539fb02f713575f17668f5a6a888b489704fc45e6d8533a8134e3fe83841dc776cf1b7e254c00aa7115f1f77e4cf6a8cb48d123f777d9a3961ae2c9a44b00b61176ce6b9a6584cd9152d5c39bb3465f1d13806abc8ff87bff62f6a8f3db93f8c97f83c0f1b903979ae43ba46bfcb69366f7bc5c1e4b78624f75ffb7d09e73df4d00aa711cf1f7be0d382a77e099bd83f87a72f99f3485a5848932a91ac141e01f81819c373103a7029b497bdfa766bd836a990054e797c4dfffc5b47703b7c349f3defc9bdc814b4df456d2368483849e857d73de449fe6006f27dd84bfde754ece9bc8c004a03a6f24cdcfdc8b73079e498a154adb69f76ea552323b03bf227d12b0967aadc17d087009e9ef7333e1a9a54d4c00aa73106157c0d83ad840fb1ab93f25cd7bd2d9ffd2341c4ffc929b89aeb3091f7aa5ec4c989c97bacbbf77fd49b63bc9c704a05a9f27cdcfde8f80799963afca2349f71e7d6ae6d8a5c67b3dd5349083c0dd84a186bdb2dd4dd846f495c00d09ef63f4f5a56c7793970940b51e41ba61a80f668ebd0a7b01d790a63e7e4efde72049b53300fc37d5359683c046c259040faff03ef627243337577c2f57d39e59ffa39900542f7697bbe1578ad32b4bd919f83ee9eaa24d9371a5ac9650cd7c80f1ae9f00af264d327020e198e26f91667c75aaeb5edabd218b0940f5522c09ec5ddb8133f2869fc47cc2ee86a9ea6135edd99a5c2ae270e076f22401bdeb56e073c05f03cf048e24740bee342cae79c01e84c97c4f26240f1f25fe94b5e95e5b865ebfcd4c00f2483517a09704bc2e6ff851161212f694efcda764bd03a9a58e25cd4e5c29aeada4dfab20e643f60533afd6c63001c8e300c2fc98943fa3efa4fe7b04ec07ac22ed7d7f3deb1d482db782ea66ce37f56ad213560c13807c5e4dfa9fd373a9e73e1c00cb493f3f67137048c67b903ae124d23fa134f1da4e7bf6f9ef8709403e73805f90fe67762d2189af8bb984f7501573749a3c0952aab563c93f27a04ed756e025d1b5d82c2600791d49788aade2e7f76ce001f96e655c27009752cdfd5d4c7bf642906ae921c0f5946f8c735ff7d2fe097fe33101c8ef0caafb39de40784acebd6cf548e00ba4df7abb77ada7ec26635267ec4b9a7dba9b72ada1fda7ae4dc404a08ccf50edcff49d84c3baaa6c3407084ffc67515dc33f3854b6b3fea58c06089bece4586b5ff2fa32b07ba23a6b221380327625cd6981535ddb80f3087b67a4982c3800fc0ef0f7c0b519e21f241c192ca9801554bfd35e896b13f0b284f5d4542600e51c4c98c097f3e7fe32e07dc08b80df05f69c24be598419f74f00fe9cb09741ee39429fa5fe4b1da5565b04fc2bede90d381b9712f59800947504f06bcabe1f7e03dc41d8a3ff97c0754331c5fe6cc45ee7e0a43fa9368e22ed5edeb9afb5c0f392d74ab3990094773ce1ec8cd2ef8f3a5d3f260c9348aa9159c073802b28ff21d1ef752b61639f9d2ba88fa63301a88715c05d947fafd4e1fa01930f4d482a6c166166ee2594ffc098e8ba953091d1867f622600f571247013e5df3725af73f0c95f6a8c01e0f184c93a556d70329d6b1bf06de0b9387ed80f13807a3984fc875ed5e53a93b08ba0a4065a04bc18b880fc13067f4ee8e63fa0ea9b6c191380fad99bf0245cba41ce756d01de40789890d4020b8195c0db09137a52cf28be99b0f3d819b843580c13807a1a005e05dc4ff906baca6b2df0fb89ea4c5330c352298b818712b61a3e7ce83a68e8cf170e5dbdb1fafb09b3a2ef241c557c2bb01ab892d03dba9a3056aa78db885b673d9bb053db44621bf1ae7f662d073e0dec5f388e2afc2f6155cebad281482a6f368edbe7660f40fded4ebbf6e1584fe8b9eb7a72274945990034c732c212b9d20df84caf6dc0278125a92b4692347d2600cd328b30d1f61aca37e8fd5edb81ff068ea9a03e2449336402d04cb3805380cb29dfc04fd6f09f0d1c5b511d489222980034db2ce0e98427ec2d946ff4070993fade0f3cbcc2fb9624453201688f3d0893eb2e227fa3bf99f0b47f0a4ee4ad2d675d4a1ace6580edb41ff018c25e1c4fa09abd32ae05be33749d4358b6ab1af3cd24693813806e389c70d6c0e1c052c27e1c8710f6e1986cfbdd7b095dfa6b08fb70ac1efa7a1561399f1ac43793a4e14c00349fb011d72242f7fd26c2065c77e2304cabf86692349c0980d411316f744992d450260092247590098024491d64022049520799004892d441260092247590098024491d64022049520799004892d441260092247590098024491d64022049520799004892d441260092247590098024491d64022049520799004892d441260092247590098024491d64022049520799004892d4412600922475d09c71fe6c1fe08f8095c0c1c0ce59236a9f7b81eb81ef009f056e2f1b8e2449230d00af07ee0106bd2ab9ee015e3b54d7521d6d23ee677caa5ec5d8f790a4c466019fa67c03d995eb4c4c02544f260052c7bc85f28d62d7ae37f5f53f23e565022075c400f040603530bf6c289d733ff050e0dad28148c36c236e72f06c60fb247f1fdb88db732625320b782936fe25cc035e543a08495237cd029e503a880e7b62e9002449dd340b38a474101d7668e9002449dd3480136b4a734cb3fe76037e0f7814700c7034b098308c0361cc7b2b701f938f7f37c1e2c8efdf50b8fcba9b4518729dc38eb916f703bf0656013f017e087c97b0878854191380f24c00ea6927e059c069c0498cbf699654952dc079c0a780af009bca86a336320128cf04a05e76075e03bc0cd8b3702c12c07ae07dc0bfd1fc1e10d5880940792600f5300b7839614f0c1b7ed5d17ae00dc0c768fe50936ac004a03c1380f20e013e019c50380ea91f1702a70337950e44cd6602509e09405927005f04f62e1d88340d7710e6a79c573a103597c701abcb4e07cec5c65fcdb317f03fc029a50351739900a8ab5e007c1267f7abb9e6039f23ac5691a62dc510c061290269b06b22bfbf6b6701cc0676252cb39b4398cc74377029f005e002e02aaa9de4f478e01bd8f8ab1dee079e44e8cd92fa962201e8fa18b67328d2db08fc8cb031ca4f877ebd82b0d94eac4387ca5b94a02ca92ed603cb801b4b07a2e6300188670290c766422fc1aa61d7a584ddf7fa3587b0c3daa3924727957711f0585c22a83e9900c4330128670ba167a09710fc14f839a107613c7f09fc739ed0a4225e017ca874106a061380782600f5730b614ff5def543c284d735847dfda5b6da001c0eac2b1d88eacf04209e094033dc0bec523a0829837f02de583a08d59f09403c13004975721770f0d0afd284dc074092da6511706ae920547ff600c4b307a05bee003e4bd83ef846e03784c9885dd07b60a86a9679d5e5d7cd5cc27e1807002703cf279c4b91c245c0ef272a4b2d650210cf04a09bee25ac3818be02e172ba930c28bd79c06b092752ce8e2c6b3bb00f4e06d4244c00e29900a8670b6117c3e12b1056019b4a06a5c6792af015e2938053802fc587a3b63201886702a0c96c25ec55d0dbd17015a1e7e09e9241a9f65e07bc23b28c7f015e9d2016b59409403c13004dd776424fc1f0ad8e5711d6704b10e6075c4658d33f531702cb9344a3563201886702a0547ec5d8a4e0b6a211a9a4d700ef8af8fe0dc01e8962510b9900c4330150956e66e444c355c00d4523522e4702bf88f8fe6d78e2a526610210cf0440b9ad63642fc12ac2b1d4fe2cb6cb42e2e78a74fdf35993300188175b7f872589a27966138ee63d00380838067834b067c9a01aec1ec2d3626ff5c1e584d312ef2f1994a2c57ebecca63bfb2a689a4c00e2597f69ed4f48067ad7b184f5cc9abef19625fe9870b4b29ac104409531018867fd556f7452f03042ef81a66f2be15444f72a6886edc47d46980068422600f1acbf3216030f676462f050accf99d8065c491836b88290147c1f585f322801e1ff26e6cc1613004dc804209ef5571f8b0833a78727050fc143af66ea1646f6145c0cdc5e34a2ee310150654c00e2597ff5b610388a306cf070e0b1c011c46fb3da55a39382cb816b8b46d46e2600aa8c09403cebaf79e61276583b015846480e8e269ccca6e9dbc08ea183de75052e4b4cc104409531018867fdb5c31c602923870f8e027629195483dd45d8ca767852f04b6c8ca6cb04409531018867fdb5d76c425270f4d0b56ce85a5432a806db085c0fdc44e821f81a7041c9801ac004409531018867fd75cb0061f3a665ec480c8e0696940caac1b6023f231c7ffb75c29c02ed6002a0ca9800c4b3fe046137c3e149c132c22e879a9eab80b3808f03ab0bc752072600aa8c09403ceb4f13d99db0e2c0bd0aa66f10381ff82061a8606bd9708a310150654c00e2597f9a8ec5ece821e8f5163c18f72a98cc95c0df025fa47b2b0b4c005419138078d69f62f5f62a189e183c0c8f721ded67c05fd0ad89832600aa8c09403c7d59c57c0000161449444154eb4f555840d8d570f844c32381f92583aa8141e0538444a00b5b159b00a8322600f1ac3fe532de5e05bf43e841e89a5b811702df2c1d48c54c005419138078d69f4a9a4dd8d570f40a84dd4b0695c976e01f81b7d0de46ce04a0be76024e044e22ac02da0dd815d817d863e8ef0709c76fdf4ed832fb06c2699cab09735bae25fc1f17610210cffa531d8d3e42f991840fa636fa16702a7077e9402a600250d600a1713f7ce87ae8d0af47017b25287f03702161c5cbb964de07c304209ef5a7a67800237b098e1efab33658053c91f69d56680290c76e84867d29e104d1c387fd3ee71921ab813309f35c6ea8fac54c00e2597f6ab2bd19bbabe1a145239ab935c0e308db0db78509403ab38143080d7baf915f3a74ed5730aef16c07ce03de099c53d58b9800c4b3fed436bb33764ec1e134e308e56b80c7102609b68109c0f4edc98e467e293b1afa0701f30ac6355397006f05ce26f13e182600f1ac3f75c12e8471cfe189c1c308472bd7cd4f81e5b4634e8009c0f8e611cee4e83dc10f6ff0f72c1857952e025e015c9aaa40138078d69fba6a3ee3ef55b0a0645043ce051e4fc119d689743d01584c18927a3821e13c74d8efebf07396db56e07dc09b817b620b33018867fd493bcc217c50f7860e8e018ea54cd7ebdb80bf2ef0ba29752101d8891d93ee4677dd7bf4f6f8ae074e037e18538809403ceb4f9adc2ce011c0e984f1f98309930fab9e5330083c15f846c5af53a53625008b19f924dffbfa609a31bfa46eee035e03bc7fa6059800c4b3fea4e95b4858b6f72c42235dd55c82758446e68e8acaaf5ad31280f984c976a31bf9a57473c7ca1c3e43d815f3fee97ea309403ceb4f8ab33ff067c09f10261ba6f61942ef4313d53101980d3c90f167daef9ff8b5d49ff380a731cd89af2600f1ac3f298d7d09e3f6cf27fdfbe2c9c0ff242e33879209c0ee8499f6a327e13d14d839222655e3c7c09398466f9709403ceb4f4a6b25f05fa47d9a5c4368c4b6262c3387aa1380b9c0818c3fd3bea91b4275d92ac2f9047df5049800c4b3fea4f4f6013e0f3c3661997f0c7c34617939a44a00f661e416b7bdaefb43082b37d41ee7127abceeebe71f0f465e5d67fd49d59847480262df63bd6b2d79f7754f611b71f77c31e1c0995475e8d58cebf3f4f97019fb425d67fd49d5994dda24e02579c38f169b007875f77a35537008209ef527556b1ef0bfc08a0465fd8cb04151dded4ae8aaff117e46d4d956e0cea1eb36c236d4df066e26fcbfcd1efa377b12e6543c0478346183acaaf73ed8029cc0249b059900c4b3fea4eaed01fc84b0fc2cd66380ef25282785fd19bbc5edc308f71933f6afb43600d7025700970f7d7dedd0d79b6750de6e8419fbcf259c6059d53c8ceb0909ef86f1fed204209ef527e5f1fbc005c4378c1f075e141d4dff265a4ef710aad9f74033733f619ec8e846fe52c2d37d550e2274d7bf846a96577e1878f9787f610210cffa93f2f900e144b418eb097b0ea45c123887f041ee72bafadbc0d846fe0a6035650f8fda1f783ba15720a5edc0ef31ce508009403ceb4fca6777e01ac290408c9308bba74d97a7d335c37d849f93d18dfccf49708a5ec5fe00f84fe27fc687fb09703ca3121c138078d69f94d71b807f8a2ce37dc0ab26f8bb79c00318dbc81f49584faffab885b18dfce5c075d4e710a4993808f832f0c884653e0f3873f81f9800c4b3fea4bc16013711377ebe8ab0e3e0784ff24bf174ba3ab993f0343fba915f0ddc5b30aeaaed027c097842a2f256137ebe7f9b189900c4b3fea4fc3e029c513a0825b315b881b18dfcb5c0af88ff9c6daa79c0d74897049c42482a00138014ac3f29bf1399d918becaba83f0247a25e17c862b877eff2bc2ba758db50b617bdfe31394b58ab0070160029082f527e53707b895b0c18aeae53ee02ac636f26b98603dbaa6b40f6113ab7d1394b56ca82c0f8190d4485b81ef027f583a900e5bcb8e06be77ad216c3e5372395d1bdd06bc9070a475ec43e373194a00ec018867fd4965bc0e7847e9205aee5e463ec90f6fe837168cabab3e49fc3e01b71156b96c35018867fd4965fc3fe0ecd241b4c076c253fbe8eefa35c08d05e3d258fb10fe8f164596b30238df2100494d754de9001ae62ee06abab79cae4d6e23ec86f9d791e5ac00ceb707209ef52795b18430ab5c3b0c1266d45fced84978b7178c4be92c21f4d8c49c1bf003e07721fecce1aeb3fea4321650feccf53a5c5b0993ba5e455837aef6fb24713f335b80ddec018867fd4965cc259ce0d6565b0863f0a337c6b90cf835b0dfd0bfbb8576d783c65a099c1359c672e700486aaadd4a0790c84d8c3fd37eaae574d7571f9a6aea7cc216c9bb4794b1d404405253c5ce84cee95e76ccac1f3dd3beeea7d3a97eb611f6c1784a44192600921aebb0d2018ca3ada7d3a97ebe870980a48e5a5ae875ef62eca638bd5f37178a49ddf3cbc8ef3fc0044052531d5761d95b09cbe94637f4ab096bb1a5d2ae8afcfe5d4d002435d58909ca58c7d831f9d584ee7b67d6abced6477effaee03af658d69f94df23887fef9d9a3d6a299d9d88fbf9ffcdacfc314b52b4d8035120cca2969a2a7652e91cf0093696f527e5351fb899b8f7dd15d9a396d2da97b8f7c03df600486a9a17b06317bc993a37411c52498b23bf7f930980a426990fbc3e4139e72728432a69ffc8ef5f670220a9495e071c1259c626e2f751974a3b26f2fb6f320190d4140f06de90a09cb370fb5d35dfd191df6f0220a911e6039f232c7d8a75668232a49206804747967105388b3d96f52755ef63c4bfd70681b5c0ecccb14ba93d9af8f7c213dd09504db317f04260397028e1c37c3d6147b7f5c3bebe63d89f0dff75b2e355554fff00bc285159efc19f0135dfb31294f1d301e29f42071204d264d65f1eb38057037f0f2c882867032139189d34dc31eccf47270df6d494f377c09b1395b50e7820e1685ea9a91610cea9d837a28c35781aa01a6216f05fc0731294b598e9af9fdd4c481c6e261cf7ba61d435facf6fc3a7cc5873817f035e9ab0ccf762e3afe67b09718d3fc0ff4178faf409368ef557bd37139e049b621b638724460f4b8cfef3bb8b445a4f0f244cf83b3e6199b7000fc17a56b3cd259c02787064397f087ccd04209ef557ad4309e75ecf2b1d48c5b63072d8a197188c37bfe1f6a1af371689b43ab3084f37ef02764b5cf6b381cf262e53caedaf80b74596f11b6009b0c92100d5dd19b4bff18790d9efc7f4b6b8ddccf889c1e8390cc37b1e36a50b399959c03380bf058ea8a0fcf3b0f157f33d94343da16733f4396002a0ba3bb9740035b6003860e8ea576f3ec354f3187ad75ae0ae74218f7000703a6186ffd28a5e6313f0ca8aca967299077c92b809d03d9fe87de110403cebaf5af7000b4b07d1711b09bd07bd1e86d1cb2b7b3d0c1b0863ec6b09431ac3cd030e238cc33f0a5841d8c9aceacdc85e0cfc67c5af21556980d0683f2f4159371086557f3b49d98d6ce2587fd5da489a0d60bcf25edb091f325b86ae12317c12a9f9de42baf7c45f8c2e3cb6c0aeb3feaab586f28d9957f3ae5f00bb2035db9b08c9748af7c40660d7e1857b1680eaeebba50350e3dc003c11d7fcabb966031f226c7c966a98f8dd8c7308566c56d175d65fb58ea5fcd3a45773ae7584790652531d025c40daf7c5ad4cd023165b70d7597fd5fb1ce51b16affa5feb81e3909a6936f0efc056d2bf37263c4b23b6e0aeb3feaab718f829e51b18affa5e37038f406aa6d3082b68aa786f5cc824c308b185779df597c762e06b946f68bcea775d0e1c88d43cc713e63955f5deb8972986c4625fa0ebacbfbc9e087c9530a3b574c3e355fe3a0bd81da9590e07be40ba19fe135d2f9b2c0837028a67fd95b33f614feb25c0dec09e43d79261bfee35ecf73b97095315d84c381efa83a50391a6613fc276be2fa6fa9d78bf023c9349da28138078d65f732c00f660c791c0bd6b3f423231facf0f001615895493b998704cf0cf4b0722f56911f05ae0cfc9b33fc565c0a399e2c030138078d65fbb2d24f41ef47a127abd09e3f530f47eedc2e145256c20ec8af67e866d652ad5d83ce00584f5fcfb647acddb80df05ae9dea1f9a00c4b3fe34da6e8c9f18f41289bd183b5ce1c15c13db087c04783b619dbf5477b308075dfd037070c6d7bd13584e9fbd632600f1ac3fa5b098913d0c13cd6318fe776ddfc9f32ec2d3fe7bb1e157733c09781bf997a5de35f4dadfeff71b4c00e2597f2a6180b1bd0bbd0462af09fe6ecf22914ecf36e05ce04cc20c7fb7f355531c0bbc0338b1c06bff9ab042ea92e97c9309403ceb4f4d319bf11383c95650e49804f96bc2d6a7e7129678de9ce135a55496026f059e4e99cff32b81a700574df71b4d00e2597f6ab3b98c4d1af61ef5fbc309678c2f1afaf713fd4cdf4b68dcaf0456134e7afc09f033c27a68a949722ee99bc8b708bb08de39d30262371ae83aeb4f1a6b17e088a16bb7c2b148292d223cf16fa4dc0658db803793601e900d581ceb4f92da6f1e7006e164bd520dff20e1d0ab6353dd940d581ceb4f92da6b16f05ce03aca36fcdb812f1186d992b1018b63fd49523bad045651b6e1df4638d1efd02a6ed0062c8ef52749ed722c701ee59ff8bf003ca8ca1bb5018b63fd49523be43aa56faaeb1ce0e88aef151204da75d69f2435db5ec0bf025b28dbf0ff0838a9e27b1dc1062c8ef52749cdb410783d7037651bfeeb082b0cb26fef6d0316c7fa93a466a9cb92be3b0809c8fc6a6f7762366071ac3f496a8659c029c035946df837124eb72cbe49960d581ceb4f92eaaf0e4bfaee271c6dbd6fc5f7da371bb038d69f24d5576796f4cd840d581ceb4f92ea67291d5bd23713366071ac3f49aa8fce2ee99b091bb038d69f2495d7f9257d33610316c7fa93a4727a4bfa6ea36cc35f7c49df4cd880c5b1fe24293f97f425600316c7fa93a4bc56023fa56cc35fbb257d33610316c7fa93a43c8ea33e4bfa0eabf85eb3b0018b63fd4952b55cd257111bb038d69f2455c3257d15b3018b63fd49525a2ee9cbc4062c8ef5274969b8a42f331bb038d69f24c571495f21366071ac3f499ab93a2de9dba7e27bad1d1bb038d69f244d9f4bfa6ac0062c8ef52749fd73495f8dd880c5b1fe24696a2ee9ab211bb038d69f244dcc257d35660316c7fa93a4b15cd2d700366071ac3f49dac1257d0d620316c7fa93a4c0257d0d630316c7fa93d4752ee96b281bb038d69fa4ae72495fc3d980c5b1fe24758d4bfa5ac2062c8ef527a92b5cd2d732366071ac3f496de792be96b2018b63fd496a2b97f4b59c0d581ceb4f521bb9a4af036cc0e2587f92dac4257d1d620316c7fa93d4062ee9eb201bb038d69fa426abd392be1515dfab46b1018b63fd496aa23a2de97b1e3050e9dd6a5c366071ac3f494de2923efd960d581ceb4f5213b8a44f63d880c5b1fe24d59d4bfa342e1bb038d69fa4ba72499f26650316c7fa9354372ee9535f6cc0e2587f92eac2257d9a161bb038d69fa4d25cd2a719b1018b63fd492ac5257d8a620316c7fa93945b5d96f4dd834bfa1acd062c8ef527292797f429191bb038d69fa41c5cd2a7e46cc0e2587f92aae4923e55c6062c8ef527a90afb13bad94b2fe9bb0497f4b5960d581ceb4f524a2ee95336366071ac3f4929b8a44fd9d980c5b1fe24c570499f8ab1018b63fd499a2997f4a9281bb038d69fa4e972499f6ac1062c8ef527a95f2ee953add880c5b1fe244dc5257daa251bb038d69fa489b8a44fb5660316c7fa93349a4bfad408366071ac3f493d2ee953a3d880c5b1fe24814bfad440366071ac3fa9db5cd2a7c6b2018b63fd49dde4923e359e0d581ceb4fea960370499f5ac2062c8ef52775435d96f4fd0a97f429111bb038d69fd46e2ee9536b35ad015b08bc18f812b00ab8823001e7ef81871688a769f527a93f2ee953eb35a9013b0db8659258b6011f0576c9185393ea4f527f5cd2a74e684a03f606fa9f6dfb536071a6b89a527f92a6761c703e651b7e97f4299b2634604f67fa4b6dbe499e49324da83f4993abd392be6515dfabf45b756fc01600d7cf30b6676688afeef52769622ee953a7d5bd013b3522b60b33c457f7fa9334964bfa24eadf807d2c22b6ad543f73b6eef527690797f449c3d4bd018bdd63fb888ae3ab7bfd4972499f34aeba3760ff17195fd57b64d7bdfea4ae73499f348e39a503e8c3cd91dfbf364914d5b994300e78edd0afc3bfbeb7605c52d31d07bc03585e308641c2a6656f20f43e48b552f727d89746c4765986f8aa7c6ab80df821f019e0adc04b809380436846f22695e0923ea94f754f00960077cd30b6d76688afe407ccaf811f133eecde4e98dcb41238146715ab7b5cd2274d53dd1300080df974e3ba9ab08740d54a7ed04c766d240c2f7c1d782ff02ae0a984499139b74a96aae6923e69869a90000c009f9f464c7752fdecff9ed20dfd4c2f8717d4742ee9932235210100980dbc8b70e0cf64f15c41de53014b37e4555d0e2fa8ae5cd22725d29404a0e708c2e640c3b3fe2d845dffce00e6668ea774435de2727841a5b8a44f4a6480f846bce4d3e04242f67d1ba167a084124950ddddced8a58dbddfdf48d8a1519a0e97f44989353d01a8031380e9db404806c6bb7e8575aa1d9602ff4038d8abe467cd7780d7117a1fa456300188676395d6bd8cdd1069f8d76e8ed40d07007f0bbc88b293527f04fc15614b72a9554c00e29900e4e5f042bb2d045e09bc11d8b5601cd7017f079c89ef71b59409403c3f1ceac5e185669a07bc80d0ddbf77c138d601ef06de4398ec27b59609403c1b94e6b80fb889f19383ab081bc928af59c0330813fc0e2918c746e003c03fe1cf813ac204209e09407b38bc90d74ac2de1e47158c610bf071c27c83db0ac62165670210cf04a01bb6129280891284dbcb85d6382ee9936a204502706d8a401aecd0d201a8165cbd303597f44935922201509c930949c4f0eb41c0a2924129b9c92627de40bb87175cd227d590094079133d092d666c62b03fb01ff03060a72cd1299736ae5ea8cb92be35c0df10bafc9b588f52254c00ca9b6957e8780942ef7a206176b5daa169ab175cd2273580094079558c85ce2774bb8e971c38bcd03e75195e70499fd4202600e595980ce5f042b7e4185e70499fd4302600e5d57119a5c30bdd113bbce0923ea9a14c00caab630230198717ba65a2de03809712bafc5dd22735d000617df2cea503e9a84dc02ea583486c2fc2f86fef3a74d8d7070173cb85a61671499f146980b044e6c1a503e9a83584cd51ba62367020231384e149c2bee5425343ac212c2bfc32f65e4a51e600176002504ad79e5eb6118e59bd0e387f9cbfdf89913d06c3bf3e84b26bc955d61dc03fe3923e299901e0f7808b4a07d251bf0bfca074100de2f042f7dc0dbc13782f6ea72c25d59bbcf325c2641ee5f379e0b4d241b488c30bed721ff021e0ad840d7d2425d64b001601df031e5e30962eb98cd0f3e22625f9b87aa119b6039f01de44182a925491e1cb7716039f029e542896aef806f05ce0ced281680437472acf257d5246e3addf7d12f0c7c0e37079602af702e700ff0efc6fe158347d0e2f54eb12c292bef126864aaac8541b782c0176cb11488bdd8d63986de7f0c2ccb8a44f2aa869bbd0494de4ea85915cd227d580098054569786175cd227d5880980546f6dd81cc9257d520d990048cd56e7d50b2ee9936acc04406aaf92c30bdf015e03fcbcc2d79014c10440eaae2a8617ee045e087c35518c922a6202206922e3ad5e38867082e5f063ac07815b810f12c6f95dd22735800980a499980d1c36f4f59a92814892244992244992244992244992244992244992244992244992244992244992244935f4ff01ab633e53b78a3a3e0000000049454e44ae426082	image/png	3051	1	admin	2020-09-17 12:12:11.293	admin	2020-09-17 12:12:11.293
3102	DOM_MC	Dodoma MC	Dodoma Mjini	\N	dodomamc@dodomamc.go.tz	Dodoma Mjini	\\x89504e470d0a1a0a0000000d4948445200000200000002000806000000f478d4fa0000000473424954080808087c086488000000097048597300000b1300000b1301009a9c180000001974455874536f667477617265007777772e696e6b73636170652e6f72679bee3c1a0000200049444154789ceddd77bcdf557df8f1d7cd06022110b680801207580202da5a0c21387fda3a402a6e2daeb6b675d55a5b6d6b9dadd63a5bad56dc0b15dbaac8aa38408d8361084346d889618418c8b8bf3fcefd9abbeff7de733ee77cc6ebf9787c1eb919f77cdf9f93fbfd9ef7e74c902449922449922449922449922449922449922449922449922449922449922449922449290d940e4052e3cd028e035600c7004b81fd8185437fbf11b819b812f831703e7009b03d7ba4922429da0380b7016b81c1695e370e7def03b2472d499266644fe043c07d4cbfe11f7d6d063e3854a62449aaa9d38075c437fca3af3b805333de872449eac31cc2537fea867ff4f581a1d792244985ed049c4df58d7feffafad06b4a92a44266035f215fe3dfbbbe813d0192241593a3db7fa2ebfd19ee4f92248d721ae51affde754ae5772949927e6b0fc2ccfcd209c03a6049c5f72a49928694ecfa1f7dfd5bc5f72a499208bbf3a5d8e427d5b51938a0d23b963a6856e90024d5ce2b8179a58318663ef08ad2414892d466b3801b28ffd43ffa5a4b58922849922af028ca37f6135dc75578df52e738042069b8934a073089134b0720b5890980a4e15234b26b09ebf7771bba9e015c95a0dc1509ca902449e3584f5c37fd8d843d0446db839018c4947d47e27b95244943b612d7483f7392b24f8d2c7b6bb2bb94244923c44ed4db7592b21725285f522203a50390542bb18dec549f2955972fa94f4e029424a9834c002449ea20130049923ac8f1349530001c4558d77ddcd0b53f30177f26a5badb066c042e05ce03ce07be0fdc5f32284d9f1fb6ca6909f072e0f9c06185639194ce7ae0b3c0fb812b0bc7a23e9900288785c09b08a7cced52381649d5d90e7c01783de15029d598a76ba96a8f07be0d3c917a1d312b29bd01e008e00cc230c12565c3d164ec0150550680b7006fc4c9a652579d053c8f900ca8664c005485d9c0478117148e43527997004f06d6950e4423990028b50142e3ffa2d28148aa8d1f1356fddc533a10ed60d7ac52fb1b6cfc258df448c22a011f3a6bc449804ae944e06398584a1aeb706013f0bdd28128301b532a3b113606717dbfa489dc071c095c553a10f9a4a6745e838dbfa4c9cd07de533a0805f60028855d81eb803d0ac721a9198e254c0c5441f6002885e763e32fa97f7f593a00d903a0347e4498e52b49fdf80db02f7077e940bacc1e00c53a101b7f49d3b313f084d241749d098062ad281d80a446f2b3436ab8f7018391d73ac2e6418b33c72e697a76024e266cef1bfbbe7712a0d470df22ee43e04ee081b9839614653eb08ab8f7fedd380f4d6ab45f10f721f0aafc214b4ae058e27b0176cd1eb5a464ae25ee03e021f9439694c87ae2defffbe70f593d4e02546976014a520126008ab531f2fb57268942526ec710bf0198c7034b0d163b09f056e0e0ec514b8ab18070aa9f9300a50e4bb10c703df062dc4e58aabb9d81c71396f0c5beef5d0658d89cd201a8f152bc89f7003e3a7449ea061380c29c03a058e7970e4052239d573a80ae73fc45295c4258132c49fdd804ec87870115650f8052f864e9002435ca57b1f12fce1e00a5b02b701d4ee293d49f47023f291d44d7d903a014ee01de533a08498df00d6cfc6bc11e00a5b200b80c38ac7420926a6b3370247075e940640f80d2d94c58cbbfad7420926aeb4dd8f8d7c6ecd201a855ae2724002b4a0722a976ce06feac7410dac10440a95d043c0038ba7420926ae347c05381fb4b07a21d4c005485ff060e0496950e44527117034f02ee2a1d8846320150150681af0f7d7d024e3695baeacbc0d3b0f1af253f9855b595c0c780834a0722299b8dc05f011f281d8826660f80aa762df01f435f2f03e6158c4552b5b6019f213cf59f5b38164dc11e00e5b427f032c293c1c2c2b1484a673bf041c2f1e057158e457d3201506e8b815b80f9a5039194d4e3816f970e42527dbd943049b08aebcc8cf7d156db88fb3f986a73b1d8ff63c5990ddc4635efbfff409226f17f5497006cc4a185582600ed7612d5bdffd60173f2dd8a62b915b0723a08784c85e5ef42d86c44d2f84ea9b0ec3d7117d0463101504ea753fdbc93d32b2e5f6aaad984d9f955aa32c190d46097525df763efda02ec93eb865ac82180f6aab2fbdf618006b20740b91c051c91e175e600a766781da969723c9def099c98e1759480098072c9d935ef308034528eeeff1e870124fdd62ce046aaef7e1c7e1d9ee5cedac7218076cad1fdef3040c3d803a01c4e241c119cd31f657e3da9ce723e953b0cd0102600caa14497fc7370a74b09f276fff7380c208905c006f276fff7aee332dc5fdb3804d03e39bbff1d0668107b0054b5a700bb177a6d27034a659ec61d0690c45729f3f43f48d8f3dca790e9b107a05daadcfb7faaebdf33dc9fa49a5a0c6ca65c0230083ca1f2bb6c1713807629d1fddfbb1c06a839870054a553297fecafc300eab29293f11c06903a2cc5c97ff7447ebf27044e8f3d00ed51b2fbbf77390c2075d041c076e23e3cae06fe33b28c41e0d915df6b9b9800b4478aeeff4d91df7f070e03d4964300aa4a8a75f89f023e9d20168701d44529baffcf8dfcfe25380c20754e8a93ff0e2724a96b23cbf184c0fed903d00e29baffef004e8e2c63108701a44e398af80f8d1f0e2befdd09cafb938aeeb56d4c00da2145f7ff470889c4ad91e5380c50530e01a80a29badc3f3dc1d733e53080ba2445f7ff170909e15991e5380c2075448a93ffc6ebb24f35a4a0c9d903d07ca9baff7b4fed2b22cb1ac461805ab20740a9a538f9ef1cc207d8709f8d2c13e0b404654875b71cd83bb28caf005b87bebe90b1efc7e97a1a0e0348ad9762d9de78ddf50713bfacf02a3c21702af60034df8789afe795a3cafc5082324faee05e25d5448a93ff26dbb8e7bb91650f02c7a6bbdd56320168b6d4ddff3d0e03b49043004a29c5c97f5f252401e37132a034b9e5a4edfeef711840d2a4529cfcf7c449cadf83f8c3853c217072f600345b15ddff3d0e03481a578a93ff6e67eac6f96b91af31083c3efe765bcb04a0b9aaeafeef7118a0651c02502a294efefb2c63bb1e477318401adf72aae9feef711840d2b8529cfc775c1fafb300b833f2753c217062f600345795ddff3d0e03481a21d5c97ffd2ed1fb78e46b0d027f34f3db6d35138066aabafbbfc761801671084029a43af9afdf0f788701a49196536df77fcf8584b93a311c06905a24f736bd9e10581d7b009a2947f77f8fc3009280f427fff5eb9f13bcae27048e6502d03cb9baff7b1c069004c0bb88ff30f8d319bceed1095ef7073378ddb63301689e5447fff62b77c221a986aa3af9af5f9745bef620f0e019be765b9900344fceeeff9e14c300d37d4d25e62440c5a8eae4bf7ea53821d0d5006ab2d984497531d601174cf37bbe18f99a00a72428435221559dfcd7af83497342a076b007a0597277fff7380c207558d527fff5eba2c81806814746c6d0262600cd52a2fbbfc76180867308403355f5c97ffd724f007555a9eeff1e8701a48eaafae4bf7eed01dc1719872704ee600f407394eafeef711840eaa05c27fff5ebeb91b10ce209813d2600cd51b2fbbf4e3168861c02d04ce43af9af5f0e03a86b4a77fff7380c20754cae93fffa95ea84c05d12c6d454f6003443e9eeff1e87011acc1e004dd741c06322cbb806f85182587a3603674596b10bf0d404b14839a4786a4ef1f4be8df8f7de12c26146cacc0440d395fbe4bf7e390ca0aea84bf77f8fc3005247e43ef9af5f9e1098864300f55797eeff1e87011aca1e004dc732e088c8322e06d6248865b4edc0e723cb98033c33412c5295ead2fddfe33080d401ef26fec9632627fff52bc50981dfaf30be26b007a0deeafab45db75e094909953ef9af5f9e1018c704a0deeadad0d63531d1241c0250bf4a9ffcd7af1427049e96a00ca90a75ebfeef7118406ab1d227fff5eb60e24f08ac628e4253d803505f757fcaae6bef84a4087539f9af5f9e1038732600f555f706b6ee098a46710840fda8cbc97ffd724f00b5515dbbff7b1c06905aa82e27fff5cb130267ce1e807a6acad375dd7b29244d43dd4efeeb578a13021f9739e63a3001a8a7a634ac4d49548443009adab3883ff9ef73a43bf9af5f0e03a84deadefddfe33080d422df25fec9e3f8ec517b42e04cd903503f4d7baa6e4a6f85a4491c44fc92baab893f3c68a63ed1477c535d5ddb13c004a07e9ad6a0362d61e92c87003499ba9efcd72f8701d4064de9feef7118406a81ba9efcd7af542704ee9d3bf082ec01a897a63e4d37add742d230cb887f03ff307bd463fd0bf1f7f18aec51976302502f4d6d489b9ab8748a43009a488aaeef145df0b11c06509335adfbbfc76100a9a19a72f25fbf3c21b07ff600d447d39fa29bda7bd119f600683c4d39f9af5f9f4b5046d75603a8bce5c4cf3ff90af9f7e0e8b980b009588ca7e330809455534efeebd7c1784260bfec01a88f0f135f5f2bb3473d528a7b38297bd4524735ede4bf7e7d8ff80fa263b2479d9f09403d34bdfbbf27c530c087b347dd110e0168b4a69dfcd72f2703aa4996d3eceeff9e0b881f067806e51319a9139a76f25fbf3c21b03ff600d4431bbaff7b1c06901aa0a927fff5eb6ce23f88da7e42a00940796de9feef7118a0a61c02d0704d3df9af5f0e03a80996d38eeeff9e0b701840aabda69efcd72f4f089c9a3d00e5b5a9fbbfe723c4df93c30052459a7ef25fbf3e41fc07519bf704300128ab6dddff3d2b89ffbf771820318700d4d3f493fffae53080ea6c39edeafeef391f8701a4da6afac97ffdf284c0c9d90350561bbbff7b1c06906aa82d27fff5cb1302276602504e5bbbff7b1c06906ae8ddc4bf31ff347bd433770cf1f7fbbdec51e76102504edb0fcf697b8223354edb4efeebd7e5c4ddf376e0d0ec5157cf04a09c3677fff7380c50234e0254db4efeeb57ec098103c0b3530422119e8e9f1659c63ac29afb3afb6282324e49508624da77f25fbf0e267ed9e32fb3475d3d7b00ca687bf77f8fc300524da4da18a76e27fff5cb1302c7320128a30bddff3d0e03d4844300ddf61460516419753cf9af5fee09a03ae84af77f8fc300520db4f5e4bf7ea53821f066c207785bd803905f57baff7b1c06900a6bfbc97ffd4a7142e0c9d9a3ae8e09407e5deafeef7118a0061c02e8aeb69ffcd72f87015452d7baff7b1c06900a6afbc97ffdda89f8899077033be70ebc22f600e4d5b5eeff1e8701a4420ea61b27fff5ebbf88ff107e56f6a8ab6102905717bbff7b1c0628cc21806e3a9d6e9cfcd72f8701544257bbff7b1c06900ae8cac97ffd4a7142e0fdc092dc8157c01e807cbadafddfe3304061f60074cf32e088c8322e06d62488a52eb613ff3432179f46343d297e5e523c4597b28db01439c612e0b10962913aa16b27fff5eb91c4d7cb45d9a34ecf1e803c7cfa0d3c2258caa4ab27fff5cb13024d007249d1f035b9fbbfc744a8208700baa5ab27fff5cb1302954bd7bbff7b1c069032e9eac97ffd7a209e10680f40f57cea1dc96100a9625d3ff9af5fdf27fec3e8e8ec51a76302503d1bbc914c880a7108a03bba7ef25fbfdc134055b3fb7fa454c300272488a553dab2939ba6f655e00f22cbd8489804d86603c0ee9165dc021c48f8606b9a6dc43d18cc260ca34c24f629bee99f59b3092748ee1d51c63a603f9a7f0ec7702b09f38b627c1878798258a4564971f29fd7f4aea66ecfea1040b5ecfe1f9fc300053804d00da7117ff29fa6e725a503502dd9fd3f3e8701a48aaca7fc1371d7aeed34f384407b00aae353eee452f48e7c287bd40d660f40fbad04f6281d44070de08791463a91b8b17f802fd3aeb1ffe1ce076e8f2ce399b437414ace04a0fdfeb574001de6d9001aceeeffc96d03be165986c300d36002d07e0f2a1d4087ed44f367ad2b8dd9c01f4696b10eb830412c75e611c1199900b4dfdcd20174dcc34a07a05ab0fbbf3fe7e1304036569254add8cd97d40e299e4a9f0d9c9aa09cba8b9d3cdb1b06382f412cad6602d07edb09dd8f2ae317a503507129baff01764d5046579c8209c0941c0268bf7b4a07d071f7960e40c5a5e8fed7f43c1b1f70a76402d07eab4b07d061bddd17d56def2a1d4007ed4637864ba29800b4dfdad20174d8bad201a8b8dd81a34a07d151ef291d40dd9900b4df4da503e830932fb90f47397be3bc89499900b4df95a503e8b035a50350718f2b1d40c7fd59e900eacc04a0fd4c00ca71fe855c065ad671a503a8336749b65f8a04e0e9c00509ca699245c0d5c42da134f9920f5965790aea244c00daef66e06ec2acd8997a1470569a701a6305f1fb279800e837d80895e47b701266a7ed37087c3fb28c67d3bd9f9567477effaf815fa608448d7663e9003a2ef6b3afd5baf6a1de55e7477eff0380c7a608a42116034f8e2ce37cc22e8ceab69f950ea0e3ee2c1d409d990074c3b909ca785182329ae274e2bb6d63932eb5c35da503e83877429d84094037fc0cd81059c6b3804312c452777381bf4c504e8aa44bcd7773e9003acebd38266102d00ddb806f47963117786d8258eaee39c4273ad7e1124005fe1c94b309138049990074c7a71294f142e0d004e5d4d502e08d09ca4951d76a0767a197b306e7e14cca6580ddf14de036609f883216001f049e9024a2fa793d705882724c00d47335b095b8cfda97015f48134ea37c113829e2fb4dbea461de4b5816187b3d2d77e0191c4658b31d5b373fcc1d7862db88bbffa97a1563ebb789d61077cf9fc81e71790b0813f862eaedcdb98396ea6c196912805b81fd32c75ea5b9c0f74853372fcf1c7b6a2600e97d82b87bbe0bd83977d0859d42fccfcac9d9a3966aee5cd234741710bf535e5dbc933475b20e589839f6d44c00d27b3ef1f7fdacec5197f575e2eaeb3e6097ec514b35b782348ddd20f08eccb157e11984894229eae30d9963af8209407a07127fdfdfcc1e7539fb02f713575f17668f5a6a888b489704fc45e6d8533a8134e3fe83841dc776cf1b7e254c00aa7115f1f77e4cf6a8cb48d123f777d9a3961ae2c9a44b00b61176ce6b9a6584cd9152d5c39bb3465f1d13806abc8ff87bff62f6a8f3db93f8c97f83c0f1b903979ae43ba46bfcb69366f7bc5c1e4b78624f75ffb7d09e73df4d00aa711cf1f7be0d382a77e099bd83f87a72f99f3485a5848932a91ac141e01f81819c373103a7029b497bdfa766bd836a990054e797c4dfffc5b47703b7c349f3defc9bdc814b4df456d2368483849e857d73de449fe6006f27dd84bfde754ece9bc8c004a03a6f24cdcfdc8b73079e498a154adb69f76ea552323b03bf227d12b0967aadc17d087009e9ef7333e1a9a54d4c00aa73106157c0d83ad840fb1ab93f25cd7bd2d9ffd2341c4ffc929b89aeb3091f7aa5ec4c989c97bacbbf77fd49b63bc9c704a05a9f27cdcfde8f80799963afca2349f71e7d6ae6d8a5c67b3dd5349083c0dd84a186bdb2dd4dd846f495c00d09ef63f4f5a56c7793970940b51e41ba61a80f668ebd0a7b01d790a63e7e4efde72049b53300fc37d5359683c046c259040faff03ef627243337577c2f57d39e59ffa39900542f7697bbe1578ad32b4bd919f83ee9eaa24d9371a5ac9650cd7c80f1ae9f00af264d327020e198e26f91667c75aaeb5edabd218b0940f5522c09ec5ddb8133f2869fc47cc2ee86a9ea6135edd99a5c2ae270e076f22401bdeb56e073c05f03cf048e24740bee342cae79c01e84c97c4f26240f1f25fe94b5e95e5b865ebfcd4c00f2483517a09704bc2e6ff851161212f694efcda764bd03a9a58e25cd4e5c29aeada4dfab20e643f60533afd6c63001c8e300c2fc98943fa3efa4fe7b04ec07ac22ed7d7f3deb1d482db782ea66ce37f56ad213560c13807c5e4dfa9fd373a9e73e1c00cb493f3f67137048c67b903ae124d23fa134f1da4e7bf6f9ef8709403e73805f90fe67762d2189af8bb984f7501573749a3c0952aab563c93f27a04ed756e025d1b5d82c2600791d49788aade2e7f76ce001f96e655c27009752cdfd5d4c7bf642906ae921c0f5946f8c735ff7d2fe097fe33101c8ef0caafb39de40784acebd6cf548e00ba4df7abb77ada7ec26635267ec4b9a7dba9b72ada1fda7ae4dc404a08ccf50edcff49d84c3baaa6c3407084ffc67515dc33f3854b6b3fea58c06089bece4586b5ff2fa32b07ba23a6b221380327625cd6981535ddb80f3087b67a4982c3800fc0ef0f7c0b519e21f241c192ca9801554bfd35e896b13f0b284f5d4542600e51c4c98c097f3e7fe32e07dc08b80df05f69c24be598419f74f00fe9cb09741ee39429fa5fe4b1da5565b04fc2bede90d381b9712f59800947504f06bcabe1f7e03dc41d8a3ff97c0754331c5fe6cc45ee7e0a43fa9368e22ed5edeb9afb5c0f392d74ab3990094773ce1ec8cd2ef8f3a5d3f260c9348aa9159c073802b28ff21d1ef752b61639f9d2ba88fa63301a88715c05d947fafd4e1fa01930f4d482a6c166166ee2594ffc098e8ba953091d1867f622600f571247013e5df3725af73f0c95f6a8c01e0f184c93a556d70329d6b1bf06de0b9387ed80f13807a3984fc875ed5e53a93b08ba0a4065a04bc18b880fc13067f4ee8e63fa0ea9b6c191380fad99bf0245cba41ce756d01de40789890d4020b8195c0db09137a52cf28be99b0f3d819b843580c13807a1a005e05dc4ff906baca6b2df0fb89ea4c5330c352298b818712b61a3e7ce83a68e8cf170e5dbdb1fafb09b3a2ef241c557c2bb01ab892d03dba9a3056aa78db885b673d9bb053db44621bf1ae7f662d073e0dec5f388e2afc2f6155cebad281482a6f368edbe7660f40fded4ebbf6e1584fe8b9eb7a72274945990034c732c212b9d20df84caf6dc0278125a92b4692347d2600cd328b30d1f61aca37e8fd5edb81ff068ea9a03e2449336402d04cb3805380cb29dfc04fd6f09f0d1c5b511d489222980034db2ce0e98427ec2d946ff4070993fade0f3cbcc2fb9624453201688f3d0893eb2e227fa3bf99f0b47f0a4ee4ad2d675d4a1ace6580edb41ff018c25e1c4fa09abd32ae05be33749d4358b6ab1af3cd24693813806e389c70d6c0e1c052c27e1c8710f6e1986cfbdd7b095dfa6b08fb70ac1efa7a1561399f1ac43793a4e14c00349fb011d72242f7fd26c2065c77e2304cabf86692349c0980d411316f744992d450260092247590098024491d64022049520799004892d441260092247590098024491d64022049520799004892d441260092247590098024491d64022049520799004892d441260092247590098024491d64022049520799004892d441260092247590098024491d64022049520799004892d4412600922475d09c71fe6c1fe08f8095c0c1c0ce59236a9f7b81eb81ef009f056e2f1b8e2449230d00af07ee0106bd2ab9ee015e3b54d7521d6d23ee677caa5ec5d8f790a4c466019fa67c03d995eb4c4c02544f260052c7bc85f28d62d7ae37f5f53f23e565022075c400f040603530bf6c289d733ff050e0dad28148c36c236e72f06c60fb247f1fdb88db732625320b782936fe25cc035e543a08495237cd029e503a880e7b62e9002449dd340b38a474101d7668e9002449dd3480136b4a734cb3fe76037e0f7814700c7034b098308c0361cc7b2b701f938f7f37c1e2c8efdf50b8fcba9b4518729dc38eb916f703bf0656013f017e087c97b0878854191380f24c00ea6927e059c069c0498cbf699654952dc079c0a780af009bca86a336320128cf04a05e76075e03bc0cd8b3702c12c07ae07dc0bfd1fc1e10d5880940792600f5300b7839614f0c1b7ed5d17ae00dc0c768fe50936ac004a03c1380f20e013e019c50380ea91f1702a70337950e44cd6602509e09405927005f04f62e1d88340d7710e6a79c573a103597c701abcb4e07cec5c65fcdb317f03fc029a50351739900a8ab5e007c1267f7abb9e6039f23ac5691a62dc510c061290269b06b22bfbf6b6701cc0676252cb39b4398cc74377029f005e002e02aaa9de4f478e01bd8f8ab1dee079e44e8cd92fa962201e8fa18b67328d2db08fc8cb031ca4f877ebd82b0d94eac4387ca5b94a02ca92ed603cb801b4b07a2e6300188670290c766422fc1aa61d7a584ddf7fa3587b0c3daa3924727957711f0585c22a83e9900c4330128670ba167a09710fc14f839a107613c7f09fc739ed0a4225e017ca874106a061380782600f5730b614ff5def543c284d735847dfda5b6da001c0eac2b1d88eacf04209e094033dc0bec523a0829837f02de583a08d59f09403c13004975721770f0d0afd284dc074092da6511706ae920547ff600c4b307a05bee003e4bd83ef846e03784c9885dd07b60a86a9679d5e5d7cd5cc27e1807002703cf279c4b91c245c0ef272a4b2d650210cf04a09bee25ac3818be02e172ba930c28bd79c06b092752ce8e2c6b3bb00f4e06d4244c00e29900a8670b6117c3e12b1056019b4a06a5c6792af015e2938053802fc587a3b63201886702a0c96c25ec55d0dbd17015a1e7e09e9241a9f65e07bc23b28c7f015e9d2016b59409403c13004dd776424fc1f0ad8e5711d6704b10e6075c4658d33f531702cb9344a3563201886702a0547ec5d8a4e0b6a211a9a4d700ef8af8fe0dc01e8962510b9900c4330150956e66e444c355c00d4523522e4702bf88f8fe6d78e2a526610210cf0440b9ad63642fc12ac2b1d4fe2cb6cb42e2e78a74fdf35993300188175b7f872589a27966138ee63d00380838067834b067c9a01aec1ec2d3626ff5c1e584d312ef2f1994a2c57ebecca63bfb2a689a4c00e2597f69ed4f48067ad7b184f5cc9abef19625fe9870b4b29ac104409531018867fd556f7452f03042ef81a66f2be15444f72a6886edc47d46980068422600f1acbf3216030f676462f050accf99d8065c491836b88290147c1f585f322801e1ff26e6cc1613004dc804209ef5571f8b0833a78727050fc143af66ea1646f6145c0cdc5e34a2ee310150654c00e2597ff5b610388a306cf070e0b1c011c46fb3da55a39382cb816b8b46d46e2600aa8c09403cebaf79e61276583b015846480e8e269ccca6e9dbc08ea183de75052e4b4cc104409531018867fdb5c31c602923870f8e027629195483dd45d8ca767852f04b6c8ca6cb04409531018867fdb5d76c425270f4d0b56ce85a5432a806db085c0fdc44e821f81a7041c9801ac004409531018867fd75cb0061f3a665ec480c8e0696940caac1b6023f231c7ffb75c29c02ed6002a0ca9800c4b3fe046137c3e149c132c22e879a9eab80b3808f03ab0bc752072600aa8c09403ceb4f13d99db0e2c0bd0aa66f10381ff82061a8606bd9708a310150654c00e2597f9a8ec5ece821e8f5163c18f72a98cc95c0df025fa47b2b0b4c005419138078d69f62f5f62a189e183c0c8f721ded67c05fd0ad89832600aa8c09403c7d59c57c0000161449444154eb4f555840d8d570f844c32381f92583aa8141e0538444a00b5b159b00a8322600f1ac3fe532de5e05bf43e841e89a5b811702df2c1d48c54c005419138078d69f4a9a4dd8d570f40a84dd4b0695c976e01f81b7d0de46ce04a0be76024e044e22ac02da0dd815d817d863e8ef0709c76fdf4ed832fb06c2699cab09735bae25fc1f17610210cffa531d8d3e42f991840fa636fa16702a7077e9402a600250d600a1713f7ce87ae8d0af47017b25287f03702161c5cbb964de07c304209ef5a7a67800237b098e1efab33658053c91f69d56680290c76e84867d29e104d1c387fd3ee71921ab813309f35c6ea8fac54c00e2597f6ab2bd19bbabe1a145239ab935c0e308db0db78509403ab38143080d7baf915f3a74ed5730aef16c07ce03de099c53d58b9800c4b3fed436bb33764ec1e134e308e56b80c7102609b68109c0f4edc98e467e293b1afa0701f30ac6355397006f05ce26f13e182600f1ac3f75c12e8471cfe189c1c308472bd7cd4f81e5b4634e8009c0f8e611cee4e83dc10f6ff0f72c1857952e025e015c9aaa40138078d69fba6a3ee3ef55b0a0645043ce051e4fc119d689743d01584c18927a3821e13c74d8efebf07396db56e07dc09b817b620b33018867fd493bcc217c50f7860e8e018ea54cd7ebdb80bf2ef0ba29752101d8891d93ee4677dd7bf4f6f8ae074e037e18538809403ceb4f9adc2ce011c0e984f1f98309930fab9e5330083c15f846c5af53a53625008b19f924dffbfa609a31bfa46eee035e03bc7fa6059800c4b3fea4e95b4858b6f72c42235dd55c82758446e68e8acaaf5ad31280f984c976a31bf9a57473c7ca1c3e43d815f3fee97ea309403ceb4f8ab33ff067c09f10261ba6f61942ef4313d53101980d3c90f167daef9ff8b5d49ff380a731cd89af2600f1ac3f298d7d09e3f6cf27fdfbe2c9c0ff242e33879209c0ee8499f6a327e13d14d839222655e3c7c09398466f9709403ceb4f4a6b25f05fa47d9a5c4368c4b6262c3387aa1380b9c0818c3fd3bea91b4275d92ac2f9047df5049800c4b3fea4f4f6013e0f3c3661997f0c7c34617939a44a00f661e416b7bdaefb43082b37d41ee7127abceeebe71f0f465e5d67fd49d59847480262df63bd6b2d79f7754f611b71f77c31e1c0995475e8d58cebf3f4f97019fb425d67fd49d5994dda24e02579c38f169b007875f77a35537008209ef527556b1ef0bfc08a0465fd8cb04151dded4ae8aaff117e46d4d956e0cea1eb36c236d4df066e26fcbfcd1efa377b12e6543c0478346183acaaf73ed8029cc0249b059900c4b3fea4eaed01fc84b0fc2cd66380ef25282785fd19bbc5edc308f71933f6afb43600d7025700970f7d7dedd0d79b6750de6e8419fbcf259c6059d53c8ceb0909ef86f1fed204209ef527e5f1fbc005c4378c1f075e141d4dff265a4ef710aad9f74033733f619ec8e846fe52c2d37d550e2274d7bf846a96577e1878f9787f610210cffa93f2f900e144b418eb097b0ea45c123887f041ee72bafadbc0d846fe0a6035650f8fda1f783ba15720a5edc0ef31ce508009403ceb4fca6777e01ac290408c9308bba74d97a7d335c37d849f93d18dfccf49708a5ec5fe00f84fe27fc687fb09703ca3121c138078d69f94d71b807f8a2ce37dc0ab26f8bb79c00318dbc81f49584faffab885b18dfce5c075d4e710a4993808f832f0c884653e0f3873f81f9800c4b3fea4bc16013711377ebe8ab0e3e0784ff24bf174ba3ab993f0343fba915f0ddc5b30aeaaed027c097842a2f256137ebe7f9b189900c4b3fea4fc3e029c513a0825b315b881b18dfcb5c0af88ff9c6daa79c0d74897049c42482a00138014ac3f29bf1399d918becaba83f0247a25e17c862b877eff2bc2ba758db50b617bdfe31394b58ab0070160029082f527e53707b895b0c18aeae53ee02ac636f26b98603dbaa6b40f6113ab7d1394b56ca82c0f8190d4485b81ef027f583a900e5bcb8e06be77ad216c3e5372395d1bdd06bc9070a475ec43e373194a00ec018867fd4965bc0e7847e9205aee5e463ec90f6fe837168cabab3e49fc3e01b71156b96c35018867fd4965fc3fe0ecd241b4c076c253fbe8eefa35c08d05e3d258fb10fe8f164596b30238df2100494d754de9001ae62ee06abab79cae4d6e23ec86f9d791e5ac00ceb707209ef52795b18430ab5c3b0c1266d45fced84978b7178c4be92c21f4d8c49c1bf003e07721fecce1aeb3fea4321650feccf53a5c5b0993ba5e455837aef6fb24713f335b80ddec018867fd4965cc259ce0d6565b0863f0a337c6b90cf835b0dfd0bfbb8576d783c65a099c1359c672e700486aaadd4a0790c84d8c3fd37eaae574d7571f9a6aea7cc216c9bb4794b1d404405253c5ce84cee95e76ccac1f3dd3beeea7d3a97eb611f6c1784a44192600921aebb0d2018ca3ada7d3a97ebe870980a48e5a5ae875ef62eca638bd5f37178a49ddf3cbc8ef3fc0044052531d5761d95b09cbe94637f4ab096bb1a5d2ae8afcfe5d4d002435d58909ca58c7d831f9d584ee7b67d6abced6477effaee03af658d69f94df23887fef9d9a3d6a299d9d88fbf9ffcdacfc314b52b4d8035120cca2969a2a7652e91cf0093696f527e5351fb899b8f7dd15d9a396d2da97b8f7c03df600486a9a17b06317bc993a37411c52498b23bf7f930980a426990fbc3e4139e72728432a69ffc8ef5f670220a9495e071c1259c626e2f751974a3b26f2fb6f320190d4140f06de90a09cb370fb5d35dfd191df6f0220a911e6039f232c7d8a75668232a49206804747967105388b3d96f52755ef63c4bfd70681b5c0ecccb14ba93d9af8f7c213dd09504db317f04260397028e1c37c3d6147b7f5c3bebe63d89f0dff75b2e355554fff00bc285159efc19f0135dfb31294f1d301e29f42071204d264d65f1eb38057037f0f2c882867032139189d34dc31eccf47270df6d494f377c09b1395b50e7820e1685ea9a91610cea9d837a28c35781aa01a6216f05fc0731294b598e9af9fdd4c481c6e261cf7ba61d435facf6fc3a7cc5873817f035e9ab0ccf762e3afe67b09718d3fc0ff4178faf409368ef557bd37139e049b621b638724460f4b8cfef3bb8b445a4f0f244cf83b3e6199b7000fc17a56b3cd259c02787064397f087ccd04209ef557ad4309e75ecf2b1d48c5b63072d8a197188c37bfe1f6a1af371689b43ab3084f37ef02764b5cf6b381cf262e53caedaf80b74596f11b6009b0c92100d5dd19b4bff18790d9efc7f4b6b8ddccf889c1e8390cc37b1e36a50b399959c03380bf058ea8a0fcf3b0f157f33d94343da16733f4396002a0ba3bb9740035b6003860e8ea576f3ec354f3187ad75ae0ae74218f7000703a6186ffd28a5e6313f0ca8aca967299077c92b809d03d9fe87de110403cebaf5af7000b4b07d1711b09bd07bd1e86d1cb2b7b3d0c1b0863ec6b09431ac3cd030e238cc33f0a5841d8c9aceacdc85e0cfc67c5af21556980d0683f2f4159371086557f3b49d98d6ce2587fd5da489a0d60bcf25edb091f325b86ae12317c12a9f9de42baf7c45f8c2e3cb6c0aeb3feaab586f28d9957f3ae5f00bb2035db9b08c9748af7c40660d7e1857b1680eaeebba50350e3dc003c11d7fcabb966031f226c7c966a98f8dd8c7308566c56d175d65fb58ea5fcd3a45773ae7584790652531d025c40daf7c5ad4cd023165b70d7597fd5fb1ce51b16affa5feb81e3909a6936f0efc056d2bf37263c4b23b6e0aeb3feaab718f829e51b18affa5e37038f406aa6d3082b68aa786f5cc824c308b185779df597c762e06b946f68bcea775d0e1c88d43cc713e63955f5deb8972986c4625fa0ebacbfbc9e087c9530a3b574c3e355fe3a0bd81da9590e07be40ba19fe135d2f9b2c0837028a67fd95b33f614feb25c0dec09e43d79261bfee35ecf73b97095315d84c381efa83a50391a6613fc276be2fa6fa9d78bf023c9349da28138078d65f732c00f660c791c0bd6b3f423231facf0f001615895493b998704cf0cf4b0722f56911f05ae0cfc9b33fc565c0a399e2c030138078d65fbb2d24f41ef47a127abd09e3f530f47eedc2e145256c20ec8af67e866d652ad5d83ce00584f5fcfb647acddb80df05ae9dea1f9a00c4b3fe34da6e8c9f18f41289bd183b5ce1c15c13db087c04783b619dbf5477b308075dfd037070c6d7bd13584e9fbd632600f1ac3fa5b098913d0c13cd6318fe776ddfc9f32ec2d3fe7bb1e157733c09781bf997a5de35f4dadfeff71b4c00e2597f2a6180b1bd0bbd0462af09fe6ecf22914ecf36e05ce04cc20c7fb7f355531c0bbc0338b1c06bff9ab042ea92e97c9309403ceb4f4d319bf11383c95650e49804f96bc2d6a7e7129678de9ce135a55496026f059e4e99cff32b81a700574df71b4d00e2597f6ab3b98c4d1af61ef5fbc309678c2f1afaf713fd4cdf4b68dcaf0456134e7afc09f033c27a68a949722ee99bc8b708bb08de39d30262371ae83aeb4f1a6b17e088a16bb7c2b148292d223cf16fa4dc0658db803793601e900d581ceb4f92da6f1e7006e164bd520dff20e1d0ab6353dd940d581ceb4f92da6b16f05ce03aca36fcdb812f1186d992b1018b63fd49523bad045651b6e1df4638d1efd02a6ed0062c8ef52749ed722c701ee59ff8bf003ca8ca1bb5018b63fd49523be43aa56faaeb1ce0e88aef151204da75d69f2435db5ec0bf025b28dbf0ff0838a9e27b1dc1062c8ef52749cdb410783d7037651bfeeb082b0cb26fef6d0316c7fa93a466a9cb92be3b0809c8fc6a6f7762366071ac3f496a8659c029c035946df837124eb72cbe49960d581ceb4f92eaaf0e4bfaee271c6dbd6fc5f7da371bb038d69f24d5576796f4cd840d581ceb4f92ea67291d5bd23713366071ac3f49aa8fce2ee99b091bb038d69f2495d7f9257d33610316c7fa93a4727a4bfa6ea36cc35f7c49df4cd880c5b1fe24293f97f425600316c7fa93a4bc56023fa56cc35fbb257d33610316c7fa93a43c8ea33e4bfa0eabf85eb3b0018b63fd4952b55cd257111bb038d69f2455c3257d15b3018b63fd49525a2ee9cbc4062c8ef5274969b8a42f331bb038d69f24c571495f21366071ac3f499ab93a2de9dba7e27bad1d1bb038d69f244d9f4bfa6ac0062c8ef52749fd73495f8dd880c5b1fe24696a2ee9ab211bb038d69f244dcc257d35660316c7fa93a4b15cd2d700366071ac3f49dac1257d0d620316c7fa93a4c0257d0d630316c7fa93d4752ee96b281bb038d69fa4ae72495fc3d980c5b1fe24758d4bfa5ac2062c8ef527a92b5cd2d732366071ac3f496de792be96b2018b63fd496a2b97f4b59c0d581ceb4f521bb9a4af036cc0e2587f92dac4257d1d620316c7fa93d4062ee9eb201bb038d69fa426abd392be1515dfab46b1018b63fd496aa23a2de97b1e3050e9dd6a5c366071ac3f494de2923efd960d581ceb4f5213b8a44f63d880c5b1fe24d59d4bfa342e1bb038d69fa4ba72499f26650316c7fa9354372ee9535f6cc0e2587f92eac2257d9a161bb038d69fa4d25cd2a719b1018b63fd492ac5257d8a620316c7fa93945b5d96f4dd834bfa1acd062c8ef527292797f429191bb038d69fa41c5cd2a7e46cc0e2587f92aae4923e55c6062c8ef527a90afb13bad94b2fe9bb0497f4b5960d581ceb4f524a2ee95336366071ac3f4929b8a44fd9d980c5b1fe24c570499f8ab1018b63fd499a2997f4a9281bb038d69fa4e972499f6ac1062c8ef527a95f2ee953add880c5b1fe244dc5257daa251bb038d69fa489b8a44fb5660316c7fa93349a4bfad408366071ac3f493d2ee953a3d880c5b1fe24814bfad440366071ac3fa9db5cd2a7c6b2018b63fd49dde4923e359e0d581ceb4fea960370499f5ac2062c8ef52775435d96f4fd0a97f429111bb038d69fd46e2ee9536b35ad015b08bc18f812b00ab8823001e7ef81871688a769f527a93f2ee953eb35a9013b0db8659258b6011f0576c9185393ea4f527f5cd2a74e684a03f606fa9f6dfb536071a6b89a527f92a6761c703e651b7e97f4299b2634604f67fa4b6dbe499e49324da83f4993abd392be6515dfabf45b756fc01600d7cf30b6676688afeef52769622ee953a7d5bd013b3522b60b33c457f7fa9334964bfa24eadf807d2c22b6ad543f73b6eef527690797f449c3d4bd018bdd63fb888ae3ab7bfd4972499f34aeba3760ff17195fd57b64d7bdfea4ae73499f348e39a503e8c3cd91dfbf364914d5b994300e78edd0afc3bfbeb7605c52d31d07bc03585e308641c2a6656f20f43e48b552f727d89746c4765986f8aa7c6ab80df821f019e0adc04b809380436846f22695e0923ea94f754f00960077cd30b6d76688afe407ccaf811f133eecde4e98dcb41238146715ab7b5cd2274d53dd1300080df974e3ba9ab08740d54a7ed04c766d240c2f7c1d782ff02ae0a984499139b74a96aae6923e69869a90000c009f9f464c7752fdecff9ed20dfd4c2f8717d4742ee9932235210100980dbc8b70e0cf64f15c41de53014b37e4555d0e2fa8ae5cd22725d29404a0e708c2e640c3b3fe2d845dffce00e6668ea774435de2727841a5b8a44f4a6480f846bce4d3e04242f67d1ba167a084124950ddddced8a58dbddfdf48d8a1519a0e97f44989353d01a8031380e9db404806c6bb7e8575aa1d9602ff4038d8abe467cd7780d7117a1fa456300188676395d6bd8cdd1069f8d76e8ed40d07007f0bbc88b293527f04fc15614b72a9554c00e29900e4e5f042bb2d045e09bc11d8b5601cd7017f079c89ef71b59409403c3f1ceac5e185669a07bc80d0ddbf77c138d601ef06de4398ec27b59609403c1b94e6b80fb889f19383ab081bc928af59c0330813fc0e2918c746e003c03fe1cf813ac204209e09407b38bc90d74ac2de1e47158c610bf071c27c83db0ac62165670210cf04a01bb6129280891284dbcb85d6382ee9936a204502706d8a401aecd0d201a8165cbd303597f44935922201509c930949c4f0eb41c0a2924129b9c92627de40bb87175cd227d590094079133d092d666c62b03fb01ff03060a72cd1299736ae5ea8cb92be35c0df10bafc9b588f52254c00ca9b6957e8780942ef7a206176b5daa169ab175cd2273580094079558c85ce2774bb8e971c38bcd03e75195e70499fd4202600e595980ce5f042b7e4185e70499fd4302600e5d57119a5c30bdd113bbce0923ea9a14c00caab630230198717ba65a2de03809712bafc5dd22735d000617df2cea503e9a84dc02ea583486c2fc2f86fef3a74d8d7070173cb85a61671499f146980b044e6c1a503e9a83584cd51ba62367020231384e149c2bee5425343ac212c2bfc32f65e4a51e600176002504ad79e5eb6118e59bd0e387f9cbfdf89913d06c3bf3e84b26bc955d61dc03fe3923e299901e0f7808b4a07d251bf0bfca074100de2f042f7dc0dbc13782f6ea72c25d59bbcf325c2641ee5f379e0b4d241b488c30bed721ff021e0ad840d7d2425d64b001601df031e5e30962eb98cd0f3e22625f9b87aa119b6039f01de44182a925491e1cb7716039f029e542896aef806f05ce0ced281680437472acf257d5246e3addf7d12f0c7c0e37079602af702e700ff0efc6fe158347d0e2f54eb12c292bef126864aaac8541b782c0176cb11488bdd8d63986de7f0c2ccb8a44f2aa869bbd0494de4ea85915cd227d580098054569786175cd227d5880980546f6dd81cc9257d520d990048cd56e7d50b2ee9936acc04406aaf92c30bdf015e03fcbcc2d79014c10440eaae2a8617ee045e087c35518c922a6202206922e3ad5e38867082e5f063ac07815b810f12c6f95dd22735800980a499980d1c36f4f59a92814892244992244992244992244992244992244992244992244992244992244992244935f4ff01ab633e53b78a3a3e0000000049454e44ae426082	image/png	3052	3101	admin	2020-09-17 12:14:19.457	admin	2020-09-17 12:14:19.457
\.


--
-- Data for Name: procedures; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.procedures (id, name, indicator_id, created_by, created_date, last_modified_by, last_modified_date) FROM stdin;
1501	Review minutes and annual meetings timetable	1451	admin	2020-09-17 07:55:09.241984	admin	2020-09-17 07:55:09.241984
1506	Review minutes	1453	admin	2020-09-17 08:06:11.707313	admin	2020-09-17 08:06:11.707313
1502	Review minutes	1452	admin	2020-09-17 08:01:50.845483	admin	2020-09-17 08:30:24.073391
1652	Review minutes	1455	admin	2020-09-17 08:31:02.457329	admin	2020-09-17 08:31:02.457329
1653	Review Standing Committees formation proceedings if it comply with Standing Committees Directives 	1456	admin	2020-09-17 08:31:23.066575	admin	2020-09-17 08:31:23.066575
1651	Interview Council Director	1454	admin	2020-09-17 08:30:44.502259	admin	2020-09-17 08:32:37.746589
1654	Interview Council Director	1457	admin	2020-09-17 08:32:20.881767	admin	2020-09-17 08:32:59.454422
\.


--
-- Data for Name: quarters; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.quarters (id, code, name, start_date, end_date, financial_year_id, created_by, created_date, last_modified_by, last_modified_date) FROM stdin;
\.


--
-- Data for Name: response_attachments; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.response_attachments (id, name, attachment_id, response_id, created_by, created_date, last_modified_by, last_modified_date) FROM stdin;
\.


--
-- Data for Name: risk_categories; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.risk_categories (id, code, name, created_by, created_date, last_modified_by, last_modified_date) FROM stdin;
1951	001	Operational Risks	admin	2020-09-17 08:55:04.979248	admin	2020-09-17 08:55:04.979248
1952	002	Compliance Risks	admin	2020-09-17 08:55:16.719808	admin	2020-09-17 08:55:16.719808
1953	003	Strategic Risks	admin	2020-09-17 08:55:26.715645	admin	2020-09-17 08:55:26.715645
1954	004	Financial risks	admin	2020-09-17 08:55:37.850526	admin	2020-09-17 08:55:37.850526
\.


--
-- Data for Name: risk_ranks; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.risk_ranks (id, name, min_value, max_value, hex_color, created_by, created_date, last_modified_by, last_modified_date) FROM stdin;
1851	Low(L)	1	4	#00ff00	admin	2020-09-17 08:46:52.807669	admin	2020-09-17 08:46:52.807669
1852	Moderate(M)	5	9	#ffff00	admin	2020-09-17 08:47:48.459659	admin	2020-09-17 08:47:48.459659
1853	High(H)	10	14	#ff9933	admin	2020-09-17 08:48:26.287914	admin	2020-09-17 08:48:26.287914
1854	Extreme or severe (E	15	25	#ff0000	admin	2020-09-17 08:49:28.66491	admin	2020-09-17 08:49:28.66491
\.


--
-- Data for Name: risk_ratings; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.risk_ratings (id, source, impact, likelihood, comments, risk_id, created_by, created_date, last_modified_by, last_modified_date) FROM stdin;
\.


--
-- Data for Name: risk_registers; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.risk_registers (id, name, is_approved, approved_date, approved_by, organisation_unit_id, financial_year_id, created_by, created_date, last_modified_by, last_modified_date) FROM stdin;
2601	DIFT PORALG - 2019/2020	f	\N	\N	1	2501	admin	2020-09-17 09:22:38.434	admin	2020-09-17 09:22:38.434
2602	DIFT PORALG - 2020/2021	f	\N	\N	1	2502	admin	2020-09-17 09:23:14.892	admin	2020-09-17 09:23:14.892
\.


--
-- Data for Name: risks; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.risks (id, code, description, risk_register_id, objective_id, risk_category_id, created_by, created_date, last_modified_by, last_modified_date) FROM stdin;
\.


--
-- Data for Name: sub_areas; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.sub_areas (id, name, area_id, created_by, created_date, last_modified_by, last_modified_date) FROM stdin;
1401	LGA Meetings and proceedings	1351	admin	2020-09-17 07:27:30.137441	admin	2020-09-17 07:27:30.137441
1402	Standing committees	1351	admin	2020-09-17 07:27:43.8575	admin	2020-09-17 07:27:43.8575
1403	Organization	1351	admin	2020-09-17 07:28:00.371589	admin	2020-09-17 07:28:00.371589
1404	Meetings of lower level LGAs	1351	admin	2020-09-17 07:28:12.089998	admin	2020-09-17 07:28:12.089998
1405	Adoption of by- laws/ legislative processes	1351	admin	2020-09-17 07:28:24.010823	admin	2020-09-17 07:28:24.010823
1406	Records Management	1351	admin	2020-09-17 07:28:34.52778	admin	2020-09-17 07:28:34.52778
1407	Revenue collection	1352	admin	2020-09-17 07:29:08.379215	admin	2020-09-17 07:29:08.379215
1408	Revenue in arrears	1352	admin	2020-09-17 07:29:24.392976	admin	2020-09-17 07:29:24.392976
1409	Borrowing	1352	admin	2020-09-17 07:29:32.512742	admin	2020-09-17 07:29:32.512742
1410	Investments	1352	admin	2020-09-17 07:29:42.802667	admin	2020-09-17 07:29:42.802667
1411	Public Private Partnership 	1352	admin	2020-09-17 07:29:51.698643	admin	2020-09-17 07:29:51.698643
1412	Fiscal Capacity	1352	admin	2020-09-17 07:30:02.190206	admin	2020-09-17 07:30:02.190206
1413	Transfer to lower  level LGAs	1352	admin	2020-09-17 07:30:11.703872	admin	2020-09-17 07:30:11.703872
1414	Custody of funds, cheque books  and cash equivalents	1352	admin	2020-09-17 07:30:21.433165	admin	2020-09-17 07:30:21.433165
1415	Stores records	1352	admin	2020-09-17 07:30:31.820425	admin	2020-09-17 07:30:31.820425
1416	Custody of stores	1352	admin	2020-09-17 07:30:41.343535	admin	2020-09-17 07:30:41.343535
1417	Insurance of assets	1352	admin	2020-09-17 07:30:52.745438	admin	2020-09-17 07:30:52.745438
1418	Vehicles	1352	admin	2020-09-17 07:31:01.628097	admin	2020-09-17 07:31:01.628097
1419	Liquidation/ write-off of assets	1352	admin	2020-09-17 07:31:12.465629	admin	2020-09-17 07:31:12.465629
\.


--
-- Data for Name: team_meetings; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.team_meetings (id, type, meeting_date, venue, summary, inspection_id, created_by, created_date, last_modified_by, last_modified_date) FROM stdin;
\.


--
-- Data for Name: user_authorities; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.user_authorities (user_id, authority_name) FROM stdin;
1	ROLE_ADMIN
1	ROLE_USER
3	ROLE_ADMIN
3	ROLE_USER
4	ROLE_USER
\.


--
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.users (id, login, password_hash, first_name, last_name, email, image_url, activated, lang_key, activation_key, reset_key, created_by, created_date, reset_date, last_modified_by, last_modified_date, organisation_unit_id) FROM stdin;
2	anonymoususer	$2a$10$j8S5d7Sr7.8VTOYNviDPOeWX8KcYILUVJBsYV83Y5NtECayypx9lO	Anonymous	User	anonymous@localhost		t	en	\N	\N	system	\N	\N	system	\N	1
3	admin	$2a$10$gSAhZrxMllrbgj/kkK9UceBPpChGWJA7SYIb1Mqo.n5aNLq1/oRrC	Administrator	Administrator	admin@localhost		t	en	\N	\N	system	\N	\N	system	\N	1
1	system	$2a$10$mE.qmcV0mFU5NcKh73TZx.z4ueI/.bDWbj0T1BYyqP481kGGarKLG	System	System	system@localhost		t	en	\N	\N	system	\N	\N	system	\N	1
4	user	$2a$10$VEjxo0jq2YG9Rbk2HmX9S.k1uZBGYUHdUcid3g/vfiEl7lwWgOH/K	User	User	user@localhost		t	en	\N	\N	system	\N	\N	system	\N	1
\.


--
-- Name: sequence_generator; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.sequence_generator', 3550, true);


--
-- Name: auditable_areas auditable_areas_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.auditable_areas
    ADD CONSTRAINT auditable_areas_pkey PRIMARY KEY (id);


--
-- Name: authorities authorities_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.authorities
    ADD CONSTRAINT authorities_pkey PRIMARY KEY (name);


--
-- Name: cluster_reports cluster_reports_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cluster_reports
    ADD CONSTRAINT cluster_reports_pkey PRIMARY KEY (id);


--
-- Name: clusters clusters_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.clusters
    ADD CONSTRAINT clusters_pkey PRIMARY KEY (id);


--
-- Name: databasechangeloglock databasechangeloglock_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.databasechangeloglock
    ADD CONSTRAINT databasechangeloglock_pkey PRIMARY KEY (id);


--
-- Name: file_resources file_resources_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.file_resources
    ADD CONSTRAINT file_resources_pkey PRIMARY KEY (id);


--
-- Name: financial_years financial_years_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.financial_years
    ADD CONSTRAINT financial_years_pkey PRIMARY KEY (id);


--
-- Name: finding_categories finding_categories_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.finding_categories
    ADD CONSTRAINT finding_categories_pkey PRIMARY KEY (id);


--
-- Name: finding_recommendations finding_recommendations_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.finding_recommendations
    ADD CONSTRAINT finding_recommendations_pkey PRIMARY KEY (id);


--
-- Name: finding_responses finding_responses_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.finding_responses
    ADD CONSTRAINT finding_responses_pkey PRIMARY KEY (id);


--
-- Name: finding_sub_categories finding_sub_categories_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.finding_sub_categories
    ADD CONSTRAINT finding_sub_categories_pkey PRIMARY KEY (id);


--
-- Name: findings findings_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.findings
    ADD CONSTRAINT findings_pkey PRIMARY KEY (id);


--
-- Name: gfs_codes gfs_codes_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.gfs_codes
    ADD CONSTRAINT gfs_codes_pkey PRIMARY KEY (id);


--
-- Name: iftmis_entity_audit_event iftmis_entity_audit_event_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.iftmis_entity_audit_event
    ADD CONSTRAINT iftmis_entity_audit_event_pkey PRIMARY KEY (id);


--
-- Name: iftmis_persistent_audit_event iftmis_persistent_audit_event_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.iftmis_persistent_audit_event
    ADD CONSTRAINT iftmis_persistent_audit_event_pkey PRIMARY KEY (event_id);


--
-- Name: iftmis_persistent_audit_evt_data iftmis_persistent_audit_evt_data_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.iftmis_persistent_audit_evt_data
    ADD CONSTRAINT iftmis_persistent_audit_evt_data_pkey PRIMARY KEY (event_id, name);


--
-- Name: iftmis_persistent_token iftmis_persistent_token_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.iftmis_persistent_token
    ADD CONSTRAINT iftmis_persistent_token_pkey PRIMARY KEY (series);


--
-- Name: indicators indicators_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.indicators
    ADD CONSTRAINT indicators_pkey PRIMARY KEY (id);


--
-- Name: inspection_activities_organisation_units inspection_activities_organisation_units_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.inspection_activities_organisation_units
    ADD CONSTRAINT inspection_activities_organisation_units_pkey PRIMARY KEY (inspection_activity_id, organisation_units_id);


--
-- Name: inspection_activities inspection_activities_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.inspection_activities
    ADD CONSTRAINT inspection_activities_pkey PRIMARY KEY (id);


--
-- Name: inspection_activities_risks inspection_activities_risks_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.inspection_activities_risks
    ADD CONSTRAINT inspection_activities_risks_pkey PRIMARY KEY (inspection_activity_id, risk_id);


--
-- Name: inspection_activity_quarters inspection_activity_quarters_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.inspection_activity_quarters
    ADD CONSTRAINT inspection_activity_quarters_pkey PRIMARY KEY (id);


--
-- Name: inspection_areas inspection_areas_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.inspection_areas
    ADD CONSTRAINT inspection_areas_pkey PRIMARY KEY (id);


--
-- Name: inspection_budget inspection_budget_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.inspection_budget
    ADD CONSTRAINT inspection_budget_pkey PRIMARY KEY (id);


--
-- Name: inspection_findings inspection_findings_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.inspection_findings
    ADD CONSTRAINT inspection_findings_pkey PRIMARY KEY (id);


--
-- Name: inspection_indicators inspection_indicators_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.inspection_indicators
    ADD CONSTRAINT inspection_indicators_pkey PRIMARY KEY (id);


--
-- Name: inspection_members inspection_members_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.inspection_members
    ADD CONSTRAINT inspection_members_pkey PRIMARY KEY (id);


--
-- Name: inspection_plans inspection_plans_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.inspection_plans
    ADD CONSTRAINT inspection_plans_pkey PRIMARY KEY (id);


--
-- Name: inspection_procedures inspection_procedures_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.inspection_procedures
    ADD CONSTRAINT inspection_procedures_pkey PRIMARY KEY (id);


--
-- Name: inspection_recommendations inspection_recommendations_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.inspection_recommendations
    ADD CONSTRAINT inspection_recommendations_pkey PRIMARY KEY (id);


--
-- Name: inspection_sub_areas inspection_sub_areas_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.inspection_sub_areas
    ADD CONSTRAINT inspection_sub_areas_pkey PRIMARY KEY (id);


--
-- Name: inspection_work_dones inspection_work_dones_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.inspection_work_dones
    ADD CONSTRAINT inspection_work_dones_pkey PRIMARY KEY (id);


--
-- Name: inspections inspections_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.inspections
    ADD CONSTRAINT inspections_pkey PRIMARY KEY (id);


--
-- Name: meeting_agendas meeting_agendas_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.meeting_agendas
    ADD CONSTRAINT meeting_agendas_pkey PRIMARY KEY (id);


--
-- Name: meeting_attachments meeting_attachments_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.meeting_attachments
    ADD CONSTRAINT meeting_attachments_pkey PRIMARY KEY (id);


--
-- Name: meeting_members meeting_members_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.meeting_members
    ADD CONSTRAINT meeting_members_pkey PRIMARY KEY (id);


--
-- Name: notifications notifications_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.notifications
    ADD CONSTRAINT notifications_pkey PRIMARY KEY (id);


--
-- Name: objectives objectives_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.objectives
    ADD CONSTRAINT objectives_pkey PRIMARY KEY (id);


--
-- Name: organisation_unit_levels organisation_unit_levels_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.organisation_unit_levels
    ADD CONSTRAINT organisation_unit_levels_pkey PRIMARY KEY (id);


--
-- Name: organisation_units organisation_units_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.organisation_units
    ADD CONSTRAINT organisation_units_pkey PRIMARY KEY (id);


--
-- Name: procedures procedures_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.procedures
    ADD CONSTRAINT procedures_pkey PRIMARY KEY (id);


--
-- Name: quarters quarters_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.quarters
    ADD CONSTRAINT quarters_pkey PRIMARY KEY (id);


--
-- Name: response_attachments response_attachments_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.response_attachments
    ADD CONSTRAINT response_attachments_pkey PRIMARY KEY (id);


--
-- Name: risk_categories risk_categories_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.risk_categories
    ADD CONSTRAINT risk_categories_pkey PRIMARY KEY (id);


--
-- Name: risk_ranks risk_ranks_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.risk_ranks
    ADD CONSTRAINT risk_ranks_pkey PRIMARY KEY (id);


--
-- Name: risk_ratings risk_ratings_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.risk_ratings
    ADD CONSTRAINT risk_ratings_pkey PRIMARY KEY (id);


--
-- Name: risk_registers risk_registers_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.risk_registers
    ADD CONSTRAINT risk_registers_pkey PRIMARY KEY (id);


--
-- Name: risks risks_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.risks
    ADD CONSTRAINT risks_pkey PRIMARY KEY (id);


--
-- Name: sub_areas sub_areas_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.sub_areas
    ADD CONSTRAINT sub_areas_pkey PRIMARY KEY (id);


--
-- Name: team_meetings team_meetings_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.team_meetings
    ADD CONSTRAINT team_meetings_pkey PRIMARY KEY (id);


--
-- Name: user_authorities user_authorities_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_authorities
    ADD CONSTRAINT user_authorities_pkey PRIMARY KEY (user_id, authority_name);


--
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);


--
-- Name: auditable_areas ux_auditable_areas_code; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.auditable_areas
    ADD CONSTRAINT ux_auditable_areas_code UNIQUE (code);


--
-- Name: auditable_areas ux_auditable_areas_name; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.auditable_areas
    ADD CONSTRAINT ux_auditable_areas_name UNIQUE (name);


--
-- Name: cluster_reports ux_cluster_reports_code; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cluster_reports
    ADD CONSTRAINT ux_cluster_reports_code UNIQUE (code);


--
-- Name: clusters ux_clusters_code; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.clusters
    ADD CONSTRAINT ux_clusters_code UNIQUE (code);


--
-- Name: financial_years ux_financial_years_name; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.financial_years
    ADD CONSTRAINT ux_financial_years_name UNIQUE (name);


--
-- Name: finding_categories ux_finding_categories_code; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.finding_categories
    ADD CONSTRAINT ux_finding_categories_code UNIQUE (code);


--
-- Name: finding_categories ux_finding_categories_name; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.finding_categories
    ADD CONSTRAINT ux_finding_categories_name UNIQUE (name);


--
-- Name: finding_sub_categories ux_finding_sub_categories_code; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.finding_sub_categories
    ADD CONSTRAINT ux_finding_sub_categories_code UNIQUE (code);


--
-- Name: finding_sub_categories ux_finding_sub_categories_name; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.finding_sub_categories
    ADD CONSTRAINT ux_finding_sub_categories_name UNIQUE (name);


--
-- Name: gfs_codes ux_gfs_codes_code; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.gfs_codes
    ADD CONSTRAINT ux_gfs_codes_code UNIQUE (code);


--
-- Name: indicators ux_indicators_name; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.indicators
    ADD CONSTRAINT ux_indicators_name UNIQUE (name);


--
-- Name: inspections ux_inspections_name; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.inspections
    ADD CONSTRAINT ux_inspections_name UNIQUE (name);


--
-- Name: meeting_attachments ux_meeting_attachments_name; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.meeting_attachments
    ADD CONSTRAINT ux_meeting_attachments_name UNIQUE (name);


--
-- Name: objectives ux_objectives_code; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.objectives
    ADD CONSTRAINT ux_objectives_code UNIQUE (code);


--
-- Name: organisation_unit_levels ux_organisation_unit_levels_code; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.organisation_unit_levels
    ADD CONSTRAINT ux_organisation_unit_levels_code UNIQUE (code);


--
-- Name: organisation_unit_levels ux_organisation_unit_levels_name; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.organisation_unit_levels
    ADD CONSTRAINT ux_organisation_unit_levels_name UNIQUE (name);


--
-- Name: organisation_units ux_organisation_units_code; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.organisation_units
    ADD CONSTRAINT ux_organisation_units_code UNIQUE (code);


--
-- Name: organisation_units ux_organisation_units_name; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.organisation_units
    ADD CONSTRAINT ux_organisation_units_name UNIQUE (name);


--
-- Name: quarters ux_quarters_code; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.quarters
    ADD CONSTRAINT ux_quarters_code UNIQUE (code);


--
-- Name: quarters ux_quarters_name; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.quarters
    ADD CONSTRAINT ux_quarters_name UNIQUE (name);


--
-- Name: risk_categories ux_risk_categories_code; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.risk_categories
    ADD CONSTRAINT ux_risk_categories_code UNIQUE (code);


--
-- Name: risk_categories ux_risk_categories_name; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.risk_categories
    ADD CONSTRAINT ux_risk_categories_name UNIQUE (name);


--
-- Name: risk_ranks ux_risk_ranks_name; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.risk_ranks
    ADD CONSTRAINT ux_risk_ranks_name UNIQUE (name);


--
-- Name: risk_registers ux_risk_registers_name; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.risk_registers
    ADD CONSTRAINT ux_risk_registers_name UNIQUE (name);


--
-- Name: risks ux_risks_code; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.risks
    ADD CONSTRAINT ux_risks_code UNIQUE (code);


--
-- Name: sub_areas ux_sub_areas_name; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.sub_areas
    ADD CONSTRAINT ux_sub_areas_name UNIQUE (name);


--
-- Name: users ux_user_email; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT ux_user_email UNIQUE (email);


--
-- Name: users ux_user_login; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT ux_user_login UNIQUE (login);


--
-- Name: idx_entity_audit_event_entity_id; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX idx_entity_audit_event_entity_id ON public.iftmis_entity_audit_event USING btree (entity_id);


--
-- Name: idx_entity_audit_event_entity_type; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX idx_entity_audit_event_entity_type ON public.iftmis_entity_audit_event USING btree (entity_type);


--
-- Name: idx_persistent_audit_event; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX idx_persistent_audit_event ON public.iftmis_persistent_audit_event USING btree (principal, event_date);


--
-- Name: idx_persistent_audit_evt_data; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX idx_persistent_audit_evt_data ON public.iftmis_persistent_audit_evt_data USING btree (event_id);


--
-- Name: user_authorities fk_authority_name; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_authorities
    ADD CONSTRAINT fk_authority_name FOREIGN KEY (authority_name) REFERENCES public.authorities(name);


--
-- Name: cluster_reports fk_cluster_reports_cluster_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cluster_reports
    ADD CONSTRAINT fk_cluster_reports_cluster_id FOREIGN KEY (cluster_id) REFERENCES public.clusters(id);


--
-- Name: iftmis_persistent_audit_evt_data fk_evt_pers_audit_evt_data; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.iftmis_persistent_audit_evt_data
    ADD CONSTRAINT fk_evt_pers_audit_evt_data FOREIGN KEY (event_id) REFERENCES public.iftmis_persistent_audit_event(event_id);


--
-- Name: finding_recommendations fk_finding_recommendations_finding_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.finding_recommendations
    ADD CONSTRAINT fk_finding_recommendations_finding_id FOREIGN KEY (finding_id) REFERENCES public.findings(id);


--
-- Name: finding_responses fk_finding_responses_recommendation_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.finding_responses
    ADD CONSTRAINT fk_finding_responses_recommendation_id FOREIGN KEY (recommendation_id) REFERENCES public.finding_recommendations(id);


--
-- Name: findings fk_findings_organisation_unit_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.findings
    ADD CONSTRAINT fk_findings_organisation_unit_id FOREIGN KEY (organisation_unit_id) REFERENCES public.organisation_units(id);


--
-- Name: indicators fk_indicators_sub_area_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.indicators
    ADD CONSTRAINT fk_indicators_sub_area_id FOREIGN KEY (sub_area_id) REFERENCES public.sub_areas(id);


--
-- Name: inspection_activities fk_inspection_activities_auditable_area_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.inspection_activities
    ADD CONSTRAINT fk_inspection_activities_auditable_area_id FOREIGN KEY (auditable_area_id) REFERENCES public.auditable_areas(id);


--
-- Name: inspection_activities fk_inspection_activities_inspection_plan_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.inspection_activities
    ADD CONSTRAINT fk_inspection_activities_inspection_plan_id FOREIGN KEY (inspection_plan_id) REFERENCES public.inspection_plans(id);


--
-- Name: inspection_activities fk_inspection_activities_objective_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.inspection_activities
    ADD CONSTRAINT fk_inspection_activities_objective_id FOREIGN KEY (objective_id) REFERENCES public.objectives(id);


--
-- Name: inspection_activities_risks fk_inspection_activities_risk_inspection_activities_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.inspection_activities_risks
    ADD CONSTRAINT fk_inspection_activities_risk_inspection_activities_id FOREIGN KEY (inspection_activity_id) REFERENCES public.inspection_activities(id);


--
-- Name: inspection_activities_risks fk_inspection_activities_risk_risk_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.inspection_activities_risks
    ADD CONSTRAINT fk_inspection_activities_risk_risk_id FOREIGN KEY (risk_id) REFERENCES public.risks(id);


--
-- Name: inspection_activities fk_inspection_activities_sub_area_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.inspection_activities
    ADD CONSTRAINT fk_inspection_activities_sub_area_id FOREIGN KEY (sub_area_id) REFERENCES public.sub_areas(id);


--
-- Name: inspection_activity_quarters fk_inspection_activity_quarters_activity_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.inspection_activity_quarters
    ADD CONSTRAINT fk_inspection_activity_quarters_activity_id FOREIGN KEY (activity_id) REFERENCES public.inspection_activities(id);


--
-- Name: inspection_activity_quarters fk_inspection_activity_quarters_quarter_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.inspection_activity_quarters
    ADD CONSTRAINT fk_inspection_activity_quarters_quarter_id FOREIGN KEY (quarter_id) REFERENCES public.quarters(id);


--
-- Name: inspection_areas fk_inspection_areas_auditable_area_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.inspection_areas
    ADD CONSTRAINT fk_inspection_areas_auditable_area_id FOREIGN KEY (auditable_area_id) REFERENCES public.auditable_areas(id);


--
-- Name: inspection_areas fk_inspection_areas_inspection_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.inspection_areas
    ADD CONSTRAINT fk_inspection_areas_inspection_id FOREIGN KEY (inspection_id) REFERENCES public.inspections(id);


--
-- Name: inspection_budget fk_inspection_budget_gfs_code_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.inspection_budget
    ADD CONSTRAINT fk_inspection_budget_gfs_code_id FOREIGN KEY (gfs_code_id) REFERENCES public.gfs_codes(id);


--
-- Name: inspection_budget fk_inspection_budget_inspection_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.inspection_budget
    ADD CONSTRAINT fk_inspection_budget_inspection_id FOREIGN KEY (inspection_id) REFERENCES public.inspections(id);


--
-- Name: inspection_findings fk_inspection_findings_category_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.inspection_findings
    ADD CONSTRAINT fk_inspection_findings_category_id FOREIGN KEY (category_id) REFERENCES public.finding_categories(id);


--
-- Name: inspection_findings fk_inspection_findings_sub_category_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.inspection_findings
    ADD CONSTRAINT fk_inspection_findings_sub_category_id FOREIGN KEY (sub_category_id) REFERENCES public.finding_sub_categories(id);


--
-- Name: inspection_findings fk_inspection_findings_work_done_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.inspection_findings
    ADD CONSTRAINT fk_inspection_findings_work_done_id FOREIGN KEY (work_done_id) REFERENCES public.inspection_work_dones(id);


--
-- Name: inspection_indicators fk_inspection_indicators_indicator_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.inspection_indicators
    ADD CONSTRAINT fk_inspection_indicators_indicator_id FOREIGN KEY (indicator_id) REFERENCES public.indicators(id);


--
-- Name: inspection_indicators fk_inspection_indicators_inspection_sub_area_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.inspection_indicators
    ADD CONSTRAINT fk_inspection_indicators_inspection_sub_area_id FOREIGN KEY (inspection_sub_area_id) REFERENCES public.inspection_sub_areas(id);


--
-- Name: inspection_members fk_inspection_members_declaration_attachment_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.inspection_members
    ADD CONSTRAINT fk_inspection_members_declaration_attachment_id FOREIGN KEY (declaration_attachment_id) REFERENCES public.file_resources(id);


--
-- Name: inspection_members fk_inspection_members_inspection_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.inspection_members
    ADD CONSTRAINT fk_inspection_members_inspection_id FOREIGN KEY (inspection_id) REFERENCES public.inspections(id);


--
-- Name: inspection_members fk_inspection_members_letter_attachment_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.inspection_members
    ADD CONSTRAINT fk_inspection_members_letter_attachment_id FOREIGN KEY (letter_attachment_id) REFERENCES public.file_resources(id);


--
-- Name: inspection_members fk_inspection_members_user_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.inspection_members
    ADD CONSTRAINT fk_inspection_members_user_id FOREIGN KEY (user_id) REFERENCES public.users(id);


--
-- Name: inspection_plans fk_inspection_plans_financial_year_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.inspection_plans
    ADD CONSTRAINT fk_inspection_plans_financial_year_id FOREIGN KEY (financial_year_id) REFERENCES public.financial_years(id);


--
-- Name: inspection_plans fk_inspection_plans_organisation_unit_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.inspection_plans
    ADD CONSTRAINT fk_inspection_plans_organisation_unit_id FOREIGN KEY (organisation_unit_id) REFERENCES public.organisation_units(id);


--
-- Name: inspection_procedures fk_inspection_procedures_inspection_indicator_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.inspection_procedures
    ADD CONSTRAINT fk_inspection_procedures_inspection_indicator_id FOREIGN KEY (inspection_indicator_id) REFERENCES public.inspection_indicators(id);


--
-- Name: inspection_procedures fk_inspection_procedures_procedure_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.inspection_procedures
    ADD CONSTRAINT fk_inspection_procedures_procedure_id FOREIGN KEY (procedure_id) REFERENCES public.procedures(id);


--
-- Name: inspection_sub_areas fk_inspection_sub_area_area_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.inspection_sub_areas
    ADD CONSTRAINT fk_inspection_sub_area_area_id FOREIGN KEY (inspection_area_id) REFERENCES public.inspection_areas(id);


--
-- Name: inspection_sub_areas fk_inspection_sub_areas_sub_area_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.inspection_sub_areas
    ADD CONSTRAINT fk_inspection_sub_areas_sub_area_id FOREIGN KEY (sub_area_id) REFERENCES public.sub_areas(id);


--
-- Name: inspection_work_dones fk_inspection_work_dones_procedure_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.inspection_work_dones
    ADD CONSTRAINT fk_inspection_work_dones_procedure_id FOREIGN KEY (procedure_id) REFERENCES public.inspection_procedures(id);


--
-- Name: inspections fk_inspections_financial_year_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.inspections
    ADD CONSTRAINT fk_inspections_financial_year_id FOREIGN KEY (financial_year_id) REFERENCES public.financial_years(id);


--
-- Name: inspections fk_inspections_organisation_unit_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.inspections
    ADD CONSTRAINT fk_inspections_organisation_unit_id FOREIGN KEY (organisation_unit_id) REFERENCES public.organisation_units(id);


--
-- Name: meeting_agendas fk_meeting_agendas_meeting_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.meeting_agendas
    ADD CONSTRAINT fk_meeting_agendas_meeting_id FOREIGN KEY (meeting_id) REFERENCES public.team_meetings(id);


--
-- Name: meeting_attachments fk_meeting_attachments_attachment_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.meeting_attachments
    ADD CONSTRAINT fk_meeting_attachments_attachment_id FOREIGN KEY (attachment_id) REFERENCES public.file_resources(id);


--
-- Name: meeting_attachments fk_meeting_attachments_meeting_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.meeting_attachments
    ADD CONSTRAINT fk_meeting_attachments_meeting_id FOREIGN KEY (meeting_id) REFERENCES public.team_meetings(id);


--
-- Name: meeting_members fk_meeting_members_meeting_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.meeting_members
    ADD CONSTRAINT fk_meeting_members_meeting_id FOREIGN KEY (meeting_id) REFERENCES public.team_meetings(id);


--
-- Name: organisation_units fk_organisation_units_organisation_unit_level_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.organisation_units
    ADD CONSTRAINT fk_organisation_units_organisation_unit_level_id FOREIGN KEY (organisation_unit_level_id) REFERENCES public.organisation_unit_levels(id);


--
-- Name: organisation_units fk_organisation_units_parent_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.organisation_units
    ADD CONSTRAINT fk_organisation_units_parent_id FOREIGN KEY (parent_id) REFERENCES public.organisation_units(id);


--
-- Name: procedures fk_procedures_indicator_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.procedures
    ADD CONSTRAINT fk_procedures_indicator_id FOREIGN KEY (indicator_id) REFERENCES public.indicators(id);


--
-- Name: quarters fk_quarters_financial_year_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.quarters
    ADD CONSTRAINT fk_quarters_financial_year_id FOREIGN KEY (financial_year_id) REFERENCES public.financial_years(id);


--
-- Name: response_attachments fk_response_attachments_attachment_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.response_attachments
    ADD CONSTRAINT fk_response_attachments_attachment_id FOREIGN KEY (attachment_id) REFERENCES public.file_resources(id);


--
-- Name: response_attachments fk_response_attachments_response_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.response_attachments
    ADD CONSTRAINT fk_response_attachments_response_id FOREIGN KEY (response_id) REFERENCES public.finding_responses(id);


--
-- Name: risk_ratings fk_risk_ratings_risk_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.risk_ratings
    ADD CONSTRAINT fk_risk_ratings_risk_id FOREIGN KEY (risk_id) REFERENCES public.risks(id);


--
-- Name: risk_registers fk_risk_registers_financial_year_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.risk_registers
    ADD CONSTRAINT fk_risk_registers_financial_year_id FOREIGN KEY (financial_year_id) REFERENCES public.financial_years(id);


--
-- Name: risk_registers fk_risk_registers_organisation_unit_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.risk_registers
    ADD CONSTRAINT fk_risk_registers_organisation_unit_id FOREIGN KEY (organisation_unit_id) REFERENCES public.organisation_units(id);


--
-- Name: risks fk_risks_objective_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.risks
    ADD CONSTRAINT fk_risks_objective_id FOREIGN KEY (objective_id) REFERENCES public.objectives(id);


--
-- Name: risks fk_risks_risk_category_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.risks
    ADD CONSTRAINT fk_risks_risk_category_id FOREIGN KEY (risk_category_id) REFERENCES public.risk_categories(id);


--
-- Name: risks fk_risks_risk_register_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.risks
    ADD CONSTRAINT fk_risks_risk_register_id FOREIGN KEY (risk_register_id) REFERENCES public.risk_registers(id);


--
-- Name: sub_areas fk_sub_areas_area_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.sub_areas
    ADD CONSTRAINT fk_sub_areas_area_id FOREIGN KEY (area_id) REFERENCES public.auditable_areas(id);


--
-- Name: team_meetings fk_team_meetings_inspection_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.team_meetings
    ADD CONSTRAINT fk_team_meetings_inspection_id FOREIGN KEY (inspection_id) REFERENCES public.inspections(id);


--
-- Name: users fk_uo; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT fk_uo FOREIGN KEY (organisation_unit_id) REFERENCES public.organisation_units(id);


--
-- Name: user_authorities fk_user_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_authorities
    ADD CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES public.users(id);


--
-- Name: iftmis_persistent_token fk_user_persistent_token; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.iftmis_persistent_token
    ADD CONSTRAINT fk_user_persistent_token FOREIGN KEY (user_id) REFERENCES public.users(id);


--
-- Name: inspection_activities_organisation_units inspection_activities_organisa_inspection_activities_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.inspection_activities_organisation_units
    ADD CONSTRAINT inspection_activities_organisa_inspection_activities_id FOREIGN KEY (inspection_activity_id) REFERENCES public.inspection_activities(id);


--
-- Name: inspection_activities_organisation_units inspection_activities_organisa_organisation_units_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.inspection_activities_organisation_units
    ADD CONSTRAINT inspection_activities_organisa_organisation_units_id FOREIGN KEY (organisation_units_id) REFERENCES public.organisation_units(id);


--
-- PostgreSQL database dump complete
--

