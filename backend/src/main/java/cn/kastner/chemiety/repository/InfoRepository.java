package cn.kastner.chemiety.repository;

import cn.kastner.chemiety.domain.Info;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InfoRepository extends JpaRepository<Info, Long> {
    List<Info> findByContentIsNotNullOrderByInfoIdDesc();
}
