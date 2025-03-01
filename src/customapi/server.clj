(ns customapi.server
  (:gen-class)
  (:require [customapi.config.secrets :refer [secrets]]
            [customapi.db.core :refer [initialize-db]]
            [customapi.routes.core :refer [openapi-handler routes]]
            [reitit.ring :as ring]
            [ring.adapter.jetty :as jetty]))

(def app
  (ring/ring-handler
   routes
   openapi-handler))

(defn -main []
  (initialize-db)
  (jetty/run-jetty #'app {:host (:host secrets), :port (:port secrets), :join? false}))