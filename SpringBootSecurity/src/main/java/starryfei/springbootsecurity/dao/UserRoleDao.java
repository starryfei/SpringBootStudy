package starryfei.springbootsecurity.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import starryfei.springbootsecurity.entity.UserRole;
@Repository
public interface UserRoleDao extends CrudRepository<UserRole, Long>,PagingAndSortingRepository<UserRole,Long> {

}
