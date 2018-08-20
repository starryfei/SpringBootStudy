package starryfei.springbootsecurity.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @ClassName  UserRole   
 * @Description 用户权限 
 * @author yafei.qin 
 * @date 2018年8月15日 下午3:20:33   
 *
 */
@Entity
@Table(name="sys_role")
public class UserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="sys_role_id")
    private long id;
    @Column(name="role_id")
    private long roleId;
    @Column(name="user_id")
    private long userId;
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public long getRoleId() {
        return roleId;
    }
    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }
    public long getUserId() {
        return userId;
    }
    public void setUserId(long userId) {
        this.userId = userId;
    }
    @Override
    public String toString() {
        return "UserRole [id=" + id + ", roleId=" + roleId + ", userId="
                + userId + "]";
    }
    
    
}
