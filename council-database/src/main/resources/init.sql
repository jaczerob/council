create table if not exists districts (
    id int not null auto_increment,
    population int,
    district varchar(32),
    status varchar(8),
    date timestamp,
    primary key (id)
);

create table if not exists invasions (
    id int not null auto_increment,
    district varchar(32),
    cog varchar(32),
    total_cogs int,
    date timestamp,
    primary key (id)
);

create table if not exists population (
    id int not null auto_increment,
    population int,
    date timestamp,
    primary key (id)
);