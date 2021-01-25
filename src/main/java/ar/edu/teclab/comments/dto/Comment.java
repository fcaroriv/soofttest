package ar.edu.teclab.comments.dto;

import java.util.Date;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Comment {

    private Long id;
    private String body;
    private Long authorId;
    private List<String> uploads;
    private List<Attachment> attachments;
    private Date createdAt;
    @JsonProperty("public")
    private Boolean publicComment;
    private String type;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public List<String> getUploads() {
        return uploads;
    }

    public void setUploads(List<String> uploads) {
        this.uploads = uploads;
    }

    public List<Attachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<Attachment> attachments) {
        this.attachments = attachments;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Boolean getPublicComment() {
        return publicComment;
    }

    public void setPublicComment(Boolean publicComment) {
        this.publicComment = publicComment;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
