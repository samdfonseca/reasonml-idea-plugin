package com.reason.lang.core.psi;

public class PsiInferredTypeUtil {
    public static String getTypeInfo(PsiNamedElement expression) {
        if (expression instanceof PsiLet) {
            return ((PsiLet) expression).getInferredType();
        } else if (expression instanceof PsiType) {
            return ((PsiType) expression).getTypeInfo();
        }

        return "";
    }
}