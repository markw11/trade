package com.example.demo;

import java.util.Collection;
import java.util.Date;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class JwtUser implements UserDetails{
	
	private static final long serialVersionUID = 1L;
	private final String id;
    private final String username;
    private final String password;
    private final String email;
    private final Collection<? extends GrantedAuthority> authorities;
    private final Date lastPasswordResetDate;
    
    public JwtUser(
            String id,
            String username,
            String password,
            String email,
            Collection<? extends GrantedAuthority> authorities,
            Date lastPasswordResetDate) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.authorities = authorities;
        this.lastPasswordResetDate = lastPasswordResetDate;
    }

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

}
