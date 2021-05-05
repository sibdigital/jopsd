package ru.sibdigital.jopsd.service;

import java.io.File;
import java.util.Collection;

public interface FileService {
    File[] getSubfolders(String dir);
    Collection<File> getFiles(String path);
    Collection<File> getFiles(File dir);
}
