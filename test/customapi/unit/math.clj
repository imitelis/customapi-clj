(ns customapi.unit.math
  (:require [clojure.test :refer [deftest is testing]]
            [customapi.schemas.math :as ms]
            [customapi.unit.common :refer [valid-schema?]]))

(deftest test-two-op-params-schema
  (testing "TwoOpParams schema validation"
    (let [valid-params {:x 10 :y 20}
          invalid-params {:x 10 :y "string-instead-of-int"}]
      (println "Valid Params Test: " (valid-schema? ms/TwoOpParams valid-params))
      (println "Invalid Params Test: " (valid-schema? ms/TwoOpParams invalid-params))
      (is (valid-schema? ms/TwoOpParams valid-params))
      (is (not (valid-schema? ms/TwoOpParams invalid-params))))))

