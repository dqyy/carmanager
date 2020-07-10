package dqyy.service;

import dqyy.Hr;
import dqyy.HrMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

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


    //验证用户名是否存在
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Hr hra = hr.selectByUsername(username);
        if (hr == null) {
            throw new UsernameNotFoundException("用户名没有找到");
        }
        return (UserDetails) hra;
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
