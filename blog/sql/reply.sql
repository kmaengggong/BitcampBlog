# Create reply Table
CREATE TABLE reply(
                      reply_id INT PRIMARY KEY AUTO_INCREMENT,
                      blog_id INT NOT NULL,
                      reply_author VARCHAR(16) NOT NULL,
                      reply_content VARCHAR(1000) NOT NULL,
                      published_at DATETIME DEFAULT NOW(),
                      updated_at DATETIME DEFAULT NOW()
);

ALTER TABLE reply ADD CONSTRAINT fk_reply FOREIGN KEY (blog_id) REFERENCES blog(blog_id);

# Create Dummy Data of reply Table
INSERT INTO reply VALUES
                      (NULL, 2, '1번유저', '이게 글인가요?', NOW(), NOW()),
                      (NULL, 2, '2번유저', '? 왜 시비세요', NOW(), NOW()),
                      (NULL, 2, '3번유저', '싸우지들 마세요..', NOW(), NOW()),
                      (NULL, 3, '2번유저', '트래픽 아깝다', NOW(), NOW()),
                      (NULL, 3, '3번유저', '미쳣음?', NOW(), NOW());