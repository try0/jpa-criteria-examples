package jp.try0.jpa.criteria.example.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "m_group")
@NamedQuery(name = "Group.findAll", query = "SELECT u FROM Group u")
public class Group implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "group_id")
	private String groupId;

	private String name;

	public Group() {
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}