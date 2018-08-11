package com.file.controller;

import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping(value = "/user")
public class FileController {
    Logger logger = Logger.getLogger(FileController.class);

    @RequestMapping(value = "/useraddsave.html",method = RequestMethod.POST)
    public String fileUploadFile(@RequestParam(value = "a_idPicPath",required = false) MultipartFile attach,
                               HttpSession session,
                               HttpServletRequest request,
                               @RequestParam(value = "userNameA",required = false) String name){
        String idPicPath = null;
        System.out.println(name);
        System.out.println(attach.isEmpty());
        if (!attach.isEmpty()){
            String path = request.getSession().getServletContext().getRealPath("statics"+ File.separator + "uploadfiles");
            logger.info("uploadFile path+============"+path);
            System.out.println("uploadFile path+============"+path);
            String oldFileName = attach.getOriginalFilename();//原文件名
            logger.info("uploadFile oldFileName==========="+oldFileName);
            String prefix = FilenameUtils.getExtension(oldFileName);
            logger.debug("uploadFile prefix=============="+prefix);
            int filesize = 500000;
            logger.debug("uploadFile size==========="+attach.getSize());
            if (attach.getSize() > filesize){
                request.setAttribute("uploadFileError","上传大小不得超过500kb");
                return "userAdd";
            }else if (prefix.equalsIgnoreCase("jpg")
                    || prefix.equalsIgnoreCase("png")
                    || prefix.equalsIgnoreCase("jpeg")
                    || prefix.equalsIgnoreCase("pneg")){
                String fileName = System.currentTimeMillis()+RandomUtils.nextInt(1000000)+"_Personal.jpg";
                logger.debug("new fileName==========="+attach.getName());
                File targetFile = new File(path,fileName);
                if (!targetFile.exists()){
                    targetFile.mkdirs();
                }
                try {
                    attach.transferTo(targetFile);
                    System.out.println("保存成功");
                } catch (IOException e) {
                    e.printStackTrace();
                    request.setAttribute("uploadFileError","上传失败");
                    return "useradd";
                }
                idPicPath = path+File.separator+fileName;

            }else{
                request.setAttribute("uploadFileError","上传图片格式不正确");
                return "useradd";
            }
        }
        request.setAttribute("uploadFileError","保存失败");
        return "useradd";
   }
}
