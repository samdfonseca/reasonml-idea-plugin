package com.reason.build.bs;

import com.intellij.openapi.editor.Document;
import com.reason.build.Compiler;
import com.reason.build.bs.compiler.BsCompiler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface Bucklescript extends Compiler {
    boolean isDependency(String path);

    @Nullable
    BsCompiler getCompiler();

    @Nullable
    BsCompiler getOrCreateCompiler();

    @NotNull
    String getNamespace();

    void refmt(@NotNull String format, @NotNull Document document);

    boolean isRefmtOnSaveEnabled();
}