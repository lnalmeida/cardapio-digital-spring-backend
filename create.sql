create table category (id bigserial not null, title varchar(255), primary key (id));
create table food (price float(53) not null, category_id bigint, id bigserial not null, description TEXT, img_url varchar(255), title varchar(255), primary key (id));
alter table if exists food add constraint FKkomdx99dhk2cveaxugl2lws2u foreign key (category_id) references category;
