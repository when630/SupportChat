package com.when630.supportchat.domain.account;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "agent_status")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AgentStatus {

  @Id
  private Long accountId;

  @MapsId
  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "account_id")
  private Account account;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private Status status;

  @Column(name = "updated_at", nullable = false)
  private LocalDateTime updatedAt;

  public enum Status {
    ONLINE, OFFLINE, AWAY, BUSY
  }

  public AgentStatus(Account account, Status status) {
    this.account = account;
    this.status = status;
    this.updatedAt = LocalDateTime.now();
  }

  public void updateStatus(Status status) {
    this.status = status;
    this.updatedAt = LocalDateTime.now();
  }
}
