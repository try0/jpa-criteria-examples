package jp.try0.jpa.criteria.example.entity;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(EMailAddress.class)
public class EMailAddress_ {
	public static volatile SingularAttribute<EMailAddress, String> userId;
	public static volatile SingularAttribute<EMailAddress, String> eMailAddress;

}