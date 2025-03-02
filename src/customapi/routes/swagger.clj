(ns customapi.routes.swagger
  (:require [reitit.swagger :as swagger]))

(def swagger-routes
  ["/swagger.json"
   {:get {:no-doc true
          :swagger {:info {:title "my-api"
                           :description "swagger docs with [malli](https://github.com/metosin/malli) and reitit-ring"
                           :version "0.0.1"}
                           ;; used in /secure APIs below
                    :securityDefinitions {"auth" {:type :apiKey
                                                  :in :header
                                                  :name "Example-Api-Key"}}
                    :tags [{:name "files", :description "file api"}
                           {:name "math", :description "math api"}]}
          :handler (swagger/create-swagger-handler)}}])