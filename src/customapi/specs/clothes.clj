(ns customapi.specs.clothes
  (:require [clojure.spec.alpha :as s]))

(s/def ::uuid string?)
(s/def ::name string?)
(s/def ::type string?)
(s/def ::size number?)
(s/def ::cloth-without-uuid (s/keys :req-un [::name ::type ::size]))
(s/def ::cloth (s/keys :req-un [::uuid ::name ::type ::size]))
(s/def ::clothes-response-list (s/coll-of ::clothes-response :kind vector?))