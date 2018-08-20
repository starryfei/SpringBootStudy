package starryfei.springbootsecurity.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import starryfei.springbootsecurity.entity.Role;


@Repository
public interface RoleDao extends CrudRepository<Role, Long>,PagingAndSortingRepository<Role,Long> {
    public List<Role> findAllById(long id);
}
