-- 001-create-clothes-table.up.sql
CREATE TABLE IF NOT EXISTS clothes (
  uuid TEXT PRIMARY KEY,
  name TEXT,
  type TEXT,
  size TEXT
);
