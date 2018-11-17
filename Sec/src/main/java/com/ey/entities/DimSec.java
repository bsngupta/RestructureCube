package com.ey.entities;


public class DimSec {

    private long Id;
    private String groupname;
    private String artifactname;
    private String artifacttype;
    private String accesstype;
    private String accesslever;
    private String isuser;

    public DimSec() {
    }

    public DimSec(String groupname, String artifactname, String artifacttype, String accesstype, String accesslever, String isuser) {
        this.groupname = groupname;
        this.artifactname = artifactname;
        this.artifacttype = artifacttype;
        this.accesstype = accesstype;
        this.accesslever = accesslever;
        this.isuser = isuser;
    }

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public String getGroupname() {
        return groupname;
    }

    public void setGroupname(String groupname) {
        this.groupname = groupname;
    }

    public String getArtifactname() {
        return artifactname;
    }

    public void setArtifactname(String artifactname) {
        this.artifactname = artifactname;
    }

    public String getArtifacttype() {
        return artifacttype;
    }

    public void setArtifacttype(String artifacttype) {
        this.artifacttype = artifacttype;
    }

    public String getAccesstype() {
        return accesstype;
    }

    public void setAccesstype(String accesstype) {
        this.accesstype = accesstype;
    }

    public String getAccesslever() {
        return accesslever;
    }

    public void setAccesslever(String accesslever) {
        this.accesslever = accesslever;
    }

    public String getIsuser() {
        return isuser;
    }

    public void setIsuser(String isuser) {
        this.isuser = isuser;
    }

    @Override
    public String toString() {
        return "DimSec{" +
                "Id=" + Id +
                ", groupname='" + groupname + '\'' +
                ", artifactname='" + artifactname + '\'' +
                ", artifacttype='" + artifacttype + '\'' +
                ", accesstype='" + accesstype + '\'' +
                ", accesslever='" + accesslever + '\'' +
                ", isuser='" + isuser + '\'' +
                '}';
    }
}
