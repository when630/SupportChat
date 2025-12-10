package com.when630.supportchat.domain.visitor;

import com.when630.supportchat.domain.common.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "visitor")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Visitor extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "external_user_id") // Nullable for anonymous guests
  private String externalUserId;

  @Column(nullable = false)
  private String name;

  private String email;

  private String phone;

  @Column(name = "user_agent")
  private String userAgent;

  @Column(name = "last_seen_at")
  private LocalDateTime lastSeenAt;

  @Builder
  public Visitor(String externalUserId, String name, String email, String phone, String userAgent) {
    this.externalUserId = externalUserId;
    this.name = name;
    this.email = email;
    this.phone = phone;
    this.userAgent = userAgent;
    this.lastSeenAt = LocalDateTime.now();
  }

  public void updateLastSeen() {
    this.lastSeenAt = LocalDateTime.now();
  }
}
