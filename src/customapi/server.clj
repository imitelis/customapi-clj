(ns customapi.server
  (:require [customapi.config.docs :refer [docs]]
            [customapi.config.middlewares :refer [middlewares]]
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
  (jetty/run-jetty #'app {:port 3000, :join? false}))