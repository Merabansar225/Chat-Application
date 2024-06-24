DROP TABLE IF EXISTS Likes;
DROP TABLE IF EXISTS Reply;
DROP TABLE IF EXISTS Post;
DROP TABLE IF EXISTS User;

-- Creating the User table
CREATE TABLE User (
  userID INT AUTO_INCREMENT PRIMARY KEY,
  firstName VARCHAR(255),
  lastName VARCHAR(255),
  age INT,
  DOB DATE,
  username VARCHAR(100) UNIQUE,
  email VARCHAR(255) UNIQUE,
  phone VARCHAR(20) UNIQUE,
  password VARCHAR(255)
);

-- Creating the Post table
CREATE TABLE Post (
  postID INT AUTO_INCREMENT PRIMARY KEY,
  userID INT,
  postMessage VARCHAR(2500),
  FOREIGN KEY (userID) REFERENCES User(userID)
);

-- Creating the Reply table
CREATE TABLE Reply (
  replyID INT AUTO_INCREMENT PRIMARY KEY,
  userID INT,
  postID INT,
  replyNotification BOOLEAN,
  replyMessage VARCHAR(2500),
  FOREIGN KEY (userID) REFERENCES User(userID),
  FOREIGN KEY (postID) REFERENCES Post(postID)
);

-- Creating the Likes table
CREATE TABLE Likes (
  likeID INT AUTO_INCREMENT PRIMARY KEY,
  userID INT,
  postID INT,
  replyID INT,
  likeNotification BOOLEAN,
  FOREIGN KEY (userID) REFERENCES User(userID),
  FOREIGN KEY (postID) REFERENCES Post(postID),
  FOREIGN KEY (replyID) REFERENCES Reply(replyID)
);
