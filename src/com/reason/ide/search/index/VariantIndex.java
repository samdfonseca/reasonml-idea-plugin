package com.reason.ide.search.index;

import com.intellij.psi.stubs.StringStubIndexExtension;
import com.intellij.psi.stubs.StubIndexKey;
import com.reason.lang.core.psi.PsiVariantDeclaration;
import org.jetbrains.annotations.NotNull;

public class VariantIndex extends StringStubIndexExtension<PsiVariantDeclaration> {
    private static final int VERSION = 1;
    private static final VariantIndex INSTANCE = new VariantIndex();

    @NotNull
    public static VariantIndex getInstance() {
        return INSTANCE;
    }


    @Override
    public int getVersion() {
        return super.getVersion() + VERSION;
    }

    @NotNull
    @Override
    public StubIndexKey<String, PsiVariantDeclaration> getKey() {
        return IndexKeys.VARIANTS;
    }
}
