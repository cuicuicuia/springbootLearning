package com.calm_seas.demo.control;

import com.calm_seas.demo.pojo.Result;
import com.calm_seas.demo.utils.AliOssUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
public class FileUploadContrller {
    @PostMapping("/upload")
    public Result<String> upload(MultipartFile file) throws Exception {
        String originFilename=file.getOriginalFilename();
        //file.transferTo(new File("C:\\Users\\cuipingping\\Desktop\\新建文件夹 (2)\\"+originFilename));
        String filename= UUID.randomUUID().toString()+originFilename.substring(originFilename.lastIndexOf("."));

        String url= AliOssUtil.upload(filename,file.getInputStream());
        return Result.success(url);
    }
}
