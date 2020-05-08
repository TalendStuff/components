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
package org.talend.components.google.drive.runtime.utils;

public class GoogleDriveGetResult {

    private String id;

    private byte[] content;

    public GoogleDriveGetResult(String id, byte[] content) {
        this.id = id;
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public byte[] getContent() {
        return content;
    }
}
