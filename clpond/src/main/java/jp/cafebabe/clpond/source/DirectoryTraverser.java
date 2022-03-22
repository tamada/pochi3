package jp.cafebabe.clpond.source;

import io.vavr.control.Try;
import jp.cafebabe.clpond.entities.PathHelper;

import java.nio.file.DirectoryStream;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.spi.FileSystemProvider;
import java.util.*;

class DirectoryTraverser {
    private static final DirectoryStream.Filter<Path> FILTER = new EveryFileAcceptFilter();

    public List<Path> traverse(Path... paths){
        FileSystem system = FileSystems.getDefault();
        return traverse(system.provider(), new ArrayList<>(), paths);
    }

    public List<Path> traverse(FileSystemProvider provider, Path... paths){
        return traverse(provider, new ArrayList<>(), paths);
    }

    private List<Path> traverse(FileSystemProvider provider, List<Path> list, Path... paths){
        Arrays.stream(paths)
                .forEach(path -> traverse(provider, list, path));
        return Collections.unmodifiableList(list);
    }

    private List<Path> traverse(FileSystemProvider provider, List<Path> list, Path path){
        Try.withResources(() -> provider.newDirectoryStream(path, FILTER))
                .of(stream -> {
                    stream.forEach(p -> traverseDirectory(provider, list, p));
                    return false;
                });
        return list;
    }

    private void traverseDirectory(FileSystemProvider provider, List<Path> list, Path path){
        Optional<BasicFileAttributes> optional = PathHelper.readAttributes(path, provider);
        optional.ifPresent(attr -> doTraverse(provider, list, path, attr));
    }

    private void doTraverse(FileSystemProvider provider, List<Path> list, Path path, BasicFileAttributes attr){
        if(attr.isDirectory())
            traverse(provider, list, path);
        else
            list.add(path);
    }
}
