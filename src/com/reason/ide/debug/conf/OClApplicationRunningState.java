package com.reason.ide.debug.conf;

import com.intellij.execution.ExecutionException;
import com.intellij.execution.configurations.CommandLineState;
import com.intellij.execution.configurations.GeneralCommandLine;
import com.intellij.execution.process.OSProcessHandler;
import com.intellij.execution.process.ProcessHandler;
import com.intellij.execution.runners.ExecutionEnvironment;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.util.text.StringUtil;
import org.jetbrains.annotations.NotNull;

public class OClApplicationRunningState extends CommandLineState {
    private final Module m_module;
    private final OClApplicationConfiguration m_configuration;

    OClApplicationRunningState(ExecutionEnvironment environment, Module module, OClApplicationConfiguration configuration) {
        super(environment);
        m_module = module;
        m_configuration = configuration;
    }

    @NotNull
    @Override
    protected ProcessHandler startProcess() throws ExecutionException {
        GeneralCommandLine commandLine = getCommand();
        return new OSProcessHandler(commandLine.createProcess(), commandLine.getCommandLineString());
    }

    public GeneralCommandLine getCommand() {
        GeneralCommandLine commandLine = new GeneralCommandLine();
        // set exe/working dir/...
        String workDirectory = m_configuration.getWorkDirectory();
        commandLine.withWorkDirectory(StringUtil.isEmpty(workDirectory) ? m_module.getProject().getBasePath() : workDirectory);

        return commandLine;
    }
}