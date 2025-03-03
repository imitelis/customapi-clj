(ns customapi.routes.core
  (:require [customapi.routes.files :refer [files-routes]]
            [customapi.routes.math :refer [math-routes]]
            [customapi.routes.opeanapi :refer [openapi-routes]]
            [customapi.routes.secure :refer [secure-routes]]
            [customapi.routes.swagger :refer [swagger-routes]]))

(def routes [files-routes
             math-routes
             secure-routes
             openapi-routes
             swagger-routes])