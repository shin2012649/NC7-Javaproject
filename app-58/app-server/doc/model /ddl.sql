-- users
DROP TABLE IF EXISTS users RESTRICT;

-- watch_lists
DROP TABLE IF EXISTS watch_lists RESTRICT;

-- films
DROP TABLE IF EXISTS films RESTRICT;

-- rating
DROP TABLE IF EXISTS rating RESTRICT;

-- comments
DROP TABLE IF EXISTS comments RESTRICT;

-- comments_like
DROP TABLE IF EXISTS comments_like RESTRICT;

-- films_genres
DROP TABLE IF EXISTS films_genres RESTRICT;

-- genres
DROP TABLE IF EXISTS genres RESTRICT;

-- films_staff
DROP TABLE IF EXISTS films_staff RESTRICT;

-- staff
DROP TABLE IF EXISTS staff RESTRICT;

-- films_actors
DROP TABLE IF EXISTS films_actors RESTRICT;

-- actors
DROP TABLE IF EXISTS actors RESTRICT;

-- roles
DROP TABLE IF EXISTS roles RESTRICT;

-- films_countries
DROP TABLE IF EXISTS films_countries RESTRICT;

-- countries
DROP TABLE IF EXISTS countries RESTRICT;

-- film_source
DROP TABLE IF EXISTS films_src RESTRICT;

-- src
DROP TABLE IF EXISTS src RESTRICT;

-- event
DROP TABLE IF EXISTS event RESTRICT;

-- manager
DROP TABLE IF EXISTS manager RESTRICT;

-- grade
DROP TABLE IF EXISTS grade RESTRICT;

-- event_application
DROP TABLE IF EXISTS event_application RESTRICT;

-- users
CREATE TABLE users (
	users_no          INTEGER      NOT NULL COMMENT 'users_no', -- users_no
	name              VARCHAR(50)  NOT NULL COMMENT 'name', -- name
	password          VARCHAR(50)  NOT NULL COMMENT 'password', -- password
	email             VARCHAR(40)  NOT NULL COMMENT 'email', -- email
	profile_image_url VARCHAR(255) NULL     COMMENT 'profile_image_url', -- profile_image_url
	date_of_birth     DATETIME     NOT NULL COMMENT 'date_of_birth', -- date_of_birth
	created_at        DATETIME     NOT NULL COMMENT 'created_at' -- created_at
)
COMMENT 'users';

-- users
ALTER TABLE users
	ADD CONSTRAINT PK_users -- users 기본키
	PRIMARY KEY (
	users_no -- users_no
	);

-- users 유니크 인덱스
CREATE UNIQUE INDEX UIX_users
	ON users ( -- users
		email ASC -- email
	);

-- watch_lists
CREATE TABLE watch_lists (
	users_no INTEGER NOT NULL COMMENT 'users_no', -- users_no
	films_no INTEGER NOT NULL COMMENT 'films_no' -- films_no
)
COMMENT 'watch_lists';

-- watch_lists
ALTER TABLE watch_lists
	ADD CONSTRAINT PK_watch_lists -- watch_lists 기본키
	PRIMARY KEY (
	users_no, -- users_no
	films_no  -- films_no
	);

-- films
CREATE TABLE films (
	films_no        INTEGER      NOT NULL COMMENT 'films_no', -- films_no
	title           VARCHAR(50)  NOT NULL COMMENT 'title', -- title
	films_image_url VARCHAR(255) NULL     COMMENT 'films_image_url', -- films_image_url
	grade_no        INTEGER      NOT NULL COMMENT 'grade_no', -- grade_no
	descriptions    VARCHAR(255) NOT NULL COMMENT 'descriptions', -- descriptions
	running_time    INTEGER      NOT NULL COMMENT 'running_time', -- running_time
	released_date   DATE         NOT NULL COMMENT 'released_date' -- released_date
)
COMMENT 'films';

-- films
ALTER TABLE films
	ADD CONSTRAINT PK_films -- films 기본키
	PRIMARY KEY (
	films_no -- films_no
	);

-- films 유니크 인덱스
CREATE UNIQUE INDEX UIX_films
	ON films ( -- films
		title ASC -- title
	);

