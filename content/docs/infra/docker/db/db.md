---
weight: 1
slug: index
date: 2025-12-04
title: "Docker DB"
description: "Docker DB"
toc: true
---


## 이중화

### docker-compose.yml
```
# /docker-compose.yml

services:
  primary:
    image: postgres:15
    container_name: pg-primary
    environment:
      POSTGRES_PASSWORD: password
      POSTGRES_USER: postgres
      POSTGRES_DB: mydb
    ports:
      - "5432:5432"
    volumes:
      - primary_data:/var/lib/postgresql/data
      - ./primary/pg_hba.conf:/etc/postgresql/pg_hba.conf
    command: >
      postgres -c wal_level=replica
              -c max_wal_senders=10
              -c max_replication_slots=10
              -c hot_standby=on
              -c listen_addresses='*'
              -c hba_file=/etc/postgresql/pg_hba.conf

  replica:
    image: postgres:15
    container_name: pg-replica
    depends_on:
      - primary
    environment:
      POSTGRES_PASSWORD: password
      POSTGRES_USER: postgres
    ports:
      - "5433:5432"
    entrypoint: [ "/bin/bash", "/replica/replica.sh" ]
    volumes:
      - replica_data:/var/lib/postgresql/data
      - ./replica:/replica

volumes:
  primary_data:
  replica_data:

networks:
  pgnet:
```

### primary
```
# /primary/pg_hba.conf

# Local connections (required)
local   all             all                                     trust

# Allow replication from Docker network
host    replication     postgres        0.0.0.0/0                trust

# Allow all remote connections (for testing)
host    all             all             0.0.0.0/0                trust
```

### replica
```
# replica.sh

#!/bin/bash
set -e

echo "Waiting for primary..."
sleep 5

echo "Cleaning old data..."
rm -rf /var/lib/postgresql/data/*

echo "Cloning primary..."
PGPASSWORD=password pg_basebackup -h pg-primary -D /var/lib/postgresql/data -U postgres -Fp -Xs -P

echo "Configuring replica..."
cat <<EOF > /var/lib/postgresql/data/postgresql.auto.conf
primary_conninfo = 'host=pg-primary port=5432 user=postgres password=password'
hot_standby = on
EOF

touch /var/lib/postgresql/data/standby.signal

echo "Starting PostgreSQL replica..."
exec docker-entrypoint.sh postgres
```

### 실행 & 종료
```
$ docker-compose up -d
$ docker-compose down -v
```

### 로그 확인
```
$ docker logs pg-primary
$ docker logs pg-replica
```

### 복제 확인
```
$ docker exec -it pg-primary psql -U postgres -c "CREATE TABLE test_rep (id SERIAL PRIMARY KEY, msg TEXT);"
$ docker exec -it pg-primary psql -U postgres -c "SELECT * FROM pg_stat_replication;"
$ docker exec -it pg-replica psql -U postgres -c "SELECT * FROM pg_stat_replication;"
```
