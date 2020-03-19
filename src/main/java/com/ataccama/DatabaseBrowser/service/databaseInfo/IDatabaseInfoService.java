package com.ataccama.databasebrowser.service.databaseinfo;

import java.util.List;
import java.util.Map;
import java.util.Optional;


/**
 * service which returs data from db by given datasource configuration
 */
public interface IDatabaseInfoService {

    Optional<List<Map<String, Object>>> getAllSchemas(Long datasourceConfigId);

    Optional<List<Map<String, Object>>> getAllTables(Long datasourceConfigId);

    Optional<List<Map<String, Object>>> getAllColumns(Long datasourceConfigId);

    Optional<List<Map<String, Object>>> getDataPreview(Long datasourceConfigId, String tableName);

}
