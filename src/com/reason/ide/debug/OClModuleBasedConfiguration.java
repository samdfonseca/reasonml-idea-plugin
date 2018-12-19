package com.reason.ide.debug;

import com.intellij.execution.configurations.RunConfigurationModule;
import com.intellij.openapi.project.Project;

public class OClModuleBasedConfiguration extends RunConfigurationModule {
    public OClModuleBasedConfiguration(Project project) {
        super(project);
    }
}
