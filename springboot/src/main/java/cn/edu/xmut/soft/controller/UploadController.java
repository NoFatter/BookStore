package cn.edu.xmut.soft.controller;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

import cn.edu.xmut.utils.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/soft/bookImg")
public class UploadController {

    String base = "D:\\springboot\\project\\vueproject\\src\\assets\\images\\book_img\\"; // 不要使用项目内的目录

    @GetMapping("/upload")
    public String upload() {
        return "upload";
    }
    @PostMapping("/save")
    @ResponseBody
    public Result save(MultipartFile file, Map<String, Object> map) throws IOException {
        Result result = new Result();
        if (file.getSize() > 0) {
            String origName = file.getOriginalFilename();
            String saveName = UUID.randomUUID() + "." + origName.split("[.]")[1];
            File files = new File(base + saveName);
            file.transferTo(files);
            result.setData(saveName);
            return result;
        }
        return result;
    }
}