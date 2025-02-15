(ns customapi.specs.math
  (:require [clojure.spec.alpha :as s]))

(s/def ::x number?)
(s/def ::y number?)
(s/def ::total number?)
(s/def ::math-one-param (s/keys :req-un [::x]))
(s/def ::math-two-params (s/keys :req-un [::x ::y]))
(s/def ::math-response (s/keys :req-un [::total]))
