package com.ataccama.databasebrowser.service;

import com.ataccama.databasebrowser.domain.DatasourceConfig;

import java.util.List;
import java.util.Optional;

/**
 * CRUD service for  @DatasourceConfig
 */
public interface IDatasourceConfigService {

    List<DatasourceConfig> findAll();

    Optional<DatasourceConfig> findById(Long id);

    DatasourceConfig save(DatasourceConfig datasourceConfig);

    void deleteById(Long id);
}
