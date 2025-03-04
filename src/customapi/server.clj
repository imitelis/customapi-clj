(ns customapi.server
  (:require [customapi.config.docs :refer [docs]]
            [customapi.config.middlewares :refer [middlewares]]
            [customapi.config.secrets :refer [secrets]]
            [customapi.db.core :refer [initialize-db!]]
            [customapi.routes.core :refer [routes]]
            [reitit.ring :as ring]
            [ring.adapter.jetty :as jetty]))

(def app
  (ring/ring-handler
   (ring/router
    routes
    middlewares)
   docs))

(defn -main []
  (initialize-db!)
  (jetty/run-jetty #'app {:host (:host secrets), :port (:port secrets), :join? false}))