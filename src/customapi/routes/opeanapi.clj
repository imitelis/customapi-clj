(ns customapi.routes.opeanapi
  (:require [reitit.openapi :as oa]))

(def openapi-routes
  ["/openapi.json"
   {:get {:no-doc true
          :openapi {:info {:title "CustomAPI"
                           :description "OpenAPI3-docs with reitit-http"
                           :version "0.0.1"}
                    ;; used in /secure APIs below
                    :components {:securitySchemes {"auth" {:type :apiKey
                                                           :in :header
                                                           :name "auth-api-header"}}}}
          :handler (oa/create-openapi-handler)}}])