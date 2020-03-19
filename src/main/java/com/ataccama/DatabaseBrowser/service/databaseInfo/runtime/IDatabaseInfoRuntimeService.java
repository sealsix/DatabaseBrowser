package com.ataccama.databasebrowser.service.databaseinfo.runtime;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * database info runtime service created and discarded in runtime
 */
public interface IDatabaseInfoRuntimeService {


    /**
     * sets datasource
     * @param dataSource datasource
     */
    void setActualDataSource(DataSource dataSource);

    /**
     * get all schemas from concrete db
     * @return db schemas
     */
    Optional<List<Map<String, Object>>> getAllSchemas();

    /**
     * get all tables from concrete db
     * @return db tables
     */
    Optional<List<Map<String, Object>>> getAllTables();

    /**
     * get all columns from concrete db
     * @return db columns
     */
    Optional<List<Map<String, Object>>> getAllColumns();


    /**
     * get table data preview
     * @param tableName table name
     * @return data preview
     */
    Optional<List<Map<String, Object>>> getDataPreview(String tableName);


    /**
     * destroys owned resources - has to be called before stop application, better when forget reference to instance
     */
    void destroy();
}
