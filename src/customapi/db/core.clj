(ns customapi.db.core
  (:require [customapi.config.secrets :refer [secrets]]
            [ragtime.jdbc :as jdbc]
            [ragtime.repl :as repl]))

(defn get-db-name []
  (let [config secrets
        env (System/getenv "ENV")
        db-name (if (= env "test")
                  "jdbc:sqlite::memory:"
                  (:db-name config))]
    db-name))

(def db-spec
  {:connection-uri (get-db-name)})

(def migration-config
  {:datastore  (jdbc/sql-database {:connection-uri (get-db-name)})
   :migrations (jdbc/load-resources "migrations")})

(defn migrate! []
  (repl/migrate migration-config))

(defn rollback! []
  (repl/rollback migration-config))

(defn initialize-db! []
  (migrate!))
