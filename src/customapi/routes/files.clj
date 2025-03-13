(ns customapi.routes.files
  (:require [customapi.handlers.files :as hf]
            [customapi.schemas.files :as sf]))

(def files-routes
  ["/files"
   {:tags #{"files"}}

   ["/data"
    {:post {:summary "Read data from a file"
            :parameters {:multipart sf/UploadParams}
            :responses {200 {:body sf/UploadResponse}}
            :handler hf/upload-handler}}]

   ["/download"
    {:get {:summary "Download a file"
           :swagger {:produces ["image/jpg"]}
           :responses {200 {:description "an image"
                            :content {"image/jpg" {:schema string?}}}
                       404 {:body {:error string?}}}
           :handler hf/download-handler}}]])