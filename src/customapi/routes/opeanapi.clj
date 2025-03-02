(ns customapi.routes.opeanapi
  (:require [reitit.openapi :as oa]))

(def openapi-routes ["/openapi.json"
                     {:get {:no-doc true
                            :openapi {:info {:title "my-api"
                                             :description "openapi3 docs with [malli](https://github.com/metosin/malli) and reitit-ring"
                                             :version "0.0.1"}
                          ;; used in /secure APIs below
                                      :components {:securitySchemes {"auth" {:type :apiKey
                                                                             :in :header
                                                                             :name "Example-Api-Key"}}}}
                            :handler (oa/create-openapi-handler)}}])