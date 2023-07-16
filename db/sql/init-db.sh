!/bin/bash

set -e

psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" --dbname "$POSTGRES_DB" <<-EOSQL
\i /docker-entrypoint-initdb.d/sql-tables.sql
#\i /docker-entrypoint-initdb.d/sql-data.sql
EOSQL
