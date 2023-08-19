insert into JavaProject_participant(
participant_no,name, age, movieAttendance, gender, movieRating, additionalInfo, password)
	values(1, 'aaa', '29', 'Y', 'M', '5', 'nice', sha1('1111'));
insert into JavaProject_participant(
participant_no,name, age, movieAttendance, gender, movieRating, additionalInfo, password)
	values(2, 'bbb', '30', 'N', 'W', '1', 'shit', sha1('1111'));





insert into JavaProject_board(board_no, title, content, writer, password, category)
  values(11, '제목1', '내용', '홍길동', sha1('1111'), 1);
insert into JavaProject_board(board_no, title, content, writer, password, category)
  values(12, '제목2', '내용', '임꺽정', sha1('1111'), 1);  
insert into JavaProject_board(board_no, title, content, writer, password, category)
  values(13, '제목3', '내용', '이순신', sha1('1111'), 2);
insert into JavaProject_board(board_no, title, content, writer, password, category)
  values(14, '제목4', '내용', '김구', sha1('1111'), 2);
  