package com.reason.lang.core.psi;

import com.intellij.psi.NavigatablePsiElement;
import com.intellij.psi.PsiNameIdentifierOwner;
import com.intellij.psi.PsiQualifiedNamedElement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;

/**
 * Common interface to file-based modules and inner modules
 */
public interface PsiModule extends PsiQualifiedNamedElement, NavigatablePsiElement, PsiStructuredElement {

    @Nullable
    String getAlias();

    @NotNull
    Collection<PsiNameIdentifierOwner> getExpressions();

    @Nullable
    PsiModule getModuleExpression(@Nullable String name);

    @Nullable
    PsiLet getLetExpression(@Nullable String name);

    @Nullable
    PsiVal getValExpression(@Nullable String name);

}
