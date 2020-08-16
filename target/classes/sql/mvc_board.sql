
-- 게시판 테이블 생성
CREATE TABLE mvc_board (
	board_no INT PRIMARY KEY AUTO_INCREMENT,
	title VARCHAR(255) NOT NULL,
	content TEXT NULL,
	writer VARCHAR(50) NOT NULL,
	reg_date TIMESTAMP DEFAULT NOW(),
	view_cnt INT DEFAULT 0
);