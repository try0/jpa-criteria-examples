package jp.try0.jpa.criteria.example;

import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Tuple;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

import org.junit.FixMethodOrder;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.runners.MethodSorters;

import jp.try0.jpa.criteria.example.entity.EMailAddress;
import jp.try0.jpa.criteria.example.entity.EMailAddress_;
import jp.try0.jpa.criteria.example.entity.User;
import jp.try0.jpa.criteria.example.entity.User_;

@TestInstance(Lifecycle.PER_CLASS)
@FixMethodOrder(MethodSorters.JVM)
public class CriteriaApiExamples {

	private static final String LOG_SEPARATOR = "=========";

	private static Logger logger = Logger.getLogger(CriteriaApiExamples.class.getSimpleName());

	private static EntityManagerFactory factory;

	@BeforeAll
	public static void before(TestInfo testInfo) {
		logger.info("start initialize");

		factory = Persistence.createEntityManagerFactory("criteria-examples");
		EntityManager em = factory.createEntityManager();
		em.close();

		logger.info("end initialize");
	}

	@BeforeEach
	public void beforeEach(TestInfo testInfo) {
		logger.info(LOG_SEPARATOR + " " + testInfo.getDisplayName() + " " + LOG_SEPARATOR);
	}

	@AfterAll
	public static void after(TestInfo testInfo) {
		factory.close();
	}

	@AfterEach
	public void afterEach(TestInfo testInfo) {
		System.out.println("");
	}

	@DisplayName("Select All")
	@Test
	public void selectAll() {
		EntityManager em = factory.createEntityManager();

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<User> query = builder.createQuery(User.class);
		Root<User> root = query.from(User.class);
		query.select(root);

		List<User> accounts = em.createQuery(query).getResultList();

		em.close();
	}

	@DisplayName("Multi Select")
	@Test
	public void multiSelect() {
		EntityManager em = factory.createEntityManager();

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<User> query = builder.createQuery(User.class);
		Root<User> root = query.from(User.class);
		query.multiselect(root.get(User_.name));

		TypedQuery<User> typedQuery = em.createQuery(query);
		((org.eclipse.persistence.jpa.JpaQuery) typedQuery).getDatabaseQuery().dontMaintainCache();
		List<User> accounts = typedQuery.getResultList();

		em.close();
	}

	@DisplayName("Select With Condition")
	@Test
	public void selectWithCondition() {
		EntityManager em = factory.createEntityManager();

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<User> query = builder.createQuery(User.class);
		Root<User> root = query.from(User.class);
		query.select(root);
		query.where(builder.equal(root.get(User_.userId), "0000"));

		List<User> accounts = em.createQuery(query).getResultList();

		em.close();
	}

	@DisplayName("Update")
	@Test
	public void update() {
		EntityManager em = factory.createEntityManager();

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaUpdate<User> update = builder.createCriteriaUpdate(User.class);
		Root<User> root = update.from(User.class);
		update.set(root.get(User_.name), "new-name");
		update.where(builder.equal(root.get(User_.userId), "0000"));

		em.getTransaction().begin();
		em.createQuery(update).executeUpdate();
		em.getTransaction().commit();

		em.close();
	}

	@DisplayName("Delete")
	@Test
	public void delete() {
		EntityManager em = factory.createEntityManager();

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaDelete<User> delete = builder.createCriteriaDelete(User.class);
		Root<User> root = delete.from(User.class);
		delete.where(builder.equal(root.get(User_.userId), "0000"));

		em.getTransaction().begin();
		em.createQuery(delete).executeUpdate();
		em.getTransaction().commit();

		em.close();
	}

	@DisplayName("Count")
	@Test
	public void count() {
		EntityManager em = factory.createEntityManager();

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Long> countQuery = builder.createQuery(Long.class);
		Root<User> root = countQuery.from(User.class);
		countQuery.select(builder.count(root));

		Long count = em.createQuery(countQuery).getSingleResult();

		em.close();
	}

	@DisplayName("Max")
	@Test
	public void max() {
		EntityManager em = factory.createEntityManager();

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Integer> maxQuery = builder.createQuery(Integer.class);
		Root<User> root = maxQuery.from(User.class);
		maxQuery.select(builder.max(root.get(User_.age)));

		Integer maxUserAge = em.createQuery(maxQuery).getSingleResult();

		em.close();
	}

