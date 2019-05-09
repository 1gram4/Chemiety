package cn.kastner.chemiety.controller;

import cn.kastner.chemiety.domain.FileType;
import cn.kastner.chemiety.domain.UploadFile;
import cn.kastner.chemiety.exception.FileException;
import cn.kastner.chemiety.repository.UploadFileRepository;
import cn.kastner.chemiety.service.FileService;
import cn.kastner.chemiety.util.NetResult;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1")
public class FileController {


    final private UploadFileRepository uploadFileRepository;

    final private NetResult netResult;

    final private FileService fileService;

    @Autowired
    public FileController(UploadFileRepository uploadFileRepository, NetResult netResult, FileService fileService) {
        this.uploadFileRepository = uploadFileRepository;
        this.netResult = netResult;
        this.fileService = fileService;
    }


    @RequestMapping(value = "/admin/uploadEduFile")
    public NetResult upload2(@RequestParam MultipartFile file, @RequestParam int type, HttpSession session) {
        NetResult result = new NetResult();// 构建源文件
        String path = session.getServletContext().getRealPath("/" + type) + "/";
        System.out.println("开始执行文件上传");
        System.out.println(file.getOriginalFilename());
        String originalFilename = file.getOriginalFilename();
        String extension = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);// 扩展名
        System.out.println(extension);
        if (!file.isEmpty()) {
            try {
                // 目标文件不存在就创建
                File tarfile = new File(path);
                if (!tarfile.exists()) {
                    tarfile.mkdirs();
                }
                System.out.println("fileName = " + file.getOriginalFilename());
                System.out.println(path);
                try (FileOutputStream out = new FileOutputStream(path + file.getOriginalFilename())) {
                    System.out.println(path + file.getOriginalFilename());
                    // 目标文件创建输出流
                    out.write(file.getBytes());
                    out.flush();
                    out.close();
                }
                FileType classType = null;
                if (0 == type) {
                    classType = FileType.SLIDE;
                } else if (1 == type) {
                    classType = FileType.DOCUMENT;
                }
                String realpath = "/" + type + "/" + file.getOriginalFilename();
                UploadFile uploadFile = uploadFileRepository.findByNameAndType(originalFilename, classType);
                if (uploadFile != null) {
                    uploadFile.setDate(new Date());
                } else {
                    uploadFile = new UploadFile();
                    uploadFile.setName(originalFilename);
                    uploadFile.setDate(new Date());
                    uploadFile.setType(classType);
                    uploadFile.setPath(realpath);
                    uploadFileRepository.save(uploadFile);
                }
                result.status = 0;
                result.result = realpath;
            } catch (Exception e) {
                e.printStackTrace();
                result.status = -1;
                result.result = "上传出错";
            }
        } else {
            System.out.println("文件为空");
            result.status = -1;
            result.result = "文件为空";
        }
        return result;
    }

    @RequestMapping(value = "/admin/uploadShowFile")
    public NetResult upload2(@RequestParam MultipartFile file, @RequestParam String author, HttpSession session) {
        NetResult result = new NetResult();// 构建源文件
        String path = session.getServletContext().getRealPath("/" + 2) + "/";
        System.out.println("开始执行文件上传");
        System.out.println(file.getOriginalFilename());
        String originalFilename = file.getOriginalFilename();
        String extension = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);// 扩展名
        System.out.println(extension);
        if (!file.isEmpty()) {
            try {
                // 目标文件不存在就创建
                File tarfile = new File(path);
                if (!tarfile.exists()) {
                    tarfile.mkdirs();
                }
                System.out.println("fileName = " + file.getOriginalFilename());
                System.out.println(path);
                try (FileOutputStream out = new FileOutputStream(path + file.getOriginalFilename())) {
                    System.out.println(path + file.getOriginalFilename());
                    // 目标文件创建输出流
                    out.write(file.getBytes());
                    out.flush();
                    out.close();
                }
                FileType classType = FileType.SHOW;
                String realpath = "/" + 2 + "/" + file.getOriginalFilename();
                UploadFile uploadFile = uploadFileRepository.findByNameAndType(originalFilename, classType);
                if (uploadFile != null) {
                    uploadFile.setDate(new Date());
                } else {
                    uploadFile = new UploadFile();
                    uploadFile.setName(originalFilename);
                    uploadFile.setDate(new Date());
                    uploadFile.setType(classType);
                    uploadFile.setPath(realpath);
                    uploadFile.setAuthor(author);
                    uploadFileRepository.save(uploadFile);
                }
                result.status = 0;
                result.result = realpath;
            } catch (Exception e) {
                e.printStackTrace();
                result.status = -1;
                result.result = "上传出错";
            }
        } else {
            System.out.println("文件为空");
            result.status = -1;
            result.result = "文件为空";
        }
        return result;
    }

    @RequestMapping(value = "/admin/addWebsite")
    public NetResult addWebsite(UploadFile uploadFile) {
        String name = uploadFile.getName();
        String url = uploadFile.getUrl();
        if (name != null) {
            if (url != null) {
                uploadFile.setType(FileType.WEBSITE);
                uploadFile.setDate(new Date());
                uploadFileRepository.save(uploadFile);
                netResult.status = 0;
                netResult.result = "添加成功！";
            } else {
                netResult.status = -1;
                netResult.result = "地址为空！";
            }
        } else {
            netResult.status = -2;
            netResult.result = "名字为空！";
        }
        return netResult;
    }

    @RequestMapping(value = "/admin/getAllFiles")
    public NetResult getAllSlides(@RequestParam String fileType) {
        List<UploadFile> uploadFiles;
        switch (fileType) {
            case "edu":
                uploadFiles = uploadFileRepository.findByTypeOrType(
                        FileType.SLIDE,
                        FileType.DOCUMENT
                );
                break;
            case "show":
                uploadFiles = uploadFileRepository.findByType(
                        FileType.SHOW
                );
                break;
            case "website":
                uploadFiles = uploadFileRepository.findByType(
                        FileType.WEBSITE
                );
                break;
            default:
                uploadFiles = null;
        }
        netResult.status = 0;
        netResult.result = uploadFiles;
        return netResult;
    }

    @RequestMapping(value = "/admin/deleteFile")
    public NetResult deleteFile(@RequestParam Long fileId) {
        UploadFile uploadFile = uploadFileRepository.findByFileId(fileId);
        if (uploadFile == null) {
            netResult.status = -1;
            netResult.result = "文件不存在！";
        } else {
            uploadFileRepository.delete(uploadFile);
            netResult.status = 0;
            netResult.result = "删除成功！";
        }
        return netResult;
    }

    @PostMapping(value = "/admin/uploadFiles")
    public NetResult uploadFile(@RequestParam MultipartFile file, @RequestParam FileType type, String author, HttpServletRequest request) throws IOException, FileException {
        String tempPath = fileService.uploadFile(file, request);
        String relativeDirectory = File.separator + type + File.separator;
        String path = fileService.saveFile(tempPath, relativeDirectory);
        UploadFile uploadFile = new UploadFile();
        uploadFile.setAuthor(author);
        uploadFile.setDate(new Date());
        uploadFile.setName(file.getOriginalFilename());
        uploadFile.setPath(path);
        uploadFile.setType(type);
        netResult.status = 0;
        netResult.result = uploadFileRepository.save(uploadFile);
        return netResult;
    }

    @GetMapping(value = "/uploadFiles")
    public void downloadFile(HttpServletRequest request, HttpServletResponse response, @RequestParam Long id) throws IOException{
        UploadFile file = uploadFileRepository.findByFileId(id);
        String path = file.getPath();
        File downloadFile = new File(path);

        ServletContext context = request.getServletContext();

        // get MIME type of the file
        String mimeType = context.getMimeType(path);
        if (mimeType == null) {
            // set to binary type if MIME mapping not found
            mimeType = "application/octet-stream";
            System.out.println("context getMimeType is null");
        }
        System.out.println("MIME type: " + mimeType);

        // set content attributes for the response
        response.setContentType(mimeType);
        response.setContentLength((int) downloadFile.length());

        // set headers for the response
        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"",
                downloadFile.getName());
        response.setHeader(headerKey, headerValue);

        // Copy the stream to the response's output stream.
        try (InputStream myStream = new FileInputStream(path)) {
            IOUtils.copy(myStream, response.getOutputStream());
        }
        response.flushBuffer();
    }
}
