(ns customapi.specs.clothes
  (:require [clojure.spec.alpha :as s]))

(s/def ::id int?)
(s/def ::name string?)
(s/def ::type string?)
(s/def ::size int?)
(s/def ::cloth-parameter (s/keys :req-un [::name ::type ::size]))
(s/def ::clothes-response (s/keys :req-un [::id ::name ::type ::size]))