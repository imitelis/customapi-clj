(ns customapi.integration.secure-test
  (:require [clojure.data.json :as json]
            [clojure.test :refer [deftest is testing]]
            [customapi.config.jwt :refer [jwt-sign]]
            [customapi.server :refer [app]]
            [ring.mock.request :refer [request]]))

(def valid-jwt
  (let [token (jwt-sign {:username "Dove"})]
    token))

(deftest secure-test
  (testing "Secret missing auth header"
    (let [response (-> (request :get "/secure/secret")
                       app :body slurp
                       (json/read-str :key-fn keyword))]
      (is (= "missing header" (:error response)))))

  (testing "Secret with valid header"
    (let [response (-> {:request-method :get
                        :uri "/secure/secret"
                        :headers {"auth-header" valid-jwt}}
                       app :body slurp
                       (json/read-str :key-fn keyword))]
      (println response)
      (is (= "I'm a secret!" (:message response)))))

  (testing "Greeting missing auth header"
    (let [response (-> (request :get "/secure/greeting")
                       app :body slurp
                       (json/read-str :key-fn keyword))]
      (is (= "missing header" (:error response)))))

  (testing "Greeting with valid header"
    (let [response (-> {:request-method :get
                        :uri "/secure/greeting"
                        :headers {"auth-header" valid-jwt}}
                       app :body slurp
                       (json/read-str :key-fn keyword))]
      (println response)
      (is (= "And your name is Dove" (:message response))))))