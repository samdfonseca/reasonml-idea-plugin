package com.reason.lang.core.stub.type;

import com.intellij.lang.Language;
import com.intellij.psi.stubs.*;
import com.intellij.util.io.StringRef;
import com.reason.ide.search.index.IndexKeys;
import com.reason.lang.core.psi.PsiLet;
import com.reason.lang.core.psi.impl.PsiLetImpl;
import com.reason.lang.core.stub.PsiLetStub;
import com.reason.lang.core.type.ORTypesUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;

public class PsiLetStubElementType extends IStubElementType<PsiLetStub, PsiLet> {

    public PsiLetStubElementType(@NotNull String name, @Nullable Language language) {
        super(name, language);
    }

    @NotNull
    public PsiLetImpl createPsi(@NotNull PsiLetStub stub) {
        return new PsiLetImpl(ORTypesUtil.getInstance(getLanguage()), stub, this);
    }

    @NotNull
    public PsiLetStub createStub(@NotNull PsiLet psi, StubElement parentStub) {
        return new PsiLetStub(parentStub, this, psi.getName(), psi.getQualifiedName(), psi.getAlias(), psi.isFunction());
    }

    public void serialize(@NotNull PsiLetStub stub, @NotNull StubOutputStream dataStream) throws IOException {
        dataStream.writeName(stub.getName());
        dataStream.writeUTFFast(stub.getQualifiedName());
        dataStream.writeBoolean(stub.isFunction());

        String alias = stub.getAlias();
        dataStream.writeBoolean(alias != null);
        if (alias != null) {
            dataStream.writeUTFFast(stub.getAlias());
        }
    }

    @NotNull
    public PsiLetStub deserialize(@NotNull StubInputStream dataStream, StubElement parentStub) throws IOException {
        StringRef name = dataStream.readName();
        String qname = dataStream.readUTFFast();
        boolean isFunction = dataStream.readBoolean();

        String alias = null;
        boolean isAlias = dataStream.readBoolean();
        if (isAlias) {
            alias = dataStream.readUTFFast();
        }

        return new PsiLetStub(parentStub, this, name, qname, alias, isFunction);
    }

    public void indexStub(@NotNull PsiLetStub stub, @NotNull IndexSink sink) {
        String name = stub.getName();
        if (name != null) {
            sink.occurrence(IndexKeys.LETS, name);
        }

        String fqn = stub.getQualifiedName();
        if (fqn != null) {
            sink.occurrence(IndexKeys.LETS_FQN, fqn.hashCode());
        }
    }

    @NotNull
    public String getExternalId() {
        return getLanguage() + "." + super.toString();
    }
}
