(ns customapi.adapters.clothes)

(defn cloth-adapter [cloth]
  {:uuid (:clothes/uuid cloth)
   :name (:clothes/name cloth)
   :type (:clothes/type cloth)
   :size (Double/parseDouble (:clothes/size cloth))})

(defn clothes-adapter [clothes]
  (vec (map cloth-adapter clothes)))