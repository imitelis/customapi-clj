(ns customapi.config.openapi)

(def create-openapi
  {:path "/"
   :config {:validatorUrl nil
            :urls [{:name "swagger" :url "swagger.json"}
                   {:name "openapi" :url "openapi.json"}]
            :urls.primaryName "openapi"
            :operationsSorter "alpha"}})