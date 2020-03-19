package com.ataccama.databasebrowser.service.databaseinfo.runtime;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

/**
 * Abstract parent for database info runtime services created and discarded in runtime
 */
public abstract class DefaultDatabaseInfoRuntimeService extends NamedParameterJdbcDaoSupport implements IDatabaseInfoRuntimeService {


    protected abstract String allSchemasSql();

    protected abstract String allTablesSql();

    protected abstract String allColumnsSql();

    protected abstract String tableNameSql();

    protected abstract String tableDataSql(final String tableName);

    @Override
    public void setActualDataSource(final DataSource dataSource) {
        Objects.requireNonNull(dataSource);
        super.setDataSource(dataSource);
        afterPropertiesSet();
    }

    @Override
    public Optional<List<Map<String, Object>>> getAllSchemas() {
        return Optional.ofNullable(getJdbcTemplate().queryForList(allSchemasSql()));
    }

    @Override
    public Optional<List<Map<String, Object>>> getAllTables() {
        return Optional.ofNullable(getJdbcTemplate().queryForList(allTablesSql()));
    }

    @Override
    public Optional<List<Map<String, Object>>> getAllColumns() {
        return Optional.ofNullable(getJdbcTemplate().queryForList(allColumnsSql()));
    }

    @Override
    public Optional<List<Map<String, Object>>> getDataPreview(String tableName) {
        /*
        sql injection prevention
        because table name cannot be searched as prepared statement parameter,
         we first try to search table name by given table via
        named parameter to check whether table with the given table name exists
        */
        final String tableNameSearched;
        try {
            tableNameSearched = getJdbcTemplate().queryForObject(tableNameSql(), new String[]{tableName}, String.class);
        } catch (IncorrectResultSizeDataAccessException e) {
            return Optional.empty();
        }

        //if table exists we can use composed query which is now safe from sql injection
        return Optional.ofNullable(getJdbcTemplate().queryForList(tableDataSql(tableNameSearched)));
    }

    @Override
    public void destroy() {
        if(getDataSource() instanceof DisposableBean) {
            try {
                ((DisposableBean) getDataSource()).destroy();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
