(ns customapi.handlers.files
  (:require [clojure.java.io :as io]))

(def upload-handler
  (fn [{{{:keys [file]} :multipart} :parameters}]
    {:status 200
     :body {:name (:filename file)
            :size (:size file)}}))

(def download-handler
  (fn [_]
    (let [resource (io/resource "portland.jpg")]
      (if resource
        {:status 200
         :headers {"Content-Type" "image/jpg"}
         :body (io/input-stream resource)}
        {:status 404
         :body {:error "File not found"}}))))
