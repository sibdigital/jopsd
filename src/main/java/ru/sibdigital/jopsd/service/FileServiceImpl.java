package ru.sibdigital.jopsd.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.DirectoryFileFilter;
import org.apache.commons.io.filefilter.RegexFileFilter;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Collection;

@Service
@Slf4j
public class FileServiceImpl extends SuperServiceImpl implements FileService {
    @Override
    public File[] getSubfolders(String dir) {
        File[] dirs = new File(dir).listFiles(File::isDirectory);
        return dirs;
    }

    @Override
    public Collection<File> getFiles(String path) {
        Collection<File> files = null;
        try {
            files = FileUtils.listFiles(new File(path),
                    new RegexFileFilter("^(.*?)"), DirectoryFileFilter.DIRECTORY);
        } catch (Exception e) {
            logError(e);
        }

        return files;
    }

    @Override
    public Collection<File> getFiles(File dir) {
        Collection<File> files = null;
        try {
            files = FileUtils.listFiles(dir,
                    new RegexFileFilter("^(.*?)"), DirectoryFileFilter.DIRECTORY);
        } catch (Exception e) {
            logError(e);
        }

        return files;
    }
}
