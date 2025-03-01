(ns customapi.config.docs-handler
  (:require [customapi.config.openapi :refer [create-openapi]]
            [customapi.config.swagger :refer [create-swagger]]
            [reitit.ring :as ring]))

(def docs-handler
  (ring/routes
   (create-swagger
    create-openapi)
   (ring/create-default-handler)))