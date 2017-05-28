## Shadow News Crawler
* Crawl news from multi suppliers
	 *  `SkyNews`: technology news  `http://feeds.skynews.com/feeds/rss/technology.xml`
	 *  `IT News`: it news `https://www.itnews.com.au/RSS/rss.ashx"`
* Store news into the `DISK` as rss file named with `article.rss`
* Also store news into the DB by using `MySQL`, about tables structure, refer to [shadow-news-entity](https://github.com/chariot9/shadow-news-entity) project 
* Multi suppliers crawlers can be executing by IDE or executing by shell scripts.
## Architecture 
* This project is built base on `DDD-Architecture` and `TDD programming`. Built by some layers like as `Domain`, `Infrastructure`, `interface`, `application`, and `repository`. 
* This project are using JPA and EclipseLink by `infrastructure layer` to access MySQL DB
* `ROME` is used as a library for working with `rss file`
* `Java 8` is the main language level

## Getting support
If there are any problems, please feel free to contact to me or create new pull request  
* Email: trungvu.inside@gmail.com
## Check out sources
* git clone git@github.com:chariot9/shadow-news-crawler.git
## Compile and run API
### Compile by maven
Execute: `mvn clean install`  
### Run by IDE:
* Run main method in `NewsBootstrap` by adding parameters to main class like as `/data/news/skynews 1 20170524 20170528`
* About the meaning of each parameters:
	* `args[0]`: folder to store news file in the the disk  
	* `args[1]`: SupplierID, for `SkyNews` is 1 and `ITNews` is 2
	* `args[2]`: Date for getting news with published from it
	* `args[3]`: Date for getting news with published to it
### Compile and run using scripts
To _set up all necessary environment and build jar file_, run the following shell script in project folder:

`./shell/release`

To _run the program_, execute the following command:

`cd /data/shell/shadow/execute && ./diamond_exe.sh`

## License
Created by Trung, Yokohama Japan 2017 
