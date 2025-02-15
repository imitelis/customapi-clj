(ns customapi.config.swagger
  (:require [reitit.swagger-ui :as swagger-ui]))

(def create-swagger
  swagger-ui/create-swagger-ui-handler)