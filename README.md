# webflux-playground
開発用MySQLは `docker compose up -d --build` で起動する。接続先は `127.0.0.1:3306`。固定したMySQL 8.4系列のイメージへセキュリティ修正を加え、サービスで使用しないMySQL Shellは含めない。通常の `mysql` クライアントは利用できる。
