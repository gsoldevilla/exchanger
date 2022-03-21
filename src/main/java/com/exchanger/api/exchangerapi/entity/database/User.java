package com.exchanger.api.exchangerapi.entity.database;

import com.exchanger.api.exchangerapi.entity.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.Email;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.annotation.Id;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements UserDetails {

    private static final long serialVersionUID = -8888149524493612739L;

    @Id
    private Integer id;

    private String username;

    @JsonIgnore
    private String password;

    @Email
    private String email;

    @Builder.Default()
    private boolean active = true;

    @Builder.Default()
    private List<Role> roles = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles
            .stream()
            .map(authority -> new SimpleGrantedAuthority(authority.name())).collect(Collectors.toList());
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return this.active;
    }
}
