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
package org.talend.components.kafka.datastore;

import java.net.MalformedURLException;
import java.net.URL;

import org.talend.components.api.component.runtime.DependenciesReader;
import org.talend.components.api.component.runtime.JarRuntimeInfo;
import org.talend.components.api.exception.ComponentException;
import org.talend.components.common.dataset.DatasetProperties;
import org.talend.components.common.datastore.DatastoreDefinition;
import org.talend.components.kafka.KafkaFamilyDefinition;
import org.talend.components.kafka.dataset.KafkaDatasetDefinition;
import org.talend.components.kafka.dataset.KafkaDatasetProperties;
import org.talend.components.kafka.input.KafkaInputDefinition;
import org.talend.components.kafka.output.KafkaOutputDefinition;
import org.talend.daikon.definition.DefinitionImageType;
import org.talend.daikon.definition.I18nDefinition;
import org.talend.daikon.runtime.RuntimeInfo;

public class KafkaDatastoreDefinition extends I18nDefinition implements DatastoreDefinition<KafkaDatastoreProperties> {

    public static final String RUNTIME = "org.talend.components.kafka.runtime.KafkaDatastoreRuntime";

    public static final String NAME = "KafkaDatastore";

    public KafkaDatastoreDefinition() {
        super(NAME);
    }

    @Override
    public Class<KafkaDatastoreProperties> getPropertiesClass() {
        return KafkaDatastoreProperties.class;
    }

    @Override
    public RuntimeInfo getRuntimeInfo(KafkaDatastoreProperties properties) {
        try {
            return new JarRuntimeInfo(new URL(KafkaFamilyDefinition.MAVEN_DEFAULT_RUNTIME_URI),
                    DependenciesReader.computeDependenciesFilePath(KafkaFamilyDefinition.MAVEN_GROUP_ID,
                            KafkaFamilyDefinition.MAVEN_DEFAULT_RUNTIME_ARTIFACT_ID), RUNTIME);
        } catch (MalformedURLException e) {
            throw new ComponentException(e);
        }
    }

    @Override
    public DatasetProperties createDatasetProperties(KafkaDatastoreProperties storeProp) {
        KafkaDatasetProperties datasetProperties = new KafkaDatasetProperties(KafkaDatasetDefinition.NAME);
        datasetProperties.init();
        datasetProperties.setDatastoreProperties(storeProp);
        return datasetProperties;
    }

    @Override
    public String getInputCompDefinitionName() {
        return KafkaInputDefinition.NAME;
    }

    @Override
    public String getOutputCompDefinitionName() {
        return KafkaOutputDefinition.NAME;
    }

    @Deprecated
    @Override
    public String getImagePath() {
        return "/org/talend/components/kafka/Kafka.svg";
    }

    @Override
    public String getImagePath(DefinitionImageType type) {
        switch (type) {
        case PALETTE_ICON_32X32:
            return null;
        case SVG_ICON:
            return "/org/talend/components/kafka/Kafka.svg";
        }
        return null;
    }

    @Override
    public String getIconKey() {
        return "kafka";
    }
}
