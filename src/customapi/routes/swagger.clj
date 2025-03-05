(ns customapi.routes.swagger
  (:require [reitit.swagger :as swagger]))

(def swagger-routes
  ["/swagger.json"
   {:get {:no-doc true
          :swagger {:info {:title "CustomAPI"
                           :description "Swagger-docs with reitit-http"
                           :version "0.0.1"}
                           ;; used in /secure APIs below
                    :securityDefinitions {"auth" {:type :apiKey
                                                  :in :header
                                                  :name "auth-api-header"}}
                    :tags [{:name "clothes", :description "clothes api"}
                           {:name "files", :description "file api"}
                           {:name "math", :description "math api"}
                           {:name "secure", :description "secure api"}]}
          :handler (swagger/create-swagger-handler)}}])