package vn.lgsp.fw.app.cmon.domain.entity;

import javax.persistence.MappedSuperclass;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString
@EqualsAndHashCode(callSuper=true)
@MappedSuperclass
public abstract class ACategory<T extends BaseEntity<T>> extends BaseEntity<T> {

	private static final long serialVersionUID = 6451571741613034513L;

	private String name;
	
	private String code;
	
}
