(ns customapi.routes.core
  (:require [customapi.config.middleware :refer [use-middleware]]
            [customapi.config.openapi :refer [create-openapi]]
            [customapi.config.swagger :refer [create-swagger]]
            [customapi.routes.clothes :refer [clothes-routes]]
            [customapi.routes.files :refer [files-routes]]
            [customapi.routes.math :refer [math-routes]]
            [customapi.routes.openapi :refer [openapi-routes]]
            [reitit.ring :as ring]))

(def routes
  (ring/router
   [clothes-routes
    files-routes
    math-routes
    openapi-routes]
   use-middleware))

(def openapi-handler
  (ring/routes
   (create-swagger
    create-openapi)
   (ring/create-default-handler)))

#_((def routes
     (wrap-cors in-routes :access-control-allow-origin (:allowed-origin secrets))))