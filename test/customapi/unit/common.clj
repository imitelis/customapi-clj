(ns customapi.unit.common
  (:require [malli.core :as m]))

(defn valid-schema? [schema data]
  (m/validate schema data))
