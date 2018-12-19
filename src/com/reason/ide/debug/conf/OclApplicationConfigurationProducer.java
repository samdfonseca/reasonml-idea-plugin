package com.reason.ide.debug.conf;

import com.intellij.execution.actions.ConfigurationContext;
import com.intellij.execution.actions.RunConfigurationProducer;
import com.intellij.execution.configurations.ConfigurationFactory;
import com.intellij.openapi.util.Ref;
import com.intellij.psi.PsiElement;

public class OclApplicationConfigurationProducer extends RunConfigurationProducer<OClApplicationConfiguration> {
    protected OclApplicationConfigurationProducer(ConfigurationFactory configurationFactory) {
        super(configurationFactory);
    }

    @Override
    protected boolean setupConfigurationFromContext(OClApplicationConfiguration configuration, ConfigurationContext context, Ref<PsiElement> sourceElement) {
        return true;
    }

    @Override
    public boolean isConfigurationFromContext(OClApplicationConfiguration configuration, ConfigurationContext context) {
        return false;
    }
}
