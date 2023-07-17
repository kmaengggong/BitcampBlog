# Create blog Table
CREATE TABLE blog(
                     blog_id INT AUTO_INCREMENT PRIMARY KEY,
                     author VARCHAR(16) NOT NULL,
                     blog_title VARCHAR(200) NOT NULL,
                     blog_content VARCHAR(4000) NOT NULL,
                     published_at DATETIME DEFAULT NOW(),
                     updated_at DATETIME DEFAULT NOW(),
                     blog_count INT DEFAULT 0
);

# Create Dummy Data of blog Table
INSERT INTO blog VALUES
    (NULL, '1번유저', '1번제목', '1번본문', NOW(), NOW(), NULL),
    (NULL, '2번유저', '2번제목', '2번본문', NOW(), NOW(), NULL),
    (NULL, '3번유저', '3번제목', '3번본문', NOW(), NOW(), NULL);

