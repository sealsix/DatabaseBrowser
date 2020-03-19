package com.ataccama.databasebrowser.controller;

import com.ataccama.databasebrowser.domain.DatasourceConfig;
import com.ataccama.databasebrowser.domain.enums.Vendor;
import com.ataccama.databasebrowser.service.IDatasourceConfigService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 *  Rest crud controller for @DatasourceConfig
 */
@RestController
@RequestMapping("/api/v1/datasources")
public class DatasourceConfigRestController {

    @Autowired
    private IDatasourceConfigService datasourceConfigService;

    @GetMapping("/supportedTypes")
    public ResponseEntity<Vendor[]> supportedTypes() {
        return ResponseEntity.ok(Vendor.values());
    }

    @GetMapping
    public ResponseEntity<List<DatasourceConfig>> findAll() {
        List<DatasourceConfig> list = datasourceConfigService.findAll();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatasourceConfig> findById(@PathVariable Long id) { ;
        return datasourceConfigService.findById(id)
                .map(datasourceConfig -> ResponseEntity.ok(datasourceConfig))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<DatasourceConfig> create(@Valid @RequestBody DatasourceConfig datasourceConfig) {
        return ResponseEntity.ok(datasourceConfigService.save(datasourceConfig));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DatasourceConfig> update(@PathVariable Long id, @Valid @RequestBody DatasourceConfig datasourceConfig) { ;
        return datasourceConfigService.findById(id)
                .map(foundDatasourceConfig ->  {
                    BeanUtils.copyProperties(datasourceConfig, foundDatasourceConfig, "id", "version");
                    datasourceConfigService.save(foundDatasourceConfig);
                    return ResponseEntity.ok(foundDatasourceConfig);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DatasourceConfig> delete(@PathVariable Long id) {
        return datasourceConfigService.findById(id)
                .map(found -> {
                    datasourceConfigService.deleteById(id);
                    return new ResponseEntity<DatasourceConfig>(HttpStatus.OK);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}

