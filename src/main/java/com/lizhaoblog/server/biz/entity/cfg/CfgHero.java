package com.lizhaoblog.server.biz.entity.cfg;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Hero")
@XmlAccessorType(XmlAccessType.FIELD)
public class CfgHero implements Serializable {
    @XmlAttribute(name = "id")
    private int id;
    @XmlAttribute(name = "roleId")
    private int roleId;
    @XmlAttribute(name = "name")
    private String name;
    @XmlAttribute(name = "type")
    private int type;
    @XmlAttribute(name = "countryType")
    private int countryType;
    @XmlAttribute(name = "icon")
    private String icon;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getCountryType() {
        return countryType;
    }

    public void setCountryType(int countryType) {
        this.countryType = countryType;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
