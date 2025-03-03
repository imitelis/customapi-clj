(ns customapi.schemas.files
  (:require [reitit.ring.malli :as rm]))

(def upload-params
  [:map [:file rm/temp-file-part]])

(def upload-response
  [:map [:name string?] [:size int?]])