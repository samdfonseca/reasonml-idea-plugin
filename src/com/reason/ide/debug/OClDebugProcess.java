package com.reason.ide.debug;

import com.intellij.execution.ExecutionException;
import com.intellij.execution.configurations.RunProfileState;
import com.intellij.execution.runners.ExecutionEnvironment;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.fileEditor.FileDocumentManager;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.openapi.project.Project;
import com.intellij.testFramework.LightVirtualFile;
import com.intellij.xdebugger.XDebugProcess;
import com.intellij.xdebugger.XDebugSession;
import com.intellij.xdebugger.XExpression;
import com.intellij.xdebugger.XSourcePosition;
import com.intellij.xdebugger.evaluation.EvaluationMode;
import com.intellij.xdebugger.evaluation.XDebuggerEditorsProvider;
import com.reason.ide.debug.conf.OClApplicationConfiguration;
import com.reason.ide.files.OclFileType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class OClDebugProcess extends XDebugProcess {
    private final ExecutionEnvironment m_environment;
    private final RunProfileState m_runningState;

    OClDebugProcess(@NotNull XDebugSession session, ExecutionEnvironment environment) throws ExecutionException {
        super(session);
        m_environment = environment;

        OClApplicationConfiguration runConfig = (OClApplicationConfiguration) getSession().getRunProfile();
        if (runConfig != null) {
            m_runningState = runConfig.getState(m_environment.getExecutor(), m_environment);
            if (m_runningState == null) {
                throw new ExecutionException("Failed to execute a run configuration.");
            }
        } else {
            throw new ExecutionException("Failed to find a run configuration.");
        }
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


}
