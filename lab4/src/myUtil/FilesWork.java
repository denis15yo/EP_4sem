package myUtil;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FilenameFilter;

public class FilesWork {
    public static File openFile(JFrame owner, FilenameFilter filenameFilter){
        FileDialog dlg = new FileDialog(owner, "Открыть", FileDialog.LOAD);
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
