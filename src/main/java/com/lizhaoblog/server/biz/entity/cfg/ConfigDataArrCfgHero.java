package com.lizhaoblog.server.biz.entity.cfg;

import com.lizhaoblog.base.xml.ConfigDataArr;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Root")
@XmlAccessorType(XmlAccessType.FIELD)
public class ConfigDataArrCfgHero implements ConfigDataArr {

    @XmlElements(value = {@XmlElement(name = "Hero", type = CfgHero.class)})
    private List<CfgHero> cfgHeroes;

    public List<CfgHero> getCfgHeroes() {
        return cfgHeroes;
    }

    public void setCfgHeroes(List<CfgHero> cfgHeroes) {
        this.cfgHeroes = cfgHeroes;
    }

    @Override
    public List getList() {
        return getCfgHeroes();
    }
}
