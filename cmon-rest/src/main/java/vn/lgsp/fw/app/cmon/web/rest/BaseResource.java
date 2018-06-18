package vn.lgsp.fw.app.cmon.web.rest;

import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

import vn.lgsp.fw.core.BaseEntity;

@JsonPropertyOrder({ "id" })
public abstract class BaseResource<T extends BaseEntity<T>> extends ResourceSupport{

	@JsonUnwrapped
	protected final T entity;
	
	public BaseResource(T entity) {
		this.entity = entity;
	}

	public T getEntity() {
		return entity;
	}
	
	@JsonProperty("id")
    public Long getResourceId() {
		return entity.getId();
    }
	
}
