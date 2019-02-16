package com.gistprototype.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;


@Entity
@Table(name = "gist")
@EntityListeners(AuditingEntityListener.class)
//@JsonIgnoreProperties(value = {"createdAt", "updatedAt"},
//        allowGetters = true)
public class Gist {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @NotBlank
    @Column(unique=true)
    private String gistApiType;

    private Integer apiHitCount;

    public Gist() {
  		this.gistApiType="";
  		this.apiHitCount=0;
  	}
    
    public Gist(String gistApiType, int apiHitCount) {
		this.gistApiType=gistApiType;
		this.apiHitCount=apiHitCount;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGistApiType() {
        return gistApiType;
    }

    public void setApiHitCount(Integer apiHitCount) {
        this.apiHitCount = apiHitCount;
    }
    
    public Integer getApiHitCount() {
        return apiHitCount;
    }

    public void setGistApiType(String gistApiType) {
        this.gistApiType = gistApiType;
    }
   
}
