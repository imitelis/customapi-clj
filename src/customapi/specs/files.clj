(ns customapi.specs.files
  (:require [clojure.spec.alpha :as s]
            [reitit.ring.middleware.multipart :as multipart]))

(s/def ::file multipart/temp-file-part)
(s/def ::file-params (s/keys :req-un [::file]))

(s/def ::name string?)
(s/def ::size int?)
(s/def ::file-response (s/keys :req-un [::name ::size]))