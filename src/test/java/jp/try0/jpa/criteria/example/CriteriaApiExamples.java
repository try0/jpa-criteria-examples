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
import javax.persistence.criteria.Root;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import jp.try0.jpa.criteria.example.entity.UserAccount;
import jp.try0.jpa.criteria.example.entity.UserAccount_;

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
		CriteriaQuery<UserAccount> query = builder.createQuery(UserAccount.class);
		Root<UserAccount> root = query.from(UserAccount.class);
		query.select(root);

		List<UserAccount> accounts = em.createQuery(query).getResultList();
	}

	@Test
	public void multiSelect() {

		EntityManager em = factory.createEntityManager();

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<UserAccount> query = builder.createQuery(UserAccount.class);
		Root<UserAccount> root = query.from(UserAccount.class);
		query.multiselect(root.get(UserAccount_.name));

		TypedQuery<UserAccount> typedQuery = em.createQuery(query);
		((org.eclipse.persistence.jpa.JpaQuery) typedQuery).getDatabaseQuery().dontMaintainCache();
		List<UserAccount> accounts = typedQuery.getResultList();

	}

	@Test
	public void selectWithCondition() {

		EntityManager em = factory.createEntityManager();

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<UserAccount> query = builder.createQuery(UserAccount.class);
		Root<UserAccount> root = query.from(UserAccount.class);
		query.select(root);
		query.where(builder.equal(root.get(UserAccount_.userId), "0000"));

		List<UserAccount> accounts = em.createQuery(query).getResultList();
	}

	@Test
	public void update() {

		EntityManager em = factory.createEntityManager();

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaUpdate<UserAccount> update = builder.createCriteriaUpdate(UserAccount.class);
		Root<UserAccount> root = update.from(UserAccount.class);
		update.set(root.get(UserAccount_.name), "new-name");
		update.where(builder.equal(root.get(UserAccount_.userId), "0000"));

		em.getTransaction().begin();
		em.createQuery(update).executeUpdate();
		em.getTransaction().commit();
	}

	@Test
	public void delete() {

		EntityManager em = factory.createEntityManager();

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaDelete<UserAccount> delete = builder.createCriteriaDelete(UserAccount.class);
		Root<UserAccount> root = delete.from(UserAccount.class);
		delete.where(builder.equal(root.get(UserAccount_.userId), "0000"));

		em.getTransaction().begin();
		em.createQuery(delete).executeUpdate();
		em.getTransaction().commit();
	}

	@Test
	public void count() {
		EntityManager em = factory.createEntityManager();

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Long> countQuery = builder.createQuery(Long.class);
		Root<UserAccount> root = countQuery.from(UserAccount.class);
		countQuery.select(builder.count(root));

		Long count = em.createQuery(countQuery).getSingleResult();
	}

	@Test
	public void max() {
		EntityManager em = factory.createEntityManager();

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Integer> maxQuery = builder.createQuery(Integer.class);
		Root<UserAccount> root = maxQuery.from(UserAccount.class);
		maxQuery.select(builder.max(root.get(UserAccount_.userNumber)));

		Integer maxUserNumber = em.createQuery(maxQuery).getSingleResult();

	}

	@Test
	public void min() {
		EntityManager em = factory.createEntityManager();

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Integer> maxQuery = builder.createQuery(Integer.class);
		Root<UserAccount> root = maxQuery.from(UserAccount.class);
		maxQuery.select(builder.min(root.get(UserAccount_.userNumber)));

		Integer minUserNumber = em.createQuery(maxQuery).getSingleResult();
	}
}
