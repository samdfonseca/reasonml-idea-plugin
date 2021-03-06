package com.reason.lang.ocamlyacc;

import com.intellij.navigation.ItemPresentation;
import com.reason.Icons;
import com.reason.lang.core.psi.ocamlyacc.OclYaccDeclaration;
import com.reason.lang.core.psi.ocamlyacc.OclYaccHeader;
import com.reason.lang.core.psi.ocamlyacc.OclYaccRule;
import com.reason.lang.core.psi.ocamlyacc.OclYaccTrailer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class OclYaccPsiImplUtil {
    private OclYaccPsiImplUtil() {
    }

    @NotNull
    public static ItemPresentation getPresentation(@NotNull OclYaccHeader it) {
        return new ItemPresentation() {
            @Override
            public String getPresentableText() {
                return "Header";
            }

            @Override
            public String getLocationString() {
                return null;
            }

            @Override
            public Icon getIcon(boolean unused) {
                return Icons.OBJECT;
            }
        };
    }

    @NotNull
    public static ItemPresentation getPresentation(@NotNull OclYaccDeclaration it) {
        return new ItemPresentation() {
            @Override
            public String getPresentableText() {
                return it.getFirstChild().getText();
            }

            @Nullable
            @Override
            public String getLocationString() {
                return null;
            }

            @Override
            public Icon getIcon(boolean unused) {
                return Icons.TYPE;
            }
        };
    }

    @NotNull
    public static ItemPresentation getPresentation(@NotNull OclYaccRule it) {
        return new ItemPresentation() {
            @Override
            public String getPresentableText() {
                return it.getFirstChild().getText();
            }

            @Nullable
            @Override
            public String getLocationString() {
                return null;
            }

            @Override
            public Icon getIcon(boolean unused) {
                return Icons.FUNCTION;
            }
        };
    }

    @NotNull
    public static ItemPresentation getPresentation(@NotNull OclYaccTrailer it) {
        return new ItemPresentation() {
            @Override
            public String getPresentableText() {
                return "Trailer";
            }

            @Override
            public String getLocationString() {
                return null;
            }

            @Override
            public Icon getIcon(boolean unused) {
                return Icons.OBJECT;
            }
        };
    }

}
