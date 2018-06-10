package vn.lgsp.fw.app.cmon.domain.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import vn.lgsp.fw.app.cmon.domain.BaseEntity;

/**
 * Danh muc Don Vi Hanh Chinh
 * @author caltalys
 *
 */
@Entity
@Table(name="app_vaitro")
@Data
@ToString
@EqualsAndHashCode(callSuper=true)	
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class VaiTro extends BaseEntity<VaiTro>{
	
	private static final long serialVersionUID = -5551311878392114212L;
	
	@NotBlank
	@Size(max=255)
	private String ten = "";
	
	@ElementCollection(targetClass = String.class, fetch = FetchType.EAGER)
	@CollectionTable(name = "app_vaitro_quyen", joinColumns = { @JoinColumn(name = "vaitro_id") })
	private Set<String> quyens = new HashSet<>();
}