	@DisplayName("Min")
	@Test
	public void min() {
		EntityManager em = factory.createEntityManager();

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Integer> minQuery = builder.createQuery(Integer.class);
		Root<User> root = minQuery.from(User.class);
		minQuery.select(builder.min(root.get(User_.age)));

		Integer minUserAge = em.createQuery(minQuery).getSingleResult();

		em.close();
	}

	@DisplayName("Avg")
	@Test
	public void avg() {
		EntityManager em = factory.createEntityManager();

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Double> avgQuery = builder.createQuery(Double.class);
		Root<User> root = avgQuery.from(User.class);
		avgQuery.select(builder.avg(root.get(User_.age)));

		Double avgUserAge = em.createQuery(avgQuery).getSingleResult();

		em.close();
	}

	@DisplayName("Left Join")
	@Test
	public void leftJoin() {
		EntityManager em = factory.createEntityManager();

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<User> query = builder.createQuery(User.class);
		Root<User> root = query.from(User.class);
		query.select(root);
		Join<User, EMailAddress> join = root.join(User_.eMailAddresses, JoinType.LEFT);
		query.where(builder.like(join.get(EMailAddress_.eMailAddress), "a%"));

		List<User> accounts = em.createQuery(query).getResultList();

		em.close();
	}

	@DisplayName("Inner Join")
	@Test
	public void innerJoin() {
		EntityManager em = factory.createEntityManager();

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<User> query = builder.createQuery(User.class);
		Root<User> root = query.from(User.class);
		query.select(root);
		Join<User, EMailAddress> join = root.join(User_.eMailAddresses, JoinType.INNER);
		query.where(builder.like(join.get(EMailAddress_.eMailAddress), "a%"));

		List<User> accounts = em.createQuery(query).getResultList();

		em.close();
	}

	@DisplayName("Fetch Left Join")
	@Test
	public void fetchLeftJoin() {
		EntityManager em = factory.createEntityManager();

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<User> query = builder.createQuery(User.class);
		Root<User> root = query.from(User.class);
		query.select(root);
		root.fetch(User_.eMailAddresses, JoinType.LEFT);

		List<User> accounts = em.createQuery(query).getResultList();

		em.close();
	}

	@DisplayName("Fetch Inner Join")
	@Test
	public void fetchInnerJoin() {
		EntityManager em = factory.createEntityManager();

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<User> query = builder.createQuery(User.class);
		Root<User> root = query.from(User.class);
		query.select(root);
		root.fetch(User_.eMailAddresses, JoinType.INNER);

		List<User> accounts = em.createQuery(query).getResultList();

		em.close();
	}

	@DisplayName("Group By")
	@Test
	public void groupBy() {
		EntityManager em = factory.createEntityManager();

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Tuple> query = builder.createQuery(Tuple.class);
		Root<User> root = query.from(User.class);
		query.select(builder.tuple(root.get(User_.name), builder.sum(root.get(User_.userNumber))))
				.groupBy(root.get(User_.name));

		List<Tuple> tuples = em.createQuery(query).getResultList();

		em.close();
	}

	@DisplayName("Group By & Having")
	@Test
	public void groupByHaving() {
		EntityManager em = factory.createEntityManager();

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Tuple> query = builder.createQuery(Tuple.class);
		Root<User> root = query.from(User.class);
		Expression<Integer> sum = builder.sum(root.get(User_.userNumber));
		query.select(builder.tuple(root.get(User_.name), sum))
				.groupBy(root.get(User_.name))
				.having(builder.greaterThan(sum, 100));

		List<Tuple> tuples = em.createQuery(query).getResultList();

		em.close();
	}

	@DisplayName("Sub Query")
	@Test
	public void subQuery() {
		EntityManager em = factory.createEntityManager();

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<User> query = builder.createQuery(User.class);
		Root<User> root = query.from(User.class);
		query.select(root);

		Subquery<String> subquery = query.subquery(String.class);
		Root<EMailAddress> subRoot = subquery.from(EMailAddress.class);
		subquery.select(subRoot.get(EMailAddress_.userId));
		subquery.where(builder.like(subRoot.get(EMailAddress_.eMailAddress), "%@gmail.com"));

		query.where(root.get(User_.userId).in(subquery));

		List<User> accounts = em.createQuery(query).getResultList();

		em.close();
	}

}
