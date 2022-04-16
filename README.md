# jpa-criteria-examples
JPA Criteria API examples


EclipseLink, H2DB


[examples](https://github.com/try0/jpa-criteria-examples/blob/master/src/test/java/jp/try0/jpa/criteria/example/CriteriaApiExamples.java)


When Junit is run, a table is created in in-memory h2db and the executed sql is output to the log.
```
情報: start initialize [土 4月 16 21:14:44 JST 2022]
[EL Info]: 2022-04-16 21:14:45.826--ServerSession(1659309731)--EclipseLink, version: Eclipse Persistence Services - 2.7.10.v20211216-fe64cd39c3
[EL Fine]: sql: 2022-04-16 21:14:46.556--ServerSession(1659309731)--Connection(1590481849)--CREATE TABLE m_user (user_id VARCHAR NOT NULL, age INTEGER, NAME VARCHAR, PASSWORD VARCHAR, user_number INTEGER, PRIMARY KEY (user_id))
[EL Fine]: sql: 2022-04-16 21:14:46.566--ServerSession(1659309731)--Connection(1590481849)--CREATE TABLE email_address (user_id VARCHAR NOT NULL, email_address VARCHAR, PRIMARY KEY (user_id))
[EL Fine]: sql: 2022-04-16 21:14:46.568--ServerSession(1659309731)--Connection(1590481849)--ALTER TABLE email_address ADD CONSTRAINT FK_email_address_user_id FOREIGN KEY (user_id) REFERENCES m_user (user_id)
情報: end initialize [土 4月 16 21:14:46 JST 2022]
情報: ========= Sub Query ========= [土 4月 16 21:14:46 JST 2022]
[EL Fine]: sql: 2022-04-16 21:14:46.676--ServerSession(1659309731)--Connection(1590481849)--SELECT t0.user_id, t0.age, t0.NAME, t0.PASSWORD, t0.user_number FROM m_user t0 WHERE t0.user_id IN (SELECT t1.user_id FROM email_address t1 WHERE t1.email_address LIKE ?)
	bind => [%@gmail.com]

情報: ========= Select All ========= [土 4月 16 21:14:46 JST 2022]
[EL Fine]: sql: 2022-04-16 21:14:46.7--ServerSession(1659309731)--Connection(1590481849)--SELECT user_id, age, NAME, PASSWORD, user_number FROM m_user

情報: ========= Fetch Left Join ========= [土 4月 16 21:14:46 JST 2022]
[EL Fine]: sql: 2022-04-16 21:14:46.713--ServerSession(1659309731)--Connection(1590481849)--SELECT t1.user_id, t1.age, t1.NAME, t1.PASSWORD, t1.user_number, t0.user_id, t0.email_address FROM {oj m_user t1 LEFT OUTER JOIN email_address t0 ON (t0.user_id = t1.user_id)}

情報: ========= Delete ========= [土 4月 16 21:14:46 JST 2022]
[EL Fine]: sql: 2022-04-16 21:14:46.722--ClientSession(356691811)--Connection(1590481849)--DELETE FROM m_user WHERE (user_id = ?)
	bind => [0000]

情報: ========= Fetch Inner Join ========= [土 4月 16 21:14:46 JST 2022]
[EL Fine]: sql: 2022-04-16 21:14:46.737--ServerSession(1659309731)--Connection(1590481849)--SELECT t1.user_id, t1.age, t1.NAME, t1.PASSWORD, t1.user_number, t0.user_id, t0.email_address FROM email_address t0, m_user t1 WHERE (t0.user_id = t1.user_id)

情報: ========= Group By & Having ========= [土 4月 16 21:14:46 JST 2022]
[EL Fine]: sql: 2022-04-16 21:14:46.743--ServerSession(1659309731)--Connection(1590481849)--SELECT NAME, SUM(user_number) FROM m_user GROUP BY NAME HAVING (SUM(user_number) > ?)
	bind => [100]

情報: ========= Update ========= [土 4月 16 21:14:46 JST 2022]
[EL Fine]: sql: 2022-04-16 21:14:46.755--ClientSession(1779479139)--Connection(1590481849)--UPDATE m_user SET NAME = ? WHERE (user_id = ?)
	bind => [new-name, 0000]

情報: ========= Inner Join ========= [土 4月 16 21:14:46 JST 2022]
[EL Fine]: sql: 2022-04-16 21:14:46.764--ServerSession(1659309731)--Connection(1590481849)--SELECT t1.user_id, t1.age, t1.NAME, t1.PASSWORD, t1.user_number FROM email_address t0, m_user t1 WHERE (t0.email_address LIKE ? AND (t0.user_id = t1.user_id))
	bind => [a%]

情報: ========= Multi Select ========= [土 4月 16 21:14:46 JST 2022]
[EL Fine]: sql: 2022-04-16 21:14:46.777--ServerSession(1659309731)--Connection(1590481849)--SELECT user_id, NAME FROM m_user

情報: ========= Avg ========= [土 4月 16 21:14:46 JST 2022]
[EL Fine]: sql: 2022-04-16 21:14:46.781--ServerSession(1659309731)--Connection(1590481849)--SELECT AVG(age) FROM m_user

情報: ========= Max ========= [土 4月 16 21:14:46 JST 2022]
[EL Fine]: sql: 2022-04-16 21:14:46.79--ServerSession(1659309731)--Connection(1590481849)--SELECT MAX(age) FROM m_user

情報: ========= Min ========= [土 4月 16 21:14:46 JST 2022]
[EL Fine]: sql: 2022-04-16 21:14:46.795--ServerSession(1659309731)--Connection(1590481849)--SELECT MIN(age) FROM m_user

情報: ========= Count ========= [土 4月 16 21:14:46 JST 2022]
[EL Fine]: sql: 2022-04-16 21:14:46.799--ServerSession(1659309731)--Connection(1590481849)--SELECT COUNT(user_id) FROM m_user

情報: ========= Group By ========= [土 4月 16 21:14:46 JST 2022]
[EL Fine]: sql: 2022-04-16 21:14:46.803--ServerSession(1659309731)--Connection(1590481849)--SELECT NAME, SUM(user_number) FROM m_user GROUP BY NAME

情報: ========= Select With Condition ========= [土 4月 16 21:14:46 JST 2022]
[EL Fine]: sql: 2022-04-16 21:14:46.806--ServerSession(1659309731)--Connection(1590481849)--SELECT user_id, age, NAME, PASSWORD, user_number FROM m_user WHERE (user_id = ?)
	bind => [0000]

情報: ========= Left Join ========= [土 4月 16 21:14:46 JST 2022]
[EL Fine]: sql: 2022-04-16 21:14:46.811--ServerSession(1659309731)--Connection(1590481849)--SELECT t1.user_id, t1.age, t1.NAME, t1.PASSWORD, t1.user_number FROM {oj m_user t1 LEFT OUTER JOIN email_address t0 ON (t0.user_id = t1.user_id)} WHERE t0.email_address LIKE ?
	bind => [a%]


```
