package com.alektoni.sharearide.security;

import com.alektoni.sharearide.model.User;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.sql.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class MyUserDetails implements UserDetails {


    private String username;
    private String password;
    private Boolean active;
    private List<GrantedAuthority> grantedAuthorityList;

    MyUserDetails(User user){

        this.username=user.getEmail();
        this.password=user.getPassword();
        this.active = user.getActive();
        this.grantedAuthorityList = Arrays.stream(user.getRoles().split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList(new SimpleGrantedAuthority(MyRoles.ROLE_USER.name()));
    }//todo return  this.grantedAuthorityList

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.active;
    }
}
