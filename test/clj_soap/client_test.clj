(ns clj-soap.client-test
  (:use [clj-soap.client]
        [clojure.test]))

(deftest test-client-request
  (testing "Validate can get weather for Paris"
    ;; fix-me this wsdl url timesout
    #_(let [client (client-fn {:wsdl "http://www.webservicex.com/globalweather.asmx?WSDL"})
          result (client :GetWeather "Paris" "France")]
      (is (= "Data Not Found" result)))))
