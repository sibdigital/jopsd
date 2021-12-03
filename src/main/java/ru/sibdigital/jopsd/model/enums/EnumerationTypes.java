package ru.sibdigital.jopsd.model.enums;

public enum EnumerationTypes {
    ARBITARY_OBJECT_TYPE("ArbitaryObjectType"),
    ATTACH_TYPE("AttachType"),
    DOCUMENT_CATEGORY("DocumentCategory"),
    IMPORTANCE("Importance"),
    ISSUE_PRIORITY("IssuePriority"),
    KPI_CALC_METHOD("KpiCalcMethod"),
    KPI_OBJECT("KpiObject"),
    ORGANIZATION_TYPE("OrganizationType"),
    PERIOD("Period"),
    POSSIBILITY("Possibility"),
    PROJECT_APPROVE_STATUS("ProjectApproveStatus"),
    PROJECT_STATUS("ProjectStatus"),
    TARGET_STATUS("TargetStatus"),
    TARGET_TYPE("TargetType"),
    TIME_ENTRY_ACTIVITY("TimeEntryActivity"),
    USER_TASK_DIRECTORY("UserTaskDirectory");

    private final String value;
    private EnumerationTypes(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
