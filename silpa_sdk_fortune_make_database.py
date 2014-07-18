# This script is used to making SQLite database for fortune module.
#
# DATABASE NAME : silpa_sdk_fortune.db
#
# DATABASE TABLES : 1) chanakya
#                   2) malayalam_proverbs
#                   3) thirukkural
#
# DATABASE FIELDS : 1) quote (TEXT)
#
# To add more quotes to database
# 1) Add paths to asset files in 'file_paths' list
# 2) Add table name for each asset files in 'table_names' list
# 3) Run script
# 4) Update changes in number of quote set in Fortune class
# 

import sqlite3
import codecs

conn = sqlite3.connect('silpa_sdk_fortune.db')
print "Opened database successfully";

file_paths = ["chanakya.dic", "malayalam_proverbs.dic", "thirukkural.dic"]

table_names = ["chanakya", "malayalam_proverbs", "thirukkural"]

for i in range(0, 3) :	

	conn.execute('CREATE TABLE {} (quote TEXT PRIMARY KEY);'.format(table_names[i]))	
	
	infile = codecs.open(file_paths[i], encoding='utf-8', errors='ignore')

	quote = ''
	for line in infile :
		
		if line == "%\n":
			quote = quote.strip()				
			conn.execute('''INSERT OR IGNORE INTO {} (quote) VALUES (?)'''.format(table_names[i]), (quote,))
			quote = ''
		else :
			quote += line	
	
	infile.close()
	
	print "Table ", table_names[i], " created successfully";	

conn.commit()
conn.close()
print "Database created successfully";
quit()
