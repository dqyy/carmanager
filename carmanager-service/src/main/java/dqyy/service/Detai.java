package dqyy.service;

import dqyy.Hr;
import dqyy.HrMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import sun.security.util.Password;

import java.util.ArrayList;
import java.util.List;

@Service
public class Detai implements UserDetailsService {
    @Autowired
    HrMapper hr;


    //用户注册

    public int insertHr(Hr hra) {
        return hr.insertSelective(hra);
    }

    //获得所有客户信息
    public List<Hr> getAllHr() {
        return hr.selectAll();

    }

    //判断用户是否存在
    public String cheackUser(String username) {
        Hr hr = this.hr.selectByUsername(username);
        if (hr == null) {
            return "ok";
        }
        return "no";

    }

    //判断用户名密码
    public Hr cheackUserAndPasswd(String username, String password) {
        return hr.cheack(username, password);

    }


    //验证用户名是否存在
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Hr hra = hr.selectByUsername(username);
        if (hra == null) {
            throw new UsernameNotFoundException("用户名没有找到");
        }
        List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();

        User user = new User(hra.getUsername(), hra.getPassword(), grantedAuthorities);

        return user;
    }

    //修改密码
    public boolean UpdataPassword(Integer id, String oldpassword, String newpassword) {
        Hr hra = hr.selectByPrimaryKey(id);
        BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
        boolean matches = bc.matches(oldpassword, hra.getPassword());
        if (matches) {
            String encode = bc.encode(newpassword);
            int i = hr.updatepasswordByPrimaryKey(encode, id);
            return i == 1;
        }
        return false;
    }
}
