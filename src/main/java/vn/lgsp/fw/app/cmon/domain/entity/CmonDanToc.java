package vn.lgsp.fw.app.cmon.domain.entity;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Danh muc Dan Toc
 * @author caltalys
 *
 */
@Entity
@Table(name="cmon_dantoc")
@Data 												//Define a mutable value object (getter, setter)
@ToString
@EqualsAndHashCode(callSuper=true)
//@NoArgsConstructor(access = AccessLevel.PRIVATE) 	//Create an empty constructor call to appease Jackson, but which is private and not usable to our app’s code
@JsonIgnoreProperties(ignoreUnknown = true) 		//Ignore unknown attributes when deserializing JSON
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class CmonDanToc extends ADanhMuc<CmonDanToc>{
	
	private static final long serialVersionUID = -168197636045249911L;

	
}
