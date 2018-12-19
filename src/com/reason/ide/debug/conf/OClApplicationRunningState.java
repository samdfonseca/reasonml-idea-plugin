package com.reason.ide.debug.conf;

import com.intellij.execution.ExecutionException;
import com.intellij.execution.configurations.CommandLineState;
import com.intellij.execution.configurations.GeneralCommandLine;
import com.intellij.execution.filters.TextConsoleBuilder;
import com.intellij.execution.filters.TextConsoleBuilderFactory;
import com.intellij.execution.process.OSProcessHandler;
import com.intellij.execution.process.ProcessHandler;
import com.intellij.execution.runners.ExecutionEnvironment;
import com.intellij.openapi.module.Module;
import com.reason.ide.debug.conf.OClApplicationConfiguration;
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

    private GeneralCommandLine getCommand() {
        GeneralCommandLine commandLine = new GeneralCommandLine();
        // set exe/working dir/...
        TextConsoleBuilder consoleBuilder = TextConsoleBuilderFactory.getInstance().createBuilder(m_module.getProject());
        setConsoleBuilder(consoleBuilder);
        return commandLine;
    }

}
