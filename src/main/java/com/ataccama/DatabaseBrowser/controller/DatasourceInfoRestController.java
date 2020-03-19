package com.ataccama.databasebrowser.controller;

import com.ataccama.databasebrowser.service.databaseinfo.IDatabaseInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Rest crud controller runtime connecting to concrete db and loading info
 */
@RestController
@RequestMapping("/api/v1/datasources")
public class DatasourceInfoRestController {

    @Autowired
    private IDatabaseInfoService databaseInfoService;

    @GetMapping("/{id}/schemas")
    public ResponseEntity getAllSchemas(@PathVariable Long id) {
        return databaseInfoService.getAllSchemas(id)
                .map(result -> ResponseEntity.ok(result))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}/tables")
    public ResponseEntity getAllTables(@PathVariable Long id) {
        return databaseInfoService.getAllTables(id)
                .map(result -> ResponseEntity.ok(result))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}/columns")
    public ResponseEntity getAllColumns(@PathVariable Long id) {
        return databaseInfoService.getAllColumns(id)
                .map(result -> ResponseEntity.ok(result))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}/tables/{tableName}/preview")
    public ResponseEntity getTableDataPreview(@PathVariable Long id, @PathVariable String tableName) {
        return databaseInfoService.getDataPreview(id, tableName)
                .map(result -> ResponseEntity.ok(result))
                .orElse(ResponseEntity.notFound().build());
    }
}
