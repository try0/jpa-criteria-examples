package jp.try0.jpa.criteria.example.entity;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(UserGroupPK.class)
public class UserGroupPK_ {
	public static volatile SingularAttribute<UserGroupPK, String> userId;
	public static volatile SingularAttribute<UserGroupPK, String> gorupId;
}