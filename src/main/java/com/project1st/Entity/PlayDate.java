package com.project1st.Entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@Setter
@EntityListeners( value = {AuditingEntityListener.class} )
public abstract class PlayDate {

  @CreatedDate
  @Column( updatable = false )
  private LocalDateTime date;
}
