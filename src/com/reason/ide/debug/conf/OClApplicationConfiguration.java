package com.reason.ide.debug.conf;

import com.intellij.execution.ExecutionException;
import com.intellij.execution.Executor;
import com.intellij.execution.configurations.ConfigurationType;
import com.intellij.execution.configurations.ModuleBasedConfiguration;
import com.intellij.execution.configurations.RunConfiguration;
import com.intellij.execution.configurations.RunProfileState;
import com.intellij.execution.runners.ExecutionEnvironment;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleManager;
import com.intellij.openapi.options.SettingsEditor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.InvalidDataException;
import com.intellij.openapi.util.WriteExternalException;
import com.intellij.util.xmlb.XmlSerializer;
import com.reason.ide.debug.OClModuleBasedConfiguration;
import org.jdom.Element;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.Collection;

public class OClApplicationConfiguration extends ModuleBasedConfiguration<OClModuleBasedConfiguration> {

    private String m_workDirectory;

    OClApplicationConfiguration(Project project, String name, ConfigurationType configurationType) {
        super(name, new OClModuleBasedConfiguration(project), configurationType.getConfigurationFactories()[0]);
    }

    @Nullable
    public String getWorkDirectory() {
        return m_workDirectory;
    }

    public void setWorkDirectory(@Nullable String workDirectory) {
        m_workDirectory = workDirectory;
    }

    @Override
    public Collection<Module> getValidModules() {
        Module[] modules = ModuleManager.getInstance(getProject()).getModules();
        return Arrays.asList(modules);
    }

    @NotNull
    @Override
    public SettingsEditor<? extends RunConfiguration> getConfigurationEditor() {
        return new OclRunConfigurationEditorForm();
    }

    @Nullable
    @Override
    public RunProfileState getState(@NotNull Executor executor, @NotNull ExecutionEnvironment environment) throws ExecutionException {
        OClModuleBasedConfiguration configuration = getConfigurationModule();
        Module module = configuration.getModule();
        return new OClApplicationRunningState(environment, module, this);
    }

    @Override
    public void writeExternal(@NotNull Element element) throws WriteExternalException {
        super.writeExternal(element);
        XmlSerializer.serializeInto(this, element);
    }

    @Override
    public void readExternal(@NotNull Element element) throws InvalidDataException {
        super.readExternal(element);
        XmlSerializer.deserializeInto(this, element);
    }
}
