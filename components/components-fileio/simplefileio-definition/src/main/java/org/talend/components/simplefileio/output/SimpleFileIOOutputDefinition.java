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

package org.talend.components.simplefileio.output;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.EnumSet;
import java.util.Set;

import org.talend.components.api.component.AbstractComponentDefinition;
import org.talend.components.api.component.ConnectorTopology;
import org.talend.components.api.component.runtime.DependenciesReader;
import org.talend.components.api.component.runtime.ExecutionEngine;
import org.talend.components.api.component.runtime.JarRuntimeInfo;
import org.talend.components.api.exception.ComponentException;
import org.talend.components.api.properties.ComponentProperties;
import org.talend.components.simplefileio.SimpleFileIOComponentFamilyDefinition;
import org.talend.daikon.properties.property.Property;
import org.talend.daikon.runtime.RuntimeInfo;

public class SimpleFileIOOutputDefinition extends AbstractComponentDefinition {

    public static final String NAME = SimpleFileIOComponentFamilyDefinition.NAME + "Output";

    public static final String RUNTIME = "org.talend.components.simplefileio.runtime.SimpleFileIOOutputRuntime";

    public SimpleFileIOOutputDefinition() {
        super(NAME, ExecutionEngine.BEAM);
    }

    @Override
    public Class<? extends ComponentProperties> getPropertyClass() {
        return SimpleFileIOOutputProperties.class;
    }

    @Override
    public String getIconKey() {
        return "file-hdfs-o";
    }

    @Override
    public String[] getFamilies() {
        return new String[] { SimpleFileIOComponentFamilyDefinition.NAME };
    }

    @Override
    public Property[] getReturnProperties() {
        return new Property[] {};
    }

    @Override
    public Set<ConnectorTopology> getSupportedConnectorTopologies() {
        return EnumSet.of(ConnectorTopology.INCOMING);
    }

    @Override
    public RuntimeInfo getRuntimeInfo(ExecutionEngine engine, ComponentProperties properties, ConnectorTopology connectorTopology) {
        assertEngineCompatibility(engine);
        assertConnectorTopologyCompatibility(connectorTopology);
        try {
            return new JarRuntimeInfo(new URL(SimpleFileIOComponentFamilyDefinition.MAVEN_DEFAULT_RUNTIME_URI),
                    DependenciesReader.computeDependenciesFilePath(SimpleFileIOComponentFamilyDefinition.MAVEN_GROUP_ID,
                            SimpleFileIOComponentFamilyDefinition.MAVEN_DEFAULT_RUNTIME_ARTIFACT_ID), RUNTIME);
        } catch (MalformedURLException e) {
            throw new ComponentException(e);
        }
    }
}
