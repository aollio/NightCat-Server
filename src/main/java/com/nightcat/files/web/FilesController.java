package com.nightcat.files.web;

import com.nightcat.common.Response;
import com.nightcat.common.base.BaseController;
import com.nightcat.utility.Assert;
import com.nightcat.utility.Util;
import com.nightcat.vo.VoService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

import static com.nightcat.common.constant.HttpStatus.*;

@RestController
@RequestMapping("/files")
public class FilesController extends BaseController {

    @Value("${files.upload.directory}")
    private String UPLOADED_FOLDER;

    @Value("http://${files.upload.url.prefix}/files/")
    private String UPLOADED_URL_PREFIX;


    /**
     * 上传文件内容
     */
    @PostMapping
    public Response upload(@RequestParam("file") String filestring) throws IOException {

        logger.info("开始上传文件");
        Assert.isFalse(filestring.isEmpty(), BAD_REQUEST, "文件为空");

        // Get the file and log it somewhere
        byte[] bytes = Base64.getDecoder().decode(filestring);

        String fileName = Util.uniqueFileName(String.valueOf(System.currentTimeMillis()) + ".jpg");
        logger.info("上传文件名称:" + fileName);
        Path path = Paths.get(UPLOADED_FOLDER + fileName);
        Files.write(path, bytes);

        return Response.ok(UPLOADED_URL_PREFIX + fileName);

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

    @Override
    protected VoService getVoService() {
        return null;
    }
}
