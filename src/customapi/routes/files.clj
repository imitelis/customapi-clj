(ns customapi.routes.files
  (:require [customapi.handlers.files :as hf]
            [customapi.schemas.files :as sf]))

(def files-routes
  ["/files"
   {:tags #{"files"}}

   ["/data"
    {:post {:summary "Read data from a file"
            :parameters {:multipart sf/upload-params}
            :responses {200 {:body sf/upload-response}}
            :handler hf/upload-handler}}]

   ["/download"
    {:get {:summary "Download a file"
           :swagger {:produces ["image/png"]}
           :responses {200 {:description "an image"
                            :content {"image/png" {:schema string?}}}}
           :handler hf/download-handler}}]])