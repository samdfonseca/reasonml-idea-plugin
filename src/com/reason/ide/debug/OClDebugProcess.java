package com.reason.ide.debug;

import com.intellij.execution.ExecutionException;
import com.intellij.execution.configurations.GeneralCommandLine;
import com.intellij.execution.filters.TextConsoleBuilder;
import com.intellij.execution.filters.TextConsoleBuilderFactory;
import com.intellij.execution.process.OSProcessHandler;
import com.intellij.execution.process.ProcessEvent;
import com.intellij.execution.process.ProcessHandler;
import com.intellij.execution.process.ProcessListener;
import com.intellij.execution.runners.ExecutionEnvironment;
import com.intellij.execution.ui.ConsoleView;
import com.intellij.execution.ui.ConsoleViewContentType;
import com.intellij.execution.ui.ExecutionConsole;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.fileEditor.FileDocumentManager;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.Key;
import com.intellij.testFramework.LightVirtualFile;
import com.intellij.xdebugger.XDebugProcess;
import com.intellij.xdebugger.XDebugSession;
import com.intellij.xdebugger.XExpression;
import com.intellij.xdebugger.XSourcePosition;
import com.intellij.xdebugger.evaluation.EvaluationMode;
import com.intellij.xdebugger.evaluation.XDebuggerEditorsProvider;
import com.reason.ide.debug.conf.OClApplicationConfiguration;
import com.reason.ide.debug.conf.OClApplicationRunningState;
import com.reason.ide.files.OclFileType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class OClDebugProcess extends XDebugProcess {

    private static final Logger LOG = Logger.getInstance("ReasonML.debug");

    private final OClApplicationRunningState m_runningState;

    OClDebugProcess(@NotNull XDebugSession session, ExecutionEnvironment environment) throws ExecutionException {
        super(session);

        OClApplicationConfiguration runConfig = (OClApplicationConfiguration) getSession().getRunProfile();
        if (runConfig != null) {
            m_runningState = (OClApplicationRunningState) runConfig.getState(environment.getExecutor(), environment);
            if (m_runningState == null) {
                throw new ExecutionException("Failed to execute a run configuration.");
            }
        } else {
            throw new ExecutionException("Failed to find a run configuration.");
        }
    }

    @NotNull
    @Override
    public ExecutionConsole createConsole() {
        TextConsoleBuilder consoleBuilder = TextConsoleBuilderFactory.getInstance().createBuilder(m_runningState.getEnvironment().getProject());
        ConsoleView consoleView = consoleBuilder.getConsole();

        ProcessHandler m_processHandler = getProcessHandler();
        m_processHandler.addProcessListener(new ProcessListener() {
            @Override
            public void startNotified(@NotNull ProcessEvent event) {
                consoleView.print("Connected to the OCaml debugger\n", ConsoleViewContentType.SYSTEM_OUTPUT);
            }

            @Override
            public void processTerminated(@NotNull ProcessEvent event) {
            }

            @Override
            public void processWillTerminate(@NotNull ProcessEvent event, boolean willBeDestroyed) {
            }

            @Override
            public void onTextAvailable(@NotNull ProcessEvent event, @NotNull Key outputType) {
                consoleView.print(event.getText(), ConsoleViewContentType.getConsoleViewType(outputType));
            }
        });
        m_processHandler.startNotify();

        return consoleView;
    }

    @NotNull
    @Override
    public XDebuggerEditorsProvider getEditorsProvider() {
        return new XDebuggerEditorsProvider() {
            @NotNull
            @Override
            public FileType getFileType() {
                return OclFileType.INSTANCE;
            }

            @NotNull
            @Override
            public Document createDocument(@NotNull Project project,
                                           @NotNull XExpression expression,
                                           @Nullable XSourcePosition sourcePosition,
                                           @NotNull EvaluationMode mode) {
                LightVirtualFile file = new LightVirtualFile("ocaml-debugger.txt", expression.getExpression());
                return FileDocumentManager.getInstance().getDocument(file);
            }
        };
    }

    @Nullable
    @Override
    protected ProcessHandler doGetProcessHandler() {
        try {
            GeneralCommandLine commandLine = m_runningState.getCommand();
            commandLine.setExePath("ocamldebug");
            commandLine.addParameters("a.out");

            LOG.debug("Running debugger process: " + commandLine.getCommandLineString());

            Process process = commandLine.createProcess();
            return new OSProcessHandler(process, commandLine.getCommandLineString());
        } catch (ExecutionException e) {
            LOG.debug("Failed to run debug target.", e);
            throw new RuntimeException(e);
        }
    }

}
