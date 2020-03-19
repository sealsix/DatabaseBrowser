package service;


import com.ataccama.databasebrowser.DatabaseBrowserApplication;
import com.ataccama.databasebrowser.service.databaseinfo.IDatabaseInfoService;
import configuration.TestContextConfiguration;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@TestPropertySource(locations = {"classpath:application-test.properties"})
@ContextConfiguration(classes = TestContextConfiguration.class)
@SpringBootTest(classes = DatabaseBrowserApplication.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DatabaseInfoServiceIntegrationTest {

    @Autowired
    private IDatabaseInfoService databaseInfoService;


    @Before
    public void setUp() {
        //Mockito.when(datasourceConfigRepository.findById(new Long(1))).thenReturn(Optional.of(dc));
    }

    @Test
    public void testGetAllSchemas() {
        Optional<List<Map<String, Object>>> result = databaseInfoService.getAllSchemas(2L);
        assertTrue(result != null);
        assertTrue(result.isPresent());
        assertEquals(result.get().size(), 2);
        assertEquals(result.get().get(0).get("SCHEMA_NAME"), "INFORMATION_SCHEMA");

    }

    @Test
    public void testGetAllTables() {
        Optional<List<Map<String, Object>>> result = databaseInfoService.getAllTables(2L);
        assertTrue(result != null);
        assertTrue(result.isPresent());
        assertEquals(result.get().size(), 36);
        assertEquals(result.get().get(0).get("TABLE_NAME"), "FUNCTION_COLUMNS");

    }

    @Test
    public void testGetAllColumns() {
        Optional<List<Map<String, Object>>> result = databaseInfoService.getAllColumns(2L);
        assertTrue(result != null);
        assertTrue(result.isPresent());
        assertEquals(result.get().size(), 336);
        assertEquals(result.get().get(0).get("TABLE_NAME"), "FUNCTION_COLUMNS");
    }

    @Test
    public void testGetDataPreview() {
        Optional<List<Map<String, Object>>> result = databaseInfoService.getDataPreview(2L, "DATASOURCE_CONFIG");
        assertTrue(result != null);
        assertTrue(result.isPresent());
        assertEquals(result.get().size(), 2);
        assertEquals(result.get().get(0).get("HOSTNAME"), "localhost");

    }


}
