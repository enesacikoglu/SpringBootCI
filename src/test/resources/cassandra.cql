CREATE KEYSPACE IF NOT EXISTS cimriKeyspace WITH durable_writes = true AND replication = { 'replication_factor' : 1, 'class' : 'SimpleStrategy' };
CREATE TABLE IF NOT EXISTS Product (id text PRIMARY KEY,url text,priceOnDateMap map<text,text>);
CREATE INDEX IF NOT EXISTS URLindex ON Product (url);
