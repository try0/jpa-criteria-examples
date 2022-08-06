package jp.try0.jpa.criteria.example.entity;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "user_group")
@NamedQuery(name = "UserGroup.findAll", query = "SELECT u FROM UserGroup u")
public class UserGroup implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private UserGroupPK id;

	public UserGroupPK getId() {
		return id;
	}

	public void setId(UserGroupPK id) {
		this.id = id;
	}

}