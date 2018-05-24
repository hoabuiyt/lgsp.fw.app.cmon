package vn.lgsp.fw.app.cmon.domain.entity;

import java.time.LocalDateTime;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class Auditable<U >{

	@JsonIgnore
	@CreatedDate
	private LocalDateTime ngayTao;// = FwDateTimeUtil.localDateTimeNow();
	
	@JsonIgnore
	@LastModifiedDate
	private LocalDateTime ngaySua;// = FwDateTimeUtil.localDateTimeNow();
	
	@JsonIgnore
	@CreatedBy
	private U nguoiTao;
	
	@JsonIgnore
	@LastModifiedBy
	private U nguoiSua;
	
}