-- rating
CREATE TABLE rating (
	users_no     INTEGER NOT NULL COMMENT 'users_no', -- users_no
	films_no     INTEGER NOT NULL COMMENT 'films_no', -- films_no
	rating_score DECIMAL NOT NULL COMMENT 'rating_score' -- rating_score
)
COMMENT 'rating';

-- rating
ALTER TABLE rating
	ADD CONSTRAINT PK_rating -- rating 기본키
	PRIMARY KEY (
	users_no, -- users_no
	films_no  -- films_no
	);

-- comments
CREATE TABLE comments (
	comments_no INTEGER      NOT NULL COMMENT 'comments_no', -- comments_no
	users_no    INTEGER      NOT NULL COMMENT 'users_no', -- users_no
	films_no    INTEGER      NOT NULL COMMENT 'films_no', -- films_no
	contents    VARCHAR(255) NOT NULL COMMENT 'contents', -- contents
	add_at      DATETIME     NOT NULL COMMENT 'add_at', -- add_at
	state       VARCHAR(255) NULL     COMMENT 'state' -- state
)
COMMENT 'comments';

-- comments
ALTER TABLE comments
	ADD CONSTRAINT PK_comments -- comments 기본키
	PRIMARY KEY (
	comments_no -- comments_no
	);

-- comments_like
CREATE TABLE comments_like (
	comments_no INTEGER NOT NULL COMMENT 'comments_no', -- comments_no
	users_no    INTEGER NOT NULL COMMENT 'users_no' -- users_no
)
COMMENT 'comments_like';

-- comments_like
ALTER TABLE comments_like
	ADD CONSTRAINT PK_comments_like -- comments_like 기본키
	PRIMARY KEY (
	comments_no, -- comments_no
	users_no     -- users_no
	);

-- films_genres
CREATE TABLE films_genres (
	films_genres_no INTEGER NOT NULL COMMENT 'films_genres_no', -- films_genres_no
	genres_no       INTEGER NOT NULL COMMENT 'genres_no', -- genres_no
	films_no        INTEGER NOT NULL COMMENT 'films_no' -- films_no
)
COMMENT 'films_genres';

-- films_genres
ALTER TABLE films_genres
	ADD CONSTRAINT PK_films_genres -- films_genres 기본키
	PRIMARY KEY (
	films_genres_no -- films_genres_no
	);

-- genres
CREATE TABLE genres (
	genres_no INTEGER     NOT NULL COMMENT 'genres_no', -- genres_no
	name      VARCHAR(50) NOT NULL COMMENT 'name' -- name
)
COMMENT 'genres';

-- genres
ALTER TABLE genres
	ADD CONSTRAINT PK_genres -- genres 기본키
	PRIMARY KEY (
	genres_no -- genres_no
	);

-- films_staff
CREATE TABLE films_staff (
	films_staff_no INTEGER     NOT NULL COMMENT 'films_staff_no', -- films_staff_no
	films_no       INTEGER     NOT NULL COMMENT 'films_no', -- films_no
	staff_no       INTEGER     NOT NULL COMMENT 'staff_no', -- staff_no
	role           VARCHAR(50) NOT NULL COMMENT 'role', -- role
	position       VARCHAR(50) NOT NULL COMMENT 'position' -- position
)
COMMENT 'films_staff';

-- films_staff
ALTER TABLE films_staff
	ADD CONSTRAINT PK_films_staff -- films_staff 기본키
	PRIMARY KEY (
	films_staff_no -- films_staff_no
	);

-- staff
CREATE TABLE staff (
	staff_no  INTEGER      NOT NULL COMMENT 'staff_no', -- staff_no
	name      VARCHAR(50)  NOT NULL COMMENT 'name', -- name
	image_url VARCHAR(255) NULL     COMMENT 'image_url' -- image_url
)
COMMENT 'staff';

-- staff
ALTER TABLE staff
	ADD CONSTRAINT PK_staff -- staff 기본키
	PRIMARY KEY (
	staff_no -- staff_no
	);

