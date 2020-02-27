CREATE USER IF NOT EXISTS jwduser IDENTIFIED WITH mysql_native_password BY 'pass';

DROP DATABASE IF EXISTS ski_skokovi;
CREATE DATABASE ski_skokovi DEFAULT CHARACTER SET utf8;

USE ski_skokovi;

GRANT ALL ON ski_skokovi.* TO 'jwduser'@'%';

FLUSH PRIVILEGES;