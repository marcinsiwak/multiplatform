CREATE TABLE IF NOT EXISTS users (created_at_utc TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL, modified_at_utc TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL, id VARCHAR(128) PRIMARY KEY, "name" VARCHAR(128) NOT NULL, email VARCHAR(128) NOT NULL);
CREATE TABLE IF NOT EXISTS categories (created_at_utc TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL, modified_at_utc TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL, id VARCHAR(128) PRIMARY KEY, "userId" VARCHAR(128) NOT NULL, "name" VARCHAR(128) NOT NULL, "exerciseType" VARCHAR(128) NOT NULL);
CREATE TABLE IF NOT EXISTS exercises (created_at_utc TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL, modified_at_utc TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL, id VARCHAR(128) PRIMARY KEY, "categoryId" VARCHAR(128) NOT NULL, "name" VARCHAR(128) NOT NULL, CONSTRAINT fk_exercises_categoryid__id FOREIGN KEY ("categoryId") REFERENCES categories(id) ON DELETE CASCADE ON UPDATE RESTRICT);
CREATE TABLE IF NOT EXISTS results (created_at_utc TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL, modified_at_utc TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL, id VARCHAR(128) PRIMARY KEY, "exerciseId" VARCHAR(128) NOT NULL, amount VARCHAR(128) NOT NULL, "result" VARCHAR(128) NOT NULL, "date" TIMESTAMP NOT NULL, CONSTRAINT fk_results_exerciseid__id FOREIGN KEY ("exerciseId") REFERENCES exercises(id) ON DELETE CASCADE ON UPDATE RESTRICT);