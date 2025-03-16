(ns customapi.unit.files-schemas
  (:require
   [clojure.test :refer [deftest is testing]]
   [customapi.schemas.files :as fs]
   [customapi.unit.common :refer [valid-schema?]]))

(deftest test-upload-response-schema
  (testing "UploadResponse schema validation"
    (let [valid-upload-response {:name "file.txt" :size 1234}
          invalid-upload-response {:name "file.txt" :size "not-an-integer"}]
      (is (valid-schema? fs/UploadResponse valid-upload-response))
      (is (not (valid-schema? fs/UploadResponse invalid-upload-response))))))
