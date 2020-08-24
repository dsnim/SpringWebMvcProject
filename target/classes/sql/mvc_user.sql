
CREATE TABLE mvc_user (
	account VARCHAR(80) PRIMARY KEY,
	password VARCHAR(80) NOT NULL,
	name VARCHAR(100) NOT NULL,
	reg_date TIMESTAMP DEFAULT NOW()
);

-- 자동로그인 관련 컬럼 추가
ALTER TABLE mvc_user ADD COLUMN session_id VARCHAR(80) NOT NULL DEFAULT 'none';
ALTER TABLE mvc_user ADD COLUMN limit_time TIMESTAMP;