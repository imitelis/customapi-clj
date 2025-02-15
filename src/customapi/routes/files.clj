(ns customapi.routes.files
  (:require [clojure.java.io :as io]
            [customapi.specs.files :as fs]))

(def files-routes
  ["/files"
   {:tags ["files"]}

   ["/upload"
    {:post {:summary "upload a file"
            :parameters {:multipart ::fs/file-params}
            :responses {200 {:body ::fs/file-response}}
            :handler (fn [{{{:keys [file]} :multipart} :parameters}]
                       {:status 200
                        :body {:name (:filename file)
                               :size (:size file)}})}}]

   ["/download"
    {:get {:summary "downloads a file"
           :swagger {:produces ["image/png"]}
           :responses {200 {:description "an image"
                            :content {"image/png" {:schema string?}}}}
           :handler (fn [_]
                      {:status 200
                       :headers {"Content-Type" "image/png"}
                       :body (io/input-stream
                              (io/resource "reitit.png"))})}}]])