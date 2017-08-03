package com.nightcat.files.web;

import com.nightcat.common.Response;
import com.nightcat.common.utility.Assert;
import com.nightcat.common.utility.Util;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static com.nightcat.common.constant.HttpStatus.*;

@RestController
@RequestMapping("/files")
public class FileUploadController {

    @Value("${files.upload.directory}")
    private String UPLOADED_FOLDER;

    @Value("${files.upload.url.prefix}")
    private String UPLOADED_URL_PREFIX;


    @PostMapping
    public Response singleFileUpload(@RequestParam("file") MultipartFile file) {

        Assert.isFalse(file.isEmpty(), BAD_REQUEST, "文件为空");

        try {

            // Get the file and save it somewhere
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


}
