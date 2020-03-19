package dao.repository;


import com.ataccama.databasebrowser.DatabaseBrowserApplication;
import com.ataccama.databasebrowser.dao.repository.IDatasourceConfigRepository;
import com.ataccama.databasebrowser.domain.DatasourceConfig;
import com.ataccama.databasebrowser.domain.enums.Vendor;
import configuration.TestContextConfiguration;
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
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@TestPropertySource(locations = {"classpath:application-test.properties"})
@ContextConfiguration(classes = TestContextConfiguration.class)
@SpringBootTest(classes = DatabaseBrowserApplication.class)
@FixMethodOrder(MethodSorters.DEFAULT)
public class DatabaseConfigRepositoryIntegrationTest {

    @Autowired
    private IDatasourceConfigRepository datasourceConfigRepository;

    @Test
    public void testFindById() {
        final Optional<DatasourceConfig> dc = datasourceConfigRepository.findById(2L);
        assertTrue(dc.isPresent());
        assertEquals(dc.get().getVendor(), Vendor.H2);
        assertEquals("tcp://localhost", dc.get().getHostname());
    }

    @Test
    public void testFindAll() {
        final List<DatasourceConfig> res = datasourceConfigRepository.findAll();
        assertTrue(res != null);
        assertTrue(res.size() > 0);
        assertEquals(res.get(0).getDbname(), "TESTDB");
        //assertEquals("mem:testdb", dc.get().getHostname());
    }

    @Test
    public void testUpdate() {
        final Optional<DatasourceConfig> dc = datasourceConfigRepository.findById(1L);
        assertTrue(dc.isPresent());
        assertEquals(dc.get().getDbname(), "TESTDB");
        dc.get().setDbname("NEWVAL");

        datasourceConfigRepository.save(dc.get());

        Optional<DatasourceConfig> dcsaved = datasourceConfigRepository.findById(1L);
        assertEquals(dc.get().getDbname(), "NEWVAL");
    }

    @Test
    public void testDelete() {
        final Optional<DatasourceConfig> dc = datasourceConfigRepository.findById(3L);
        assertTrue(dc.isPresent());

        datasourceConfigRepository.deleteById(dc.get().getId());

        assertEquals(datasourceConfigRepository.findById(3L), Optional.empty());
    }


}
