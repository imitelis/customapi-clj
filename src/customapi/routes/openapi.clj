(ns customapi.routes.openapi
  (:require [reitit.openapi :as openapi]
            [reitit.swagger :as swagger]))

(def openapi-routes
  [["/swagger.json"
    {:get {:no-doc true
           :swagger {:info {:title "customapi"}}
           :handler (swagger/create-swagger-handler)}}]
   ["/openapi.json"
    {:get {:no-doc true
           :openapi {:info {:title "Custom API"
                            :description "OpenAPI3-docs with reitit-http"
                            :version "0.0.1"}
                     :components {:securitySchemes {"auth" {:type :apiKey
                                                            :in :header
                                                            :name "Example-Api-Key"}}}}
           :handler (openapi/create-openapi-handler)}}]])