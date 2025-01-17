package com.hotelmanager.models;
import java.time.LocalDateTime;

public class Notification {
    private int id;
    private String roomId;  // Có thể null nếu là thông báo chung
    private String title;
    private String content;
    private String type;
    private boolean isRead;
    private LocalDateTime createdAt;

    public Notification(int id, String roomId, String title, String content, String type, boolean isRead, LocalDateTime createdAt) {
        this.id = id;
        this.roomId = roomId;
        this.title = title;
        this.content = content;
        this.type = type;
        this.isRead = isRead;
        this.createdAt = createdAt;
    }

    // get id
    public int getId() {
        return id;
    }

    public boolean isRead() {
        return isRead;
    }

    public String getType() {
        return type;
    }

    public String getRoomId() {
        return roomId;
    }

    // get title
    public String getTitle() {
        return title;
    }
    // get content
    public String getContent() {
        return content;
    }

    public String getMessage() {
        return "Phòng " + roomId.toString() + " - " + title + "\n" + content;
    }
}