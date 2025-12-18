package com.when630.supportchat.domain.chat;

import com.when630.supportchat.domain.account.Account;
import com.when630.supportchat.domain.common.BaseEntity;
import com.when630.supportchat.domain.visitor.Visitor;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "conversation", indexes = {
    @Index(name = "idx_conversation_visitor", columnList = "visitor_id"),
    @Index(name = "idx_conversation_agent", columnList = "assigned_agent_id"),
    @Index(name = "idx_conversation_status", columnList = "status")
})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Conversation extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "visitor_id", nullable = false)
  private Visitor visitor;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "assigned_agent_id")
  private Account assignedAgent;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private ConversationStatus status;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private ConversationSource source;

  @Column(name = "started_at", nullable = false)
  private LocalDateTime startedAt;

  @Column(name = "closed_at")
  private LocalDateTime closedAt;

  @Column(name = "last_message_at")
  private LocalDateTime lastMessageAt;

  private String subject;

  @Builder
  public Conversation(Visitor visitor, ConversationSource source, String subject) {
    this.visitor = visitor;
    this.source = source;
    this.subject = subject;
    this.status = ConversationStatus.WAITING;
    this.startedAt = LocalDateTime.now();
    this.lastMessageAt = LocalDateTime.now();
  }

  public void assignAgent(Account agent) {
    this.assignedAgent = agent;
    this.status = ConversationStatus.IN_PROGRESS;
  }

  public void close() {
    this.status = ConversationStatus.CLOSED;
    this.closedAt = LocalDateTime.now();
  }

  public void updateLastMessageAt() {
    this.lastMessageAt = LocalDateTime.now();
  }
}
