(ns customapi.db.core
  (:require [customapi.config.secrets :refer [secrets]]
            [ragtime.jdbc :as jdbc]
            [ragtime.repl :as repl]))

(defn get-db-name []
  (let [env (:env secrets)]
    (if (= env "test")
      (:db-test secrets)
      (:db-name secrets))))

(def ^:dynamic db-spec
  {:connection-uri (get-db-name)})

(def migration-config
  {:datastore  (jdbc/sql-database db-spec)
   :migrations (jdbc/load-resources "migrations")})

(defn migrate! []
  (repl/migrate migration-config))

(defn rollback! []
  (repl/rollback migration-config))

(defn initialize-db! []
  (migrate!))