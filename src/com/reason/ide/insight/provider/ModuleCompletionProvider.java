package com.reason.ide.insight.provider;

import com.intellij.codeInsight.completion.CompletionResultSet;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiQualifiedNamedElement;
import com.intellij.psi.impl.source.tree.LeafPsiElement;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.util.PsiIconUtil;
import com.reason.Log;
import com.reason.ide.IconProvider;
import com.reason.ide.files.FileBase;
import com.reason.ide.search.FileModuleIndexService;
import com.reason.ide.search.IndexedFileModule;
import com.reason.ide.search.PsiFinder;
import com.reason.lang.core.ModulePath;
import com.reason.lang.core.psi.PsiInnerModule;
import com.reason.lang.core.psi.PsiUpperSymbol;
import com.reason.lang.core.type.ORTypes;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class ModuleCompletionProvider {
    private static final Log LOG = Log.create("insight.module");

    public static void addCompletions(@NotNull ORTypes types, @NotNull PsiElement element, @NotNull CompletionResultSet resultSet) {
        LOG.debug("MODULE expression completion");

        Project project = element.getProject();
        GlobalSearchScope scope = GlobalSearchScope.allScope(project);

        // Compute module path (all module names before the last dot)
        ModulePath modulePath = computePathFromPsi(types, element);
        if (LOG.isDebugEnabled()) {
            LOG.debug("  module path", modulePath.toString());
        }

        PsiFinder psiFinder = PsiFinder.getInstance(project);
        if (modulePath.isEmpty()) {
            // First module to complete, use the list of files
            Collection<IndexedFileModule> files = FileModuleIndexService.getService().getFilesWithoutNamespace(project, scope);
            for (IndexedFileModule indexedFile : files) {
                resultSet.addElement(LookupElementBuilder.
                        create(indexedFile.getModuleName()).
                        withTypeText(indexedFile.getPath()).
                        withIcon(IconProvider.getFileModuleIcon(indexedFile))
                );
            }
        } else {
            PsiQualifiedNamedElement foundModule = psiFinder.findModuleFromQn(modulePath.toString());
            if (foundModule != null) {
                LOG.debug("  Found module", foundModule);
                Collection<PsiInnerModule> modules = foundModule instanceof FileBase ? ((FileBase) foundModule).getModules() : ((PsiInnerModule) foundModule).getModules();
                for (PsiInnerModule module : modules) {
                    resultSet.addElement(LookupElementBuilder.
                            create(module).
                            withIcon(PsiIconUtil.getProvidersIcon(module, 0))
                    );
                }
            }
        }

    }

    @NotNull
    private static ModulePath computePathFromPsi(@NotNull ORTypes types, @Nullable PsiElement cursorElement) {
        List<PsiUpperSymbol> moduleNames = new ArrayList<>();
        PsiElement previousLeaf = cursorElement == null ? null : PsiTreeUtil.prevLeaf(cursorElement);
        if (previousLeaf != null) {
            IElementType previousElementType = previousLeaf.getNode().getElementType();
            while (previousElementType == types.DOT || previousElementType == types.UIDENT) {
                if (previousElementType == types.UIDENT) {
                    assert previousLeaf != null;
                    moduleNames.add((PsiUpperSymbol) ((LeafPsiElement) previousLeaf.getNode()).getParent());
                }
                previousLeaf = previousLeaf == null ? null : PsiTreeUtil.prevLeaf(previousLeaf);
                previousElementType = previousLeaf == null ? null : previousLeaf.getNode().getElementType();
            }
        }
        Collections.reverse(moduleNames);
        return new ModulePath(moduleNames);
    }
}
