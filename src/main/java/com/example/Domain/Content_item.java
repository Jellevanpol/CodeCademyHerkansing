package com.example.Domain;

import java.util.Date;
import com.example.Domain.Enumerations.Status;

public class Content_item {
    private String contentId;
    private String titel;
    private String beschrijving;
    private Date pubDate;
    private Status status;

    public Content_item(String contentId, String titel, String beschrijving, Date pubDate, Status status) {
        this.contentId = contentId;
        this.titel = titel;
        this.beschrijving = beschrijving;
        this.pubDate = pubDate;
        this.status = status;
    }

    public String getContentId() {
        return this.contentId;
    }

    public void setContentId(String contentId) {
        this.contentId = contentId;
    }

    public String getTitel() {
        return this.titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public String getBeschrijving() {
        return this.beschrijving;
    }

    public void setBeschrijving(String beschrijving) {
        this.beschrijving = beschrijving;
    }

    public Date getPubDate() {
        return this.pubDate;
    }

    public void setPubDate(Date pubDate) {
        this.pubDate = pubDate;
    }

    public Status getStatus() {
        return this.status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

}
