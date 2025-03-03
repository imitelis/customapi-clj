(ns customapi.handlers.files
  (:require [clojure.java.io :as io]))

(def upload-handler
  (fn [{{{:keys [file]} :multipart} :parameters}]
    {:status 200
     :body {:name (:filename file)
            :size (:size file)}}))

(def download-handler
  (fn [_]
    {:status 200
     :headers {"Content-Type" "image/png"}
     :body (-> "/picture.png"
               (io/resource)
               (io/input-stream))}))