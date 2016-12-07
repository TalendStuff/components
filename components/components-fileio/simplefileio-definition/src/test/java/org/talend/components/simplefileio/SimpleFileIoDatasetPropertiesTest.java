// ============================================================================
//
// Copyright (C) 2006-2016 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================

package org.talend.components.simplefileio;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.talend.daikon.properties.Properties;
import org.talend.daikon.properties.presentation.Form;
import org.talend.daikon.properties.presentation.Widget;

public class SimpleFileIoDatasetPropertiesTest {

    /**
     * Useful constant listing all of the fields in the properties.
     */
    public static final Iterable<String> ALL = Arrays.asList("format", "path", "recordDelimiter", "fieldDelimiter");

    /**
     * Instance to test. A new instance is created for each test.
     */
    SimpleFileIoDatasetProperties properties = null;

    @Before
    public void setup() {
        properties = new SimpleFileIoDatasetProperties("test");
        properties.init();
    }

    /**
     * Check the correct default values in the properties.
     */
    @Test
    public void testDefaultProperties() {
        assertThat(properties.format.getValue(), is(SimpleFileIoFormat.CSV));
        assertThat(properties.path.getValue(), is(""));
        assertThat(properties.recordDelimiter.getValue(), is("\n"));
        assertThat(properties.fieldDelimiter.getValue(), is(";"));
    }

    /**
     * Check the setup of the form layout.
     */
    @Test
    public void testSetupLayout() {
        properties.setupLayout();

        Form main = properties.getForm(Form.MAIN);
        assertThat(main, notNullValue());
        assertThat(main.getWidgets(), hasSize(4));

        for (String field : ALL) {
            Widget w = main.getWidget(field);
            assertThat(w, notNullValue());
        }
    }

    /**
     * Checks {@link Properties#refreshLayout(Form)}
     */
    @Test
    public void testRefreshLayout() {
        Form main = properties.getForm(Form.MAIN);
        properties.refreshLayout(main);

        for (String field : ALL) {
            Widget w = main.getWidget(field);
            assertThat(w.isVisible(), is(true));
        }

        // These three formats only have format and path visible.
        for (SimpleFileIoFormat format : Arrays.asList(SimpleFileIoFormat.AVRO, SimpleFileIoFormat.PARQUET,
                SimpleFileIoFormat.JSON)) {
            properties.format.setValue(format);
            properties.afterFormat();

            assertThat(main.getWidget("format").isVisible(), is(true));
            assertThat(main.getWidget("path").isVisible(), is(true));
            assertThat(main.getWidget("recordDelimiter").isVisible(), is(false));
            assertThat(main.getWidget("fieldDelimiter").isVisible(), is(false));
        }

        // All visible for CSV.
        properties.format.setValue(SimpleFileIoFormat.CSV);
        properties.afterFormat();

        assertThat(main.getWidget("format").isVisible(), is(true));
        assertThat(main.getWidget("path").isVisible(), is(true));
        assertThat(main.getWidget("recordDelimiter").isVisible(), is(true));
        assertThat(main.getWidget("fieldDelimiter").isVisible(), is(true));
    }
}