package com.bonifacio.app.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Entity(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(unique = true)
    @Size(max = 150)
    @NotNull
    private String username;
    @Column
    @Email
    @NotNull
    private String email;
    @Column()
    @NotNull
    private String password;

    @Enumerated(EnumType.STRING)
    Role role;

    /**
     * @return 
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority((role.name())));
    }

    /**
     * @return 
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * @return 
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * @return 
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * @return 
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}
