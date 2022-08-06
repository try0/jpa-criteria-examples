# jpa-criteria-examples
JPA Criteria API examples


EclipseLink, H2DB


[examples](https://github.com/try0/jpa-criteria-examples/blob/master/src/test/java/jp/try0/jpa/criteria/example/CriteriaApiExamples.java)


When the tests are run, the table is created in the in-memory h2db and the executed sql is output to the log.

```
情報: start initialize [日 8月 07 08:59:19 JST 2022]
[EL Info]: 2022-08-07 08:59:20.138--ServerSession(2144644334)--EclipseLink, version: Eclipse Persistence Services - 2.7.10.v20211216-fe64cd39c3
[EL Fine]: sql: 2022-08-07 08:59:20.774--ServerSession(2144644334)--Connection(1402333753)--CREATE TABLE m_group (group_id VARCHAR NOT NULL, NAME VARCHAR, PRIMARY KEY (group_id))
[EL Fine]: sql: 2022-08-07 08:59:20.787--ServerSession(2144644334)--Connection(1402333753)--CREATE TABLE m_user (user_id VARCHAR NOT NULL, age INTEGER, NAME VARCHAR, PASSWORD VARCHAR, user_number INTEGER, PRIMARY KEY (user_id))
[EL Fine]: sql: 2022-08-07 08:59:20.79--ServerSession(2144644334)--Connection(1402333753)--CREATE TABLE user_group (group_id VARCHAR NOT NULL, user_id VARCHAR NOT NULL, PRIMARY KEY (group_id, user_id))
[EL Fine]: sql: 2022-08-07 08:59:20.792--ServerSession(2144644334)--Connection(1402333753)--CREATE TABLE email_address (user_id VARCHAR NOT NULL, email_address VARCHAR, PRIMARY KEY (user_id))
[EL Fine]: sql: 2022-08-07 08:59:20.794--ServerSession(2144644334)--Connection(1402333753)--ALTER TABLE email_address ADD CONSTRAINT FK_email_address_user_id FOREIGN KEY (user_id) REFERENCES m_user (user_id)
[EL Fine]: sql: 2022-08-07 08:59:21.043--ClientSession(219812012)--Connection(1402333753)--INSERT INTO m_user (user_id, age, NAME, PASSWORD, user_number) VALUES (?, ?, ?, ?, ?)
	bind => [uid1, 43, UserName1, 93439e71, 1]
[EL Fine]: sql: 2022-08-07 08:59:21.046--ClientSession(219812012)--Connection(1402333753)--INSERT INTO m_user (user_id, age, NAME, PASSWORD, user_number) VALUES (?, ?, ?, ?, ?)
	bind => [uid3, 47, UserName3, 1f1693e1, 3]
[EL Fine]: sql: 2022-08-07 08:59:21.046--ClientSession(219812012)--Connection(1402333753)--INSERT INTO m_user (user_id, age, NAME, PASSWORD, user_number) VALUES (?, ?, ?, ?, ?)
	bind => [uid4, 86, UserName4, 63843736, 4]
[EL Fine]: sql: 2022-08-07 08:59:21.047--ClientSession(219812012)--Connection(1402333753)--INSERT INTO m_user (user_id, age, NAME, PASSWORD, user_number) VALUES (?, ?, ?, ?, ?)
	bind => [uid0, 18, UserName0, 4bf5deee, 0]
[EL Fine]: sql: 2022-08-07 08:59:21.047--ClientSession(219812012)--Connection(1402333753)--INSERT INTO m_user (user_id, age, NAME, PASSWORD, user_number) VALUES (?, ?, ?, ?, ?)
	bind => [uid2, 37, UserName2, 74f0d62c, 2]
[EL Fine]: sql: 2022-08-07 08:59:21.047--ClientSession(219812012)--Connection(1402333753)--INSERT INTO email_address (user_id, email_address) VALUES (?, ?)
	bind => [uid2, EmailAddress2@example.com]
[EL Fine]: sql: 2022-08-07 08:59:21.049--ClientSession(219812012)--Connection(1402333753)--INSERT INTO email_address (user_id, email_address) VALUES (?, ?)
	bind => [uid0, EmailAddress0@example.com]
[EL Fine]: sql: 2022-08-07 08:59:21.05--ClientSession(219812012)--Connection(1402333753)--INSERT INTO email_address (user_id, email_address) VALUES (?, ?)
	bind => [uid1, EmailAddress1@example.com]
[EL Fine]: sql: 2022-08-07 08:59:21.05--ClientSession(219812012)--Connection(1402333753)--INSERT INTO email_address (user_id, email_address) VALUES (?, ?)
	bind => [uid4, EmailAddress4@example.com]
[EL Fine]: sql: 2022-08-07 08:59:21.051--ClientSession(219812012)--Connection(1402333753)--INSERT INTO email_address (user_id, email_address) VALUES (?, ?)
	bind => [uid3, EmailAddress3@example.com]
[EL Fine]: sql: 2022-08-07 08:59:21.051--ClientSession(219812012)--Connection(1402333753)--INSERT INTO m_group (group_id, NAME) VALUES (?, ?)
	bind => [gid4, GroupName4]
[EL Fine]: sql: 2022-08-07 08:59:21.051--ClientSession(219812012)--Connection(1402333753)--INSERT INTO m_group (group_id, NAME) VALUES (?, ?)
	bind => [gid0, GroupName0]
[EL Fine]: sql: 2022-08-07 08:59:21.052--ClientSession(219812012)--Connection(1402333753)--INSERT INTO m_group (group_id, NAME) VALUES (?, ?)
	bind => [gid2, GroupName2]
[EL Fine]: sql: 2022-08-07 08:59:21.052--ClientSession(219812012)--Connection(1402333753)--INSERT INTO m_group (group_id, NAME) VALUES (?, ?)
	bind => [gid1, GroupName1]
[EL Fine]: sql: 2022-08-07 08:59:21.052--ClientSession(219812012)--Connection(1402333753)--INSERT INTO m_group (group_id, NAME) VALUES (?, ?)
	bind => [gid3, GroupName3]
[EL Fine]: sql: 2022-08-07 08:59:21.054--ClientSession(219812012)--Connection(1402333753)--INSERT INTO user_group (group_id, user_id) VALUES (?, ?)
	bind => [gid4, uid4]
[EL Fine]: sql: 2022-08-07 08:59:21.054--ClientSession(219812012)--Connection(1402333753)--INSERT INTO user_group (group_id, user_id) VALUES (?, ?)
	bind => [gid0, uid0]
[EL Fine]: sql: 2022-08-07 08:59:21.054--ClientSession(219812012)--Connection(1402333753)--INSERT INTO user_group (group_id, user_id) VALUES (?, ?)
	bind => [gid1, uid1]
[EL Fine]: sql: 2022-08-07 08:59:21.055--ClientSession(219812012)--Connection(1402333753)--INSERT INTO user_group (group_id, user_id) VALUES (?, ?)
	bind => [gid3, uid3]
[EL Fine]: sql: 2022-08-07 08:59:21.055--ClientSession(219812012)--Connection(1402333753)--INSERT INTO user_group (group_id, user_id) VALUES (?, ?)
	bind => [gid2, uid2]
情報: end initialize [日 8月 07 08:59:21 JST 2022]
情報: ========= Sub Query ========= [日 8月 07 08:59:21 JST 2022]
[EL Fine]: sql: 2022-08-07 08:59:21.133--ServerSession(2144644334)--Connection(1402333753)--SELECT t0.user_id, t0.age, t0.NAME, t0.PASSWORD, t0.user_number FROM m_user t0 WHERE t0.user_id IN (SELECT t1.user_id FROM email_address t1 WHERE t1.email_address LIKE ?)
	bind => [%@gmail.com]

情報: ========= Select All ========= [日 8月 07 08:59:21 JST 2022]
[EL Fine]: sql: 2022-08-07 08:59:21.163--ServerSession(2144644334)--Connection(1402333753)--SELECT user_id, age, NAME, PASSWORD, user_number FROM m_user

情報: ========= Fetch Left Join ========= [日 8月 07 08:59:21 JST 2022]
[EL Fine]: sql: 2022-08-07 08:59:21.192--ServerSession(2144644334)--Connection(1402333753)--SELECT t1.user_id, t1.age, t1.NAME, t1.PASSWORD, t1.user_number, t0.user_id, t0.email_address FROM {oj m_user t1 LEFT OUTER JOIN email_address t0 ON (t0.user_id = t1.user_id)}

情報: ========= Delete ========= [日 8月 07 08:59:21 JST 2022]
[EL Fine]: sql: 2022-08-07 08:59:21.206--ClientSession(1232703108)--Connection(1402333753)--DELETE FROM m_user WHERE (user_id = ?)
	bind => [0000]

情報: ========= Select Using Function ========= [日 8月 07 08:59:21 JST 2022]
[EL Fine]: sql: 2022-08-07 08:59:21.214--ServerSession(2144644334)--Connection(1402333753)--SELECT user_id, UUID() FROM m_user

情報: ========= Fetch Inner Join ========= [日 8月 07 08:59:21 JST 2022]
[EL Fine]: sql: 2022-08-07 08:59:21.236--ServerSession(2144644334)--Connection(1402333753)--SELECT t1.user_id, t1.age, t1.NAME, t1.PASSWORD, t1.user_number, t0.user_id, t0.email_address FROM email_address t0, m_user t1 WHERE (t0.user_id = t1.user_id)

情報: ========= Group By & Having ========= [日 8月 07 08:59:21 JST 2022]
[EL Fine]: sql: 2022-08-07 08:59:21.244--ServerSession(2144644334)--Connection(1402333753)--SELECT NAME, SUM(user_number) FROM m_user GROUP BY NAME HAVING (SUM(user_number) > ?)
	bind => [100]

情報: ========= Update ========= [日 8月 07 08:59:21 JST 2022]
[EL Fine]: sql: 2022-08-07 08:59:21.257--ClientSession(382252989)--Connection(1402333753)--UPDATE m_user SET NAME = ? WHERE (user_id = ?)
	bind => [new-name, 0000]

情報: ========= Inner Join ========= [日 8月 07 08:59:21 JST 2022]
[EL Fine]: sql: 2022-08-07 08:59:21.266--ServerSession(2144644334)--Connection(1402333753)--SELECT t1.user_id, t1.age, t1.NAME, t1.PASSWORD, t1.user_number FROM email_address t0, m_user t1 WHERE (t0.email_address LIKE ? AND (t0.user_id = t1.user_id))
	bind => [a%]

情報: ========= Multi Select ========= [日 8月 07 08:59:21 JST 2022]
[EL Fine]: sql: 2022-08-07 08:59:21.279--ServerSession(2144644334)--Connection(1402333753)--SELECT user_id, NAME FROM m_user

情報: ========= Avg ========= [日 8月 07 08:59:21 JST 2022]
[EL Fine]: sql: 2022-08-07 08:59:21.283--ServerSession(2144644334)--Connection(1402333753)--SELECT AVG(age) FROM m_user

情報: ========= Max ========= [日 8月 07 08:59:21 JST 2022]
[EL Fine]: sql: 2022-08-07 08:59:21.29--ServerSession(2144644334)--Connection(1402333753)--SELECT MAX(age) FROM m_user

情報: ========= Min ========= [日 8月 07 08:59:21 JST 2022]
[EL Fine]: sql: 2022-08-07 08:59:21.294--ServerSession(2144644334)--Connection(1402333753)--SELECT MIN(age) FROM m_user

情報: ========= Count ========= [日 8月 07 08:59:21 JST 2022]
[EL Fine]: sql: 2022-08-07 08:59:21.299--ServerSession(2144644334)--Connection(1402333753)--SELECT COUNT(user_id) FROM m_user

情報: ========= Group By ========= [日 8月 07 08:59:21 JST 2022]
[EL Fine]: sql: 2022-08-07 08:59:21.304--ServerSession(2144644334)--Connection(1402333753)--SELECT NAME, SUM(user_number) FROM m_user GROUP BY NAME

情報: ========= Select With Condition ========= [日 8月 07 08:59:21 JST 2022]
[EL Fine]: sql: 2022-08-07 08:59:21.309--ServerSession(2144644334)--Connection(1402333753)--SELECT user_id, age, NAME, PASSWORD, user_number FROM m_user WHERE (user_id = ?)
	bind => [0000]

情報: ========= Left Join ========= [日 8月 07 08:59:21 JST 2022]
[EL Fine]: sql: 2022-08-07 08:59:21.314--ServerSession(2144644334)--Connection(1402333753)--SELECT t1.user_id, t1.age, t1.NAME, t1.PASSWORD, t1.user_number FROM {oj m_user t1 LEFT OUTER JOIN email_address t0 ON (t0.user_id = t1.user_id)} WHERE t0.email_address LIKE ?
	bind => [a%]

情報: ========= Cross Join (No relations) ========= [日 8月 07 08:59:21 JST 2022]
[EL Fine]: sql: 2022-08-07 08:59:21.322--ServerSession(2144644334)--Connection(1402333753)--SELECT t0.user_id, t0.age, t0.NAME, t0.PASSWORD, t0.user_number FROM m_user t0, user_group t1 WHERE ((t0.user_id = t1.user_id) AND (t1.group_id IN (?, ?)))
	bind => [gid1, gid2]




```
