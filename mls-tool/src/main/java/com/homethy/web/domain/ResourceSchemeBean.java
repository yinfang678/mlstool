package com.homethy.web.domain;

import java.util.ArrayList;

/**
 * Created by wu.chen on 2017/7/24.
 */
public class ResourceSchemeBean {
    private String resouceName;
    private ArrayList className;
    private String resourcekeySysname;
    private String resourcekeyQuery;
    private String moddtSysname;
    private String photoModdtSysname;
    private String photoFetchMode;
    private String photoType;
    private String mlsnumberSysname;
    private String statusSysname;

    public String getResourceName() {
        return resouceName;
    }

    public void setResourceName(String resouceName) {
        this.resouceName = resouceName;
    }

    public ArrayList getClassName() {
        return className;
    }

    public void setClassName(ArrayList className) {
        this.className = className;
    }

    public String getResourcekeySysname() {
        return resourcekeySysname;
    }

    public void setResourcekeySysname(String resourcekeySysname) {
        this.resourcekeySysname = resourcekeySysname;
    }

    public String getResourcekeyQuery() {
        return resourcekeyQuery;
    }

    public void setResourcekeyQuery(String resourcekeyQuery) {
        this.resourcekeyQuery = resourcekeyQuery;
    }

    public String getModdtSysname() {
        return moddtSysname;
    }

    public void setModdtSysname(String moddtSysname) {
        this.moddtSysname = moddtSysname;
    }

    public String getPhotoModdtSysname() {
        return photoModdtSysname;
    }

    public void setPhotoModdtSysname(String photoModdtSysname) {
        this.photoModdtSysname = photoModdtSysname;
    }

    public String getPhotoFetchMode() {
        return photoFetchMode;
    }

    public void setPhotoFetchMode(String photoFetchMode) {
        this.photoFetchMode = photoFetchMode;
    }

    public String getPhotoType() {
        return photoType;
    }

    public void setPhotoType(String photoType) {
        this.photoType = photoType;
    }

    public String getMlsnumberSysname() {
        return mlsnumberSysname;
    }

    public void setMlsnumberSysname(String mlsnumberSysname) {
        this.mlsnumberSysname = mlsnumberSysname;
    }

    public String getStatusSysname() {
        return statusSysname;
    }

    public void setStatusSysname(String statusSysname) {
        this.statusSysname = statusSysname;
    }

}


