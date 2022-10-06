package zw.co.getsol.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zw.co.getsol.model.UserAccountRole;

import java.util.List;

@Repository
public interface UserAccountRoleRepository extends JpaRepository<UserAccountRole,Long> {
    List<UserAccountRole> findByUserAccount_Username(String username);
}
