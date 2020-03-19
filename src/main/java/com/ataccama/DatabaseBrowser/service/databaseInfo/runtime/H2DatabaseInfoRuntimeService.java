package com.ataccama.databasebrowser.service.databaseinfo.runtime;

/**
 *  H2 database implementation for database info runtime service
 */
public final class H2DatabaseInfoRuntimeService extends DefaultDatabaseInfoRuntimeService {

    private static final String ALL_SCHEMAS_SQL = "SELECT * FROM INFORMATION_SCHEMA.SCHEMATA";
    private static final String ALL_TABLES_SQL = "SELECT * FROM INFORMATION_SCHEMA.TABLES";
    private static final String ALL_COLUMNS_SQL = "SELECT * FROM INFORMATION_SCHEMA.COLUMNS";
    private static final String TABLE_NAME_SQL = "SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = ?";
    private static final String TABLE_PREVIEW_PREFIX = "SELECT * FROM ";
    private static final String TABLE_PREVIEW_SUFIX = " LIMIT 100";

    @Override
    protected String allSchemasSql() {
        return ALL_SCHEMAS_SQL;
    }

    @Override
    protected String allTablesSql() {
        return ALL_TABLES_SQL;
    }

    @Override
    protected String allColumnsSql() {
        return ALL_COLUMNS_SQL;
    }

    @Override
    protected String tableNameSql() {
        return TABLE_NAME_SQL;
    }

    @Override
    protected String tableDataSql(final String tableName) {
        return TABLE_PREVIEW_PREFIX + tableName + TABLE_PREVIEW_SUFIX;
    }
}
