package com.when630.supportchat.domain.account;

import com.when630.supportchat.domain.common.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "account")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Account extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, unique = true)
  private String email;

  @Column(name = "password_hash", nullable = false)
  private String passwordHash;

  @Column(nullable = false)
  private String name;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private Role role;

  @Column(name = "is_active", nullable = false)
  private Boolean isActive;

  @OneToOne(mappedBy = "account", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private AgentStatus agentStatus;

  @Builder
  public Account(String email, String passwordHash, String name, Role role) {
    this.email = email;
    this.passwordHash = passwordHash;
    this.name = name;
    this.role = role;
    this.isActive = true;
  }
}
