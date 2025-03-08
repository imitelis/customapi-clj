(ns customapi.schemas.files
  (:require [reitit.ring.malli :as rm]))

(def UploadParams
  [:map [:file rm/temp-file-part]])

(def UploadResponse
  [:map [:name string?] [:size int?]])