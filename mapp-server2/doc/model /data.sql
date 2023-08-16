-- users
INSERT INTO users (users_no, name, password, email, profile_image_url, date_of_birth, created_at)
VALUES
  (1, 'aaa', '1111', 'aaa@test.com', 'profile1.jpg', '1990-01-15', '2023-08-14 10:00:00'),
  (2, 'bbb', '1111', 'bbb@test.com', 'profile1.jpg', '1988-05-20', '2023-08-14 11:30:00'),
  (3, 'ccc', '1111', 'ccc@test.com', 'profile1.jpg', '1995-03-10', '2023-08-14 12:45:00'),
  (4, 'ddd', '1111', 'ddd@test.com', 'profile4.jpg', '2000-09-08', '2023-08-14 13:15:00'),
  (5, 'eee', '1111', 'eee@test.com', NULL, '1987-11-25', '2023-08-14 14:20:00');

-- films
INSERT INTO films (films_no, title, films_image_url, grade_no, descriptions, running_time, released_date)
VALUES
  (1, 'film A', 'movieA.jpg', 1, 'Description for film A', 120, '2023-07-15'),
  (2, 'film B', 'movieB.jpg', 2, 'Description for film B', 105, '2023-08-01'),
  (3, 'film C', 'movieC.jpg', 1, 'Description for film C', 95, '2023-09-10'),
  (4, 'film D', 'movieD.jpg', 3, 'Description for film D', 135, '2023-06-20'),
  (5, 'film E', 'movieE.jpg', 2, 'Description for film E', 110, '2023-08-28');
  
-- watch_lists
INSERT INTO watch_lists (users_no, films_no)
VALUES
  (1, 1),
  (1, 2),
  (2, 3),
  (3, 4),
  (4, 5),
  (5, 1),
  (5, 3);

-- ratings
INSERT INTO rating (users_no, films_no, rating_score)
VALUES
  (1, 1, 4.5),
  (1, 2, 3.8),
  (2, 1, 3.0),
  (2, 2, 4.2),
  (3, 1, 3.5);

-- comments
INSERT INTO comments (comments_no, users_no, films_no, contents, add_at, state)
VALUES
  (1, 1, 1, 'Great film!', '2023-08-14 12:00:00', NULL),
  (2, 2, 1, 'I enjoyed it.', '2023-08-14 13:30:00', NULL),
  (3, 1, 2, 'Awesome!', '2023-08-14 14:15:00', NULL),
  (4, 3, 1, 'Must-watch!', '2023-08-15 10:45:00', NULL),
  (5, 4, 3, 'Not bad.', '2023-08-15 11:30:00', NULL);

-- genres
INSERT INTO genres (genres_no, name)
VALUES
  (1, 'Action'),
  (2, 'Comedy'),
  (3, 'Drama'),
  (4, 'Science Fiction'),
  (5, 'Horror');

-- films_genres
INSERT INTO films_genres (films_genres_no, genres_no, films_no)
VALUES
  (1, 1, 1),
  (2, 2, 1),
  (3, 1, 2),
  (4, 3, 1),
  (5, 2, 3);

-- actors
INSERT INTO actors (actors_no, name, image_url)
VALUES
  (1, 'aa', 'actor1.jpg'),
  (2, 'bb', 'actor2.jpg'),
  (3, 'cc', 'actor3.jpg'),
  (4, 'dd', 'actor4.jpg'),
  (5, 'ee', 'actor5.jpg');

-- roles
INSERT INTO roles (roles_no, name)
VALUES
  (1, 'Main Actor'),
  (2, 'Supporting Actor'),
  (3, 'Villain'),
  (4, 'Comic Relief'),
  (5, 'Sidekick');

-- films_actors
INSERT INTO films_actors (films_actors_no, films_no, roles_no, actors_no, name)
VALUES
  (1, 1, 1, 1, 'John Actor'),
  (2, 1, 2, 2, 'Jane Actress'),
  (3, 2, 1, 3, 'Michael Actor'),
  (4, 3, 3, 4, 'Emily Actress'),
  (5, 3, 4, 1, 'John Actor');

-- countries
INSERT INTO countries (countries_no, name)
VALUES
  ('US', 'United States'),
  ('KR', 'South Korea'),
  ('JP', 'Japan'),
  ('CN', 'China'),
  ('UK', 'United Kingdom');

-- films_countries
INSERT INTO films_countries (films_no, countries_no)
VALUES
  (1, 'US'),
  (2, 'KR'),
  (3, 'JP'),
  (4, 'US'),
  (5, 'UK');

-- event
INSERT INTO event (event_no, films_no, screening_date, screening_location, entry_period_start, entry_period_end, announcement_date, winners_count, notice)
VALUES
  (1, 1, '2023-09-01 18:00:00', 'Theater A', '2023-08-20 00:00:00', '2023-08-25 23:59:59', '2023-08-28 12:00:00', 3, 'Event notice here'),
  (2, 3, '2023-09-10 15:00:00', 'Theater B', '2023-08-25 00:00:00', '2023-08-30 23:59:59', '2023-09-02 10:00:00', 2, 'Event notice here');

-- manager
INSERT INTO manager (users_no, department, job_title)
VALUES
  (4, 'HR', 'Manager'),
  (5, 'Finance', 'Analyst');

-- event_application
INSERT INTO event_application (users_no, event_no, created_date, state)
VALUES
  (1, 1, '2023-08-23', 'Pending'),
  (2, 1, '2023-08-24', 'Approved'),
  (3, 2, '2023-08-28', 'Pending'),
  (4, 2, '2023-08-30', 'Rejected'),
  (5, 1, '2023-08-25', 'Pending');

  
-- films_src
INSERT INTO films_src (films_no, src_no)
VALUES
  (1, 1),
  (2, 2),
  (3, 3),
  (4, 4),
  (5, 5);

-- staff
INSERT INTO staff (staff_no, name, image_url)
VALUES
  (1, 'aaa', 'directorA.jpg'),
  (2, 'bbb', 'writerB.jpg'),
  (3, 'ccc', 'directorC.jpg'),
  (4, 'ddd', 'writerD.jpg');

-- films_staff
INSERT INTO films_staff (films_staff_no, films_no, staff_no, role, position)
VALUES
  (6, 1, 1, 'Director', 'Main'),
  (7, 1, 2, 'Writer', 'Screenplay'),
  (8, 2, 3, 'Director', 'Main'),
  (9, 2, 4, 'Writer', 'Novel'),
  (10, 3, 1, 'Director', 'Main');

-- comments_like
INSERT INTO comments_like (comments_no, users_no)
VALUES
  (6, 1),
  (7, 2),
  (8, 3),
  (9, 4),
  (10, 5);
  
-- comments
INSERT INTO comments (comments_no, users_no, films_no, contents, add_at, state)
VALUES
  (6, 1, 3, 'Nice movie!', '2023-09-02 10:30:00', NULL),
  (7, 2, 5, 'Scary but good!', '2023-09-03 11:15:00', NULL),
  (8, 3, 2, 'I laughed so hard!', '2023-09-04 14:20:00', NULL),
  (9, 4, 1, 'Could have been better.', '2023-09-05 09:45:00', NULL),
  (10, 5, 4, 'Exciting plot twists!', '2023-09-06 15:00:00', NULL);

