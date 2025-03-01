(ns customapi.server
  (:gen-class)
  (:require [customapi.config.secrets :refer [secrets]]
            [customapi.config.docs-handler :refer [docs-handler]]
            [customapi.db.core :refer [initialize-db]]
            [customapi.routes.core :refer [routes]]
            [reitit.ring :as ring]
            [ring.adapter.jetty :as jetty]))

(def app
  (ring/ring-handler
   routes
   docs-handler))

(defn -main []
  (initialize-db)
  (jetty/run-jetty #'app {:host (:host secrets), :port (:port secrets), :join? false}))