package com.ataccama.databasebrowser.service.databaseinfo;

import com.ataccama.databasebrowser.dao.repository.IDatasourceConfigRepository;
import com.ataccama.databasebrowser.domain.DatasourceConfig;
import com.ataccama.databasebrowser.service.databaseinfo.runtime.IDatabaseInfoRuntimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Service
public final class DatabaseInfoService implements IDatabaseInfoService {

    @Autowired
    private IDatasourceConfigRepository datasourceConfigRepository;

    @Override
    public Optional<List<Map<String, Object>>> getAllSchemas(Long datasourceConfigId) {
        final IDatabaseInfoRuntimeService runtimeService = getDatabaseInfoRuntimeService(datasourceConfigId);
        try {
            return runtimeService != null ? runtimeService.getAllSchemas() : Optional.empty();
        }
        finally {
            if(runtimeService != null) {
                runtimeService.destroy();
            }
        }
    }

    @Override
    public Optional<List<Map<String, Object>>> getAllTables(Long datasourceConfigId) {
        final IDatabaseInfoRuntimeService runtimeService = getDatabaseInfoRuntimeService(datasourceConfigId);
        try {
            return runtimeService != null ? runtimeService.getAllTables() : Optional.empty();
        }
        finally {
            runtimeService.destroy();
        }
    }

    @Override
    public Optional<List<Map<String, Object>>> getAllColumns(Long datasourceConfigId) {
        final IDatabaseInfoRuntimeService runtimeService = getDatabaseInfoRuntimeService(datasourceConfigId);
        try {
            return runtimeService != null ? runtimeService.getAllColumns() : Optional.empty();
        }
        finally {
            runtimeService.destroy();
        }
    }

    @Override
    public Optional<List<Map<String, Object>>> getDataPreview(Long datasourceConfigId, String tableName) {
        final IDatabaseInfoRuntimeService runtimeService = getDatabaseInfoRuntimeService(datasourceConfigId);
        try {
            return runtimeService != null ? runtimeService.getDataPreview(tableName) : Optional.empty();
        }
        finally {
            runtimeService.destroy();
        }
    }

    private IDatabaseInfoRuntimeService getDatabaseInfoRuntimeService(Long datasourceConfigId) {
        Objects.requireNonNull(datasourceConfigId);
        final Optional<DatasourceConfig> datasourceConfig = datasourceConfigRepository.findById(datasourceConfigId);
        if (!datasourceConfig.isPresent()) {
            return null;
        }
        final IDatabaseInfoRuntimeService loadedDatabaseInfoRuntimeService = getDatabaseInfoRuntimeServiceFromDataSource(datasourceConfig);
        return loadedDatabaseInfoRuntimeService;
    }

    private IDatabaseInfoRuntimeService getDatabaseInfoRuntimeServiceFromDataSource(Optional<DatasourceConfig> datasourceConfig) {
        if (!datasourceConfig.isPresent()) {
            return null;
        }
        final Class<? extends IDatabaseInfoRuntimeService> databaseInfoServiceClass
                = datasourceConfig.get().getVendor().getDatabaseInfoRuntimeServiceClass();

        SingleConnectionDataSource dataSource = null;
        try {
            dataSource = getDatasourceFromConfig(datasourceConfig);
            if (dataSource == null) {
                return null;
            }

            final IDatabaseInfoRuntimeService databaseInfoRuntimeService = databaseInfoServiceClass.newInstance();

            databaseInfoRuntimeService.setActualDataSource(dataSource);
            return databaseInfoRuntimeService;
        } catch (InstantiationException | IllegalAccessException e) {
            if (dataSource != null) {
                dataSource.destroy();
            }
            throw new IllegalStateException("databaseInfoService class cant be instantiated !!: " + databaseInfoServiceClass, e);
        } catch (Exception e) {
            if (dataSource != null) {
                dataSource.destroy();
            }
        }

        return null;
    }

    private SingleConnectionDataSource getDatasourceFromConfig(final Optional<DatasourceConfig> datasourceConfig) {
        if (!datasourceConfig.isPresent()) {
            return null;
        }

        final String url = new StringBuilder(100)
                .append("jdbc:").append(datasourceConfig.get().getVendor().getVendorUrlName())
                .append(":")
                .append(datasourceConfig.get().getHostname())
                .append(":")
                .append(datasourceConfig.get().getPort())
                .append("/")
                .append(datasourceConfig.get().getDbname())
                .toString();

        return DataSourceBuilder.create()
                .type(SingleConnectionDataSource.class)
                .driverClassName(datasourceConfig.get().getVendor().getDriverClassName())
                .url(url)
                .username(datasourceConfig.get().getUsername())
                .password(datasourceConfig.get().getPassword())
                .build();
    }
}
