package com.shubhamdeshmukh.attendencemanagementsystem.backend.other_entities;

import java.util.Calendar;
import java.util.Date;

public class MetaInfo {
    private String id;
    private String createdBy;
    private String modifiedBy;
    private Date createdDateTime;
    private Date modifiedDateTime;
    private boolean isActive;
    private boolean isDeleted;

    private MetaInfo(String id, String createdBy, String modifiedBy, Date createdDateTime, Date modifiedDateTime, boolean isActive, boolean isDeleted) {
        this.id = id;
        this.createdBy = createdBy;
        this.modifiedBy = modifiedBy;
        this.createdDateTime = createdDateTime;
        this.modifiedDateTime = modifiedDateTime;
        this.isActive = isActive;
        this.isDeleted = isDeleted;
    }

    public MetaInfo(String id, String createdBy, String modifiedBy) {
        this.id = id;
        this.createdBy = createdBy;
        this.modifiedBy = modifiedBy;

        Date currentDateTime = Calendar.getInstance().getTime();
        this.createdDateTime = currentDateTime;
        this.isActive = true;
        this.isDeleted = false;

        this.modifiedDateTime = currentDateTime;
    }

    public void setActive(boolean active, String modifiedBy) {
        this.isActive = active;
        this.modifiedBy = modifiedBy;

        this.modifiedDateTime = Calendar.getInstance().getTime();
    }

    public void setDeleted(boolean deleted, String modifiedBy) {
        isDeleted = deleted;
        this.modifiedBy = modifiedBy;

        this.modifiedDateTime = Calendar.getInstance().getTime();
    }

    public void modify(String modifiedBy)
    {
        this.modifiedBy = modifiedBy;
        this.modifiedDateTime = Calendar.getInstance().getTime();
    }

    public String getId() {
        return id;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public Date getCreatedDateTime() {
        return createdDateTime;
    }

    public Date getModifiedDateTime() {
        return modifiedDateTime;
    }

    public boolean isActive() {
        return isActive;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public static MetaInfo getCopy(MetaInfo object)
    {
        return new MetaInfo(
                object.id,
                object.createdBy,
                object.modifiedBy,
                object.createdDateTime,
                object.modifiedDateTime,
                object.isActive,
                object.isDeleted
        );
    }
}
