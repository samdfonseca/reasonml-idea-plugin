package com.reason.lang;

import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class OclCodeFragmentFactory {
    private final Project m_project;

    public static OclCodeFragmentFactory getInstance(Project project) {
        return ServiceManager.getService(project, OclCodeFragmentFactory.class);
    }

    public OclCodeFragmentFactory(Project project) {
        m_project = project;
    }

    public PsiFile createExpressionCodeFragment(@NotNull String text, @Nullable PsiElement context, @Nullable PsiType expectedType, boolean isPhysical) {
        return RmlElementFactory.createFileFromText(m_project, text);
    }
}
