(ns customapi.routes.core
  (:require [customapi.routes.auth :refer [auth-routes]]
            [customapi.routes.clothes :refer [clothes-routes]]
            [customapi.routes.files :refer [files-routes]]
            [customapi.routes.math :refer [math-routes]]
            [customapi.routes.opeanapi :refer [openapi-routes]]
            [customapi.routes.secure :refer [secure-routes]]
            [customapi.routes.swagger :refer [swagger-routes]]))

(def routes [auth-routes
             clothes-routes
             files-routes
             math-routes
             secure-routes
             openapi-routes
             swagger-routes])