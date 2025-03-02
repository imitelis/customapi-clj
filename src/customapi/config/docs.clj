(ns customapi.config.docs
  (:require [reitit.ring :as ring]
            [reitit.swagger-ui :as swagger-ui]))

(def docs (ring/routes
           (swagger-ui/create-swagger-ui-handler
            {:path "/"
             :config {:validatorUrl nil
                      :urls [{:name "swagger", :url "swagger.json"}
                             {:name "openapi", :url "openapi.json"}]
                      :urls.primaryName "openapi"
                      :operationsSorter "alpha"}})
           (ring/create-default-handler)))