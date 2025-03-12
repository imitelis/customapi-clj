(ns customapi.schemas.math)

(def TwoOpParams
  [:map
   [:x int?]
   [:y int?]])

(def TotalResponse
  [:map
   [:total int?]])
