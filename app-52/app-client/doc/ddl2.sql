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