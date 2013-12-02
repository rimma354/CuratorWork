
--TASK # 1

--creating package
create package pack as
  TYPE  name_type IS TABLE of VARCHAR2(20) INDEX BY BINARY_INTEGER;
  TYPE  salary_type IS TABLE of NUMBER INDEX BY BINARY_INTEGER;
  TYPE  hire_date_type IS TABLE of DATE INDEX BY BINARY_INTEGER;

  TYPE  persons IS RECORD ( 
        person_name name_type,
        salary salary_type,
        hire_date hire_date_type ); 
  procedure  work_with_record_persons(some_rec persons );
end pack;

--creating package body and procedure

create or replace 
package body pack as
	procedure work_with_record_persons(some_rec persons) is
	total_salary NUMBER;
  names_length NUMBER;
  dates_dif NUMBER;
  min_date DATE;
  max_date DATE;
	begin
	total_salary:=0;
  names_length:=0;
  FOR i IN some_rec.salary.FIRST .. some_rec.salary.LAST
		LOOP
		total_salary:=total_salary+some_rec.salary(i);
    names_length:=names_length+length(some_rec.person_name(i));
  end loop;
  min_date:=some_rec.hire_date(some_rec.hire_date.FIRST);
  max_date:=some_rec.hire_date(some_rec.hire_date.FIRST);
  FOR i IN some_rec.hire_date.FIRST..some_rec.hire_date.LAST
  LOOP
    IF min_date>some_rec.hire_date(i) THEN
       min_date:=some_rec.hire_date(i);
    END IF;
    IF max_date<some_rec.hire_date(i) THEN
       max_date:=some_rec.hire_date(i);
    END IF;
  END LOOP;
  dates_dif:=(max_date-min_date)*86400;
	dbms_output.put_line('Summa of number fields');
  dbms_output.put_line(total_salary);
	dbms_output.put_line('Length of varchar2 fields');
  dbms_output.put_line(names_length);
 	dbms_output.put_line('Minimum hire date is ');
  dbms_output.put_line(min_date);
	dbms_output.put_line('Maximum hire_date is');
  dbms_output.put_line(max_date);
  dbms_output.put_line('Difference between max and min dates in sec');
  dbms_output.put_line(dates_dif);

	end;	
end;

--calling procedure

declare
  rec pack.persons;
  salary_1 pack.salary_type;
  name_1 pack.name_type;
  hire_date_1 pack.hire_date_type;
begin
  salary_1(1):=8000;
  salary_1(2):=7000;
  salary_1(3):=10000;
  salary_1(4):=6000;
  name_1(1):='John';
  name_1(2):='Anna';
  name_1(3):='Peter';
  name_1(4):='Kolin';
  hire_date_1(1):='25-AUG-2013';
  hire_date_1(2):='21-NOV-2012';
  hire_date_1(3):='17-JAN-2013';
  hire_date_1(4):='7-SEP-2013';
  rec.salary:=salary_1;
  rec.person_name:=name_1;
  rec.hire_date:=hire_date_1;
  pack.work_with_record_persons(rec);
end;
/

-------------------------------------------------------------------

--TASK # 2

--creating package
create or replace 
package pack2 as
  TYPE  name_type IS TABLE of VARCHAR2(20) INDEX BY BINARY_INTEGER;
  TYPE  salary_type IS TABLE of NUMBER INDEX BY BINARY_INTEGER;
  TYPE  hire_date_type IS TABLE of DATE INDEX BY BINARY_INTEGER;

  TYPE  persons IS RECORD ( 
        person_name name_type,
        salary salary_type,
        hire_date hire_date_type ); 
  function  sort_persons_by_increase(some_rec persons) return persons;
end pack2;

--creating package body and function
create or replace 
package body pack2 as
	function sort_persons_by_increase(some_rec persons) 
  return persons is
  sorted_rec persons;
  temp_salary NUMBER;
  temp_date DATE;
  min_position_salary NUMBER;
  min_position_date NUMBER;
  
	begin
sorted_rec:=some_rec;
FOR  i IN sorted_rec.salary.FIRST..sorted_rec.salary.LAST
LOOP
min_position_salary:=i;
  for j in i+1..sorted_rec.salary.LAST
  loop
    if sorted_rec.salary(j)<sorted_rec.salary(min_position_salary) then
    min_position_salary:=j;
    end if;
  end loop;
temp_salary:=sorted_rec.salary(i);
sorted_rec.salary(i):=sorted_rec.salary(min_position_salary);
sorted_rec.salary(min_position_salary):=temp_salary;
END LOOP;

FOR  i IN sorted_rec.hire_date.FIRST..sorted_rec.hire_date.LAST
LOOP
min_position_date:=i;
  for j in i+1..sorted_rec.hire_date.LAST
  loop
    if sorted_rec.hire_date(j)<sorted_rec.hire_date(min_position_date) then
    min_position_date:=j;
    end if;
  end loop;
temp_date:=sorted_rec.hire_date(i);
sorted_rec.hire_date(i):=sorted_rec.hire_date(min_position_date);
sorted_rec.hire_date(min_position_date):=temp_date;
END LOOP;
return sorted_rec;
	end;	
end;

--callinc function and testing its work

declare
  rec pack2.persons;
  salary_1 pack2.salary_type;
  name_1 pack2.name_type;
  hire_date_1 pack2.hire_date_type;
  sorted_rec pack2.persons;
begin
  salary_1(1):=8000;
  salary_1(2):=7000;
  salary_1(3):=10000;
  salary_1(4):=6000;
  name_1(1):='John';
  name_1(2):='Anna';
  name_1(3):='Peter';
  name_1(4):='Kolin';
  hire_date_1(1):='25-AUG-2013';
  hire_date_1(2):='21-NOV-2012';
  hire_date_1(3):='17-JAN-2013';
  hire_date_1(4):='7-SEP-2013';
  rec.salary:=salary_1;
  rec.person_name:=name_1;
  rec.hire_date:=hire_date_1; 
  sorted_rec:=pack2.sort_persons_by_increase(rec);
  FOR i IN sorted_rec.salary.FIRST..sorted_rec.salary.LAST
  LOOP
    dbms_output.put_line(sorted_rec.salary(i));
  END LOOP;
    FOR i IN sorted_rec.hire_date.FIRST..sorted_rec.hire_date.LAST
  LOOP
    dbms_output.put_line(sorted_rec.hire_date(i));
  END LOOP;
end;
/
