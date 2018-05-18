package vn.lgsp.fw.app.cmon.domain.entity;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * Danh muc Dan Toc
 * @author caltalys
 *
 */
@Entity
@Table(name="cmon_ethnicity")
@Data
@ToString
@EqualsAndHashCode(callSuper=true)
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class CmonEthnicity extends ACategory<CmonEthnicity>{

	private static final long serialVersionUID = 1836710375481963127L;

	
}
