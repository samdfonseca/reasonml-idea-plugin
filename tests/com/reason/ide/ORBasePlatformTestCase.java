package com.reason.ide;

import com.intellij.psi.PsiFile;
import com.intellij.psi.impl.DebugUtil;
import com.intellij.testFramework.fixtures.BasePlatformTestCase;
import com.reason.ide.files.FileBase;
import org.jetbrains.annotations.NotNull;

public abstract class ORBasePlatformTestCase extends BasePlatformTestCase {

    @NotNull
    @SuppressWarnings("UnusedReturnValue")
    protected FileBase configureCode(@NotNull String fileName, @NotNull String code) {
        return configureCode(fileName, code, false);
    }

    @NotNull
    @SuppressWarnings({"SameParameterValue", "WeakerAccess"})
    protected FileBase configureCode(@NotNull String fileName, @NotNull String code, boolean debug) {
        PsiFile file = myFixture.configureByText(fileName, code);
        if (debug) {
            System.out.println("» " + fileName + " " + this.getClass());
            System.out.println(DebugUtil.psiToString(file, true, true));
        }
        return (FileBase) file;
    }

}
