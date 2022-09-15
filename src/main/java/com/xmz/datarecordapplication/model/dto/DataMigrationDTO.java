package com.xmz.datarecordapplication.model.dto;

/**
 * @author xiaomingzhang
 * @date 2022/9/14
 */
public class DataMigrationDTO {

    private String tenantId;

    private Long dataSourceId;

    private String schemaName;

    private String tableName;

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public Long getDataSourceId() {
        return dataSourceId;
    }

    public void setDataSourceId(Long dataSourceId) {
        this.dataSourceId = dataSourceId;
    }

    public String getSchemaName() {
        return schemaName;
    }

    public void setSchemaName(String schemaName) {
        this.schemaName = schemaName;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    @Override
    public String toString() {
        return "DaraMigrationDTO{" +
                "tenantId='" + tenantId + '\'' +
                ", dataSourceId=" + dataSourceId +
                ", schemaName=" + schemaName +
                ", tableName='" + tableName + '\'' +
                '}';
    }
}
