package vn.lgsp.fw.app.cmon.domain.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.Persistable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class Auditable<U extends Serializable> implements Persistable<Long>{

	private static final long serialVersionUID = 1L;

	@JsonIgnore
	@CreatedDate
	//@Convert(converter = LocalDateTimeAttributeConverter.class)
	private LocalDateTime createdAt;// = FwDateTimeUtil.localDateTimeNow();
	
	@JsonIgnore
	@LastModifiedDate
	//@Convert(converter = LocalDateTimeAttributeConverter.class)
	private LocalDateTime modifiedAt;// = FwDateTimeUtil.localDateTimeNow();
	
	@JsonIgnore
	@CreatedBy
	private U createdBy;
	
	@JsonIgnore
	@LastModifiedBy
	private U modifiedBy;
	
}
