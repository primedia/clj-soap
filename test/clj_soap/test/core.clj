(ns clj-soap.test.core
  (:require [clj-soap.core :refer [defservice client-fn serve]]
            [clojure.test :refer [deftest is]]))

(def test-value (ref false))

(defservice jp.myclass.MyApp
  (changeval [^String string] (dosync (ref-set test-value string)))
  (hypotenuse ^Double [^Double x ^Double y] (Math/sqrt (+ (* x x) (* y y)))))

(deftest test-my-app
  (serve "jp.myclass.MyApp")
  (let [cl (client-fn "http://localhost:6060/axis2/services/MyApp?wsdl")]
    (is (= 5.0 (cl :hypotenuse 3 4)) "SOAP call with return value")
    (cl :changeval "piyopiyo")
    (is (= "piyopiyo" @test-value) "SOAP call without return value")))

;;;; Test for exteral SOAP service
(let [client (client-fn "http://www.thomas-bayer.com/axis2/services/BLZService?wsdl")]
  (client :getBank "10070000"))
