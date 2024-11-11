

CREATE TABLE IF NOT EXISTS User (
    id INT AUTO_INCREMENT PRIMARY KEY,
    level INT NOT NULL DEFAULT 1,
    coins INT NOT NULL DEFAULT 5000,
    country ENUM('TURKEY', 'UNITED_STATES', 'UNITED_KINGDOM', 'FRANCE', 'GERMANY') NOT NULL
);


CREATE TABLE IF NOT EXISTS Tournament (
    id INT AUTO_INCREMENT PRIMARY KEY,
    start_date DATETIME NOT NULL,
    end_date DATETIME NOT NULL
);



CREATE TABLE IF NOT EXISTS TournamentGroup (
    id INT AUTO_INCREMENT PRIMARY KEY,
    tournament_id INT NOT NULL,
    status ENUM('ACTIVE', 'COMPLETED') NOT NULL DEFAULT 'ACTIVE',
    FOREIGN KEY (tournament_id) REFERENCES Tournament(id)
);


CREATE TABLE IF NOT EXISTS TournamentParticipant (
    user_id INT NOT NULL,
    group_id INT NOT NULL,
    score INT NOT NULL DEFAULT 0,
    rewards_claimed BOOLEAN NOT NULL DEFAULT FALSE,
    FOREIGN KEY (user_id) REFERENCES User(id),
    FOREIGN KEY (group_id) REFERENCES TournamentGroup(id),
    PRIMARY KEY (user_id, group_id)
);


CREATE INDEX idx_tournament_id ON TournamentGroup (tournament_id);
CREATE INDEX idx_group_id ON TournamentParticipant (group_id);


INSERT INTO User (level, coins, country) VALUES
    (1, 5000, 'TURKEY'),
    (1, 5000, 'UNITED_STATES'),
    (1, 5000, 'UNITED_KINGDOM'),
    (1, 5000, 'FRANCE'),
    (1, 5000, 'GERMANY');


	
INSERT INTO Tournament (start_date, end_date) VALUES
    ('2024-05-01 00:00:00', '2024-05-01 20:00:00'),
    ('2024-05-02 00:00:00', '2024-05-02 20:00:00');



INSERT INTO TournamentGroup (tournament_id, status) VALUES
    (1, 'ACTIVE'),
    (1, 'ACTIVE'),
    (2, 'ACTIVE');



INSERT INTO TournamentParticipant (user_id, group_id, score, rewards_claimed) VALUES
    (1, 1, 10, false),
    (2, 1, 15, false),
    (3, 1, 20, false),
    (4, 1, 25, false),
    (5, 1, 30, false),
    (1, 2, 12, false),
    (2, 2, 18, false),
    (3, 2, 24, false),
    (4, 2, 30, false),
    (5, 2, 36, false),
    (1, 3, 8, false),
    (2, 3, 12, false),
    (3, 3, 16, false),
    (4, 3, 20, false),
    (5, 3, 24, false);
