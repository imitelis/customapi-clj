(ns customapi.adapters.clothes)

(defn cloth-adapter [cloth]
  {:uuid (:clothes/uuid cloth)
   :name (:clothes/name cloth)
   :type (:clothes/type cloth)
   :size (Long/parseLong (:clothes/size cloth))})