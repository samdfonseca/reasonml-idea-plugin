package com.reason.lang.ocaml;

import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiQualifiedNamedElement;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.util.containers.ArrayListSet;
import com.reason.ide.files.FileBase;
import com.reason.lang.BaseQNameFinder;
import com.reason.lang.core.psi.*;
import org.jetbrains.annotations.NotNull;

import java.util.EnumSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.reason.lang.QNameFinder.Includes.containingFile;
import static com.reason.lang.QNameFinder.Includes.includedModules;

public class OclQNameFinder extends BaseQNameFinder {

    // Find the expression paths
    @NotNull
    public Set<String> extractPotentialPaths(@NotNull PsiElement element, @NotNull EnumSet<Includes> include, boolean addTypes) {
        Set<String> qualifiedNames = new ArrayListSet<>();

        String path = extractPathName(element, OclTypes.INSTANCE);
        String pathExtension = path.isEmpty() ? "" : "." + path;

        if (!path.isEmpty()) {
            qualifiedNames.add(path);
        }

        // Walk backward until top of the file is reached, trying to find local opens and opens/includes
        PsiElement item = element;
        while (item != null) {
            if (100 < qualifiedNames.size()) {
                break; // There must be a problem with the parser
            }

            if ((item instanceof FileBase) && include.contains(containingFile)) {
                qualifiedNames.add(((FileBase) item).asModuleName());
                break;
            } else if (item instanceof PsiOpen || (include.contains(includedModules) && item instanceof PsiInclude)) {
                String openName = ((PsiQualifiedElement) item).getQualifiedName();
                // Add open value to all previous elements
                List<String> withOpenQualifier = qualifiedNames.stream().map(name -> openName + pathExtension).collect(Collectors.toList());
                qualifiedNames.addAll(withOpenQualifier);
                qualifiedNames.add(openName + pathExtension);
            } else if (item instanceof PsiInnerModule) {
                if (path.equals(((PsiInnerModule) item).getName())) {
                    qualifiedNames.add(((FileBase) element.getContainingFile()).asModuleName() + pathExtension);
                }
            } else if (item instanceof PsiType && addTypes) {
                qualifiedNames.add(((PsiType) item).getQualifiedName() + pathExtension);
            } else if (item instanceof PsiLet) {
                qualifiedNames.add(((PsiLet) item).getQualifiedPath());
            } else if (item instanceof PsiFunction) {
                PsiQualifiedNamedElement parent = PsiTreeUtil.getParentOfType(item, PsiQualifiedNamedElement.class);
                if (parent != null) {
                    String parentQName = parent.getQualifiedName();
                    // Register all parameters of function
                    for (PsiParameter parameter : ((PsiFunction) item).getParameters()) {
                        qualifiedNames.add(parentQName + "[" + parameter.getName() + "]");
                    }
                }
            }

            PsiElement prevItem = item.getPrevSibling();
            if (prevItem == null) {
                PsiElement parent = item.getParent();
                if (parent instanceof PsiLocalOpen) {
                    qualifiedNames.add(((PsiLocalOpen) parent).getName());
                }
                item = parent;
            } else {
                item = prevItem;
            }
        }

        qualifiedNames.add("Pervasives");

        return qualifiedNames;
    }

}