-- films_actors
CREATE TABLE films_actors (
	COL       INTEGER     NOT NULL COMMENT 'films_actors_no', -- films_actors_no
	films_no  INTEGER     NOT NULL COMMENT 'films_no', -- films_no
	roles_no  INTEGER     NOT NULL COMMENT 'roles_no', -- roles_no
	actors_no INTEGER     NOT NULL COMMENT 'actors_no', -- actors_no
	name      VARCHAR(50) NOT NULL COMMENT 'name' -- name
)
COMMENT 'films_actors';

-- films_actors
ALTER TABLE films_actors
	ADD CONSTRAINT PK_films_actors -- films_actors 기본키
	PRIMARY KEY (
	COL -- films_actors_no
	);

-- actors
CREATE TABLE actors (
	actors_no INTEGER      NOT NULL COMMENT 'actors_no', -- actors_no
	name      VARCHAR(50)  NOT NULL COMMENT 'name', -- name
	image_url VARCHAR(255) NULL     COMMENT 'image_url' -- image_url
)
COMMENT 'actors';

-- actors
ALTER TABLE actors
	ADD CONSTRAINT PK_actors -- actors 기본키
	PRIMARY KEY (
	actors_no -- actors_no
	);

-- roles
CREATE TABLE roles (
	roles_no INTEGER     NOT NULL COMMENT 'roles_no', -- roles_no
	name     VARCHAR(50) NOT NULL COMMENT 'name' -- name
)
COMMENT 'roles';

-- roles
ALTER TABLE roles
	ADD CONSTRAINT PK_roles -- roles 기본키
	PRIMARY KEY (
	roles_no -- roles_no
	);

-- films_countries
CREATE TABLE films_countries (
	films_no     INTEGER     NOT NULL COMMENT 'films_no', -- films_no
	countries_no VARCHAR(50) NOT NULL COMMENT 'countries_no' -- countries_no
)
COMMENT 'films_countries';

-- films_countries
ALTER TABLE films_countries
	ADD CONSTRAINT PK_films_countries -- films_countries 기본키
	PRIMARY KEY (
	films_no,     -- films_no
	countries_no  -- countries_no
	);

-- countries
CREATE TABLE countries (
	countries_no VARCHAR(50) NOT NULL COMMENT 'countries_no', -- countries_no
	name         VARCHAR(50) NOT NULL COMMENT 'name' -- name
)
COMMENT 'countries';

-- countries
ALTER TABLE countries
	ADD CONSTRAINT PK_countries -- countries 기본키
	PRIMARY KEY (
	countries_no -- countries_no
	);

-- film_source
CREATE TABLE films_src (
	films_no INTEGER NOT NULL COMMENT 'films_no', -- films_no
	src_no   INTEGER NOT NULL COMMENT 'src_no' -- src_no
)
COMMENT 'film_source';

-- film_source
ALTER TABLE films_src
	ADD CONSTRAINT PK_films_src -- film_source 기본키
	PRIMARY KEY (
	films_no, -- films_no
	src_no    -- src_no
	);

-- src
CREATE TABLE src (
	src_no INTEGER     NOT NULL COMMENT 'src_no', -- src_no
	name   VARCHAR(50) NOT NULL COMMENT 'name' -- name
)
COMMENT 'src';

-- src
ALTER TABLE src
	ADD CONSTRAINT PK_src -- src 기본키
	PRIMARY KEY (
	src_no -- src_no
	);

-- event
CREATE TABLE event (
	event_no             INTEGER      NOT NULL COMMENT 'event_no', -- event_no
	films_no             INTEGER      NOT NULL COMMENT 'films_no', -- films_no
	screening_date       DATETIME     NOT NULL COMMENT 'screening_date   ', -- screening_date   
	screening_location   VARCHAR(50)  NOT NULL COMMENT 'screening_location  ', -- screening_location  
	entry_period_start   DATETIME     NOT NULL COMMENT 'entry_period_start  ', -- entry_period_start  
	entry_period_end     DATETIME     NOT NULL COMMENT 'entry_period_end', -- entry_period_end
	announcement_date    DATETIME     NOT NULL COMMENT 'announcement_date ', -- announcement_date 
	winners_count        INTEGER      NOT NULL COMMENT 'winners_count', -- winners_count
	notice               VARCHAR(255) NULL     COMMENT 'notice ' -- notice 
)
COMMENT 'event';

