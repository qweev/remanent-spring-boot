cd "C:\Program Files\MySQL\MySQL Server 5.7\bin"
mysqldump -u admin -padmin sklep > e:\sklep_backup1.sql
timeout 5
del e:\sklep_backup2.sql
timeout 300
cd "C:\Program Files\MySQL\MySQL Server 5.7\bin"
mysqldump -u admin -padmin sklep > e:\sklep_backup2.sql
timeout 5
del e:\sklep_backup1.sql