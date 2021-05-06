# LOLDB

I started this project to improve my Java skills and to practice writing SOLID code.

There are three phases for the project:

1. Import the data from the files into the database with adequate testing.
2. Create PowerBI reports to analyse the data
3. Create a web application for the data

Extra: Instead of using files connect to the api to retrieve the data to populate the database tables in realtime

Database schema located at: loldb/schema

Files at: loldb/datafiles

Currenting in the first phase. I have successfully populated the champion, role and championrole tables using the json file. The activation of populating these tables are within the unit test area within the area: src/test/java/org/toby/database/insertion.

Stored procedures have been created database side to verify that the insertions were successful. These can be found in the area: loldb/testscripts.

Continuing with the project by populating the other tables. Will update this document when appropiate.
