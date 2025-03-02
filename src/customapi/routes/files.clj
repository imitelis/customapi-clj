(ns customapi.routes.files
  (:require [clojure.java.io :as io]
            [reitit.ring.malli :as rm]))

(def files-routes
  ["/files"
   {:tags #{"files"}}

   ["/upload"
    {:post {:summary "upload a file"
            :parameters {:multipart [:map [:file rm/temp-file-part]]}
            :responses {200 {:body [:map [:name string?] [:size int?]]}}
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
                       :body (-> "reitit.png"
                                 (io/resource)
                                 (io/input-stream))})}}]])