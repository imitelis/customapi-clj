(ns customapi.specs.clothes
  (:require [clojure.spec.alpha :as s]))

(s/def ::uuid string?)
(s/def ::name string?)
(s/def ::type string?)
(s/def ::size number?)
(s/def ::cloth-without-uuid (s/keys :req-un [::name ::type ::size]))
(s/def ::cloth (s/keys :req-un [::uuid ::name ::type ::size]))
(s/def ::clothes (s/coll-of ::cloth :kind vector?))

(s/def ::clothes-name string?)
(s/def ::clothes-type string?)
(s/def ::clothes-query (s/keys :opt-un [::clothes-name ::clothes-type]))