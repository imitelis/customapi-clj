(ns customapi.schemas.math)

(def TotalResponse
  [:map
   [:total number?]])

(def TwoOpParams
  [:map
   [:x number? :y number?]])
