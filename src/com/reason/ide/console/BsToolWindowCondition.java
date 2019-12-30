package com.reason.ide.console;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.Condition;
import com.intellij.openapi.vfs.VirtualFile;
import com.reason.Platform;

public class BsToolWindowCondition implements Condition<Project> {
    @Override
    public boolean value(Project project) {
        VirtualFile bsConfig = Platform.findBsconfig(project);
        return bsConfig != null && bsConfig.exists();
    }
}
