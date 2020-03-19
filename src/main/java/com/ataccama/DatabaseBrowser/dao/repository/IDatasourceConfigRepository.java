package com.ataccama.databasebrowser.dao.repository;

import com.ataccama.databasebrowser.domain.DatasourceConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDatasourceConfigRepository extends JpaRepository<DatasourceConfig, Long> {
}
