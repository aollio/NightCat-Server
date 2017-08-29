package com.yemao.files.web

import com.yemao.common.Response
import com.yemao.common.base.BaseController
import com.yemao.utility.Util
import org.springframework.beans.factory.annotation.Value
import org.springframework.core.io.FileSystemResource
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths

@RestController
@RequestMapping("/files")
class FileController : BaseController() {

    @Value("\${files.upload.directory}")
    private lateinit var UPLOADED_FOLDER: String

    @Value("http://\${files.upload.url.prefix}/files/")
    private lateinit var UPLOADED_URL_PREFIX: String

    private val DEFAULT_SUFFIX = ".jpg"

    @PostMapping
    fun upload(@RequestParam("file") file: MultipartFile, suffix: String?): Response {
        logger.info("开始上传文件...")
        var name = Util.uniqueFileName(System.currentTimeMillis().toString())
        name += if (Util.strExist(suffix)) suffix else DEFAULT_SUFFIX

        Files.write(Paths.get(UPLOADED_FOLDER + name), file.bytes)
        return ok(UPLOADED_URL_PREFIX + name)
    }

    @GetMapping(value = "/{filename:.+}")
    fun download(@PathVariable filename: String): FileSystemResource? {
        val file = File(UPLOADED_FOLDER + filename)
        if (!file.exists()) return null
        return FileSystemResource(file)
    }

}