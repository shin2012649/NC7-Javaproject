insert into JavaProject_participant(
participant_no,name, age, movieAttendance, gender, movieRating, additionalInfo, password)
	values(1, 'aaa', '29', 'Y', 'M', '5', 'nice', sha1('1111'));
insert into JavaProject_participant(
participant_no,name, age, movieAttendance, gender, movieRating, additionalInfo, password)
	values(2, 'bbb', '30', 'N', 'W', '1', 'shit', sha1('1111'));
insert into JavaProject_participant(
participant_no,name, age, movieAttendance, gender, movieRating, additionalInfo, password)
	values(3, 'ccc', '50', 'Y', 'M', '3', 'not bad', sha1('1111'));
insert into JavaProject_participant(
participant_no,name, age, movieAttendance, gender, movieRating, additionalInfo, password)
	values(4, 'ddd', '30', 'W', 'M', '5', 'ohora', sha1('1111'));
insert into JavaProject_participant(
participant_no,name, age, movieAttendance, gender, movieRating, additionalInfo, password)
	values(5, 'eee', '10', 'Y', 'M', '5', 'aww', sha1('1111'));


insert into JavaProject_board_category(board_category_no, name) values(1, '게시판');
insert into JavaProject_board_category(board_category_no, name) values(2, '평론');


insert into JavaProject_board(board_no, title, content, writer, category)
  values(11, '제목1', '내용', 1, 1);
insert into JavaProject_board(board_no, title, content, writer, category)
  values(12, '제목2', '내용', 1, 1);
insert into JavaProject_board(board_no, title, content, writer, category)
  values(13, '제목3', '내용', 3, 1);
insert into JavaProject_board(board_no, title, content, writer, category)
  values(14, '제목4', '내용', 4, 1);
insert into JavaProject_board(board_no, title, content, writer, category)
  values(15, '제목5', '내용', 5, 2);
insert into JavaProject_board(board_no, title, content, writer, category)
  values(16, '제목6', '내용', 5, 2);
insert into JavaProject_board(board_no, title, content, writer, category)
  values(17, '제목7', '내용', 5, 2);



--INSERT INTO JavaProject_event (
 -- name, entry_period, announcement_date, winners_count, screening_date, screening_location, notice
--) VALUES (
  --'Avengers: Infinity War Screening', '2023-08-15 ~ 2023-08-31', '2023-08-01 10:00:00',
  --10, '2023-09-05 18:00:00', '용산 아이맥스', '• 당첨인원: 10명 (1인 2석, 총 20석)\n• 영화당 1인 1회 응모 가능합니다.'
--);

--INSERT INTO JavaProject_event (
  --name, entry_period, announcement_date, winners_count, screening_date, screening_location, notice
--) VALUES (
  --'HorrorFest: A Night of Chills', '2023-09-10 ~ 2023-09-20', '2023-09-01 14:00:00',
 -- 20, '2023-09-25 20:00:00', '군자 메가박스', '• 당첨인원: 20명 (1인 2석, 총 40석)\n• 영화당 1인 1회 응모 가능합니다.'
--);
  