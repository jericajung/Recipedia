DROP DATABASE if exists recipedia;
CREATE DATABASE recipedia;

USE recipedia;

CREATE TABLE Users ( 								
	userID int(11) primary key not null auto_increment,
	username varchar(50) not null,
    pword varchar(50) not null,
	fname varchar(50) not null,
    lname varchar(50) not null, 
    image varchar(200) not null
);

CREATE TABLE Tags(
	tagID int(11) primary key not null auto_increment,
    tag varchar(50) not null
);

CREATE TABLE Recipes (
	recipeID int(11) primary key not null auto_increment,
    title varchar(50) not null,
    tagID int(11) not null,
    likes int(4),
    FOREIGN KEY (tagID) references tags(tagID)
);



CREATE TABLE SavedRecipes (
	userID int(11) not null, 
    recipeID int(11) not null,
    FOREIGN KEY (userID) REFERENCES users(userID),
    FOREIGN KEY (recipeID) REFERENCES recipes(recipeID)
);

CREATE TABLE UploadedRecipes (
	userID int(11) not null, 
    recipeID int(11) not null,
    FOREIGN KEY (userID) REFERENCES users(userID),
    FOREIGN KEY (recipeID) REFERENCES recipes(recipeID)
);

CREATE TABLE Ingredients (
	recipeID int(11) not null,
    quantity int(4) not null,
    ingredient varchar(50) not null,
    FOREIGN KEY (recipeID) REFERENCES recipes(recipeID)
);
CREATE TABLE Instructions (
	recipeID int(11) not null,
    instruction varchar(1000) not null,
    FOREIGN KEY (recipeID) REFERENCES recipes(recipeID)
);


CREATE TABLE Fans (
	userID int(11) not null,
    fanID int(11) not null,
    FOREIGN KEY (userID) REFERENCES users(userID),
    FOREIGN KEY (fanID) REFERENCES users(userID)
);

CREATE TABLE Reviews (
	recipeID int(11) not null,
    review varchar(5000) not null,
    FOREIGN KEY (recipeID) REFERENCES recipes(recipeID)
);
