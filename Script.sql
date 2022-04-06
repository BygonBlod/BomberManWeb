Create database BomberMan ;
use BomberMan ;
create table Users(id int auto_increment,name varchar(20) UNIQUE not null,password varchar(80) not null,nbParty int not null,nbWin int not null,primary key(id));