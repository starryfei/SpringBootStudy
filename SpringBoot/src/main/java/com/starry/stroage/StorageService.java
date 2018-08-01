package com.starry.stroage;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;

/**
 *
 */
public interface StorageService {
    void init();

    void store(MultipartFile file);

    /**
     * java8 流的初始化
     * @return
     */
    Stream<Path> loadAll();

    Path load(String filename);

    Resource loadAsResource(String filename);

    void deleteAll();
}
