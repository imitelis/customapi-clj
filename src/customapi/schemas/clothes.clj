(ns customapi.schemas.clothes)

(def Cloth
  [:map
   [:uuid string?]
   [:name string?]
   [:type string?]
   [:size number?]])

(def ClothWithoutUuid
  [:map
   [:name string?]
   [:type string?]
   [:size number?]])

(def ClothesQuery
  [:map
   [:clothes-name {:optional true} [:maybe string?]]
   [:clothes-type {:optional true} [:maybe string?]]])

(def Clothes
  [:vector Cloth])