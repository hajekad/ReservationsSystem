-- Remove conflicting tables
DROP TABLE IF EXISTS coach CASCADE;
DROP TABLE IF EXISTS place CASCADE;
DROP TABLE IF EXISTS trainee CASCADE;
DROP TABLE IF EXISTS training CASCADE;
DROP TABLE IF EXISTS trainee_training CASCADE;
-- End of removing

CREATE TABLE coach (
    id_coach SERIAL NOT NULL,
    cost_rate INTEGER NOT NULL,
    sport VARCHAR(256) NOT NULL,
    password VARCHAR(256) NOT NULL,
    email VARCHAR(256) NOT NULL
);
ALTER TABLE coach ADD CONSTRAINT pk_coach PRIMARY KEY (id_coach);

CREATE TABLE place (
    id_place SERIAL NOT NULL,
    latitude DOUBLE PRECISION NOT NULL,
    longitude DOUBLE PRECISION NOT NULL
);
ALTER TABLE place ADD CONSTRAINT pk_place PRIMARY KEY (id_place);

CREATE TABLE trainee (
    id_trainee SERIAL NOT NULL,
    username VARCHAR(256) NOT NULL,
    email VARCHAR(256) NOT NULL,
    password VARCHAR(256) NOT NULL,
    skill_cap INTEGER NOT NULL
);
ALTER TABLE trainee ADD CONSTRAINT pk_trainee PRIMARY KEY (id_trainee);

CREATE TABLE training (
    id_training SERIAL NOT NULL,
    id_coach INTEGER NOT NULL,
    id_place INTEGER NOT NULL,
    date TIMESTAMP NOT NULL,
    description VARCHAR(256) NOT NULL
);
ALTER TABLE training ADD CONSTRAINT pk_training PRIMARY KEY (id_training, id_coach);

CREATE TABLE trainee_training (
    id_trainee INTEGER NOT NULL,
    id_training INTEGER NOT NULL,
    id_coach INTEGER NOT NULL
);
ALTER TABLE trainee_training ADD CONSTRAINT pk_trainee_training PRIMARY KEY (id_trainee, id_training, id_coach);

ALTER TABLE training ADD CONSTRAINT fk_training_coach FOREIGN KEY (id_coach) REFERENCES coach (id_coach) ON DELETE CASCADE;
ALTER TABLE training ADD CONSTRAINT fk_training_place FOREIGN KEY (id_place) REFERENCES place (id_place) ON DELETE CASCADE;

ALTER TABLE trainee_training ADD CONSTRAINT fk_trainee_training_trainee FOREIGN KEY (id_trainee) REFERENCES trainee (id_trainee) ON DELETE CASCADE;
ALTER TABLE trainee_training ADD CONSTRAINT fk_trainee_training_training FOREIGN KEY (id_training, id_coach) REFERENCES training (id_training, id_coach) ON DELETE CASCADE;