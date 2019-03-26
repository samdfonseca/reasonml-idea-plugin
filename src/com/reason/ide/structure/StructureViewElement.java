package com.reason.ide.structure;

import com.intellij.ide.structureView.StructureViewTreeElement;
import com.intellij.ide.util.treeView.smartTree.SortableTreeElement;
import com.intellij.ide.util.treeView.smartTree.TreeElement;
import com.intellij.navigation.ItemPresentation;
import com.intellij.navigation.NavigationItem;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.PsiNamedElement;
import com.reason.ide.files.FileBase;
import com.reason.lang.core.ORUtil;
import com.reason.lang.core.psi.*;
import com.reason.lang.core.psi.ocamlyacc.OclYaccHeader;
import com.reason.lang.core.psi.ocamlyacc.OclYaccTrailer;
import com.reason.lang.ocamlyacc.OclYaccTypes;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class StructureViewElement implements StructureViewTreeElement, SortableTreeElement {
    private final PsiElement m_element;

    StructureViewElement(PsiElement element) {
        m_element = element;
    }

    @Override
    public Object getValue() {
        return m_element;
    }

    @Override
    public void navigate(boolean requestFocus) {
        if (m_element instanceof NavigationItem) {
            ((NavigationItem) m_element).navigate(requestFocus);
        }
    }

    @Override
    public boolean canNavigate() {
        return m_element instanceof NavigationItem && ((NavigationItem) m_element).canNavigate();
    }

    @Override
    public boolean canNavigateToSource() {
        return m_element instanceof NavigationItem && ((NavigationItem) m_element).canNavigateToSource();
    }

    @NotNull
    @Override
    public String getAlphaSortKey() {
        String name = null;

        if (m_element instanceof PsiNamedElement) {
            name = ((PsiNamedElement) m_element).getName();
        } else if (m_element instanceof PsiOpen) {
            name = ((PsiOpen) m_element).getQualifiedName();
        } else if (m_element instanceof PsiInclude) {
            name = ((PsiInclude) m_element).getQualifiedName();
        }

        return name == null ? "" : name;
    }

    @NotNull
    @Override
    public ItemPresentation getPresentation() {
        if (m_element instanceof NavigationItem) {
            ItemPresentation presentation = ((NavigationItem) m_element).getPresentation();
            if (presentation != null) {
                return presentation;
            }
        }
        return new ItemPresentation() {
            @Nullable
            @Override
            public String getPresentableText() {
                return "Unknown presentation for element " + m_element.getText();
            }

            @Nullable
            @Override
            public String getLocationString() {
                return null;
            }

            @Nullable
            @Override
            public Icon getIcon(boolean unused) {
                return null;
            }
        };
    }

    @NotNull
    @Override
    public TreeElement[] getChildren() {
        if (m_element instanceof FileBase) {
            List<TreeElement> treeElements = new ArrayList<>();
            m_element.acceptChildren(new ElementVisitor(treeElements));
            return treeElements.toArray(new TreeElement[0]);
        } else if (m_element instanceof PsiInnerModule) {
            List<TreeElement> treeElements = buildModuleStructure((PsiInnerModule) m_element);
            if (!treeElements.isEmpty()) {
                return treeElements.toArray(new TreeElement[0]);
            }
        } else if (m_element instanceof PsiFunctor) {
            List<TreeElement> treeElements = buildFunctorStructure((PsiFunctor) m_element);
            if (!treeElements.isEmpty()) {
                return treeElements.toArray(new TreeElement[0]);
            }
        } else if (m_element instanceof PsiClass) {
            List<TreeElement> treeElements = buildClassStructure((PsiClass) m_element);
            if (!treeElements.isEmpty()) {
                return treeElements.toArray(new TreeElement[0]);
            }
        } else if (m_element instanceof OclYaccHeader) {
            List<TreeElement> treeElements = buildYaccHeaderStructure((OclYaccHeader) m_element);
            if (!treeElements.isEmpty()) {
                return treeElements.toArray(new TreeElement[0]);
            }
        } else if (m_element instanceof OclYaccTrailer) {
            List<TreeElement> treeElements = buildYaccTrailerStructure((OclYaccTrailer) m_element);
            if (!treeElements.isEmpty()) {
                return treeElements.toArray(new TreeElement[0]);
            }
        }

        return EMPTY_ARRAY;
    }

    @NotNull
    private List<TreeElement> buildModuleStructure(PsiInnerModule moduleElement) {
        List<TreeElement> treeElements = new ArrayList<>();

        PsiElement rootElement = moduleElement.getSignature();
        if (rootElement == null) {
            rootElement = moduleElement.getBody();
        }

        if (rootElement != null) {
            rootElement.acceptChildren(new ElementVisitor(treeElements));
        }

        return treeElements;
    }

    @NotNull
    private List<TreeElement> buildFunctorStructure(PsiFunctor functor) {
        List<TreeElement> treeElements = new ArrayList<>();

        PsiElement binding = functor.getBinding();
        if (binding != null) {
            binding.acceptChildren(new ElementVisitor(treeElements));
        }

        return treeElements;
    }

    @NotNull
    private List<TreeElement> buildClassStructure(@NotNull PsiClass classElement) {
        List<TreeElement> treeElements = new ArrayList<>();

        PsiElement rootElement = classElement.getClassBody();
        if (rootElement != null) {
            rootElement.acceptChildren(new ElementVisitor(treeElements));
        }

        return treeElements;
    }

    private List<TreeElement> buildYaccHeaderStructure(OclYaccHeader root) {
        List<TreeElement> treeElements = new ArrayList<>();

        PsiElement rootElement = ORUtil.findImmediateFirstChildOfType(root, OclYaccTypes.OCAML_LAZY_NODE);
        if (rootElement != null) {
            rootElement.acceptChildren(new ElementVisitor(treeElements));
        }

        return treeElements;
    }

    private List<TreeElement> buildYaccTrailerStructure(OclYaccTrailer root) {
        List<TreeElement> treeElements = new ArrayList<>();

        PsiElement rootElement = ORUtil.findImmediateFirstChildOfType(root, OclYaccTypes.OCAML_LAZY_NODE);
        if (rootElement != null) {
            rootElement.acceptChildren(new ElementVisitor(treeElements));
        }

        return treeElements;
    }

    static class ElementVisitor extends PsiElementVisitor {
        private final List<TreeElement> m_treeElements;

        ElementVisitor(List<TreeElement> elements) {
            m_treeElements = elements;
        }

        @Override
        public void visitElement(PsiElement element) {
            if (element instanceof PsiStructuredElement) {
                if (((PsiStructuredElement) element).canBeDisplayed()) {
                    m_treeElements.add(new StructureViewElement(element));
                }
            }
        }
    }

}
