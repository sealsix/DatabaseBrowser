package com.ataccama.databasebrowser.service;

import com.ataccama.databasebrowser.domain.DatasourceConfig;
import com.ataccama.databasebrowser.dao.repository.IDatasourceConfigRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DatasourceConfigService implements IDatasourceConfigService {

    @Autowired
    private IDatasourceConfigRepository datasourceConfigRepository;

    @Override
    public List<DatasourceConfig> findAll() {
        return datasourceConfigRepository.findAll();
    }

    @Override
    public Optional<DatasourceConfig> findById(Long id) {
        return datasourceConfigRepository.findById(id);
    }

    @Override
    public DatasourceConfig save(DatasourceConfig datasourceConfig) {
        return datasourceConfigRepository.save(datasourceConfig);
    }

    @Override
    public void deleteById(Long id) {
        datasourceConfigRepository.deleteById(id);
    }
}
