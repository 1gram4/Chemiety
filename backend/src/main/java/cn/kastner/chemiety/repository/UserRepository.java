package cn.kastner.chemiety.repository;

import cn.kastner.chemiety.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserId (Long userId);
    User findByUsername(String username);
    User findByUsernameAndPassword(String username, String password);
}
