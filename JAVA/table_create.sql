CREATE TABLE Projects (
  id_project      number(5) NOT NULL, 
  project_name    varchar2(100) NOT NULL, 
  customer        varchar2(20) NOT NULL, 
  executor        varchar2(50) NOT NULL, 
  date_start      date NOT NULL, 
  date_finish     date NOT NULL, 
  project_cost    number(10,2) NOT NULL, 
  PRIMARY KEY (id_project));

  
CREATE SEQUENCE id_project;

--Projects(id_project,project_name,customer,executor,date_start, date_finish, project_cost)
INSERT INTO projects VALUES (id_project.nextval,'CRM for bank','PrivatBank','Project team #1',TO_DATE('2013-03-17', 'YYYY-MM-DD'),TO_DATE('2013-09-17', 'YYYY-MM-DD'),10000);
INSERT INTO projects VALUES (id_project.nextval,'some programm','some customer','Project team #2',TO_DATE('2013-05-19', 'YYYY-MM-DD'),TO_DATE('2013-10-19', 'YYYY-MM-DD'),15000);
