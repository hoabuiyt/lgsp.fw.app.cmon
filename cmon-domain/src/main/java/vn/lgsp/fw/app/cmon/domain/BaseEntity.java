package vn.lgsp.fw.app.cmon.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.springframework.data.domain.Persistable;
import org.springframework.hateoas.Identifiable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * This class is an abstract superclass for all Entity classes in the
 * application. This class defines variables which are common for all entity
 * classes.
 */

@Data
@ToString
@EqualsAndHashCode(callSuper=true)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@MappedSuperclass
public class BaseEntity<T extends BaseEntity<T>> extends Auditable<String> implements Identifiable<Long>, Persistable<Long>{

	private static final long serialVersionUID = 9197809217082471305L;

	@Id
	@GeneratedValue
	private Long id;
	
	@JsonIgnore
	private boolean deleted;

	@JsonIgnore
	@Override
	public boolean isNew() {
		return id == null || id == 0;
	}
	

	/*@Override
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
