create table JavaProject_board(
  board_no int not null,
  title varchar(255) not null,
  content text null,
  writer int not null,
  password varchar(100) null,
  view_count int default 0,
  created_date datetime default now(),
  category int not null
);

alter table JavaProject_board
add constraint primary key (board_no),
modify column board_no int not null auto_increment;
  
   
create table JavaProject_participant(
	participant_no int not null,
	name varchar(20) not null,
	age int not null,
	movieAttendance varchar(20) not null,
	gender char(1) not null,
	movieRating int not null,
	additionalInfo varchar(100) not null,
	password varchar(100) not null,
	created_date date default (current_date())
);

alter table JavaProject_participant
	add constraint primary key (participant_no),
	modify column participant_no int not null auto_increment;
	
	
alter table JavaProject_board
 add constraint JavaProject_board_fk foreign key (writer) references JavaProject_participant (participant_no);
 
ALTER TABLE JavaProject_participant
    MODIFY participant_no INT NOT NULL AUTO_INCREMENT,
    MODIFY name VARCHAR(20) NULL,
    MODIFY age INT NULL,
    MODIFY movieAttendance VARCHAR(20) NULL,
    MODIFY gender CHAR(1) NULL,
    MODIFY movieRating INT NULL,
    MODIFY additionalInfo VARCHAR(100) NULL,
    MODIFY password VARCHAR(100) NULL;
    
CREATE TABLE JavaProject_event (
  event_id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(255) NOT NULL,
  entry_period VARCHAR(100) NOT NULL,
  announcement_date DATETIME NOT NULL,
  winners_count INT NOT NULL,
  screening_date DATETIME NOT NULL,
  screening_location VARCHAR(255) NOT NULL,
  notice TEXT NOT NULL,
  PRIMARY KEY (event_id)
);

-- 게시판유형
CREATE TABLE JavaProject_board_category (
  board_category_no INTEGER     NOT NULL COMMENT '번호', -- 번호
  name              VARCHAR(50) NOT NULL COMMENT '게시판이름' -- 게시판이름
)
COMMENT '게시판유형';

-- 게시판유형
ALTER TABLE JavaProject_board_category
  ADD CONSTRAINT PK_JavaProject_board_category -- 게시판유형 기본키
  PRIMARY KEY (
  board_category_no -- 번호
  );
  
  -- 게시판유형 유니크 인덱스
CREATE UNIQUE INDEX UIX_JavaProject_board_category
  ON JavaProject_board_category ( -- 게시판유형
    name ASC -- 게시판이름
  );

ALTER TABLE JavaProject_board_category
  MODIFY COLUMN board_category_no INTEGER NOT NULL AUTO_INCREMENT COMMENT '번호';

-- 게시글첨부파일
CREATE TABLE JavaProject_board_file (
  board_file_no INTEGER      NOT NULL COMMENT '번호', -- 번호
  filepath      VARCHAR(255) NOT NULL COMMENT '파일경로', -- 파일경로
  board_no      INTEGER      NOT NULL COMMENT '게시글번호' -- 게시글번호
)
COMMENT '게시글첨부파일';

ALTER TABLE JavaProject_board_file
  ADD CONSTRAINT PK_JavaProject_board_file -- 게시글첨부파일 기본키
  PRIMARY KEY (
  board_file_no -- 번호
  );
  
  ALTER TABLE JavaProject_board_file
  MODIFY COLUMN board_file_no INTEGER NOT NULL AUTO_INCREMENT COMMENT '번호';

-- 게시글
ALTER TABLE JavaProject_board
  ADD CONSTRAINT FK_JavaProject_participant_TO_JavaProject_board -- 회원 -> 게시글
  FOREIGN KEY (
  writer -- 작성자
  )
  REFERENCES JavaProject_participant ( -- 회원
  particpant_no -- 번호
  );

-- 게시글
ALTER TABLE JavaProject_board
  ADD CONSTRAINT FK_JavaProject_board_category_TO_JavaProject_board -- 게시판유형 -> 게시글
  FOREIGN KEY (
  category -- 게시판
  )
  REFERENCES JavaProject_board_category ( -- 게시판유형
  board_category_no -- 번호
  );

-- 게시글첨부파일
ALTER TABLE JavaProject_board_file
  ADD CONSTRAINT FK_JavaProject_board_TO_JavaProject_board_file -- 게시글 -> 게시글첨부파일
  FOREIGN KEY (
  board_no -- 게시글번호
  )
  REFERENCES JavaProject_board ( -- 게시글
  board_no -- 번호
  );

-- JavaProject_board 테이블 변경: 모든 컬럼에 대해 NULL 허용
--ALTER TABLE JavaProject_board
--    MODIFY board_no INT NOT NULL AUTO_INCREMENT,
--    MODIFY title VARCHAR(255) NULL,
--    MODIFY content TEXT NULL,
--    MODIFY writer INT NULL,
--    MODIFY password VARCHAR(100) NULL,






