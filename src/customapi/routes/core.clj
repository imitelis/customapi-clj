(ns customapi.routes.core
  (:require [customapi.config.middlewares :refer [use-middlewares]]
            [customapi.routes.clothes :refer [clothes-routes]]
            [customapi.routes.files :refer [files-routes]]
            [customapi.routes.math :refer [math-routes]]
            [customapi.routes.openapi :refer [openapi-routes]]
            [customapi.routes.secure :refer [secure-routes]]
            [reitit.ring :as ring]))

(def routes
  (ring/router
   [clothes-routes
    files-routes
    math-routes
    secure-routes
    openapi-routes]
   use-middlewares))