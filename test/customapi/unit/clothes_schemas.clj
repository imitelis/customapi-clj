(ns customapi.unit.clothes-schemas
  (:require [clojure.test :refer [deftest is testing]]
            [customapi.schemas.clothes :as cs]
            [customapi.unit.common :refer [valid-schema?]]))

(deftest test-cloth-schema
  (testing "Cloth schema validation"
    (let [valid-cloth {:uuid "12345" :name "Shirt" :type "Casual" :size 42}
          invalid-cloth {:uuid "12345" :name "Shirt" :type "Casual" :size "large"}]
      (is (valid-schema? cs/Cloth valid-cloth))
      (is (not (valid-schema? cs/Cloth invalid-cloth))))))

(deftest test-cloth-without-uuid-schema
  (testing "ClothWithoutUuid schema validation"
    (let [valid-cloth-without-uuid {:name "Shirt" :type "Casual" :size 42}
          invalid-cloth-without-uuid {:name "Shirt" :type "Casual" :size "large"}]
      (is (valid-schema? cs/ClothWithoutUuid valid-cloth-without-uuid))
      (is (not (valid-schema? cs/ClothWithoutUuid invalid-cloth-without-uuid))))))

(deftest test-clothes-query-schema
  (testing "ClothesQuery schema validation"
    (let [valid-query {:clothes-name "Shirt" :clothes-type "Casual"}
          invalid-query-1 {:clothes-name "Shirt" :clothes-type 123}
          invalid-query-2 {:clothes-name 123 :clothes-type "Casual"}
          invalid-query-3 {:clothes-name 123 :clothes-type 456}]
      (is (valid-schema? cs/ClothesQuery valid-query))
      (is (not (valid-schema? cs/ClothesQuery invalid-query-1)))
      (is (not (valid-schema? cs/ClothesQuery invalid-query-2)))
      (is (not (valid-schema? cs/ClothesQuery invalid-query-3))))))

(deftest test-clothes-schema
  (testing "Clothes schema validation"
    (let [valid-clothes [{:uuid "12345" :name "Shirt" :type "Casual" :size 42}
                         {:uuid "67890" :name "Jeans" :type "Denim" :size 34}]
          invalid-clothes [{:uuid "12345" :name "Shirt" :type "Casual" :size 42}
                           {:uuid "67890" :name "Jeans" :type "Denim" :size "large"}]]
      (is (valid-schema? cs/Clothes valid-clothes))
      (is (not (valid-schema? cs/Clothes invalid-clothes))))))
