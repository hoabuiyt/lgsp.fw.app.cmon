package vn.lgsp.fw.app.cmon.domain.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Table(name="ethnicity")
@Data
@ToString
@EqualsAndHashCode(callSuper=true)
public class Ethnicity extends BaseEntity<Ethnicity>{

	private static final long serialVersionUID = 1836710375481963127L;

	
}
