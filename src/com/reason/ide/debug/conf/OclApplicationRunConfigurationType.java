package com.reason.ide.debug.conf;

import com.intellij.execution.configurations.ConfigurationFactory;
import com.intellij.execution.configurations.ConfigurationType;
import com.intellij.execution.configurations.ConfigurationTypeBase;
import com.intellij.execution.configurations.RunConfiguration;
import com.intellij.openapi.extensions.Extensions;
import com.intellij.openapi.project.Project;
import com.reason.icons.Icons;
import org.jetbrains.annotations.NotNull;

public class OclApplicationRunConfigurationType extends ConfigurationTypeBase {
    protected OclApplicationRunConfigurationType() {
        super("OclApplicationRunConfiguration", "OCaml application", "OCaml application run configuration", Icons.OCL_FILE);
        addFactory(new OclFactory(this));
    }

    public static OclApplicationRunConfigurationType getInstance() {
        return Extensions.findExtension(CONFIGURATION_TYPE_EP, OclApplicationRunConfigurationType.class);
    }

    public static class OclFactory extends ConfigurationFactory {

        public OclFactory(ConfigurationType type) {
            super(type);
        }

        @NotNull
        public RunConfiguration createTemplateConfiguration(@NotNull Project project) {
            return new OClApplicationConfiguration(project, "OCaml", getInstance());
        }
    }
}
