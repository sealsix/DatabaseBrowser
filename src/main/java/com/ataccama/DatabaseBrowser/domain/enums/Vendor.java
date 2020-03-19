package com.ataccama.databasebrowser.domain.enums;

import com.ataccama.databasebrowser.service.databaseinfo.runtime.H2DatabaseInfoRuntimeService;
import com.ataccama.databasebrowser.service.databaseinfo.runtime.IDatabaseInfoRuntimeService;

public enum Vendor {

    H2("org.h2.Driver", "h2", H2DatabaseInfoRuntimeService.class);

    private final String driverClassName;
    private final String vendorUrlName;
    private final Class<? extends IDatabaseInfoRuntimeService> databaseInfoRuntimeServiceClass;


    Vendor(String driverClassName, String vendorUrlName, Class<? extends IDatabaseInfoRuntimeService> databaseInfoRuntimeServiceClass) {
        this.driverClassName = driverClassName;
        this.vendorUrlName = vendorUrlName;
        this.databaseInfoRuntimeServiceClass = databaseInfoRuntimeServiceClass;
    }

    public String getDriverClassName() {
        return driverClassName;
    }

    public String getVendorUrlName() {
        return vendorUrlName;
    }

    public Class<? extends IDatabaseInfoRuntimeService> getDatabaseInfoRuntimeServiceClass() {
        return databaseInfoRuntimeServiceClass;
    }
}
