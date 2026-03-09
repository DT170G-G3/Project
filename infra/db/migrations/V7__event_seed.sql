INSERT INTO event (title, description, start_time) VALUES
    ('Musikquiz 90-tal', 'Quiz med de största hitsen från 90-talet.', '2026-01-01 19:00:00'),
    ('Musikquiz Rock', 'Rockquiz med klassiska band.', '2026-02-01 19:00:00'),
    ('Musikquiz Pop', 'Popquiz med låtar från olika årtionden.', '2026-04-01 19:00:00');


INSERT INTO posts (image_path, event_id) VALUES
    ('resources/images/event/past/1.jpg', 1),
    ('resources/images/event/past/2.jpg', 1),
    ('resources/images/event/past/3.jpg', 2),
    ('resources/images/event/past/4.jpg', 2);


INSERT INTO comments (event_id, name, comment, date_and_time) VALUES
   (1, 'Erik', 'Ser fram emot quizet!', '2026-01-01 20:15:00'),
   (1, 'Anna', '90-tal är min favorit!', '2026-01-01 20:20:00'),
   (2, 'Johan', 'Rockquiz blir grymt!', '2026-02-01 20:10:00'),
   (3, 'Sara', 'Popquiz låter kul!', '2026-04-01 20:05:00');