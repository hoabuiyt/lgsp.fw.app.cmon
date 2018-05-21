package vn.lgsp.fw.app.cmon.web.rest;

import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

import vn.lgsp.fw.app.cmon.domain.entity.BaseEntity;

@JsonPropertyOrder({ "id" })
public abstract class BaseResource<T extends BaseEntity<T>> extends ResourceSupport{

	@JsonUnwrapped
	private final T entity;
	
	public BaseResource(T entity) {
		this.entity = entity;
	}

	@JsonProperty("id")
    public Long getResourceId() {
		return entity.getId();
    }
	
}
