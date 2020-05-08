// ==============================================================================
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
// ==============================================================================
package org.talend.components.service.rest.mock;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.talend.components.api.component.Connector;
import org.talend.components.api.component.PropertyPathConnector;

/**
 * Mock source component properties for tests.
 */
public class MockComponentTransformerProperties extends MockComponentProperties {

    /**
     * Default constructor.
     *
     * @param name the properties name.
     */
    public MockComponentTransformerProperties(String name) {
        super(name);
    }

    @Override
    public Set<? extends Connector> getPossibleConnectors(boolean isOutgoingConnection) {
        return isOutgoingConnection //
        ? new HashSet<>(Arrays.asList(new PropertyPathConnector("MAIN", "schema.main"))) //
                : new HashSet<>(Arrays.asList(new PropertyPathConnector("REJECT", "schema.outMain")));
    }
}
