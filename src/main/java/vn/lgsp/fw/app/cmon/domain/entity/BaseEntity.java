package vn.lgsp.fw.app.cmon.domain.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import org.springframework.data.domain.Persistable;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import vn.lgsp.fw.app.cmon.util.FwDateTimeUtil;

/**
 * This class is an abstract superclass for all Entity classes in the
 * application. This class defines variables which are common for all entity
 * classes.
 */

@Data
@ToString
@EqualsAndHashCode
@MappedSuperclass
public class BaseEntity<T extends BaseEntity<T>> implements Persistable<Long>{

	private static final long serialVersionUID = 6272945932232827822L;
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(columnDefinition="datetime")
	private LocalDateTime createdAt = FwDateTimeUtil.localDateTimeNow();
	
	@Column(columnDefinition="datetime")
	private LocalDateTime modifiedAt = FwDateTimeUtil.localDateTimeNow();
	
	private Long createdBy;
	
	private Long modifiedBy;
	
	@JsonIgnore
	private boolean deleted;
	
	@Override
	public Long getId() {
		return id;
	}

	@Transient
	@JsonIgnore
	@Override
	public boolean isNew() {
		return id == null || id == 0;
	}
/*
	@Override
	public boolean equals(Object that) {
		return this == that 
				|| that != null && getClass().isAssignableFrom(that.getClass()) 
					&& getId() != null
					&& Objects.equals(getId(), ((BaseEntity<?>) that).getId());
	}
	
	@Override
    public int hashCode() {
        *//**
         * WARNING: Do not use collection relying on hashCode() such as
         * HashMap etc. before entity has been persisted. This implementation
         * breaks the constraint of hashCode immutability!
         *
         * Alternative: Generate UUID in constructor for hashCode().
         *//*
        return null == getId() ? 0 : 17 + getId().hashCode() * 31;
    }*/
	
}
