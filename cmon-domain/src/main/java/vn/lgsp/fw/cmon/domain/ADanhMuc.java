package vn.lgsp.fw.cmon.domain;

import javax.persistence.MappedSuperclass;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import vn.lgsp.fw.core.BaseEntity;

@Data
@ToString
@EqualsAndHashCode(callSuper=true)
@MappedSuperclass
public abstract class ADanhMuc<T extends BaseEntity<T>> extends BaseEntity<T> {
	
	private static final long serialVersionUID = -3538337409835996732L;

	private String ma;
	
	private String ten;
	
}
