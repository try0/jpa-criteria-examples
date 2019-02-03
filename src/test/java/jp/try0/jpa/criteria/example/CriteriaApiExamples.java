package jp.try0.jpa.criteria.example;

import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import jp.try0.jpa.criteria.example.entity.EMailAddress;
import jp.try0.jpa.criteria.example.entity.EMailAddress_;
import jp.try0.jpa.criteria.example.entity.User;
import jp.try0.jpa.criteria.example.entity.User_;

@TestInstance(Lifecycle.PER_CLASS)
public class CriteriaApiExamples {

	private static final String LOG_SEPARATOR = "============= ";

	private static Logger logger = Logger.getLogger(CriteriaApiExamples.class.getSimpleName());

	private static EntityManagerFactory factory;

	@BeforeAll
	public static void before() {
		factory = Persistence.createEntityManagerFactory("criteria-examples");
	}

	@BeforeEach
	public void beforeEach(TestInfo testInfo) {
		testInfo.getTestMethod().ifPresent(m -> logger.info(LOG_SEPARATOR + "start " + m.getName()));
	}

	@AfterAll
	public static void after(TestInfo testInfo) {
		factory.close();
	}

	@AfterEach
	public void afterEach(TestInfo testInfo) {
		testInfo.getTestMethod().ifPresent(m -> logger.info(LOG_SEPARATOR + "end " + m.getName()));
	}

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

	@Test
	public void max() {
		EntityManager em = factory.createEntityManager();

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Integer> maxQuery = builder.createQuery(Integer.class);
		Root<User> root = maxQuery.from(User.class);
		maxQuery.select(builder.max(root.get(User_.userNumber)));

		Integer maxUserNumber = em.createQuery(maxQuery).getSingleResult();

		em.close();
	}

	@Test
	public void min() {
		EntityManager em = factory.createEntityManager();

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Integer> maxQuery = builder.createQuery(Integer.class);
		Root<User> root = maxQuery.from(User.class);
		maxQuery.select(builder.min(root.get(User_.userNumber)));

		Integer minUserNumber = em.createQuery(maxQuery).getSingleResult();

		em.close();
	}

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
}
