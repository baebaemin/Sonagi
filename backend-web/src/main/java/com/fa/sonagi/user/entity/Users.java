package com.fa.sonagi.user.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fa.sonagi.oauth.entity.ProviderType;
import com.fa.sonagi.user.utils.BaseTimeEntity;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users", uniqueConstraints = @UniqueConstraint(columnNames = {"social_id"}))
public class Users extends BaseTimeEntity implements UserDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id", nullable = false, columnDefinition = "BIGINT UNSIGNED")
	private Long userId;

	@NotNull
	@Column(name = "social_id", length = 64)
	private String socialId;

	@Setter
	@Column(name = "email", length = 100)
	private String email;

	@Setter
	@Column(name = "name", length = 25)
	private String name;

	@Column(name = "roles")
	@ElementCollection(fetch = FetchType.EAGER)
	@Builder.Default
	private List<String> roles = new ArrayList<>();

	@Column(name = "provider_type", length = 20)
	@Enumerated(EnumType.STRING)
	@NotNull
	private ProviderType providerType;

	@Column(name = "FCMToken", length = 70)
	private String FirebaseToken;

	@Column(name = "v_alarm", columnDefinition = "tinyint(1) default 1")
	private Boolean vAlarm = true;

	@Column(name = "c_alarm", columnDefinition = "tinyint(1) default 1")
	private Boolean cAlarm = true;

	@Column(name = "d_alarm", columnDefinition = "tinyint(1) default 1")
	private boolean dAlarm = true;

	@Column(name = "m_alarm", columnDefinition = "tinyint(1) default 1")
	private boolean mAlarm = true;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.roles
			.stream()
			.map(SimpleGrantedAuthority::new)
			.collect(Collectors.toList());
	}

	@Override
	public String getPassword() {
		return "No_Password";
	}

	@Override
	public String getUsername() {
		return socialId;
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
		return true;
	}

	public void updateName(String name) {
		this.name = name;
	}

}