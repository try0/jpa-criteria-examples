package jp.try0.jpa.criteria.example.entity;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(UserGroup.class)
public class UserGroup_ {
	public static volatile SingularAttribute<UserGroup, UserGroupPK> id;
}