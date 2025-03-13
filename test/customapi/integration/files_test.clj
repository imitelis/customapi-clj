(ns customapi.integration.files-test
  (:require [clojure.java.io :as io]
            [clojure.test :refer [deftest is testing]]
            [customapi.server :refer [app]]
            [ring.mock.request :as mock]))

(deftest files-test
  (testing "Download a file"
    (let [response (app (mock/request :get "/files/download"))
          response-body (slurp (:body response) :encoding "ISO-8859-1")
          expected-file (io/file "resources/portland.jpg")
          expected-body (slurp expected-file :encoding "ISO-8859-1")]

      (is (= 200 (:status response)))
      (is (= "image/jpg" (get-in response [:headers "Content-Type"])))
      (is (= (.length expected-file) (.length response-body)))
      (is (= expected-body response-body))))

  (testing "Data of a file"
    (let [file (clojure.java.io/file "resources/portland.jpg")
          multipart-temp-file-part {:tempfile file
                                    :size (.length file)
                                    :filename "portland.jpg"
                                    :content-type "image/jpg"}]

      (is (= (-> {:request-method :post
                  :uri "/files/data"
                  :multipart-params {:file multipart-temp-file-part}}
                 app :body slurp)
             "{\"name\":\"portland.jpg\",\"size\":864498}")))))
