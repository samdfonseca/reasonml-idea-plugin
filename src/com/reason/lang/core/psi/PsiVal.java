package com.reason.lang.core.psi;

import com.intellij.psi.NavigatablePsiElement;
import com.intellij.psi.PsiNameIdentifierOwner;
import com.intellij.psi.PsiQualifiedNamedElement;
import com.intellij.psi.StubBasedPsiElement;
import com.reason.lang.core.stub.PsiValStub;

public interface PsiVal extends PsiVar, PsiQualifiedNamedElement, PsiNameIdentifierOwner, PsiSignatureElement, NavigatablePsiElement, PsiStructuredElement, StubBasedPsiElement<PsiValStub> {
}