-- event
ALTER TABLE event
	ADD CONSTRAINT PK_event -- event 기본키
	PRIMARY KEY (
	event_no -- event_no
	);

-- manager
CREATE TABLE manager (
	users_no   INTEGER     NOT NULL COMMENT 'manager_no', -- manager_no
	department VARCHAR(50) NOT NULL COMMENT 'department', -- department
	job_title  VARCHAR(50) NOT NULL COMMENT 'job_title' -- job_title
)
COMMENT 'manager';

-- manager
ALTER TABLE manager
	ADD CONSTRAINT PK_manager -- manager 기본키
	PRIMARY KEY (
	users_no -- manager_no
	);

-- grade
CREATE TABLE grade (
	grade_no INTEGER     NOT NULL COMMENT 'grade_no', -- grade_no
	name     VARCHAR(50) NOT NULL COMMENT 'name', -- name
	add_at   DATETIME    NOT NULL COMMENT 'add_at' -- add_at
)
COMMENT 'grade';

-- grade
ALTER TABLE grade
	ADD CONSTRAINT PK_grade -- grade 기본키
	PRIMARY KEY (
	grade_no -- grade_no
	);

-- event_application
CREATE TABLE event_application (
	users_no     INTEGER      NOT NULL COMMENT 'users_no', -- users_no
	event_no     INTEGER      NOT NULL COMMENT 'event_no', -- event_no
	created_date DATE         NOT NULL COMMENT 'created_date', -- created_date
	state        VARCHAR(255) NOT NULL COMMENT 'state' -- state
)
COMMENT 'event_application';

-- event_application
ALTER TABLE event_application
	ADD CONSTRAINT PK_event_application -- event_application 기본키
	PRIMARY KEY (
	users_no, -- users_no
	event_no  -- event_no
	);

-- watch_lists
ALTER TABLE watch_lists
	ADD CONSTRAINT FK_users_TO_watch_lists -- users -> watch_lists
	FOREIGN KEY (
	users_no -- users_no
	)
	REFERENCES users ( -- users
	users_no -- users_no
	);

-- watch_lists
ALTER TABLE watch_lists
	ADD CONSTRAINT FK_films_TO_watch_lists -- films -> watch_lists
	FOREIGN KEY (
	films_no -- films_no
	)
	REFERENCES films ( -- films
	films_no -- films_no
	);

-- films
ALTER TABLE films
	ADD CONSTRAINT FK_grade_TO_films -- grade -> films
	FOREIGN KEY (
	grade_no -- grade_no
	)
	REFERENCES grade ( -- grade
	grade_no -- grade_no
	);

-- rating
ALTER TABLE rating
	ADD CONSTRAINT FK_users_TO_rating -- users -> rating
	FOREIGN KEY (
	users_no -- users_no
	)
	REFERENCES users ( -- users
	users_no -- users_no
	);

-- rating
ALTER TABLE rating
	ADD CONSTRAINT FK_films_TO_rating -- films -> rating
	FOREIGN KEY (
	films_no -- films_no
	)
	REFERENCES films ( -- films
	films_no -- films_no
	);

-- comments
ALTER TABLE comments
	ADD CONSTRAINT FK_users_TO_comments -- users -> comments
	FOREIGN KEY (
	users_no -- users_no
	)
	REFERENCES users ( -- users
	users_no -- users_no
	);

-- comments
ALTER TABLE comments
	ADD CONSTRAINT FK_films_TO_comments -- films -> comments
	FOREIGN KEY (
	films_no -- films_no
	)
	REFERENCES films ( -- films
	films_no -- films_no
	);

-- comments_like
ALTER TABLE comments_like
	ADD CONSTRAINT FK_users_TO_comments_like -- users -> comments_like
	FOREIGN KEY (
	users_no -- users_no
	)
	REFERENCES users ( -- users
	users_no -- users_no
	);

-- comments_like
ALTER TABLE comments_like
	ADD CONSTRAINT FK_comments_TO_comments_like -- comments -> comments_like
	FOREIGN KEY (
	comments_no -- comments_no
	)
	REFERENCES comments ( -- comments
	comments_no -- comments_no
	);

