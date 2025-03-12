(ns customapi.unit.secure
  (:require [clojure.test :refer [deftest is testing]]
            [customapi.schemas.secure :as ss]
            [customapi.unit.common :refer [valid-schema?]]))

(deftest test-error-response-schema
  (testing "ErrorResponse schema validation"
    (let [valid-response {:error "Some error message"}
          invalid-response {:error 123}]
      (is (valid-schema? ss/ErrorResponse valid-response))
      (is (not (valid-schema? ss/ErrorResponse invalid-response))))))

(deftest test-message-response-schema
  (testing "MessageResponse schema validation"
    (let [valid-response {:message "This is a message"}
          invalid-response {:message 123}]
      (is (valid-schema? ss/MessageResponse valid-response))
      (is (not (valid-schema? ss/MessageResponse invalid-response))))))
