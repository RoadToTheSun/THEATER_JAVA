-- MVStore
CREATE ALIAS IF NOT EXISTS READ_BLOB FOR "org.h2.tools.Recover.readBlob";
CREATE ALIAS IF NOT EXISTS READ_CLOB FOR "org.h2.tools.Recover.readClob";
CREATE ALIAS IF NOT EXISTS READ_BLOB_DB FOR "org.h2.tools.Recover.readBlobDb";
CREATE ALIAS IF NOT EXISTS READ_CLOB_DB FOR "org.h2.tools.Recover.readClobDb";
CREATE ALIAS IF NOT EXISTS READ_BLOB_MAP FOR "org.h2.tools.Recover.readBlobMap";
CREATE ALIAS IF NOT EXISTS READ_CLOB_MAP FOR "org.h2.tools.Recover.readClobMap";
-- LOB
CREATE TABLE IF NOT EXISTS INFORMATION_SCHEMA.LOB_BLOCKS(LOB_ID BIGINT, SEQ INT, DATA BINARY, PRIMARY KEY(LOB_ID, SEQ));
-- lobMap.size: 0
-- lobData.size: 0
-- Meta
-- chunk.17 = chunk:17,block:2,len:1,liveMax:0,livePages:0,map:d,max:3c0,next:3,pages:2,root:5c000005c92,time:b637,unused:b765,unusedAtVersion:1a,version:17,pinCount:0
-- chunk.19 = chunk:19,block:3,len:1,liveMax:0,livePages:0,map:d,max:300,next:4,pages:1,root:64000002e52,time:b6e0,unused:b744,unusedAtVersion:19,version:19,pinCount:0
-- chunk.1a = chunk:1a,block:4,len:1,liveMax:0,livePages:0,map:e,max:300,next:6,pages:1,root:68000002e52,time:b744,unused:b765,unusedAtVersion:1a,version:1a,pinCount:0
-- map.1 = name:openTransactions
-- map.3 = name:table.0
-- map.4 = name:lobMap
-- map.5 = name:lobRef
-- map.6 = name:lobData
-- map.e = name:undoLog.1,createVersion:19
-- name.lobData = 6
-- name.lobMap = 4
-- name.lobRef = 5
-- name.openTransactions = 1
-- name.table.0 = 3
-- name.undoLog.1 = e
-- root.3 = 6c000002e4a
-- Tables
---- Schema SET ----
SET CREATE_BUILD 200;
---- Table Data ----
---- Schema ----
CREATE USER IF NOT EXISTS "KOLUPANOV" SALT '99faaca5427467ae' HASH 'a4d9bf2cc964a324f60b8e6c903d9768b922fb4c1a8b4317509969c0645461e9' ADMIN;
DROP ALIAS READ_BLOB;
DROP ALIAS READ_CLOB;
DROP ALIAS READ_BLOB_DB;
DROP ALIAS READ_CLOB_DB;
DROP ALIAS READ_BLOB_MAP;
DROP ALIAS READ_CLOB_MAP;
DROP TABLE IF EXISTS INFORMATION_SCHEMA.LOB_BLOCKS;
