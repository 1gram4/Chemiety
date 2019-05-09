package cn.kastner.chemiety.repository;

import cn.kastner.chemiety.domain.UploadFile;
import cn.kastner.chemiety.domain.FileType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UploadFileRepository extends JpaRepository<UploadFile, Long> {
    UploadFile findByNameAndType(String name, FileType type);
    List<UploadFile> findByType(FileType type);
    List<UploadFile> findByTypeOrType(FileType type1, FileType type2);
    UploadFile findByFileId(Long fileId);
}
