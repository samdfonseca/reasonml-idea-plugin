package com.reason.ide.debug;

import com.intellij.openapi.fileTypes.FileType;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.xdebugger.evaluation.XDebuggerEditorsProviderBase;
import com.reason.ide.files.OclFileType;
import com.reason.lang.OclCodeFragmentFactory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class OclDebuggerEditorsProvider extends XDebuggerEditorsProviderBase {
    @Override
    protected PsiFile createExpressionCodeFragment(@NotNull Project project, @NotNull String text, @Nullable PsiElement context, boolean isPhysical) {
        return OclCodeFragmentFactory.getInstance(project).createExpressionCodeFragment(text, context, null, isPhysical);
    }

    @NotNull
    @Override
    public FileType getFileType() {
        return OclFileType.INSTANCE;
    }
}
