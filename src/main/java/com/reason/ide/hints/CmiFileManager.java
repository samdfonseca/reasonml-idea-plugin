package com.reason.ide.hints;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.reason.Platform;
import com.reason.bs.BucklescriptProjectComponent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.nio.file.Path;

class CmiFileManager {

    @NotNull
    static String toRelativeSourceName(@NotNull Path relativeCmi) {
        return relativeCmi.toString().replace(".cmi", ".re");
    }

    @Nullable
    static VirtualFile toSource(@NotNull Project project, @NotNull Path relativeCmi) {
        /* ml if re not found ?? */
        String relativeSource = toRelativeSourceName(relativeCmi);
        return Platform.findBaseRoot(project).findFileByRelativePath(relativeSource);
    }

    @Nullable
    static VirtualFile fromSource(@NotNull Project project, @NotNull VirtualFile sourceFile) {
        String namespace = BucklescriptProjectComponent.getInstance(project).getNamespace();
        String cmiFilename = Platform.removeProjectDir(project, sourceFile).replace(sourceFile.getPresentableName(), sourceFile.getNameWithoutExtension() + (namespace.isEmpty() ? "" : "-" + namespace) + ".cmi");

        return Platform.findBaseRoot(project).findFileByRelativePath("lib/bs/" + cmiFilename);
    }
}