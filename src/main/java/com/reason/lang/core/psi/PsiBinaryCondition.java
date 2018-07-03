package com.reason.lang.core.psi;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;

public class PsiBinaryCondition extends ASTWrapperPsiElement {

    public PsiBinaryCondition(ASTNode node) {
        super(node);
    }

    @Override
    public boolean canNavigate() {
        return false;
    }
}