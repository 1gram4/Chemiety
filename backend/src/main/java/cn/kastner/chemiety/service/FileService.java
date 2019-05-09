package cn.kastner.chemiety.service;

import cn.kastner.chemiety.exception.FileException;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public interface FileService {
    String uploadFile(MultipartFile file, HttpServletRequest request) throws FileException, IOException;

    String saveFile(String tempPath, String relativePath);
}
