(ns customapi.server
  (:gen-class)
  (:require [customapi.config.database :refer [initialize-db]]
            [customapi.config.middleware :refer [use-middleware]]
            [customapi.config.openapi :refer [create-openapi]]
            [customapi.config.swagger :refer [create-swagger]]
            [customapi.routes.files :refer [files-routes]]
            [customapi.routes.math :refer [math-routes]]
            [customapi.routes.openapi :refer [openapi-routes]]
            [reitit.ring :as ring]
            [ring.adapter.jetty :as jetty]))

(def app
  (ring/ring-handler
   (ring/router
    [files-routes
     math-routes
     openapi-routes]
    use-middleware)
   (ring/routes
    (create-swagger
     create-openapi)
    (ring/create-default-handler))))

(defn -main []
  (initialize-db)
  (println "Starting server on port 3000")
  (jetty/run-jetty #'app {:port 3000, :join? false}))