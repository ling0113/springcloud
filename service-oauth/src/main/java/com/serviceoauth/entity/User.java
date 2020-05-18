package com.serviceoauth.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * getAuthorities()：获取当前用户对象所具有的角色信息 getPassword()：获取当前用户对象的密码 getUsername()：获取当前用户对象的用户名 isAccountNonExpired()：当前账户是否未过期
 * isAccountNonLocked()：当前账户是否未锁定 isCredentialsNonExpired()：当前账户密码是否未过期 isEnabled()：当前账户是否可用 （1）用户根据实际情况设置这 7
 * 个方法的返回值。默认情况下不需要开发者自己进行密码角色等信息的比对，开发者只需要提供相关信息即可，例如： getPassword() 方法返回的密码和用户输入的登录密码不匹配，会自动抛出 BadCredentialsException 异常 isAccountNonLocked() 方法返回了
 * false，会自动抛出 AccountExpiredException 异常。 本案例因为数据库中只有 enabled 和 locked 字段，故账户未过期和密码未过期两个方法都返回 true. （2）getAuthorities
 * 方法用来获取当前用户所具有的角色信息，本案例中，用户所具有的角色存储在 roles 属性中，因此该方法直接遍历 roles 属性，然后构造 SimpleGrantedAuthority 集合并返回。
 */
@NoArgsConstructor
public class User implements UserDetails {

    private Integer id;
    private String username;
    private String password;
    private Boolean enabled = true;
    private Boolean locked;
    private List<Role> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    /**
     * get、set 方法
     **/

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Boolean getLocked() {
        return locked;
    }

    public void setLocked(Boolean locked) {
        this.locked = locked;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

}