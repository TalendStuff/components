// ============================================================================
//
// Copyright (C) 2006-2020 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.components.simplefileio.runtime;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.talend.components.common.datastore.DatastoreDefinition;
import org.talend.components.simplefileio.SimpleFileIODatastoreDefinition;
import org.talend.components.simplefileio.SimpleFileIODatastoreProperties;
import org.talend.daikon.runtime.RuntimeInfo;
import org.talend.daikon.runtime.RuntimeUtil;

/**
 * Unit tests for {@link SimpleFileIODatastoreRuntime}.
 */
public class SimpleFileIODatastoreRuntimeTest {

    /**
     * Instance to test. Definitions are immutable.
     */
    private final DatastoreDefinition<?> def = new SimpleFileIODatastoreDefinition();

    static {
        RuntimeUtil.registerMavenUrlHandler();
    }

    /**
     * @return the properties for this datastore, fully initialized with the default values.
     */
    public static SimpleFileIODatastoreProperties createDatastoreProperties() {
        // Configure the datastore.
        SimpleFileIODatastoreProperties datastoreProps = new SimpleFileIODatastoreProperties(null);
        datastoreProps.init();
        return datastoreProps;
    }

    /**
     * Checks the {@link RuntimeInfo} of the definition.
     */
    @Test
    @Ignore("This can't work unless the runtime jar is already installed in maven!")
    public void testRuntimeInfo() throws MalformedURLException {
        RuntimeInfo runtimeInfo = def.getRuntimeInfo(null);

        // We test the maven dependencies in this runtime class, where they are available.
        List<URL> dependencies = runtimeInfo.getMavenUrlDependencies();
        assertThat(dependencies, notNullValue());
        assertThat(dependencies, hasItem(new URL("mvn:org.apache.commons/commons-csv/1.4/jar")));
        assertThat(dependencies, hasSize(greaterThan(100)));
    }

}
