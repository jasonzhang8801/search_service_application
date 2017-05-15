# Search Service Application

## System Overview
I developed a Java database application for users to query on Yelp restaurant dataset

## How to use
- create table (create_table.sql)
- import both populate.java and hw3.java into java IDE;
- add external libraries: 
    * json.jar // json parsing java library
    * ojdbc6.jar // oracle database driver library
    * swingx-all-1.6.4.jar // java GUI library
    * rs2xml.jar // GUI table generator library
- compile populate.java;
- pass arguments into populate.class and run (arguments: yelp_business.json, yelp_review.json, yelp_checkin.json, yelp_user.json)
- add index to the tables (create_index.sql)
- complie and run hw3.java
- use java GUI to query 
- drop table (drop_table.sql)

## License
Copyright 2017 Sen Zhang
