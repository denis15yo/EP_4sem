package by.bsu.util;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FilenameFilter;

public class FilesWork {
    public static File showFileDialog(JFrame owner, FilenameFilter filenameFilter, int mode){
        FileDialog dlg = new FileDialog(owner, "Открыть", mode);
        dlg.setDirectory(System.getProperty("user.dir"));
        dlg.setMultipleMode(false);
        dlg.setFilenameFilter(filenameFilter);
        dlg.setVisible(true);
        if(dlg.getFile() != null){
            return dlg.getFiles()[0];
        }
        else{
            return null;
        }
    }
}
