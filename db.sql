DROP DATABASE IF EXISTS AM_DB_25_03;
CREATE DATABASE AM_DB_25_03;
USE AM_DB_25_03;

CREATE TABLE article(
                        id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
                        regDate DATETIME NOT NULL,
                        updateDate DATETIME NOT NULL,
                        title CHAR(100) NOT NULL,
                        `body` TEXT NOT NULL
);

CREATE TABLE `member`(
                         id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
                         regDate DATETIME NOT NULL,
                         updateDate DATETIME NOT NULL,
                         loginId CHAR(30) NOT NULL,
                         loginPw CHAR(100) NOT NULL,
                         `name` CHAR(100) NOT NULL
);


INSERT INTO article
SET regDate = NOW(),
    updateDate = NOW(),
    title = '제목1',
    `body` = '내용1';

INSERT INTO article
SET regDate = NOW(),
    updateDate = NOW(),
    title = '제목2',
    `body` = '내용2';

INSERT INTO article
SET regDate = NOW(),
    updateDate = NOW(),
    title = '제목3',
    `body` = '내용3';

INSERT INTO MEMBER
SET regDate = NOW(),
    updateDate = NOW(),
    loginId = 'Id',
    loginPw = 'Pw'
                  `name` = '이름';

#####################################################

SELECT *
FROM article
ORDER BY id DESC;

SELECT NOW();

SELECT '제목1';

SELECT CONCAT('제목',' 1');

SELECT SUBSTRING(RAND() * 1000 FROM 1 FOR 2);

INSERT INTO articleset regDate = NOW(),updateDate = NOW(),title = CONCAT('제목',SUBSTRING(RAND() * 1000 FROM 1 FOR 2)),`body` = CONCAT('내용',SUBSTRING(RAND() * 1000 FROM 1 FOR 2));

INSERT INTO article
SET regDate = NOW(),
    updateDate = NOW(),
    title = CONCAT('제목',SUBSTRING(RAND() * 1000 FROM 1 FOR 2)),
    `body` = CONCAT('내용',SUBSTRING(RAND() * 1000 FROM 1 FOR 2));

INSERT INTO MEMBER
SET regDate = NOW(),
    updateDate = NOW(),
    loginId = CONCAT('아이디',SUBSTRING(RAND() * 1000 FROM 1 FOR 2)),
    loginPw = CONCAT('비밀번호',SUBSTRING(RAND() * 1000 FROM 1 FOR 2)),
    `name` = CONCAT('이름',SUBSTRING(RAND() * 1000 FROM 1 FOR 2)),;

UPDATE article
SET updateDate = NOW(),
    title = 'title1',
    `body` = 'body1'
WHERE id = 3;

UPDATE article
SET updateDate = NOW(),
    `body` = 'body1'
WHERE id = 1;

SELECT * FROM article;

SELECT COUNT(*)
FROM article
WHERE id = 5;

UPDATE article
SET updateDate = NOW(),
    title = 'title1',
    `body` = 'body1'
WHERE id = 5;