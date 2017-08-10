package com.nightcat.files.web;

import com.nightcat.common.Response;
import com.nightcat.common.base.BaseController;
import com.nightcat.common.utility.Assert;
import com.nightcat.common.utility.Util;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static com.nightcat.common.constant.HttpStatus.*;

@RestController
@RequestMapping("/files")
public class FilesController extends BaseController {

    @Value("${files.upload.directory}")
    private String UPLOADED_FOLDER;

    @Value("http://${files.upload.url.prefix}:${server.port}/files/")
    private String UPLOADED_URL_PREFIX;


    /**
     * 上传文件内容
     */
    @PostMapping
    public Response upload(@RequestParam("file") MultipartFile file) {

        Assert.isFalse(file.isEmpty(), BAD_REQUEST, "文件为空");

        try {
            // Get the file and log it somewhere
            byte[] bytes = file.getBytes();

            String fileName = Util.uniqueFileName(file.getOriginalFilename());
            Path path = Paths.get(UPLOADED_FOLDER + fileName);
            Files.write(path, bytes);

            return Response.ok(UPLOADED_URL_PREFIX + fileName);

        } catch (IOException e) {
            e.printStackTrace();
            return Response.error();
        }

    }

    /**
     * 读取图片内容.
     */
    @GetMapping(
            value = "/{file_name:.+}",
            produces = {MediaType.IMAGE_JPEG_VALUE,
                    MediaType.IMAGE_GIF_VALUE,
                    MediaType.IMAGE_PNG_VALUE,
                    MediaType.ALL_VALUE})
    public FileSystemResource download(@PathVariable("file_name") String fileName) {
        File file = new File(UPLOADED_FOLDER + fileName);
        if (!file.exists()) {
            return null;
        }
        return new FileSystemResource(file);
    }

}
