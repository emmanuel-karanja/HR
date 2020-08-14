package com.atticus.hr.domain;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class AbstractId<ID extends Serializable> implements Serializable  {

	private static final long serialVersionUID = 1L;
	
	@Id 
	protected ID id;

	public ID getId() {
		return id;
	}

	public void setId(ID id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return id.toString();
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		
		AbstractId<?> other = (AbstractId<?>) obj;
		return Objects.equals(this.id, other.id);
	}
}
