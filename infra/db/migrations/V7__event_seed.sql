INSERT INTO event (title, description, start_time) VALUES
   ('Musikquiz 90-tal', 'Quiz med de största hitsen från 90-talet.', '2026-04-10 19:00:00'),
   ('Musikquiz Rock', 'Rockquiz med klassiska band.', '2026-04-17 19:00:00'),
   ('Musikquiz Pop', 'Popquiz med låtar från olika årtionden.', '2026-04-24 19:00:00'),
   ('Musikquiz 80-tal', 'Quiz om 80-talets största hits.', '2026-03-20 19:00:00'),
   ('Musikquiz Film', 'Quiz om låtar från filmer.', '2026-03-27 19:00:00');

INSERT INTO posts (event_id) VALUES
    (1),
    (2),
    (3),
    (4),
    (5);

INSERT INTO comments (post_id, name, comment) VALUES
    (1, 'Erik', 'Ser fram emot quizet!'),
    (1, 'Anna', '90-tal är min favorit!'),
    (2, 'Johan', 'Rockquiz blir grymt!'),
    (3, 'Sara', 'Popquiz låter kul!'),
    (4, 'David', '80-talets musik är bäst!'),
    (5, 'Emma', 'Filmmusik quiz låter svårt!');