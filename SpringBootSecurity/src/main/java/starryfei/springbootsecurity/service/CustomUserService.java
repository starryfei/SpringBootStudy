package starryfei.springbootsecurity.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import starryfei.springbootsecurity.dao.RoleDao;
import starryfei.springbootsecurity.dao.UserDao;
import starryfei.springbootsecurity.entity.Role;
import starryfei.springbootsecurity.entity.SysUser;
@Service
public class CustomUserService implements UserDetailsService{
    @Autowired
    private UserDao userDao;
    @Autowired
    private RoleDao roleDao;
    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        SysUser user = userDao.findOneByName(username);
        if (user != null) {
            List<Role> roles = roleDao.findAllById(user.getId());
            List<GrantedAuthority> grantedAuthorities = new ArrayList <>();
            for (Role role : roles) {
                if (role != null && role.getName()!=null) {
                    GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(role.getName());
                    grantedAuthorities.add(grantedAuthority);
                }
            }
            return new User(user.getName(), user.getPassWord(), grantedAuthorities);
        } else {
            throw new UsernameNotFoundException("admin: " + username + " do not exist!");
        }
    }

}
