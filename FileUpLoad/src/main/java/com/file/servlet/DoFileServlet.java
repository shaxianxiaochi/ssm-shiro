package com.file.servlet;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

@WebServlet(urlPatterns = "/FileUpLoad")
public class DoFileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String fileName = "";
        String filePath = request.getServletContext().getRealPath("/upload/images");
        File dir = new File(filePath);
        if (!dir.exists()){
            dir.mkdirs();
        }
        ServletFileUpload fileUpload = null;
        DiskFileItemFactory factory = null;
        try {
            factory = new DiskFileItemFactory();
            factory.setSizeThreshold(1024*1024*1024*10);
            File tempFile = new File(filePath,"/temp");
            if (!tempFile.exists()){
                tempFile.mkdirs();
            }
            factory.setRepository(tempFile);
            fileUpload = new ServletFileUpload(factory);
            fileUpload.setSizeMax(1024*1024*10);
            boolean isMultipart = fileUpload.isMultipartContent(request);
            if (isMultipart){
                List<FileItem> list = fileUpload.parseRequest(request);
                Iterator<FileItem> iterator = list.iterator();
                while (iterator.hasNext()){
                    FileItem fileItem = iterator.next();
                    if (fileItem.isFormField()){
                        String fieldName = fileItem.getFieldName();
                        if ("userName".equals(fileName)){
                            System.out.println(fileItem.getString("UTF-8"));
                        }
                    }else{
                        String fileNames = fileItem.getName();
                        String ext = fileNames.substring(fileNames.lastIndexOf("."));
                        List<String> fileType = Arrays.asList("jpg","gif","png");
                        if (fileType.contains(ext)){
                            System.out.println("文件类型错误");
                        } else {
                            File saveFile = new File(filePath,fileNames);
                            System.out.println();
                            fileItem.write(saveFile);
                            System.out.println("文件保存成功！");
                        }
                    }
                }
            }else{
                System.out.println("提交的普通表单！");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
    }
}
