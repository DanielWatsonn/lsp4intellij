// FileUtils.java
package org.wso2.lsp4intellij.utils;

import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.fileEditor.FileEditor;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;

public class FileUtils {

    public static Editor editorFromVirtualFile(VirtualFile file, Project project) {
        FileEditor[] editors = FileEditorManager.getInstance(project).getAllEditors(file);
        for (FileEditor editor : editors) {
            if (editor instanceof Editor) {
                return (Editor) editor;
            }
        }
        return null;
    }
}

// FileUtilsTest.java
package org.wso2.lsp4intellij.utils;

import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.fileEditor.FileEditor;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest(FileEditorManager.class)
public class FileUtilsTest {

    @Mock
    private VirtualFile file;

    @Mock
    private Project project;

    @Test
    public void testEditorFromVirtualFile() {
        // Mocking necessary objects
        Editor editor = Mockito.mock(Editor.class);
        FileEditorManager fileEditorManager = Mockito.mock(FileEditorManager.class);

        // Stubbing behaviors
        Mockito.when(fileEditorManager.getAllEditors(file)).thenReturn(new FileEditor[]{Mockito.mock(FileEditor.class)});
        PowerMockito.mockStatic(FileEditorManager.class);
        PowerMockito.when(FileEditorManager.getInstance(project)).thenReturn(fileEditorManager);

        // Testing the method
        Assert.assertEquals(editor, FileUtils.editorFromVirtualFile(file, project));
    }
}