-- films_genres
ALTER TABLE films_genres
	ADD CONSTRAINT FK_genres_TO_films_genres -- genres -> films_genres
	FOREIGN KEY (
	genres_no -- genres_no
	)
	REFERENCES genres ( -- genres
	genres_no -- genres_no
	);

-- films_genres
ALTER TABLE films_genres
	ADD CONSTRAINT FK_films_TO_films_genres -- films -> films_genres
	FOREIGN KEY (
	films_no -- films_no
	)
	REFERENCES films ( -- films
	films_no -- films_no
	);

-- films_staff
ALTER TABLE films_staff
	ADD CONSTRAINT FK_staff_TO_films_staff -- staff -> films_staff
	FOREIGN KEY (
	staff_no -- staff_no
	)
	REFERENCES staff ( -- staff
	staff_no -- staff_no
	);

-- films_staff
ALTER TABLE films_staff
	ADD CONSTRAINT FK_films_TO_films_staff -- films -> films_staff
	FOREIGN KEY (
	films_no -- films_no
	)
	REFERENCES films ( -- films
	films_no -- films_no
	);

-- films_actors
ALTER TABLE films_actors
	ADD CONSTRAINT FK_films_TO_films_actors -- films -> films_actors
	FOREIGN KEY (
	films_no -- films_no
	)
	REFERENCES films ( -- films
	films_no -- films_no
	);

-- films_actors
ALTER TABLE films_actors
	ADD CONSTRAINT FK_roles_TO_films_actors -- roles -> films_actors
	FOREIGN KEY (
	roles_no -- roles_no
	)
	REFERENCES roles ( -- roles
	roles_no -- roles_no
	);

-- films_actors
ALTER TABLE films_actors
	ADD CONSTRAINT FK_actors_TO_films_actors -- actors -> films_actors
	FOREIGN KEY (
	actors_no -- actors_no
	)
	REFERENCES actors ( -- actors
	actors_no -- actors_no
	);

-- films_countries
ALTER TABLE films_countries
	ADD CONSTRAINT FK_countries_TO_films_countries -- countries -> films_countries
	FOREIGN KEY (
	countries_no -- countries_no
	)
	REFERENCES countries ( -- countries
	countries_no -- countries_no
	);

-- films_countries
ALTER TABLE films_countries
	ADD CONSTRAINT FK_films_TO_films_countries -- films -> films_countries
	FOREIGN KEY (
	films_no -- films_no
	)
	REFERENCES films ( -- films
	films_no -- films_no
	);

-- film_source
ALTER TABLE films_src
	ADD CONSTRAINT FK_src_TO_films_src -- src -> film_source
	FOREIGN KEY (
	src_no -- src_no
	)
	REFERENCES src ( -- src
	src_no -- src_no
	);

-- film_source
ALTER TABLE films_src
	ADD CONSTRAINT FK_films_TO_films_src -- films -> film_source
	FOREIGN KEY (
	films_no -- films_no
	)
	REFERENCES films ( -- films
	films_no -- films_no
	);

-- event
ALTER TABLE event
	ADD CONSTRAINT FK_films_TO_event -- films -> event
	FOREIGN KEY (
	films_no -- films_no
	)
	REFERENCES films ( -- films
	films_no -- films_no
	);

-- manager
ALTER TABLE manager
	ADD CONSTRAINT FK_users_TO_manager -- users -> manager
	FOREIGN KEY (
	users_no -- manager_no
	)
	REFERENCES users ( -- users
	users_no -- users_no
	);

-- event_application
ALTER TABLE event_application
	ADD CONSTRAINT FK_users_TO_event_application -- users -> event_application
	FOREIGN KEY (
	users_no -- users_no
	)
	REFERENCES users ( -- users
	users_no -- users_no
	);

-- event_application
ALTER TABLE event_application
	ADD CONSTRAINT FK_event_TO_event_application -- event -> event_application
	FOREIGN KEY (
	event_no -- event_no
	)
	REFERENCES event ( -- event
	event_no -- event_no
	);