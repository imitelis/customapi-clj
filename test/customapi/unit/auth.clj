(ns customapi.unit.auth
  (:require [clojure.test :refer [deftest is testing]]
            [customapi.schemas.auth :as as]
            [customapi.unit.common :refer [valid-schema?]]))

(deftest test-login-params-schema
  (testing "LoginParams schema validation"
    (let [valid-login {:username "user1" :password "pass123"}
          invalid-login {:username "user1" :password 123}]
      (is (valid-schema? as/LoginParams valid-login))
      (is (not (valid-schema? as/LoginParams invalid-login))))))

(deftest test-login-response-schema
  (testing "LoginResponse schema validation"
    (let [valid-response {:token "some-token"}
          invalid-response {:token 123}]
      (is (valid-schema? as/LoginResponse valid-response))
      (is (not (valid-schema? as/LoginResponse invalid-response))))))

(deftest test-auth-param-schema
  (testing "AuthParam schema validation"
    (let [valid-param {:auth-token "some-auth-token"}
          invalid-param {:auth-token 123}]
      (is (valid-schema? as/AuthParam valid-param))
      (is (not (valid-schema? as/AuthParam invalid-param))))))

(deftest test-auth-response-schema
  (testing "AuthResponse schema validation"
    (let [valid-response {:username "user1"}
          invalid-response {:username 123}]
      (is (valid-schema? as/AuthResponse valid-response))
      (is (not (valid-schema? as/AuthResponse invalid-response))))))

(deftest test-error-response-schema
  (testing "ErrorResponse schema validation"
    (let [valid-error {:error "Some error occurred"}
          invalid-error {:error 404}]
      (is (valid-schema? as/ErrorResponse valid-error))
      (is (not (valid-schema? as/ErrorResponse invalid-error))))))
