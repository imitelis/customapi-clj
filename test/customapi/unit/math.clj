(ns customapi.unit.math
  (:require [clojure.test :refer [deftest is testing]]
            [customapi.schemas.math :as ms]
            [customapi.unit.common :refer [valid-schema?]]))

(deftest test-total-response-schema
  (testing "TotalResponse schema validation"
    (let [valid-params {:total 100}
          invalid-params {:total "not-an-int"}]
      (is (valid-schema? ms/TotalResponse valid-params))
      (is (not (valid-schema? ms/TotalResponse invalid-params))))))

(deftest test-two-op-params-schema
  (testing "TwoOpParams schema validation"
    (let [valid-params {:x 10 :y 20}
          invalid-params {:x 10 :y "string-instead-of-int"}]
      (is (valid-schema? ms/TwoOpParams valid-params))
      (is (not (valid-schema? ms/TwoOpParams invalid-params))))))

