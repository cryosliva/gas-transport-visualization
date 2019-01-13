[![Build Status](https://travis-ci.org/cryosliva/gas-transport-visualization.svg?branch=master)](https://travis-ci.org/cryosliva/gas-transport-visualization)

# Gas Transport Visualisation
Web visualisation of russian gas pipeline system

### Параметры запуска:
Для запуска нужно указать параметр JVM -Dspring.profiles.active=

* dev для локальной сборки
* prod для сборки продакшена

Для создания локальной базы необходимо создать бд из-под имени postgres и паролем 12345
Скрипт генератора схемы:
```
create sequence hibernate_sequence;

create table gtv_user
(
	id bigserial not null
		constraint gtv_user_pkey
			primary key,
	datetime_create timestamp,
	non_expired boolean,
	non_locked boolean,
	credentials_non_expired boolean,
	enadled boolean,
	password varchar(255),
	username varchar(255)
		constraint uk_84aq4ed27y31ytqwi8873bq9j
			unique
);

create table gtv_user_role
(
	user_id bigint not null
		constraint fkt90ipmsisvmkrwwwpqb9c3nuy
			references gtv_user,
	user_role varchar(255)
);

```
