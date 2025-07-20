(ns customapi.unit.math-schemas
  (:require [clojure.test :refer [deftest is testing]]
            [customapi.aux.common :refer [valid-schema?]]
            [customapi.schemas.math :as ms]))

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

