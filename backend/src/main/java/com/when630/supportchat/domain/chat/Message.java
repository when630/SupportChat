package com.when630.supportchat.domain.chat;

import com.when630.supportchat.domain.common.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "message", indexes = {
    @Index(name = "idx_message_conversation", columnList = "conversation_id"),
    @Index(name = "idx_message_created_at", columnList = "created_at")
})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Message extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "conversation_id", nullable = false)
  private Conversation conversation;

  @Enumerated(EnumType.STRING)
  @Column(name = "sender_type", nullable = false)
  private SenderType senderType;

  @Column(name = "sender_id", nullable = false)
  private Long senderId;

  @Lob
  @Column(columnDefinition = "TEXT", nullable = false)
  private String content;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private MessageType type;

  @Column(name = "is_read", nullable = false)
  private Boolean isRead;

  @Builder
  public Message(Conversation conversation, SenderType senderType, Long senderId, String content, MessageType type) {
    this.conversation = conversation;
    this.senderType = senderType;
    this.senderId = senderId;
    this.content = content;
    this.type = (type != null) ? type : MessageType.TEXT;
    this.isRead = false;
  }

  public void markAsRead() {
    this.isRead = true;
  }
}
