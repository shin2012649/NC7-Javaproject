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

-- JavaProject_board 테이블 변경: 모든 컬럼에 대해 NULL 허용
--ALTER TABLE JavaProject_board
--    MODIFY board_no INT NOT NULL AUTO_INCREMENT,
--    MODIFY title VARCHAR(255) NULL,
--    MODIFY content TEXT NULL,
--    MODIFY writer INT NULL,
--    MODIFY password VARCHAR(100) NULL,






