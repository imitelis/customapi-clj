(ns customapi.routes.core
  (:require [customapi.config.middleware :refer [use-middleware]]
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