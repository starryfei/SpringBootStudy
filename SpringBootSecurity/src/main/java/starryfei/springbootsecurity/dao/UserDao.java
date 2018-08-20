package starryfei.springbootsecurity.dao;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import starryfei.springbootsecurity.entity.SysUser;

@Repository
public interface UserDao extends CrudRepository<SysUser, Long>,PagingAndSortingRepository<SysUser,Long> {
        /**
         * @param id
         * @return
         */
    SysUser findOneByName(String name);
}
