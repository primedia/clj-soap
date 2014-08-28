(defproject com.rentpath/clj-soap "0.2.3"
  :description "SOAP Client and Server using Apache Axis2."
  :url "https://github.com/primedia/clj-soap"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure                    "1.5.1"]
                 [org.apache.axis2/axis2-adb             "1.6.2"]
                 [org.apache.axis2/axis2-transport-http  "1.6.2"]
                 [org.apache.axis2/axis2-transport-local "1.6.2"]]
  :source-paths ["src" "test"]
  :aot [clj-soap.test.core]
  :repositories [["releases" {:url "http://nexus.idg.primedia.com/nexus/content/repositories/primedia"
                              :sign-releases false}]])

