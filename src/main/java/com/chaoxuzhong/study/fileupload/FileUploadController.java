package com.chaoxuzhong.study.fileupload;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotBlank;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@RestController
@RequestMapping("/upload")
@Slf4j
public class FileUploadController {
    public static final String DELIMITER = "/";
    // 绑定文件上传的临时路径
    @Value("${aden.resources-path}")
    private String resourcesPath;

    // 临时文件根路径 - 暂时没临时文件
    private static String tempFileRoot;
    // 正式文件根路径
    private static String resourcesRoot;

    @PostConstruct
    public void init() {
        // 初始化资源根路径
        resourcesRoot = resourcesPath + DELIMITER + "resources";
    }

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd_");

    @PostMapping("/saveOrUpdate")
    public String upload(MultipartFile file,
                         @RequestParam("type") @NotBlank String type,
                         @RequestParam("oldFilePath") String oldFilePath,
                         HttpServletRequest request) {
        deleteOldFileIfExist(oldFilePath);
        return saveNewFile(file, type);

    }

    private void deleteOldFileIfExist(@RequestParam("oldFilePath") String oldFilePath) {
        try {
            File oldFile = new File(oldFilePath);
            if (oldFile.exists()) {
                oldFile.delete();
            }
        } catch (Exception e) {
            log.error("oldFile delete fail", e);
        }
    }

    private String saveNewFile(MultipartFile file, @RequestParam("type") @NotBlank String type) {
        // 创建、验证路径
        String filePathPrefix = resourcesRoot + DELIMITER + type + DELIMITER;
        File folder = new File(filePathPrefix);
        if (!folder.isDirectory()) {
            folder.mkdirs();
        }

        // 对上传的文件重命名，避免文件重名
        String oldName = file.getOriginalFilename();
        String format = sdf.format(new Date());
        // 如 ： 2021-05-25_cefe0811-29a6-4531-b688-1e191e5ea602.jpg
        String newName = format + UUID.randomUUID().toString()
                + oldName.substring(oldName.lastIndexOf("."), oldName.length());
        try {
            // 文件保存
            file.transferTo(new File(folder, newName));

            // 返回上传文件的访问路径
            String filePath = filePathPrefix + newName;
            return filePath;
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }
}

